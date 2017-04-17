package Model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Trainer {

	private double x;
	private double y;
	private double dx;
	private double dy;

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

	public Trainer(Map map) {

		this.map = map;

		this.width = 50;
		this.height = 50;

		this.x = 300;
		this.y = 300;
		this.dx = 0;
		this.dy = 0;
		moveSpeed = .5;
		maxSpeed = 2.5;
		stopSpeed = .25;
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

		else {

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
		}

		// GOOD UP TO THIS POINT NOW ITS BROKEN

		int currCol = map.getColTileIndex((int) x);
		int currRow = map.getRowTileIndex((int) y);

		double to_x = x + dx;
		double to_y = y + dy;

		// collision check here
		double temp_x = x;
		double temp_y = y;
		calculateNeighbors(to_x, to_y);
		if (dy < 0) {
			if (topLeft || top || topRight) {
				dy = 0;
				temp_y = currRow * map.getTileSize() + height / 2;
			} 
			else {
				temp_y += dy;
			}
		}
		if (dy > 0) {
			if (bottomLeft || bottom || bottomRight) {
				dy = 0;
				temp_y = (currRow + 1) * map.getTileSize() - height / 2;
			} 
			else {
				temp_y += dy;
			}
		}
		// calculateNeighbors(to_x, y);
		if (dx < 0) {
			if (topLeft || left || bottomLeft) {
				dx = 0;
				temp_x = currCol * map.getTileSize() + width / 2;
			} 
			else {
				temp_x += dx * (1.25);
			}
		}
		if (dx > 0) {
			if (topRight || right || bottomRight) {
				dx = 0;
				temp_x = (currCol + 1) * map.getTileSize() - width / 2;
			} 
			else {
				temp_x += dx * (1.25);
			}

		}

		
		x = temp_x;
		y = temp_y;

		// hardcoded dimensions of MapPanel
		map.setX(750 / 2 - x);
		map.setY(550 / 2 - y);

	}

	private void calculateNeighbors(double y, double x) {
		int rowIndex = map.getRowTileIndex((int) y);
		int colIndex = map.getColTileIndex((int) x);
		int leftIndex = map.getColTileIndex((int) (x - width / 2));
		int rightIndex = map.getColTileIndex((int) (x + width / 2) - 1);
		int topIndex = map.getColTileIndex((int) (y - height / 2));
		int bottomIndex = map.getColTileIndex((int) (y + height / 2) - 1);

		left = (map.getTile(rowIndex, leftIndex) == 0);
		right = (map.getTile(rowIndex, rightIndex) == 0);

		top = (map.getTile(topIndex, colIndex) == 0);
		bottom = (map.getTile(bottomIndex, colIndex) == 0);

		topLeft = (map.getTile(topIndex, leftIndex) == 0);
		topRight = (map.getTile(topIndex, rightIndex) == 0);

		bottomLeft = (map.getTile(bottomIndex, leftIndex) == 0);
		bottomRight = (map.getTile(bottomIndex, rightIndex) == 0);
	}

	public void draw(Graphics2D g) {
		int tileX = map.getX();
		int tileY = map.getY();

		g.setColor(Color.RED);
		g.fillRect((int) (tileX + x - width / 2), (int) (tileY + y - height / 2), width, height);
	}

}
