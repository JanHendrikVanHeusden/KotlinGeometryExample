package nl.jhvh.kotlin.geometry.twodimensional

import nl.jhvh.kotlin.geometry.rectangleDegrees

data class Rectangle(val length: Double, val width: Double): TwoDimensional by Parallelogram(length, width, rectangleDegrees)

fun main() {
    val rectangle = Rectangle(10.0, 20.0)
    println("rectangle.length:        ${rectangle.length}")
    println("rectangle.width:         ${rectangle.width}")
    println("rectangle.circumference: ${rectangle.circumference}")
    println("rectangle.area:          ${rectangle.area}")
    println()
    println("Again - rectangle.width:         ${rectangle.width}")
    println()

    val otherRectangle = Rectangle(10.0, 20.0)
    val areEqual = rectangle == otherRectangle
    println("Are the rectangles $rectangle and $otherRectangle equal? $areEqual")
}
