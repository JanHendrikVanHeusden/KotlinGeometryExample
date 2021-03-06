package nl.jhvh.kotlin.geometry.model.twodimensional

import nl.jhvh.kotlin.geometry.model.GeometryType
import nl.jhvh.kotlin.geometry.model.GeometryType.RECTANGLE
import nl.jhvh.kotlin.geometry.util.rectangleDegrees
import nl.jhvh.java.geometry.model.twodimensional.Rectangle as JavaRectangle

data class Rectangle(val length: Double, val width: Double) : TwoDimensional by Parallelogram(length, width, rectangleDegrees) {
    override val geometryType: GeometryType = RECTANGLE
}

fun main() {
    val rectangle = Rectangle(10.0, 20.0)
    println("rectangle.length:        ${rectangle.length}")
    println("rectangle.width:         ${rectangle.width}")
    println("rectangle.circumference: ${rectangle.circumference}")
    println("rectangle.area:          ${rectangle.area}")
    println()
    println("Again - rectangle.width:         ${rectangle.width}")
    println()

    val otherRectangle = Rectangle(length = 10.0, width = 20.0)
    val areEqual = rectangle == otherRectangle
    println("Are the rectangles $rectangle and $otherRectangle equal? $areEqual")

    // using import alias: import nl.jhvh.java.geometry.twodimensional.Rectangle as JavaRectangle
    val javaRectangle = JavaRectangle(10.0, 20.0)
    // Import aliases are nice if you have to use, say, joda LocalDateTime and java LocalDateTime in the same class
    // (or even the old fashioned java.util.Date and java.sql.Date)

    println()
    println()
    var areKotlinAndJavaRectangleEqual = rectangle.equals(javaRectangle)
    println("Are the rectangles $rectangle (Kotlin) and $javaRectangle (Java) equal? $areKotlinAndJavaRectangleEqual")

    areKotlinAndJavaRectangleEqual = javaRectangle.equals(rectangle)
    println("Are the rectangles $javaRectangle (Java) and $rectangle (Kotlin) equal? $areKotlinAndJavaRectangleEqual")

    println()
    println()
    val smallParallelogram = Parallelogram(1.0, 1.0, 35.0)
    val largerSquare = Square(16.8)
    val isBigger = largerSquare > smallParallelogram
    println(("'$largerSquare' is larger than '$smallParallelogram'? -> $isBigger"))
}
