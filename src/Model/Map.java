package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.imageio.ImageIO;

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
	
	private BufferedImage tileSet;
	private Tile[] tiles;
	
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
			
			String delimiters = "\\s+";
			
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
		//loadTiles("resizedTiles.png");
		//currentMap = buildMapOne();
	}
	
	public void loadTiles(String s) {
		
		try {
			tileSet = ImageIO.read(new File(s));
			int numTilesAcross = 3;//(tileSet.getWidth() +1/ (tileSize +1));
			tiles = new Tile[4];
			
			BufferedImage subImage;
			boolean b = false;
			for(int col = 0; 0 < numTilesAcross; col++) {
				subImage = tileSet.getSubimage(col*tileSize, 0, tileSize, tileSize);
				if (col == 3) {
					b = true;
				} else
					b = false;
				tiles[col] = new Tile(subImage, b);
				//subImage = tileSet.getSubimage(col*tileSize+col, 0, tileSize, tileSize);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
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
				
				//int r = rc / 3;
				//int c = rc % 3;
				
				g.drawImage(tiles[rc].getImage(), x+col*tileSize,
							y+row*tileSize, null);
				
				
			/*	if(rc == 0) {
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
			
			*/
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

	public boolean isBlocked(int col, int row) {
		int rc = currentMap[row][col];
		//int r = rc / tiles[0].length;
		//int c = rc % tiles[0].length;
		return tiles[rc].isBlocked();
	}
	
	public int getTileSize(){
		return this.tileSize;
	}

	public void setX(double x){
		this.x = (int) x;
	}
	
	public void setY(double y){
		this.y = (int) y;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public int getColTileIndex(int x){
		return x / tileSize;
	}
	
	public int getRowTileIndex(int y){
		return y / tileSize;
	}
	
	public int getTile(int x, int y){
		return currentMap[y][x];
	}

}
