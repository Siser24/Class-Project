/* MyList uses queue first in first out. 
 * 
 */

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

//import Controls.ButtonListener;


public class MyList extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static String localDir ="/Users/merce/eclipse-workspace/6_13BookProject/";
	private File[] musicFile;
	private AudioInputStream audioStream;
	private Clip audioClip;
	private File current;
	private JButton stopButton, playButton;
	
	
	public MyList() {
		super(new GridLayout(1, 0));
		JTable table = new JTable(new MyTableModel());
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		
		
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
		
		musicFile = new File[8];
		musicFile[0] = null;// correspond to "Make a selection.."
		musicFile[1]= f1;
		musicFile[2]= f2;
		musicFile[3]= f3;
		musicFile[4]= f4;
		musicFile[5]= f4;
		musicFile[6]= f5;
		musicFile[7]= f6;
		
		/*ArrayList<AudioClip> list = new ArrayList<AudioClip>();
		list.add((AudioClip) f1);
		list.add((AudioClip) f2);
		list.add((AudioClip) f3);
		list.add((AudioClip) f4);
		list.add((AudioClip) f5);
		list.add((AudioClip) f6);
		
		if(list.isEmpty()) {
			System.out.println("No audio found");
			return;
		}
		
		for(int i = 0; i <list.size(); i++) {
			final AudioClip lists = list.get(i);
			((AudioClip) list).play();
		        
		}
	
	}*/
	/*protected class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
			if (current!= null)
				audioClip.stop();

			if (e.getSource() == playButton) {
				if (current != null)
					audioClip.start();
			}
		}}
	*/}
	class MyTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		
		
		
		private String[] columnNames = {"Song Name", "Album", "Artist"};
		private Object[] [] data = {{"High School Never Ends", "The Great Burrito Extortion Case ", "Bowling For Soup"},
				{"Sweet Child O Mine", "Appetite for Destruction", "Guns N Roses"}, 
				{"7 Years", "Blue Album", "Lucas Graham"}, 
				{"Smells Like Teen Spriit", "Nevermind", "Nirvana"}, 
				{"Every Rose Has Its Thorn", "Open Up and Say... Ahh!", "Poison"},
				{"Hey Jude", "Hey Jude", "The Beatles"}};
		

		public int getColumnCount() {
			return columnNames.length;
		}
		

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
		
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}
		
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
	}
	
	
	
public static void main(String args) {
	
	 
	}
		}

	
		
		
	


