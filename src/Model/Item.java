package Model;

public abstract class Item {
	private boolean isThrowable;
	private int hpModifier, catchModifier;
	
	public Item(boolean isThrowable, int hpModifier, int catchModifier) {
		this.isThrowable = isThrowable;
		this.hpModifier = hpModifier;
		this.catchModifier = catchModifier;
	}
	
	public boolean isThrowable() {
		return isThrowable;
	}
	
	public int hpModifier() {
		return hpModifier;
	}
	
	public int getCatchModifier() {
		return catchModifier;
	}
	
	public abstract String toString();
}
