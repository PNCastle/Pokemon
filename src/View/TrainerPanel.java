package View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class TrainerPanel extends JPanel {
	public TrainerPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblTest = new JLabel("Test");
		add(lblTest);
		
		setPreferredSize(new Dimension(750, 300));
		setBackground(Color.WHITE);
	}

}
