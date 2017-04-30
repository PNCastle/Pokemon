/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: RarePokemon.java
 * Purpose: RarePokemon which inherits from the abstract class Pokemon, and
 * serves as the abstract class that all rare-ranked Pokemon inherit from
 * (with an encounter rate of 0.9, for nearly infrequent encounters)
 */

package Model;

public abstract class RarePokemon extends Pokemon {
	
	public RarePokemon(String name, int hp, int catchRate, int speed,
			String type, String pokePicName) {
		super(name, hp, catchRate, speed, 0.9, type, pokePicName);
	}

}
