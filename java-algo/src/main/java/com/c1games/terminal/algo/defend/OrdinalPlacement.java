package com.c1games.terminal.algo.defend;

import com.c1games.terminal.algo.Coords;
import com.c1games.terminal.algo.units.UnitType;

public class OrdinalPlacement {
    private int moveNumber;
    private Coords coords;
    private UnitType unitType;
    private PlaceOperationType placeOperationType;

    public OrdinalPlacement(int moveNumber, Coords coords, UnitType unitType, PlaceOperationType placeOperationType) {
        this.moveNumber = moveNumber;
        this.coords = coords;
        this.unitType = unitType;
        this.placeOperationType = placeOperationType;
    }

    @Override
    public String toString() {
        return "OrdinalPlacement{" +
                "moveNumber=" + moveNumber +
                ", coords=" + coords +
                ", unitType=" + unitType +
                ", placeOperationType=" + placeOperationType +
                '}';
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public Coords getCoords() {
        return coords;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public PlaceOperationType getPlaceOperationType() {
        return placeOperationType;
    }
}
