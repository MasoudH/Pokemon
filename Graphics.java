// Masoud Harati
// Graphics.java
// January 13, 2015

import java.util.*;
import java.io.*;

public class Graphics {
	public static void main(String[] args) {
		// Scanner kb = new Scanner(System.in);
		// String pokemonName = kb.nextLine();
		start();
		displayPokemon("pikachu");
		displayFinal(false);
	}

	public static void start() {
		IO.clear();
		display("logo");
		IO.pause(500);
		IO.clear();
		display("masoud");
		IO.pause(500);
	}

	public static void displayPokemon(String name) {
		IO.clear();
		IO.pause(500);
		display(name);
		IO.pause(500);
	}
	
	public static void displayFinal(boolean won) {
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
		// Displays the ASCII art of the Pokemon

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