package com.c1games.terminal.algo.defend;

import com.c1games.terminal.algo.Coords;
import com.c1games.terminal.algo.GameIO;
import com.c1games.terminal.algo.units.UnitType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DefensivePlacementStrategy {

    private static DefensivePlacementStrategy single_instance = null;
    private final List<OrdinalPlacement> unitsToPlace;

    DefensivePlacementStrategy() {
        unitsToPlace = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new StringReader(CSVString.ordinal_csv));

            String s;
            StringTokenizer st;
            int moveNumber = 0;

            while ((s = br.readLine()) != null) {
                st = new StringTokenizer(s, ",");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                Coords coords = new Coords(x, y);
                UnitType unitType = UnitType.valueOf(st.nextToken());
                PlaceOperationType placeOperationType = PlaceOperationType.valueOf(st.nextToken());

                OrdinalPlacement placement = new OrdinalPlacement(moveNumber, coords, unitType, placeOperationType);
                unitsToPlace.add(placement);

                moveNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<OrdinalPlacement> getOrdinalPlacements() {
        return unitsToPlace;
    }

    public static DefensivePlacementStrategy getInstance() {
        if (single_instance == null) {
            single_instance = new DefensivePlacementStrategy();
        }

        return single_instance;
    }

    public static void main(String[] args) throws IOException {
        DefensivePlacementStrategy ds = DefensivePlacementStrategy.getInstance();

        ds.getOrdinalPlacements().stream().forEach(System.out::println);
    }
}
