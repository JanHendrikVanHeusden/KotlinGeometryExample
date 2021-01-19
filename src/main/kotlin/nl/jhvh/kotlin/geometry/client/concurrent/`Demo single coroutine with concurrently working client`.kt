package nl.jhvh.kotlin.geometry.client.concurrent

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.jhvh.kotlin.geometry.model.twodimensional.Parallelogram
import nl.jhvh.kotlin.geometry.service.ParallelogramSpecifierByExample
import nl.jhvh.kotlin.geometry.service.TwoDimensionalService
import nl.jhvh.kotlin.util.logger
import kotlin.time.ExperimentalTime

@ExperimentalTime
class `Single coroutine with concurrently working client`(val count: Int, maxDelayMillis: Long) {
    val minBound = Parallelogram(0.0, 0.0, 0.0)
    val maxBound = Parallelogram(100.0, 100.0, 9.0)
    val spec = ParallelogramSpecifierByExample(minBound, maxBound, maxDelayMillis)

    private val logger = logger()

    fun runBlocking() = runBlocking {
        var ready = false

        println("1. Before concurrent work launcher\n")
        // `launch` executes reactively, in coroutine scope
        // asynchronous, like execution of a Java Future, but in the same Thread as the caller
        launch {
            while (!ready) {
                delay(100)
                println("concurrent work in ${Thread.currentThread()} ...")
            }
            // Uncomment to show that the exception is propagated, while in the same Thread!
            // throw Exception("Ready !!")
        }
        println("2. After concurrent work launcher\n")

        val service = TwoDimensionalService()
        val flow = service.generateByExample(spec, count)
        println("3. Before flow collector\n")
        // Flow.collect runs reactively: retrieves the emitted values asynchronously, in coroutine scope
        // asynchronous, like execution of Java CompletableFuture.supplyAsync(), but in the same Thread as the caller
        var count = 0
        flow.collect { parallelogram ->
            logger.debug { "\nRetrieved #${++count} $parallelogram, Thread: ${Thread.currentThread().name}"
            }
        }
        ready = true

        println("\n4. After flow collector\n")
    }

}

@ExperimentalTime
fun main() {
    // Let's give the current Thread a distinctive name, so we can easily see that we are running in same Thread all the time
    with (Thread.currentThread()) {
        this.name = "#${this.id} - only lonely Thread"
        println("new Thread name = '${this.name}', id = ${this.id}, priority = ${this.priority} group = ${this.threadGroup.name}")
    }

    val maxDelayMillis: Long = 1000
    val count = 5
    println("\n\nGeneration of a single rectangle takes a random time between 0 and $maxDelayMillis ms")
    println("$count rectangles are generated\n")
    `Single coroutine with concurrently working client`(count, maxDelayMillis).runBlocking()
    println("Everything ready !!!")
}