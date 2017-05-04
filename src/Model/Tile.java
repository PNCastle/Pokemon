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
	private boolean spawnable;
	private boolean hasItem;
	private boolean hasBike;
	//ctor
	public Tile (BufferedImage image, boolean blocked, boolean spawnable, boolean hasItem, boolean hasBike) {
		this.image = image;
		this.blocked = blocked;
		this.spawnable = spawnable;
		this.hasItem = hasItem;
		this.hasBike = hasBike;
	}
	
	// a getter for the image of this tile
	public BufferedImage getImage() {
		return this.image;
	}
	
	// a getter for whether this tile is blocked
	public boolean isBlocked() {
		return this.blocked;
	}

	// a getter for whether this tile is spawnable
	public boolean isSpawnable() {
		return this.spawnable;
	}

	// a getter for whether this item contains an item
	public boolean hasItem() {
		return this.hasItem;
	}

	// a getter for whether this item contains a bike
	public boolean hasBike() {
		return this.hasBike;
	}

}
