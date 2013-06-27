//import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class StartListener implements ActionListener{
	ApfelkriegWindow am;
	String path[] = new String[2];
	//Graphics g;
	boolean[] output = new boolean[3];
	
	public StartListener(ApfelkriegWindow menu){
		am = menu;
	}
	public void actionPerformed(ActionEvent e) {
		am.updateUI();
		am.setDone(am.checkPrefs());
		if(am.done){
			if(am.prefs[6] == "true"){
				am.setBackground(Color.WHITE);
				am.setContentPane(new JPanel());
				am.setTitle("Apfelkrieg Spielfeld");
				am.setSize(1150, 700);
			} else {
				am.setContentPane(new JPanel());
				am.setTitle("Simulation läuft ... (0%)");
				am.setSize(270, 70);		
			}
			path[0] = am.prefs()[8];
			path[1] = am.prefs()[9];
			output[0] = Boolean.parseBoolean(am.prefs()[5]);
			output[1] = Boolean.parseBoolean(am.prefs()[6]);
			output[2] = Boolean.parseBoolean(am.prefs()[7]);
			new Apfelkrieg(path, output, am).start(Integer.parseInt(am.prefs()[0]));
		}
		
	}
}
