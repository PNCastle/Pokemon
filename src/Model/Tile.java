/*
 * Authors:  Paul Castleberry, Angel Burr, Sohyun Kim, Isaac Kim
 * Filename: Tile.java
 * Purpose:  Individual tiles for our map that contain an image
 *           and some booleans for blocked and spawnable tiles
 */

package Model;

import java.awt.image.BufferedImage;

public class Tile {
	
	// instance variables
	private BufferedImage image;
	private boolean blocked;
	//private boolean spawnable;
	
	//ctor
	public Tile (BufferedImage image, boolean blocked) {
		this.image = image;
		this.blocked = blocked;
	}
	
	// a getter for the image of this tile
	public BufferedImage getImage() {
		return this.image;
	}
	
	// a getter for whether this tile is blocked
	public boolean isBlocked() {
		return this.blocked;
	}

}
