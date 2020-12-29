package nl.jhvh.java.geometry.model.twodimensional;

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
class SquareTest {

    @Mock
    private Rectangle rectangleDelegateMock;

    private final double sideLength = 6.3d;

    @InjectMocks
    private final Square subjectForDelegationTests = new Square(sideLength);

    @BeforeEach
    void setUp() {
        reset(rectangleDelegateMock);
    }

    @Test
    void getSide() {
        var square = new Square(387.14d);
        assertThat(square.getSide()).isEqualTo(387.14d);
        verifyNoInteractions(rectangleDelegateMock);
    }

    @Test
    void testGetAreaAndDelegationToRectangleClass() {
        // given
        var area = 65.2d;
        // when
        when(rectangleDelegateMock.getArea()).thenReturn(area);

        // then
        assertThat(subjectForDelegationTests.getArea()).isEqualTo(area);
        // verify that it was delegated
        verify(rectangleDelegateMock, times(1)).getArea();
        verify(rectangleDelegateMock, never()).getCircumference();
        verifyNoMoreInteractions(rectangleDelegateMock);
    }

    @Test
    void testGetCircumferenceAndDelegationToRectangleClass() {
        // given
        var circumference = 125.74d;
        // when
        when(rectangleDelegateMock.getCircumference()).thenReturn(circumference);

        // then
        assertThat(subjectForDelegationTests.getCircumference()).isEqualTo(circumference);
        // verify that it was delegated
        verify(rectangleDelegateMock, times(1)).getCircumference();
        verify(rectangleDelegateMock, never()).getArea();
        verifyNoMoreInteractions(rectangleDelegateMock);
    }

    @Test
    void verifyThatSideLengthsAreValidatedByDelegateOnConstruction() {
        // OK
        new Square(1.8d);

        // fails: side < 0
        var validationException = assertThrows(IllegalArgumentException.class, () -> new Square(-0.000001d));

        // Filter out calling classes (JUnit and test class) and anything called by Rectangle constructor,
        // so keeping only the part we are interested in
        var validationStackTrace = Arrays.stream(validationException.getStackTrace())
                .dropWhile(stackTraceElement -> !stackTraceElement.getClassName().equals(Rectangle.class.getName())
                        && !stackTraceElement.getClassName().equals(Square.class.getName())
                )
                .takeWhile(stackTraceElement -> stackTraceElement.getClassName().equals(Rectangle.class.getName())
                        || stackTraceElement.getClassName().equals(Square.class.getName())
                ).collect(Collectors.toList());

        var squareConstructorCall = validationStackTrace.get(validationStackTrace.size()-1);
        assertThat(squareConstructorCall.getClassName()).isEqualTo(Square.class.getName());
        assertThat(squareConstructorCall.getMethodName()).isEqualTo("<init>");

        var rectangleConstructorCall = validationStackTrace.get(validationStackTrace.size()-2);
        assertThat(rectangleConstructorCall.getClassName()).isEqualTo(Rectangle.class.getName());
        assertThat(rectangleConstructorCall.getMethodName()).isEqualTo("<init>");
        // now we have proven:
        //  1) the Rectangle constructor is called
        //  2) the exception is thrown
        // So we can rely on the Rectangle constructor (and it's tests) for validation
    }

    @Disabled("No copy method in Java for data classes!")
    void copy() {
        fail("No copy method in Java for data classes!");
//        val side = 8.453e8
//        val square1 = Square(side)
//
//        val copy1 = square1.copy()
//        assertThat(copy1).isEqualTo(square1)
//        assertThat(copy1.side).isEqualTo(square1.side)
//
//        val amendedCopy1 = square1.copy(side = side / 5.0)
//        assertThat(amendedCopy1).isNotEqualTo(square1)
//        assertThat(amendedCopy1.side).isEqualTo(square1.side / 5.0)
//
//        // verify that the lazy attribute was not called
//        verify(exactly = 0) { parallelogramDelegateMock.area }
//        confirmVerified(parallelogramDelegateMock)
    }

    @Test
    void testToString() {
        var side = 8.53213d;
        var square = new Square(side);
        var str = square.toString();
        assertThat(str).contains(square.getClass().getSimpleName(), "side", String.valueOf(side));
        assertThat(str).doesNotContain("s1", "s2", "angleDegrees", "length", "width");  // should not use toString of delegate!

        // verify that the lazy attribute was not called
        verify(rectangleDelegateMock, never()).getArea();
        verifyNoMoreInteractions(rectangleDelegateMock);
    }

    @Test
    void testEquals() {
        // given
        var square1 = new Square(14.0d);
        var square2 = new Square(14.0d);
        // check equality
        assertThat(square1).isEqualTo(square2);

        // given
        var squareDifferent = new Square(14.0001d);
        // check inequality
        assertThat(squareDifferent).isNotEqualTo(square1);
        // given
        squareDifferent = new Square(8.0d);
        // check inequality
        assertThat(squareDifferent).isNotEqualTo(square1);

        // verify that the lazy attribute was not called
        verify(rectangleDelegateMock, never()).getArea();
    }

}