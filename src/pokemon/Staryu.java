/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Staryu.java
 * Purpose: A Pokemon which inherits the CommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import java.io.Serializable;

import Model.CommonPokemon;

public class Staryu extends CommonPokemon implements Serializable {
	private static String name = "Staryu";
	private static String type = "Water";
	private static String info = "<html>Even if its body is torn, it can regenerate as long<br>as the glowing central core remains intact.</html>";
	private int pokemonID = 120;
	private int pokemonSP = 85;
	private static String pokePicName = "pokePic/Staryu.gif";
	
	public Staryu (int pokemonID) {
		// hp = 30, catchRate = 225
		super(name, 30, 225, 85, type, pokePicName, info);
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
