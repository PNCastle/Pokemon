/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Pokemon.java
 * Purpose: The main Pokemon inheritance hierarchy, which includes all operations
 * relating to all Pokemon, regardless of type or rarity, and calculates all
 * probabilites (since these are independent of rarity). Also includes setters
 * and getters for various Pokemon stats during battle.
 */

package Model;

import java.util.Random;

public abstract class Pokemon {
	private int catchProbability, catchRate, hp, runProbability, maxHP, speed;
	private double encounterRate;
	private String name, type, pokePicName, info;
	
	private boolean isEating, isAngry, willRun;
	
	public Pokemon(String name, int hp, int catchRate, int speed, double encounterRate,
			String type, String pokePicName, String info) {
		this.name = name;
		this.hp = hp;
		this.maxHP = hp;
		this.speed = speed;
		this.catchRate = catchRate;
		this.encounterRate = encounterRate;
		this.type = type;
		this.pokePicName = pokePicName;
		this.willRun = false;
		pokemonRun();
		calcCatchProbability();
	}
	
	public String getName() {
		return name;
	}
	
	public String getInfo() {
		return info;
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
	
	public int getCatchProbability() {
		return catchProbability;
	}
	
	public boolean willRun() {
		return willRun;
	}
	
	// Uses an item and runs the methods to determine the state of the Pokemon
	// during battle
	public void useItem(Item item) {
		willRun = pokemonRun();
		
		if (item.getClass() == items.SafariBall.class){
			calcCatchProbability();
			isEating = false;
			isAngry = false;
			return;
		}
		
		if (!isEating && !isAngry) {
			if (item.getClass() == items.Rock.class){
				isAngry = true;
			}
			else if (item.getClass() == items.Bait.class){
				isEating = true;
			}
		}
		
		if (isEating && !isAngry){
			if (item.getClass() == items.Rock.class){
				isEating = false;
				isAngry = true;
			}
			else if (item.getClass() == items.Bait.class){
				isEating = true;
				isAngry = false;
			}
			else {
				isEating = false;
			}
		}
		
		if (!isEating && isAngry){
			if (item.getClass() == items.Bait.class){
				isEating = true;
				isAngry = false;
			}
			else if (item.getClass() == items.Rock.class){
				isEating = false;
				isAngry = true;
			}
			else {
				isAngry = false;
			}
		}
		
		setCatchRate(item.getCatchModifier());
		setHP(item.hpModifier());
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
	public boolean pokemonRun() {
		if (!isEating && !isAngry){
			runProbability = (speed % 256) * 2;
		}
		else if (isAngry){
			runProbability = (speed % 256) * 4;
			if (runProbability > 255){
				runProbability = 255;
			}
		}
		else if (isEating){
			runProbability = (speed % 256) * 2;
			runProbability /= 4;
		}
		
		
		Random random = new Random();
		if (random.nextInt(256) < runProbability && !isAngry){
			return true;
		}
		if (random.nextInt(320) < runProbability && isAngry){
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getEncounterRate() {
		return encounterRate;
	}
	
	public abstract int getPokemonID();
}
