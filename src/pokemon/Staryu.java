package pokemon;

import Model.CommonPokemon;

public class Staryu extends CommonPokemon{
	private String name = "Staryu";
	private int pokemonID;
	
	public Staryu (int pokemonID) {
		super(30, 225);
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
