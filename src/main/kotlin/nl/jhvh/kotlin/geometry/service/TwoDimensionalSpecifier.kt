package nl.jhvh.kotlin.geometry.service

import kotlinx.coroutines.delay
import nl.jhvh.kotlin.geometry.model.twodimensional.Parallelogram
import nl.jhvh.kotlin.geometry.model.twodimensional.Rectangle
import nl.jhvh.kotlin.geometry.model.twodimensional.Square
import nl.jhvh.kotlin.geometry.model.twodimensional.TwoDimensional
import nl.jhvh.kotlin.util.doubleUntil
import nl.jhvh.kotlin.util.logger
import nl.jhvh.kotlin.util.randomInRange
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
interface TwoDimensionalSpecifier<T : TwoDimensional> {
    fun predicate(toTest: T): Boolean
    suspend fun generateWithDelay(): T
    fun generateToSpec(): T
    val maxDelayMillis: Long
    fun duration(): Duration
}

@ExperimentalTime
sealed class TwoDimensionalSpecifierByExample<T : TwoDimensional>(
    open val example1: T,
    open val example2: T,
    final override val maxDelayMillis: Long = 0L
) : TwoDimensionalSpecifier<T> {

    @Volatile
    private var duration: Duration = Duration.ZERO
    final override fun duration() = duration

    final override suspend fun generateWithDelay(): T =  run {
        val (result: T, elapsed: Duration) = measureTimedValue {
            if (maxDelayMillis > 0L) {
                delay(Random.nextLong(0L, maxDelayMillis))
            }
            generateToSpec()
        }
        duration = duration.plus(elapsed)
        result
    }

}

@ExperimentalTime
class ParallelogramSpecifierByExample(
    override val example1: Parallelogram,
    override val example2: Parallelogram,
    maxDelayMillis: Long = 0L
) : TwoDimensionalSpecifierByExample<Parallelogram>(example1, example2, maxDelayMillis) {

    constructor(example: Parallelogram) : this(example, example)

    // `doubleUntil` is a home grown method, defined the same way as the built-in Int.until method!
    // We can neither use the .. operator (does not include the start value), nor Double.until, the Kotlin folks did not implement that (yet)
    private val s1Range = min(example1.s1, example2.s1) doubleUntil max(example1.s1, example2.s1)
    private val s2Range: ClosedRange<Double> = min(example1.s2, example2.s2) doubleUntil max(example1.s2, example2.s2)
    private val angleDegreesRange =
        min(example1.angleDegrees, example2.angleDegrees) doubleUntil max(example1.angleDegrees, example2.angleDegrees)

    override fun predicate(toTest: Parallelogram) =
        toTest.s1 in s1Range && toTest.s2 in s2Range && toTest.angleDegrees in angleDegreesRange

    override fun generateToSpec() =
        let { Parallelogram(s1Range.randomInRange(), s2Range.randomInRange(), angleDegreesRange.randomInRange()) }
            .also { logger().debug { "\nGenerating $it" } }

}

@ExperimentalTime
class RectangleSpecifierByExample(
    override val example1: Rectangle,
    override val example2: Rectangle,
    maxDelayMillis: Long = 0L
) : TwoDimensionalSpecifierByExample<Rectangle>(example1, example2, maxDelayMillis,) {

    constructor(example: Rectangle) : this(example, example)

    // `doubleUntil` is a home grown method, defined the same way as the built-in Int.until method!
    // We can neither use the .. operator (does not include the start value), nor Double.until, the Kotlin folks did not implement that (yet)
    private val lengthRange: ClosedRange<Double> =
        min(example1.length, example2.length) doubleUntil max(example1.length, example2.length)
    private val widthRange: ClosedRange<Double> =
        min(example1.width, example2.width) doubleUntil max(example1.width, example2.width)

    override fun predicate(toTest: Rectangle) = toTest.length in lengthRange && toTest.width in widthRange

    override fun generateToSpec() =
        let {
            Rectangle(lengthRange.randomInRange(), widthRange.randomInRange())
        }
            .also { logger().debug { "\nGenerating $it" } }
}

@ExperimentalTime
class SquareSpecifierByExample(
    override val example1: Square,
    override val example2: Square,
    maxDelayMillis: Long = 0L
) : TwoDimensionalSpecifierByExample<Square>(example1, example2, maxDelayMillis,) {

    constructor(example: Square) : this(example, example)

    private val sideRange = min(example1.side, example2.side) doubleUntil max(example1.side, example2.side)

    override fun predicate(toTest: Square) = toTest.side in sideRange

    override fun generateToSpec() =
        let { Square(sideRange.randomInRange()) }
            .also { logger().debug { "\nGenerating $it" } }
}
