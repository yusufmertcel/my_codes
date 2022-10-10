package Proje;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
//Yeni oyun , Oyuna devam et, TEST ,Oyun istatistikleri butonlarýnýn olduðu ve ve basýlan duruma göre farklý panellerin açýlacaðý main class
public class Menü {

	private JFrame frame;

	public static void main(String[] args) {
	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menü window2 = new Menü();
					window2.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Menü() {
		initializee();
	}

	
	public void initializee() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 481);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(60, 10, 306, 424);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//Yeni oyun butonuna basýldýðýnda 'Denklem' isimli sýnýftan bir nesne üreterek bir denklem üretir.Denkemi ve denklemin uzunluðunu parametre olarak göndererek
		//oyunun kronometresi '00.00.00' dan baþlayacak þekilde oyun açýlýr.
		JButton btnNewButton = new JButton("Yeni Oyun");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Denklem b=new Denklem();
				b.start();
				test window = new test(b.getLength(),b.getIfade(),0);
				window.frmNerdleGame.setVisible(true);
				//System.out.println(window.matris[i]);
			}
		});
		btnNewButton.setBounds(77, 10, 139, 72);
		panel.add(btnNewButton);
		//Oyuna devam et  butonuna basýldýðýnda 'denklem.txt' isimli dosyayý okuyarak yarým kalan oyudaki denklemi ve denklemin uzunluðunu parametre olarak göndererek
		//oyunun kronometresi yarým kalan oyundaki süreden devam edecek þekilde oyun açýlýr.
		JButton btnNewButton_1 = new JButton("Oyuna Devam Et");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ifade = null;
				int length = 0;
				int i=0;
				Scanner sc = null;
				
				File file6 = new File("denklem.txt");
				if (!file6.exists()) {
					Denklem b=new Denklem();
					b.start();
					test window = new test(b.getLength(),b.getIfade(),0);
					window.frmNerdleGame.setVisible(true);
				}else {
					try {
			            File file = new File("denklem.txt"); 
			            sc = new Scanner(file);    
			            String line;
			            while (sc.hasNextLine()) {
			              line = sc.nextLine();
			              if(i==0) {
			            	  length=Integer.parseInt(line);
			              }
			              else if(i==1) {
			            	  ifade=line;
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
					
		
					test window = new test(length,ifade,1);
					window.frmNerdleGame.setVisible(true);
				}
				}
				
		        
		});
		btnNewButton_1.setBounds(77, 110, 139, 72);
		panel.add(btnNewButton_1);
		
		// Test butonuna týklandýðýnda oyun harici bi pencere açýlarak denklem üretir. Sonrasýnda her 'denklem üret' butonuna týklandýðýnda yeni bir denklem üretir.
		JButton btnNewButton_1_1 = new JButton("TEST");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Denklem_goster window2 = new Denklem_goster();
				window2.goster.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(77, 210, 139, 72);
		panel.add(btnNewButton_1_1);
		//Oyun Ýstatistikleri butonuna týklandýðýnda geçmiþte oynanan oyunlarýn istatistiklerinin olduðu bir panel açýlýr. Bu panel 'Istatistik' isimli classdan ürettiði nesne aracýlýðý ile açar. 
		JButton btnNewButton_1_1_1 = new JButton("Oyun Ýstatistikleri");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Istatistik window3 = new Istatistik();
				window3.istatistik.setVisible(true);
			}
		});
		btnNewButton_1_1_1.setBounds(77, 310, 139, 72);
		panel.add(btnNewButton_1_1_1);
	}
}
