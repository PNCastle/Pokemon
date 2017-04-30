/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Dragonair.java
 * Purpose: A Pokemon which inherits the RarePokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import Model.RarePokemon;

public class Dragonair extends RarePokemon{
	private static String name = "Dragonair";
	private static String type = "Dragon";
	private static String info = "<html>Its crystalline orbs appear to give this POKéMON<br>the power to freely control the weather.</html>";
	private int pokemonID = 148;
	private int pokemonSP = 70;
	private static String pokePicName = "pokePic/Dragonair.gif";

	public Dragonair(int pokemonID) {
		// hp = 61, catchRate = 45
		super(name, 61, 45, 70, type, pokePicName, info);
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
