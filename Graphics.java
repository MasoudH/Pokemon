// Masoud Harati
// Graphics.java
// January 13, 2015
// Text-Based Graphical animations used to give the user a better experience

import java.util.*;
import java.io.*;

public class Graphics {
	// Handles the main components of the text based graphics of
	// of the game.

	public static void start() {
		// Displays a text based version of the Pokemon logo
		// and the creator of the game (Masoud Harati).
		IO.clear();
		System.out.println("Please Enter Full Screen mode in Terminal/CMD window for best results.");
		IO.pause(2000);
		IO.clear();
		display("logo");
		IO.pause(500);
		IO.clear();
		display("masoud");
		IO.pause(500);
		IO.clear();
	}

	public static void displayPokemon(String name, boolean clear) {
		// Displays a text based version of the pokemon.
		if (clear) {
			IO.clear();
		}

		IO.pause(100);
		display(name);
		IO.pause(100);
	}
	
	public static void displayFinal(boolean won) {
		// Displays a text based version of "You Win!" or
		// "Game Over." depending on the outcome of the game.

		IO.clear();
		if (won) {
			display("win");
		} 
		else {
			display("lose");
		}
		IO.pause(1000);
	}

	public static void display(String name) {
		// Displays an ASCII version of the Pokemon.

		try {
			name = name.toLowerCase();

			BufferedReader in = new BufferedReader(new FileReader("PokemonArt/"+name+".txt"));

			String line;

			System.out.println();
			System.out.println();

			while((line = in.readLine()) != null)
			{
			    IO.scrollPrintArt(line);
			}

			System.out.println();
			System.out.println();

			in.close();
		}

		catch (FileNotFoundException ex) {
			System.out.println("Unable to find file. The extact error is: ");
			System.err.println(ex.getMessage());
		} 

		catch (IOException ex) {
			System.out.println("The error is: ");
			System.err.println(ex.getMessage());
		}
	}
}