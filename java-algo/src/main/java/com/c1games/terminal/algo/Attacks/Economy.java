package com.c1games.terminal.algo.Attacks;

import com.c1games.terminal.algo.Config;
import com.c1games.terminal.algo.Coords;
import com.c1games.terminal.algo.PlayerId;
import com.c1games.terminal.algo.map.CanSpawn;
import com.c1games.terminal.algo.map.GameState;
import com.c1games.terminal.algo.map.SpawnCommand;
import com.c1games.terminal.algo.map.Unit;
import com.c1games.terminal.algo.units.UnitType;

public class Economy {
    
    //Save money on attack - don't spend all
    public GameState Economy(int budget, GameState curr, UnitType pieces,  Config config) {
         int totalCost = 0;
         Coords best = Attack.bestLaunch(curr, pieces, 1);
         while (totalCost < budget) {
             curr.attemptSpawn(best, pieces);
             totalCost += config.unitInformation.get(pieces.ordinal()).cost()[1];
         }
         return curr;
    }   
}
