package nl.jhvh.kotlin.geometry.twodimensional

import nl.jhvh.java.geometry.twodimensional.Parallelogram
import nl.jhvh.kotlin.conversion.m2ToSquareFeet
import nl.jhvh.kotlin.conversion.meterToFeet
import nl.jhvh.kotlin.geometry.degreesToRadiansFactor
import nl.jhvh.kotlin.geometry.radiansToDegrees
import nl.jhvh.kotlin.util.logger
import kotlin.math.atan
import kotlin.math.sin

data class Parallelogram constructor(val s1: Double, val s2: Double, val angleDegrees: Double) : TwoDimensional {

    val angleRadians: Double = angleDegrees * degreesToRadiansFactor
    val length: Double = s1
    val width: Double by lazy {
        logger().debug { "Lazy initialization of ${this.javaClass.simpleName}.width" }
        s2 * sin(angleRadians)
    }

    override val circumference: Double = (s1 + s2) * 2

    // lazy because it uses width (which is also lazy)
    override val area: Double by lazy { s1 * width }

}

fun main() {
    // Let's define a parallelogram with s2 = 5, and where the tangent of the angle is 4/3
    // This results in a width of 4
    val angle = atan(4.0 / 3.0).radiansToDegrees()
    val parallelogram = Parallelogram(10.0, 5.0, angle)

    println("The $parallelogram has length ${parallelogram.length} and width ${parallelogram.width}")
    println("It's area is ${parallelogram.area} m2 (${parallelogram.area.m2ToSquareFeet()} square feet)")
    println("It's circumference is ${parallelogram.circumference} m (${parallelogram.circumference.meterToFeet()} feet)")
}
