/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Grimer.java
 * Purpose: A Pokemon which inherits the CommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import Model.CommonPokemon;

public class Grimer extends CommonPokemon {
	private static String name = "Grimer";
	private String type = "Poison";
	private String info = "Wherever GRIMER has passed, so many germs are left behind \nthat no plants will ever grow again.";
	private int pokemonID = 88;
	private int pokemonSP = 25;
	private String pokePicName = "pokePic/Grimer.gif";
	
	public Grimer(int pokemonID) {
		// hp = 80, catchRate = 190
		super(name, 80, 190);
		this.pokemonID = pokemonID;
	}	
	
	public String getType() {
		return type;
	}
	
	public String getInfo() {
		return info;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public int getPokemonSP() {
		return pokemonSP;
	}

	public String getPicFileName() {
		return pokePicName;
	}
	
	public String toString() {
		return "Name:" + name 
				+ "Type: " + type
				+ "Pokédex entry: " + info
				+ "PokemonID: " + pokemonID 
				+ "Speed: " + pokemonSP;
	}
}
