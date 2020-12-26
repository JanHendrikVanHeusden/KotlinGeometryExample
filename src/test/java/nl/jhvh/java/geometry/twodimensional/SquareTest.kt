package nl.jhvh.java.geometry.twodimensional

import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockkConstructor
import io.mockk.unmockkConstructor
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertFailsWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SquareTest {

    // values can be reassigned by individual tests
    private var circumference = 0.0
    private var area = 0.0

    private lateinit var parallelogramDelegateMock: Parallelogram

    @BeforeAll
    fun setUpTest() {
        // Note that this mock will stay active until unmockkConstructor is called!
        mockkConstructor(Parallelogram::class)

        every { anyConstructed<Parallelogram>().circumference } answers { circumference }
        every { anyConstructed<Parallelogram>().area } answers { area }

        parallelogramDelegateMock = Parallelogram(0.0, 0.0, 0.0)
    }

    @AfterAll
    fun tearDownTest() {
        // Important!
        unmockkConstructor(Parallelogram::class)
    }

    @BeforeEach
    fun setUp() {
        circumference = 0.0
        area = 0.0
        clearMocks(parallelogramDelegateMock, answers = false, recordedCalls = true, verificationMarks = true)

        // Note that this mock will stay active until unmockkConstructor is called!
        mockkConstructor(Parallelogram::class)

        every { anyConstructed<Parallelogram>().circumference } answers { circumference }
        every { anyConstructed<Parallelogram>().area } answers { area }

        parallelogramDelegateMock = Parallelogram(0.0, 0.0, 0.0)
    }

    @Test
    fun getLength() {
        val square = Square(387.14)
        assertThat(square.side).isEqualTo(387.14)
        confirmVerified(parallelogramDelegateMock)
    }

    @Test
    fun `test getArea and delegation to Parallelogram class`() {
        // given
        val square = Square(1.0)
        this.area = 380.2
        // when, then
        assertThat(square.area).isEqualTo(this.area)
        // verify that it was delegated
        verify(exactly = 1) { parallelogramDelegateMock.area }
        verify(exactly = 0) { parallelogramDelegateMock.circumference }
    }

    @Test
    fun `test getCircumference and delegation to Parallelogram class`() {
        // given
        val square = Square(1.0)
        this.circumference = 203.87
        // when, then
        assertThat(square.circumference).isEqualTo(this.circumference)
        // verify that it was delegated
        verify(exactly = 1) { parallelogramDelegateMock.circumference }
        verify(exactly = 0) { parallelogramDelegateMock.area }
    }

    @Test
    fun `verify that side lengths are validated on construction`() {
        // We can't verify the validation call with a constructor mock,
        // so we do a simple test to prove that validation is done on construction of the Rectangle delegate.
        unmockkConstructor(Parallelogram::class)

        // OK
        Square(1.8)
        // fails: any input < 0
        val validationException: IllegalArgumentException = assertFailsWith { Square(-0.000001) }

        // Filter out calling classes (JUnit and test class) and anything called by Rectangle constructor,
        // so keeping only the part we are interested in
        val validationStackTrace = validationException.stackTrace
                .dropWhile {
                    it.className != Rectangle::class.java.name
                            && it.className != Square::class.java.name
                }
                .takeWhile {
                    it.className == Parallelogram::class.java.name
                            || it.className == Rectangle::class.java.name
                            || it.className == Square::class.java.name
                }
        // println(validationStackTrace.joinToString(separator = "\n"))

        val squareConstructorCall = validationStackTrace.last()
        assertThat(squareConstructorCall.className).isEqualTo(Square::class.java.name)
        assertThat(squareConstructorCall.methodName).isEqualTo("<init>")

        val rectangleConstructorCall = validationStackTrace.dropLast(1).last()
        assertThat(rectangleConstructorCall.className).isEqualTo(Rectangle::class.java.name)
        assertThat(rectangleConstructorCall.methodName).isEqualTo("<init>")
        // now we have proven:
        //  1) the Rectangle constructor is called
        //  2) the exception is thrown
        // So we can rely on the Rectangle constructor (and it's tests) for validation
    }

    @Test
    fun copy() {
        val side = 8.453e8
        val square1 = Square(side)

        val copy1 = Square(8.453e8)
        assertThat(copy1).isEqualTo(square1)
        assertThat(copy1.side).isEqualTo(square1.side)

        val amendedCopy1 = Square(side / 5.0)
        assertThat(amendedCopy1).isNotEqualTo(square1)
        assertThat(amendedCopy1.side).isEqualTo(square1.side / 5.0)

        // verify that the lazy attribute was not called
        verify(exactly = 0) { parallelogramDelegateMock.area }
        confirmVerified(parallelogramDelegateMock)
    }

    @Test
    fun testToString() {
        val side = 8.53213
        val square = Square(side)
        val str = square.toString()
        assertThat(str).contains(square.javaClass.simpleName, "side", "$side")
        assertThat(str).doesNotContain("s1", "s2", "angleDegrees", "length", "width")  // should not use toString of delegate!

        // verify that the lazy attribute was not called
        verify(exactly = 0) { parallelogramDelegateMock.area }
        confirmVerified(parallelogramDelegateMock)
    }

    @Test
    fun testEquals() {
        // given
        val square1 = Square(14.0)
        val square2 = Square(14.0)
        // check equality
        assertThat(square1).isEqualTo(square2)

        // given
        var squareDifferent = Square(14.0001)
        // check inequality
        assertThat(squareDifferent).isNotEqualTo(square1)
        // given
        squareDifferent = Square(8.0)
        // check inequality
        assertThat(squareDifferent).isNotEqualTo(square1)

        // verify that the lazy attribute was not called
        verify(exactly = 0) { parallelogramDelegateMock.area }
        confirmVerified(parallelogramDelegateMock)
    }

}