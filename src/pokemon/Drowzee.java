package pokemon;

import Model.CommonPokemon;

public class Drowzee extends CommonPokemon {
	private String name = "Drowzee";
	private int pokemonID;
	
	public Drowzee (int pokemonID) {
		super(60, 190);
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
