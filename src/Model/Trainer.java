package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import items.*;
import pokemon.*;

public class Trainer {

	/*
	 * Movement variables here
	 */
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private int prevRow, currRow, prevCol, currCol;

	private int width;
	private int height;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private double moveSpeed;
	private double maxSpeed;
	private double stopSpeed;

	private boolean top, bottom, midLeft, midRight, topLeft, topRight, bottomLeft, bottomRight;

	private Map map;

	/*
	 * End movement variables
	 */

	/*
	 * Hierarchy variables here
	 */

	private int stepsTaken;
	private boolean gameOver;

	private ArrayList<Pokemon> pokeDex;
	private ArrayList<CommonPokemon> commonCollection;
	private ArrayList<UncommonPokemon> uncommonCollection;
	private ArrayList<RarePokemon> rareCollection;
	private ArrayList<Item> items;

	/*
	 * End hierarchy variables
	 */

	public Trainer(Map map) {

		this.map = map;

		this.width = 50;
		this.height = 50;

		this.x = 78/2  *  width;
		this.y = 110/2 * height;
		this.dx = 0;
		this.dy = 0;
		moveSpeed = 1;
		maxSpeed = 4;
		stopSpeed = .5;

		currRow = map.getRowTileIndex((int) y);
		currCol = map.getColTileIndex((int) x);
		stepsTaken = 0;
		gameOver = false;

		initCollections();

	}

	public void initCollections() {
		pokeDex = new ArrayList<Pokemon>();
		commonCollection = new ArrayList<CommonPokemon>();
		uncommonCollection = new ArrayList<UncommonPokemon>();
		rareCollection = new ArrayList<RarePokemon>();
		items = new ArrayList<Item>();

		items.add(new SafariBall());
		items.add(new Rock());
		items.add(new Bait());

		// Testing purposes?
		pokeDex.add(new Pikachu(4));

		// Placeholder Pokemon spawner for now
		commonCollection.add(new Abra(0));
		commonCollection.add(new Drowzee(1));
		commonCollection.add(new Grimer(2));
		commonCollection.add(new Pidgey(3));
		commonCollection.add(new Pikachu(4));
		commonCollection.add(new Staryu(5));

		uncommonCollection.add(new Graveler(6));
		uncommonCollection.add(new Haunter(7));
		uncommonCollection.add(new Rapidash(8));

		rareCollection.add(new Dragonair(9));
	}

	public void setLeft(boolean b) {
		this.left = b;
	}

	public void setRight(boolean b) {
		this.right = b;
	}

	public void setUp(boolean b) {
		this.up = b;
	}

	public void setDown(boolean b) {
		this.down = b;
	}

	public void update() {
		// determine next position

		if (left) { // moving left
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) { // moving right
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else if (up) { // moving up
			dy -= moveSpeed;
			if (dy < -maxSpeed) {
				dy = -maxSpeed;
			}
		} else if (down) { // moving down
			dy += moveSpeed;
			if (dy > maxSpeed) {
				dy = maxSpeed;
			}
		}

		// stopping
		if (dx != 0) { // stopping in x direction
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}
		if (dy != 0) { // stopping in y direction
			if (dy > 0) {
				dy -= stopSpeed;
				if (dy < 0) {
					dy = 0;
				}
			} else {
				dy += stopSpeed;
				if (dy > 0) {
					dy = 0;
				}
			}
		}

		// update steps taken
		prevRow = currRow;
		prevCol = currCol;
		currCol = map.getColTileIndex((int) x);
		currRow = map.getRowTileIndex((int) y);
		if(prevRow != currRow){
			stepsTaken++;
			System.out.println("Steps taken " + this.getStepCount());
		}
		if(prevCol != currCol){
			stepsTaken++;
			System.out.println("Steps taken " + this.getStepCount());
		}

		// collision check here
		double to_x = x + dx;
		double to_y = y + dy;

		double temp_x = x;
		double temp_y = y;
		calculateNeighbors(to_x, to_y);
		if (dy < 0) {
			if (topLeft || top || topRight) {
				dy = 0;
				temp_y = currRow * map.getTileSize() + height / 2;
			} else {
				temp_y += dy;
			}
		}
		if (dy > 0) {
			if (bottomLeft || bottom || bottomRight) {
				dy = 0;
				temp_y = (currRow + 1) * map.getTileSize() - height / 2;
			} else {
				temp_y += dy;
			}
		}
		// calculateNeighbors(to_x, y);
		if (dx < 0) {
			if (topLeft || left || bottomLeft) {
				dx = 0;
				temp_x = currCol * map.getTileSize() + width / 2;
			} else {
				temp_x += dx * (1.36);
			}
		}
		if (dx > 0) {
			if (topRight || right || bottomRight) {
				dx = 0;
				temp_x = (currCol + 1) * map.getTileSize() - width / 2;
			} else {
				temp_x += dx * (1.36);
			}

		}

		x = temp_x;
		y = temp_y;

		// hardcoded dimensions of MapPanel
		// this keeps player centered at all times
		map.setX(750 / 2 - x);
		map.setY(550 / 2 - y);

		//stepsTaken++;
		checkWinConditions();
	}

	private void checkWinConditions() {
		if (stepsTaken == 500) {
			gameOver = true;
		} else if (pokeDex.size() == 10) {
			gameOver = true;
		}
	}

	private void calculateNeighbors(double y, double x) {
		int rowIndex = map.getRowTileIndex((int) y);
		int colIndex = map.getColTileIndex((int) x);
		int leftIndex = map.getColTileIndex((int) (x - width / 2));
		int rightIndex = map.getColTileIndex((int) (x + width / 2) - 1);
		int topIndex = map.getColTileIndex((int) (y - height / 2));
		int bottomIndex = map.getColTileIndex((int) (y + height / 2) - 1);

		left = (map.isBlocked(rowIndex, leftIndex)); // == 0);
		right = (map.isBlocked(rowIndex, rightIndex)); // == 0);

		top = (map.isBlocked(topIndex, colIndex)); // == 0);
		bottom = (map.isBlocked(bottomIndex, colIndex)); // == 0);

		topLeft = (map.isBlocked(topIndex, leftIndex)); // == 0);
		topRight = (map.isBlocked(topIndex, rightIndex)); // == 0);

		bottomLeft = (map.isBlocked(bottomIndex, leftIndex)); // == 0);
		bottomRight = (map.isBlocked(bottomIndex, rightIndex)); // == 0);
	}

	public void draw(Graphics2D g) {
		int tileX = map.getX();
		int tileY = map.getY();

		//g.setColor(Color.BLACK);
		//g.fillRect(tileX + 600, tileY + 50, 100, 50);
		//g.drawString("Number of steps taken: " + this.getStepCount(), tileX + 600, tileY + 52);
		
		g.setColor(Color.RED);
		g.fillRect((int) (tileX + x - width / 2), (int) (tileY + y - height / 2), width, height);
		


	}

	public int getStepCount() {
		return this.stepsTaken;
	}

}