package pokemon;

import Model.CommonPokemon;

public class Pikachu extends CommonPokemon {
	private static String name = "Pikachu";
	private int pokemonID;
	
	public Pikachu(int pokemonID) {
		super(name, 35, 190);
		this.pokemonID = pokemonID;
	}
	
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
