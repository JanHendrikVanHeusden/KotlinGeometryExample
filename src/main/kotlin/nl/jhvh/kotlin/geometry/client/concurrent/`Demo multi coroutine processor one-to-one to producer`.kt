package nl.jhvh.kotlin.geometry.client.concurrent

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.jhvh.kotlin.geometry.model.twodimensional.Rectangle
import nl.jhvh.kotlin.geometry.model.twodimensional.TwoDimensional
import nl.jhvh.kotlin.geometry.service.RectangleSpecifierByExample
import nl.jhvh.kotlin.geometry.service.TwoDimensionalSpecifierByExample
import nl.jhvh.kotlin.util.logger
import kotlin.math.roundToInt
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
@ExperimentalCoroutinesApi
class `Massive concurrent multi-coroutine producer processor`() {

    private val logger = logger()

    fun <T : TwoDimensional> CoroutineScope.produceTwoDimensionals(spec: TwoDimensionalSpecifierByExample<T>): ReceiveChannel<T> =
        produce {
            send(spec.generateWithDelay()) // produce next
        }

    fun <T : TwoDimensional> CoroutineScope.launchProcessor(channel: ReceiveChannel<T>, result: MutableList<T>): Job = launch {
        for (twoDimensional in channel) {
            result.add(twoDimensional)
            logger.debug { "Processor received $twoDimensional #${result.size}" }
        }
    }

    fun <T : TwoDimensional> produceParallelograms(spec: TwoDimensionalSpecifierByExample<T>, toProduce: Int, result: MutableList<T>) =
        runBlocking {
            logger.debug { "About to start production of Parallelograms!" }
            repeat(toProduce) { i ->
                val receiveChannel = produceTwoDimensionals(spec)
                logger.debug { "Producer #${i + 1}" }
                launchProcessor(receiveChannel, result)
            }
        }

}

@ExperimentalTime
@ExperimentalCoroutinesApi
fun main() {
    // Let's give the current Thread a distinctive name, so we can easily see that we are running in same Thread all the time
    Thread.currentThread().name = "${Thread.currentThread().id} - Concurrency in single Thread"

    val count = 10000
    val maxDelayMillis = 1000L
    println("\n\nAbout to generate $count Rectangles, each one taking a random time between 0 and $maxDelayMillis ms\n\n")

    val minBound = Rectangle(0.0, 0.0)
    val maxBound = Rectangle(100.0, 100.0)
    val spec = RectangleSpecifierByExample(minBound, maxBound, maxDelayMillis)

    val list: MutableList<Rectangle> = mutableListOf()
    val (_, elapsed) = measureTimedValue {
        `Massive concurrent multi-coroutine producer processor`().produceParallelograms(spec, count, list)
    }

    println("\n\nReady! Generated $count Rectangles, each one taking a random time between 0 and $maxDelayMillis ms\n")
    println("Processed ${list.size} Rectangles in ${elapsed}; total Rectangle generation time was ${spec.duration()}\n")
    println("Time sharing factor by coroutines = ${((spec.duration() / elapsed).roundToInt())}")
}
