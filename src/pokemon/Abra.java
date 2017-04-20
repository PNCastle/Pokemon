/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Abra.java
 * Purpose: A Pokemon which inherits the CommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 */

package pokemon;

import Model.CommonPokemon;

public class Abra extends CommonPokemon{
	private static String name = "Abra";
	private int pokemonID;
	
	public Abra(int pokemonID) {
		super(name, 25, 200);
		this.pokemonID = pokemonID;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}