package com.c1games.terminal.algo.defend;

import com.c1games.terminal.algo.Coords;
import com.c1games.terminal.algo.map.CanSpawn;
import com.c1games.terminal.algo.map.GameState;

import java.util.List;
import java.util.Set;

public class BuildOrdinalDefense {

    private GameState move;

    public BuildOrdinalDefense(GameState move) {
        this.move = move;
    }

    public void buildDefense(boolean isAttackTurn, Set<Coords> coordsToAvoid) {
        List<OrdinalPlacement> ordinalPlacements = DefensivePlacementStrategy.getInstance().getOrdinalPlacements();

        int numberBuilt = 0;
        for (int i = 0; i < ordinalPlacements.size() && move.data.p2Stats.cores > 0; i++) {
            OrdinalPlacement unitToPlace = ordinalPlacements.get(i);
            Coords coords = unitToPlace.getCoords();

            // do not rebuild if we need it broken for the win condition
            if (isAttackTurn && coordsToAvoid.contains(coords)) {
                continue;
            }
            // Unit currentUnitAtLoc = gameState.getWallAt(coords);

            if (unitToPlace.getPlaceOperationType() == PlaceOperationType.Build) {
                // has the unit been manually deleted?
                if (move.canSpawn(coords, unitToPlace.getUnitType(), 1) == CanSpawn.Yes) {
                    // can you afford to build
                    if (move.numberAffordable(unitToPlace.getUnitType()) >= 1) {
                        // spawn unit
                        move.attemptSpawn(coords, unitToPlace.getUnitType());

                        numberBuilt++;
                    } else {
                        // need to save 2 credits during the current turn
                        if (numberBuilt >= 1) {
                            // save credits
                        }
                    }
                }
            } else if (unitToPlace.getPlaceOperationType() == PlaceOperationType.Upgrade) {
                move.attemptUpgrade(coords);
            }

        }
    }

    public void buildDefense() {
        List<OrdinalPlacement> ordinalPlacements = DefensivePlacementStrategy.getInstance().getOrdinalPlacements();

        int numberBuilt = 0;
        for (int i = 0; i < ordinalPlacements.size() && move.data.p2Stats.cores > 0; i++) {
            OrdinalPlacement unitToPlace = ordinalPlacements.get(i);
            Coords coords = unitToPlace.getCoords();

            // Unit currentUnitAtLoc = gameState.getWallAt(coords);

            if (unitToPlace.getPlaceOperationType() == PlaceOperationType.Build) {
                // has the unit been manually deleted?
                if (move.canSpawn(coords, unitToPlace.getUnitType(), 1) == CanSpawn.Yes) {
                    // can you afford to build
                    if (move.numberAffordable(unitToPlace.getUnitType()) >= 1) {
                        // spawn unit
                        move.attemptSpawn(coords, unitToPlace.getUnitType());

                        numberBuilt++;
                    } else {
                        // need to save 2 credits during the current turn
                        if (numberBuilt >= 1) {
                            // save credits
                        }
                    }
                }
            } else if (unitToPlace.getPlaceOperationType() == PlaceOperationType.Upgrade) {
                move.attemptUpgrade(coords);
            }

        }
    }
}
