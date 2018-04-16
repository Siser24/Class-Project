import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Controls extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> musicCombo;
	private JButton stopButton, playButton;
	private File[] musicFile;
	private File current;
	private AudioInputStream audioStream;
	private Clip audioClip;
	private ImageIcon cover;
	private ImageIcon jude;
	private ImageIcon nirvana;
	private ImageIcon blank;
	private ImageIcon HSNE;
	private ImageIcon gunsnroses;
	private ImageIcon Luke;
	private ImageIcon poison;
	
	private ImageIcon[] covers;

	// Music names for Music Combo and History/Playlist display 
	private static String[] musicNames = {"Make a selection...", "High School Never Ends", 
			"Sweet Child O Mine", "7 Years", "Smells Like Teen Spirit", "Every Rose Has Its Thorn", "Hey Jude"};
	
	private static String localDir ="/Users/merce/eclipse-workspace/6_13BookProject/";
	
	
	//Stuff for History Panel 
	private JPanel historyPanel;
	private JLabel historyLabel;
	private JButton goBackButton;
	private JButton cueUp;
		
	private ArrayDeque<Integer> pastHistory;
	private ArrayDeque<Integer> futureHistory;
	

	
	
	public Controls(){
		
		File f1, f2, f3, f4, f5, f6;
		 f1=f2=f3=f4=f5=f6=null;
				
		try {
			f1 = new File(localDir + "BowlingForSoup-HighSchoolNeverEnds.wav");
			f2 = new File(localDir + "GunsNRoses-SweetChildOMine.wav");
			f3 = new File(localDir + "LukasGraham-7Years.wav");
			f4 = new File(localDir + "Nirvana-SmellsLikeTeenSpirit.wav");
			f5 = new File(localDir + "Poison-EveryRoseHasItsThorn.wav");
			f6 = new File(localDir + "TheBeatles-HeyJude.wav");
		}
		catch (Exception e) {
			System.err.println("There is a problem.");
		}
		
		musicFile = new File[7];
		musicFile[0] = null;// correspond to "Make a selection.."
		musicFile[1]= f1;
		musicFile[2]= f2;
		musicFile[3]= f3;
		musicFile[4]= f4;
		musicFile[5]= f5;
		musicFile[6]= f6;
		
		
		
		
		blank = new ImageIcon("Blank.jpg");	
		HSNE= new ImageIcon("HSNE.jpg");
		gunsnroses =  new ImageIcon("GunsNRoses.jpg");
		Luke = new ImageIcon("7years.jpg");
		nirvana = new ImageIcon("TeenSpirit.jpg");
		poison = new ImageIcon("EveryRose.jpg");	
		jude = new ImageIcon("HeyJude.jpg");
		
						
		// Create the list of string for the combo box options 
		musicCombo = new JComboBox<String> (musicNames);
		musicCombo.setBackground(Color.white);
		
		playButton = new JButton ("Play", new ImageIcon ("PlayRS.png"));
		playButton.setBackground(Color.gray);
		stopButton = new JButton ("Stop", new ImageIcon ("PauseRS2.png"));
		stopButton.setBackground(Color.gray);
		goBackButton = new JButton("Go Back", new ImageIcon(""));
		
		
		
		pastHistory = new ArrayDeque<Integer> ();
		futureHistory = new ArrayDeque<Integer> ();

		historyLabel = new JLabel("Previous Song: No Songs left!");
		
		historyPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(pastHistory.isEmpty())
					historyLabel.setText("Previous Song: No songs left!");
				else {
					String lastSong = musicNames[pastHistory.peek()];
					historyLabel.setText("Previous Song: " + lastSong);
				}
				
			}
		};
			
		historyPanel.setPreferredSize(new Dimension(300, 100));
		historyPanel.add(historyLabel);
		historyPanel.add(goBackButton);
		
		setPreferredSize (new Dimension(600	,600));
		setBackground(Color.WHITE);
		add(musicCombo);
		add(playButton);
		add(stopButton);
		add(historyPanel);	
				
		musicCombo.addActionListener(new ComboListener());
		stopButton.addActionListener(new ButtonListener());
		playButton.addActionListener(new ButtonListener());
		goBackButton.addActionListener(new GoBackListener());
		current = null;
		cover=blank;
	}	
	
	
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	cover.paintIcon(this, g, 100, 200);
}

	private class ComboListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
			if (current!= null) {
				audioClip.close();
				audioStream.close();
			}
				
			int selected = musicCombo.getSelectedIndex();
			current = musicFile[selected];
			if(selected != 0) {
				pastHistory.push(selected);
			}
			String currentString = musicNames[musicCombo.getSelectedIndex()];
			
			if (current != null) {
				System.out.println(currentString.toString());
				
				if (currentString.equals("Smells Like Teen Spirit")) {
					cover = nirvana;
					repaint();
				} 
				else if (currentString.equals("Hey Jude")) {
					cover = jude;
					repaint();
				}
				else if (currentString.equals("High School Never Ends")) { 
					cover = HSNE;
					repaint();
				}
				else if (currentString.equals("7 Years")) { 

					cover = Luke;
					repaint();
				}
				else if (currentString.equals("Sweet Child O Mine")) {
					cover = gunsnroses;
					repaint();
				}
				else if (currentString.equals("Every Rose Has Its Thorn")) {
					cover = poison;
					repaint();
				}
				
				
				audioStream = AudioSystem.getAudioInputStream(current);
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				audioClip = (Clip) AudioSystem.getLine(info);
				audioClip.open(audioStream);		
			}	
	} catch (IOException exception) {
		System.err.println(exception);
	} catch (UnsupportedAudioFileException exception) {
		System.err.println(exception);
	} catch (LineUnavailableException exception) {
		System.err.println(exception);
	}
}
		
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (current != null)
				audioClip.stop();

			if (e.getSource() == playButton) {
				if (current != null)
					audioClip.start();
			}
		}
	}
	private class GoBackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!pastHistory.isEmpty()) {
				futureHistory.push(pastHistory.pop());
				repaint();
			}
		}
	}
}


