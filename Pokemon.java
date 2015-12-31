// Masoud Harati
// Pokemon.java
// January 13, 2015

import java.util.*;
import java.io.*;

public class Pokemon {
	// Class for Pokemon Moves

	private String name, resistance, weakness, type;
	private int hp, startHP;
	private int energy = 50;
	private ArrayList<String> attacks = new ArrayList<String>();
	private boolean disabled = false;
	private boolean stunned = false;
	private int numOfAttacks;

	public Pokemon(String dataLine) {
		// Constructor Method

		String[] inArray = dataLine.split(",");
		name = inArray[0];
		hp = Integer.parseInt(inArray[1]);
		startHP = hp;
		type = inArray[2];
		resistance = inArray[3];
		weakness = inArray[4];
	}

	public String getName() {
		// Getter method for the pokemons name

		return name;
	}

	public int getHP() {
		// Getter method for the pokemons hp
		
		return hp;
	}

	public int getEnergy() {
		// Getter method for the pokemons energy

		return energy;
	}

	public String getType() {
		// Getter method for the pokemons type

		return type;
	}

	public String getResistance() {
		// Getter method for the pokemons resistance

		if (resistance.equals(" ")) {
			return "None";
		}
		else {
			return resistance;
		}
	}

	public String getWeakness() {
		// Getter method for the pokemons weakness

		if (weakness.equals(" ")) {
			return "None";
		} else {
			return weakness;
		}
	}

	public boolean getIsDisabled() {
		// Getter method for the disabled boolean of the pokemon

		return disabled;
	}

	public boolean getIsStunned() {
		// Getter method for the stunned boolean of the pokemon

		return stunned;
	}

}