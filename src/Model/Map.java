package Model;

import java.awt.Color;
import java.awt.Graphics2D;
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
	private int[][] currentMap;
	private int mapWidth; 	//tile#
	private int mapHeight; 	//tile#
	
	//ctor
	public Map(String fileName, int tileSize) {
		//currentMap = new char[30][22];
		//if statement for which  map
		this.tileSize = tileSize;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(fileName));
		
			mapWidth = Integer.parseInt(br.readLine());
			mapHeight = Integer.parseInt(br.readLine());
			
			currentMap = new int[mapHeight][mapWidth];
			
			String delimiters = " ";
			
			for(int row = 0; row < mapHeight; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for (int col = 0; col < mapWidth; col++) {
					currentMap[row][col] = Integer.parseInt(tokens[col]);
				}
				
				
			}
			
			
		}
		
		catch(Exception e) {	
		}
		
		//currentMap = buildMapOne();
	}
	
	public void update() {
		
	}
	
	// 0 = blocked     = black
	// 1 = trail       = white
	// 2 = short grass = light green
	// 3 = tall grass  = dark green
	public void draw(Graphics2D g) {
		
		for(int row = 0; row < mapHeight; row++) {
			for(int col = 0; col < mapWidth; col++) {
				
				int rc = currentMap[row][col];
				
				if(rc == 0) {
					g.setColor(Color.BLACK);
				}
				if(rc == 1) {
					g.setColor(Color.WHITE);
				}
				if(rc == 2) {
					g.setColor(Color.gray);
				}
				if(rc == 3) {
					g.setColor(Color.GREEN);
				}
				g.fillRect(x + col * tileSize,  y + row * tileSize,  tileSize,  tileSize);
				
			}
		}
		
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

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}
