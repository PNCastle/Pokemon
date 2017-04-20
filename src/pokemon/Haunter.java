package pokemon;

import Model.UncommonPokemon;

public class Haunter extends UncommonPokemon {
	private static String name = "Haunter";
	private int pokemonID;
	
	public Haunter (int pokemonID) {
		super(name, 45, 90);
		this.pokemonID = pokemonID;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
