package nl.jhvh.kotlin.basics

import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

val longList = mutableListOf<Int>()

@ExperimentalTime
fun main() {
    longList.clear()

    repeat(100_000) { longList.add(it) }
    println("Unfiltered size: ${longList.size}")

    // Unlike to Java Streams, this is performed eagerly !!
    var eagerlyProcessed: List<String>
    eagerlyProcessed = longList
        .filter { it % 10 == 0 }
        .map { it.toString() }
        .filter(isNumericString)
    println("eager: ${eagerlyProcessed.size}")

    // asSequence() lets it process lazily, like Java Streams
    var lazyProcessed: List<String>
    lazyProcessed = longList
        .asSequence()
        .filter { it % 10 == 0 }
        .map { it.toString() }
        .filter(isNumericString)
        .toList()
    println("lazy: ${lazyProcessed.size}")

    // Now warmed up both, let's measure the processing time!
    val (_, eagerDuration) = measureTimedValue {
        eagerlyProcessed = longList
            .filter { it % 10 == 0 }
            .map { it.toString() }
            .filter(isNumericString)
    }
    println("eager duration: $eagerDuration")

    val (_, lazyDuration) = measureTimedValue {
        lazyProcessed = longList
            .asSequence()
            .filter { it % 10 == 0 }
            .map { it.toString() }
            .filter(isNumericString)
            .toList()
    }
    println("lazy duration:  $lazyDuration")
}
