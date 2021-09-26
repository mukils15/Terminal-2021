package com.c1games.terminal.algo.Attacks;


import java.util.List;

import com.c1games.terminal.algo.Config;
import com.c1games.terminal.algo.Coords;
import com.c1games.terminal.algo.PlayerId;
import com.c1games.terminal.algo.map.CanSpawn;
import com.c1games.terminal.algo.map.GameState;
import com.c1games.terminal.algo.map.SpawnCommand;
import com.c1games.terminal.algo.map.Unit;
import com.c1games.terminal.algo.units.UnitType;

public class ScoutRush extends Attack{
    
    public ScoutRush(int numScouts, GameState curr, int rand) {
        Coords best = bestLaunch(curr, UnitType.Scout, numScouts).get(rand);
        for (int i = 0; i < numScouts; i++) {
            boolean can = curr.attemptSpawn(best, UnitType.Scout);
            if (!can) {
                i = numScouts;
            }
        }
    }
    
}