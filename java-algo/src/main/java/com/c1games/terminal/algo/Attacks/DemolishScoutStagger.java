package com.c1games.terminal.algo.Attacks;

import com.c1games.terminal.algo.Config;
import com.c1games.terminal.algo.Coords;
import com.c1games.terminal.algo.PlayerId;
import com.c1games.terminal.algo.map.CanSpawn;
import com.c1games.terminal.algo.map.GameState;
import com.c1games.terminal.algo.map.SpawnCommand;
import com.c1games.terminal.algo.map.Unit;
import com.c1games.terminal.algo.units.UnitType;

public class DemolishScoutStagger extends Attack{
    
    public DemolishScoutStagger(GameState curr, int numScouts, int rand) {
        int totalCost = 0;
        Coords best = bestLaunch(curr, UnitType.Scout, 1).get(rand);
        for (int i = 0; i < numScouts; i++) {
            curr.attemptSpawn(best, UnitType.Scout);
        }
        if (best.x > 14) {
            Coords demolish = new Coords(best.x-1, best.y-1);
            curr.attemptSpawn(demolish, UnitType.Demolisher);
        } else if (best.x < 14) {
            Coords demolish = new Coords(best.x-1, best.y+1);
            curr.attemptSpawn(demolish, UnitType.Demolisher);
        } else {
            Coords demolish = new Coords(13, 0);
            curr.attemptSpawn(demolish, UnitType.Demolisher);
        }
    }
}
