/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Grimer.java
 * Purpose: A Pokemon which inherits the CommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 */

package pokemon;

import Model.CommonPokemon;

public class Grimer extends CommonPokemon {
	private static String name = "Grimer";
	private int pokemonID;
	
	public Grimer(int pokemonID) {
		super(name, 80, 190);
		this.pokemonID = pokemonID;
	}	
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
