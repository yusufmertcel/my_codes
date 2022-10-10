package Proje;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class Istatistik {

	JFrame istatistik;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Istatistik window = new Istatistik();
					window.istatistik.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Istatistik() {
		initialize();
	}

	private void initialize() {
		 String basarili = null;
		 String basarisiz = null;
		 String yarim = null;
		 String satir = null;
		 String zaman = null;
		 
		 int basarili_int=0;
		 int basarisiz_int=0;
		 int yarim_int=0;
		 int satir_int=0;
		 int zaman_int=0;
		 int top_zaman_int=0;
		 int top_satir_int=0;
		 
		 int i=0;
		 File file6 = new File("istatistik.txt");
	        //if file doesn't exists , then create it
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

			
	        Scanner sc = null;
	        
			try {
	     
	            File file = new File("istatistik.txt");
	            sc = new Scanner(file);   
	            String linee;
	            while (sc.hasNextLine()) {
	              linee = sc.nextLine();
	              if(i==2) {
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
		
		 istatistik = new JFrame();
		 istatistik.getContentPane().setBackground(new Color(250, 235, 215));
		 istatistik.setBounds(100, 100, 450, 363);
		 istatistik.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 istatistik.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(250, 235, 215));
		panel.setBounds(10, 10, 416, 306);
		istatistik.getContentPane().add(panel);
		panel.setLayout(new GridLayout(5, 1, 5, 10));
		
		JLabel lblNewLabel_1 = new JLabel("Baþarýlý Tamamlanan Oyun: "+basarili);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setForeground(new Color(46, 139, 87));
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Baþarýsýz Tamamlanan Oyun: "+basarisiz);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setForeground(new Color(139, 0, 0));
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Yarým Býrakýlan Oyun: "+yarim);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setForeground(new Color(255, 140, 0));
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Ortalama Bitirme Süresi: "+zaman+" Saniye");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setForeground(new Color(0, 0, 139));
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Ortalama Kaç Satýrda Bitirildi: "+satir+" Satýr");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(0, 0, 139));
		panel.add(lblNewLabel);
	}

}
