package pokemon;

import Model.CommonPokemon;

public class Pikachu extends CommonPokemon {
	private String name = "Pikachu";
	private int pokemonID;
	
	public Pikachu(int pokemonID) {
		super(35, 190);
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
