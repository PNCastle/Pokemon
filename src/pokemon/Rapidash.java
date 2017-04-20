package pokemon;

import Model.UncommonPokemon;

public class Rapidash extends UncommonPokemon {
	private static String name = "Rapidash";
	private int pokemonID;

	public Rapidash (int pokemonID) {
		super(name, 65, 60);
		this.pokemonID = pokemonID;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
