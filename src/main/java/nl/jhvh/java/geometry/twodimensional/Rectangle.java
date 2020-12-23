package nl.jhvh.java.geometry.twodimensional;

import java.util.Objects;

import static nl.jhvh.java.geometry.GeometryUtil.RECTANGLE_DEGREES;

public class Rectangle implements TwoDimensional {

    private final Parallelogram delegate;

    public Rectangle(double length, double width) {
        this.delegate = new Parallelogram(length, width, RECTANGLE_DEGREES);
    }

    @Override
    public double getCircumference() {
        return delegate.getCircumference();
    }

    @Override
    public double getArea() {
        return delegate.getArea();
    }

    public double getLength() {
        return delegate.getLength();
    }

    public double getWidth() {
        return delegate.getWidth();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return delegate.equals(rectangle.delegate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delegate);
    }

    @Override
    public String toString() {
        return "Parallelogram{" +
                "length=" + getLength() +
                ", width=" + getWidth() +
                '}';
    }


    public static void main(String[] args) {
        final Rectangle rectangle = new Rectangle(10.0, 20.0);
        System.out.println("rectangle.length:        " + rectangle.getLength());
        System.out.println("rectangle.width:         " + rectangle.getWidth());
        System.out.println("rectangle.circumference: " + rectangle.getCircumference());
        System.out.println("rectangle.area:          " + rectangle.getArea());
        System.out.println();
        System.out.println("Again - rectangle.area:          " + rectangle.getArea());
        System.out.println();

        final Rectangle otherRectangle = new Rectangle(10.0, 20.0);
        boolean areEqual = rectangle.equals(otherRectangle);
        System.out.println("Are the rectangles equal? " + areEqual);
    }

}
