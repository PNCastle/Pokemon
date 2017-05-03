/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: RarePokemon.java
 * Purpose: RarePokemon which inherits from the abstract class Pokemon, and
 * serves as the abstract class that all rare-ranked Pokemon inherit from
 * (with an encounter rate of 0.9, for nearly infrequent encounters)
 */

package Model;

import java.io.Serializable;

public abstract class RarePokemon extends Pokemon implements Serializable {
	
	public RarePokemon(String name, int hp, int catchRate, int speed,
			String type, String pokePicName, String info) {
		super(name, hp, catchRate, speed, 0.9, type, pokePicName, info);
	}

	public abstract int getPokemonID();
	
}
