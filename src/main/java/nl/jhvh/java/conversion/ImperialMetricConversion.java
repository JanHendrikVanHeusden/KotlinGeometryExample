package nl.jhvh.java.conversion;

import org.apache.commons.lang3.NotImplementedException;

/**
 * This is for DEMO only to show difference between Java and Kotlin (using extension methods in Kotlin).
 * For any real use, make sure to use the stuff from the `javax.measure` package instead !!
 */
public class ImperialMetricConversion {

    private ImperialMetricConversion() {
        throw new NotImplementedException(this.getClass().getName() + " is a utility class; do not instantiate!");
    }

    public static final double METER_TO_FEET_FACTOR = 3.2808399;
    public static final double M2_TO_SQUARE_FEET_FACTOR = METER_TO_FEET_FACTOR * METER_TO_FEET_FACTOR; // 10.7639104

    public static final int FOOT_TO_INCH_FACTOR = 12;
    public static final int SQUARE_FOOT_TO_SQUARE_INCH_FACTOR = FOOT_TO_INCH_FACTOR * FOOT_TO_INCH_FACTOR;

    public static double meterToFeet(double meter) {
        return meter * METER_TO_FEET_FACTOR;
    }

    public static double m2ToSquareFeet(double squareMeter) {
        return squareMeter * M2_TO_SQUARE_FEET_FACTOR;
    }

    public static double meterToInch(double meter) {
        return meterToFeet(meter) * FOOT_TO_INCH_FACTOR;
    }

    public static double m2ToSquareInch(double squareMeter) {
        return m2ToSquareFeet(squareMeter) * SQUARE_FOOT_TO_SQUARE_INCH_FACTOR;
    }

    public static double feetToMeter(double feet) {
        return feet / METER_TO_FEET_FACTOR;
    }

    public static double squareFeetToM2(double squareFeet) {
        return squareFeet / M2_TO_SQUARE_FEET_FACTOR;
    }

    public static double inchToMeter(double inches) {
        return feetToMeter(inches / FOOT_TO_INCH_FACTOR);
    }

    public static double squareInchToM2(double squareInches) {
        return squareFeetToM2(squareInches / SQUARE_FOOT_TO_SQUARE_INCH_FACTOR);
    }

}
