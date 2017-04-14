package model;

public abstract class Pokemon {
	private int hp;
	private double catchProbability, encounterRate;
	
	public Pokemon(int hp, double encounterRate) {
		this.hp = hp;
		this.encounterRate = encounterRate;
		
	}
	
	public int getHP() {
		return hp;
	}
	
	public void setHP(int hpModifier){
		hp += hpModifier;
	}
	
	public double getCatchProbability() {
		return catchProbability;
	}
	
	public void calcCatchProbability() {
		//
	}
	
	public double getEncounterRate() {
		return encounterRate;
	}
	
}
