import javax.swing.*;

/**
 * File name: Display.java
 * Author: Lily Chua
 * Date:10/1/2017
 * Purpose of the code: Homework 1 HangMan
 * Description: A main method is created in this file. Dictionary file name that will 
 * be input by user is passed through args through the main method to the 
 * openAndReadFile method. JPanel and JFrame is instantiated. The main method will 
 * show the frame and perform the game. 
 */

public class Display extends JFrame{
	public static final int FRAME_WIDTH=1000;
	public static final int FRAME_HEIGHT=700;
	
	public static void main(String[]args) {
		if (args.length == 1) {
			ReadFile fr = new ReadFile();
			JPanel panel = new WordPanel(fr.openAndReadFile(args[0]));
			JFrame frame = new JFrame();
			frame.add(panel);
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setTitle("HangMan");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}

		else{
			System.out.println("Error. Ending. Please run in command prompt.");
			System.out.println("To compile: javac Display.java");
			System.out.println("To run: java Display dic.txt");
			System.exit(0);
		}
	}
}
