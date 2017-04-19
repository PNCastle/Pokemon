package pokemon;

import Model.RarePokemon;

public class Dragonair extends RarePokemon{
	private static String name = "Dragonair";
	private int pokemonID;

	public Dragonair(int pokemonID) {
		super(name, 61, 45);
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
