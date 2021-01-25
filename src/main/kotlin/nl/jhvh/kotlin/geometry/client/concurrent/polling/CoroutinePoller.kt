package nl.jhvh.kotlin.geometry.client.concurrent.polling

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.jhvh.kotlin.geometry.model.twodimensional.TwoDimensional
import nl.jhvh.kotlin.util.logger
import nl.jhvh.kotlin.util.minus
import java.time.LocalDateTime
import java.util.*
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

interface Poller<T> {
    @ExperimentalTime
    fun poll(delay: Duration): Flow<T?>
}

/**
 * Thanks to article [Polling with Kotlin Channels & Flows by Mohit Sarveiya](https://proandroiddev.com/polling-with-kotlin-channels-flows-1a69e94fdfe9)
 * Simplified & modified for the goal of the Guild Night
 */
@ExperimentalTime
class CoroutinePoller<T>(
    private val repository: DataRepository<T>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : Poller<T> {

    val logger = logger()

    override fun poll(delay: Duration): Flow<T?> {
        var lastCall: LocalDateTime
        return channelFlow {
            while (!isClosedForSend) {
                lastCall = LocalDateTime.now()
                send(repository.getData())
                val remainingDelay = delay - (LocalDateTime.now() - lastCall)
                if (remainingDelay.isPositive()) {
                    delay(remainingDelay)
                }
            }
        }.flowOn(dispatcher)
    }

}

private const val iterationCount = 100

/**
 * NOTE: This `main` method will perform [iterationCount] iterations; may take quite some time!
 *
 * Runs the [CoroutinePoller] with a [TwoDimensionalRepository] to periodically generate
 * various [TwoDimensional]s, with a random delay.
 * If the generation of the [TwoDimensional] times out, `null` is returned.
 *
 * The [TwoDimensionalRepository.getData] method shows (among others) that coroutines can be
 * effectively cancelled by a timeout (or by another event): If a timeout occurs (seen as a `WARN` message
 * in the logging, no [TwoDimensional] is generated or collected anymore.
 * And on the other hand, if [TwoDimensionalRepository.getData] does NOT time out, the [TwoDimensional] is generated
 * and collected.
 *
 * This is due to the fact that the coroutine runs in the same context as the generation of the [TwoDimensional].
 * Query Google on "Structured concurrency" to find out more!
 */
@ExperimentalTime
fun main() {

    val logger = logger(CoroutinePoller::class.java)
    val pollInterval = 1.toDuration(DurationUnit.SECONDS)

    val expectedDemoDuration = pollInterval * iterationCount
    if (expectedDemoDuration > 10.toDuration(DurationUnit.SECONDS)) {
        logger.warn { "This demo will produce $iterationCount geometries with intervals of $pollInterval" }
        println()
        println("Expected duration: $expectedDemoDuration")
        println("Press enter to start!")
        with(Scanner(System.`in`)) {
            this.nextLine()
            this.reset()
        }
    }

    val repoTimeOut = 300.toDuration(DurationUnit.MILLISECONDS)
    // generation delay a bit longer than repo timeout, so will sometimes succeed, sometimes timeout
    val generationMaxDelay = 500.toDuration(DurationUnit.MILLISECONDS)

    val repo = TwoDimensionalRepository(timeOut = repoTimeOut, generationMaxDelay = generationMaxDelay)

    val poller = CoroutinePoller(repo, Dispatchers.Default)
    val flow: Flow<TwoDimensional?> = poller.poll(pollInterval)

    var count = 0
    runBlocking {
        launch {
            flow.collect {
                logger.info { "# ${++count} - Collected: $it" }
                if (count >= iterationCount) {
                    // Note that it stops all concurrent coroutines when cancelled!
                    this.coroutineContext.cancel()
                    logger.info { "\nFinished ($count iterations completed)" }
                }
            }
        }
    }
}