package nl.jhvh.kotlin.geometry.model.twodimensional

data class Square(val side: Double) : TwoDimensional by Rectangle(side, side)
