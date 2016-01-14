// Masoud Harati
// PokemonArena.java
// January 13, 2016
// Contains the main method. Also, includes the

/*
to-do:
	- Battle Phase (PokemonArena.java)
	- Winner (PokemonArena.java)
	- Comment in the following files:
		- PokemonArena.java
		- Pokemon.java
		- Attack.java
*/

import java.util.*;
import java.io.*;

public class PokemonArena {

	private static int usersCurrentPokemonIndex = 1;
	private static ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>(); // Arraylist containing all pokemons
	private static ArrayList<Pokemon> enemiesPokemons = new ArrayList<Pokemon>(); // Arraylist containing users pokemons
	private static ArrayList<Pokemon> usersPokemons = new ArrayList<Pokemon>(); // Arraylist containg enemy's pokemons

	public static void main(String[] args) {
		// Main method runs the PokemonArena game.
		
		// Setting up graceful shutdown
		PokemonArena runtimeCheck = new PokemonArena();
		runtimeCheck.attachShutDownHook();

		// Running the game
		// Graphics.start();
		loadPokemons();
		// choose4();
		testing();
		// chosePokemon();
		// Graphics.displayFinal(true);
	}

	private static void loadPokemons() {
		// Reads pokemon.txt, and stores the data for the pokemons in the pokedex
		// arraylist

		Scanner inFile = null;

		try {
			inFile = new Scanner(new File("pokemon.txt"));
		}
		catch(IOException ex) {
			Text.println("Unable to load pokemons from file. Please ensure the file is in the same directory as PokemonArena.java");
		}

		int numLine;
		numLine = Integer.parseInt(inFile.nextLine());

		for(int i = 0; i < numLine; i++) {
			String line = inFile.nextLine();
			pokedex.add(new Pokemon(line));
		}
	}

	private static void choose4() {
		// Allows the user to chose the 4 pokemons of their choice by entering
		// the pokemons corresponding number


		int selection = 0;
		int choicesLeft = 4;
		int choice = 0;
		boolean whileLoopFlag = true;
		Scanner kb = new Scanner(System.in);


		// Start of Introduction
		Text.scrollPrintText("Welcome to the Arena, Trainer.", true);
		Text.scrollPrintText("You may chose 4 pokemons from my Pokedex to battle the rest.", true);
		// End of Introduction


		while(whileLoopFlag) {
			Text.clear();
			Text.scrollPrintText("You may chose ", false);
			Text.print(Text.intToString(choicesLeft));
			Text.scrollPrintText(" Pokemons out of the following ", false);
			Text.print(pokedex.size() + ":");
			System.out.println();

			for(int i = 1; i <= pokedex.size(); i++) {
				// Displays all the available Pokemons to the user.

				Text.print("\t"+i + " - " + pokedex.get(i-1).getName()+"\n");
				Text.pause(20);
			}

			Text.scrollPrintText("Enter the corresponding number to the Pokemon you want. There are ", false);
			Text.print(Text.intToString(choicesLeft));
			Text.scrollPrintText(" remaining.", true);

			try {
				Text.textToYellow();
				selection = kb.nextInt();
				Text.revertToOriginalColour();
			}
			catch(InputMismatchException ex) {
				// If user doesn't input an Integer. requests for integer to be selected

				Text.println("Please enter an integer.");
				kb.next(); // Flushes the users invalid input
				selection = 0;
			}

			if (selection >= 1 && selection <= pokedex.size()) {
				Text.clear();
				Text.print("You chose " + pokedex.get(selection-1).getName()+".\n");
				Graphics.displayPokemon(pokedex.get(selection-1).getName(), false);
				displayStatistics(pokedex.get(selection-1));
				System.out.println();

				Text.scrollPrintText("Would you like to add ", false);
				Text.print(pokedex.get(selection-1).getName());
				Text.scrollPrintText(" to your group [1 - Yes / 2 - No]: ", false);

				try {
					Text.textToYellow();
					choice = kb.nextInt();
					Text.revertToOriginalColour();
				}
				catch(InputMismatchException ex) {
					// If the user doesn't input an Integer, requests for an integer to be selected

					Text.println("Please enter an integer.");
					kb.next();
					selection = 0;
				}

				if(choice == 1) {
					// If the user confirms their choice

					choicesLeft -= 1;
					usersPokemons.add(pokedex.get(selection-1));
					pokedex.remove(pokedex.get(selection-1));
				}
				else if(choice == 2) {
					// If the user declines their choice 

					Text.scrollPrintText("Please chose another pokemon.", true);
				}
				else {
					// If the user enters and invalid Integer. Either too high or too low. 

					Text.scrollPrintText("That is not a valid choice. Please enter a valid number.", true);
				}
			}
			else {
				// If the user enters and invalid Integer. Either too high or too low.

				Text.scrollPrintText("That is not a valid choice. Please enter a valid number.", true);
			}

			if (choicesLeft == 0) {
				// The base case. If the user chose 4 pokemons it exits the loops and continues the game.

				enemiesPokemons = pokedex; // Adds the rest of the pokemons to the enemies group.
				whileLoopFlag = false; // Ends loop when there are no choices left.
			}
		}
	}

	private static void displayStatistics(Pokemon pokemon) {
		// Displays the Pokemons main statistics

		Text.print("\t- ");
		Text.scrollPrintText("HP: ", false);
		Text.print(pokemon.getHP()+"\n");

		Text.print("\t- ");
		Text.scrollPrintText("Energy: ", false);
		Text.print(pokemon.getEnergy()+"\n");

		Text.print("\t- ");
		Text.scrollPrintText("Type: ", false);
		Text.print(pokemon.getType().substring(0, 1).toUpperCase() + pokemon.getType().substring(1)+"\n");

		Text.print("\t- ");
		Text.scrollPrintText("Resistance: ", false);
		Text.print(pokemon.getResistance().substring(0, 1).toUpperCase() + pokemon.getResistance().substring(1)+"\n");

		Text.print("\t- ");
		Text.scrollPrintText("Weakness: ", false);
		Text.print(pokemon.getWeakness().substring(0, 1).toUpperCase() + pokemon.getWeakness().substring(1)+"\n");

		Text.print("\t- ");
		Text.scrollPrintText("Number of Attacks: ", false);
		Text.print(pokemon.getNumOfAttacks()+"\n");

		pokemon.displayAttacks();
	}

	private static void chosePokemon() {
		// Allows the player to chose which pokemon they would like to use.

		Scanner kb = new Scanner(System.in);

		int selection = 5;
		boolean whileLoopFlag = true;

		Text.clear();
		Text.scrollPrintText("Your group of pokemons consists of:\n", false);

		for(int i = 1; i <= usersPokemons.size(); i++) {
			// Only allows the user to chose a pokemon if their is atleast 2 available

			Text.print("\t"+i);
			Text.scrollPrintText(" - ", false);
			Text.print(usersPokemons.get(i-1).getName()+ " (");
			Text.print(usersPokemons.get(i-1).findNumPossibleAttacks()+" attacks available, ");

			if(usersPokemons.get(i-1).getHP() > 10) {
				Text.scrollPrintText("Active)", false);
			}
			else if(usersPokemons.get(i-1).getHP() <= 10 && usersPokemons.get(i-1).getHP() != 0) {
				Text.scrollPrintText("HP low)", false);
			}
			else {
				Text.scrollPrintText("KO)", false);
			}

			System.out.println();
		}

		while(whileLoopFlag) {

			Text.scrollPrintText("Which Pokemon would you like to use to battle? ", false);

			try {
				Text.textToYellow();
				selection = kb.nextInt();
				Text.revertToOriginalColour();
			}
			catch(InputMismatchException ex) {
				// If the input isn't an Integer prompts for an Integer input.

				Text.println("Please enter an Integer.");
				selection = 5;
				kb.next();
			}

			if(selection <= usersPokemons.size() && selection >= 0 && usersPokemons.get(selection-1).getHP() > 0) {
				usersCurrentPokemonIndex = selection-1;
				whileLoopFlag = false;
				Text.print(usersPokemons.get(selection-1).getName());
				Text.scrollPrintText(", I chose you\n", false);
				System.out.println();
				Text.pause(1000);
			}
			else {
				Text.scrollPrintText("Sorry, that isn't a valid Pokemon.\n", false);
			}
		}
	}

	private static String move(Pokemon pokemon) {
		// Allows the user to chose from the possible moves.

		boolean whileLoopFlag = true;
		Scanner kb = new Scanner(System.in);
		int selection = 0;

		if(usersPokemons.contains(pokemon)) {

			boolean isRetreatPossible;
			if(usersPokemons.size() > 1) {
				isRetreatPossible = true;
			}
			else {
				isRetreatPossible = false;
			}

			Text.scrollPrintText(pokemon.getName(), true);

			Text.scrollPrintText("1. Attack ("+pokemon.findNumPossibleAttacks()+" Available)", true);
			if(isRetreatPossible) {
				Text.scrollPrintText("2. Retreat", true);
			}
			Text.scrollPrintText("3. Pass", true);

			Text.scrollPrintText("Please enter the number corresponding to the move you want.", true);

			while(whileLoopFlag) {

				try {
					Text.textToYellow();
					selection = kb.nextInt();
					Text.revertToOriginalColour();
				}
				catch(InputMismatchException ex) {
					Text.scrollPrintText("Please enter an Integer.", true);
					selection = 4;
					kb.next();
				}

				if(pokemon.findNumPossibleAttacks() > 0 && selection == 1) {
					return "attack";
				}
				else if(pokemon.findNumPossibleAttacks() == 0 && selection == 1) {
					Text.scrollPrintText("The Pokemon is too tired to perform an attack. Please choose another option.", true);
					return "";
				}

				else if(selection == 2 && isRetreatPossible) {
					return "retreat";
				}

				else if(selection == 3) {
					return "pass";
				}

				else {
					Text.scrollPrintText("Invalid, Please try again.", true);
					return "";
				}
			}

			return "";
		}

		else {
			// For enemy pokemons choice

			if(pokemon.findNumPossibleAttacks() > 0) {
				return "attack";
			}
			else {
				return "pass";
			}
		}
	}

	public static void pass(Pokemon pokemon) {
		// Does nothing

		Text.scrollPrintText("\n"+pokemon.getName()+"passed. Nothing happened.\n", true);
	}

	public static void retreat(Pokemon pokemon) {
		// Allows the user to change their pokemon.

		boolean whileLoopFlag = true;
		Scanner kb = new Scanner(System.in);
		int selection = 0;

		Text.scrollPrintText("The following pokemons are available to chose from:", true);

		for(int i = 1; i <= usersPokemons.size(); i++) {
			Text.scrollPrintText("\t" + i + " - " + usersPokemons.get(i-1).getName(), true);
		}

		while(whileLoopFlag) {

			Text.scrollPrintText("Enter the number corresponding to the Pokemon you would like to switch to.", true);

			try {
				Text.textToYellow();
				selection = kb.nextInt();
				Text.revertToOriginalColour();
			}

			catch(InputMismatchException ex) {
				Text.scrollPrintText("Please enter an Integer.", true);
				selection = 0;
				kb.next();
			}

			if(selection - 1 >= 0 && selection <= usersPokemons.size() && selection - 1 != usersCurrentPokemonIndex) {
				usersCurrentPokemonIndex = selection - 1;
				Text.scrollPrintText("You switched to "+usersPokemons.get(selection-1)+".", true);
				whileLoopFlag = false;
			}
			else if(selection - 1 == usersCurrentPokemonIndex) {
				Text.scrollPrintText("You chose your current Pokemon please chose move again.", true);
				Text.pause(1000);
				Text.clear();
				move(usersPokemons.get(usersCurrentPokemonIndex));
				whileLoopFlag = false;
			}
			else {
				Text.scrollPrintText("That is an invalid choice.", true);
			}
		}
	}

	public static Attack attack(Pokemon pokemon) {
		// Allows the user to chose the attack they want to use

		ArrayList<Attack> pokemonsAttacks = new ArrayList<Attack>(); // List of the users pokemons attacks
		Scanner kb = new Scanner(System.in);
		int selection = 0;
		boolean whileLoopFlag = true;
		Random rand = new Random();

		for(int i = 0; i < pokemon.getAttacks().size(); i++) {
			if(pokemon.getAttacks().get(i).getAttackCost() <= pokemon.getEnergy()) {
				pokemonsAttacks.add(pokemon.getAttacks().get(i));
			}	
		}

		if(usersPokemons.contains(pokemon)) {
			// if its the users pokemon

			Text.scrollPrintText("The following attack are available to chose from: ", true);

			for(int i = 1; i <= pokemonsAttacks.size(); i++) {

				// Prints the pokemons attacks, spread into many statements for code neatness
				Text.scrollPrintText("\t" + i + " - " + pokemonsAttacks.get(i-1).getAttackName(), false);
				Text.scrollPrintText("(Energy Cost: " + pokemonsAttacks.get(i-1).getAttackCost() + ", ", false);
				Text.scrollPrintText("Damage: " + pokemonsAttacks.get(i-1).getAttackDamage() + ", ", false);
				Text.scrollPrintText("Special: " + pokemonsAttacks.get(i-1).getSpecialType().substring(0, 1).toUpperCase()+pokemonsAttacks.get(i-1).getSpecialType().substring(1) + ")", true);
			}

			Text.scrollPrintText("Enter the number corresponding to the attack you want.", true);

			while(whileLoopFlag) {
			 	
			 	try {
			 		Text.textToYellow();
					selection = kb.nextInt();
					Text.revertToOriginalColour();
				}
				catch(InputMismatchException ex) {
					Text.scrollPrintText("Please enter an Integer.", true);
					selection = 0;
					kb.next();
				}

				if(selection > 0 && selection <= pokemonsAttacks.size()) {
					whileLoopFlag = false;
				}

				else {
					Text.scrollPrintText("Invalid, Please try again.", true);
				}
			}

			return pokemonsAttacks.get(selection - 1);
		}

		else {
			// Random attack selection for the enemy's behalf

			int randomNum = rand.nextInt(pokemonsAttacks.size());
			return pokemonsAttacks.get(randomNum);
		}
	}

	public static void round(Pokemon player1, Pokemon player2) {
		if(!player1.getIsStunned()) {

			String choice = move(player1);

			if(choice.equals("attack")) {

				player1.attack(attack(player1), player2);
			}
			else if(choice.equals("pass")) {

				pass(player1);
			}
			else if(choice.equals("retreat")) {

				retreat(player1);
				player1 = usersPokemons.get(usersCurrentPokemonIndex);
			}
		}

		else {

			Text.scrollPrintText(player1.getName() + "was stunned, and couldn't do anything", true);
			player1.unstun();
		}

		Text.pause(2000);
		Text.clear();

		if(!player2.isDead()) {

			if(!player2.getIsStunned()) {

				String choice = move(player2);
				if(choice.equals("attack")) {

					player2.attack(attack(player2), player1);
				}
				else if(choice.equals("pass")) {

					pass(player2);
				}
				else if(choice.equals("retreat")) {

					retreat(player2);
					player2 = usersPokemons.get(usersCurrentPokemonIndex);
				}
			}

			else {

				Text.scrollPrintText(player2.getName() + "was stunned, and couldn't do anything", true);
				player2.unstun();
			}
		}

		Text.pause(2000);

		for(int i = 0; i < usersPokemons.size(); i++) {
			// Increases the energy for the users Pokemons

			usersPokemons.get(i).changeEnergy(10);
		}

		for(int i = 0; i < enemiesPokemons.size(); i++) {
			// Increases the energy of the enemies Pokemons

			enemiesPokemons.get(i).changeEnergy(10);
		}
	}

	public static void battle() {
		// The battle phase of the Pokemon. Goes through rounds of battle until the user or the enemy run
		// out of Pokemons.

		Random rand = new Random();
		int randomNum = rand.nextInt(2);
		int enemiesPokemonIndex = rand.nextInt(enemiesPokemons.size());
		boolean whileLoopFlag = true;


		Text.pause(700);

		Text.scrollPrintText("A wild " + enemiesPokemons.get(enemiesPokemonIndex).getName() + " appeared!\n\n", true);

		Text.pause(1000);

		chosePokemon(); // Prompts the user to chose their Pokemon

		Text.pause(700);

		if(randomNum == 0) {
			// The user goes first

			Text.scrollPrintText(usersPokemons.get(usersCurrentPokemonIndex).getName() + " gets to go first!", true);
		}
		else {
			Text.scrollPrintText(enemiesPokemons.get(enemiesPokemonIndex).getName() + "gets to go first!", true);
		}

		Text.pause(700);


		while(whileLoopFlag) {

			if(usersPokemons.get(usersCurrentPokemonIndex).isDead()) {

				Text.scrollPrintText(enemiesPokemons.get(enemiesPokemonIndex).getName() + " killed " + usersPokemons.get(usersCurrentPokemonIndex).getName(), true);
				usersPokemons.remove(usersCurrentPokemonIndex);
				chosePokemon(); // Prompts the user to chose a Pokemon because their Pokemon is dead.
			}
			if(enemiesPokemons.get(enemiesPokemonIndex).isDead()) {

				Text.scrollPrintText(usersPokemons.get(usersCurrentPokemonIndex).getName() + " killed " + enemiesPokemons.get(enemiesPokemonIndex).getName(), true);
				enemiesPokemons.remove(enemiesPokemonIndex);
				enemiesPokemonIndex = rand.nextInt(enemiesPokemons.size()); // Choses a new Pokemon for the enemy
			}
			else {

				if(randomNum == 0) {

					round(usersPokemons.get(usersCurrentPokemonIndex), enemiesPokemons.get(enemiesPokemonIndex));
				}
				else {

					round(enemiesPokemons.get(enemiesPokemonIndex), usersPokemons.get(usersCurrentPokemonIndex));
				}
			}

			for(int i = 0; i < usersPokemons.size(); i++) {
				// Heals all of the users Pokemons by 20 HP

				usersPokemons.get(i).changeHP(20);
				usersPokemons.get(i).undisable(); // undisable the users Pokemons if they were disabled
			}

			for(int i = 0; i < usersPokemons.size(); i++) {
				// undisables the enemies Pokemons

				enemiesPokemons.get(i).undisable();
			}
		}
	}

	public static String completeBattle() {
		// Creates the battle and checks which user 

		String out = ""; // Wether the user won or not
		boolean whileLoopFlag = true;

		while(whileLoopFlag) {

			battle();

			if(usersPokemons.size() == 0) {
				// If all of the users Pokemons die

				out = "lost";
				whileLoopFlag = false;
			}
			if(enemiesPokemons.size() == 0) {
				// If all of the enemies Pokemons die

				out = "win";
				whileLoopFlag = true;
			}
		}
		
		return out;
	}

	public void attachShutDownHook() {
		// Call in main class for graceful shutdown

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				Text.revertToOriginalColour();
				Text.clear();
			}
		});
	}

	private static void testing() {
		// Erase at the end

		Text.clear();

		// Used for testing
		usersPokemons.add(pokedex.get(0));
		usersPokemons.add(pokedex.get(1));
		usersPokemons.add(pokedex.get(2));
		usersPokemons.add(pokedex.get(3));

		for(int i = 0; i < 4; i++) {
			pokedex.remove(pokedex.get(0));
		}

		enemiesPokemons = pokedex;
		// End of testing stage
	}
}