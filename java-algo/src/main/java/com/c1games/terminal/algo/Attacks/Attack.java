package com.c1games.terminal.algo.Attacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.c1games.terminal.algo.Coords;
import com.c1games.terminal.algo.map.GameState;
import com.c1games.terminal.algo.map.Unit;
import com.c1games.terminal.algo.units.UnitType;

public abstract class Attack {
    public static List<Coords> bestLaunch(GameState curr, UnitType Piece, int num) {
        Coords minCoord = new Coords(0,0);
        double minDamage = Integer.MAX_VALUE;
        List<Double> minDamages = new ArrayList<Double>();
        List<Coords> minCoords = new ArrayList<Coords>();
        for (int i = 0; i <= 27; i++) {
            Coords start = new Coords(0,0);
            if (i <= 13) {
                start = new Coords(i, 13-i);
            } else {
                start = new Coords(i, i-14);
            }
            
            if (curr.canSpawn(start, Piece, num).affirmative()) {
                List<Coords> path = curr.pathfind(start, 2);
                double totalDamage = 0;
                for (Coords m: path) {
                    List<Unit> attackers = curr.getAttackers(m);
                    for (Unit k: attackers) {
                        totalDamage += k.unitInformation.attackDamageWalker.orElse(0);
                    }
                }
                int index = Collections.binarySearch(minDamages, totalDamage);
                if (index < 0) index = ~index;
                minDamages.add(index, totalDamage);
                minCoords.add(index, start);
            }
        }
        return minCoords;
    }
}
