// Masoud Harati
// Graphics.java
// January 13, 2016
// Text-Based graphical animations used to give the user a better experience,
// the graphics is displayed in ASCII format.

import java.util.*;
import java.io.*;

public class Graphics {
	// Handles the main components of the text based graphics of
	// of the game.

	public static void start() {
		// Displays a text based version of the Pokemon logo
		// and the creator of the game (Masoud Harati).

		Text.clear();
		Text.println("Please Enter Full Screen mode in Terminal/CMD window for best results.");
		Text.pause(2000);
		Text.clear();
		display("logo");
		Text.pause(500);
		Text.clear();
		display("masoud");
		Text.pause(500);
		Text.clear();
	}

	public static void displayPokemon(String name, boolean clear) {
		// Displays a text based version of the pokemon.

		if (clear) {
			Text.clear();
		}

		Text.pause(100);
		display(name);
		Text.pause(100);
	}
	
	public static void displayFinal(boolean won) {
		// Displays a text based version of "You Win!" or
		// "Game Over." depending on the outcome of the game.

		Text.clear();

		if (won) {
			display("win"); // Displays "You Win!"
		} 
		else {
			display("lose"); // Displays "You Lose!"
		}
		
		Text.pause(1000);
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
			    Text.scrollPrintArt(line);
			}

			System.out.println();
			System.out.println();

			in.close();
		}

		catch (FileNotFoundException ex) {
			Text.println("Unable to find file. The extact error is: ");
			System.err.println(ex.getMessage());
		} 

		catch (IOException ex) {
			Text.println("The error is: ");
			System.err.println(ex.getMessage());
		}
	}
}