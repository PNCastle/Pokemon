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

import Model.CommonPokemon;

public class Staryu extends CommonPokemon{
	private static String name = "Staryu";
	private String type = "Water";
	private String info = "Even if its body is torn, \nit can regenerate as long as the glowing central core remains intact.";
	private int pokemonID = 120;
	private int pokemonSP = 85;
	
	public Staryu (int pokemonID) {
		// hp = 30, catchRate = 225
		super(name, 30, 225);
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
	
	public String toString() {
		return "Name:" + name 
				+ "Type: " + type
				+ "Pokédex entry: " + info
				+ "PokemonID: " + pokemonID 
				+ "Speed: " + pokemonSP;
	}
}
