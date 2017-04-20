package Model;

public abstract class Pokemon {
	private int catchProbability, catchRate, hp, runProbability, maxHP;
	private double encounterRate;
	private String name;
	
	private int eatingCounter, angryCounter;
	
	public Pokemon(String name, int hp, int catchRate, double encounterRate) {
		this.name = name;
		this.hp = hp;
		this.maxHP = hp;
		this.catchRate = catchRate;
		this.encounterRate = encounterRate;
		calcRunProbability();
		calcCatchProbability();
	}
	
	public String getName() {
		return name;
	}

	public int getHP() {
		return hp;
	}
	
	public void setHP(int hpModifier){
		hp += hpModifier;
		
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
