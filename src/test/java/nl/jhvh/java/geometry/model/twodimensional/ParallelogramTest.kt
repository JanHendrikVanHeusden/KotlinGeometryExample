package nl.jhvh.java.geometry.model.twodimensional

import io.mockk.clearMocks
import io.mockk.spyk
import io.mockk.verify
import nl.jhvh.java.geometry.GeometryUtil.radiansToDegrees
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.math.atan
import kotlin.math.sin
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.fail

internal class ParallelogramTest {

    private val angleDegreesToRadians: (Double) -> Double = { degrees -> degrees * (Math.PI/180.0) }
    private val calcArea: (Double, Double, Double) -> Double = { s1, s2, degrees -> s1 * s2 * sin(angleDegreesToRadians(degrees)) }

    @Test
    fun `verify that side lengths are validated on construction`() {
        // OK: all inputs 0
        Parallelogram(0.0, 0.0, 0.0)
        // OK: very large values
        Parallelogram(1.8e58, 0.0, 88.0)

        // fails: any input < 0
        var s1 = -0.000001
        var s2 = 0.0
        val expectedMessage = { "Lengths of both sides must be positive, but sides s1 , s2 are $s1 , $s2" }

        // Some assertions are done twice, just to compare syntax: one with kotlin.test assertion, and one with Assertj

        // kotlin.test Assertions
        var message = assertFailsWith<IllegalArgumentException> { Parallelogram(s1, s2, 0.0) }
            .message
        // kotlin.test Assertions
        assertEquals(expectedMessage(), message)
        // Often expected and actual are swapped accidentally, so better to use named notation when using Kotlin test:
        assertEquals(expected = expectedMessage(), actual = message)

        // AssertJ
        assertThatThrownBy { Parallelogram(s1, s2, 0.0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(expectedMessage())

        // AssertJ nicely avoids hassle of actual and expected, intuitively right here:
        assertThat(message).isEqualTo(expectedMessage())

        s1 = 50.0
        s2 = -0.00000003
        message = assertFailsWith<IllegalArgumentException> { Parallelogram(s1, s2, 0.0) }
                .message
        assertThat(message).isEqualTo(expectedMessage())

        s1 = -5.0
        s2 = -1.0
        message = assertFailsWith<IllegalArgumentException> { Parallelogram(s1, s2, 0.0) }
                .message
        assertThat(message).isEqualTo(expectedMessage())

        // very large values
        s1 = 8.23e85
        s2 = -7.8134e89
        message = assertFailsWith<IllegalArgumentException> { Parallelogram(s1, s2, 0.0) }
                .message
        assertThat(message).isEqualTo(expectedMessage())
    }

    @Test
    fun `verify that angle is validated on construction`() {
        // OK: angle 0
        Parallelogram(1.0, 2.0, 0.0)
        // OK: angle 90
        Parallelogram(1.8e58, 0.0, 90.0)

        // fails: angle < 0
        var angle = -0.00000000001
        val expectedMessage: () -> String = { "The angle of a parallelogram must be in range 0.0 and 90.0, but is $angle" }

        var message = assertFailsWith<IllegalArgumentException> { Parallelogram(1.0, 2.0, angle) }
                .message
        assertThat(message).isEqualTo(expectedMessage())

        angle = 90.00000000001
        message = assertFailsWith<IllegalArgumentException> { Parallelogram(15.0, 38.4, angle) }
                .message
        assertThat(message).isEqualTo(expectedMessage())

        angle = 361.0
        message = assertFailsWith<IllegalArgumentException> { Parallelogram(24.8, 11.2, angle) }
                .message
        assertThat(message).isEqualTo(expectedMessage())
    }

    @Test
    fun getAngleRadians() {
        val parallelogram1 = Parallelogram(10.0, 15.0, 45.0)
        assertThat(parallelogram1.angleRadians).isCloseTo(Math.PI/4.0, offset(0.00001))

        val parallelogram2 = Parallelogram(4.3564, 125.68, 30.0)
        assertThat(parallelogram2.angleRadians).isCloseTo(Math.PI/6.0, offset(0.00001))
    }

    @Test
    fun getLength() {
        var s1 = 10.0
        var s2 = 15.0
        val parallelogram1 = Parallelogram(s1, s2, 76.4)
        assertThat(parallelogram1.length).isEqualTo(s1) // s1 is synonym for length

        s1 = 22.458
        s2 = 314543.12
        val parallelogram2 = Parallelogram(s1, s2, 66.832)
        assertThat(parallelogram2.length).isEqualTo(s1) // s1 is synonym for length
    }

    @Test
    fun getWidth() {
        // Let's define a parallelogram with s2 = 5, and where the tangent of the angle is 4/3
        // This results in a width of 4
        var s1 = 35.0
        var s2 = 5.0
        var angle = radiansToDegrees(atan(4.0 / 3.0))
        val parallelogram1 = Parallelogram(s1, s2, angle)
        assertThat(parallelogram1.width).isCloseTo(4.0, offset(0.00001))

        s1 = 35.0
        s2 = 5.0
        angle = 90.0
        val parallelogram2 = Parallelogram(s1, s2, angle)
        assertThat(parallelogram2.width).isCloseTo(s2, offset(0.00001)) // because angle = 90 degrees

        s1 = 20.0
        s2 = 10.0
        angle = 0.0 // so in fact it's 1-dimensional
        val parallelogram3 = Parallelogram(s1, s2, angle)
        assertThat(parallelogram3.width).isEqualTo(0.0) // because angle = 0 degrees (flat)

        s1 = 4.389
        s2 = 17.0
        angle = 30.0
        val parallelogram4 = Parallelogram(s1, s2, angle)
        assertThat(parallelogram4.width).isCloseTo(s2 * 0.5, offset(0.00001))  // sin(30 gr) = 0.5

        s1 = 438.0
        s2 = 527.0
        angle = 14.0
        val expectedWidth = s2 * 0.241921896  // sin(14 gr) = 0.241921896
        val parallelogram5 = Parallelogram(s1, s2, angle)
        assertThat(parallelogram5.width).isCloseTo(expectedWidth, offset(0.00001))

        // verify lazy initialization
        // given
        val spyk = spyk(parallelogram1, recordPrivateCalls = true)
        verify (exactly = 0) { spyk.width }
        clearMocks(spyk, verificationMarks = true, recordedCalls = true)
        // when
        spyk.width
        // then
        verify (exactly = 1) { spyk.width }
    }

    @Test
    fun getCircumference() {
        var s1 = 35.0
        var s2 = 5.0
        var expectedCircumference = 80.0 // 2 * s1 + 2 * s2
        var angle = 42.9
        val parallelogram1 = Parallelogram(s1, s2, angle)
        assertThat(parallelogram1.circumference).isCloseTo(expectedCircumference, offset(0.0000001))

        s1 = 8.453e12
        s2 = 4.8621e13
        expectedCircumference = 2.0 * (s1 + s2)
        angle = 66.45
        val parallelogram2 = Parallelogram(s1, s2, angle)
        assertThat(parallelogram2.circumference).isCloseTo(expectedCircumference, offset(1.0))
    }

    @Test
    fun getArea() {
        var s1 = 8.5
        var s2 = 11.7
        var angleDegrees = 45.0
        val spyk1 = spyk(Parallelogram(s1, s2, angleDegrees), recordPrivateCalls = true)

        verify (exactly = 0) { spyk1.area } // attribute not initialized yet, because lazy
        clearMocks(spyk1, verificationMarks = true, recordedCalls = true)
        assertThat(spyk1.area).isCloseTo(calcArea(s1, s2, angleDegrees), offset(0.0000001))
        verify (exactly = 1) { spyk1.area } // attribute initialized now, because called to calculate area

        s1 = 74.0
        s2 = 85.4
        angleDegrees = 14.37
        val parallelogram2 = Parallelogram(s1, s2, angleDegrees)
        assertThat(parallelogram2.area).isCloseTo(calcArea(s1, s2, angleDegrees), offset(0.0000001))
    }

    @Test
    @Disabled("No copy method in Java for data classes!")
    fun copy() {
        fail("No copy method in Java for data classes!")
//        val s1 = 8.453e12
//        val s2 = 4.8621e13
//        val angle = 66.45
//        val parallelogram1 = Parallelogram(s1, s2, angle)
//
//        val copy1 = parallelogram1.copy()
//        assertThat(copy1).isEqualTo(parallelogram1)
//        assertThat(copy1.s1).isEqualTo(parallelogram1.s1)
//        assertThat(copy1.s2).isEqualTo(parallelogram1.s2)
//        assertThat(copy1.angleDegrees).isEqualTo(parallelogram1.angleDegrees)
//
//        val amendedCopy1 = parallelogram1.copy(angleDegrees = angle/2.0)
//        assertThat(amendedCopy1).isNotEqualTo(parallelogram1)
//        assertThat(amendedCopy1.s1).isEqualTo(parallelogram1.s1)
//        assertThat(amendedCopy1.s2).isEqualTo(parallelogram1.s2)
//        assertThat(amendedCopy1.angleDegrees).isCloseTo(angle / 2.0, offset(0.000000001))
//        assertThat(amendedCopy1.angleRadians).isCloseTo(parallelogram1.angleRadians / 2.0, offset(0.000000001))
    }

    @Test
    fun testToString() {
        val s1 = 6.545645
        val s2 = 13.75321
        val angleDegrees = 45.0
        val parallelogram1 = Parallelogram(s1, s2, angleDegrees)
        val str = parallelogram1.toString()
        assertThat(str).contains(parallelogram1.javaClass.simpleName, "s1", "$s1", "s2", "$s2", "angleDegrees", "$angleDegrees")
    }

    @Test
    fun testEquals() {
        // given
        val parallelogram1 = Parallelogram(10.0, 15.0, 25.0)
        val parallelogram2 = Parallelogram(10.0, 15.0, 25.0)
        // check equality
        assertThat(parallelogram1).isEqualTo(parallelogram2)

        // given
        var parallelogramDifferent = Parallelogram(10.0001, 15.0, 25.0)
        // check inequality
        assertThat(parallelogramDifferent).isNotEqualTo(parallelogram1)
        // given
        parallelogramDifferent = Parallelogram(10.0, 8.0, 25.0)
        // check inequality
        assertThat(parallelogramDifferent).isNotEqualTo(parallelogram1)
        // given
        parallelogramDifferent = Parallelogram(10.0, 15.0, 33.0)
        // check inequality
        assertThat(parallelogramDifferent).isNotEqualTo(parallelogram1)
    }

    @Test
    fun `equals call should not call lazy attributes`() {
        // given
        val parallelogram1 = Parallelogram(10.0, 15.0, 25.0)
        val spyk1 = spyk(parallelogram1)
        verify (exactly = 0) { spyk1.width }
        verify (exactly = 0) { spyk1.area }

        val parallelogram2 = Parallelogram(10.0, 15.0, 25.0)
        val spyk2 = spyk(parallelogram2)
        verify (exactly = 0) { spyk2.width }
        verify (exactly = 0) { spyk2.area }
        // when
        assertThat(spyk1).isEqualTo(spyk2)
        // then
        verify (exactly = 0) { spyk1.width }
        verify (exactly = 0) { spyk1.area }
        verify (exactly = 0) { spyk2.width }
        verify (exactly = 0) { spyk2.area }
        spyk2.width
        verify (exactly = 1) { spyk2.width }

        // To show that changing a spyk property causes equals to return false
        val spykDifferent = spyk(Parallelogram(8.0, 15.0, 25.0))
        assertThat(spykDifferent).isNotEqualTo(spyk1)
    }

}