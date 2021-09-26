package com.c1games.terminal.algo.Attacks;

import java.util.List;

import com.c1games.terminal.algo.Coords;
import com.c1games.terminal.algo.map.GameState;
import com.c1games.terminal.algo.map.Unit;
import com.c1games.terminal.algo.units.UnitType;

public class Attack {
    public Coords bestLaunch(GameState curr, UnitType Piece, int num) {
        Coords minCoords = new Coords(0,0);
        double minDamage = Integer.MAX_VALUE;
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
                for (int m = 0; m < path.size(); m++) {
                    Coords coord = path.get(m);
                    List<Unit> attackers = curr.getAttackers(coord);
                    for (int k = 0; k < attackers.size(); k++) {
                        Unit atk = attackers.get(k);
                        totalDamage += atk.unitInformation.attackDamageWalker.getAsDouble();
                    }
                }
                if (totalDamage < minDamage) {
                    minDamage = totalDamage;
                    minCoords = start;
                }
            }
        }
        
        return minCoords;
    }
}
