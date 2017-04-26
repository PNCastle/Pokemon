package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

public class TrainerPanel extends JPanel {
	private BufferedImage image;
	private Graphics2D g;
	
	public TrainerPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		setPreferredSize(new Dimension(750, 300));
		setBackground(Color.WHITE);
	}
	
	//draw the graphics
	private void draw(){
		Graphics g2 = getGraphics();
		if (g2 != null){
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
		}
	}
	
}
