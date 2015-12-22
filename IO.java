// Masoud Harati
// io.java
// January 13, 2015

import java.util.*;
import java.io.*;

public class IO {
	// Used to improve interaction with user

	private static Scanner kb = new Scanner(System.in);

	// public static void main(String[] args) throws IOException, InterruptedException {
	// 	for (int i = 0; i < 100; i++) {
	// 		System.out.println(i);
	// 		pause(50);
	// 	}

	// 	getNum(1, 15);

	// 	scrollPrintArt("At the start of each battle all Pokemon start with 50 energy. This energy is used to activate its attacks, and it can never go above 50. Although each battle is between the player’s four Pokemon and one computer Pokemon, only one Pokemon from each side fights at a time (Pokemon have a code of honor.) At the start of the battle the player chooses which Pokemon will start the battle. The player MUST say “<name>, I choose you” (don't make the user type this in, just display it) Randomly determine which enemy Pokemon they will be fighting from the group of bad guys that they haven't fought yet. The battle is played out in a series of rounds. In each round the user can perform one action and the computer can perform one action. Randomly determine whether the computer or the user goes first. An action is one of the following:");
	// }

	public static void pause(int ms) {
		// Pauses the program for a certain amount of ms
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex){
			// Does nothing
			System.err.println(ex.getMessage);
		}
	}

	public static void clear() {
		// Clears the terminal window to display new text
		try{
			if (System.getProperty("os.name").startsWith("Windows")) {
				// Windows Based Systems

				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				Runtime.getRuntime().exec("cls");

			} else {
				// Unix Based Systems

				final String ANSI_CLS = "\u001b[2J";
		        final String ANSI_HOME = "\u001b[H";
		        System.out.print(ANSI_CLS + ANSI_HOME);
		        System.out.flush();
			}
		}
		catch(IOException ex){
			// Error Messaged is displayed

			System.out.println("Unable to clear window. Windows Based. Problem is: ");
			System.err.println(ex.getMessage());
		}
		catch(InterruptedException ex){
			// Error Message is displayed

			System.out.println("Unable to clear window. Windows Based");
			System.err.println(ex.getMessage());
			
		}
	}

	public static int getNum(int min, int max) {
		// Gets an int as an input from the user and
		// checks if it is a valid input

		int num;

		while(true) {
			
			try {
				num = kb.nextInt();
			} 

			catch (InputMismatchException ex) {
				kb.next();
				System.out.println("That's not a number. Please try again!");
				continue;
			}

			if (num > min && num < max) {
				break;
			} 

			else {
				System.out.println("That number is invalid. Please try again!");
				continue;
			}
		}

		clear();
		return num;
	}

	public static void scrollPrint(String text, int charWait, int lineWait) {
		// Animated print fucntion generic

		for(char c : text.toCharArray()) {
			System.out.print(c);
			pause(charWait);
		}

		pause(lineWait);
		System.out.println();

	}

	public static void scrollPrintTextFast(String text) {
		// Animated print function for printing text fast

		scrollPrint(text, 7, 100);

	}

	public static void scrollPrintTextSlow(String text) {
		// Animated print function for printing text slowly

		scrollPrint(text, 43, 100);

	}

	public static void scrollPrintArt(String text) {
		// Animated print function for printing Pokemon ASCII Art

		scrollPrint(text, 2, 2);

	}
}

