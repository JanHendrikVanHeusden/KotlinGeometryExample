package nl.jhvh.kotlin.geometry.client.concurrent.polling

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import nl.jhvh.kotlin.geometry.model.twodimensional.Parallelogram
import nl.jhvh.kotlin.geometry.model.twodimensional.Rectangle
import nl.jhvh.kotlin.geometry.model.twodimensional.Square
import nl.jhvh.kotlin.geometry.model.twodimensional.TwoDimensional
import nl.jhvh.kotlin.geometry.service.ParallelogramSpecifierByExample
import nl.jhvh.kotlin.geometry.service.RectangleSpecifierByExample
import nl.jhvh.kotlin.geometry.service.SquareSpecifierByExample
import nl.jhvh.kotlin.util.logger
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

interface DataRepository<T> {
    suspend fun getData(): T?
}

@ExperimentalTime
class TwoDimensionalRepository(
    private val timeOut: Duration = 5.toDuration(DurationUnit.SECONDS),
    generationMaxDelay: Duration = 0.toDuration(DurationUnit.SECONDS)
) : DataRepository<TwoDimensional> {

    private val maxDelayMillis = generationMaxDelay.toLongMilliseconds()
    private val logger = logger()

    private val parSpec = ParallelogramSpecifierByExample(
        Parallelogram(0.0, 0.0, 0.0),
        Parallelogram(100.0, 100.0, 90.0),
        maxDelayMillis = maxDelayMillis
    )
    private val rectSpec = RectangleSpecifierByExample(
        Rectangle(0.0, 0.0),
        Rectangle(100.0, 100.0),
        maxDelayMillis = maxDelayMillis
    )
    private val squareSpec = SquareSpecifierByExample(
        Square(0.0),
        Square(100.0),
        maxDelayMillis = maxDelayMillis
    )

    private val allSpecs = listOf(parSpec, rectSpec, squareSpec)
    private fun randomSpec() = allSpecs[Random.nextInt(0, 3)]

    override suspend fun getData(): TwoDimensional? = run {
        var result: TwoDimensional? = null
        try {
            @Suppress("UNCHECKED_CAST")
            withTimeout(timeOut) {
                result = randomSpec().generateWithDelay()
            }
        } catch (e: TimeoutCancellationException) {
            logger.warn { "Timed out after ${timeOut}" }
        }
        return result
    }

}