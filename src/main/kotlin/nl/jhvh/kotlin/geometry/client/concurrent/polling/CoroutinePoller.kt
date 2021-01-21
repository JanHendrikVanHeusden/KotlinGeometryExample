package nl.jhvh.kotlin.geometry.client.concurrent.polling

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import nl.jhvh.kotlin.geometry.model.twodimensional.TwoDimensional
import nl.jhvh.kotlin.util.logger
import nl.jhvh.kotlin.util.minus
import java.time.LocalDateTime
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

interface Poller<T> {
    @ExperimentalTime
    fun poll(delay: Duration): Flow<T?>
    fun close()
}

/**
 * Thanks to article [Polling with Kotlin Channels & Flows by Mohit Sarveiya](https://proandroiddev.com/polling-with-kotlin-channels-flows-1a69e94fdfe9)
 * Simplified & modified for the goal of the Guild Night
 */
@ExperimentalCoroutinesApi
@ExperimentalTime
class CoroutinePoller<T>(
    val repository: DataRepository<T>,
    val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : Poller<T> {

    private val logger = logger()

    override fun poll(delay: Duration): Flow<T?> {
        var lastCall: LocalDateTime = LocalDateTime.now()
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

    override fun close() {
        dispatcher.cancel()
    }

}

/**
 * NOTE: This `main` method will run infinitely until interrupted!
 *
 * Runs the [CoroutinePoller] with a [TwoDimensionalRepository] to periodically generate
 * various [TwoDimensional]s, with a random delay.
 * If the generation of the [TwoDimensional] times out, `null` is returned.
 *
 * The [TwoDimensionalRepository.getData] method shows (among others) that coroutines can be
 * effectively cancelled by a timeout (or by another event): If a timeout occurs (seen as a `WARN` message
 * in the logging, no [TwoDimensional] is generated or collected anymore;
 * and on the other hand, if [TwoDimensionalRepository.getData] does NOT time out, the [TwoDimensional] is generated
 * and collected.
 *
 * This is due to the fact that the coroutine runs in the same context as the generation of the [TwoDimensional].
 */
@ExperimentalCoroutinesApi
@ExperimentalTime
suspend fun main() {

    val logger = logger(CoroutinePoller::class.java)

    val repoTimeOut = 300.toDuration(DurationUnit.MILLISECONDS)
    // generation delay a bit longer than repo timeout, so will sometimes succeed, sometimes timeout
    val generationMaxDelay = 500.toDuration(DurationUnit.MILLISECONDS)

    val repo = TwoDimensionalRepository(timeOut = repoTimeOut, generationMaxDelay = generationMaxDelay)

    val poller = CoroutinePoller(repo, Dispatchers.Default)
    val pollInterval = 1.toDuration(DurationUnit.SECONDS)
    val flow: Flow<TwoDimensional?> = poller.poll(pollInterval)

    flow.collect { logger.info { "Collected: ${it.toString()}" } }

}