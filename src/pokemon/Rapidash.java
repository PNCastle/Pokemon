/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Rapidash.java
 * Purpose: A Pokemon which inherits the UnommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import Model.UncommonPokemon;

public class Rapidash extends UncommonPokemon {
	private static String name = "Rapidash";
	private static String type = "Fire";
	private String info = "With incredible acceleration, \nit reaches its top speed of 150 mph after running just ten steps.";
	private int pokemonID = 78;
	private int pokemonSP = 105;
	private static String pokePicName = "pokePic/Rapidash.gif";

	public Rapidash (int pokemonID) {
		// hp = 65, catchRate = 60
		super(name, 65, 60, 105, type, pokePicName);
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
