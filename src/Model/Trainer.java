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
//	private double stopSpeed;
	
	private Map map;
	
	public Trainer(Map map) {
		
		this.map = map;
		
		this.width = 20;
		this.height = 20;
		
		moveSpeed = .5;
		maxSpeed = 4;
		
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
		
	}
	
	public void draw(Graphics2D g) {
		int tileX = map.getX();
		int tileY = map.getY();
		
		g.setColor(Color.RED);
		g.fillRect(
				(int) (tileX + x - width/2),
				(int) (tileY + y - height/2),
				width,
				height
		);
	}
	
}
