package Model;

import java.io.BufferedReader;
import java.io.FileReader;

public class Map {
	
	final static int MAP_WIDTH = 30;
	final static int MAP_HEIGHT = 22;
	
	final static char SHORT_GRASS = 'g';
	final static char TREE = 'T';
	final static char TRAIL = 't';
	final static char TALL_GRASS = 'G';
	
	//instance variables
	private int tileSize;
	private int x;
	private int y;
	private char[][] currentMap;
	
	//ctor
	public Map(String fileName, int tielSize) {
		//currentMap = new char[30][22];
		//if statement for which  map
		this.tileSize = tileSize;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		}
		
		catch(Exception e) {	
		}
		
		currentMap = buildMapOne();
	}
	
	//to string method
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

	//method to build first map
	private char[][] buildMapOne() {
		char[][] tempMap = new char[MAP_HEIGHT][MAP_WIDTH];
		//set all tiles to walkable
		for(int i = 0; i < MAP_HEIGHT; i++) {	
			for (int j = 0; j < MAP_WIDTH; j++) {
				tempMap[i][j] = SHORT_GRASS;
			}
		}
		// build barrier
		for (int i = 0; i < MAP_WIDTH; i++) {
			tempMap[0][i] = TREE;
			tempMap[MAP_HEIGHT-1][i] = TREE;
			if (i < MAP_HEIGHT) {
				tempMap[i][0] = TREE;
				tempMap[i][MAP_WIDTH-1] = TREE;
			}
		}
		//build trail
		for(int i = 1; i < MAP_HEIGHT - 1; i++){
			tempMap[i][14] = TRAIL;
			tempMap[i][15] = TRAIL;
		}
		for(int j = 1; j < MAP_WIDTH - 1; j++){
			tempMap[11][j] = TRAIL;
			tempMap[10][j] = TRAIL;
		}
		
		//build tall grass
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 9; j++){
				tempMap[3+i][3+j] = TALL_GRASS;
				tempMap[3+i][18+j] = TALL_GRASS;
				tempMap[14+i][3+j] = TALL_GRASS;
				tempMap[14+i][18+j] = TALL_GRASS;
			}
		}
		return tempMap;
	}

}
