package Model;

public class Map {
	
	final static int MAP_WIDTH = 30;
	final static int MAP_HEIGHT = 22;
	
	final static char WALKABLE = 'W';
	final static char BLOCKED = 'X';
	
	private char[][] currentMap;
	
	public Map() {
		//currentMap = new char[30][22];
		//if statement for which  map
		currentMap = buildMapOne();
	}
	
	@Override
	public String toString() {
		StringBuilder mapToString = new StringBuilder();
		
		for(int i = 0; i < MAP_HEIGHT; i++) {
			for (int j = 0; j < MAP_WIDTH; j++) {
				mapToString.append(this.currentMap[i][j] + " ");
			}
			mapToString.append('\n');
		}
		return mapToString.toString();
	}

	private char[][] buildMapOne() {
		char[][] tempMap = new char[MAP_HEIGHT][MAP_WIDTH];
		
		for(int i = 0; i < MAP_HEIGHT; i++) {	
			for (int j = 0; j < MAP_WIDTH; j++) {
				tempMap[i][j] = WALKABLE;
			}
		}
		
		
		// set edges
		for (int i = 0; i < MAP_WIDTH; i++) {
			tempMap[0][i] = BLOCKED;
			tempMap[MAP_HEIGHT-1][i] = BLOCKED;
			if (i < MAP_HEIGHT) {
				tempMap[i][0] = BLOCKED;
				tempMap[i][MAP_WIDTH-1] = BLOCKED;
			}
		}
		
		
		
		return tempMap;
	}

}
