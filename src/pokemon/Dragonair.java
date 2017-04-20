/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Dragonair.java
 * Purpose: A Pokemon which inherits the RarePokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 */

package pokemon;

import Model.RarePokemon;

public class Dragonair extends RarePokemon{
	private static String name = "Dragonair";
	private int pokemonID;

	public Dragonair(int pokemonID) {
		super(name, 61, 45);
		this.pokemonID = pokemonID;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
