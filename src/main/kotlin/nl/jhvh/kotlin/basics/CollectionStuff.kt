package nl.jhvh.kotlin.basics

import kotlin.random.Random
import kotlin.time.ExperimentalTime

fun drop3(list: List<String>) = list.drop(3)

val isNumericString = { str: String -> str.matches(Regex("""-?\d+(\.\d+)?(([Ee])-?\d+)?""")) }
fun numericStrings(list: List<String>) = list.filter { isNumericString(it) }

val myList = listOf("1234", "5678", "test", Random.nextDouble().toString(), (-1.23e-50).toString(), "check1")
val myLongList = mutableListOf<Int>()

@ExperimentalTime
fun main() {
    println(drop3(myList))
    println(numericStrings(myList))

    repeat(100_000) { myLongList.add(it) }
    println("Unfiltered size: ${myLongList.size}")

    val filtered = myLongList
        .filter { it % 10 == 0 }
        .map { it.toString() }
        .filter(isNumericString)
    println("Filtered size:    ${filtered.size}")
}
