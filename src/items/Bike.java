/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Bike.java
 * Purpose: An item that can be found on the map and used with 'B'
 * 			increases the trainers movement speed and makes it so 
 * 			that pokemon can be avoided while traversing bushes
 */
package items;

import Model.Item;

public class Bike extends Item {

	private static String name = "Bike";
	
	public Bike() {
		super(false, 0, 0, 0, name);
	}

	@Override
	public String toString() {
		return name;
	}

}
