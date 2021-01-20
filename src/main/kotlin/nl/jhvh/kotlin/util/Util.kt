package nl.jhvh.kotlin.util

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

// Can not use the `..` operator (does not include lower bound; so 1.0..7.0 does not include 1.0).
// Neither can we use `until` (like in `0 until 5`): the Kotlin folks did not implement that for Float or Double
// So instead we define a home grown `until` (following the pattern of the Int.until method)
//
/**
 * Mimics the [Int.until], [Long.until] etc. method for [Double]s.
 * > Take care of -0.0 and 0.0, because of [Double] semantics this expression is true: -0.0 != 0.0
 * > So -0.0 in 0.0 [doubleUntil] 2.0 will return `false`, but 0.0 in 0.0 [doubleUntil] 2.0 will return `true` !!
 * @return a [ClosedRange] where the [ClosedRange.contains] returns `true`
 *         when and only when [ClosedRange.start] <= this <= [ClosedRange.endInclusive]
 *
 * So both start and end of range are inclusive!
 */
// DELIBERATELY NOT USING THE NAME "until", THAT WOULD BREAK AS SOON AS KOTLIN WOULD INTRODUCE A NATIVE Double.until
infix fun Double.doubleUntil(to: Double): ClosedRange<Double> {
    val thisDouble = this
    return object : ClosedRange<Double> {
        override val endInclusive = to
        override val start = thisDouble
    }
}

fun ClosedRange<Double>.randomInRange(random: Random? = null): Double {
    check(!this.isEmpty()) { "Can't generate a random Double, $this is empty!" }
    val randomToUse = random ?: Random
    return if (this.start == this.endInclusive) this.start else randomToUse.nextDouble(start, endInclusive)
}

@ExperimentalTime
infix operator fun LocalDateTime.minus(other: LocalDateTime): Duration {
    return try {
        val duration = other.until(this, ChronoUnit.NANOS)
            .toDuration(DurationUnit.NANOSECONDS)
        duration
    } catch (ae: ArithmeticException) {
        // long overflow when diff is more than about 292 years,
        // switch to milliseconds, allows for 292000 years; hopefully that's enough!
        other.until(this, ChronoUnit.MILLIS)
            .toDuration(DurationUnit.MILLISECONDS)
    }
}
