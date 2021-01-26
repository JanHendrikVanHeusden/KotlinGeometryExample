package nl.jhvh.kotlin.basics

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random
import nl.jhvh.kotlin.util.logger

// () -> Int
val randomIntSupplier = { Random.nextInt() }
val millisSupplier = { System.currentTimeMillis().toInt() }

// () -> String
val doubleLetterSupplier = { with(Random.nextInt(33, 127).toChar().toString()) { this + this } }
val dateTimeStringSupplier = { LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString().replace('T', ' ')}

// (String) -> String
val inverter = { inputString: String -> StringBuffer(inputString).reverse().toString() }
val doubler = { inputString: String -> inputString + inputString }

// (Int, Int) -> Int
val multiplier = { a: Int, b: Int -> a * b }
val subtractor = { a: Int, b: Int -> a - b }

class Lambdas {
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
    val lambdas = Lambdas()

    println("Random: ${lambdas.getNumber(randomIntSupplier)}")
    println("Millis: ${lambdas.getNumber(millisSupplier)}")

    println()
    println("2 letters: ${lambdas.getString(doubleLetterSupplier)}")
    println("date+time: ${lambdas.getString(dateTimeStringSupplier)}")

    println()
    println("doubled:  ${lambdas.handleString("hoi", doubler)}")
    println("inverted: ${lambdas.handleString("hallo", inverter)}")

    println()
    println("multiplied: ${lambdas.calculate(7, 12, multiplier)}")
    println("subtracted: ${lambdas.calculate(69, 14, subtractor)}")
}