package com.c1games.terminal.algo.Attacks;

import com.c1games.terminal.algo.Config;
import com.c1games.terminal.algo.Coords;
import com.c1games.terminal.algo.PlayerId;
import com.c1games.terminal.algo.map.CanSpawn;
import com.c1games.terminal.algo.map.GameState;
import com.c1games.terminal.algo.map.SpawnCommand;
import com.c1games.terminal.algo.map.Unit;
import com.c1games.terminal.algo.units.UnitType;

public class DemolishScoutStagger {
    
    public static void DemolishScoutStagger(GameState curr, Coords best) {
        
        int bitsRemaining = (int) curr.data.p1Stats.bits;
        if (best.x < 13) {
        	   for (int i = 0; i < bitsRemaining * 0.75; i++) {
                curr.attemptSpawn(new Coords(10, 3), UnitType.Demolisher);
        	   }
        	   bitsRemaining = (int) curr.data.p1Stats.bits;
        	   for (int i = 0; i <= bitsRemaining; i ++) {
        	       curr.attemptSpawn(new Coords(11, 2), UnitType.Scout);
        	   } 	
        }  else {
           for (int i = 0; i < curr.data.p1Stats.bits * 0.75; i++) {
                curr.attemptSpawn(new Coords(14, 0), UnitType.Demolisher);
            }
           bitsRemaining = (int) curr.data.p1Stats.bits;
        	   for (int i = 0; i <= bitsRemaining; i ++) {
        	       curr.attemptSpawn(new Coords(11, 2), UnitType.Scout);
        	   }	
        }
        
//        for (int i = 0; i < curr.data.p1Stats.bits * 0.75; i++) {
//            curr.attemptSpawn(best, UnitType.Demolisher);
//        }
//        
//        while (curr.data.p1Stats.bits > 0) {
//            if (best.x < 13) {
//                Coords scout = new Coords(best.x + 1, best.y-1);
//                curr.attemptSpawn(scout, UnitType.Scout);
//            } else if (best.x > 13) {
//                Coords scout = new Coords(best.x + 1, best.y + 1);
//                curr.attemptSpawn(scout, UnitType.Scout);
//            } else {
//                Coords scout = new Coords(14, 0);
//                curr.attemptSpawn(scout, UnitType.Scout);
//            }
//        }
    }
}
