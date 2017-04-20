package items;

import Model.Item;

public class Potion extends Item {

	private String name = "Potion";
	
	public Potion() {
		super(true, 2, 15, 0);
	}
	
	@Override
	public String toString() {
		return name;
	}

}