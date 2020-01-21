import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

/**
 * File name: Display.java
 * Author: Lily Chua
 * Date:10/3/2017
 * Purpose of the code: Homework 1 HangMan
 * Description: A constructor is created in order to build the panel. A ClickListener 
 * method is created in order to perform actions when the button is clicked. 
 * ShowWordLine method is created in order to show the words to guess on the panel. 
 * Penalty method is created so that when user key in wrong letter, the hangman
 * will be draw accordingly.
 */

public class WordPanel extends JPanel{
	private JTextField showLine ;
	private JTextField inputAns ;
	private JTextField showGuessed;
	private JButton button;
	private JLabel statusLabel;
	private int count=0;
	private String chose;
	private String[] lineArr;
	private char[] lettersArr;
	private Game start = new Game();
	private DrawHangMan man= new DrawHangMan();
	private ArrayList<Character> allInput = new ArrayList<Character>();

	/**
	 * Constructor
	 * @parameter buf - ArrayList<String> - tokenized words
	 */
	public WordPanel(ArrayList<String> buf){
		//set the word and put the letters into array
		chose=start.setWord(buf);
		lettersArr=start.lettersArray();
		
		//set layout for main panel
		setLayout(new BorderLayout());
		man.setPreferredSize(new Dimension(600,600));
		
		//panel for the show word
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel1.add(new JLabel("Word to Guess: "));
		showLine = new JTextField(Arrays.toString(ShowWordLine()));
		panel1.add(showLine);
		showLine.setEditable(false);

		//panel for numbers of letters to guess.
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel2.add(new JLabel("The word to guess has "+lettersArr.length+" letters"));

		//panel for user input and submit button
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel3.add(new JLabel("Guess a letter: "));
		inputAns= new JTextField(10);
		panel3.add(inputAns);
		button = new JButton("Submit");
		panel3.add(button);

		//panel to show user's guessed letters
		JPanel panel4 = new JPanel();
		panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel4.add(new JLabel("Guessed Letters: "));
		showGuessed = new JTextField();
		panel4.add(showGuessed);
		showGuessed.setEditable(false);

		//showWordpanel to add all panels above
		JPanel showWordPanel = new JPanel();
		showWordPanel.setLayout(new BoxLayout(showWordPanel,BoxLayout.PAGE_AXIS));
		showWordPanel.add(panel1);
		showWordPanel.add(panel2);
		showWordPanel.add(panel3);
		showWordPanel.add(panel4);
		
		//status label which shows correct, wrong, won and lost
		statusLabel = new JLabel();
		statusLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		
		//add the showWordPanel and DrawHangMan into the main panel
		add(showWordPanel,BorderLayout.CENTER);
		add(man,BorderLayout.EAST);
		add(statusLabel,BorderLayout.SOUTH);
		
		//set action on button
		ActionListener submit = new ClickListener();
		button.addActionListener(submit);
	}
	
	/**
	 * This is a method to perform action when button is clicked. The method will 
	 * check if the user's input is valid by calling the method from Game. Messages
	 * will be shown if the input is invalid. 
	 * If the input is valid, it will proceed to check if the letter input by user 
	 * is correct. The letter will be shown in showLine if the letter input is correct.
	 * The method will keep count on the number of mistakes and draw the hangman
	 * according to the number of mistakes.
	 */
	public class ClickListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String input=inputAns.getText().toLowerCase();
			char in = input.charAt(0);
			inputAns.setText("");
			//check validality of the input and show messages if wrong
			if(start.checkValidality(input)&&start.checkRepeat(input, allInput)) {
				allInput.add(in);
				if(count<7) {
					//check the letter input by user and show message
					if(start.checkGuess(in)) {
						statusLabel.setText("Right Guess!!");
						//JOptionPane.showMessageDialog(null, "You got the correct letter!");
						showLine.setText(Arrays.toString(ShowWordLine()));
						//check if the user got all the words 
						if(start.checkWin(lineArr)) {
							statusLabel.setText("You Won!!");
							//JOptionPane.showMessageDialog(null, "You Win!!!");
						}
					}else{
						//if the letter input by user is wrong, show message and draw hangman
						count++;
						statusLabel.setText("Wrong Guess!!");
						//JOptionPane.showMessageDialog(null, "You have got the wrong letter.","Warning",JOptionPane.WARNING_MESSAGE);
						penalty(count);
						//if count is 7, then show message 
						if(count==7) {
							button.setEnabled(false);
							statusLabel.setText("You Lost!! The word is "+chose);
							//JOptionPane.showMessageDialog(null, "You Lost!! The word is "+chose,"Warning",JOptionPane.WARNING_MESSAGE);
						}
					}
					//reset show all guessed text. Revalidate and repaint the panel to refresh GUI
					showGuessed.setText(allInput.toString());
					revalidate();
					repaint();
				}
				
			}else if(start.checkValidality(input)==false){
				JOptionPane.showMessageDialog(null, "You need to guess one letter.","Warning",JOptionPane.WARNING_MESSAGE);
			}else if(start.checkRepeat(input,allInput)==false){
				JOptionPane.showMessageDialog(null, "You have guessed that already.","Warning",JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/*
	 * The showWordLine() is created to show the line of words in the panel.
	 * @return String[]
	 */
	public String[] ShowWordLine() {
		lineArr=new String[lettersArr.length];
		for(int i=0;i<lineArr.length;i++) {
			lineArr[i]="_ ";
			if(allInput.contains(lettersArr[i])) {
				lineArr[i]=Character.toString(lettersArr[i]);
			}
		}
		return lineArr;
	}
	/*
	 * The penalty method is to call the method from DrawHangMan according to the number of 
	 * wrong letters input by user.
	 * @parameter count - an int counting the wrong guessed
	 */
	public void penalty(int count) {
		if(count==1) {
			man.drawGallows();
		}else if(count==2) {
			man.drawHead();
		}else if(count==3) {
			man.drawBody();
		}else if(count==4){
			man.drawRightArm();
		}else if(count==5) {
			man.drawLeftArm();
		}else if(count==6) {
			man.drawRightLeg();
		}else if(count==7) {
			man.drawLeftLeg();
		}

	}
}