package nl.jhvh.java.geometry.model.twodimensional;

import nl.jhvh.java.geometry.model.GeometryType;
import nl.jhvh.java.util.Log;

import java.util.Objects;

import static java.lang.Math.sin;
import static nl.jhvh.java.conversion.ImperialMetricConversion.m2ToSquareFeet;
import static nl.jhvh.java.conversion.ImperialMetricConversion.meterToFeet;
import static nl.jhvh.java.geometry.model.GeometryType.PARALLELOGRAM;
import static nl.jhvh.java.geometry.util.GeometryUtil.DEGREES_TO_RADIANS_FACTOR;
import static nl.jhvh.java.geometry.util.GeometryUtil.radiansToDegrees;

public class Parallelogram implements TwoDimensional {

    private static final double MIN_ANGLE_DEGREES = 0.0;
    private static final double MAX_ANGLE_DEGREES = 90.0;

    private final double s1;
    private final double s2;
    private final double angleRadians;
    private final double angleDegrees;
    private final double length;

    private Double width = null;

    public Parallelogram(double s1, double s2, double angleDegrees) {
        this.s1 = s1;
        this.s2 = s2;
        this.length = s1;
        this.angleDegrees = angleDegrees;
        this.angleRadians = angleDegrees * DEGREES_TO_RADIANS_FACTOR;
        validateInput();
    }

    @Override
    public GeometryType getGeometryType() {
        return PARALLELOGRAM;
    }

    @Override
    public double getCircumference() {
        return (s1 + s2) * 2;
    }

    @Override
    public double getArea() {
        // use getter to allow for lazy initialization!
        return s1 * getWidth();
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        // Pretending that it's a very costly operation, so lazy initialization

        // Theoretically it would require synchronization to avoid multiple initialization; but the outcome will
        // be the same in either case. So not synchronizing it won't cause trouble, except the performance penalty
        // of being initialized more than once.
        //
        // If that penalty of initializing more than once would be considerably higher than the performance penalty
        // of synchronizing the getWidth() method, one could synchronize the method; or double checked locking idiom
        // could be used, if it's worth the additional complexity and discussion.
        //
        // NB: that DOES work since JDK5, see https://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html,
        //     section "Fixing Double-Checked Locking using Volatile"
        if (width == null) {
            Log.logger(this.getClass()).debug("Lazy initialization of " + this.getClass().getSimpleName() + ".width");
            width = s2 * sin(angleRadians);
        }
        return width;
    }

    public double getS1() {
        return s1;
    }

    public double getS2() {
        return s2;
    }

    public double getAngleRadians() {
        return angleRadians;
    }

    public double getAngleDegrees() {
        return angleDegrees;
    }

    private void validateInput() {
        validateSideLengths();
        validateAngle();
    }

    private void validateSideLengths() {
        if (this.s1 < 0.0 || s2 < 0.0) {
            throw new IllegalArgumentException("Lengths of both sides must be positive, but sides s1 , s2 are " + s1 + " , " + s2);
        }
    }

    private void validateAngle() {
        if (angleDegrees < MIN_ANGLE_DEGREES || angleDegrees > MAX_ANGLE_DEGREES) {
            throw new IllegalArgumentException("The angle of a parallelogram must be in range " + MIN_ANGLE_DEGREES + " and " + MAX_ANGLE_DEGREES + ", but is " + angleDegrees);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parallelogram other = (Parallelogram) o;
        return Double.compare(other.s1, s1) == 0 &&
                Double.compare(other.s2, s2) == 0 &&
                Double.compare(other.angleRadians, angleRadians) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(s1, s2, angleRadians);
    }

    @Override
    public String toString() {
        return "Parallelogram{" +
                "s1=" + s1 +
                ", s2=" + s2 +
                ", angleDegrees=" + angleDegrees +
                '}';
    }

    public static void main(String[] args) {
        // Let's define a parallelogram with s2 = 5, and where the tangent of the angle is 4/3
        // This results in a width of 4
        double angle = radiansToDegrees(Math.atan(4.0 / 3.0));
        Parallelogram parallelogram = new Parallelogram(10.0D, 5.0D, angle);

        System.out.println("The " + parallelogram + " has length " + parallelogram.getLength() +
                " and width " + parallelogram.getWidth());
        System.out.println("It's area is " + parallelogram.getArea() + " m2 ( " +
                m2ToSquareFeet(parallelogram.getArea()) + " square feet)");
        System.out.println("It's circumference is " + parallelogram.getCircumference() + " m ( " +
                meterToFeet(parallelogram.getCircumference()) + " feet)");
    }

}
