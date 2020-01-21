import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * File name: ReadFile.java
 * Author: Lily Chua Li Nee
 * Date:10/1/2017
 * Purpose of the code: Homework 1
 * Description: This java file is created to read file and tokenize the words from dictionary file.
 * The tokenize words are added into ArrayList buf.
 */

public class ReadFile {
	public ArrayList<String> openAndReadFile(String filename) {
		ArrayList<String> buf = new ArrayList<String>();
		String line = null;
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line=bufferedReader.readLine())!=null) {
				String[] token = line.split("\\s+");
				buf.addAll(Arrays.asList(token));
			}
			bufferedReader.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return buf;
	}
}
