package nl.jhvh.java.geometry.model.twodimensional;

import nl.jhvh.java.geometry.model.Dimensional;
import nl.jhvh.java.geometry.model.Geometry;

import static nl.jhvh.java.geometry.model.Dimensional.twoDimensional;

interface TwoDimensional extends Geometry {

    /** Circumference of the {@link TwoDimensional} geometry in meters */
    double getCircumference();

    /** Area of a {@link TwoDimensional} geometry in square meters */
    double getArea();

    @Override
    default Dimensional getDimensional() {
        return twoDimensional;
    }
}
