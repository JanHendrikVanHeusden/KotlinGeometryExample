/**
 * This is for DEMO only to show difference between Java and Kotlin (using extension methods in Kotlin).
 * For any real use, make sure to use the stuff from the `javax.measure` package instead !!
 */

package nl.jhvh.kotlin.conversion

const val meterToFeetFactor: Double = 3.2808399
const val m2ToSquareFeetFactor: Double = meterToFeetFactor * meterToFeetFactor // 10.7639104

const val footToInchFactor: Int = 12
const val squareFootToSquareInchFactor: Int = footToInchFactor * footToInchFactor

// Extension functions
fun Double.meterToFeet(): Double = this * meterToFeetFactor
fun Double.m2ToSquareFeet(): Double = this * m2ToSquareFeetFactor

fun Double.meterToInch(): Double = this.meterToFeet() * footToInchFactor
fun Double.m2ToSquareInch(): Double = this.m2ToSquareFeet() * squareFootToSquareInchFactor

fun Double.feetToMeter(): Double = this / meterToFeetFactor
fun Double.squareFeetToM2(): Double = this / m2ToSquareFeetFactor

fun Double.inchToMeter(): Double = this.feetToMeter() / footToInchFactor
fun Double.squareInchToM2(): Double = this.squareFeetToM2() / squareFootToSquareInchFactor
