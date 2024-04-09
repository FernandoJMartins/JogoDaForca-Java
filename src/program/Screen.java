package program;

import javax.swing.JFrame;

public class Screen extends JFrame{

	public Screen() {
		setTitle("Jogo da Forca");
		setVisible(true);
		setSize(640, 480);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
