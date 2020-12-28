package nl.jhvh.java.geometry.twodimensional;

import java.util.Objects;

import static nl.jhvh.java.geometry.GeometryUtil.RECTANGLE_DEGREES;

public class Rectangle implements TwoDimensional {

    // Non-private and non-final for testability...
    protected Parallelogram delegateFor2Dimensional;

    private final double width;

    private final double length;

    public Rectangle(double length, double width) {
        this.delegateFor2Dimensional = new Parallelogram(length, width, RECTANGLE_DEGREES);
        this.length = length;
        this.width = width;
    }

    @Override
    public double getCircumference() {
        return delegateFor2Dimensional.getCircumference();
    }

    @Override
    public double getArea() {
        return delegateFor2Dimensional.getArea();
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return delegateFor2Dimensional.equals(rectangle.delegateFor2Dimensional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delegateFor2Dimensional);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
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
