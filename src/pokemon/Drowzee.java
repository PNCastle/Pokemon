/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Drowzee.java
 * Purpose: A Pokemon which inherits the CommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 */

package pokemon;

import Model.CommonPokemon;

public class Drowzee extends CommonPokemon {
	private static String name = "Drowzee";
	private int pokemonID;
	
	public Drowzee (int pokemonID) {
		super(name, 60, 190);
		this.pokemonID = pokemonID;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
