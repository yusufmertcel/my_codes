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
//Yeni oyun , Oyuna devam et, TEST ,Oyun istatistikleri butonlar�n�n oldu�u ve ve bas�lan duruma g�re farkl� panellerin a��laca�� main class
public class Men� {

	private JFrame frame;

	public static void main(String[] args) {
	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Men� window2 = new Men�();
					window2.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Men�() {
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
		
		//Yeni oyun butonuna bas�ld���nda 'Denklem' isimli s�n�ftan bir nesne �reterek bir denklem �retir.Denkemi ve denklemin uzunlu�unu parametre olarak g�ndererek
		//oyunun kronometresi '00.00.00' dan ba�layacak �ekilde oyun a��l�r.
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
		//Oyuna devam et  butonuna bas�ld���nda 'denklem.txt' isimli dosyay� okuyarak yar�m kalan oyudaki denklemi ve denklemin uzunlu�unu parametre olarak g�ndererek
		//oyunun kronometresi yar�m kalan oyundaki s�reden devam edecek �ekilde oyun a��l�r.
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
		
		// Test butonuna t�kland���nda oyun harici bi pencere a��larak denklem �retir. Sonras�nda her 'denklem �ret' butonuna t�kland���nda yeni bir denklem �retir.
		JButton btnNewButton_1_1 = new JButton("TEST");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Denklem_goster window2 = new Denklem_goster();
				window2.goster.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(77, 210, 139, 72);
		panel.add(btnNewButton_1_1);
		//Oyun �statistikleri butonuna t�kland���nda ge�mi�te oynanan oyunlar�n istatistiklerinin oldu�u bir panel a��l�r. Bu panel 'Istatistik' isimli classdan �retti�i nesne arac�l��� ile a�ar. 
		JButton btnNewButton_1_1_1 = new JButton("Oyun �statistikleri");
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
