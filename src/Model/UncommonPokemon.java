package Model;

public abstract class UncommonPokemon extends Pokemon {

	public UncommonPokemon(String name, int hp, int catchRate) {
		super(name, hp, catchRate, 0.75);
	}
	
}
