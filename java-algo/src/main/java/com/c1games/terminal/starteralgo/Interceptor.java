package com.c1games.terminal.starteralgo;

import com.c1games.terminal.algo.*;
import com.c1games.terminal.algo.io.GameLoop;
import com.c1games.terminal.algo.io.GameLoopDriver;
import com.c1games.terminal.algo.map.GameState;
import com.c1games.terminal.algo.map.MapBounds;
import com.c1games.terminal.algo.map.Unit;
import com.c1games.terminal.algo.units.UnitType;


public class Interceptor {
	
	public static void deployInterceptors (GameState gs) {
		if (funnelOpen(gs)) {
			while (gs.data.p1Stats.bits >= 1) {
				gs.attemptSpawn(new Coords(25, 11), UnitType.Interceptor);
			}
		} else if (healthDeplenished(gs)){
			gs.attemptSpawn(new Coords(25, 11), UnitType.Interceptor);
		} else {
			double x = Math.random(); 
			if (x < 0.5) {
				gs.attemptSpawn(new Coords(25, 11), UnitType.Interceptor);
			}
		}
	}
	
	public static boolean funnelOpen (GameState gs) {
		// finish logic  
		return false; 
	}
	
	public static boolean healthDeplenished (GameState gs) {
		double totalHealth = 0; 
		for (int i = 24; i <= 27; i++) {
			for (int j = i - 14; j <= 13; j++) {
				Unit u = gs.getWallAt(new Coords(i, j));
				if (u != null) {
					totalHealth += u.health; 
				}
			}
		}
		if (totalHealth <= 0.85 * 598) {
			return true;
		} else {
			return false;
		}
	}
	
}
