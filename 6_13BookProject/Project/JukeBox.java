/* JukeBox.java 	Java Foundations 
 * 
 * Demonstrates the used of a combo box
 */

/* 
 * M.Thill 
 * CSC 121 
 * 2/4/18
 */

import javax.swing.*;

public class JukeBox {
	
	public static void main(String[] args) {
	
	JFrame frame = new JFrame("Java Juke Box");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JTabbedPane tp = new JTabbedPane();
	tp.addTab("Controls", new Controls());
	tp.addTab("Playlist", new MyList());
	tp.addTab("History", new History());

	//frame.getContentPane().add(new JukeBoxControls());
	frame.getContentPane().add(tp);
	frame.pack();
	frame.setVisible(true);
	
	}
	
}
