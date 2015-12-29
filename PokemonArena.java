// Masoud Harati
// PokemonArena.java
// January 13, 2015
// Contains the main method. Also, includes the main layout of the game.

import java.util.*;
import java.io.*;

public class PokemonArena {

	private static ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();

	public static void main(String[] args) {
		Graphics.start();
		loadPokemons();	
	}



	private static void choose4() {
		// Allows the user to chose the 4 pokemons of their choice by entering
		// the pokemons corresponding number

		System.out.println("We Cool Bro");


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
}