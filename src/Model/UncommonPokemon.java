/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: UnommonPokemon.java
 * Purpose: UnommonPokemon which inherits from the abstract class Pokemon, and
 * serves as the abstract class that all uncommonly-ranked Pokemon inherit from
 * (with an encounter rate of 0.75, for somewhat uncommon encounters)
 */

package Model;

public abstract class UncommonPokemon extends Pokemon {

	public UncommonPokemon(String name, int hp, int catchRate) {
		super(name, hp, catchRate, 0.75);
	}
	
}
