// Masoud Harati
// PokemonArena.java
// January 13, 2016
// Contains the main method. Also, includes the main layout of the game.

import java.util.*;
import java.io.*;

public class PokemonArena {

	private static ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
	private static ArrayList<Pokemon> enemiesPokemons = new ArrayList<Pokemon>();
	private static ArrayList<Pokemon> usersPokemons = new ArrayList<Pokemon>();

	public static void main(String[] args) {
		// Graphics.start();
		loadPokemons();
		choose4();
		// Graphics.displayFinal(true);
		// displayStatistics(pokedex.get(6));
	}

	private static void loadPokemons() {
		// Reads pokemon.txt, and stores the data in the pokedex arraylist

		Scanner inFile = null;

		try {
			inFile = new Scanner(new File("pokemon.txt"));
		}
		catch(IOException ex) {
			System.out.println(ex);
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
		IO.scrollPrintTextSlow("Welcome to the Arena, Trainer.", true);
		IO.scrollPrintTextSlow("You may chose 4 pokemons from my Pokedex to battle the rest.", true);
		// End of Introduction


		while(whileLoopFlag) {
			IO.clear();
			IO.scrollPrintTextSlow("You may chose ", false);
			System.out.print(choicesLeft);
			IO.scrollPrintTextSlow(" Pokemons out of the following ", false);
			System.out.print(pokedex.size() + ":");
			System.out.println();

			for(int i = 1; i <= pokedex.size(); i++) {
				System.out.printf("\t%d - %s\n", i, pokedex.get(i-1).getName());
				IO.pause(10);
			}

			IO.scrollPrintTextSlow("Enter the corresponding number to the Pokemon you want. There are ", false);
			System.out.print(choicesLeft);
			IO.scrollPrintTextSlow(" remaining.", true);

			try {
				selection = kb.nextInt();
			}
			catch(InputMismatchException ex) {
				System.out.println("Please enter an integer.");
			}

			if (selection >= 1 && selection <= pokedex.size()) {
				IO.clear();
				System.out.printf("You chose %s.\n", pokedex.get(selection-1).getName());
				Graphics.displayPokemon(pokedex.get(selection-1).getName(), false);
				displayStatistics(pokedex.get(selection-1));
				System.out.println();

				IO.scrollPrintTextSlow("Would you like to add ", false);
				System.out.print(pokedex.get(selection-1).getName());
				IO.scrollPrintTextSlow(" to your group [1 - Yes / 2 - No]: ", false);

				try {
					choice = kb.nextInt();
				}
				catch(InputMismatchException ex) {
					System.out.println("Please enter an integer.");
				}

				if(choice == 1) {
					choicesLeft -= 1;
					usersPokemons.add(pokedex.get(selection-1));
					pokedex.remove(pokedex.get(selection-1));
				}
				else if(choice == 2) {
					IO.scrollPrintTextSlow("Please chose another pokemon.", true);
				}
				else {
					IO.scrollPrintTextSlow("That is not a valid choice. Please enter a valid number.", true);
				}
			}
			else {
				IO.scrollPrintTextSlow("That is not a valid choice. Please enter a valid number.", true);
			}

			if (choicesLeft == 0) {
				enemiesPokemons = pokedex; // Adds the rest of the pokemons to the enemies group.
				whileLoopFlag = false; // Ends loop when there are no choices left.
			}
		}
	}

	private static void displayStatistics(Pokemon pokemon) {
		// Displays the Pokemons main statistics

		System.out.print("\t");
		IO.scrollPrintTextSlow("HP: ", false);
		System.out.print(pokemon.getHP()+"\n");

		System.out.print("\t");
		IO.scrollPrintTextSlow("Energy: ", false);
		System.out.print(pokemon.getEnergy()+"\n");

		System.out.print("\t");
		IO.scrollPrintTextSlow("Type: ", false);
		System.out.print(pokemon.getType().substring(0, 1).toUpperCase() + pokemon.getType().substring(1)+"\n");

		System.out.print("\t");
		IO.scrollPrintTextSlow("Resistance: ", false);
		System.out.print(pokemon.getResistance().substring(0, 1).toUpperCase() + pokemon.getResistance().substring(1)+"\n");

		System.out.print("\t");
		IO.scrollPrintTextSlow("Weakness: ", false);
		System.out.print(pokemon.getWeakness().substring(0, 1).toUpperCase() + pokemon.getWeakness().substring(1)+"\n");
	}
}