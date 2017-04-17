package pokemon;

import Model.CommonPokemon;

public class Grimer extends CommonPokemon {
	private String name = "Grimer";
	private int pokemonID;
	
	public Grimer(int pokemonID) {
		super(80, 190);
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
