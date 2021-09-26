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

public class Economy{
    
    //Save money on attack - don't spend all
    public static void Economy(int budget, GameState curr,  Coords best) {
         int totalCost = 0;
         if (budget >= 3) {
        	 curr.attemptSpawn(best, UnitType.Demolisher);
             totalCost += 3; 
         }
         if (best.x != 25 || best.y != 11) {
        	 best =  new Coords(25, 11);
         } else {
        	 best = new Coords(24, 10);
         }
         while (totalCost <= budget) {
             curr.attemptSpawn(best, UnitType.Interceptor);
             totalCost += 1;
         }
    }   
}
