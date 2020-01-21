import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/*
 * File name: DrawHangMan.java
 * Author: Lily Chua Li Nee
 * Date:10/2/2017
 * Purpose of the code: Homework 1 HangMan
 * Description: This java file is created in order to draw HangMan. 
 */

public class DrawHangMan extends JComponent{
	private Graphics2D g2;
	private boolean gallows = false;
	private boolean head = false;
	private boolean body = false;
	private boolean leftArm = false;
	private boolean rightArm = false;
	private boolean leftLeg = false;
	private boolean rightLeg = false;
	
	public void paintComponent(Graphics g){
		g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(8));
		if(gallows) {
			g2.drawLine(100,100,100,0);
			g2.drawLine(100,0,350,0);
			g2.drawLine(350,0,350,500);
			g2.drawLine(100,500,500,500);
		}
		if(head) {
			g2.drawOval(50, 100, 100, 100);
		}
		if(body) {
			g2.drawLine(100,200,100,300);
		}
		if(leftArm){
			g2.drawLine(100,200,50,250);
		}
		if(rightArm) {
			g2.drawLine(100,200,150,250);
		}
		if(leftLeg) {
			g2.drawLine(50,400,100,300);
		}
		if(rightLeg) {
			g2.drawLine(150,400,100,300);
		}
	}
	
	public void drawGallows(){
		gallows=true;
		repaint();
	}
	public void drawHead(){
		head=true;
		repaint();
	}
	public void drawBody(){
		body=true;
		repaint();
	}
	public void drawLeftArm(){
		leftArm=true;
		repaint();
	}
	public void drawRightArm(){
		rightArm=true;
		repaint();
	}
	public void drawLeftLeg(){
		leftLeg=true;
		repaint();
	}

	public void drawRightLeg(){
		rightLeg=true;
		repaint();
	}
}