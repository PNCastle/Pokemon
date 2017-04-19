package Model;

public abstract class RarePokemon extends Pokemon {
	
	public RarePokemon(int hp, int catchRate) {
		super(hp, catchRate, 0.75);
	}

}
