package nl.jhvh.java.geometry.model.twodimensional;

import java.util.Objects;

public class Square implements TwoDimensional {

    private final Rectangle delegateFor2Dimensional;

    private final Double side;

    public Square(double side) {
        this.delegateFor2Dimensional = new Rectangle(side, side);
        this.side = side;
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
