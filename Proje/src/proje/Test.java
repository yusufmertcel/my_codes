package proje;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class Test implements ActionListener{
	int COLUMN_SIZE;
	EquationGenerator newEquation;
	Random random = new Random();
	JFrame frame = new JFrame();
	JButton button = new JButton();
	JLabel label = new JLabel();
	JTextField text = new JTextField();
	Test(){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Menu");
		frame.setSize(new Dimension(200, 300));
		frame.setResizable(true);
		
		Border border = BorderFactory.createLineBorder(new Color(0x123456),5);
		
		COLUMN_SIZE = random.nextInt(3) + 7;
		initialise();
		
		label.setBackground(Color.lightGray);
		label.setText(newEquation.randomEquationgenerator());
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		label.setForeground(new Color(0xFFFFFF));
		label.setFont(new Font("MV Boli",Font.PLAIN,30));
		label.setBorder(border);
		label.setVerticalAlignment(JLabel.CENTER);//set vertical position of icon + text
		label.setHorizontalAlignment(SwingConstants.CENTER);//set horizontal position of icon + text
		label.setOpaque(true);
		
		button.setText("Yeniden Üret");
		button.setFont(new Font("MV Boli",Font.PLAIN,15));
		button.addActionListener(this);
		
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.getContentPane().add(button, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		
		
	}
	public static void main(String[] args) {
		new Test();
	}
	
	public void initialise() {
		if(COLUMN_SIZE == 7) {
			newEquation = new SevenDigit();
		}
		else if(COLUMN_SIZE == 8) {
			newEquation = new EightDigit();
		}
		else {
			newEquation = new NineDigit();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button) {
			COLUMN_SIZE = random.nextInt(3) + 7;
			initialise();
			label.setText(newEquation.randomEquationgenerator());
		}
	}

}
