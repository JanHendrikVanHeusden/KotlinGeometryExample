package nl.jhvh.java.geometry.model.twodimensional;

import nl.jhvh.java.geometry.model.GeometryType;

import java.util.Objects;

import static nl.jhvh.java.geometry.model.GeometryType.SQUARE;

public class Square implements TwoDimensional {

    // Non-private and non-final for testability...
    protected Rectangle delegateFor2Dimensional;

    private final Double side;

    public Square(double side) {
        this.delegateFor2Dimensional = new Rectangle(side, side);
        this.side = side;
    }

    @Override
    public GeometryType getGeometryType() {
        return SQUARE;
    }

    @Override
    public double getCircumference() {
        return delegateFor2Dimensional.getCircumference();
    }

    @Override
    public double getArea() {
        return delegateFor2Dimensional.getArea();
    }

    public double getSide() {
        return this.side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return delegateFor2Dimensional.equals(square.delegateFor2Dimensional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delegateFor2Dimensional);
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + getSide() +
                '}';
    }

}
