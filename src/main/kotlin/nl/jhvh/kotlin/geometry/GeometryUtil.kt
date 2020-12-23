package nl.jhvh.kotlin.geometry

const val rectangleDegrees: Double = 90.0

const val radiansToDegreesFactor: Double = 180 / Math.PI
const val degreesToRadiansFactor: Double = Math.PI / 180

fun Double.radiansToDegrees() = this * radiansToDegreesFactor
fun Double.degreesToRadians() = this * degreesToRadiansFactor
