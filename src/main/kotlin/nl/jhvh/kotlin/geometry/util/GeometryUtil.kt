/**
 * This is for DEMO only to show difference between Java and Kotlin (using extension methods in Kotlin).
 * For any real use, make sure to use the stuff from the `javax.measure` package instead !!
 */

package nl.jhvh.kotlin.geometry.util

import nl.jhvh.kotlin.geometry.model.twodimensional.Parallelogram
import nl.jhvh.kotlin.geometry.model.twodimensional.Rectangle
import nl.jhvh.kotlin.geometry.model.twodimensional.Square
import nl.jhvh.kotlin.geometry.model.twodimensional.plus

const val rectangleDegrees: Double = 90.0

const val radiansToDegreesFactor: Double = 180 / Math.PI
const val degreesToRadiansFactor: Double = Math.PI / 180

fun Double.radiansToDegrees() = this * radiansToDegreesFactor
fun Double.degreesToRadians() = this * degreesToRadiansFactor

fun main() {
    println(listOf(1, 2) + listOf(3, 4))
    println(listOf(1, 2) + 7)
    println(Parallelogram(1.0, 2.0, 80.0) * 3.0)
    println(Parallelogram(1.0, 7.0, 65.0) * Pair(1.2, 5.0))

    val small = Parallelogram(1.0, 4.0, 80.0)
    val large = Parallelogram(7.0, 8.0, 80.0)
    println("""area of 'small' = ${small.area}; area of 'large' = ${large.area}.
        |Is 'small' bigger than 'large'? -> ${small > large}"""
        .trimMargin())

    println("""The sum of the areas should be 2.0.
        |Let's check: outcome = ${Parallelogram(1.0, 1.0, 90.0) + Square(1.0) }"""
        .trimMargin()
    )

    with (Parallelogram(1.0, 4.0/kotlin.math.sqrt(2.0), 45.0)) {
        println("""This one: $this has an area of about 2: area = ${this.area}.
            |If I subtract a Rectangle with area 1, the diff should be about 1: ${this - Rectangle(1.0, 1.0)}"""
            .trimMargin()
        )
    }
}
