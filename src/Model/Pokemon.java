/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Pokemon.java
 * Purpose: The main Pokemon inheritance hierarchy, which includes all operations
 * relating to all Pokemon, regardless of type or rarity, and calculates all
 * probabilites (since these are independent of rarity). Also includes setters
 * and getters for various Pokemon stats during battle.
 */

package Model;

public abstract class Pokemon {
	private int catchProbability, catchRate, hp, runProbability, maxHP, speed;
	private double encounterRate;
	private String name, type, pokePicName;
	
	private int eatingCounter, angryCounter;
	
	public Pokemon(String name, int hp, int catchRate, int speed, double encounterRate,
			String type, String pokePicName) {
		this.name = name;
		this.hp = hp;
		this.maxHP = hp;
		this.speed = speed;
		this.catchRate = catchRate;
		this.encounterRate = encounterRate;
		this.type = type;
		this.pokePicName = pokePicName;
		calcRunProbability();
		calcCatchProbability();
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getPicFileName() {
		return pokePicName;
	}

	public int getHP() {
		return hp;
	}
	
	public void setHP(int hpModifier){
		hp += hpModifier;
		
		// We are implementing such that pokemon don't actually faint
		if (hp <= 0) {
			hp = 1;
		}
		
		if (hp > maxHP){
			hp = maxHP;
		}
	}
	
	public int getCatchRate() {
		return catchRate;
	}
	
	public void setCatchRate(int catchRateModifier) {
		catchRate += catchRateModifier;
	}
	
	public double getCatchProbability() {
		return catchProbability;
	}
	
	// Uses an item and runs the methods to determine the state of the Pokemon
	// during battle
	public void useItem(Item item) {
		setCatchRate(item.getCatchModifier());
		setHP(item.hpModifier());
		calcRunProbability();
		calcCatchProbability();
	}
	
	/**
	 * Formula from https://www.dragonflycave.com/mechanics/gen-i-safari-zone
	 */
	public void calcCatchProbability() {
		catchProbability = (Math.min(catchRate + 1, 151) * Math.min(255, 
				((hp * 255) / 12) / Math.max(1, hp / 4) + 1) / 256) / 151; 
	}
	
	/**
	 * Formula also from https://www.dragonflycave.com/mechanics/gen-i-safari-zone
	 */
	public void calcRunProbability() {
		runProbability = Math.max(255, (hp % 256) * 2);
	}
	
	public double getEncounterRate() {
		return encounterRate;
	}
	
	public abstract int getPokemonID();
}
