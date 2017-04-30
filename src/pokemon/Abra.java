/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Abra.java
 * Purpose: A Pokemon which inherits the CommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import Model.CommonPokemon;

public class Abra extends CommonPokemon{
	private static String name = "Abra";
	private static String type = "Psychic";
	private static String info = "If it decides to TELEPORT randomly,\nit creates the illusion that it has created copies of itself.";
	private int pokemonID = 63;
	private int pokemonSP = 90;
	private static String pokePicName = "pokePic/Abra.gif";

	public Abra(int pokemonID) {
		// hp = 25, catchRate = 200
		super(name, 25, 200, 90, type, pokePicName, info);
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