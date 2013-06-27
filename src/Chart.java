package apfelkrieg_danielOld;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Chart {
	
	Garden[] garden;
	Char c[];
	int currentMoves = 0, maxApples, totalMoves, currentPlayer, movesPerX, xPerMove, yAmp = 99999;
	boolean skipMoves;
	int values[][];
	
	public Chart(Garden[] arg0, Char[] arg1){
		garden = new Garden[arg0.length];
		c = new Char[arg1.length];
		for(int i = 0; i < arg0.length; i++){
			garden[i] = arg0[i];
		}
		for(int i = 0; i < arg1.length; i++){
			c[i] = arg1[i];
		}
	}
	/*void delay(long msec){
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis()-startTime < msec);
	}
	*/
	
	public void draw(Graphics g){
		g.setColor(Color.GRAY);
		for(int i = 0; i < 6; i++) g.drawLine(30, 60*i + 30, 930, 60*i + 30);
		g.setColor(Color.BLACK);
		g.drawLine(30, 330, 930, 330);
		//Lines structuring Chart
		g.setFont(new Font("SansSerif", Font.PLAIN, 12));
		if(maxApples <= 25){
			g.drawString("25", 10, 36);
			g.drawString("20", 10, 96);
			g.drawString("15", 10, 156);
			g.drawString("10", 10, 216);
			g.drawString("5", 10, 276);
			g.drawString("0", 16, 336);
		} else if(maxApples <= 50){
			g.drawString("50", 10, 36);
			g.drawString("40", 10, 96);
			g.drawString("30", 10, 156);
			g.drawString("20", 10, 216);
			g.drawString("10", 10, 276);
			g.drawString("0", 16, 336);
		} else if(maxApples <= 75){
			g.drawString("75", 10, 36);
			g.drawString("60", 10, 96);
			g.drawString("45", 10, 156);
			g.drawString("30", 10, 216);
			g.drawString("15", 10, 276);
			g.drawString("0", 16, 336);
		} else if(maxApples <= 100){
			g.drawString("100", 4, 36);
			g.drawString("80", 10, 96);
			g.drawString("60", 10, 156);
			g.drawString("40", 10, 216);
			g.drawString("20", 10, 276);
			g.drawString("0", 16, 336);
		}
		g.drawString("€pfel", 10, 360);
		g.setColor(Color.RED);
		g.drawString("Thomas", 730, 360);
		g.setColor(Color.BLUE);
		g.drawString("Alter Sack", 820, 360);
		//Captions
		/*if(skipMoves){
			for(int i = 0; i < totalMoves; i = i + xPerMove){
				drawGraphLine(g, 0, i);
				drawGraphLine(g, 1, i);
			}
		} else {
			for(int i = 0; i < 300; i++){
				drawGraphLine(g, 0, i);
				drawGraphLine(g, 1, i);
			}
		}*/
		for(int i = 0; i < 300; i++){
			if(i+1 <= totalMoves){
				drawGraphLine(g, 0, i);
				drawGraphLine(g, 1, i);
			}
		}
		//Filling in the graphs
	}
	
	public void setCurrentMoves(int i){
		currentMoves = i;
		values[currentPlayer][i] = garden[currentPlayer].totalApples;
	}
	
	public void setTotalMoves(int total, int tom, int old){
		values = new int[2][total+1];
		values[0][0] = tom;
		values[1][0] = old;
		//System.out.println(""+ values[1].length);
		totalMoves = total;
		/*skipMoves = totalMoves >= 300;
		if(totalMoves <= 300) xPerMove = (int)(300/totalMoves);
		else movesPerX = (int)(totalMoves/300);*/
		//System.out.println("" + values[0].length);
	}
	
	public void setMaxApples(int i){
		maxApples = i;
		if(maxApples <= 25) yAmp = 12;
		else if(maxApples <= 50) yAmp = 6;
		else if(maxApples <= 75) yAmp = 4;
		else if(maxApples <= 100) yAmp = 3;
	}
	
	private void drawGraphLine(Graphics g, int c, int i){
		if(c == 0) 
			g.setColor(Color.RED);
		else 
			g.setColor(Color.BLUE);
		
		if(totalMoves > 300)
				g.drawLine(
						i*3 + 30,
						330 - values[c][(int)(i*totalMoves/300)]*yAmp,
						i*3 + 33,
						330 - values[c][(int)((i+1)*totalMoves/300)]*yAmp
						);
		else
				g.drawLine(
						(int)(i*300/totalMoves) + 30,
						330 - values[c][i]*yAmp,
						(int)((i+1)*300/totalMoves + 30),
						330 - values[c][i+1]*yAmp);
		//g.drawLine(xa, ya, xb, yb)
		/*try{
			g.drawLine(i*3 + 30, 330 - values[c][i]*20, (i+1)*3 + 30, 330 - values[c][i+1]*20);
			g.drawLine(i*3 + 29, 330 - values[c][i]*20 - 1, (i+1)*3 + 29, 330 - values[c][i+1]*20 - 1);
		} catch(ArrayIndexOutOfBoundsException e){}*/
		
	}

	public void updateCurrentPlayer(int i) {
		currentPlayer = i;
	}
}
