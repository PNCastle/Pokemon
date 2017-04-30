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
	private static String type = "Poison";
	private static String info = "<html>Wherever GRIMER has passed,<br>so many germs are left behind that no plants<br>will ever grow again.</html>";
	private int pokemonID = 88;
	private int pokemonSP = 25;
	private static String pokePicName = "pokePic/Grimer.gif";
	
	public Grimer(int pokemonID) {
		// hp = 80, catchRate = 190
		super(name, 80, 190, 25, type, pokePicName, info);
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
