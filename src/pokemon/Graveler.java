/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Graveler.java
 * Purpose: A Pokemon which inherits the UncommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import java.io.Serializable;

import Model.UncommonPokemon;

public class Graveler extends UncommonPokemon implements Serializable {
	private static String name = "Graveler";
	private static String type = "Rock&Ground";
	private static String info = "<html>A slow walker, it rolls to move.<br>It pays no attention to any object that happens<br>to be in its path.</html>";
	private int pokemonID = 75;
	private int pokemonSP = 35;
	private static String pokePicName = "pokePic/Graveler.gif";

	public Graveler(int pokemonID) {
		// hp = 55, catchRate = 120
		super(name, 55, 120, 35, type, pokePicName, info);
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
