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
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertFailsWith
import kotlin.test.fail

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class RectangleTest {

    // values can be reassigned by individual tests
    private var circumference = 0.0
    private var area = 0.0

    private lateinit var parallelogramDelegateMock: Parallelogram

    @BeforeAll
    fun setUpTest() {
        // Note that this mock will stay active until unmockkConstructor is called!
        mockkConstructor(Parallelogram::class)

        every { anyConstructed<Parallelogram>().circumference } answers {circumference}
        every { anyConstructed<Parallelogram>().area } answers {area}

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
        clearMocks(parallelogramDelegateMock, answers = false, recordedCalls = true, verificationMarks = true )

        // Note that this mock will stay active until unmockkConstructor is called!
        mockkConstructor(Parallelogram::class)

        every { anyConstructed<Parallelogram>().circumference } answers {circumference}
        every { anyConstructed<Parallelogram>().area } answers {area}

        parallelogramDelegateMock = Parallelogram(0.0, 0.0, 0.0)
    }

    @Test
    fun getLength() {
        val rectangle = Rectangle(387.14, 14.0)
        assertThat(rectangle.length).isEqualTo(387.14)
        confirmVerified(parallelogramDelegateMock)
    }

    @Test
    fun getWidth() {
        val rectangle = Rectangle(9.0, 387.2e35)
        assertThat(rectangle.width).isEqualTo(387.2e35)
        confirmVerified(parallelogramDelegateMock)
    }

    @Test
    fun `test getArea and delegation to Parallelogram class`() {
        // given
        val rectangle = Rectangle(1.0, 2.0)
        this.area = 380.2
        // when, then
        assertThat(rectangle.area).isEqualTo(this.area)
        // verify that it was delegated
        verify (exactly = 1) { parallelogramDelegateMock.area }
        verify (exactly = 0) { parallelogramDelegateMock.circumference }
    }

    @Test
    fun `test getCircumference and delegation to Parallelogram class`() {
        // given
        val rectangle = Rectangle(1.0, 2.0)
        this.circumference = 214.12
        // when, then
        assertThat(rectangle.circumference).isEqualTo(this.circumference)
        // verify that it was delegated
        verify (exactly = 1) { parallelogramDelegateMock.circumference }
        verify (exactly = 0) { parallelogramDelegateMock.area }
    }

    @Test
    fun `verify that side lengths are validated on construction`() {
        // We can't verify the validation call with a constructor mock,
        // so we do a simple test to prove that validation is done on construction of the Parallelogram delegate.
        unmockkConstructor(Parallelogram::class)

        // OK
        Rectangle(1.8, 2.0)
        // fails: any input < 0
        val validationException: IllegalArgumentException = assertFailsWith { Rectangle(-0.000001, 0.0) }

        // Filter out calling classes (JUnit and test class), so keeping only the first few
        val validationStackTrace = validationException.stackTrace
                .takeWhile { it.className == Parallelogram::class.java.name || it.className == Rectangle::class.java.name }
        // println(validationStackTrace.joinToString(separator = "\n"))

        val rectangleConstructorCall = validationStackTrace.last()
        assertThat(rectangleConstructorCall.className).isEqualTo(Rectangle::class.java.name)
        assertThat(rectangleConstructorCall.methodName).isEqualTo("<init>")

        val parallelogramConstructorCall = validationStackTrace.dropLast(1).last()
        assertThat(parallelogramConstructorCall.className).isEqualTo(Parallelogram::class.java.name)
        assertThat(parallelogramConstructorCall.methodName).isEqualTo("<init>")
        // now we have proven:
        //  1) the Parallelogram constructor is called
        //  2) the exception is thrown
        // So we can rely on the Parallelogram constructor (and it's tests) for validation
    }

    @Test
    @Disabled("No copy method in Java for data classes!")
    fun copy() {
        fail("No copy method in Java for data classes!")
//        val length = 8.453e8
//        val width = 4.8721e13
//        val rectangle1 = Rectangle(length, width)
//
//        val copy1 = rectangle1.copy()
//        assertThat(copy1).isEqualTo(rectangle1)
//        assertThat(copy1.length).isEqualTo(rectangle1.length)
//        assertThat(copy1.width).isEqualTo(rectangle1.width)
//
//        val amendedCopy1 = rectangle1.copy(width = width/5.0)
//        assertThat(amendedCopy1).isNotEqualTo(rectangle1)
//        assertThat(amendedCopy1.length).isEqualTo(rectangle1.length)
//        assertThat(amendedCopy1.width).isCloseTo(width / 5.0, Assertions.offset(0.1))
//
//        // verify that the lazy attribute was not called
//        verify (exactly = 0) { parallelogramDelegateMock.area }
//        confirmVerified(parallelogramDelegateMock)
    }

    @Test
    fun testToString() {
        val length = 8.53213
        val width = 11.74454
        val rectangle = Rectangle(length, width)
        val str = rectangle.toString()
        assertThat(str).contains(rectangle.javaClass.simpleName, "length", "$length", "width", "$width")
        assertThat(str).doesNotContain("s1", "s2", "angleDegrees")  // should not use toString of delegate!

        // verify that the lazy attribute was not called
        verify (exactly = 0) { parallelogramDelegateMock.area }
        confirmVerified(parallelogramDelegateMock)
    }

    @Test
    fun testEquals() {
        // given
        val rectangle1 = Rectangle(14.0, 15.0)
        val rectangle2 = Rectangle(14.0, 15.0)
        // check equality
        assertThat(rectangle1).isEqualTo(rectangle2)

        // given
        var rectangleDifferent = Rectangle(14.0001, 15.0)
        // check inequality
        assertThat(rectangleDifferent).isNotEqualTo(rectangle1)
        // given
        rectangleDifferent = Rectangle(14.0, 8.0)
        // check inequality
        assertThat(rectangleDifferent).isNotEqualTo(rectangle1)

        // verify that the lazy attribute was not called
        verify (exactly = 0) { parallelogramDelegateMock.area }
        confirmVerified(parallelogramDelegateMock)
    }

}