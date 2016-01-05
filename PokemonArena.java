// Masoud Harati
// PokemonArena.java
// January 13, 2016
// Contains the main method. Also, includes the main layout of the game.

/*
to-do:
	- Add special attack (Special.java)
	- get number of attacks available to chose from (Pokemon.java)
	- Give the user their options to for moves (PokemonArena.java)
	- Battle Phase (PokemonArena.java)
	- Winner (PokemonArena.java)
	- Fix Move (PokemonArena.java)
	- Change game to turn text into yellow (IO.java)
	- Change the program to revert text to orginal colour incase of interruption (PokemonArena.java) in main method by catching InterruptedException
*/

import java.util.*;
import java.io.*;

public class PokemonArena {

	private static int usersCurrentPokemon;
	private static ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>(); // Arraylist containing all pokemons
	private static ArrayList<Pokemon> enemiesPokemons = new ArrayList<Pokemon>(); // Arraylist containing users pokemons
	private static ArrayList<Pokemon> usersPokemons = new ArrayList<Pokemon>(); // Arraylist containg enemy's pokemons

	public static void main(String[] args) {
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
			IO.println("Unable to load pokemons from file. Please ensure the file is in the same directory as PokemonArena.java");
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
		IO.scrollPrintText("Welcome to the Arena, Trainer.", true);
		IO.scrollPrintText("You may chose 4 pokemons from my Pokedex to battle the rest.", true);
		// End of Introduction


		while(whileLoopFlag) {
			IO.clear();
			IO.scrollPrintText("You may chose ", false);
			IO.print(IO.intToString(choicesLeft));
			IO.scrollPrintText(" Pokemons out of the following ", false);
			IO.print(pokedex.size() + ":");
			System.out.println();

			for(int i = 1; i <= pokedex.size(); i++) {
				IO.print("\t"+i + " - " + pokedex.get(i-1).getName()+"\n");
				IO.pause(20);
			}

			IO.scrollPrintText("Enter the corresponding number to the Pokemon you want. There are ", false);
			IO.print(IO.intToString(choicesLeft));
			IO.scrollPrintText(" remaining.", true);

			try {
				IO.textToYellow();
				selection = kb.nextInt();
				IO.revertToOriginalColour();
			}
			catch(InputMismatchException ex) {
				IO.println("Please enter an integer.");
				IO.textToYellow();
				kb.next();
				IO.revertToOriginalColour();
				selection = 0;
			}

			if (selection >= 1 && selection <= pokedex.size()) {
				IO.clear();
				System.out.print("You chose " + pokedex.get(selection-1).getName()+".\n");
				Graphics.displayPokemon(pokedex.get(selection-1).getName(), false);
				displayStatistics(pokedex.get(selection-1));
				System.out.println();

				IO.scrollPrintText("Would you like to add ", false);
				IO.print(pokedex.get(selection-1).getName());
				IO.scrollPrintText(" to your group [1 - Yes / 2 - No]: ", false);

				try {
					IO.textToYellow();
					choice = kb.nextInt();
					IO.revertToOriginalColour();
				}
				catch(InputMismatchException ex) {
					IO.println("Please enter an integer.");
					IO.textToYellow();
					kb.next();
					IO.revertToOriginalColour();
					selection = 0;
				}

				if(choice == 1) {
					choicesLeft -= 1;
					usersPokemons.add(pokedex.get(selection-1));
					pokedex.remove(pokedex.get(selection-1));
				}
				else if(choice == 2) {
					IO.scrollPrintText("Please chose another pokemon.", true);
				}
				else {
					IO.scrollPrintText("That is not a valid choice. Please enter a valid number.", true);
				}
			}
			else {
				IO.scrollPrintText("That is not a valid choice. Please enter a valid number.", true);
			}

			if (choicesLeft == 0) {
				enemiesPokemons = pokedex; // Adds the rest of the pokemons to the enemies group.
				whileLoopFlag = false; // Ends loop when there are no choices left.
			}
		}
	}

	private static void displayStatistics(Pokemon pokemon) {
		// Displays the Pokemons main statistics

		IO.print("\t- ");
		IO.scrollPrintText("HP: ", false);
		IO.print(pokemon.getHP()+"\n");

		IO.print("\t- ");
		IO.scrollPrintText("Energy: ", false);
		IO.print(pokemon.getEnergy()+"\n");

		IO.print("\t- ");
		IO.scrollPrintText("Type: ", false);
		IO.print(pokemon.getType().substring(0, 1).toUpperCase() + pokemon.getType().substring(1)+"\n");

		IO.print("\t- ");
		IO.scrollPrintText("Resistance: ", false);
		IO.print(pokemon.getResistance().substring(0, 1).toUpperCase() + pokemon.getResistance().substring(1)+"\n");

		IO.print("\t- ");
		IO.scrollPrintText("Weakness: ", false);
		IO.print(pokemon.getWeakness().substring(0, 1).toUpperCase() + pokemon.getWeakness().substring(1)+"\n");

		IO.print("\t- ");
		IO.scrollPrintText("Number of Attacks: ", false);
		IO.print(pokemon.getNumOfAttacks()+"\n");

		pokemon.displayAttacks();
	}

	private static void chosePokemon() {
		// Allows the player to chose which pokemon they would like to use.

		Scanner kb = new Scanner(System.in);

		int selection = 5;
		boolean whileLoopFlag = true;

		while(whileLoopFlag) {

			IO.clear();
			IO.scrollPrintText("Your group of pokemons consists of:\n", false);

			for(int i = 1; i <= usersPokemons.size(); i++) {
				IO.print("\t"+i);
				IO.scrollPrintText(" - ", false);
				IO.print(usersPokemons.get(i-1).getName()+ " (");
				IO.print(usersPokemons.get(i-1).findNumPossibleAttacks()+" attacks available, ");

				if(usersPokemons.get(i-1).getHP() > 10) {
					IO.scrollPrintText("Active)", false);
				}
				else if(usersPokemons.get(i-1).getHP() <= 10 && usersPokemons.get(i-1).getHP() != 0) {
					IO.scrollPrintText("HP low)", false);
				}
				else {
					IO.scrollPrintText("KO)", false);
				}

				System.out.println();
			}

			IO.scrollPrintText("Which Pokemon would you like to use to battle? ", false);

			try {
				IO.textToYellow();
				selection = kb.nextInt();
				IO.revertToOriginalColour();
			}
			catch(InputMismatchException ex) {
				IO.println("Please enter an Integer.");
				selection = 5;
				IO.textToYellow();
				kb.next();
				IO.revertToOriginalColour();
			}

			if(selection <= usersPokemons.size() && selection >= 0 && usersPokemons.get(selection-1).getHP() > 0) {
				usersCurrentPokemon = selection-1;
				whileLoopFlag = false;
				IO.print(usersPokemons.get(selection-1).getName());
				IO.scrollPrintText(", I chose you", false);
				System.out.println();
				IO.pause(500);
			}
			else {
				IO.scrollPrintText("Sorry, that isn't a valid Pokemon.\n", false);
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

	private static void testing() {
		// Erase at the end

		IO.clear();

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