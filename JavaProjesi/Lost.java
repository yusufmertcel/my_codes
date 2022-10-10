package Proje;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Lost {

	JFrame lost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lost window = new Lost();
					window.lost.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Lost() {
		initialize();
	}

	private void initialize() {
		lost = new JFrame();
		lost.setBounds(100, 100, 450, 300);
		lost.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 127, 80));
		panel.setForeground(new Color(255, 99, 71));
		panel.setBounds(10, 10, 416, 243);
		lost.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Oyunu Kaybetiniz!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Sitka Banner", Font.BOLD, 20));
		lblNewLabel.setBounds(99, 42, 209, 141);
		panel.add(lblNewLabel);
	}
}
