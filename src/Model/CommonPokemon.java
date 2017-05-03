/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: CommonPokemon.java
 * Purpose: CommonPokemon which inherits from the abstract class Pokemon, and
 * serves as the abstract class that all common-ranked Pokemon inherit from
 * (with an encounter rate of 0.5, most common)
 */

package Model;

import java.io.Serializable;

public abstract class CommonPokemon extends Pokemon implements Serializable {

	public CommonPokemon(String name, int hp, int catchRate, int speed, 
			String type, String pokePicName, String info) {
		super(name, hp, catchRate, speed, 0.5, type, pokePicName, info);
	}

	public abstract int getPokemonID();
	
}
