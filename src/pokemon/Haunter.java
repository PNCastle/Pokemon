/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Haunter.java
 * Purpose: A Pokemon which inherits the UnommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 */

package pokemon;

import Model.UncommonPokemon;

public class Haunter extends UncommonPokemon {
	private static String name = "Haunter";
	private int pokemonID;
	
	public Haunter (int pokemonID) {
		super(name, 45, 90);
		this.pokemonID = pokemonID;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
