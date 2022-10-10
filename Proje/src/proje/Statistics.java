package proje;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Statistics implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1407265728623333960L;
	private int Kazanilan_oyun;
	private int Kaybedilen_oyun;
	private int Yarida_birakilan_oyun;
	private int Kazanilan_oyunlarin_ortalama_satir_sayisi;
	private int Kazanilan_oyunlarin_ortalama_suresi;
	
	public Statistics(int Kazanilan_oyun,int Kaybedilen_oyun,int Yarida_birakilan_oyun,int Kazanilan_oyunlarin_ortalama_satir_sayisi,int Kazanilan_oyunlarin_ortalama_suresi) {
		this.Kazanilan_oyun = Kazanilan_oyun;
		this.Kaybedilen_oyun = Kaybedilen_oyun;
		this.Yarida_birakilan_oyun = Yarida_birakilan_oyun;
		this.Kazanilan_oyunlarin_ortalama_satir_sayisi = Kazanilan_oyunlarin_ortalama_satir_sayisi;
		this.Kazanilan_oyunlarin_ortalama_suresi = Kazanilan_oyunlarin_ortalama_suresi; 
	}
	
	//For reseting the statistics you have to run this class BUT be careful you will lose all of the statistics for your game.
	public static void main(String[] args) {
		LinkedList<Statistics> istatistikler = new LinkedList<Statistics>();
		Statistics first = new Statistics(0,0,0,0,0);
		System.out.println("Girdi");
		istatistikler.add(first);
		String filename = "D:\\CE 2.SINIF\\CE 2.dönem\\Java\\Proje\\istatistikler.txt";
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(filename,false));
			out.writeObject(istatistikler);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getKazanilan_oyun() {
		return Kazanilan_oyun;
	}

	public void setKazanilan_oyun(int kazanilan_oyun) {
		Kazanilan_oyun = kazanilan_oyun;
	}

	public int getKaybedilen_oyun() {
		return Kaybedilen_oyun;
	}

	public void setKaybedilen_oyun(int kaybedilen_oyun) {
		Kaybedilen_oyun = kaybedilen_oyun;
	}

	public int getYarida_birakilan_oyun() {
		return Yarida_birakilan_oyun;
	}

	public void setYarida_birakilan_oyun(int yarida_birakilan_oyun) {
		Yarida_birakilan_oyun = yarida_birakilan_oyun;
	}

	public int getKazanilan_oyunlarin_ortalama_satir_sayisi() {
		return Kazanilan_oyunlarin_ortalama_satir_sayisi;
	}

	public void setKazanilan_oyunlarin_ortalama_satir_sayisi(int kazanilan_oyunlarin_ortalama_satir_sayisi) {
		Kazanilan_oyunlarin_ortalama_satir_sayisi = kazanilan_oyunlarin_ortalama_satir_sayisi;
	}

	public int getKazanilan_oyunlarin_ortalama_suresi() {
		return Kazanilan_oyunlarin_ortalama_suresi;
	}

	public void setKazanilan_oyunlarin_ortalama_suresi(int kazanilan_oyunlarin_ortalama_suresi) {
		Kazanilan_oyunlarin_ortalama_suresi = kazanilan_oyunlarin_ortalama_suresi;
	}
	

	public String toString1() {
		return "Kazanýlan Oyun =  "+Kazanilan_oyun+"   Kaybedilen Oyun = "+Kaybedilen_oyun;
			
	}
	
	public String toString2() {
		return 	"Yarýda Kalan = "+Yarida_birakilan_oyun+"  Satir sayisi = "+Kazanilan_oyunlarin_ortalama_satir_sayisi
				+"  Ortalama süre = "+Kazanilan_oyunlarin_ortalama_suresi+"sn\n";
	}

}
