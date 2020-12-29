package nl.jhvh.java.geometry.model.twodimensional;

import nl.jhvh.java.geometry.util.GeometryUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.lang.Math.atan;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParallelogramTest {

    private Double angleDegreesToRadians(Double degrees) {
        return degrees * (Math.PI / 180.0d);
    }

    private Double calcArea(Double s1, Double s2, Double degrees) {
        return s1 * s2 * Math.sin(angleDegreesToRadians(degrees));
    }

    @Test
    void verifyThatSideLengthsAreValidatedOnConstruction() {
        // OK: all inputs 0
        new Parallelogram(0.0d, 0.0d, 0.0d);
        // OK: very large values
        new Parallelogram(1.8e58d, 0.0d, 88.0d);

        // fails: any input < 0

//        var s1 = -0.000001;
//        var s2 = 0.0;
//        var expectedMessage = new Supplier<String>() {
//            @Override
//            public String get() {
//                return ("Lengths of both sides must be positive, but sides s1 , s2 are " + s1 + " , " + s2);
//            }
//        };
//        // OOPS!! DOES NOT COMPILE: local variables referenced from an inner class must be final or effectively final
//        assertThatThrownBy(() -> { new Parallelogram(s1, s2, 0.0); })
//                .isExactlyInstanceOf(IllegalArgumentException.class)
//                .hasMessage(expectedMessage.get());
//
//        s1 = 50.0;
//        s2 = -0.00000003;

        assertThatThrownBy(() -> new Parallelogram(-0.000001d, 0.0d, 0.0d))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lengths of both sides must be positive, but sides s1 , s2 are " + -0.000001d + " , " + 0.0d);

        assertThatThrownBy(() -> new Parallelogram(50.0d, -0.00000003d, 0.0))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lengths of both sides must be positive, but sides s1 , s2 are " + 50.0d + " , " + -0.00000003d);

        assertThatThrownBy(() -> new Parallelogram(-5.0d, -1.0d, 0.0d))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lengths of both sides must be positive, but sides s1 , s2 are " + -5.0d + " , " + -1.0d);

        assertThatThrownBy(() -> new Parallelogram(8.23e85d, -7.8134e89d, 0.0d))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lengths of both sides must be positive, but sides s1 , s2 are " + 8.23e85d + " , " + -7.8134e89d);
    }

    @Test
    void verifyThatAngleIsValidatedOnConstruction() {
        // OK: angle 0
        new Parallelogram(1.0, 2.0, 0.0);
        // OK: angle 90
        new Parallelogram(1.8e58, 0.0, 90.0);

        // fails: angle < 0
        assertThatThrownBy(() -> new Parallelogram(1.0d, 2.0d, -0.00000000001d))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The angle of a parallelogram must be in range 0.0 and 90.0, but is " + -0.00000000001d);

        assertThatThrownBy(() -> new Parallelogram(1.0d, 2.0d, 90.00000000001d))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The angle of a parallelogram must be in range 0.0 and 90.0, but is " + 90.00000000001d);

        assertThatThrownBy(() -> new Parallelogram(1.0d, 2.0d, 361.0d))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The angle of a parallelogram must be in range 0.0 and 90.0, but is " + 361.0d);
    }

    @Test
    void getAngleRadians() {
        var parallelogram1 = new Parallelogram(10.0, 15.0, 45.0);
        assertThat(parallelogram1.getAngleRadians()).isCloseTo(Math.PI/4.0, offset(0.00001));

        var parallelogram2 = new Parallelogram(4.3564, 125.68, 30.0);
        assertThat(parallelogram2.getAngleRadians()).isCloseTo(Math.PI/6.0, offset(0.00001));
    }

    @Test
    void getLength() {
        var s1 = 10.0d;
        var s2 = 15.0d;
        var parallelogram1 = new Parallelogram(s1, s2, 76.4d);
        assertThat(parallelogram1.getLength()).isEqualTo(s1); // s1 is synonym for length

        s1 = 22.458d;
        s2 = 314543.12d;
        var parallelogram2 = new Parallelogram(s1, s2, 66.832d);
        assertThat(parallelogram2.getLength()).isEqualTo(s1); // s1 is synonym for length
    }

    @Test
    void getWidth() {
        // Let's define a parallelogram with s2 = 5, and where the tangent of the angle is 4/3
        // This results in a width of 4
        var s1 = 35.0d;
        var s2 = 5.0d;
        var angle = GeometryUtil.radiansToDegrees(atan(4.0d / 3.0d));
        var parallelogram1 = new Parallelogram(s1, s2, angle);
        assertThat(parallelogram1.getWidth()).isCloseTo(4.0d, offset(0.00001d));

        s1 = 35.0d;
        s2 = 5.0d;
        angle = 90.0d;
        var parallelogram2 = new Parallelogram(s1, s2, angle);
        assertThat(parallelogram2.getWidth()).isCloseTo(s2, offset(0.00001)); // because angle = 90 degrees

        s1 = 20.0d;
        s2 = 10.0d;
        angle = 0.0d; // so in fact it's 1-dimensional
        var parallelogram3 = new Parallelogram(s1, s2, angle);
        assertThat(parallelogram3.getWidth()).isEqualTo(0.0d); // because angle = 0 degrees (flat)

        s1 = 4.389d;
        s2 = 17.0d;
        angle = 30.0d;
        var parallelogram4 = new Parallelogram(s1, s2, angle);
        assertThat(parallelogram4.getWidth()).isCloseTo(s2 * 0.5d, offset(0.00001d));  // sin(30 gr) = 0.5

        s1 = 438.0d;
        s2 = 527.0d;
        angle = 14.0d;
        var expectedWidth = s2 * 0.241921896d;  // sin(14 gr) = 0.241921896
        var parallelogram5 = new Parallelogram(s1, s2, angle);
        assertThat(parallelogram5.getWidth()).isCloseTo(expectedWidth, offset(0.00001d));

        // verify lazy initialization
        // given
        var spy = Mockito.spy(parallelogram1);
        verify(spy, never()).getWidth();
        reset(spy);
        // when
        spy.getWidth();
        // then
        verify(spy, times(1)).getWidth();
        Mockito.verifyNoMoreInteractions(spy);
    }

    @Test
    void getCircumference() {
        var s1 = 35.0d;
        var s2 = 5.0d;
        var expectedCircumference = 80.0d; // 2 * s1 + 2 * s2
        var angle = 42.9d;
        var parallelogram1 = new Parallelogram(s1, s2, angle);
        assertThat(parallelogram1.getCircumference()).isCloseTo(expectedCircumference, offset(0.0000001d));

        s1 = 8.453e12d;
        s2 = 4.8621e13d;
        expectedCircumference = 2d * (s1 + s2);
        angle = 66.45d;
        var parallelogram2 = new Parallelogram(s1, s2, angle);
        assertThat(parallelogram2.getCircumference()).isCloseTo(expectedCircumference, offset(1.0d));
    }

    @Test
    void getArea() {
        var s1 = 8.5d;
        var s2 = 11.7d;
        var angleDegrees = 45.0d;
        var spy = Mockito.spy(new Parallelogram(s1, s2, angleDegrees));

        verify(spy, never()).getArea(); // attribute not initialized yet, because lazy
        reset(spy);
        assertThat(spy.getArea()).isCloseTo(calcArea(s1, s2, angleDegrees), offset(0.0000001d));
        verify(spy, times(1)).getArea(); // attribute initialized now, because called to calculate area

        s1 = 74.0d;
        s2 = 85.4d;
        angleDegrees = 14.37d;
        var parallelogram2 = new Parallelogram(s1, s2, angleDegrees);
        assertThat(parallelogram2.getArea()).isCloseTo(calcArea(s1, s2, angleDegrees), offset(0.0000001d));
    }

    @Test
    @Disabled("No copy method in Java for data classes!")
    void copy() {
        fail("No copy method in Java for data classes!");
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
    void testToString() {
        var s1 = 6.545645d;
        var s2 = 13.75321d;
        var angleDegrees = 45.0d;
        var parallelogram1 = new Parallelogram(s1, s2, angleDegrees);
        var str = parallelogram1.toString();
        assertThat(str).contains(
                parallelogram1.getClass().getSimpleName(),
                "s1", String.valueOf(s1), "s2", String.valueOf(s2), "angleDegrees", String.valueOf(angleDegrees)
        );
    }

    @Test
    void testEquals() {
        // given
        var parallelogram1 = new Parallelogram(10.0d, 15.0d, 25.0d);
        var parallelogram2 = new Parallelogram(10.0d, 15.0d, 25.0d);
        // check equality
        assertThat(parallelogram1).isEqualTo(parallelogram2);

        // given
        var parallelogramDifferent = new Parallelogram(10.0001d, 15.0d, 25.0d);
        // check inequality
        assertThat(parallelogramDifferent).isNotEqualTo(parallelogram1);
        // given
        parallelogramDifferent = new Parallelogram(10.0d, 8.0d, 25.0d);
        // check inequality
        assertThat(parallelogramDifferent).isNotEqualTo(parallelogram1);
        // given
        parallelogramDifferent = new Parallelogram(10.0d, 15.0d, 33.0d);
        // check inequality
        assertThat(parallelogramDifferent).isNotEqualTo(parallelogram1);
    }

    @Test
    void equalsCallShouldNotCallLazyAttributes() {
        // given
        var parallelogram1 = new Parallelogram(10.0d, 15.0d, 25.0d);
        var spy1 = Mockito.spy(parallelogram1);
        verify(spy1, never()).getWidth();
        verify(spy1, never()).getArea();

        var parallelogram2 = new Parallelogram(10.0d, 15.0d, 25.0d);
        var spy2 = Mockito.spy(parallelogram2);
        verify(spy2, never()).getWidth();
        verify(spy2, never()).getArea();
        // when
        assertThat(parallelogram1).isEqualTo(parallelogram2);
        // then
        verify(spy1, never()).getWidth();
        verify(spy1, never()).getArea();
        verify(spy2, never()).getWidth();
        verify(spy2, never()).getArea();
        spy2.getWidth();
        verify(spy2, times(1)).getWidth();
    }

}