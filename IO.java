// Masoud Harati
// io.java
// January 13, 2016
// Basic Input and Output functions which allow text to be
// displayed on the screen and recieving inputs.

import java.util.*;
import java.io.*;

public class IO {
	// Used to improve interaction with user

	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String HEX_YELLOW = "0E";
	public static final String HEX_RESET = "07";

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
			} 
			else {
				// Unix Based Systems

		        System.out.print("\u001b[2J\u001b[H");
		        System.out.flush();
			}
		}
		catch(IOException ex){
			// Error message is displayed

			System.out.println("Unable to clear window. Problem is: ");
			System.err.println(ex.getMessage());
		}
		catch(InterruptedException ex){
			// Error message is displayed

			System.out.println("Unable to clear window.");
			System.err.println(ex.getMessage());
			
		}
	}

	public static void textToYellow() {
		// Converts the text that is displayed on the screen to yellow for Unix and
		// and Windows based systems

		try {
			if(System.getProperty("os.name").startsWith("Windows")) {
				// Windows based systems

				new ProcessBuilder("cmd", "/c", "color"+HEX_YELLOW).inheritIO().start().waitFor();
			}
			else {
				// Unix based systems
				System.out.print(ANSI_YELLOW);
			}
		}
		catch(IOException ex) {
			// Error message is displayed

			System.out.println("Unable to change the color of the window. Windows Based. Problem is: ");
			System.err.println(ex.getMessage());
		}
		catch(InterruptedException ex) {
			// Error message is displayed

			System.out.println("Unable to change the color of the window. Windows Based. Problem is: ");
			System.err.println(ex.getMessage());
		}		
	}

	public static void revertToOriginalColour() {
		// Reverts the colour of the text on the screen to its original colour for Unix and
		// and Windows based systems

		try {
			if(System.getProperty("os.name").startsWith("Windows")) {
				// Windows based systems

				new ProcessBuilder("cmd", "/c", "color"+HEX_RESET).inheritIO().start().waitFor();
			}
			else {
				// Unix based systems
				System.out.print(ANSI_RESET);
			}
		}
		catch(IOException ex) {
			// Error message is displayed

			System.out.println("Unable to change the color of the window. Windows Based. Problem is: ");
			System.err.println(ex.getMessage());
		}
		catch(InterruptedException ex) {
			// Error message is displayed

			System.out.println("Unable to change the color of the window. Windows Based. Problem is: ");
			System.err.println(ex.getMessage());
		}

	}

	public static String intToString(int num) {
		// Converts an integer to a string, this method is mainly used for the print methods
		// within this class

		return String.valueOf(num);
	}

	public static void print(String text) {
		// Duplicates the command "System.out.print()" except the text that it prints will
		// always be yellow for Strings

		textToYellow();
		System.out.print(text);
		revertToOriginalColour();
	}

	public static void println(String text) {
		// Duplicates the command "System.out.println()" except the text that it prints will
		// always be yellow for Strings

		textToYellow();
		System.out.printf(text);
		revertToOriginalColour();
	}

	public static void scrollPrint(String text, int charWait, int lineWait) {
		// Animated print fucntion generic

		textToYellow();
		for(char c : text.toCharArray()) {
			System.out.print(c);
			pause(charWait);
		}

		revertToOriginalColour();
		pause(lineWait);
	}

	public static void scrollPrintText(String text, boolean printExtraLine) {
		// Animated print function for printing text slowly

		scrollPrint(text, 30, 60);

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