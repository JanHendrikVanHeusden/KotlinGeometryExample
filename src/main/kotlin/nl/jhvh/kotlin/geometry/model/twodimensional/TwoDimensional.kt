package nl.jhvh.kotlin.geometry.model.twodimensional

import nl.jhvh.kotlin.geometry.model.Geometry

interface TwoDimensional: Geometry, Comparable<TwoDimensional> {

    /** Circumference of the [TwoDimensional] geometry in meters */
    val circumference: Double

    /** Area of a [TwoDimensional] geometry in square meters */
    val area: Double

    /** @return [Double] The sum of the [TwoDimensional.area]s */
    operator fun <T: TwoDimensional, S: TwoDimensional>T.plus(other: S): Double

    /** @return [Double] The subtraction of the [TwoDimensional.area]s */
    operator fun <T: TwoDimensional, S: TwoDimensional>T.minus(other: S): Double

}

operator fun <T: TwoDimensional, S: TwoDimensional> T.plus(other: S): Double = this.plus(other)
operator fun <T: TwoDimensional, S: TwoDimensional> T.minus(other: S): Double = this.minus(other)
