/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Item.java
 * Purpose: An inheritance hierarchy for all Items used in the Safari Zone, which
 * includes in-battle objects (like rocks) and out of battle objects (like potions)
 */

package Model;

public abstract class Item {
	private boolean isThrowable;
	private int amount, hpModifier, catchModifier;
	
	private String name;
	
	public Item(boolean isThrowable, int amount, int hpModifier, int catchModifier, String name) {
		this.amount = amount;
		this.isThrowable = isThrowable;
		this.hpModifier = hpModifier;
		this.catchModifier = catchModifier;
		
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	// Determines if item can be thrown (like potions can't, but rocks can)
	public boolean isThrowable() {
		return isThrowable;
	}
	
	// Gets how the item affects HP
	public int hpModifier() {
		return hpModifier;
	}
	
	// Uses one of the item (-1 indicates unlimited usage, for rocks and bait)
	public boolean useOne() {
		if (amount == 0){
			return false;
		}
		
		if (amount == -1){
			return true;
		}
		
		amount--;
		return true;
	}
	
	public void addOne(){
		amount++;
	}
	
	public int amount() {
		return amount;
	}
	
	public int getCatchModifier() {
		return catchModifier;
	}
	
	public abstract String toString();
}
