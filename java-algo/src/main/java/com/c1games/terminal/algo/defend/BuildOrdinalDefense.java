package com.c1games.terminal.algo.defend;

import com.c1games.terminal.algo.Coords;
import com.c1games.terminal.algo.map.CanSpawn;
import com.c1games.terminal.algo.map.GameState;
import com.c1games.terminal.algo.map.Unit;

import java.io.IOException;
import java.util.List;

public class BuildOrdinalDefense {

    private GameState gameState;

    public BuildOrdinalDefense(GameState gameState) {
        this.gameState = gameState;
    }

    public void buildDefense() {
        List<OrdinalPlacement> ordinalPlacements = DefensivePlacementStrategy.getInstance().getOrdinalPlacements();

        int numberBuilt = 0;
        for (int i = 0; i < ordinalPlacements.size(); i++) {
            OrdinalPlacement unitToPlace = ordinalPlacements.get(i);
            Coords coords = unitToPlace.getCoords();

            Unit currentUnitAtLoc = gameState.getWallAt(coords);

            // has the unit been manually deleted?
            if (gameState.canSpawn(coords, unitToPlace.getUnitType(), 1) == CanSpawn.Yes) {
                // can you afford to build
                if (gameState.numberAffordable(unitToPlace.getUnitType()) >= 1) {
                    // spawn unit
                    gameState.attemptSpawn(coords, unitToPlace.getUnitType());

                    numberBuilt++;
                } else {
                    // need to save 2 credits during the current turn
                    if (numberBuilt >= 1) {
                        // save credits
                    }
                }
            }
        }
    }
}
