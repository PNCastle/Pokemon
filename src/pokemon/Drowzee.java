/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Drowzee.java
 * Purpose: A Pokemon which inherits the CommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import Model.CommonPokemon;

public class Drowzee extends CommonPokemon {
	private static String name = "Drowzee";
	private String type = "Psychic";
	private String info = "It remembers every dream it eats. \nIt rarely eats the dreams of adults because children's are much tastier.";
	private int pokemonID = 96;
	private int pokemonSP = 42;
	
	public Drowzee (int pokemonID) {
		// hp = 60, catchRate = 190
		super(name, 60, 190);
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