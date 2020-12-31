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

sealed class TwoDimensionalSpecifierByExample<T : TwoDimensional>(open val example1: T, open val example2: T) :
    TwoDimensionalSpecifier<T> {
    val random = Random(System.currentTimeMillis())
}

class ParallelogramSpecifierByExample(
    override val example1: Parallelogram,
    override val example2: Parallelogram,
    override val maxDelayMillis: Long = 0L
) : TwoDimensionalSpecifierByExample<Parallelogram>(example1, example2) {

    constructor(example: Parallelogram) : this(example, example)

    // `doubleUntil` is a home grown method, defined the same way as the built-in Int.until method!
    // We can neither use the .. operator (does not include the start value), nor Double.until, the Kotlin folks did not implement that (yet)
    val s1Range = min(example1.s1, example2.s1) doubleUntil max(example1.s1, example2.s1)
    val s2Range: ClosedRange<Double> = min(example1.s2, example2.s2) doubleUntil max(example1.s2, example2.s2)
    val angleDegreesRange =
        min(example1.angleDegrees, example2.angleDegrees) doubleUntil max(example1.angleDegrees, example2.angleDegrees)

    override fun predicate(toTest: Parallelogram) =
        toTest.s1 in s1Range && toTest.s2 in s2Range && toTest.angleDegrees in angleDegreesRange

    override val generateToSpec = suspend {
        delay(random.nextLong(0L, maxDelayMillis))
        logger().debug { "\nGenerating ${Parallelogram::class.java.simpleName} from ${Thread.currentThread().name}" }
        Parallelogram(s1Range.randomInRange(), s2Range.randomInRange(), angleDegreesRange.randomInRange())
    }

}

class RectangleSpecifierByExample(
    override val example1: Rectangle,
    override val example2: Rectangle,
    override val maxDelayMillis: Long = 0L
) : TwoDimensionalSpecifierByExample<Rectangle>(example1, example2) {

    constructor(example: Rectangle) : this(example, example)

    // `doubleUntil` is a home grown method, defined the same way as the built-in Int.until method!
    // We can neither use the .. operator (does not include the start value), nor Double.until, the Kotlin folks did not implement that (yet)
    val lengthRange: ClosedRange<Double> =
        min(example1.length, example2.length) doubleUntil max(example1.length, example2.length)
    val widthRange: ClosedRange<Double> =
        min(example1.width, example2.width) doubleUntil max(example1.width, example2.width)

    override fun predicate(toTest: Rectangle) = toTest.length in lengthRange && toTest.width in widthRange

    override val generateToSpec = suspend {
        delay(random.nextLong(0L, maxDelayMillis))
        logger().debug { "\nGenerating ${Rectangle::class.java.simpleName} from ${Thread.currentThread().name}" }
        Rectangle(lengthRange.randomInRange(), widthRange.randomInRange())
    }
}

class SquareSpecifierByExample(
    override val example1: Square,
    override val example2: Square,
    override val maxDelayMillis: Long = 0L
) : TwoDimensionalSpecifierByExample<Square>(example1, example2) {

    constructor(example: Square) : this(example, example)

    val sideRange = min(example1.side, example2.side) doubleUntil max(example1.side, example2.side)

    override fun predicate(toTest: Square) = toTest.side in sideRange

    override val generateToSpec = suspend {
        delay(random.nextLong(0L, maxDelayMillis))
        logger().debug { "g\nGenerating ${Square::class.java.simpleName} from ${Thread.currentThread().name}" }
        Square(sideRange.randomInRange())
    }
}

interface TwoDimensionalSpecifier<T : TwoDimensional> {
    fun predicate(toTest: T): Boolean
    val generateToSpec: suspend () -> T
    val maxDelayMillis: Long
}
