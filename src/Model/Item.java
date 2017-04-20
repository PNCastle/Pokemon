package Model;

public abstract class Item {
	private boolean isThrowable;
	private int amount, hpModifier, catchModifier;
	
	public Item(boolean isThrowable, int amount, int hpModifier, int catchModifier) {
		this.amount = amount;
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
	
	public int amount() {
		return amount;
	}
	
	public int getCatchModifier() {
		return catchModifier;
	}
	
	public abstract String toString();
}
