import java.util.ArrayList;
/*
 * File name: Game.java
 * Author: Lily Chua Li Nee
 * Date:10/1/2017
 * Purpose of the code: Homework 1 HangMan
 * Description: This file is created to store the logic of the game. It is called into the
 * WordPanel to enable the game to be run on GUI. 
 */

public class Game {
	private String chose = null;
	private static char[] lettersArr;

	/*
	 * set the word to be guess by user
	 * @parameter buf - ArrayList<String> - tokenized words
	 */
	public String setWord(ArrayList<String> buf) {
		int ranNum = (int)(Math.random()*buf.size());
		chose = buf.get(ranNum);
		return chose;
	}

	/*
	 * put the letters of the chosen word into array
	 * @return char[]
	 */
	public char[]lettersArray (){
		lettersArr=new char[chose.length()];
		for(int i=0;i<chose.length();i++) {
			lettersArr[i]=chose.charAt(i);
		}
		return lettersArr;
	}

	/*
	 * check the validality of the input
	 * @parameter input - String
	 * @return boolean 
	 */
	public boolean checkValidality(String input) {
		boolean check =false;
		char in = input.charAt(0);
		if(input.length()==1&&Character.isLetter(in)) {
			check=true;
		}else {
			check=false;
		}
		return check;
	}

	/*
	 * check if the input is repeated
	 * @parameter input - String
	 * @parameter allInput - ArrayList<Character> - all letters input by user
	 * @return boolean 
	 */
	public boolean checkRepeat(String input,ArrayList<Character>allInput) {
		char in = input.charAt(0);
		if(!(allInput.contains(in))) {
			return true;
		}
		return false;
	}

	/*
	 * check if the letters input by user is the answer
	 * @parameter input - char
	 * @return boolean 
	 */
	public boolean checkGuess(char input) {
		boolean check = false;
		ArrayList<Character>list = new ArrayList<Character>();
		for(char c:lettersArr) {
			list.add(c);
		}
		if(list.contains(input)) {
			check=true;
		}
		return check;
	}

	/*
	 * check if the user got all the letters
	 * @parameter lineArr - String - the string array showed in panel
	 * @return boolean 
	 */
	public boolean checkWin(String[] lineArr) {
		Boolean c= false;
		String input="";
		for(String in:lineArr) {
			input += in;
		}
		if(input.equals(chose)) {
			c= true;
		}
		return c;
	}
}

