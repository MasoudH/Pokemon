// Masoud Harati
// io.java
// January 13, 2015
// Basic Input and Output functions which allow text to be
// displayed on the screen and recieving inputs.

import java.util.*;
import java.io.*;

public class IO {
	// Used to improve interaction with user

	private static Scanner kb = new Scanner(System.in);

	public static void pause(int ms) {
		// Pauses the program for a certain amount of ms
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex){
			// Does nothing
			System.err.println(ex.getMessage());
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
			// Error Messaged is displayed

			System.out.println("Unable to clear window. Windows Based");
			System.err.println(ex.getMessage());
			
		}
	}

	public static void scrollPrint(String text, int charWait, int lineWait) {
		// Animated print fucntion generic

		for(char c : text.toCharArray()) {
			System.out.print(c);
			pause(charWait);
		}

		pause(lineWait);
	}

	public static void scrollPrintTextFast(String text, boolean printExtraLine) {
		// Animated print function for printing text fast

		scrollPrint(text, 7, 100);

		if (printExtraLine) {
			System.out.println();
		}

	}

	public static void scrollPrintTextSlow(String text, boolean printExtraLine) {
		// Animated print function for printing text slowly

		scrollPrint(text, 30, 100);

		if (printExtraLine) {
			System.out.println();
		}

	}

	public static void scrollPrintArt(String text) {
		// Animated print function for printing Pokemon ASCII Art

		scrollPrint(text, 1, 1);
		System.out.println();

	}
}