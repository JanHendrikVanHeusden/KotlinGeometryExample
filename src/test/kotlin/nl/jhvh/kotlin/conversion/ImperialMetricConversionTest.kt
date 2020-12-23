package nl.jhvh.kotlin.conversion

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Test


/** Unit tests for conversion imperial to metric and vice versa  */
internal class ImperialMetricConversionTest {

    /** Unit test for [meterToFeetFactor]  */
    @Test
    fun `test meterToFeetFactor`() {
        assertThat(meterToFeetFactor).isCloseTo(3.2808399, offset(0.00000001))
    }

    /** Unit test for [m2ToSquareFeetFactor]  */
    @Test
    fun `test m2ToSquareFeetFactor`() {
        assertThat(m2ToSquareFeetFactor).isCloseTo(10.7639104, offset(0.000001))
    }

    /** Unit test for [m3ToCubicFeetFactor]  */
    @Test
    fun `test m3ToCubicFeetFactor`() {
        assertThat(m3ToCubicFeetFactor).isCloseTo(35.3146667, offset(0.000001))
    }

    /** Unit test for [footToInchFactor]  */
    @Test
    fun `test footToInchFactor`() {
        assertThat(footToInchFactor).isEqualTo(12)
    }

    /** Unit test for [squareFootToSquareInchFactor]  */
    @Test
    fun `test squareFootToSquareInchFactor`() {
        assertThat(squareFootToSquareInchFactor).isEqualTo(144) // 12 * 12
    }

    /** Unit test for [cubicFootToCubicInchFactor]  */
    @Test
    fun `test cubicFootToCubicInchFactor`() {
        assertThat(cubicFootToCubicInchFactor).isEqualTo(1728) // 12 * 12 * 12
    }

    /** Unit test for [meterToFeet]  */
    @Test
    fun `test meterToFeet`() {
        assertThat(5.28.meterToFeet()).isCloseTo(17.3228346, offset(0.000001))
        assertThat(1.0.meterToFeet()).isCloseTo(meterToFeetFactor, offset(0.000001))
        assertThat(0.0.meterToFeet()).isEqualTo(0.0)
        assertThat((-2.0).meterToFeet()).isCloseTo(meterToFeetFactor * -2.0, offset(0.000001))
        assertThat(3.49498746312.meterToFeet()).isCloseTo(meterToFeetFactor * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.meterToFeet()).isCloseTo(meterToFeetFactor * 3.49498746312e-16, offset(1e-26))
        assertThat(5.1486e24.meterToFeet()).isCloseTo(meterToFeetFactor * 5.1486e24, offset(1e16))
    }

    /** Unit test for [m2ToSquareFeet]  */
    @Test
    fun `test m2ToSquareFeet`() {
        assertThat(28.65.m2ToSquareFeet()).isCloseTo(308.386033, offset(0.0001))
        assertThat(1.0.m2ToSquareFeet()).isCloseTo(m2ToSquareFeetFactor, offset(0.000001))
        assertThat(0.0.m2ToSquareFeet()).isEqualTo(0.0)
        assertThat((-2.0).m2ToSquareFeet()).isCloseTo(m2ToSquareFeetFactor * -2.0, offset(0.000001))
        assertThat(3.49498746312.m2ToSquareFeet()).isCloseTo(m2ToSquareFeetFactor * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.m2ToSquareFeet()).isCloseTo(m2ToSquareFeetFactor * 3.49498746312e-16, offset(1e-26))
        assertThat(5.1486e24.m2ToSquareFeet()).isCloseTo(m2ToSquareFeetFactor * 5.1486e24, offset(1e16))
    }

    /** Unit test for [m3ToCubicFeet]  */
    @Test
    fun `test m3ToCubicFeet`() {
        assertThat(245.2.m3ToCubicFeet()).isCloseTo(8659.15628, offset(0.0001))
        assertThat(1.0.m3ToCubicFeet()).isCloseTo(m3ToCubicFeetFactor, offset(0.000001))
        assertThat(0.0.m3ToCubicFeet()).isEqualTo(0.0)
        assertThat((-2.0).m3ToCubicFeet()).isCloseTo(m3ToCubicFeetFactor * -2.0, offset(0.000001))
        assertThat(3.49498746312.m3ToCubicFeet()).isCloseTo(m3ToCubicFeetFactor * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.m3ToCubicFeet()).isCloseTo(m3ToCubicFeetFactor * 3.49498746312e-16, offset(1e-26))
        assertThat(5.1486e24.m3ToCubicFeet()).isCloseTo(m3ToCubicFeetFactor * 5.1486e24, offset(1e16))
    }

    /** Unit test for [meterToInch]  */
    @Test
    fun `test meterToInch`() {
        assertThat(3183.25.meterToInch()).isCloseTo(125324.803, offset(0.001))
        assertThat(1.0.meterToInch()).isCloseTo(meterToFeetFactor * footToInchFactor, offset(0.000001))
        assertThat(0.0.meterToInch()).isEqualTo(0.0)
        assertThat((-2.0).meterToInch()).isCloseTo(meterToFeetFactor * footToInchFactor * -2.0, offset(0.000001))
        assertThat(3.49498746312.meterToInch()).isCloseTo(meterToFeetFactor * footToInchFactor * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.meterToInch()).isCloseTo(meterToFeetFactor * footToInchFactor * 3.49498746312e-16, offset(1e-26))
        assertThat(5.1486e24.meterToInch()).isCloseTo(meterToFeetFactor * footToInchFactor * 5.1486e24, offset(1e16))
    }

    /** Unit test for [m2ToSquareInch]  */
    @Test
    fun `test m2ToSquareInch`() {
        assertThat(2.34.m2ToSquareInch())
                .isCloseTo(3627.00725, offset(0.0001))
        assertThat(1.0.m2ToSquareInch())
                .isCloseTo(m2ToSquareFeetFactor * squareFootToSquareInchFactor, offset(0.000001))
        assertThat((-2.0).m2ToSquareInch())
                .isCloseTo(m2ToSquareFeetFactor * squareFootToSquareInchFactor * -2.0, offset(0.000001))
        assertThat(3.49498746312.m2ToSquareInch())
                .isCloseTo(m2ToSquareFeetFactor * squareFootToSquareInchFactor * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.m2ToSquareInch())
                .isCloseTo(m2ToSquareFeetFactor * squareFootToSquareInchFactor * 3.49498746312e-16, offset(1e-26))
    }

    /** Unit test for [m3ToCubicInch]  */
    @Test
    fun `test m3ToCubicInch`() {
        assertThat(14.89.m3ToCubicInch())
                .isCloseTo(908643.55, offset(0.01))
        assertThat(1.0.m3ToCubicInch())
                .isCloseTo(m3ToCubicFeetFactor * cubicFootToCubicInchFactor, offset(0.000001))
        assertThat((-2.0).m3ToCubicInch())
                .isCloseTo(m3ToCubicFeetFactor * cubicFootToCubicInchFactor * -2.0, offset(0.000001))
        assertThat(3.49498746312.m3ToCubicInch())
                .isCloseTo(m3ToCubicFeetFactor * cubicFootToCubicInchFactor * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.m3ToCubicInch())
                .isCloseTo(m3ToCubicFeetFactor * cubicFootToCubicInchFactor * 3.49498746312e-16, offset(1e-26))
    }

    /** Unit test for [feetToMeter]  */
    @Test
    fun `test feetToMeter`() {
        assertThat(3546.12.feetToMeter()).isCloseTo(1080.85738, offset(0.0001))
        assertThat(1.0.feetToMeter()).isCloseTo(1.0 / meterToFeetFactor, offset(0.000001))
        assertThat(0.0.feetToMeter()).isEqualTo(0.0)
        assertThat((-2.0).feetToMeter()).isCloseTo(1.0 / meterToFeetFactor * -2.0, offset(0.000001))
        assertThat(3.49498746312.feetToMeter()).isCloseTo(1.0 / meterToFeetFactor * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.feetToMeter()).isCloseTo(1.0 / meterToFeetFactor * 3.49498746312e-16, offset(1e-26))
        assertThat(5.1486e24.feetToMeter()).isCloseTo(1.0 / meterToFeetFactor * 5.1486e24, offset(1e16))
    }

    /** Unit test for [squareFeetToM2]  */
    @Test
    fun `test squareFeetToM2`() {
        assertThat(7464.64.squareFeetToM2()).isCloseTo(693.487749, offset(0.00001))
        assertThat(1.0.squareFeetToM2()).isCloseTo(1.0 / m2ToSquareFeetFactor, offset(0.000001))
        assertThat(0.0.squareFeetToM2()).isEqualTo(0.0)
        assertThat((-2.0).squareFeetToM2()).isCloseTo(1.0 / m2ToSquareFeetFactor * -2.0, offset(0.000001))
        assertThat(3.49498746312.squareFeetToM2()).isCloseTo(1.0 / m2ToSquareFeetFactor * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.squareFeetToM2()).isCloseTo(1.0 / m2ToSquareFeetFactor * 3.49498746312e-16, offset(1e-26))
        assertThat(5.1486e24.squareFeetToM2()).isCloseTo(1.0 / m2ToSquareFeetFactor * 5.1486e24, offset(1e16))
    }

    /** Unit test for [cubicFeetToM3]  */
    @Test
    fun `test cubicFeetToM3`() {
        assertThat(642.1.cubicFeetToM3()).isCloseTo(18.1822472, offset(0.000001))
        assertThat(1.0.cubicFeetToM3()).isCloseTo(1.0 / m3ToCubicFeetFactor, offset(0.000001))
        assertThat(0.0.cubicFeetToM3()).isEqualTo(0.0)
        assertThat((-2.0).cubicFeetToM3()).isCloseTo(1.0 / m3ToCubicFeetFactor * -2.0, offset(0.000001))
        assertThat(3.49498746312.cubicFeetToM3()).isCloseTo(1.0 / m3ToCubicFeetFactor * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.cubicFeetToM3()).isCloseTo(1.0 / m3ToCubicFeetFactor * 3.49498746312e-16, offset(1e-26))
        assertThat(5.1486e24.cubicFeetToM3()).isCloseTo(1.0 / m3ToCubicFeetFactor * 5.1486e24, offset(1e16))
    }

    /** Unit test for [inchToMeter]  */
    @Test
    fun `test inchToMeter`() {
        assertThat(54686358.63.inchToMeter()).isCloseTo(1389033.51, offset(0.01))
        assertThat(1.0.inchToMeter()).isCloseTo(1.0 / (meterToFeetFactor * footToInchFactor), offset(0.000001))
        assertThat(0.0.inchToMeter()).isEqualTo(0.0)
        assertThat((-2.0).inchToMeter()).isCloseTo(1.0 / (meterToFeetFactor * footToInchFactor) * -2.0, offset(0.000001))
        assertThat(3.49498746312.inchToMeter()).isCloseTo(1.0 / (meterToFeetFactor * footToInchFactor) * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.inchToMeter()).isCloseTo(1.0 / (meterToFeetFactor * footToInchFactor) * 3.49498746312e-16, offset(1e-26))
        assertThat(5.1486e24.inchToMeter()).isCloseTo(1.0 / (meterToFeetFactor * footToInchFactor) * 5.1486e24, offset(1e16))
    }

    /** Unit test for [squareInchToM2]  */
    @Test
    fun `test squareInchToM2`() {
        assertThat(3165674.68.squareInchToM2()).isCloseTo(2042.36668, offset(0.001))
        assertThat(1.0.squareInchToM2()).isCloseTo(1.0 / (m2ToSquareFeetFactor * squareFootToSquareInchFactor), offset(0.000001))
        assertThat(0.0.squareInchToM2()).isEqualTo(0.0)
        assertThat((-2.0).squareInchToM2()).isCloseTo(1.0 / (m2ToSquareFeetFactor * squareFootToSquareInchFactor) * -2.0, offset(0.000001))
        assertThat(3.49498746312.squareInchToM2()).isCloseTo(1.0 / (m2ToSquareFeetFactor * squareFootToSquareInchFactor) * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.squareInchToM2()).isCloseTo(1.0 / (m2ToSquareFeetFactor * squareFootToSquareInchFactor) * 3.49498746312e-16, offset(1e-26))
        assertThat(5.1486e24.squareInchToM2()).isCloseTo(1.0 / (m2ToSquareFeetFactor * squareFootToSquareInchFactor) * 5.1486e24, offset(1e16))
    }

    /** Unit test for [cubicInchToM3]  */
    @Test
    fun `test cubicInchToM3`() {
        assertThat(683456456.0.cubicInchToM3()).isCloseTo(11199.8447, offset(0.001))
        assertThat(1.0.cubicInchToM3()).isCloseTo(1.0 / (m3ToCubicFeetFactor * cubicFootToCubicInchFactor), offset(0.000001))
        assertThat(0.0.cubicInchToM3()).isEqualTo(0.0)
        assertThat((-2.0).cubicInchToM3()).isCloseTo(1.0 / (m3ToCubicFeetFactor * cubicFootToCubicInchFactor) * -2.0, offset(0.000001))
        assertThat(3.49498746312.cubicInchToM3()).isCloseTo(1.0 / (m3ToCubicFeetFactor * cubicFootToCubicInchFactor) * 3.49498746312, offset(0.000001))
        assertThat(3.49498746312e-16.cubicInchToM3()).isCloseTo(1.0 / (m3ToCubicFeetFactor * cubicFootToCubicInchFactor) * 3.49498746312e-16, offset(1e-26))
        assertThat(5.1486e24.cubicInchToM3()).isCloseTo(1.0 / (m3ToCubicFeetFactor * cubicFootToCubicInchFactor) * 5.1486e24, offset(1e16))
    }
}
