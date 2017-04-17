package items;

import Model.Item;

public class Bait extends Item {

	private String name = "Bait";
	
	// Placeholder values, to determine later
	public Bait() {
		super(true, 3, -4);
	}
	
	@Override
	public String toString() {
		return name;
	}
}
