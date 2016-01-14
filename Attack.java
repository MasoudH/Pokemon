// Masoud Harati
// Attack.java
// January 13, 2016
// Handles the getter and setter methods for the Pokemons Attacks, also contains the constructor method for
// the pokemons attack properties.

import java.util.*;
import java.io.*;

public class Attack {
	private int cost, damage;
	private String name, special;

	public Attack(String name, int cost, int damage, String special) {
		// Constructor method for the Attack class

		this.name = name;
		this.cost = cost;
		this.damage = damage;
		this.special = special;
	}

	public String getAttackName() {
		// Getter method for the attack name

		return name;
	}

	public int getAttackCost() {
		// Getter method for the energy cost of the attack

		return cost;
	}

	public int getAttackDamage() {
		// Getter method for the damage dealt by the attack

		return damage;
	}

	public String getSpecialType() {
		// Getter method for the special type

		if (special.equals(" ")) {
			return "None";	
		}
		else {
			return special;
		}
	}

	public int doSpecial(Pokemon friendly, Pokemon enemy) {
	// doSpecial returns an integer which determines how much the special changes the attack
	// damage. If it returns 0 then the attack was not completed

		if(special.equals("stun")) {
			return stun(friendly, enemy);
		} 
		else if(special.equals("wild card")) {
			return wildcard(friendly, enemy);
		}
		else if(special.equals("wild storm")) {
			return wildstorm(friendly, enemy);
		}
		else if(special.equals("disable")) {
			return disable(friendly, enemy);
		}
		else if(special.equals("recharge")) {
			return recharge(friendly, enemy);
		}

		return 1;
	}

	public int stun(Pokemon friendly, Pokemon enemy) {
		// Stun: on top of normal damage there is a 50% chance that the opponent 
		// will be stunned for one turn. If a Pokemon is stunned it may not attack
		// or retreat.

		Text.scrollPrintText(friendly.getName(), false);

		Random rand = new Random();
		int randomNum = rand.nextInt(2);

		if(randomNum == 1) {
			Text.scrollPrintText(" used stun and was successful.", true);
			enemy.stun();
		}
		else {
			Text.scrollPrintText(" used stun and was unsuccessful.", true);
		}

		return 1;
	}

	public int wildcard(Pokemon friendly, Pokemon enemy) {
		// Wild Card: The attack only has a 50% chance of success. If it does not
		// succeed no damage is done.

		Text.scrollPrintText(friendly.getName(), true);

		Random rand = new Random();
		int randomNum = rand.nextInt(2);

		if(randomNum == 1) {
			Text.scrollPrintText(" used a wild card and the damage was SUCCESSFUL.", true);
			return randomNum;
		}
		else {
			Text.scrollPrintText(" used a wild card and the damage was UNSUCCESSFUL", true);
			return randomNum;
		}
	}

	public int wildstorm(Pokemon friendly, Pokemon enemy) {
		// Base attack has a 50% chance of success, again no damage on a miss, but
		// if it succeeds then the Pokemon does a free wild storm attack (yes this
		// can go on forever.)

		int count = 0;

		Text.scrollPrintText(friendly.getName(), false);
		Text.scrollPrintText(" used wild storm. The damage was increased by ", true);

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

		
		Text.scrollPrintText(Text.intToString(count), false);
		Text.scrollPrintText("times.", true);

		return count;
	}

	public int disable(Pokemon friendly, Pokemon enemy) {
		// The target Pokemon becomes disabled, and its attacks will do 10 less
		// damage for the rest of the battle (to a minimum of zero). A Pokemon
		// can only be disabled once.

		if (!enemy.getIsDisabled()) {
			enemy.disable();
			Text.scrollPrintText(friendly.getName() + "used disable on " + enemy.getName() + "and was successful.", true);
		}

		else {
			Text.scrollPrintText(friendly.getName() + "used disable on " + enemy.getName() + "and was unsuccessful.", true);
		}

		return 1;
	}

	public int recharge(Pokemon friendly, Pokemon enemy) {
		// Adds 20 energy to the attacking Pokemon.

		if(friendly.getEnergy() <= 30) {
			friendly.changeEnergy(20);
		}
		else {
			friendly.changeEnergy(50-friendly.getEnergy());
		}

		Text.scrollPrintText(friendly.getName() + "used recharge and gained 20 energy.", true);
		return 1;
	}
}