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

	private Map map;

	public Trainer(Map map) {

		this.map = map;

		this.width = 50;
		this.height = 50;

		this.dx = 0;
		this.dy = 0;
		moveSpeed = .5;
		maxSpeed = 4;
		stopSpeed = .1;

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
		} else {
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

		int currCol = map.getColTile((int) x);
		int currRow = map.getRowTile((int) y);

		double to_x = x + dx;
		double to_y = y + dy;

		// collision check here

		x = to_x;
		y = to_y;

	}

	public void draw(Graphics2D g) {
		int tileX = map.getX();
		int tileY = map.getY();

		g.setColor(Color.RED);
		g.fillRect((int) (tileX + x - width / 2), (int) (tileY + y - height / 2), width, height);
	}

}
