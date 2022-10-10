package proje;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Main extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3792634174385329312L;
	gamescreen screen;
	Cell[][] cellvalues;
	LinkedList<Statistics> istatistikler = new LinkedList<Statistics>();
	
	public Main() throws FileNotFoundException, ClassNotFoundException, IOException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Menu");
		this.setSize(new Dimension(502, 583));
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0xc72451));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(20);
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Nerdle");
		lblNewLabel.setHorizontalTextPosition(JLabel.CENTER);
		lblNewLabel.setVerticalTextPosition(JLabel.TOP);
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setFont(new Font("MV Boli", Font.PLAIN, 27));
		panel.add(lblNewLabel);
		
		
		//FileRead
		fileRead();
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0x145DA0));//2E8BC0
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Integer col=0,row = 0;
						screen = new gamescreen(cellvalues,row,col,false,"xxx",0,60000*3);
					} catch (FileNotFoundException e1) {
						
						e1.printStackTrace();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
			}
		});
		btnNewButton.setBounds(74, 92, 89, 43);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Continue");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjectInputStream in = null;
				try {
					in = new ObjectInputStream(new FileInputStream("D:\\CE 2.SINIF\\CE 2.dönem\\Java\\Proje\\save.dat"));
					Integer row = (Integer) in.readObject();
					Integer col = (Integer) in.readObject();
					String equation = (String) in.readObject();
					int time = (Integer) in.readObject();
					System.out.println("Time="+time);
					//System.out.println(row+" "+col);
					cellvalues = new Cell[row][col];
					for(int i = 0 ;i<row;i++) {
						for(int j = 0;j<col;j++) {
							//System.out.println("Girdi");
							Cell tmp = (Cell)in.readObject();
							cellvalues[i][j] = tmp;
						}
					}
					screen = new gamescreen(cellvalues,row,col,true,equation,row-1,time);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(331, 92, 89, 43);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		btnNewButton_2.setBounds(203, 251, 89, 43);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Test");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Test();
			}
		});
		btnNewButton_3.setBounds(203, 168, 89, 43);
		panel_1.add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("MV Boli", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(41, 329, 412, 43);
		String a = null;
		for(Statistics iter : istatistikler)
			a = iter.toString1();
		lblNewLabel_1.setText(a);
		lblNewLabel_1.setForeground(Color.WHITE);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("MV Boli", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(41, 371, 412, 43);
		for(Statistics iter : istatistikler)
			a = iter.toString2();
		lblNewLabel_2.setText(a);
		lblNewLabel_2.setForeground(Color.WHITE);
		panel_1.add(lblNewLabel_2);
		//menu.setLayout(null);
		//this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		 SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	            try {
					new Main();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	         }
	      });
	}
	
	
	@SuppressWarnings("unchecked")
	public void fileRead() throws FileNotFoundException, IOException, ClassNotFoundException {
		String filename = "D:\\CE 2.SINIF\\CE 2.dönem\\Java\\Proje\\istatistikler.txt";
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
		istatistikler = (LinkedList<Statistics>)in.readObject();
		in.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
