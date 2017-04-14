package model;

public abstract class Pokemon {
	private int hp;
	private double encounterRate;
	
	public Pokemon(int hp, double encounterRate) {
		this.hp = hp;
		this.encounterRate = encounterRate;
	}
	
	public int getHP() {
		return hp;
	}
	
	public double getEncounterRate() {
		return encounterRate;
	}
	
	public abstract double runChance();
	
}
