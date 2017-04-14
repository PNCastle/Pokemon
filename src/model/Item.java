package model;

public abstract class Item {
	private boolean isThrowable;
	private int hpModifier;
	
	public Item(boolean isThrowable, int hpModifier) {
		this.isThrowable = isThrowable;
		this.hpModifier = hpModifier;
	}
	
	public boolean isThrowable() {
		return isThrowable;
	}
	
	public int hpModifier() {
		return hpModifier;
	}
	
}
