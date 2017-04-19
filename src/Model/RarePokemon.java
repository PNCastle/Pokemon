package Model;

public abstract class RarePokemon extends Pokemon {
	
	public RarePokemon(String name, int hp, int catchRate) {
		super(name, hp, catchRate, 0.75);
	}

}
