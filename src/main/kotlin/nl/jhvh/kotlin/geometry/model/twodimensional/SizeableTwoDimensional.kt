package nl.jhvh.kotlin.geometry.model.twodimensional

import nl.jhvh.kotlin.geometry.model.SizeableGeometry

interface SizeableTwoDimensional<T: TwoDimensional>: TwoDimensional, SizeableGeometry<T> {

    /** @return [T] A new instance with the original lengths of the sides multiplied by [Pair.first] and [Pair.second], respectively */
    operator fun times(factors: Pair<Double, Double>): T

    /** @return [T] A new instance with the original lengths of the sides divided by [Pair.first] and [Pair.second], respectively */
    operator fun div(divisors: Pair<Double, Double>): T
}
