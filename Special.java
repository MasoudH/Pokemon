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
			IO.scrollPrintTextSlow(" used stun and was successful. ");
			System.out.print(enemy.getName()+"\n");
			// enemy.stun();
		}
		else {
			IO.scrollPrintTextSlow(" used stun and was unsuccessful.\n");
		}
	}

	public void wildcard(Pokemon friendly, Pokemon enemy) {
		// Wild Card: The attack only has a 50% chance of success. If it does not
		// succeed no damage is done.

		System.out.print(friendly.getName());

		Random rand = new Random();
		int randomNum = rand.nextInt(2);

		if(randomNum == 0) {
			IO.scrollPrintTextSlow(" used a wild card and the damage was SUCCESSFUL.");
		}
		else {
			IO.scrollPrintTextSlow(" used a wild card and the damage was UNSUCCESSFUL");
		}
	}

}