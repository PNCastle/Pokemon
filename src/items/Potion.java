/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Potion.java
 * Purpose: An item that inherits the Item hierarchy, by providing its own values
 * for each of the stats that the hierarchy requires, and how many the Trainer
 * starts out with (-1 for infinite)
 */

package items;

import java.io.Serializable;

import Model.Item;

public class Potion extends Item implements Serializable {

	private static String name = "Potion";
	
	public Potion() {
		super(false, 1000, 15, 0, name);
	}
	
	@Override
	public String toString() {
		return name;
	}

}
