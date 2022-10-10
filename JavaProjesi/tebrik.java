package Proje;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class tebrik {

	JFrame tebrik;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tebrik window = new tebrik();
					window.tebrik.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public tebrik() {
		initialize();
	}

	private void initialize() {
		tebrik = new JFrame();
		tebrik.setBounds(100, 100, 450, 300);
		tebrik.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tebrik.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 436, 263);
		tebrik.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tebrikler Oyunu Kazand\u0131n\u0131z !");
		lblNewLabel.setForeground(new Color(255, 250, 205));
		lblNewLabel.setFont(new Font("Sitka Banner", Font.BOLD, 20));
		lblNewLabel.setBounds(92, 36, 273, 160);
		panel.add(lblNewLabel);
	}
}
