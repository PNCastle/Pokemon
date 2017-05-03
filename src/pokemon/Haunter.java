/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Haunter.java
 * Purpose: A Pokemon which inherits the UnommonPokemon hierarchy, and implements
 * any abstract methods from the main Pokemon abstract class
 * Rare Pokemon (1 ~ 50)
 * Uncommon Pokemon (51 ~ 150)
 * Common Pokemon (151 ~ 255)
 */

package pokemon;

import java.io.Serializable;

import Model.UncommonPokemon;

public class Haunter extends UncommonPokemon implements Serializable {
	private static String name = "Haunter";
	private static String type = "Ghost&Poison";
	private static String info = "<html>Its tongue is made of gas.<br>If licked, \nits victim starts shaking constantly until<br>death eventually comes.</html>";
	private int pokemonID = 93;
	private int pokemonSP = 95;
	private static String pokePicName = "pokePic/Haunter.gif";
	
	public Haunter (int pokemonID) {
		// hp = 45, catchRate = 90
		super(name, 45, 90, 95, type, pokePicName, info);
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
