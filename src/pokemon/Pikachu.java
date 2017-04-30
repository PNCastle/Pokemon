/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Pikachu.java
 * Purpose: A Pokemon which inherits the CommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import Model.CommonPokemon;

public class Pikachu extends CommonPokemon {
	private static String name = "Pikachu";
	private static String type = "Electric";
	private static String info = "<html>It raises its tail to check its surroundings.<br>The tail is sometimes struck by lightning in this<br>pose.</html>";
	private int pokemonID = 25;
	private int pokemonSP = 90;
	private static String pokePicName = "pokePic/Pikachu.gif";
	
	public Pikachu(int pokemonID) {
		// hp = 35, catchRate = 190
		super(name, 35, 190, 90, type, pokePicName, info);
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

	public String getPicFileName() {
		return pokePicName;
	}
	
	public String toString() {
		return "Name:" + name 
				+ "Type: " + type
				+ "Pokédex entry: " + info
				+ "PokemonID: " + pokemonID 
				+ "Speed: " + pokemonSP;
	}
}