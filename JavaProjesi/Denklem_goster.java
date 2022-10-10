package Proje;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Denklem_goster {

	JFrame goster;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Denklem_goster window = new Denklem_goster();
					window.goster.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Denklem_goster() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Denklem b=new Denklem();
		b.start();
		goster = new JFrame();
		goster.setBounds(100, 100, 450, 300);
		goster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		goster.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setForeground(new Color(255, 0, 0));
		panel.setBounds(77, 25, 293, 64);
		goster.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(b.getIfade());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(176, 224, 230));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(23, 10, 229, 44);
		panel.add(lblNewLabel);
		
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(124, 135, 197, 54);
		goster.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Denklem Üret");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Denklem b=new Denklem();
				b.start();
				lblNewLabel.setText(b.getIfade());
			}
		});
		btnNewButton.setBounds(0, 0, 197, 54);
		panel_1.add(btnNewButton);
	}
}
