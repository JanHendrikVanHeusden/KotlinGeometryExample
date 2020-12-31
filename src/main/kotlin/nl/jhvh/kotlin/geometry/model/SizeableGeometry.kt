package nl.jhvh.kotlin.geometry.model

interface SizeableGeometry<T: Geometry> {

    /** @return [T] A new instance with the original lengths of the sides multiplied by the [factor] */
    operator fun times(factor: Double): T

    /** @return [T] A new instance with the original lengths of the sides divided by the [divisor] */
    operator fun div(divisor: Double): T
}
