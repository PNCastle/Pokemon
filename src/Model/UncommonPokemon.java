/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: UnommonPokemon.java
 * Purpose: UnommonPokemon which inherits from the abstract class Pokemon, and
 * serves as the abstract class that all uncommonly-ranked Pokemon inherit from
 * (with an encounter rate of 0.75, for somewhat uncommon encounters)
 */

package Model;

import java.io.Serializable;

public abstract class UncommonPokemon extends Pokemon implements Serializable {

	public UncommonPokemon(String name, int hp, int catchRate, int speed,
			String type, String pokePicName, String info) {
		super(name, hp, catchRate, speed, 0.75, type, pokePicName, info);
	}
	
	public abstract int getPokemonID();
	
}
