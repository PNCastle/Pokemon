/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: CommonPokemon.java
 * Purpose: CommonPokemon which inherits from the abstract class Pokemon, and
 * serves as the abstract class that all common-ranked Pokemon inherit from
 * (with an encounter rate of 0.5, most common)
 */

package Model;

public abstract class CommonPokemon extends Pokemon {

	public CommonPokemon(String name, int hp, int catchRate) {
		super(name, hp, catchRate, 0.5);
	}

}
