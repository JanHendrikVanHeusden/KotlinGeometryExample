package nl.jhvh.java.geometry;

import org.apache.commons.lang3.NotImplementedException;

public class GeometryUtil {

    private GeometryUtil() {
        throw new NotImplementedException(this.getClass().getName() + " is a utility class; do not instantiate!");
    }

    public static final double RECTANGLE_DEGREES = 90;

    public static double radiansToDegreesFactor = 180 / Math.PI;
    public static double degreesToRadiansFactor = Math.PI / 180;

    public static double radiansToDegrees(double radians) {
        return radians * radiansToDegreesFactor;
    }

    public static double getDegreesToRadians(double radians) {
        return radians * degreesToRadiansFactor;
    }

}
