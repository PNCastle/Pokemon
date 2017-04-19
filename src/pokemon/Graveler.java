package pokemon;

import Model.UncommonPokemon;

public class Graveler extends UncommonPokemon {
	private static String name = "Graveler";
	private int pokemonID;

	public Graveler(int pokemonID) {
		super(name, 55, 120);
		this.pokemonID = pokemonID;
	}

	public String getName() {
		return name;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
