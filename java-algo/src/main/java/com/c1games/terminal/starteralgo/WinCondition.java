package com.c1games.terminal.starteralgo;

import com.c1games.terminal.algo.*;
import com.c1games.terminal.algo.io.GameLoop;
import com.c1games.terminal.algo.io.GameLoopDriver;
import com.c1games.terminal.algo.map.GameState;
import com.c1games.terminal.algo.map.MapBounds;
import com.c1games.terminal.algo.map.Unit;
import com.c1games.terminal.algo.units.UnitType;

import java.util.*;

public class WinCondition {

// 0 if no win, 1 if right, 2 if left 
    public static int winPossible(GameState gs, double multiplier) {
        int winAmount = (int) gs.data.p2Stats.integrity;
        winAmount++;
        int responseLeft = 0;
        int responseRight = 0;
        double damagePossible = 0;

        for (int i = 0; i <= 3; i++) {
            for (int j = 13; j >= 13 - i; j--) {
                List<Unit> attackers = new ArrayList<Unit>();
                attackers = gs.getAttackers(new Coords(i, j));
                for (Unit u : attackers) {
                    damagePossible += u.unitInformation.attackDamageWalker.orElse(0);
                }
            }
        }
        int spawnPossible = gs.numberAffordable(UnitType.Scout);
        double sP = (double) spawnPossible;
        if ((sP * 15) - damagePossible > winAmount * multiplier) {
            responseLeft = 2;
        } else {
            responseRight = 0;
        }

        damagePossible = 0;
        for (int i = 24; i >= 27; i++) {
            for (int j = 13; j >= i - 14; j--) {
                List<Unit> attackers = new ArrayList<Unit>();
                attackers = gs.getAttackers(new Coords(i, j));
                for (Unit u : attackers) {
                    damagePossible += u.unitInformation.attackDamageWalker.orElse(0);
                }
            }
        }
        if ((sP * 15) - damagePossible > winAmount * multiplier) {
            responseRight = 1;
        } else {
            responseLeft = 0;
        }

        return (Math.max(responseLeft, responseRight));
    }

    public static boolean doWin(GameState gs, int side) {
        List<Coords> coord = new ArrayList<Coords>();
        if (side == 1) {
            coord.add(new Coords(9, 5));
            coord.add(new Coords(2, 12));
            coord.add(new Coords(0, 13));
            coord.add(new Coords(1, 13));
            coord.add(new Coords(3, 11));
        } else {
            coord.add(new Coords(27, 13));
            for (int i = 26; i >= 16; i--) {
                for (int j = i - 13; j >= i - 14; j--) {
                    coord.add(new Coords(i, j));
                }
            }
        }
        if (defenseBroken(gs, side, coord)) {
            if (side == 1) {
                for (int i = 1; i <= gs.numberAffordable(UnitType.Scout); i++) {
                	gs.attemptSpawn(new Coords(21, 11), UnitType.Wall);
                    gs.attemptSpawn(new Coords(13, 0), UnitType.Scout);
                }
                return true;
            } else {
                for (int i = 1; i <= gs.numberAffordable(UnitType.Scout); i++) {
                    gs.attemptSpawn(new Coords(14, 0), UnitType.Scout);
                }
                return true;
            }
        } else {
            breakDefense(gs, coord);
            return false;
        }

    }

// barrier 9, 5
    public static boolean defenseBroken(GameState gs, int side, List<Coords> coord) {
        for (Coords x : coord) {
            if (gs.getWallAt(x) != null) {
                return false;
            }
        }
        return true;
    }

    public static void breakDefense(GameState gs, List<Coords> coord) {
        gs.attemptRemoveStructureMultiple(coord);
    }

    public static boolean opponentWinCondition(GameState gs) {
        double potentialDamage = 0;
        float theirUnits = gs.data.p2Stats.bits;
        for (int i = 0; i <= 3; i++) {
            for (int j = 13; j >= 13 - i; j--) {
                Unit u = gs.getWallAt(new Coords(i, j));
                potentialDamage += (u.unitInformation.attackDamageWalker.orElse(0) * (13 - 9));
            }
        }
        if (potentialDamage <= (theirUnits * 9)) {
            return true;
        } else {
            return false;
        }
    }
}
