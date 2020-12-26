package nl.jhvh.java.geometry.twodimensional;

import java.util.Objects;

public class Square implements TwoDimensional {

    private final Rectangle delegate;

    private final Double side;

    public Square(double side) {
        this.delegate = new Rectangle(side, side);
        this.side = side;
    }

    @Override
    public double getCircumference() {
        return delegate.getCircumference();
    }

    @Override
    public double getArea() {
        return delegate.getArea();
    }

    public double getSide() {
        return this.side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return delegate.equals(square.delegate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delegate);
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + getSide() +
                '}';
    }

}
