package nl.jhvh.kotlin.geometry.twodimensional

data class Square(val side: Double): TwoDimensional by Rectangle(side, side)
