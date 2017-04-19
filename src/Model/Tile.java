package Model;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage image;
	private boolean blocked;
	
	public Tile (BufferedImage image, boolean blocked) {
		this.image = image;
		this.blocked = blocked;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public boolean isBlocked() {
		return this.blocked;
	}

}
