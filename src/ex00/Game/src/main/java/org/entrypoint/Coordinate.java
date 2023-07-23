package org.entrypoint;

import java.util.Objects;

public class Coordinate {
    private int row;
    private int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Coordinate up() {
        return new Coordinate(row - 1, column);
    }

    public Coordinate down() {
        return new Coordinate(row + 1, column);
    }

    public Coordinate left() {
        return new Coordinate(row, column - 1);
    }

    public Coordinate right() {
        return new Coordinate(row, column + 1);
    }

    public boolean isValidRange(int min, int max) {
        return (row >= min) && (row < max)
                && (column >= min) && (column < max);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Coordinate other = (Coordinate) obj;
        return row == other.row && column == other.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
