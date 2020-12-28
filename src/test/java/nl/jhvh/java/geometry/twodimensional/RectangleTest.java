package nl.jhvh.java.geometry.twodimensional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RectangleTest {

    @Mock
    private Parallelogram parallelogramDelegateMock;

    private final double length = 6.3d;
    private final double width = 9.2d;

    @InjectMocks
    private final Rectangle subjectForDelegationTests = new Rectangle(length, width);

    @BeforeEach
    void setUp() {
        reset(parallelogramDelegateMock);
    }

    @Test
    void getLength() {
        var square = new Rectangle(387.14d, 214.7d);
        assertThat(square.getLength()).isEqualTo(387.14d);
        verifyNoInteractions(parallelogramDelegateMock);
    }

    @Test
    void testGetAreaAndDelegationToRectangleClass() {
        // given
        var area = 65.2d;
        // when
        when(parallelogramDelegateMock.getArea()).thenReturn(area);

        // then
        assertThat(subjectForDelegationTests.getArea()).isEqualTo(area);
        // verify that it was delegated
        verify(parallelogramDelegateMock, times(1)).getArea();
        verify(parallelogramDelegateMock, never()).getCircumference();
        verifyNoMoreInteractions(parallelogramDelegateMock);
    }

    @Test
    void getWidth() {
        var rectangle = new Rectangle(9.0d, 387.2e35d);
        assertThat(rectangle.getWidth()).isEqualTo(387.2e35d);
        verifyNoMoreInteractions(parallelogramDelegateMock);
    }

    @Test
    void testGetAreaAndDelegationToParallelogramClass() {
        // given
        var area = 380.2d;
        // when
        when(parallelogramDelegateMock.getArea()).thenReturn(area);

        // then
        assertThat(subjectForDelegationTests.getArea()).isEqualTo(area);
        // verify that it was delegated
        verify(parallelogramDelegateMock, times(1)).getArea();
        verify(parallelogramDelegateMock, never()).getCircumference();
        verifyNoMoreInteractions(parallelogramDelegateMock);
    }

    @Test
    void testGetCircumferenceAndDelegationToParallelogramClass() {
        // given
        var circumference = 214.12d;
        // when
        when(parallelogramDelegateMock.getCircumference()).thenReturn(circumference);

        // then
        assertThat(subjectForDelegationTests.getCircumference()).isEqualTo(circumference);
        // verify that it was delegated
        verify(parallelogramDelegateMock, times(1)).getCircumference();
        verify(parallelogramDelegateMock, never()).getArea();
        verifyNoMoreInteractions(parallelogramDelegateMock);
    }

    @Test
    void verifyThatSideLengthsAreValidatedByDelegateOnConstruction() {

        // fails: side < 0
        var validationException = assertThrows(IllegalArgumentException.class, () -> new Rectangle(-0.000001d, 2.1d));

        // Filter out calling classes (JUnit and test class) and anything called by Rectangle constructor,
        // so keeping only the part we are interested in
        var validationStackTrace = Arrays.stream(validationException.getStackTrace())
                .takeWhile(stackTraceElement -> stackTraceElement.getClassName().equals(Parallelogram.class.getName())
                        || stackTraceElement.getClassName().equals(Rectangle.class.getName())
                ).collect(Collectors.toList());

        var rectangleConstructorCall = validationStackTrace.get(validationStackTrace.size()-1);
        assertThat(rectangleConstructorCall.getClassName()).isEqualTo(Rectangle.class.getName());
        assertThat(rectangleConstructorCall.getMethodName()).isEqualTo("<init>");

        var parallelogramConstructorCall = validationStackTrace.get(validationStackTrace.size()-2);
        assertThat(parallelogramConstructorCall.getClassName()).isEqualTo(Parallelogram.class.getName());
        assertThat(parallelogramConstructorCall.getMethodName()).isEqualTo("<init>");
        // now we have proven:
        //  1) the Parallelogram constructor is called
        //  2) the exception is thrown
        // So we can rely on the Parallelogram constructor (and it's tests) for validation
    }

    @Test
    @Disabled("No copy method in Java for data classes!")
    void copy() {
        fail("No copy method in Java for data classes!");
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
    void testToString() {
        var length = 8.53213d;
        var width = 11.74454d;
        var rectangle = new Rectangle(length, width);
        var str = rectangle.toString();
        assertThat(str).contains(rectangle.getClass().getSimpleName(), "length", String.valueOf(length), "width", String.valueOf(width));
        assertThat(str).doesNotContain("s1", "s2", "angleDegrees");  // should not use toString of delegate!

        // verify that the lazy attribute was not called
        verify(parallelogramDelegateMock, never()).getArea();
        verifyNoMoreInteractions(parallelogramDelegateMock);
    }

    @Test
    void testEquals() {
        // given
        var rectangle1 = new Rectangle(14.0d, 15.0d);
        var rectangle2 = new Rectangle(14.0d, 15.0d);
        // check equality
        assertThat(rectangle1).isEqualTo(rectangle2);

        // given
        var rectangleDifferent = new Rectangle(14.0001d, 15.0d);
        // check inequality
        assertThat(rectangleDifferent).isNotEqualTo(rectangle1);
        // given
        rectangleDifferent = new Rectangle(14.0d, 8.0d);
        // check inequality
        assertThat(rectangleDifferent).isNotEqualTo(rectangle1);

        // verify that the lazy attribute was not called
        verify(parallelogramDelegateMock, never()).getArea();
    }

}