import java.awt.Graphics;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Apfelkrieg {
	Garden[] garden;
	Char c[];
	//Pitch p;
    Graphics g;
	int numberPlayers = 2;
	int dice;
	FileWriter outFile1, outFile2;
    PrintWriter pw1, pw2;
    ApfelkriegWindow w;
    boolean outFiles, simulation, graph, charsAI;
    
	
	public Apfelkrieg(String[] path, boolean[] output, ApfelkriegWindow am){
		outFiles = output[0];
		simulation = output[1];
		graph = output[2];
		w = am;
		g = w.getGraphics();
		System.out.println("" + am != null);
		if(outFiles){
			try {
				outFile1 = new FileWriter(path[0]);
				outFile2 = new FileWriter(path[1]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw1 = new PrintWriter(outFile1);
			pw2 = new PrintWriter(outFile2);
		}
		garden = new Garden[2];
		garden[0] = new Garden(Integer.parseInt(w.prefs()[1]));
		garden[1] = new Garden(Integer.parseInt(w.prefs()[2]));
		//garden[0] = new Garden(10);
		//garden[1] = new Garden(5);
		charsAI = w.prefs()[10] == "true";
		c = new Char[2];
		c[0] = new Char(garden[0], garden[1], charsAI);
		c[1] = new Char(garden[1], garden[0],  charsAI);
		
		w.setSimObjects(garden, c);
		if(w != null) w.paint(g);
	}
	
	public void start(int iterations) {
		w.setTotalMoves(iterations);
		w.chart.setMaxApples(Integer.parseInt(w.prefs()[1]) + Integer.parseInt(w.prefs()[2]));
		
		for(int i=0; i < iterations; i++){
			for(int j=0; j < numberPlayers; j++){
				if(simulation || graph) w.updateCurrentPlayer(j);
				if(graph || outFiles)w.setCurrentMoves(i+1);
				if(j == 0){
					dice = (int)(Math.random()*5 + 1 + Integer.parseInt(w.prefs()[3]));
					//Integer ranging from 2 - 7
					if(simulation) w.updateDice(dice-Integer.parseInt(w.prefs()[3]));
				} else if(j == 1){
					dice = (int)(Math.random()*5 + 1 + Integer.parseInt(w.prefs()[4]));
					if(simulation) w.updateDice(dice-Integer.parseInt(w.prefs()[4]));
				}
				if(charsAI) c[j].setDirection(dice);
				for(int k = 0; k < dice; k++){
					if(simulation) delay(400);
					if(!charsAI) c[j].detectApple();
					c[j].walk();
					if(w != null) w.paint(g);
					
				}
		        if(outFiles){
		        	pw1.println("" + garden[0].totalApples);
		        	pw2.println("" + garden[1].totalApples);
		        }
				c[j].throwApples();
			}
		}
		if(outFiles){
			pw1.close();
			pw2.close();
		}
		if(graph){
			w.setSize(960, 390);
			w.setTitle("Auswertung");
			w.paintGraph = true;
			w.paint(g);
		}
		//System.exit(0);
		
	}
	
	
	void delay(long msec){
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis()-startTime < msec){}
	}

}

