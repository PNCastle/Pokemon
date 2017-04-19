package Model;

public abstract class Pokemon {
	private int catchProbability, catchRate, hp, runProbability;
	private double encounterRate;
	private String name;
	
	private int eatingCounter, angryCounter;
	
	public Pokemon(String name, int hp, int catchRate, double encounterRate) {
		this.name = name;
		this.hp = hp;
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
	
	/**
	 * Formula from https://www.dragonflycave.com/mechanics/gen-i-safari-zone
	 */
	public void calcCatchProbability() {
		catchProbability = (Math.min(catchRate + 1, 151) * Math.min(255, 
				((hp * 255) / 12) / Math.max(1, hp / 4) + 1) / 256) / 151; 
	}
	
	public void calcRunProbability() {
		runProbability = 0;
		/**
		 * Placeholder, need to implement Speed stat for each pokemon
		 */
	}
	
	public double getEncounterRate() {
		return encounterRate;
	}
	
}
