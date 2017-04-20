package pokemon;

import Model.CommonPokemon;

public class Staryu extends CommonPokemon{
	private static String name = "Staryu";
	private int pokemonID;
	
	public Staryu (int pokemonID) {
		super(name, 30, 225);
		this.pokemonID = pokemonID;
	}
	
	public int getPokemonID() {
		return pokemonID;
	}
	
	public String toString() {
		return "Name:" + name + "PokemonID: " + pokemonID;
	}
}
