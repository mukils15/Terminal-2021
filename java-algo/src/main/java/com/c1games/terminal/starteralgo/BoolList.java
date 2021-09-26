package com.c1games.terminal.starteralgo;


import com.c1games.terminal.algo.*;
import com.c1games.terminal.algo.io.GameLoop;
import com.c1games.terminal.algo.io.GameLoopDriver;
import com.c1games.terminal.algo.map.GameState;
import com.c1games.terminal.algo.map.MapBounds;
import com.c1games.terminal.algo.map.Unit;
import com.c1games.terminal.algo.units.UnitType;

import java.util.*;


public class BoolList {
	public List<Coords> coordList; 
	public boolean tried; 
	
	public BoolList(List<Coords> c, boolean b) {
		coordList = c; 
		tried = b; 
	}
}
