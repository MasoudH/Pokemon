// Masoud Harati
// Special.java
// January 13, 2016
// The Special class contains methods that handle the pokemons special attacks

import java.util.*;
import java.io.*;

public class Special {
	public void stun(Pokemon friendly, Pokemon enemy) {
		// Stun: on top of normal damage there is a 50% chance that the opponent 
		// will be stunned for one turn. If a Pokemon is stunned it my not attack
		// or retreat.

		System.out.print(friendly.getName());

		Random rand = new Random();
		int randomNum = rand.nextInt(2);

		if(randomNum == 0) {
			IO.scrollPrintText(" used stun and was successful. ", false);
			System.out.print(enemy.getName()+"\n");
			// enemy.stun();
		}
		else {
			IO.scrollPrintText(" used stun and was unsuccessful.\n", false);
		}
	}

	public void wildcard(Pokemon friendly, Pokemon enemy) {
		// Wild Card: The attack only has a 50% chance of success. If it does not
		// succeed no damage is done.

		System.out.print(friendly.getName());

		Random rand = new Random();
		int randomNum = rand.nextInt(2);

		if(randomNum == 0) {
			IO.scrollPrintText(" used a wild card and the damage was SUCCESSFUL.", false);
		}
		else {
			IO.scrollPrintText(" used a wild card and the damage was UNSUCCESSFUL", false);
		}
	}

	public void wildstorm(Pokemon friendly, Pokemon enemy) {
		// Base attack has a 50% chance of success, again no damage on a miss, but
		// if it succeeds then the Pokemon does a free wild storm attack (yes this
		// can go on forever.)

		int count = 0;

		System.out.print(friendly.getName());
		IO.scrollPrintText(" used wild storm. The damage was increased by ", false);

		Random rand = new Random();
		int randomNum = rand.nextInt(2);


		if(randomNum == 1) {
			count++;
			boolean whileLoopFlag = true;

			while(whileLoopFlag) {
				randomNum = rand.nextInt(2);

				if(randomNum == 0) {
					whileLoopFlag = false;
				}

				count++;
			}
		}

		
		System.out.print(count);
		IO.scrollPrintText("times.", false);
	}

	public void disable(Pokemon enemy) {
		// The target Pokemon becomes disabled, and its attacks will do 10 less
		// damage for the rest of the battle (to a minimum of zero). A Pokemon
		// can only be disabled once.

		if (!enemy.getIsDisabled()) {
			enemy.disable();
			enemy.changeHP(-10);
		}
	}

	public void recharge(friendly) {
		// Adds 20 energy to the attacking Pokemon.

		if(friendly.getEnergy <= 30) {
			friendly.changeEnergy(20);
		}
		else {
			friendly.changeEnergy(50-friendly.getEnergy());
		}
	}
}