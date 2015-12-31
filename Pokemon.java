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
		String[] inArray = dataLine.split(",");
		name = inArray[0];
		hp = Integer.parseInt(inArray[1]);
		startHP = hp;
		type = inArray[2];
		resistance = inArray[3];
		weakness = inArray[4];
	}


	// Start of Getter Methods for the Pokemon Class
	public String getName() {
		return name;
	}

	public int getHP() {
		return hp;
	}

	public int getEnergy() {
		return energy;
	}

	public String getType() {
		return type;
	}

	public String getResistance() {
		if (resistance.equals(" ")) {
			return "None";
		}
		else {
			return resistance;
		}
	}

	public String getWeakness() {
		if (weakness.equals(" ")) {
			return "None";
		} else {
			return weakness;
		}
	}

	public int getNumOfAttacks() {
		return numOfAttacks;
	}

	public boolean getIsDisabled() {
		return disabled;
	}

	public boolean getIsStunned() {
		return stunned;
	}
	// End of Getter Methods
}