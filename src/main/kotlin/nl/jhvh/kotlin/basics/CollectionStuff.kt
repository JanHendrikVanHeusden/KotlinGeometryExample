package nl.jhvh.kotlin.basics

import kotlin.random.Random

val isNumericString = { str: String -> str.matches(Regex("""-?\d+(\.\d+)?(([Ee])-?\d+)?""")) }
fun numericStrings(list: List<String>) = list.filter { isNumericString(it) }

val myList = listOf("1234", "5678", "test", Random.nextDouble().toString(), (-1.23e-50).toString(), "check1")
val myLongList =  (1..100_000).toList()

fun main() {

    println(myList.drop(3))
    println(numericStrings(myList))
    println()

    println("myLongList size:                   ${myLongList.size}")
    val filtered = myLongList
        .filter { it % 10 == 0 }
        .map { it.toString() }
        .filter(isNumericString)
    println("Filtered by multiples of 10; size:  ${filtered.size}")
    println()

    val mappedByLastNumber: Map<Int, List<Int>> = myLongList
            .takeWhile { it <= 500 }.dropLast(300)            // keep first 200 only
            .filter { it % 6 == 0 }                              // only when divisible by 6
            .groupBy { it.toString().last().toString().toInt() } // group by last digit
            .toSortedMap()                                       // sorted by keys

    println("Numbers 1..200, filtered on divisible by 6, then mapped by last digit:")
    println(mappedByLastNumber)
}
