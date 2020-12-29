package nl.jhvh.kotlin.geometry.model.twodimensional

import nl.jhvh.kotlin.geometry.model.GeometryType
import nl.jhvh.kotlin.geometry.model.GeometryType.SQUARE

data class Square(val side: Double) : TwoDimensional by Rectangle(side, side) {
    override val geometryType: GeometryType = SQUARE
}
