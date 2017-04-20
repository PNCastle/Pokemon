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

import Model.UncommonPokemon;

public class Graveler extends UncommonPokemon {
	private static String name = "Graveler";
	private int pokemonID;

	public Graveler(int pokemonID) {
		// hp = 55, catchRate = 120
		super(name, 55, 120);
		// pokemonID = 75
		this.pokemonID = pokemonID;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
