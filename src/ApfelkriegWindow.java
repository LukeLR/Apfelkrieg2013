import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileFilter;

import apfelkrieg_danielOld.lang.*;

public class ApfelkriegWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel cp;
	JCheckBox charsAI, outFiles, visualize, showSim, showGraphs;
	JTextField numberSteps, thomasApples, oldApples, vThomas, vOld, outFile1, outFile2;
	JLabel thomasL, oldL, numberStepsL, thomasApplesL, oldApplesL, vThomasL, vOldL;
	JButton file1Browse, file2Browse, quit, start;
	JProgressBar pb;
	JSeparator s1, s2;
	String lang;
	boolean doOutFiles = true;
	boolean doSim = false;
	boolean doGraphs = true;
	public boolean done = false;
	boolean stepsValid = true, thomasApplesValid = true, oldApplesValid = true, vThomasValid = true, vOldValid = true, path1Valid = true, path2Valid = true;
	boolean paintGraph = false;
	String[] prefs = new String[11];
	Garden[] garden;
	Char[] c;
	int dice, currentPlayer, totalMoves, currentMoves;
	ApfelkriegPitch ap;
	Chart chart;
	String noStepsL, applesL, vL, vTip, playersAI, aiTip,
	 outFilesL, outFilesTip, outFile1L, outFile2L, fileBrowse, 
	 visualizeL, showGraphsL, graphsTip, showSimL, simTip,
	 quitL, startL,
	 setUpWindow, pitchWindow, progressWindow, graphWindow, fileChoose1, fileChoose2;
	
	
	public ApfelkriegWindow(){
		/*addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				running = false;
			}
		});*/
		if(Locale.getDefault() == Locale.GERMAN || Locale.getDefault() == Locale.GERMANY)
			lang = "ger";
		else lang = "en";
		setVisible(false);
		initUI();
		updateUI();
		repaint();
		setVisible(true);
	}

	public void updateDice(int arg0) {
		ap.updateDice(arg0);
	}

	public void updateCurrentPlayer(int arg0) {
		ap.updateCurrentPlayer(arg0);
		chart.updateCurrentPlayer(arg0);
	}
	public void paint(Graphics g){
		if(done){
			if(prefs[6] == "true" && !paintGraph){
				ap.draw(g);
			} else if(prefs[6] != "true" && !paintGraph){
				g.setColor(Color.BLUE);
				if(currentMoves >= 1) g.fillRoundRect(10, 30, (int)(250 * currentMoves/totalMoves), 30, 7, 7);
				g.setColor(Color.BLACK);
				g.drawRoundRect(10, 30, 250, 30, 7, 7);
				System.out.println("" + currentMoves);
				//progress bar
				if(currentMoves > 0) setTitle(progressWindow + 100*currentMoves/totalMoves + "%)");
			} else if(paintGraph){
				setBackground(Color.WHITE);
				g.clearRect(0, 0, getContentPane().getHeight(), getContentPane().getWidth());
				chart.draw(g);
			}
		} else {
			//----------------------positioning components-----------------------
			numberSteps.setBounds(30, 30, 80, 25);
			numberStepsL.setBounds(110, 30, 100, 25);
			thomasL.setBounds(30, 90, 60, 25);
			oldL.setBounds(300, 90, 90, 25);
			thomasApples.setBounds(30, 120, 60, 25);
			thomasApplesL.setBounds(90, 120, 90, 25);
			oldApples.setBounds(300, 120, 60, 25);
			oldApplesL.setBounds(360, 120, 90, 25);
			vThomas.setBounds(30, 150, 60, 25);
			vThomasL.setBounds(90, 150, 120, 25);
			vOld.setBounds(300, 150, 60, 25);
			vOldL.setBounds(360, 150, 120, 25);
			charsAI.setBounds(30, 210, 150, 25);
			s1.setBounds(30, 245, 420, 10);
			outFiles.setBounds(30, 270, 400, 25);
			outFile1.setBounds(60, 330, 270, 25);
			file1Browse.setBounds(330, 330, 120, 25);
			outFile2.setBounds(60, 360, 270, 25);
			file2Browse.setBounds(330, 360, 120, 25);
			s2.setBounds(30, 425, 420, 10);
			visualize.setBounds(30, 450, 240, 25);
			showGraphs.setBounds(60, 480, 400, 25);
			showSim.setBounds(60, 510, 400, 25);
			quit.setBounds(30, 580, 90, 25);
			start.setBounds(360, 580, 90, 25);
		}
	}
	public boolean checkPrefs(){
		path1Valid = (new File(outFile1.getText()).exists() || prefs[5] == "false");
		path2Valid = (new File(outFile2.getText()).exists() || prefs[5] == "false");
		try {
			prefs[0] = "" + (int)(Double.parseDouble(numberSteps.getText()));
			
		}	catch (NumberFormatException e) {
			stepsValid = false;
		}
		try {
			prefs[1] = "" + (int)(Double.parseDouble(thomasApples.getText())); 
		}	catch (NumberFormatException e) {
			thomasApplesValid = false;
		}
		try {
			prefs[2] = "" + (int)(Double.parseDouble(oldApples.getText())); 
		}	catch (NumberFormatException e) {
			oldApplesValid = false;
		}
		try {
			prefs[3] = "" + (int)(Double.parseDouble(vThomas.getText())); 
			vThomasValid = Integer.parseInt(prefs[3]) >= -1;
		}	catch (NumberFormatException e) {
			vThomasValid = false;
		}
		try {
			prefs[4] = "" + (int)(Double.parseDouble(vOld.getText()));
			vThomasValid = Integer.parseInt(prefs[4]) >= -1;
		}	catch (NumberFormatException e) {
			vOldValid = false;
		}
		
		if(Integer.parseInt(prefs[1]) + Integer.parseInt(prefs[2]) >= 100)
				thomasApplesValid = oldApplesValid = false;
		prefs[8] = outFile1.getText();
		prefs[9] = outFile2.getText();
		return (stepsValid && thomasApplesValid && oldApplesValid && vThomasValid && vOldValid && path1Valid && path2Valid);
	}
	
	public void setSimObjects(Garden[] arg0, Char[] arg1){
		ap = new ApfelkriegPitch(arg0, arg1);
		chart = new Chart(arg0, arg1);
	}
	
	public String[] prefs(){
		return prefs;
	}
	
	public void setDone(boolean arg0){
		done = arg0;
	}
	
	public void setCurrentMoves(int i){
		currentMoves = i;
		chart.setCurrentMoves(i);
	}
	
	public void initUI(){
		//setting language-specific strings
		if(Locale.getDefault() == Locale.GERMAN || Locale.getDefault() == Locale.GERMANY){
			 noStepsL = lang_GER.numberStepsL;
			 applesL = lang_GER.applesL;
			 vL = lang_GER.vL;
			 vTip = lang_GER.vTip;
			 playersAI = lang_GER.charsAI;
			 aiTip = lang_GER.aiTip;
			 outFilesL = lang_GER.outFiles;
			 outFilesTip = lang_GER.outFilesTip;
			 outFile1L = lang_GER.outFile1;
			 outFile2L = lang_GER.outFile2;
			 fileBrowse = lang_GER.fileBrowse;
			 visualizeL = lang_GER.visualize;
			 showGraphsL = lang_GER.showGraphs;
			 graphsTip = lang_GER.graphsTip;
			 showSimL = lang_GER.showSim;
			 simTip = lang_GER.simTip;
			 setUpWindow = lang_GER.setUpWindow;
			 pitchWindow = lang_GER.pitchWindow;
			 progressWindow = lang_GER.progressWindow;
			 graphWindow = lang_GER.graphWindow;
			 fileChoose1 = lang_GER.fileChoose1;
			 fileChoose2 = lang_GER.fileChoose2;
			 quitL = lang_GER.quit;
			 startL = lang_GER.start;
		 } else {
			 noStepsL = lang_EN.numberStepsL;
			 applesL = lang_EN.applesL;
			 vL = lang_EN.vL;
			 vTip = lang_EN.vTip;
			 playersAI = lang_EN.charsAI;
			 aiTip = lang_EN.aiTip;
			 outFilesL = lang_EN.outFiles;
			 outFilesTip = lang_EN.outFilesTip;
			 outFile1L = lang_EN.outFile1;
			 outFile2L = lang_EN.outFile2;
			 fileBrowse = lang_EN.fileBrowse;
			 visualizeL = lang_EN.visualize;
			 showGraphsL = lang_EN.showGraphs;
			 graphsTip = lang_EN.graphsTip;
			 showSimL = lang_EN.showSim;
			 simTip = lang_EN.simTip;
			 setUpWindow = lang_EN.setUpWindow;
			 pitchWindow = lang_EN.pitchWindow;
			 progressWindow = lang_EN.progressWindow;
			 graphWindow = lang_EN.graphWindow;
			 fileChoose1 = lang_EN.fileChoose1;
			 fileChoose2 = lang_EN.fileChoose2;
			 quitL = lang_EN.quit;
			 startL = lang_EN.start;
		 }
		setContentPane(cp = new JPanel());
		cp.setLayout(null);
		//Setting up file chooser
		final JFileChooser fc = new JFileChooser();	
		fc.setMultiSelectionEnabled(false);
		fc.setFileHidingEnabled(true);
		fc.addChoosableFileFilter(new FileFilter(){

			@Override
			public boolean accept(File arg0) {
				return 	arg0.getName().endsWith(".txt") ||
						arg0.getName().endsWith(".TXT") ||
						arg0.getName().endsWith(".text") ||
						arg0.getName().endsWith(".TEXT") ||
						(!arg0.getName().contains(".") && !arg0.isDirectory());
			}

			@Override
			public String getDescription() {
				if(Locale.getDefault() == Locale.GERMAN || Locale.getDefault() == Locale.GERMANY)
					return "Textdateien";
				else
					return "plain text files";
			}		
		});
		//Setting the default values of the check boxes
		prefs[5] = "true";
		prefs[6] = "false";
		prefs[7] = "false";
		prefs[10] = "false";
		//Adding newly constructed Components to ContentPane		
		cp.add(numberSteps = new JTextField("21"));
		cp.add(numberStepsL = new JLabel(noStepsL));
		cp.add(thomasL = new JLabel("Thomas"));
		cp.add(oldL = new JLabel("Alter Herr"));
		cp.add(thomasApples = new JTextField("10"));
		cp.add(thomasApplesL = new JLabel(applesL));
		cp.add(oldApples = new JTextField("5"));
		cp.add(oldApplesL = new JLabel(applesL));
		cp.add(vThomas = new JTextField("1"));
		cp.add(vThomasL = new JLabel(vL));
		cp.add(vOld = new JTextField("-1"));
		cp.add(vOldL = new JLabel(vL));
		cp.add(charsAI = new JCheckBox(playersAI));
		cp.add(s1 = new JSeparator(JSeparator.HORIZONTAL));
		cp.add(outFiles = new JCheckBox(outFilesL, true));
		cp.add(outFile1 = new JTextField(outFile1L));
		cp.add(file1Browse = new JButton(fileBrowse));
		cp.add(outFile2 = new JTextField(outFile2L));
		cp.add(file2Browse = new JButton(fileBrowse));
		cp.add(s2 = new JSeparator(JSeparator.HORIZONTAL));
		cp.add(visualize = new JCheckBox(visualizeL));
		cp.add(showSim = new JCheckBox(showSimL, false));
		cp.add(showGraphs = new JCheckBox(showGraphsL, false));
		cp.add(quit = new JButton(quitL));
		cp.add(start = new JButton(startL));
		//random adjustment
		if(Locale.getDefault() != Locale.GERMAN && Locale.getDefault() != Locale.GERMANY)
			oldL.setText("Elderly Man");
		//pb = new JProgressBar();
		//Setting the ToolTips
		vThomas.setToolTipText(vTip);
		vOld.setToolTipText(vTip);
		vThomasL.setToolTipText(vTip);
		vOldL.setToolTipText(vTip);
		charsAI.setToolTipText(aiTip);
		outFiles.setToolTipText(outFilesTip);
		showGraphs.setToolTipText(graphsTip);
		showSim.setToolTipText(simTip);
		//Adding ActionListeners to various components
		charsAI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(prefs[10] == "false") prefs[10] = "true";
				else prefs[10] = "false";
				//toggles
				updateUI();
			}
		});
		outFiles.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(prefs[5] == "false") prefs[5] = "true";
				else prefs[5] = "false";
				//toggles
				updateUI();
			}
		});
		visualize.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				updateUI();
			}
		});
		showSim.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(prefs[6] == "true") prefs[6] = "false";
				else prefs[6] = "true";
				//toggles
				updateUI();
			}
		});
		showGraphs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(prefs[7] == "true") prefs[7] = "false";
				else prefs[7] = "true";
				//toggles
			}
		});
		file1Browse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fc.setDialogTitle(fileChoose1);
				int returnVal = fc.showOpenDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION)
				outFile1.setText(fc.getSelectedFile().getAbsolutePath());
			}
		});
		file2Browse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fc.setDialogTitle(fileChoose2);
				int returnVal = fc.showOpenDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION)
				outFile2.setText(fc.getSelectedFile().getAbsolutePath());
				updateUI();
			}
		});
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		start.addActionListener(new StartListener(this));
		//--------------------------Setting up Window----------------------------
		setTitle(setUpWindow);
		setSize(480, 645);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void updateUI(){
		//-----Re-coloring the text fields if their contents are invalid-----
		if(!stepsValid) numberSteps.setBackground(Color.YELLOW);
		else numberSteps.setBackground(Color.WHITE);
		if(!thomasApplesValid) thomasApples.setBackground(Color.YELLOW);
		else thomasApples.setBackground(Color.WHITE);
		if(!oldApplesValid) oldApples.setBackground(Color.YELLOW);
		else oldApples.setBackground(Color.WHITE);
		if(!vThomasValid) vThomas.setBackground(Color.YELLOW);
		else vThomas.setBackground(Color.WHITE);
		if(!vOldValid) vOld.setBackground(Color.YELLOW);
		else vOld.setBackground(Color.WHITE);
		if(!path1Valid) outFile1.setBackground(Color.YELLOW);
		else outFile1.setBackground(Color.WHITE);
		if(!path2Valid) outFile2.setBackground(Color.YELLOW);
		else outFile2.setBackground(Color.WHITE);
		//-------------------Miscellaneous adjustments-----------------------
		//-----------Setting alignment in text fields with numbers-----------
		numberSteps.setHorizontalAlignment(JTextField.RIGHT);
		thomasApples.setHorizontalAlignment(JTextField.RIGHT);
		oldApples.setHorizontalAlignment(JTextField.RIGHT);
		vThomas.setHorizontalAlignment(JTextField.RIGHT);
		vOld.setHorizontalAlignment(JTextField.RIGHT);
		//-------------------Enabling low-level components-------------------
		showGraphs.setEnabled(visualize.isSelected());
		showSim.setEnabled(visualize.isSelected());
		outFile1.setEnabled(outFiles.isSelected());
		outFile2.setEnabled(outFiles.isSelected());
		file1Browse.setEnabled(outFiles.isSelected());
		file2Browse.setEnabled(outFiles.isSelected());
		//--------------------Setting font of headlines----------------------
		thomasL.setFont(new Font("Display", Font.BOLD, 14));
		oldL.setFont(new Font("Display", Font.BOLD, 14));
	}
	
	public void setTotalMoves(int i){
		totalMoves = i;
		chart.setTotalMoves(i, Integer.parseInt(prefs[1]), Integer.parseInt(prefs[2]));
	}
}
