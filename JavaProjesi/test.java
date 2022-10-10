package Proje;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class test {

	JFrame frmNerdleGame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	public int active_index = 0;
	public int line = 0;
	private int length;
	private int[] color= new int[9];
	private int[][]color2=new int [6][9];
	private int flag=0;
	private String ifade;
	private String tahmin="";
	private int kontrol=1;
	private int dosya;
	private int sayac=0;
	int i=0,comp=0;
	Scanner sc = null;
	JLabel timeLabel = new JLabel();

	
	int elapsedTime = 0;
	int seconds =0;
	int minutes =0;
	int hours =0;
	int hours2=99;
	boolean started = false;
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);
	String elapsedTime_string = String.format("%02d", elapsedTime);
	
	int elapsedTime_tmp = 0;
	int seconds_tmp =0;
	int minutes_tmp =0;
	int hours_tmp =0;
	int time=0;
	String seconds_string_tmp;
	String minutes_string_tmp;
	String hours_string_tmp;
	String elapsedTime_string_tmp;
	
	 String basarili = null;
	 String basarisiz = null;
	 String yarim = null;
	 String satir = null;
	 String zaman = null;
	 String top_zaman;
	 String top_satir;
	 
	 int basarili_int=0;
	 int basarisiz_int=0;
	 int yarim_int=0;
	 int satir_int=0;
	 int zaman_int=0;
	 int top_zaman_int=0;
	 int top_satir_int=0;
	
	 
	
	JTextField[][] matris = new JTextField[6][9];
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public test(int length, String ifade,int dosya) {
		this.length = length;
		this.ifade=ifade;
		this.dosya=dosya;
		initialize();
	}
	
	

	private void initialize() {
		
		frmNerdleGame = new JFrame();
		frmNerdleGame.setType(Type.UTILITY);
		frmNerdleGame.setTitle("Nerdle Game");
		frmNerdleGame.setBounds(100, 100, 1030, 657);
		frmNerdleGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNerdleGame.getContentPane().setLayout(null);
		
		//Denklemi tahmin etmek için üretilen 'Textfield'lerin durduðu panel1,
		//Textfieldlere sayý veya operatör girmek ayrýca 'sonra devam et - tahmin' butonlarýnýn bulunduðu panel2,
		//Kronometre ve 'high score'nin yazýlýdý panel3
		
		JPanel panel = new JPanel();
		panel.setBounds(692, 129, 288, 313);
		frmNerdleGame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 4, 10, 20));
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 6, 612, 540);
		frmNerdleGame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(6, length, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(692, 465, 288, 133);
		frmNerdleGame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 5, 5));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(692, 6, 293, 82);
		frmNerdleGame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel timeLabel = new JLabel(hours_string+":"+minutes_string+":"+seconds_string);
		timeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		timeLabel.setBounds(10, 10, 110, 62);
		panel_3.add(timeLabel);
		
		i=0;
		Scanner sc2 = null;
		//Oyunu baþarýlý/ baþarýsýz biritme durumunda veya oyunu sonra bitirme durumunda istatistik tablosu güncelleneceði için (istatistik.txt dosyasýna yeni veriler kayýt edilir)
		//oyuna ilk kez baþlandýðýnda bu verileri '0' olarak dosyaya kayýt eder(aksi durumda hata ile karþýlaþýlýyor) ve dosya bu þekilde oluþturulur.
		File file6 = new File("istatistik.txt");
        if (!file6.exists())
            try {
                file6.createNewFile();
                try {
                    FileWriter fw = new FileWriter(file6.getAbsoluteFile());
                    BufferedWriter denklem = new BufferedWriter(fw);
                    denklem.write(String.valueOf(top_zaman_int) );
                    denklem.newLine();
                    denklem.write(String.valueOf(top_satir_int));
                    denklem.newLine();
                    denklem.write(String.valueOf(basarili_int)  );
                    denklem.newLine();
                    denklem.write(String.valueOf(basarisiz_int) );
                    denklem.newLine();
                    denklem.write(String.valueOf(yarim_int) );
                    denklem.newLine();
                    denklem.write(String.valueOf(zaman_int) );
                    denklem.newLine();
                    denklem.write(String.valueOf(satir_int) );
                    denklem.close();
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
      //Oyunu baþarýlý durumunda high score  güncelleneceði için (best.txt dosyasýna yeni veriler kayýt edilir)
      //oyuna ilk kez baþlandýðýnda bu verileri '0' olarak dosyaya kayýt eder(aksi durumda hata ile karþýlaþýlýyor) ve dosya bu þekilde oluþturulur.
        File file5 = new File("best.txt");
        if (!file5.exists())
            try {
            	file5.createNewFile();
            	 try {
                     FileWriter fw = new FileWriter(file5.getAbsoluteFile());
                     BufferedWriter best= new BufferedWriter(fw);
                     best.write(String.valueOf(hours2));
                     best.newLine();
                     best.write(String.valueOf(minutes));
                     best.newLine();
                     best.write(String.valueOf(seconds));
                     best.newLine();
                     best.write(String.valueOf(elapsedTime));
                     best.newLine();
                     best.close();
                 } catch (Exception ee) {
                     ee.printStackTrace();
                 }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        
       //Ýstatistikler dosyadan okunarak tanýmlý String deðerlere atanýr ve sonrasýnda bu String deðerler integere dönüþtürülür.
		try {
     
            File file = new File("istatistik.txt");
            sc2 = new Scanner(file);   
            String linee;
            while (sc2.hasNextLine()) {
              linee = sc2.nextLine();
              if(i==0) {
            	  top_zaman=linee;
              }
              else if(i==1) {
            	  top_satir=linee;
              }
              else if(i==2) {
            	  basarili=linee;
              }
              else if(i==3) {
            	  basarisiz=linee;
              }
              else if(i==4) {
            	  yarim=linee;
              }
              else if(i==5) {
            	  zaman=linee;
              }
              else if(i==6) {
            	  satir=linee;
              }
              i++;
            }
          }		
		
          catch(FileNotFoundException ee)
          {
              ee.printStackTrace();
          }
          finally {
            if (sc != null) sc.close();
          }
		top_zaman_int=Integer.parseInt(top_zaman);
		top_satir_int=Integer.parseInt(top_satir);
		basarili_int=Integer.parseInt(basarili);
		basarisiz_int=Integer.parseInt(basarisiz);
		zaman_int=Integer.parseInt(zaman);
		satir_int=Integer.parseInt(satir);
		yarim_int=Integer.parseInt(yarim);
		
		i=0;
		//High score dosyadan okunarak tanýmlý String deðerlere atanýr ve sonrasýnda bu String deðerler integere dönüþtürülür.
        try {
     
            File file = new File("best.txt");
            sc = new Scanner(file);   
            String linee;
            while (sc.hasNextLine()) {
              linee = sc.nextLine();
              if(i==0) {
            	  hours_string_tmp=linee;
              }
              else if(i==1) {
            	  minutes_string_tmp=linee;
              }
              else if(i==2) {
            	  seconds_string_tmp=linee;
              }
              i++;
            }
          }				        
          catch(FileNotFoundException ee)
          {
              ee.printStackTrace();
          }
          finally {
            if (sc != null) sc.close();
          }
		hours_tmp=Integer.valueOf(hours_string_tmp);
		minutes_tmp=Integer.valueOf(minutes_string_tmp);
		seconds_tmp=Integer.valueOf(seconds_string_tmp);
		JLabel lblNewLabel = new JLabel("High Score :" + hours_string_tmp+":"+minutes_string_tmp+":"+seconds_string_tmp);
		lblNewLabel.setBounds(147, 10, 123, 62);
		panel_3.add(lblNewLabel);
		
		//Kronometrenin saniye saniye ilerleyip 60 saniyede 1 dakika 60 dakikada 1 saaate dönüþümünü saðlar.
		Timer timer = new Timer(1000, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				elapsedTime=elapsedTime+1000;
				hours = (elapsedTime/3600000);
				minutes = (elapsedTime/60000) % 60;
				seconds = (elapsedTime/1000) % 60;
				seconds_string = String.format("%02d", seconds);
				minutes_string = String.format("%02d", minutes);
				hours_string = String.format("%02d", hours);
				elapsedTime_string = String.format("%02d", elapsedTime);
				timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
				
			}
			
		});
		//Kronometreyi baþlatýr
		timer.start();
		//Denklem tahmininin yapýlmasý için (6 xdenklem lengthi)i kadar textfield üretilir ve bu textfieldler matris içinde tutulur.
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < length; j++) {
				JTextField textField = new JTextField();
				matris[i][j] = textField;
				matris[i][j].addMouseListener(new MouseAdapter() {

					@Override
					public void mousePressed(MouseEvent e) {
						for (int k = 0; k < length; k++) {
							if (matris[line][k] == e.getSource()) {
								active_index = k;
							}
						}

					}

				});
				panel_1.add(matris[i][j]);

			}
		}
		//Oyuna devam et butonuna basýldýðýnda dosya'nýn deðeri 1 yapýlýr ve yarým kalan oyundaki denklem, kalýnan satýr, tahmin edilen denklemlerin doðruluk yanlýþlýklarý,
		//kronometrenin kaldýðý zaman dilim dosyalardan okunarak yarým kalan oyun devam ettirilir.
		if(dosya==1) {
			 sc = null;
			int i=0;
	        try {
	            File file = new File("denklem.txt");
	            sc = new Scanner(file);    
	            String linee;
	            while (sc.hasNextLine()) {
	              linee = sc.nextLine();
	              if(i==2) {
	            	  line=Integer.parseInt(linee);
	              }
	              else if(i==3) {
	            	  kontrol=Integer.parseInt(linee);
	              }
	              else if(i==4) {
	            	  sayac=Integer.parseInt(linee);
	              }
	              i++;
	            }
	          }
	          catch(FileNotFoundException ee)
	          {
	              ee.printStackTrace();
	          }
	          finally {
	            if (sc != null) sc.close();
	          }
	         i=0;
	        try {
	            File file = new File("gui.txt"); 
	            sc = new Scanner(file);     
	            String linee;
	   
	            while (sc.hasNextLine()) {
	              linee = sc.nextLine();
	       
	              for(int j=0;j<length;j++) {
	            	  matris[i][j].setText(String.valueOf(linee.charAt(j)));
	              }
	              i++;
	            }
	          }
	          catch(FileNotFoundException ee)
	          {
	              ee.printStackTrace();
	          }
	          finally {
	            if (sc != null) sc.close();
	          }
	        i=0;
	        //Denklem ile tahminler arasý iliþkinin (0-kýrmýzý 1-yeþil 2-sarý) okunduðu kýsýmdýr.
	        try {
	            File file = new File("renk.txt");
	            sc = new Scanner(file);     
	            String linee;
	   
	            while (sc.hasNextLine()) {
	              linee = sc.nextLine();
	       
	              for(int j=0;j<length;j++) {
	            	  color[j]=Integer.parseInt(String.valueOf(linee.charAt(j)));
	              }
	              for (int j = 0; j < length;j++) {
						if(color[j]==0) {
							matris[i][j].setBackground(Color.red);
						}
						else if(color[j]==1) {
							matris[i][j].setBackground(Color.green);
						}
						else {
							matris[i][j].setBackground(Color.yellow);
						}
					}
	              i++;
	            }
	          }
	          catch(FileNotFoundException ee)
	          {
	              ee.printStackTrace();
	          }
	          finally {
	            if (sc != null) sc.close();
	          }
	        i=0;
	        try {
	            File file = new File("time.txt"); 
	            sc = new Scanner(file);     
	            String linee;
	   
	            while (sc.hasNextLine()) {
	              linee = sc.nextLine();
	              if(i==0) {
	            	  hours_string=linee;
	              }
	              else if(i==1) {
	            	  minutes_string=linee;
	              }
	              else if(i==2) {
	            	  seconds_string=linee;
	              }else if(i==3) {
	            	  elapsedTime_string=linee;
	              }
	              i++;
	            }
	          }
	          catch(FileNotFoundException ee)
	          {
	              ee.printStackTrace();
	          }
	          finally {
	            if (sc != null) sc.close();
	            i=0;
	          }
	        
	        hours=Integer.parseInt(hours_string);
	        minutes=Integer.parseInt(minutes_string);
	        seconds=Integer.parseInt(seconds_string);
	        elapsedTime=Integer.parseInt(elapsedTime_string);
	        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
	        
		}
		

		JButton btnNewButton_1 = new JButton("1");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("2");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("3");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("+");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_4);

		JButton btnNewButton_6 = new JButton("4");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_6);

		JButton btnNewButton_5 = new JButton("5");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_5);

		JButton btnNewButton_7 = new JButton("6");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_7);

		JButton btnNewButton_12 = new JButton("-");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_12.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_12);

		JButton btnNewButton = new JButton("7");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton);

		JButton btnNewButton_9 = new JButton("8");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_9);

		JButton btnNewButton_8 = new JButton("9");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_8);

		JButton btnNewButton_10 = new JButton("*");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_10.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_10);

		JButton btnNewButton_11 = new JButton("0");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_11.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_11);

		JButton btnNewButton_13 = new JButton("=");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_13.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_13);

		JButton btnSil = new JButton("C");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText("");
				if (active_index > 0) {
					active_index--;
				}
			}
		});
		btnSil.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnSil);

		JButton btnNewButton_15 = new JButton("/");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matris[line][active_index].setText(e.getActionCommand());
				if (active_index < length-1) {
					active_index++;
				}
			}
		});
		btnNewButton_15.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		panel.add(btnNewButton_15);

		

		JButton btnNewButton_14_1 = new JButton("Tahmin ET");
		btnNewButton_14_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tahmin="";
				if(sayac < 6) {
					for(int j=0; j<length ;j++) {
						tahmin=tahmin+matris[line][j].getText();
					}
					Kontrol c= new Kontrol(ifade,tahmin,length);
					flag=c.kontrol_et();
					if(flag==1) {
						kontrol=1;
						active_index=0;
						c.renklendirme();
						color=c.getArr();
						
						for(int j=0; j<length ;j++) {
							color2[line][j]=color[j];
						}
						for (int i = 0; i < length;i++) {
							if(color[i]==0) {
								matris[line][i].setBackground(Color.red);
							}
							else if(color[i]==1) {
								matris[line][i].setBackground(Color.green);
							}
							else {
								matris[line][i].setBackground(Color.yellow);
							}
						}
						
			
						for (int j = 0; j < length; j++) {
						matris[line][j].setEditable(false);
					}
						if(sayac<5) {
							line++;
						}else {
							//oyunu kaybetme durumunda istatistik dosyasýnda baþarýsýz sayýsý 1 arttýrýlarak güncellenir.
							basarisiz_int=basarisiz_int+1;
					        File file6 = new File("istatistik.txt");
					        if (!file6.exists())
					            try {
					                file6.createNewFile();
					            } catch (Exception ee) {
					                ee.printStackTrace();
					            }

					        try {
					            FileWriter fw = new FileWriter(file6.getAbsoluteFile());
					            BufferedWriter denklem = new BufferedWriter(fw);
					            denklem.write(String.valueOf(top_zaman_int) );
					            denklem.newLine();
					            denklem.write(String.valueOf(top_satir_int));
					            denklem.newLine();
					            denklem.write(String.valueOf(basarili_int)  );
					            denklem.newLine();
					            denklem.write(String.valueOf(basarisiz_int) );
					            denklem.newLine();
					            denklem.write(String.valueOf(yarim_int) );
					            denklem.newLine();
					            denklem.write(String.valueOf(zaman_int) );
					            denklem.newLine();
					            denklem.write(String.valueOf(satir_int) );
					            denklem.close();
					        } catch (Exception ee) {
					            ee.printStackTrace();
					        }
					        //Oyunu kaybettiniz yazýlý panel açýlýr
							Lost window = new Lost();
							window.lost.setVisible(true);
						}
						for( int i=0; i<length ;i++) {
							if(color[i] != 1) {
								kontrol=0;
							}
						}
						if(kontrol != 1) {
							for (int j = 0; j < length; j++) {
								matris[line][j].setEditable(true);
							}
						}else {
							timer.stop();
							sc = null;
							int i=0;
							
					        try {
					     
					            File file = new File("best.txt");
					            sc = new Scanner(file);   
					            String linee;
					            while (sc.hasNextLine()) {
					              linee = sc.nextLine();
					              if(i==0) {
					            	  hours_string_tmp=linee;
					              }
					              else if(i==1) {
					            	  minutes_string_tmp=linee;
					              }
					              else if(i==2) {
					            	  seconds_string_tmp=linee;
					              }
					              i++;
					            }
					          }				        
					          catch(FileNotFoundException ee)
					          {
					              ee.printStackTrace();
					          }
					          finally {
					            if (sc != null) sc.close();
					          }
							hours_tmp=Integer.valueOf(hours_string_tmp);
							minutes_tmp=Integer.valueOf(minutes_string_tmp);
							seconds_tmp=Integer.valueOf(seconds_string_tmp);
							comp=1;
							
							if(comp ==1 && hours < hours_tmp) {
								comp=1;
							}else {
								if( minutes< minutes_tmp) {
									comp=1;
								}else {
									if(seconds < seconds_tmp) {
										comp=1;
									}else {
										comp=0;
									}
								}
							}
							
							
							
							//Oyunu kazanma durumunda yeni bitirme süresi high score ile kýyaslanarak daha iyi sürede bitirildi ise bu süre high score olarak güncellenir.
							if(comp==1) {
								File file5 = new File("best.txt");
						        if (!file5.exists())
						            try {
						            	file5.createNewFile();
						            } catch (Exception ee) {
						                ee.printStackTrace();
						            }
						        
						        try {
						            FileWriter fw = new FileWriter(file5.getAbsoluteFile());
						            BufferedWriter best= new BufferedWriter(fw);
						            best.write(String.valueOf(hours));
						            best.newLine();
						            best.write(String.valueOf(minutes));
						            best.newLine();
						            best.write(String.valueOf(seconds));
						            best.newLine();
						            best.write(String.valueOf(elapsedTime));
						            best.newLine();
						            best.close();
						        } catch (Exception ee) {
						            ee.printStackTrace();
						        }
							}
							time=(hours*3600)+(minutes*60)+seconds;
							top_zaman_int=top_zaman_int+time;
							top_satir_int=top_satir_int+line;
							basarili_int=basarili_int+1;
							zaman_int=(top_zaman_int)/basarili_int;
							satir_int=(top_satir_int)/basarili_int;
							
							//Oyunu baþarý ile kazanma durumunda 'Oyunu Kazandýnýz' yazýlý panel açýlýr
							tebrik window = new tebrik();
							window.tebrik.setVisible(true);
							
							File file6 = new File("istatistik.txt");
					        if (!file6.exists())
					            try {
					                file6.createNewFile();
					            } catch (Exception ee) {
					                ee.printStackTrace();
					            }

					        try {
					            FileWriter fw = new FileWriter(file6.getAbsoluteFile());
					            BufferedWriter denklem = new BufferedWriter(fw);
					            denklem.write(String.valueOf(top_zaman_int) );
					            denklem.newLine();
					            denklem.write(String.valueOf(top_satir_int));
					            denklem.newLine();
					            denklem.write(String.valueOf(basarili_int)  );
					            denklem.newLine();
					            denklem.write(String.valueOf(basarisiz_int) );
					            denklem.newLine();
					            denklem.write(String.valueOf(yarim_int) );
					            denklem.newLine();
					            denklem.write(String.valueOf(zaman_int) );
					            denklem.newLine();
					            denklem.write(String.valueOf(satir_int) );
					            denklem.close();
					        } catch (Exception ee) {
					            ee.printStackTrace();
					        }
						}
						sayac++;
					}
				}
				
				
					
			}
			
		});
		panel_2.add(btnNewButton_14_1);

		JButton btnNewButton_14_2 = new JButton("Sonra Bitir");
		//Sonra bitir butonuna basýldýðýnda mevcur süre, yapýlan tahminler ve oluþan denklemler, kalýnan satýr, mevcut süre dosyalara sonrasýnda yeniden kalýnan yerde devam ettirilme dsenaryosuna hazýr olmak için kayýt edilir.
		btnNewButton_14_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				File file = new File("denklem.txt");
		        if (!file.exists())
		            try {
		                file.createNewFile();
		            } catch (Exception ee) {
		                ee.printStackTrace();
		            }

		        try {
		            FileWriter fw = new FileWriter(file.getAbsoluteFile());
		            BufferedWriter denklem = new BufferedWriter(fw);
		            denklem.write(String.valueOf(length) );
		            denklem.newLine();
		            denklem.write(ifade);
		            denklem.newLine();
		            denklem.write(String.valueOf(line) );
		            denklem.newLine();
		            denklem.write(String.valueOf(kontrol));
		            denklem.newLine();
		            denklem.write(String.valueOf(sayac));
		            denklem.close();
		        } catch (Exception ee) {
		            ee.printStackTrace();
		        }
		        
		        File file2 = new File("gui.txt");
		        if (!file2.exists())
		            try {
		                file2.createNewFile();
		            } catch (Exception ee) {
		                ee.printStackTrace();
		            }

		        try {
		            FileWriter fw = new FileWriter(file2.getAbsoluteFile());
		            BufferedWriter gui = new BufferedWriter(fw);
		            for(int i=0;i<line;i++) {
		            	for(int j=0;j<length;j++) {
		            		gui.write(String.valueOf(matris[i][j].getText()));
		            	}
			            gui.newLine();
		            }
		            gui.close();
		        } catch (Exception ee) {
		            ee.printStackTrace();
		        }
		        
		        File file3 = new File("renk.txt");
		        if (!file3.exists())
		            try {
		                file3.createNewFile();
		            } catch (Exception ee) {
		                ee.printStackTrace();
		            }

		        try {
		            FileWriter fw = new FileWriter(file3.getAbsoluteFile());
		            BufferedWriter renk = new BufferedWriter(fw);
		            for(int i=0;i<line;i++) {
		            	for(int j=0;j<length;j++) {
		            		renk.write(String.valueOf(color2[i][j]));
		            	}
			            renk.newLine();
		            }
		            renk.close();
		        } catch (Exception ee) {
		            ee.printStackTrace();
		        }
		        
		        File file4 = new File("time.txt");
		        if (!file4.exists())
		            try {
		                file4.createNewFile();
		            } catch (Exception ee) {
		                ee.printStackTrace();
		            }

		        try {
		            FileWriter fw = new FileWriter(file4.getAbsoluteFile());
		            BufferedWriter time= new BufferedWriter(fw);
		            time.write(String.valueOf(hours_string));
		            time.newLine();
		            time.write(String.valueOf(minutes_string));
		            time.newLine();
		            time.write(String.valueOf(seconds_string));
		            time.newLine();
		            time.write(String.valueOf(elapsedTime_string));
		            time.newLine();
		            time.close();
		        } catch (Exception ee) {
		            ee.printStackTrace();
		        }
		        yarim_int=yarim_int+1;
		        File file6 = new File("istatistik.txt");
		        if (!file6.exists())
		            try {
		                file6.createNewFile();
		            } catch (Exception ee) {
		                ee.printStackTrace();
		            }

		        try {
		            FileWriter fw = new FileWriter(file6.getAbsoluteFile());
		            BufferedWriter denklem = new BufferedWriter(fw);
		            denklem.write(String.valueOf(top_zaman_int) );
		            denklem.newLine();
		            denklem.write(String.valueOf(top_satir_int));
		            denklem.newLine();
		            denklem.write(String.valueOf(basarili_int)  );
		            denklem.newLine();
		            denklem.write(String.valueOf(basarisiz_int) );
		            denklem.newLine();
		            denklem.write(String.valueOf(yarim_int) );
		            denklem.newLine();
		            denklem.write(String.valueOf(zaman_int) );
		            denklem.newLine();
		            denklem.write(String.valueOf(satir_int) );
		            denklem.close();
		        } catch (Exception ee) {
		            ee.printStackTrace();
		        }

			}
		});
		panel_2.add(btnNewButton_14_2);
		

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < length; j++) {
				matris[i][j].setEditable(false);
			}
		}
		for (int j = 0; j < length; j++) {
			matris[line][j].setEditable(true);
		}
		
		

	}

}
