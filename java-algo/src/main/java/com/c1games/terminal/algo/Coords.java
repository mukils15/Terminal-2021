package com.c1games.terminal.algo;

import java.util.List;
import java.util.Objects;

/**
 * Two-dimensional, integer coordinates.
 */
public class Coords {
    public int x;
    public int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public List<Coords> neighbors() {
        return List.of(
                new Coords(x + 1, y),
                new Coords(x - 1, y),
                new Coords(x, y + 1),
                new Coords(x, y - 1)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coords coords = (Coords) o;
        return x == coords.x && y == coords.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    public float distance(Coords other) {
        return (float)Math.sqrt(((x - other.x) * (x - other.x)) + ((y - other.y) * (y - other.y)));
    }
}
