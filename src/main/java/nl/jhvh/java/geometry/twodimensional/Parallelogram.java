package nl.jhvh.java.geometry.twodimensional;

import com.google.common.util.concurrent.AtomicDouble;
import nl.jhvh.java.util.Log;

import java.util.Objects;

import static java.lang.Math.sin;
import static nl.jhvh.java.conversion.ImperialMetricConversion.m2ToSquareFeet;
import static nl.jhvh.java.conversion.ImperialMetricConversion.meterToFeet;
import static nl.jhvh.java.geometry.GeometryUtil.DEGREES_TO_RADIANS_FACTOR;
import static nl.jhvh.java.geometry.GeometryUtil.radiansToDegrees;

public class Parallelogram implements TwoDimensional {

    private static final double MIN_ANGLE_DEGREES = 0.0;
    private static final double MAX_ANGLE_DEGREES = 90.0;

    private final double s1;
    private final double s2;
    private final double angleRadians;
    private final double angleDegrees;
    private final double length;

    // Needs to be Guava AtomicDouble in order to be thread safe (Java has no AtomicDouble)
    private AtomicDouble width = null;

    public Parallelogram(double s1, double s2, double angleDegrees) {
        this.s1 = s1;
        this.s2 = s2;
        this.length = s1;
        this.angleDegrees = angleDegrees;
        this.angleRadians = angleDegrees * DEGREES_TO_RADIANS_FACTOR;
        validateInput();
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
        if (width == null) {
            Log.logger(this.getClass()).debug("Lazy initialization of " + this.getClass().getSimpleName() + ".width");
            width = new AtomicDouble(s2 * sin(angleRadians));
        }
        return width.doubleValue();
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
        if (this.s1 < 0.0 || s2 < 0.0) {
            throw new IllegalArgumentException("Lengths of both sides must be positive, but s1 = " + s1 + " and s2 = " + s2);
        }
        if (angleDegrees < MIN_ANGLE_DEGREES || angleDegrees >= MAX_ANGLE_DEGREES) {
            throw new IllegalArgumentException("The angle of the parallelogram must be in range " + MIN_ANGLE_DEGREES + " and " + MAX_ANGLE_DEGREES + ", but is " + angleDegrees);
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
                ", angleRadians=" + angleRadians +
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
