package pokemon;

import Model.CommonPokemon;

public class Pidgey extends CommonPokemon{
	private String name = "Pidgey";
	private int pokemonID;

	public Pidgey(int pokemonID) {
		super(40, 255);
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