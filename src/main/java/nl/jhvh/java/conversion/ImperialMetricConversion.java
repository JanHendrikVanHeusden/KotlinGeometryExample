package nl.jhvh.java.conversion;

import org.apache.commons.lang3.NotImplementedException;

public class ImperialMetricConversion {

    private ImperialMetricConversion() {
        throw new NotImplementedException(this.getClass().getName() + " is a utility class; do not instantiate!");
    }

    public static double meterToFeetFactor = 3.2808399;
    public static double m2ToSquareFeetFactor = meterToFeetFactor * meterToFeetFactor; // 10.7639104
    public static double m3ToCubicFeetFactor = meterToFeetFactor * meterToFeetFactor * meterToFeetFactor; // 35.3146667

    public static int footToInchFactor = 12;
    public static int squareFootToSquareInchFactor = footToInchFactor * footToInchFactor;
    public static int cubicFootToCubicInchFactor = footToInchFactor * footToInchFactor * footToInchFactor;

    public static double meterToFeet(double meter) {
        return meter * meterToFeetFactor;
    }

    public static double m2ToSquareFeet(double squareMeter) {
        return squareMeter * m2ToSquareFeetFactor;
    }

    public static double m3ToCubicFeet(double cubicMeter) {
        return cubicMeter * m3ToCubicFeetFactor;
    }

    public static double meterToInch(double meter) {
        return meterToFeet(meter) * footToInchFactor;
    }

    public static double m2ToSquareInch(double squareMeter) {
        return m2ToSquareFeet(squareMeter) * squareFootToSquareInchFactor;
    }

    public static double m3ToCubicInch(double cubicMeter) {
        return m3ToCubicFeet(cubicMeter) * cubicFootToCubicInchFactor;
    }

    public static double feetToMeter(double feet) {
        return feet / meterToFeetFactor;
    }

    public static double squareFeetToM2(double squareFeet) {
        return squareFeet / m2ToSquareFeetFactor;
    }

    public static double cubicFeetToM3(double cubicFeet) {
        return cubicFeet / m3ToCubicFeetFactor;
    }

    public static double inchToMeter(double inches) {
        return feetToMeter(inches / footToInchFactor);
    }

    public static double squareInchToM2(double squareInches) {
        return squareFeetToM2(squareInches / squareFootToSquareInchFactor);
    }

    public static double cubicInchToM3(double cubicInches) {
        return cubicFeetToM3(cubicInches / cubicFootToCubicInchFactor);
    }

}
