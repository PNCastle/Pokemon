package items;

import Model.Item;

public class Rock extends Item {

	private String name = "Rock";
	
	// Placeholder values, to determine later
	public Rock() {
		super(true, -1, -2, 5);
	}

	@Override
	public String toString() {
		return name;
	}
}
