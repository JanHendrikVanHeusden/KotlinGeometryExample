package nl.jhvh.java.conversion;

import org.junit.jupiter.api.Test;

import static nl.jhvh.java.conversion.ImperialMetricConversion.cubicFootToCubicInchFactor;
import static nl.jhvh.java.conversion.ImperialMetricConversion.footToInchFactor;
import static nl.jhvh.java.conversion.ImperialMetricConversion.m2ToSquareFeetFactor;
import static nl.jhvh.java.conversion.ImperialMetricConversion.m3ToCubicFeetFactor;
import static nl.jhvh.java.conversion.ImperialMetricConversion.meterToFeetFactor;
import static nl.jhvh.java.conversion.ImperialMetricConversion.squareFootToSquareInchFactor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

/** Unit tests for {@link ImperialMetricConversion} */
class ImperialMetricConversionTest {

    /** Unit test for {@link ImperialMetricConversion#meterToFeetFactor} */
    @Test
    void meterToFeetFactor() {
        assertThat(meterToFeetFactor).isCloseTo(3.2808399d, offset(0.00000001d));
    }

    /** Unit test for {@link ImperialMetricConversion#m2ToSquareFeetFactor} */
    @Test
    void m2ToSquareFeetFactor() {
        assertThat(m2ToSquareFeetFactor).isCloseTo(10.7639104d, offset(0.000001d));
    }

    /** Unit test for {@link ImperialMetricConversion#m3ToCubicFeetFactor} */
    @Test
    void m3ToCubicFeetFactor() {
        assertThat(m3ToCubicFeetFactor).isCloseTo(35.3146667d, offset(0.000001d));
    }

    /** Unit test for {@link ImperialMetricConversion#footToInchFactor} */
    @Test
    void footToInchFactor() {
        assertThat(footToInchFactor).isEqualTo(12);
    }

    /** Unit test for {@link ImperialMetricConversion#squareFootToSquareInchFactor} */
    @Test
    void squareFootToSquareInchFactor() {
        assertThat(squareFootToSquareInchFactor).isEqualTo(144); // 12 * 12
    }

    /** Unit test for {@link ImperialMetricConversion#cubicFootToCubicInchFactor} */
    @Test
    void cubicFootToCubicInchFactor() {
        assertThat(cubicFootToCubicInchFactor).isEqualTo(1728); // 12 * 12 * 12
    }

    /** Unit test for {@link ImperialMetricConversion#meterToFeet(double)} */
    @Test
    void meterToFeet() {
        assertThat(ImperialMetricConversion.meterToFeet(5.28d)).isCloseTo(17.3228346d, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToFeet(1.0d)).isCloseTo(meterToFeetFactor, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToFeet(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.meterToFeet(-2.0d)).isCloseTo(meterToFeetFactor * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToFeet(3.49498746312d)).isCloseTo(meterToFeetFactor * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToFeet(3.49498746312e-16d)).isCloseTo(meterToFeetFactor * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.meterToFeet(5.1486e24)).isCloseTo(meterToFeetFactor * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#m2ToSquareFeet(double)} */
    @Test
    void m2ToSquareFeet() {
        assertThat(ImperialMetricConversion.m2ToSquareFeet(28.65d)).isCloseTo(308.386033d, offset(0.0001d));
        assertThat(ImperialMetricConversion.m2ToSquareFeet(1.0d)).isCloseTo(m2ToSquareFeetFactor, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareFeet(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.m2ToSquareFeet(-2.0d)).isCloseTo(m2ToSquareFeetFactor * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareFeet(3.49498746312d)).isCloseTo(m2ToSquareFeetFactor * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareFeet(3.49498746312e-16d)).isCloseTo(m2ToSquareFeetFactor * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.m2ToSquareFeet(5.1486e24)).isCloseTo(m2ToSquareFeetFactor * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#m3ToCubicFeet(double)} */
    @Test
    void m3ToCubicFeet() {
        assertThat(ImperialMetricConversion.m3ToCubicFeet(245.2d)).isCloseTo(8659.15628d, offset(0.0001d));
        assertThat(ImperialMetricConversion.m3ToCubicFeet(1.0d)).isCloseTo(m3ToCubicFeetFactor, offset(0.000001d));
        assertThat(ImperialMetricConversion.m3ToCubicFeet(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.m3ToCubicFeet(-2.0d)).isCloseTo(m3ToCubicFeetFactor * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m3ToCubicFeet(3.49498746312d)).isCloseTo(m3ToCubicFeetFactor * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m3ToCubicFeet(3.49498746312e-16d)).isCloseTo(m3ToCubicFeetFactor * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.m3ToCubicFeet(5.1486e24)).isCloseTo(m3ToCubicFeetFactor * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#meterToInch(double)} */
    @Test
    void meterToInch() {
        assertThat(ImperialMetricConversion.meterToInch(3183.25d)).isCloseTo(125324.803d, offset(0.001d));
        assertThat(ImperialMetricConversion.meterToInch(1.0d)).isCloseTo(meterToFeetFactor * footToInchFactor, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToInch(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.meterToInch(-2.0d)).isCloseTo(meterToFeetFactor * footToInchFactor * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToInch(3.49498746312d)).isCloseTo(meterToFeetFactor * footToInchFactor * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToInch(3.49498746312e-16d)).isCloseTo(meterToFeetFactor * footToInchFactor * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.meterToInch(5.1486e24)).isCloseTo(meterToFeetFactor * footToInchFactor * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#m2ToSquareInch(double)} */
    @Test
    void m2ToSquareInch() {
        assertThat(ImperialMetricConversion.m2ToSquareInch(2.34d))
                .isCloseTo(3627.00725d, offset(0.0001d));
        assertThat(ImperialMetricConversion.m2ToSquareInch(1.0d))
                .isCloseTo(m2ToSquareFeetFactor * squareFootToSquareInchFactor, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareInch(-2.0d))
                .isCloseTo(m2ToSquareFeetFactor * squareFootToSquareInchFactor * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareInch(3.49498746312d))
                .isCloseTo(m2ToSquareFeetFactor * squareFootToSquareInchFactor * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareInch(3.49498746312e-16d))
                .isCloseTo(m2ToSquareFeetFactor * squareFootToSquareInchFactor * 3.49498746312e-16d, offset(1e-26d));
    }

    /** Unit test for {@link ImperialMetricConversion#m3ToCubicInch(double)} */
    @Test
    void m3ToCubicInch() {
        assertThat(ImperialMetricConversion.m3ToCubicInch(14.89d))
                .isCloseTo(908643.55d, offset(0.01d));
        assertThat(ImperialMetricConversion.m3ToCubicInch(1.0d))
                .isCloseTo(m3ToCubicFeetFactor * cubicFootToCubicInchFactor, offset(0.000001d));
        assertThat(ImperialMetricConversion.m3ToCubicInch(-2.0d))
                .isCloseTo(m3ToCubicFeetFactor * cubicFootToCubicInchFactor * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m3ToCubicInch(3.49498746312d))
                .isCloseTo(m3ToCubicFeetFactor * cubicFootToCubicInchFactor * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m3ToCubicInch(3.49498746312e-16d))
                .isCloseTo(m3ToCubicFeetFactor * cubicFootToCubicInchFactor * 3.49498746312e-16d, offset(1e-26d));
    }

    /** Unit test for {@link ImperialMetricConversion#feetToMeter(double)} */
    @Test
    void feetToMeter() {
        assertThat(ImperialMetricConversion.feetToMeter(3546.12d)).isCloseTo(1080.85738d, offset(0.0001d));
        assertThat(ImperialMetricConversion.feetToMeter(1.0d)).isCloseTo(1.0 / meterToFeetFactor, offset(0.000001d));
        assertThat(ImperialMetricConversion.feetToMeter(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.feetToMeter(-2.0d)).isCloseTo((1.0 / meterToFeetFactor) * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.feetToMeter(3.49498746312d)).isCloseTo((1.0 / meterToFeetFactor) * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.feetToMeter(3.49498746312e-16d)).isCloseTo((1.0 / meterToFeetFactor) * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.feetToMeter(5.1486e24)).isCloseTo((1.0 / meterToFeetFactor) * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#squareFeetToM2(double)} */
    @Test
    void squareFeetToM2() {
        assertThat(ImperialMetricConversion.squareFeetToM2(7464.64d)).isCloseTo(693.487749d, offset(0.00001d));
        assertThat(ImperialMetricConversion.squareFeetToM2(1.0d)).isCloseTo(1.0 / m2ToSquareFeetFactor, offset(0.000001d));
        assertThat(ImperialMetricConversion.squareFeetToM2(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.squareFeetToM2(-2.0d)).isCloseTo((1.0 / m2ToSquareFeetFactor) * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.squareFeetToM2(3.49498746312d)).isCloseTo((1.0 / m2ToSquareFeetFactor) * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.squareFeetToM2(3.49498746312e-16d)).isCloseTo((1.0 / m2ToSquareFeetFactor) * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.squareFeetToM2(5.1486e24)).isCloseTo((1.0 / m2ToSquareFeetFactor) * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#cubicFeetToM3(double)} */
    @Test
    void cubicFeetToM3() {
        assertThat(ImperialMetricConversion.cubicFeetToM3(642.1d)).isCloseTo(18.1822472d, offset(0.000001d));
        assertThat(ImperialMetricConversion.cubicFeetToM3(1.0d)).isCloseTo(1.0 / m3ToCubicFeetFactor, offset(0.000001d));
        assertThat(ImperialMetricConversion.cubicFeetToM3(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.cubicFeetToM3(-2.0d)).isCloseTo((1.0 / m3ToCubicFeetFactor) * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.cubicFeetToM3(3.49498746312d)).isCloseTo((1.0 / m3ToCubicFeetFactor) * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.cubicFeetToM3(3.49498746312e-16d)).isCloseTo((1.0 / m3ToCubicFeetFactor) * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.cubicFeetToM3(5.1486e24)).isCloseTo((1.0 / m3ToCubicFeetFactor) * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#inchToMeter(double)} */
    @Test
    void inchToMeter() {
        assertThat(ImperialMetricConversion.inchToMeter(54686358.63d)).isCloseTo(1389033.51d, offset(0.01d));
        assertThat(ImperialMetricConversion.inchToMeter(1.0d)).isCloseTo((1.0 / (meterToFeetFactor*footToInchFactor)), offset(0.000001d));
        assertThat(ImperialMetricConversion.inchToMeter(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.inchToMeter(-2.0d)).isCloseTo((1.0 / (meterToFeetFactor*footToInchFactor)) * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.inchToMeter(3.49498746312d)).isCloseTo((1.0 / (meterToFeetFactor*footToInchFactor)) * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.inchToMeter(3.49498746312e-16d)).isCloseTo((1.0 / (meterToFeetFactor*footToInchFactor)) * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.inchToMeter(5.1486e24)).isCloseTo((1.0 / (meterToFeetFactor*footToInchFactor)) * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#squareInchToM2(double)} */
    @Test
    void squareInchToM2() {
        assertThat(ImperialMetricConversion.squareInchToM2(3165674.68d)).isCloseTo(2042.36668d, offset(0.001d));
        assertThat(ImperialMetricConversion.squareInchToM2(1.0d)).isCloseTo((1.0 / (m2ToSquareFeetFactor*squareFootToSquareInchFactor)), offset(0.000001d));
        assertThat(ImperialMetricConversion.squareInchToM2(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.squareInchToM2(-2.0d)).isCloseTo((1.0 / (m2ToSquareFeetFactor*squareFootToSquareInchFactor)) * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.squareInchToM2(3.49498746312d)).isCloseTo((1.0 / (m2ToSquareFeetFactor*squareFootToSquareInchFactor)) * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.squareInchToM2(3.49498746312e-16d)).isCloseTo((1.0 / (m2ToSquareFeetFactor*squareFootToSquareInchFactor)) * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.squareInchToM2(5.1486e24)).isCloseTo((1.0 / (m2ToSquareFeetFactor*squareFootToSquareInchFactor)) * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#cubicInchToM3(double)} */
    @Test
    void cubicInchToM3() {
        assertThat(ImperialMetricConversion.cubicInchToM3(683456456d)).isCloseTo(11199.8447d, offset(0.001d));
        assertThat(ImperialMetricConversion.cubicInchToM3(1.0d)).isCloseTo((1.0 / (m3ToCubicFeetFactor*cubicFootToCubicInchFactor)), offset(0.000001d));
        assertThat(ImperialMetricConversion.cubicInchToM3(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.cubicInchToM3(-2.0d)).isCloseTo((1.0 / (m3ToCubicFeetFactor*cubicFootToCubicInchFactor)) * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.cubicInchToM3(3.49498746312d)).isCloseTo((1.0 / (m3ToCubicFeetFactor*cubicFootToCubicInchFactor)) * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.cubicInchToM3(3.49498746312e-16d)).isCloseTo((1.0 / (m3ToCubicFeetFactor*cubicFootToCubicInchFactor)) * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.cubicInchToM3(5.1486e24)).isCloseTo((1.0 / (m3ToCubicFeetFactor*cubicFootToCubicInchFactor)) * 5.1486e24, offset(1e16));
    }
}
