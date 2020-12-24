/**
 * This is for DEMO only to show difference between Java and Kotlin (using extension methods in Kotlin).
 * For any real use, make sure to use the stuff from the `javax.measure` package instead !!
 */

package nl.jhvh.kotlin.geometry

const val rectangleDegrees: Double = 90.0

const val radiansToDegreesFactor: Double = 180 / Math.PI
const val degreesToRadiansFactor: Double = Math.PI / 180

fun Double.radiansToDegrees() = this * radiansToDegreesFactor
fun Double.degreesToRadians() = this * degreesToRadiansFactor
