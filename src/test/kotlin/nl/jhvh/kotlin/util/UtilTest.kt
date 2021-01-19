package nl.jhvh.kotlin.util

import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.random.Random


internal class UtilTest {

    @Test
    fun testDoubleUntil() {
        var range = 1.0 doubleUntil 2.0
        assertThat(range.isEmpty()).isFalse
        assertThat(range.start).isEqualTo(1.0)
        assertThat(range.endInclusive).isEqualTo(2.0)
        assertThat(1.2 in range).isTrue
        assertThat(1.0 in range).isTrue
        assertThat(2.0 in range).isTrue

        assertThat((1.0 - 1e-15) in range).isFalse
        assertThat((2.0 + 1e-15) in range).isFalse
        assertThat(-1.2 in range).isFalse

        assertThat(Double.NaN in range).isFalse

        assertThat(Double.NEGATIVE_INFINITY in range).isFalse
        assertThat(Double.POSITIVE_INFINITY in range).isFalse

        assertThat(Double.MIN_VALUE in range).isFalse
        assertThat(Double.MAX_VALUE in range).isFalse

        range = 0.0 doubleUntil 0.0
        assertThat(range.isEmpty()).isFalse // because both boundaries included
        assertThat(0.0 in range).isTrue
        assertThat(-0.0 in range).isFalse // ... that's how Double semantics work :-(

        range = 0.0 doubleUntil -0.0
        assertThat(range.isEmpty()).isTrue
        assertThat(0.0 in range).isFalse
        assertThat(-0.0 in range).isFalse

        range = -0.0 doubleUntil 0.0
        assertThat(range.isEmpty()).isFalse // because both boundaries included
        assertThat(0.0 in range).isTrue
        assertThat(-0.0 in range).isTrue

        range = 0.0 doubleUntil 3.5
        assertThat(range.isEmpty()).isFalse
        assertThat(0.0 in range).isTrue
        assertThat(-0.0 in range).isFalse


        range = 3.5 doubleUntil 3.0
        assertThat(range.isEmpty()).isTrue
        assertThat(range.start in range).isFalse
        assertThat(range.endInclusive in range).isFalse

        range = Double.NEGATIVE_INFINITY doubleUntil Double.POSITIVE_INFINITY
        assertThat(range.isEmpty()).isFalse
        assertThat(range.start in range).isTrue
        assertThat(range.endInclusive in range).isTrue
        assertThat(Double.MIN_VALUE in range).isTrue
        assertThat(Double.MAX_VALUE in range).isTrue

        range = Double.NEGATIVE_INFINITY doubleUntil Double.POSITIVE_INFINITY
        assertThat(range.start).isEqualTo(Double.NEGATIVE_INFINITY)
        assertThat(range.endInclusive).isEqualTo(Double.POSITIVE_INFINITY)
        assertThat(range.isEmpty()).isFalse
        assertThat(range.start in range).isTrue
        assertThat(range.endInclusive in range).isTrue
        assertThat(Double.MIN_VALUE in range).isTrue
        assertThat(Double.MAX_VALUE in range).isTrue

        range = Double.MIN_VALUE doubleUntil Double.MAX_VALUE
        assertThat(range.isEmpty()).isFalse
        assertThat(range.start in range).isTrue
        assertThat(range.endInclusive in range).isTrue
        assertThat(Double.MIN_VALUE in range).isTrue
        assertThat(Double.MAX_VALUE in range).isTrue
        assertThat(Double.NEGATIVE_INFINITY in range).isFalse
        assertThat(Double.POSITIVE_INFINITY in range).isFalse
    }

    @Test
    fun `test randomInRange with mocked Random`() {
        val random: Random = mockk()
        val range = 0.0 doubleUntil 10.0

        val lowValueCapturer: CapturingSlot<Double> = slot()
        val highValueCapturer: CapturingSlot<Double> = slot()

        var capturedLowValue = 0.0
        var capturedHighValue = 0.0
        var diff = 0.0

        every { random.nextDouble(capture(lowValueCapturer), capture(highValueCapturer)) } answers {
            capturedLowValue = lowValueCapturer.captured
            capturedHighValue = highValueCapturer.captured
            diff = capturedHighValue - capturedLowValue
            capturedLowValue
        } andThen {
            capturedLowValue + diff * 0.1
        } andThen {
            capturedLowValue + diff * 0.2
        } andThen {
            capturedLowValue + diff * 0.3
        } andThen {
            capturedLowValue + diff * 0.4
        } andThen {
            capturedLowValue + diff * 0.5
        } andThen {
            capturedLowValue + diff * 0.6
        } andThen {
            capturedLowValue + diff * 0.7
        } andThen {
            capturedLowValue + diff * 0.8
        } andThen {
            capturedLowValue + diff * 0.9
        } andThen {
            capturedHighValue
        }

        for (x in 0..10) {
            assertThat(range.randomInRange(random)).isEqualTo(x.toDouble())
        }
    }

}