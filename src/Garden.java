package apfelkrieg_danielOld;
import java.awt.Color;
import java.awt.Graphics;

public class Garden {
	boolean[][] appleOnField = new boolean[10][10]; //x, y
	int totalApples;
	Graphics g = null;
	boolean leftGarden;
	
	public Garden(int startApples) {
		this.spreadApples(startApples);
	}
	
	public Garden(int startApples, Graphics gra, boolean leftHandGarden) {
		this.spreadApples(startApples);
		g = gra;
		leftGarden = leftHandGarden;
	}

	public void spreadApples(int numberOfApples) {
		totalApples += numberOfApples;
		int xTestPos = 0, yTestPos = 0;
		for(int i = 0; i < numberOfApples; i++){
			while(appleOnField[xTestPos][yTestPos] == true){
				xTestPos = (int)(Math.random()*10);
				yTestPos = (int)(Math.random()*10);
			}
			appleOnField[xTestPos][yTestPos] = true;
			if(g != null){
				if(leftGarden){
					g.setColor(Color.RED);
					g.fillRect(xTestPos*50+65, yTestPos*50+65, 20, 20);
					g.setColor(Color.BLACK);
					g.drawRect(xTestPos*50+65, yTestPos*50+65, 20, 20);
				} else {
					g.setColor(Color.RED);
					g.fillRect(xTestPos*50+65, yTestPos*50+65, 20, 20);
					g.setColor(Color.BLACK);
					g.drawRect(xTestPos*50+65, yTestPos*50+65, 20, 20);
				}
			}
		}
	}
	
	public void removeApple(int x, int y) {
		appleOnField[x][y] = false;
		totalApples--;
		if(g != null){
			g.setColor(new Color(232, 233, 232));
			if(leftGarden) g.fillRect(x*50+65, y*50+65, 20, 20);
			else g.fillRect(x*50+65, y*50+65, 20, 20);
		}
	}

	public boolean isAppleOnField(int x, int y) {
		return appleOnField[x][y];
	}
}
