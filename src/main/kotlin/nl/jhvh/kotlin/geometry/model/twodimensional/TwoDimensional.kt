package nl.jhvh.kotlin.geometry.model.twodimensional

import nl.jhvh.kotlin.geometry.model.Dimensional
import nl.jhvh.kotlin.geometry.model.Geometry

interface TwoDimensional: Geometry {

    /** Circumference of the [TwoDimensional] geometry in meters */
    val circumference: Double

    /** Area of a [TwoDimensional] geometry in square meters */
    val area: Double

}
