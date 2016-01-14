// Masoud Harati
// Pokemon.java
// January 13, 2016
// Handles all the attributes of the Pokemon. Includes the Pokemon constructor method and
// the all the getter and setter methods for the Pokemon. Does not include methods for the
// Pokemons attacks.

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

	public ArrayList<Attack> getAttacks() {
		// Getter method for the pokemons attacks

		return attacks;
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

	public int findNumPossibleAttacks() {
		// Returns the number of possible attacks for a certain Pokemon
		// The Pokemons energy must be higher or equal to the cost of the attack

		int posAttackNum = 0;
		for(int i = 0; i < numOfAttacks; i++) {
			if(attacks.get(i).getAttackCost() <= energy) {
				posAttackNum++;
			}
		}

		return posAttackNum;
	}

	public void displayAttacks() {
		// Displays the Pokemons different attacks

		for(int i = 0; i < attacks.size(); i++) {
			System.out.println(attacks.size());
			Text.print("\t\t- ");
			Text.scrollPrintText("Name of Attack: ", false);
			Text.print(attacks.get(i).getAttackName()+"\n");

			Text.print("\t\t\t- ");
			Text.scrollPrintText("Cost of Attack: ", false);
			Text.print(attacks.get(i).getAttackCost()+"\n");

			Text.print("\t\t\t- ");
			Text.scrollPrintText("Damage of Attack: ", false);
			Text.print(attacks.get(i).getAttackDamage()+"\n");

			Text.print("\t\t\t- ");
			Text.scrollPrintText("Special Type: ", false);
			Text.print(attacks.get(i).getSpecialType().substring(0, 1).toUpperCase()+attacks.get(i).getSpecialType().substring(1)+"\n");
		}
	}

	public void stun() {
		// Changes the value of the pokemon to stunned

		stunned = true;
	}

	public void disable() {
		// Changes the value of the pokemon to disabled

		disabled = true;
	}

	public void undisable() {
		// Changes the value of the pokemon to not disabled

		disabled = false;
	}

	public void changeHP(int amount) {
		if(hp + amount >= 50) {
			hp = 50;
		}
		else if(energy + amount <= 0) {
			hp = 0;
		}
		else {
			hp -= amount;
		}
	}

	public void changeEnergy(int amount) {
		if(energy + amount >= 50) {
			energy = 50;
		}
		else if(energy + amount <= 0) {
			energy = 0;
		}
		else {
			energy += amount;
		}
	}

	public void attack(Attack attack, Pokemon enemy) {
		// Attack method that takes in the attack and the enemy and considers if the Pokemon is
		// weak or resistant to the attack and calculates all the damage.

		double damage = 1;

		Text.scrollPrintText(this.getName() + " used " + attack.getAttackName() + " against " + enemy.getName(), true);

		if(enemy.getResistance().equals(this.getType())) {
			Text.scrollPrintText(enemy.getName() + " is resistant to " + this.getType() + ", the damage dealt was cut in half.", true);
		}
		else if(enemy.getWeakness().equals(this.getType())) {
			Text.scrollPrintText(enemy.getName() + "'s weakness is " + this.getType() + ", the damage dealt was doubled.", true);
		}

		damage *= attack.doSpecial(this, enemy); // Performs the special attacks and returns the results
		energy -= attack.getAttackCost(); // Subtracts the energy cost

		if(getIsDisabled()) {
			// Does 10 less damage if the Pokemon is disabled

			damage *= Math.max(attack.getAttackDamage() - 10, 0);
		}
		else {
			// Does regular damage if the Pokemon isn't diabled

			damage *= attack.getAttackDamage();
		}

		enemy.changeHP((int)(-1*damage));

		Text.scrollPrintText(enemy.getName() + " lost " + (int) damage + " HP.", true);
	}

	public String toString() {
		// Displays the pokemons statistics

		return String.format("%s: %d/50 Energy, %d/%d HP", name, energy, hp, startHP);
	}
}