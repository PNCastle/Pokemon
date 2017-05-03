/*
 * Authors:  Paul Castleberry, Angel Burr, Sohyun Kim, Isaac Kim
 * Filename: Map.java
 * Purpose:  The Map object of the Pokemon game. Handles reading
 * 			 of image files and text files to build an object.
 *           A 2D int array is used in a text file to build the map.
 */

package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import pokemon.Abra;
import pokemon.Dragonair;
import pokemon.Drowzee;
import pokemon.Graveler;
import pokemon.Grimer;
import pokemon.Haunter;
import pokemon.Pidgey;
import pokemon.Pikachu;
import pokemon.Rapidash;
import pokemon.Staryu;

public class Map {
	
	//instance variables
	private int tileSize;
	private int x;
	private int y;
	private int[][] currentMap;
	private int[][] tempMap;
	private int mapWidth; 	//tile#
	private int mapHeight; 	//tile#
	
	private BufferedImage tileSet;
	private Tile[] tiles;
	
	private ArrayList<CommonPokemon> commonCollection;
	private ArrayList<UncommonPokemon> uncommonCollection;
	private ArrayList<RarePokemon> rareCollection;
	
	//ctor
	public Map(String fileName, int tileSize) {

		// ToDo: if statement for which map
		
		this.tileSize = tileSize;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(fileName));
		
			mapWidth = Integer.parseInt(br.readLine()); // these are variables from the first
			mapHeight = Integer.parseInt(br.readLine());// and second lines of the text file
			
			currentMap = new int[mapHeight][mapWidth];
			
			String delimiters = "\\s+"; // this will separate our file by whitespace
			
			// split our text file up and assign appropriate tile types throughout
			// our int array
			for(int row = 0; row < mapHeight; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for (int col = 0; col < mapWidth; col++) {
					currentMap[row][col] = Integer.parseInt(tokens[col]);
				}		
			}
			br.close();
		}
		catch(Exception e) {	
		}
		tempMap = currentMap.clone();
		initCollections();

	}
	
	// loadTiles will accept the name of a file as a string then walk through
	// the the image file and assign sub images and tile type (ie blocked)
	// for each tile type. Currently 4 50x50 sub images.
	public void loadTiles(String fileName) {
		
		try {
			tileSet = ImageIO.read(new File(fileName));
			int numTilesAcross = 5;
			tiles = new Tile[6];
			
			BufferedImage subImage;
			boolean blocked = false;
			boolean spawnable = false;
			boolean hasItem = false;
			for(int col = 0; col <= numTilesAcross; col++) {
				subImage = tileSet.getSubimage(col*tileSize, 0, tileSize, tileSize);
				if(col == 5){
					hasItem = true;
				}
				if(col == 4){
					hasItem = true;
				}
				if (col == 3) {
					blocked = true;
				}
				else
					blocked = false;
				if (col == 2) {
					spawnable = true;
				}
				else 
					spawnable = false;
				tiles[col] = new Tile(subImage, blocked, spawnable, hasItem);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// empty update method
	public void update() {
		
	}
	
	// walk through the currentMap and draw appropriate
	// images by indexing into the tiles[] array images
	// to draw our entire map from txt file
	// 0 = path
	// 1 = short grass
	// 2 = tall grass
	// 3 = tree(blocked)
	public void draw(Graphics2D g) {
		
		for(int row = 0; row < mapHeight; row++) {
			for(int col = 0; col < mapWidth; col++) {
				
				int rc = tempMap[row][col];
								
				g.drawImage(tiles[rc].getImage(), x+col*tileSize,
							y+row*tileSize, null);
			}
		}
	}
	
	// getter for whether the tile at (col,row) is blocked
	public boolean isBlocked(int col, int row) {
		int rc = tempMap[row][col];
		return tiles[rc].isBlocked();
	}
	
	public boolean isSpawnable(int col, int row){
		int rc = tempMap[row][col];
		return tiles[rc].isSpawnable();
	}
	
	public boolean hasItem(int col, int row){
		int rc = tempMap[row][col];
		return tiles[rc].hasItem();
	}
	
	// getter for tileSize
	public int getTileSize(){
		return this.tileSize;
	}

	// setter for X
	public void setX(double x){
		this.x = (int) x;
	}
	
	// setter for Y
	public void setY(double y){
		this.y = (int) y;
	}
	
	// getter for X
	public int getX() {
		return this.x;
	}

	// getter for Y
	public int getY() {
		return this.y;
	}
	// getter for col tile index
	public int getColTileIndex(int x){
		return x / tileSize;
	}
	
	// getter for row tile index
	public int getRowTileIndex(int y){
		return y / tileSize;
	}
	
	// getter for tile at (x,y)
	public int getTile(int x, int y){
		return tempMap[y][x];
	}
	
	//initializes all lists currently stored in the trainer
	public void initCollections() {
		commonCollection = new ArrayList<CommonPokemon>();
		uncommonCollection = new ArrayList<UncommonPokemon>();
		rareCollection = new ArrayList<RarePokemon>();

		// Placeholder Pokemon spawner for now
		commonCollection.add(new Abra(63));
		commonCollection.add(new Drowzee(96));
		commonCollection.add(new Grimer(88));
		commonCollection.add(new Pidgey(16));
		commonCollection.add(new Pikachu(25));
		commonCollection.add(new Staryu(120));

		uncommonCollection.add(new Graveler(75));
		uncommonCollection.add(new Haunter(93));
		uncommonCollection.add(new Rapidash(78));

		rareCollection.add(new Dragonair(148));
	}

	public void removeItem(int currRow, int currCol) {
		int rc = tempMap[currRow][currCol];
		if(rc == 4 || rc == 5){
			tempMap[currRow][currCol] = 1;
		}
	}

}
