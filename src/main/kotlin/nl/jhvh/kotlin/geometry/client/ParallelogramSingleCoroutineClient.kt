package nl.jhvh.kotlin.geometry.client

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.jhvh.kotlin.geometry.model.twodimensional.Parallelogram
import nl.jhvh.kotlin.geometry.service.ParallelogramSpecifierByExample
import nl.jhvh.kotlin.geometry.service.TwoDimensionalService
import nl.jhvh.kotlin.util.logger

class ParallelogramSingleCoroutineClient(val count: Int, val maxDelayMillis: Long) {
    val minBound = Parallelogram(0.0, 0.0, 0.0)
    val maxBound = Parallelogram(100.0, 100.0, 9.0)
    val spec = ParallelogramSpecifierByExample(minBound, maxBound, maxDelayMillis = this.maxDelayMillis)

    fun runBlocking() = runBlocking {

        var ready = false
        println("1. Before print launcher\n")

        // launch runs reactively, in coroutine scope
        // asynchronous, like execution of a Java Future, but in the same Thread as the caller
        launch {
            while (!ready) {
                delay(100)
                println("running concurrently from ${Thread.currentThread()} ...")
            }
            // Uncomment to show that the exception is propagated, while in the same Thread!
            // throw Exception("Ready !!")
        }

        println("2. After print launcher\n")

        val service = TwoDimensionalService()
        val flow = service.generateByExample(spec, count)

        println("3. Before flow collector\n")

        // Flow.collect runs reactively: retrieves the emitted values asynchronously, in coroutine scope
        // asynchronous, like execution of Java CompletableFuture.supplyAsync(), but in the same Thread as the caller
        var count = 0
        flow.collect { parallelogram ->
            logger(ParallelogramSingleCoroutineClient::class.java).debug { "\nRetrieved #${++count} $parallelogram, Thread: ${Thread.currentThread().name}" }
        }
        ready = true

        println("\n4. After flow collector\n")
    }

}

fun main() {
    // Let's give the current Thread a distinctive name, so we can easily see that we are running in same Thread all the time
    Thread.currentThread().name = "${Thread.currentThread().id} - Concurrency in single Thread"

    with (Thread.currentThread()) {
        println("new Thread name = '${this.name}', id = ${this.id}, priority = ${this.priority} group = ${this.threadGroup.name}")
    }
    println()

    ParallelogramSingleCoroutineClient(count = 10, maxDelayMillis = 1000).runBlocking()
    println("Everything ready !!!")
}