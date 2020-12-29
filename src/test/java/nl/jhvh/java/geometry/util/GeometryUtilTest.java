package nl.jhvh.java.geometry.util;

import org.junit.jupiter.api.Test;

import static nl.jhvh.java.geometry.util.GeometryUtil.DEGREES_TO_RADIANS_FACTOR;
import static nl.jhvh.java.geometry.util.GeometryUtil.RADIANS_TO_DEGREES_FACTOR;
import static nl.jhvh.java.geometry.util.GeometryUtil.RECTANGLE_DEGREES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

/**
 * This is for DEMO only to show difference between Java and Kotlin (using extension methods in Kotlin).
 * For any real use, make sure to use the stuff from the `javax.measure` package instead !!
 */
class GeometryUtilTest {

    @Test
    void rectangleDegrees() {
        assertThat(RECTANGLE_DEGREES).isEqualTo(90);
    }

    @Test
    void radiansToDegreesFactor() {
        assertThat(RADIANS_TO_DEGREES_FACTOR).isCloseTo(57.2957795d, offset(0.0000001d));
    }

    @Test
    void degreesToRadiansFactor() {
        assertThat(DEGREES_TO_RADIANS_FACTOR).isCloseTo(0.0174532925d, offset(0.0000000001d));
    }

    @Test
    void radiansToDegrees() {
        assertThat(GeometryUtil.radiansToDegrees(Math.PI)).isCloseTo(180d, offset(0.0000001d));
        assertThat(GeometryUtil.radiansToDegrees(-Math.PI)).isCloseTo(-180d, offset(0.0000001d));
        assertThat(GeometryUtil.radiansToDegrees(Math.PI/4.0d)).isCloseTo(45d, offset(0.0000001d));
        assertThat(GeometryUtil.radiansToDegrees(0.7456d)).isCloseTo(42.7197332d, offset(0.0000001d));
    }

    @Test
    void degreesToRadians() {
        assertThat(GeometryUtil.degreesToRadians(180d)).isCloseTo(Math.PI, offset(0.0000001d));
        assertThat(GeometryUtil.degreesToRadians(-180d)).isCloseTo(-Math.PI, offset(0.0000001d));
        assertThat(GeometryUtil.degreesToRadians(45d)).isCloseTo(Math.PI/4.0d, offset(0.0000001d));
        assertThat(GeometryUtil.degreesToRadians(42.7197332d)).isCloseTo(0.7456d, offset(0.0000001d));
    }
}