package nl.jhvh.kotlin.conversion

const val meterToFeetFactor: Double = 3.2808399
const val m2ToSquareFeetFactor: Double = meterToFeetFactor * meterToFeetFactor // 10.7639104
const val m3ToCubicFeetFactor: Double = meterToFeetFactor * meterToFeetFactor * meterToFeetFactor // 35.3146667

const val footToInchFactor: Int = 12
const val squareFootToSquareInchFactor: Int = footToInchFactor * footToInchFactor
const val cubicFootToCubicInchFactor: Int = footToInchFactor * footToInchFactor * footToInchFactor

// Extension functions
fun Double.meterToFeet(): Double = this * meterToFeetFactor
fun Double.m2ToSquareFeet(): Double = this * m2ToSquareFeetFactor
fun Double.m3ToCubicFeet(): Double = this * m3ToCubicFeetFactor

fun Double.meterToInch(): Double = this.meterToFeet() * footToInchFactor
fun Double.m2ToSquareInch(): Double = this.m2ToSquareFeet() * squareFootToSquareInchFactor
fun Double.m3ToCubicInch(): Double = this.m3ToCubicFeet() * cubicFootToCubicInchFactor

fun Double.feetToMeter(): Double = this / meterToFeetFactor
fun Double.squareFeetToM2(): Double = this / m2ToSquareFeetFactor
fun Double.cubicFeetToM3(): Double = this / m3ToCubicFeetFactor

fun Double.inchToMeter(): Double = this.feetToMeter() * footToInchFactor
fun Double.squareInchToM2(): Double = this.squareFeetToM2() * squareFootToSquareInchFactor
fun Double.cubicInchToM3(): Double = this.cubicFeetToM3() * cubicFootToCubicInchFactor
