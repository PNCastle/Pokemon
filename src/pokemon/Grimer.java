package pokemon;

import Model.CommonPokemon;

public class Grimer extends CommonPokemon {
	private static String name = "Grimer";
	private int pokemonID;
	
	public Grimer(int pokemonID) {
		super(name, 80, 190);
		this.pokemonID = pokemonID;
	}	
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
