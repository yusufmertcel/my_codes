package proje;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class gamescreen extends JFrame implements MouseListener,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5558816764557800888L;
	Random random = new Random();
	private static Integer ROW_SIZE = 6;
	private static Integer COLUMN_SIZE = 0;
	public static final int CELL_SIZE = 60;
	public static final int BOARD_WIDTH = CELL_SIZE * ROW_SIZE;
	public static final int BOARD_HEIGHT = CELL_SIZE * COLUMN_SIZE;
	public static String button;
	private static Integer currentRow = 0;
	private static Cell[][] cells;
	public  static int lastSelectedCol;
	String a;
	EquationGenerator equation;
	LinkedList<Statistics> istatistikler = new LinkedList<Statistics>();
	JButton btnNewButton;
	JButton btnNewButton_1; 
	JButton btnNewButton_2;
	JButton btnNewButton_3;
	JButton btnNewButton_4;
	JButton btnNewButton_5;
	JButton btnNewButton_6;
	JButton btnNewButton_7;
	JButton btnNewButton_8;
	JButton btnNewButton_9;
	JButton btnNewButton_10;
	JButton btnNewButton_11;
	JButton btnNewButton_12;
	JButton btnNewButton_12_1;
	JButton btnNewButton_13;
	JButton btnNewButton_14;
	JButton btnNewButton_15;
	JButton btnNewButton_16;
	JPanel Container;
	JPanel panel;
	JPanel mainpanel;
	int elapsedTime;
	int minutes = 1;
	int seconds = 0;
	String seconds_str;
	String minutes_str;
	
	
	Timer timer = new Timer(1000, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			elapsedTime-=1000;
			minutes = (elapsedTime/60000) % 60;
			seconds = (elapsedTime/1000) % 60;
			seconds_str = String.format("%02d", seconds);
			minutes_str = String.format("%02d", minutes);
			timeLabel.setText(minutes_str+":"+seconds_str);
			gameOver();
		}
	});
	private JLabel timeLabel;
	
	public gamescreen(Cell[][] cellvalues,Integer row,Integer col,boolean saved,String savedequation,Integer initialindex,int time) throws FileNotFoundException, IOException, ClassNotFoundException{
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(518,620);
		currentRow = initialindex;
		lastSelectedCol = 0;
		elapsedTime = time;
		minutes = (elapsedTime/60000) % 60;
		seconds = (elapsedTime/1000) % 60;
		seconds_str = String.format("%02d", seconds);
		minutes_str = String.format("%02d", minutes);
		
		//File Read
		fileRead();
		
		getContentPane().setLayout(new BorderLayout(10, 10));
		this.setTitle("Nerdle");
		timer.start();
		if(saved) {
			COLUMN_SIZE = col;
		}
		else {
			COLUMN_SIZE = random.nextInt(3) + 7;
		}
		cells = new Cell[ROW_SIZE][COLUMN_SIZE];
		
		System.out.println(COLUMN_SIZE+row);
		
		Container = new JPanel();
		getContentPane().add(Container, BorderLayout.CENTER);
		Container.setLayout(new BorderLayout(75, 300));
		
		initialise(saved,savedequation);
		panel = new JPanel();
		Container.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(2,9,5,5));
		
		
		btnNewButton = new JButton("0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("0");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("1");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("1");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("2");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("2");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("3");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("3");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("4");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("4");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("5");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("5");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("6");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("6");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("7");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("7");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_7);
		
		btnNewButton_8 = new JButton("8");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("8");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_8);
		
		btnNewButton_9 = new JButton("9");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("9");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_9);
		
		btnNewButton_10 = new JButton("+");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("+");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_10);
		
		btnNewButton_11 = new JButton("-");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("-");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_11);
		
		btnNewButton_12 = new JButton("/");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("/");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_12);
		
		btnNewButton_12_1 = new JButton("*");
		btnNewButton_12_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("*");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_12_1);
		
		btnNewButton_13 = new JButton("=");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText("=");
				if(lastSelectedCol < COLUMN_SIZE-1)
					lastSelectedCol++;
				cells[currentRow][lastSelectedCol].grabFocus();
			}
		});
		panel.add(btnNewButton_13);
		
		btnNewButton_14 = new JButton("Tahmin Et");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = isEquationSensible(currentRow);
				if(currentRow<ROW_SIZE && isFull(currentRow) && flag) {
					if(isSolutionTrue(currentRow)) {
						for(Statistics iter : istatistikler)
							if(iter.getKazanilan_oyun() != 0)
								iter.setKazanilan_oyunlarin_ortalama_satir_sayisi((iter.getKazanilan_oyunlarin_ortalama_satir_sayisi() + currentRow)/iter.getKazanilan_oyun()); 
						for(Statistics iter : istatistikler)
							iter.setKazanilan_oyun(iter.getKazanilan_oyun() + 1);
						timer.stop();
						for(Statistics iter : istatistikler)
							iter.setKazanilan_oyunlarin_ortalama_suresi((iter.getKazanilan_oyunlarin_ortalama_suresi() + minutes*60 + seconds)/iter.getKazanilan_oyun());
						try {
							fileWrite();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(panel,"CONGRATULATIONS!!! You found the equation");
						timer.stop();
					}
					else {
						currentRow++;
						for(int j = 0;j < COLUMN_SIZE ;j++) {
							cells[currentRow - 1][j].setEnabled(false);
							cells[currentRow-1][j].setFocusable(false);
							cells[currentRow][j].setEditable(true);
							cells[currentRow][j].setFocusable(true);
						}
						lastSelectedCol = 0;
					}
				}
				else if(!flag) {
					JOptionPane.showMessageDialog(Container, "Your equation is not sensible,please change it");
				}
				else {
					if(currentRow == ROW_SIZE) {
						JOptionPane.showMessageDialog(Container, "You could not find the equation");
						for(Statistics iter : istatistikler)
							iter.setKaybedilen_oyun(iter.getKaybedilen_oyun() + 1);
						try {
							fileWrite();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						setVisible(false); //you can't see me!
						dispose(); //Destroy the JFrame object
						timer.stop();
					}
					else
						JOptionPane.showMessageDialog(panel, "You did not fill all the blanks!!!");
				}
			}
		});
		panel.add(btnNewButton_14);
		
		btnNewButton_15 = new JButton("Sil");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cells[currentRow][lastSelectedCol].setText(null);
				if(lastSelectedCol > 0)
					lastSelectedCol--;
			}
		});
		
		
		
		
		panel.add(btnNewButton_15);
		
		btnNewButton_16 = new JButton("Sonra Bitir");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String filename = "D:\\CE 2.SINIF\\CE 2.dönem\\Java\\Proje\\save.dat";
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename,false));
					System.out.println("Row="+ROW_SIZE+COLUMN_SIZE);
					out.writeObject(currentRow+1);
					out.writeObject(COLUMN_SIZE);
					out.writeObject(a);
					out.writeObject(elapsedTime);
					for(int i = 0;i<ROW_SIZE;i++) {
						for(int j=0;j<COLUMN_SIZE;j++) {
							out.writeObject(cells[i][j]);
						}
					}
					timer.stop();
					for(Statistics iter : istatistikler)
						iter.setYarida_birakilan_oyun(iter.getYarida_birakilan_oyun() + 1);
					out.close();
					try {
						fileWrite();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					System.out.println("Game has been saved successfully");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		panel.add(btnNewButton_16);
		
		mainpanel = new JPanel();
		mainpanel.setPreferredSize(new Dimension(75, 400));
		mainpanel.setBackground(Color.LIGHT_GRAY);
		Container.add(mainpanel, BorderLayout.NORTH);
		Container.add(panel,BorderLayout.SOUTH);
		mainpanel.setLayout(new GridLayout(ROW_SIZE, COLUMN_SIZE, 10, 10));
		
		timeLabel = new JLabel();
		timeLabel.setText(minutes_str+":"+seconds_str);
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		getContentPane().add(timeLabel, BorderLayout.NORTH);
		for(int i = 0;i<ROW_SIZE;i++) {
			for(int j=0;j<COLUMN_SIZE;j++) {
				cells[i][j] = new Cell(i,j);
				if(i != 0) {
					cells[i][j].setEditable(false);
					cells[i][j].setFocusable(false);
				}
				mainpanel.add(cells[i][j]);
			}
		}
		
		if(saved)
			for(int i = 0;i<row;i++) {
				for(int j = 0;j<col;j++) {
					cells[i][j].setText(cellvalues[i][j].getText());
					cells[i][j].setBackground(cellvalues[i][j].getBackground());
					cells[i][j].setEnabled(cellvalues[i][j].isEnabled());
					cells[i][j].setEditable(cellvalues[i][j].isEditable());
					cells[i][j].setFocusable(cellvalues[i][j].isFocusable());
				}
			}
		
		this.setVisible(true);
		//this.pack();
	}
	
	@SuppressWarnings("unchecked")
	public void fileRead() throws FileNotFoundException, IOException, ClassNotFoundException {
		String filename = "D:\\CE 2.SINIF\\CE 2.dönem\\Java\\Proje\\istatistikler.txt";
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
		istatistikler = (LinkedList<Statistics>)in.readObject();
		in.close();
	}
	
	public void fileWrite() throws FileNotFoundException, IOException {
		String filename = "D:\\CE 2.SINIF\\CE 2.dönem\\Java\\Proje\\istatistikler.txt";
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename,false));
		out.writeObject(istatistikler);
		out.close();
	}
	
	
	
	public void gameOver() {
		if(minutes == 0 && seconds == 0) {
			JOptionPane.showMessageDialog(Container, "Game Over");
			for(Statistics iter : istatistikler)
				iter.setKaybedilen_oyun(iter.getKaybedilen_oyun() + 1);
			try {
				fileWrite();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			setVisible(false); 
			dispose(); 
		}
	}
		
	public int getColSize() {
		return COLUMN_SIZE;
	}
	
	public static int getCurrentPosition() {
		int j = 0;
		while(j<COLUMN_SIZE && !(cells[currentRow][j].isFocusOwner())) {
			j++;
		}
		return j;
	}

	public boolean isFull(int row) {
		int j = 0;;
		while(j < COLUMN_SIZE && !(cells[row][j].getText().isBlank()))
			j++;
		if(j == COLUMN_SIZE) {
			return true;
		}
		else 
			return false;
	}
	
	public boolean isEquationSensible(int row) {
		int i = 0;
		int result = 0;
		String s = "0123456789";
		String operators = "+-/*";
		String operator = "";
		String number;
		if(!(s.contains(cells[row][0].getText())))
			return false;
		while(i<COLUMN_SIZE && !(cells[row][i].getText().equals("=")))
		{
			int j = i;
			number = "";
			if(s.contains(cells[row][j].getText())) {
				while(j<COLUMN_SIZE && s.contains(cells[row][j].getText())){
					number = number + cells[row][j].getText();
					System.out.println("Number="+number);
					j++;
				}
				System.out.println("J="+j);
				System.out.println(operator);
				switch(operator) {
				case "+":
					result = result + Integer.valueOf(number);
					break;
				case "-":
					if(Integer.valueOf(number) > result)
						result = Integer.valueOf(number) - result;
					else
						result = result - Integer.valueOf(number);
					break;
				case "/":
					if(Integer.valueOf(number) > result) {
						if(Integer.valueOf(number) % result == 0)
							result =Integer.valueOf(number) / result;
					}	
					else {
						if(result % Integer.valueOf(number) == 0)
							result = result / Integer.valueOf(number);
					}
					break;
				case "*":
					result = result * Integer.valueOf(number);
					break;
				case "":
					result = Integer.valueOf(number);
					break;
				default:
					return false;
				}
				i=j;
			}
			else if(operators.contains(cells[row][j].getText())){
				i++;
				if(operators.contains(cells[row][i].getText()))
					return false;
				else
					operator = cells[row][j].getText();
				
			}
			else {
				return false;
			}
		}
		i++;
		number = "0";
		System.out.println("Result="+result);
		while(i<COLUMN_SIZE && s.contains(cells[row][i].getText())){
			number = number + cells[row][i].getText();
			i++;
		}
		if(result == Integer.valueOf(number))
			return true;
		else
			return false;
	}
	
	public void initialise(boolean saved,String savedequation) {
		if(COLUMN_SIZE == 7) {
			equation = new SevenDigit();
		}
		else if(COLUMN_SIZE == 8) {
			equation = new EightDigit();
		}
		else {
			equation = new NineDigit();
		}
		if(saved)
			a = savedequation;
		else
			a = equation.randomEquationgenerator();
		System.out.println(a);
	}
	

	public boolean isSolutionTrue(int row) {
		int j = 0,truecol=0;
		System.out.println(a);
		while(j<COLUMN_SIZE)
		{
			if(cells[row][j].StatusofCell(a,COLUMN_SIZE,j)) 
				truecol++;
			j++;
		}
		System.out.println(j);
		if(truecol == COLUMN_SIZE)
			return true;
		else
			return false;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
