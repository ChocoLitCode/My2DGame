package main;

// import java.awt.Component;
// import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Main {
	
	public static JFrame window;
	
	public static void main(String[]args) {
		
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("College Odyssey:CpE Edition");
		
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
//		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}
}