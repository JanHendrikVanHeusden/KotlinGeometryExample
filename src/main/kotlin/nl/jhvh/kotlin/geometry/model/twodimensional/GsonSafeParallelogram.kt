package nl.jhvh.kotlin.geometry.model.twodimensional

import nl.jhvh.kotlin.geometry.model.Dimensional
import nl.jhvh.kotlin.geometry.model.Dimensional.twoDimensional
import nl.jhvh.kotlin.geometry.model.GeometryType
import nl.jhvh.kotlin.geometry.model.GeometryType.PARALLELOGRAM
import nl.jhvh.kotlin.geometry.util.degreesToRadiansFactor
import kotlin.math.sin

/** Made Gson safe using getters (not only Gson, also for other deserializers) */
data class GsonSafeParallelogram constructor(val s1: Double, val s2: Double, val angleDegrees: Double) :
    TwoDimensional {

    init {
        validateInput()
    }

    val angleRadians: Double
        get() = angleDegrees * degreesToRadiansFactor

    val length: Double
        get() = s1

    val width: Double
        get() = s2 * sin(angleRadians)

    override val dimensional: Dimensional
        get() = twoDimensional

    override val geometryType: GeometryType
        get() = PARALLELOGRAM

    override val circumference: Double
        get() = (s1 + s2) * 2

    override val area
        get() = s1 * width

    private fun validateInput() {
        require(s1 >= 0.0 && s2 >= 0.0) { "Lengths of both sides must be positive, but sides s1 , s2 are $s1 , $s2" }
        require(angleDegrees in minAngleDegrees..maxAngleDegrees) { "The angle of a parallelogram must be in range $minAngleDegrees and $maxAngleDegrees, but is $angleDegrees" }
    }

    override fun compareTo(other: TwoDimensional): Int =
        if (this == other || this.area == other.area) 0 else if (this.area > other.area) 1 else 0
}
