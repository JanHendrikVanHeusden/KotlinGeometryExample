package nl.jhvh.kotlin.util

import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration


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

    @ExperimentalTime
    @Test
    fun `test diff between LocalDateTime values`() {
        val now = LocalDateTime.now()

        assertThat(now.plusDays(1L).plusHours(3) - now ).isEqualTo(27.toDuration(DurationUnit.HOURS))

        // test with various random diff values
        for (x in 1..1000) {
            // diff values roughly -292 to +292 years
            val nanosDiff = Random.nextLong()
            val t2 = now.plusNanos(nanosDiff)
            assertThat(t2 - now).isEqualTo(nanosDiff.toDuration(DurationUnit.NANOSECONDS))
        }
        // Long.MAX_VALUE / Long.MIN_VALUE represent +/- 292 years, which is about 106751.991 days
        assertThat((now.plusNanos(Long.MAX_VALUE) - now).inDays).isCloseTo(106751.991, Offset.offset(0.001))
        assertThat((now.minusNanos(Long.MAX_VALUE) - now).inDays).isCloseTo(-106751.991, Offset.offset(0.001))
        assertThat((now.plusNanos(Long.MIN_VALUE) - now).inDays).isCloseTo(-106751.991, Offset.offset(0.001))
        assertThat((now.minusNanos(Long.MIN_VALUE) - now).inDays).isCloseTo(106751.991, Offset.offset(0.001))

        // No overflow / underflow with value > MAX_VALUE or value < MIN_VALUE
        val veryLongAgo = now.minusNanos(Long.MAX_VALUE).minusNanos(Long.MAX_VALUE)
        assertThat((now-veryLongAgo).inDays).isCloseTo(213503.982, Offset.offset(0.001))
        val veryLongAhead = now.plusNanos(Long.MAX_VALUE).plusNanos(Long.MAX_VALUE)
        assertThat((veryLongAhead-now).inDays).isCloseTo(213503.982, Offset.offset(0.001))
    }

}