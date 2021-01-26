package nl.jhvh.kotlin.basics

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random
import nl.jhvh.kotlin.util.logger

// () -> Int
val randomIntSupplier = { Random.nextInt() }
val millisIntSupplier = { System.currentTimeMillis().toInt() }

// () -> String
val doubleLetterSupplier =
    { with(Random.nextInt(33, 127).toChar().toString()) { this + this } }
val dateTimeStringSupplier =
    { LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString().replace('T', ' ') }

// (String) -> String
val inverter = { inputString: String -> StringBuffer(inputString).reverse().toString() }
val doubler = { inputString: String -> inputString + inputString }

// (Int, Int) -> Int
val multiplier = { a: Int, b: Int -> a * b }
val subtractor = { a: Int, b: Int -> a - b }

val addFourNumbers = { a: Int, b: Int, c: Int, d: Int -> a + b + c + d }

class LambdasKotlin {
    private val logger = logger()

    fun getNumber(numberSupplier: () -> Int) = numberSupplier()
    fun getString(stringSupplier: () -> String) = stringSupplier()

    fun handleString(inputString: String, stringHandler: (String) -> String): String {
        return stringHandler(inputString)
            .also { logger.info { "$inputString -> $it" } }
    }

    fun calculate(int1: Int, int2: Int, calculator: (Int, Int) -> Int): Int {
        return calculator(int1, int2)
            .also { logger.info { "$int1 $int2 -> $it" } }
    }
}

fun main() {
    val lambdas = LambdasKotlin()

    println("Random: ${lambdas.getNumber(randomIntSupplier)}")
    println("Millis: ${lambdas.getNumber(millisIntSupplier)}")

    println()
    println("2 letters: ${lambdas.getString(doubleLetterSupplier)}")
    println("date+time: ${lambdas.getString(dateTimeStringSupplier)}")

    println()
    println("doubled:  ${lambdas.handleString("hoi", doubler)}")
    println("inverted: ${lambdas.handleString("hallo", inverter)}")

    println()
    println("multiplied: ${lambdas.calculate(7, 12, multiplier)}")
    println("subtracted: ${lambdas.calculate(69, 14, subtractor)}")

    println("4 numbers added: ${addFourNumbers(2, 4, 6, 8)}")
}