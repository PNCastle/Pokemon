package pokemon;

import Model.UncommonPokemon;

public class Haunter extends UncommonPokemon {
	private String name = "Haunter";
	private int pokemonID;
	
	public Haunter (int pokemonID) {
		super (45, 90);
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
