package nl.jhvh.kotlin.geometry.model.twodimensional

import nl.jhvh.kotlin.conversion.m2ToSquareFeet
import nl.jhvh.kotlin.conversion.meterToFeet
import nl.jhvh.kotlin.geometry.model.Dimensional
import nl.jhvh.kotlin.geometry.model.Dimensional.twoDimensional
import nl.jhvh.kotlin.geometry.model.GeometryType
import nl.jhvh.kotlin.geometry.model.GeometryType.PARALLELOGRAM
import nl.jhvh.kotlin.geometry.util.degreesToRadiansFactor
import nl.jhvh.kotlin.geometry.util.radiansToDegrees
import nl.jhvh.kotlin.util.logger
import kotlin.math.atan
import kotlin.math.sin

private const val minAngleDegrees = 0.0
private const val maxAngleDegrees = 90.0

data class Parallelogram constructor(val s1: Double, val s2: Double, val angleDegrees: Double) : TwoDimensional {

    init {
        validateInput()
    }

    override val dimensional: Dimensional = twoDimensional

    override val geometryType: GeometryType = PARALLELOGRAM

    val angleRadians: Double = angleDegrees * degreesToRadiansFactor
    val length: Double = s1

    // pretend that it's a heavy initialization, so lazy
    // NB: default lazy() initialization mode = LazyThreadSafetyMode.SYNCHRONIZED.
    val width: Double by lazy {
        logger().debug { "Lazy initialization of ${this.javaClass.simpleName}.width" }
        s2 * sin(angleRadians)
    }

    override val circumference: Double = (s1 + s2) * 2

    // lazy because it uses width (which is also lazy)
    override val area: Double by lazy {
        logger().debug { "Lazy initialization of ${this.javaClass.simpleName}.area" }
        s1 * width
    }

    private fun validateInput() {
        require(s1 >= 0.0 && s2 >= 0.0) { "Lengths of both sides must be positive, but sides s1 , s2 are $s1 , $s2" }
        require(angleDegrees in minAngleDegrees..maxAngleDegrees) { "The angle of a parallelogram must be in range $minAngleDegrees and $maxAngleDegrees, but is $angleDegrees" }
    }

    override fun compareTo(other: TwoDimensional): Int = if (this == other || this.area == other.area) 0 else if (this.area > other.area) 1 else 0
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
