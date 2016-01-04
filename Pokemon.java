// Masoud Harati
// Pokemon.java
// January 13, 2016

import java.util.*;
import java.io.*;

public class Pokemon {
	// Class for Pokemon Moves

	private String name, resistance, weakness, type;
	private int hp, startHP, numOfAttacks;
	private int energy = 50;
	private ArrayList<Attack> attacks = new ArrayList<Attack>();
	private boolean disabled = false;
	private boolean stunned = false;

	public Pokemon(String dataLine) {
		// Constructor Method

		String[] inArray = dataLine.split(",");
		name = inArray[0];
		hp = Integer.parseInt(inArray[1]);
		startHP = hp;
		type = inArray[2];
		resistance = inArray[3];
		weakness = inArray[4];
		numOfAttacks = Integer.parseInt(inArray[5]);

		for(int i = 0; i < numOfAttacks; i++) {
			// Adding the pokemons attack attributes to the attacks arraylist
			attacks.add(new Attack(inArray[4*i+6], Integer.parseInt(inArray[4*i+7]), Integer.parseInt(inArray[4*i+8]), inArray[4*i+9]));
		}
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

	public int getNumOfAttacks() {
		// Getter method for the number of different attacks a pokemon has

		return numOfAttacks;
	}

	public void displayAttacks() {
		// Displays the Pokemons different attacks

		for(int i = 0; i < attacks.size(); i++) {
			System.out.print("\t\t- ");
			IO.scrollPrintTextSlow("Name of Attack: ", false);
			System.out.print(attacks.get(i).getAttackName()+"\n");

			System.out.print("\t\t\t- ");
			IO.scrollPrintTextSlow("Cost of Attack: ", false);
			System.out.print(attacks.get(i).getAttackCost()+"\n");

			System.out.print("\t\t\t- ");
			IO.scrollPrintTextSlow("Damage of Attack: ", false);
			System.out.print(attacks.get(i).getAttackDamage()+"\n");

			System.out.print("\t\t\t- ");
			IO.scrollPrintTextSlow("Special Type: ", false);
			System.out.print(attacks.get(i).getSpecialType().substring(0, 1).toUpperCase()+attacks.get(i).getSpecialType().substring(1)+"\n");
		}
	} 

	public void stun() {
		// Changes the value of the pokemon to stunned

		stunned = true;
	}

	public void unstun() {
		// Changes the value of the pokemon to not stunned

		stunned = false;
	}

	public void disable() {
		// Changes the value of the pokemon to disabled

		disabled = true;
	}

	public void undisable() {
		// Changes the value of the pokemon to not disabled

		disabled = false;
	}
}