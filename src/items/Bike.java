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
