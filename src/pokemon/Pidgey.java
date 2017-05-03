/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Pidgey.java
 * Purpose: A Pokemon which inherits the CommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import java.io.Serializable;

import Model.CommonPokemon;

public class Pidgey extends CommonPokemon implements Serializable {
	private static String name = "Pidgey";
	private static String type = "Normal&Flying";
	private static String info = "<html>Common in grassy areas and forests,<br>it is very docile and will chase off enemies by<br>flapping up sand.</html>";
	private int pokemonID = 16;
	private int pokemonSP = 56;
	private static String pokePicName = "pokePic/Pidgey.gif";

	public Pidgey(int pokemonID) {
		// hp = 40, catchRate = 255
		super(name, 40, 255, 56, type, pokePicName, info);
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