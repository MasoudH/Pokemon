// Masoud Harati
// PokemonCreator.java
// January 12, 2015

import java.io.*;

public class PokemonCreator {

	private final String fileName = "pokemon.txt";

	public void readFile() {

		FileReader in = null;

		try {
			in = new FileReader(fileName);
		}
		catch (IOException ex) {
			System.out.println("Unable to find file. The extact error is: ");
			System.err.println(ex.getMessage());
		}
		finally {
			if (in != null) {
           		in.close();
         	}
		}

	}
}