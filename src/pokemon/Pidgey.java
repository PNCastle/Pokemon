/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Pidgey.java
 * Purpose: A Pokemon which inherits the CommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 */

package pokemon;

import Model.CommonPokemon;

public class Pidgey extends CommonPokemon{
	private static String name = "Pidgey";
	private int pokemonID;

	public Pidgey(int pokemonID) {
		super(name, 40, 255);
		this.pokemonID = pokemonID;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}