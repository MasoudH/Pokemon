// Masoud Harati
// Attack.java
// January 13, 2016
// Handles the getter methods for the Pokemons Attacks, also contains the constructor method for
// the pokemons properties of the pokemons attack.

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
}