package pokemon;

import Model.UncommonPokemon;

public class Rapidash extends UncommonPokemon {
	private String name = "Rapidash";
	private int pokemonID;

	public Rapidash (int pokemonID) {
		super(65, 60);
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