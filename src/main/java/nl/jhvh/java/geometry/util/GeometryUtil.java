package nl.jhvh.java.geometry.util;

import org.apache.commons.lang3.NotImplementedException;

/**
 * This is for DEMO only to show difference between Java and Kotlin (using extension methods in Kotlin).
 * For any real use, make sure to use the stuff from the `javax.measure` package instead !!
 */
public class GeometryUtil {

    private GeometryUtil() {
        throw new NotImplementedException(this.getClass().getName() + " is a utility class; do not instantiate!");
    }

    public static final double RECTANGLE_DEGREES = 90;

    public static final double RADIANS_TO_DEGREES_FACTOR = 180 / Math.PI;
    public static final double DEGREES_TO_RADIANS_FACTOR = Math.PI / 180;

    public static double radiansToDegrees(double radians) {
        return radians * RADIANS_TO_DEGREES_FACTOR;
    }

    public static double degreesToRadians(double radians) {
        return radians * DEGREES_TO_RADIANS_FACTOR;
    }

}
