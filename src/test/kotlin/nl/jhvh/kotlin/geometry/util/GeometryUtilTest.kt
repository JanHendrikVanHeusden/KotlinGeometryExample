package nl.jhvh.kotlin.geometry.util

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Test

/**
 * This is for DEMO only to show difference between Java and Kotlin (using extension methods in Kotlin).
 * For any real use, make sure to use the stuff from the `javax.measure` package instead !!
 */
internal class GeometryUtilTest {
    @Test
    fun rectangleDegrees() {
        assertThat(rectangleDegrees).isEqualTo(90.0)
    }

    @Test
    fun radiansToDegreesFactor() {
        assertThat(radiansToDegreesFactor).isCloseTo(57.2957795, offset(0.0000001))
    }

    @Test
    fun degreesToRadiansFactor() {
        assertThat(degreesToRadiansFactor).isCloseTo(0.0174532925, offset(0.0000000001))
    }

    @Test
    fun `test radiansToDegrees`() {
        assertThat(Math.PI.radiansToDegrees()).isCloseTo(180.0, offset(0.0000001))
        assertThat((-Math.PI).radiansToDegrees()).isCloseTo(-180.0, offset(0.0000001))
        assertThat((Math.PI / 4.0).radiansToDegrees()).isCloseTo(45.0, offset(0.0000001))
        assertThat(0.7456.radiansToDegrees()).isCloseTo(42.7197332, offset(0.0000001))
    }

    @Test
    fun `test degreesToRadians`() {
        assertThat(180.0.degreesToRadians()).isCloseTo(Math.PI, offset(0.0000001))
        assertThat((-180.0).degreesToRadians()).isCloseTo(-Math.PI, offset(0.0000001))
        assertThat(45.0.degreesToRadians()).isCloseTo(Math.PI / 4.0, offset(0.0000001))
        assertThat(42.7197332.degreesToRadians()).isCloseTo(0.7456, offset(0.0000001))
    }
}