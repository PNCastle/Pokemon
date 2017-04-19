package pokemon;

import Model.CommonPokemon;

public class Abra extends CommonPokemon{
	private static String name = "Abra";
	private int pokemonID;
	
	public Abra(int pokemonID) {
		super(name, 25, 200);
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