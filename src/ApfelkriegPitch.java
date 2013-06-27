package apfelkrieg_danielOld;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ApfelkriegPitch {
	
	Garden[] garden;
	Char c[];
	int dice, currentPlayer;
	
	public ApfelkriegPitch(Garden[] arg0, Char[] arg1){
		garden = new Garden[arg0.length];
		c = new Char[arg1.length];
		for(int i = 0; i < arg0.length; i++){
			garden[i] = arg0[i];
		}
		for(int i = 0; i < arg1.length; i++){
			c[i] = arg1[i];
		}
	}
	
	public void draw(Graphics g){
		//paints field the char is to walk into gray
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				g.setColor(Color.BLACK);
				g.drawLine(50, y*50+50, 550, y*50+50);
				//All horizontal lines in the first garden
				g.drawLine(x*50+50, 50, x*50+50, 550);
				//All vertical lines in the first garden
				g.drawLine(600, y*50+50, 1100, y*50+50);
				//All horizontal lines in the second garden
				g.drawLine(x*50+600, 50, x*50+600, 550);
				//All vertical lines in the second garden
				g.setColor(Color.BLACK);
				g.fillRect(c[0].xPos*50+65, c[0].yPos*50+65, 20, 20);
				g.fillRect(c[1].xPos*50+615, c[1].yPos*50+65, 20, 20);
				//places player
				g.clearRect(x*50+64, y*50+64, 22, 22);
				g.clearRect(x*50+614, y*50+64, 22, 22);
				//removes all boxes in the fields
				if(garden[0].isAppleOnField(x,y)){
					g.setColor(Color.RED);
					g.fillRect(x*50+65, y*50+65, 20, 20);
					g.setColor(Color.BLACK);
					g.drawRect(x*50+65, y*50+65, 20, 20);
				}
				//places Apples on first garden
				if(garden[1].isAppleOnField(x,y)){
					g.setColor(Color.RED);
					g.fillRect(x*50+615, y*50+65, 20, 20);
					g.setColor(Color.BLACK);
					g.drawRect(x*50+615, y*50+65, 20, 20);
				}
				//places Apples on second garden
			}
		}
		g.setColor(Color.BLACK);
		g.drawLine(550, 50, 550, 550);
		g.drawLine(1100, 50, 1100, 550);
		g.drawLine(50, 550, 550, 550);
		g.drawLine(600, 550, 1100, 550);
		//The for loops only range from 0,0 to 9,9 so this ugly fix will have to do :(
		switch(dice){
			case 1:
				g.setColor(Color.DARK_GRAY);
				g.fillRect(1040, 590, 60, 60);
				g.setColor(Color.WHITE);
				g.fillRect(1065, 615, 10, 10);
				break;				
			case 2:
				g.setColor(Color.DARK_GRAY);
				g.fillRect(1040, 590, 60, 60);
				g.setColor(Color.WHITE);
				g.fillRect(1045, 595, 10, 10);
				g.fillRect(1085, 635, 10, 10);
				break;
			case 3:
				g.setColor(Color.DARK_GRAY);
				g.fillRect(1040, 590, 60, 60);
				g.setColor(Color.WHITE);
				g.fillRect(1045, 595, 10, 10);
				g.fillRect(1065, 615, 10, 10);
				g.fillRect(1085, 635, 10, 10);
				break;
			case 4:
				g.setColor(Color.DARK_GRAY);
				g.fillRect(1040, 590, 60, 60);
				g.setColor(Color.WHITE);
				g.fillRect(1045, 595, 10, 10);
				g.fillRect(1045, 635, 10, 10);
				g.fillRect(1085, 595, 10, 10);
				g.fillRect(1085, 635, 10, 10);
				break;
			case 5:
				g.setColor(Color.DARK_GRAY);
				g.fillRect(1040, 590, 60, 60);
				g.setColor(Color.WHITE);
				g.fillRect(1045, 595, 10, 10);
				g.fillRect(1045, 635, 10, 10);
				g.fillRect(1065, 615, 10, 10);
				g.fillRect(1085, 595, 10, 10);
				g.fillRect(1085, 635, 10, 10);
				break;
			case 6:
				g.setColor(Color.DARK_GRAY);
				g.fillRect(1040, 590, 60, 60);
				g.setColor(Color.WHITE);
				g.fillRect(1045, 595, 10, 10);
				g.fillRect(1045, 615, 10, 10);
				g.fillRect(1045, 635, 10, 10);
				g.fillRect(1085, 595, 10, 10);
				g.fillRect(1085, 615, 10, 10);
				g.fillRect(1085, 635, 10, 10);
				break;
			default:
				g.setColor(Color.RED);
				g.drawString("Error", 635, 1030);
				
		}
		//draws Dice
		g.clearRect(49, 580, 500, 70);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Display", 0, 15));
		if(currentPlayer == 0){
			g.drawString("Thomas (to Move): ", 50, 600);
		} else {
			g.drawString("Thomas: ", 50, 600);
		}
		g.drawString("Collected apples: " + c[0].appleCache, 50, 620);
		g.drawString("Total apples in garden: " + garden[0].totalApples, 50, 640);
		if(currentPlayer == 1){
			g.drawString("Old (to Move): ", 250, 600);
		} else {
			g.drawString("Old: ", 250, 600);
		}
		g.drawString("Collected apples: " + c[1].appleCache, 250, 620);
		g.drawString("Total apples in garden: " + garden[1].totalApples, 250, 640);
		
	}

	public void updateDice(int arg0) {
		dice = arg0;
	}

	public void updateCurrentPlayer(int arg0) {
		currentPlayer = arg0;		
	}
}
