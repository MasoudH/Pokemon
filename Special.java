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
			IO.scrollPrintTextSlow(" used stun and was successful. ", false);
			System.out.print(enemy.getName()+"\n");
			// enemy.stun();
		}
		else {
			IO.scrollPrintTextSlow(" used stun and was unsuccessful.\n", false);
		}
	}

	public void wildcard(Pokemon friendly, Pokemon enemy) {
		// Wild Card: The attack only has a 50% chance of success. If it does not
		// succeed no damage is done.

		System.out.print(friendly.getName());

		Random rand = new Random();
		int randomNum = rand.nextInt(2);

		if(randomNum == 0) {
			IO.scrollPrintTextSlow(" used a wild card and the damage was SUCCESSFUL.", false);
		}
		else {
			IO.scrollPrintTextSlow(" used a wild card and the damage was UNSUCCESSFUL", false);
		}
	}

	public wildstorm(Pokemon friendly, Pokemon enemy) {
		int count = 0;

		System.out.print(friendly.getName());
		IO.scrollPrintTextSlow(" used wild storm. The damage was increased by ", false);

		Random rand = new Random();
		int randomNum = rand.nextInt(2);


		if(randomNum == 1) {
			count++;
			boolean whileLoopFlag = true;

			while(flag) {
				randomNum = rand.nextInt(2);

				if(randomNum == 0) {
					whileLoopFlag = false;
				}

				count++;
			}
		}

		System.out.print(count);
		IO.scrollPrintTextSlow("times.", false);
	}
}