/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Dragonair.java
 * Purpose: A Pokemon which inherits the RarePokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import Model.RarePokemon;

public class Dragonair extends RarePokemon{
	private static String name = "Dragonair";
	private int pokemonID;

	public Dragonair(int pokemonID) {
		// hp = 61, catchRate = 45
		super(name, 61, 45);
		// pokemonID = 148
		this.pokemonID = pokemonID;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
