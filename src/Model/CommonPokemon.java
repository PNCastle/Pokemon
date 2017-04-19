package Model;

public abstract class CommonPokemon extends Pokemon {

	public CommonPokemon(String name, int hp, int catchRate) {
		super(name, hp, catchRate, 0.5);
	}

}
