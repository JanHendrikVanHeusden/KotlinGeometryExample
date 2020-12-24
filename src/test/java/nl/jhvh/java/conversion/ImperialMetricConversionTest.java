package nl.jhvh.java.conversion;

import org.junit.jupiter.api.Test;

import static nl.jhvh.java.conversion.ImperialMetricConversion.FOOT_TO_INCH_FACTOR;
import static nl.jhvh.java.conversion.ImperialMetricConversion.M2_TO_SQUARE_FEET_FACTOR;
import static nl.jhvh.java.conversion.ImperialMetricConversion.METER_TO_FEET_FACTOR;
import static nl.jhvh.java.conversion.ImperialMetricConversion.SQUARE_FOOT_TO_SQUARE_INCH_FACTOR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

/**
 * Unit tests for {@link ImperialMetricConversion}
 *
 * This is for DEMO only to show difference between Java and Kotlin (using extension methods in Kotlin).
 * For any real use, make sure to use the stuff from the `javax.measure` package instead !!
 */
class ImperialMetricConversionTest {

    /** Unit test for {@link ImperialMetricConversion#METER_TO_FEET_FACTOR} */
    @Test
    void meterToFeetFactor() {
        assertThat(METER_TO_FEET_FACTOR).isCloseTo(3.2808399d, offset(0.00000001d));
    }

    /** Unit test for {@link ImperialMetricConversion#M2_TO_SQUARE_FEET_FACTOR} */
    @Test
    void m2ToSquareFeetFactor() {
        assertThat(M2_TO_SQUARE_FEET_FACTOR).isCloseTo(10.7639104d, offset(0.000001d));
    }

    /** Unit test for {@link ImperialMetricConversion#FOOT_TO_INCH_FACTOR} */
    @Test
    void footToInchFactor() {
        assertThat(FOOT_TO_INCH_FACTOR).isEqualTo(12);
    }

    /** Unit test for {@link ImperialMetricConversion#SQUARE_FOOT_TO_SQUARE_INCH_FACTOR} */
    @Test
    void squareFootToSquareInchFactor() {
        assertThat(SQUARE_FOOT_TO_SQUARE_INCH_FACTOR).isEqualTo(144); // 12 * 12
    }

    /** Unit test for {@link ImperialMetricConversion#meterToFeet(double)} */
    @Test
    void meterToFeet() {
        assertThat(ImperialMetricConversion.meterToFeet(5.28d)).isCloseTo(17.3228346d, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToFeet(1.0d)).isCloseTo(METER_TO_FEET_FACTOR, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToFeet(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.meterToFeet(-2.0d)).isCloseTo(METER_TO_FEET_FACTOR * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToFeet(3.49498746312d)).isCloseTo(METER_TO_FEET_FACTOR * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToFeet(3.49498746312e-16d)).isCloseTo(METER_TO_FEET_FACTOR * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.meterToFeet(5.1486e24)).isCloseTo(METER_TO_FEET_FACTOR * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#m2ToSquareFeet(double)} */
    @Test
    void m2ToSquareFeet() {
        assertThat(ImperialMetricConversion.m2ToSquareFeet(28.65d)).isCloseTo(308.386033d, offset(0.0001d));
        assertThat(ImperialMetricConversion.m2ToSquareFeet(1.0d)).isCloseTo(M2_TO_SQUARE_FEET_FACTOR, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareFeet(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.m2ToSquareFeet(-2.0d)).isCloseTo(M2_TO_SQUARE_FEET_FACTOR * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareFeet(3.49498746312d)).isCloseTo(M2_TO_SQUARE_FEET_FACTOR * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareFeet(3.49498746312e-16d)).isCloseTo(M2_TO_SQUARE_FEET_FACTOR * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.m2ToSquareFeet(5.1486e24)).isCloseTo(M2_TO_SQUARE_FEET_FACTOR * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#meterToInch(double)} */
    @Test
    void meterToInch() {
        assertThat(ImperialMetricConversion.meterToInch(3183.25d)).isCloseTo(125324.803d, offset(0.001d));
        assertThat(ImperialMetricConversion.meterToInch(1.0d)).isCloseTo(METER_TO_FEET_FACTOR * FOOT_TO_INCH_FACTOR, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToInch(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.meterToInch(-2.0d)).isCloseTo(METER_TO_FEET_FACTOR * FOOT_TO_INCH_FACTOR * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToInch(3.49498746312d)).isCloseTo(METER_TO_FEET_FACTOR * FOOT_TO_INCH_FACTOR * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.meterToInch(3.49498746312e-16d)).isCloseTo(METER_TO_FEET_FACTOR * FOOT_TO_INCH_FACTOR * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.meterToInch(5.1486e24)).isCloseTo(METER_TO_FEET_FACTOR * FOOT_TO_INCH_FACTOR * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#m2ToSquareInch(double)} */
    @Test
    void m2ToSquareInch() {
        assertThat(ImperialMetricConversion.m2ToSquareInch(2.34d))
                .isCloseTo(3627.00725d, offset(0.0001d));
        assertThat(ImperialMetricConversion.m2ToSquareInch(1.0d))
                .isCloseTo(M2_TO_SQUARE_FEET_FACTOR * SQUARE_FOOT_TO_SQUARE_INCH_FACTOR, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareInch(-2.0d))
                .isCloseTo(M2_TO_SQUARE_FEET_FACTOR * SQUARE_FOOT_TO_SQUARE_INCH_FACTOR * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareInch(3.49498746312d))
                .isCloseTo(M2_TO_SQUARE_FEET_FACTOR * SQUARE_FOOT_TO_SQUARE_INCH_FACTOR * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.m2ToSquareInch(3.49498746312e-16d))
                .isCloseTo(M2_TO_SQUARE_FEET_FACTOR * SQUARE_FOOT_TO_SQUARE_INCH_FACTOR * 3.49498746312e-16d, offset(1e-26d));
    }

    /** Unit test for {@link ImperialMetricConversion#feetToMeter(double)} */
    @Test
    void feetToMeter() {
        assertThat(ImperialMetricConversion.feetToMeter(3546.12d)).isCloseTo(1080.85738d, offset(0.0001d));
        assertThat(ImperialMetricConversion.feetToMeter(1.0d)).isCloseTo(1.0 / METER_TO_FEET_FACTOR, offset(0.000001d));
        assertThat(ImperialMetricConversion.feetToMeter(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.feetToMeter(-2.0d)).isCloseTo((1.0 / METER_TO_FEET_FACTOR) * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.feetToMeter(3.49498746312d)).isCloseTo((1.0 / METER_TO_FEET_FACTOR) * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.feetToMeter(3.49498746312e-16d)).isCloseTo((1.0 / METER_TO_FEET_FACTOR) * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.feetToMeter(5.1486e24)).isCloseTo((1.0 / METER_TO_FEET_FACTOR) * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#squareFeetToM2(double)} */
    @Test
    void squareFeetToM2() {
        assertThat(ImperialMetricConversion.squareFeetToM2(7464.64d)).isCloseTo(693.487749d, offset(0.00001d));
        assertThat(ImperialMetricConversion.squareFeetToM2(1.0d)).isCloseTo(1.0 / M2_TO_SQUARE_FEET_FACTOR, offset(0.000001d));
        assertThat(ImperialMetricConversion.squareFeetToM2(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.squareFeetToM2(-2.0d)).isCloseTo((1.0 / M2_TO_SQUARE_FEET_FACTOR) * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.squareFeetToM2(3.49498746312d)).isCloseTo((1.0 / M2_TO_SQUARE_FEET_FACTOR) * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.squareFeetToM2(3.49498746312e-16d)).isCloseTo((1.0 / M2_TO_SQUARE_FEET_FACTOR) * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.squareFeetToM2(5.1486e24)).isCloseTo((1.0 / M2_TO_SQUARE_FEET_FACTOR) * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#inchToMeter(double)} */
    @Test
    void inchToMeter() {
        assertThat(ImperialMetricConversion.inchToMeter(54686358.63d)).isCloseTo(1389033.51d, offset(0.01d));
        assertThat(ImperialMetricConversion.inchToMeter(1.0d)).isCloseTo((1.0 / (METER_TO_FEET_FACTOR * FOOT_TO_INCH_FACTOR)), offset(0.000001d));
        assertThat(ImperialMetricConversion.inchToMeter(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.inchToMeter(-2.0d)).isCloseTo((1.0 / (METER_TO_FEET_FACTOR * FOOT_TO_INCH_FACTOR)) * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.inchToMeter(3.49498746312d)).isCloseTo((1.0 / (METER_TO_FEET_FACTOR * FOOT_TO_INCH_FACTOR)) * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.inchToMeter(3.49498746312e-16d)).isCloseTo((1.0 / (METER_TO_FEET_FACTOR * FOOT_TO_INCH_FACTOR)) * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.inchToMeter(5.1486e24)).isCloseTo((1.0 / (METER_TO_FEET_FACTOR * FOOT_TO_INCH_FACTOR)) * 5.1486e24, offset(1e16));
    }

    /** Unit test for {@link ImperialMetricConversion#squareInchToM2(double)} */
    @Test
    void squareInchToM2() {
        assertThat(ImperialMetricConversion.squareInchToM2(3165674.68d)).isCloseTo(2042.36668d, offset(0.001d));
        assertThat(ImperialMetricConversion.squareInchToM2(1.0d)).isCloseTo((1.0 / (M2_TO_SQUARE_FEET_FACTOR * SQUARE_FOOT_TO_SQUARE_INCH_FACTOR)), offset(0.000001d));
        assertThat(ImperialMetricConversion.squareInchToM2(0.0d)).isEqualTo(0.0d);
        assertThat(ImperialMetricConversion.squareInchToM2(-2.0d)).isCloseTo((1.0 / (M2_TO_SQUARE_FEET_FACTOR * SQUARE_FOOT_TO_SQUARE_INCH_FACTOR)) * -2.0d, offset(0.000001d));
        assertThat(ImperialMetricConversion.squareInchToM2(3.49498746312d)).isCloseTo((1.0 / (M2_TO_SQUARE_FEET_FACTOR * SQUARE_FOOT_TO_SQUARE_INCH_FACTOR)) * 3.49498746312d, offset(0.000001d));
        assertThat(ImperialMetricConversion.squareInchToM2(3.49498746312e-16d)).isCloseTo((1.0 / (M2_TO_SQUARE_FEET_FACTOR * SQUARE_FOOT_TO_SQUARE_INCH_FACTOR)) * 3.49498746312e-16d, offset(1e-26d));
        assertThat(ImperialMetricConversion.squareInchToM2(5.1486e24)).isCloseTo((1.0 / (M2_TO_SQUARE_FEET_FACTOR * SQUARE_FOOT_TO_SQUARE_INCH_FACTOR)) * 5.1486e24, offset(1e16));
    }

}
