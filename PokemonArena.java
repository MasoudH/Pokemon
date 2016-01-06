// Masoud Harati
// PokemonArena.java
// January 13, 2016
// Contains the main method. Also, includes the main layout of the game.

/*
to-do:
	- Add special attack (Special.java)
	- Give the user their options to for moves (PokemonArena.java)
	- Battle Phase (PokemonArena.java)
	- Winner (PokemonArena.java)
	- Fix Move (PokemonArena.java)
	- Comment (Everywhere)
*/

import java.util.*;
import java.io.*;

public class PokemonArena {

	private static int usersCurrentPokemon;
	private static ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>(); // Arraylist containing all pokemons
	private static ArrayList<Pokemon> enemiesPokemons = new ArrayList<Pokemon>(); // Arraylist containing users pokemons
	private static ArrayList<Pokemon> usersPokemons = new ArrayList<Pokemon>(); // Arraylist containg enemy's pokemons

	public static void main(String[] args) {
		
		// Setting up graceful shutdown
		PokemonArena runtimeCheck = new PokemonArena();
		runtimeCheck.attachShutDownHook();

		// Running the game
		Graphics.start();
		loadPokemons();
		choose4();
		// testing();
		chosePokemon();
		Graphics.displayFinal(true);
	}

	private static void loadPokemons() {
		// Reads pokemon.txt, and stores the data in the pokedex arraylist

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
		Scanner kb = new Scanner(System.in); // Intilization of scanner to gather input


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
				Text.println("Please enter an integer.");
				kb.next();
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
					Text.println("Please enter an integer.");
					kb.next();
					selection = 0;
				}

				if(choice == 1) {
					choicesLeft -= 1;
					usersPokemons.add(pokedex.get(selection-1));
					pokedex.remove(pokedex.get(selection-1));
				}
				else if(choice == 2) {
					Text.scrollPrintText("Please chose another pokemon.", true);
				}
				else {
					Text.scrollPrintText("That is not a valid choice. Please enter a valid number.", true);
				}
			}
			else {
				Text.scrollPrintText("That is not a valid choice. Please enter a valid number.", true);
			}

			if (choicesLeft == 0) {
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

		while(whileLoopFlag) {

			Text.clear();
			Text.scrollPrintText("Your group of pokemons consists of:\n", false);

			for(int i = 1; i <= usersPokemons.size(); i++) {
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

			Text.scrollPrintText("Which Pokemon would you like to use to battle? ", false);

			try {
				Text.textToYellow();
				selection = kb.nextInt();
				Text.revertToOriginalColour();
			}
			catch(InputMismatchException ex) {
				Text.println("Please enter an Integer.");
				selection = 5;
				kb.next();
			}

			if(selection <= usersPokemons.size() && selection >= 0 && usersPokemons.get(selection-1).getHP() > 0) {
				usersCurrentPokemon = selection-1;
				whileLoopFlag = false;
				Text.print(usersPokemons.get(selection-1).getName());
				Text.scrollPrintText(", I chose you", false);
				System.out.println();
				Text.pause(500);
			}
			else {
				Text.scrollPrintText("Sorry, that isn't a valid Pokemon.\n", false);
			}
		}
	}

	private static void move(Pokemon friendly) {
		// Allows the user to chose from the possible moves.

		if(usersPokemons.contains(friendly)) {

			boolean isRetreatPossible;
			if(usersPokemons.size() > 1) {
				isRetreatPossible = true;
			}
			else {
				isRetreatPossible = false;
			}

			System.out.println();
		}
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