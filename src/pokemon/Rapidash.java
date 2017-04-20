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
	private int pokemonID;

	public Rapidash (int pokemonID) {
		// hp = 65, catchRate = 60
		super(name, 65, 60);
		// pokemonID = 78
		this.pokemonID = pokemonID;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
