package items;

import Model.Item;

public class SafariBall extends Item {

	private String name = "Safari Ball";
	
	public SafariBall() {
		super(true, 30, 0, 0);
	}
	
	@Override
	public String toString() {
		return name;
	}
}
