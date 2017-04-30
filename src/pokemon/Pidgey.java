/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Pidgey.java
 * Purpose: A Pokemon which inherits the CommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import Model.CommonPokemon;

public class Pidgey extends CommonPokemon{
	private static String name = "Pidgey";
	private String type = "Normal & Flying";
	private String info = "Common in grassy areas and forests, \nit is very docile and will chase off enemies by flapping up sand.";
	private int pokemonID = 16;
	private int pokemonSP = 56;

	public Pidgey(int pokemonID) {
		// hp = 40, catchRate = 255
		super(name, 40, 255);
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