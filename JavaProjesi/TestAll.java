package Proje;

import org.junit.Test;

import junit.framework.TestCase;

public class TestAll extends TestCase{
	private char[] denklem;
	private String esittir="=";
	private String arti="+";
	private String eksi="-";
	private String carpi="*";
	private String bolu="/";
	
	
	//�retilen denklemde e�ittir(=) say�s�n�n 1 olup olmad��� kontrol edilir
	@Test
	public void test_esittir() {
		String ifade;
		Denklem b=new Denklem();
		b.start();
		ifade=b.getIfade();
		int tmp=0;
		for(int i=0; i<ifade.length() ; i++) {
			if(ifade.charAt(i)==esittir.charAt(0)) {
				tmp++;
			}
		}
		assertTrue("Bu denklemde birden fazla e�ittir vard�r.",tmp == 1);
		
	}
	@Test
	//�retilen denklemin uzunlu�unun 7 ile 9 aras�nda olup olmad��� kontrol edilir.
	public void test_length() {
		int length;
		Denklem b=new Denklem();
		b.start();
		length=b.getLength();
		int tmp=0;
		if(length <7 || length > 9) {
			tmp=1;
		}
		assertTrue("Bu denklemde birden fazla e�ittir vard�r.",tmp == 0);
		
	}
	////�retilen denklemde operat�rlerin ilk veya son indisde olup olmad��� kontrol edilir.
	public void test_op_konum() {
		int length;
		String ifade;
		int i,tmp=0;
		Denklem b=new Denklem();
		b.start();
		length=b.getLength();
		ifade=b.getIfade();
		denklem=new char [length];
		for(i=0;i< length ; i++) {
			denklem[i]=ifade.charAt(i);
		}
		
		if((denklem[0]==arti.charAt(0)) || (denklem[0]==eksi.charAt(0))|| (denklem[0]==carpi.charAt(0)) || (denklem[0]==bolu.charAt(0))) {
			tmp++;
		}
		if((denklem[length -1]==arti.charAt(0)) || (denklem[length -1]==eksi.charAt(0))|| (denklem[length -1]==carpi.charAt(0)) || (denklem[length -1]==bolu.charAt(0))) {
			tmp++;
		}
		assertTrue("Bu denklemde birden fazla e�ittir vard�r.",tmp == 0);
		
	}
	//�retilen denklemde operat�r say�s�n�n 2 veya 3den farkl� olup olmad��� kontrol edilir.
	public void test_op_sayisi() {
		int length;
		String ifade;
		int i,tmp=0;
		Denklem b=new Denklem();
		b.start();
		length=b.getLength();
		ifade=b.getIfade();
		denklem=new char [length];
		for(i=0;i< length ; i++) {
			denklem[i]=ifade.charAt(i);
		}
		
		for(i=0; i<length ; i++) {
			if((denklem[i]==arti.charAt(0)) || (denklem[i]==eksi.charAt(0))|| (denklem[i]==carpi.charAt(0)) || (denklem[i]==bolu.charAt(0))) {
				tmp++;
			}
		}
		assertTrue("Bu denklemde birden fazla e�ittir vard�r.",tmp <= 2);
		
	}
	//�retilen denklemde pe� pe�e operat�r olup olmad��� kontrol edilir.
	public void test_op_pespese() {
		int length;
		String ifade;
		int i=0,tmp=0;
		Denklem b=new Denklem();
		b.start();
		length=b.getLength();
		ifade=b.getIfade();
		denklem=new char [length];
		for(i=0;i< length ; i++) {
			denklem[i]=ifade.charAt(i);
		}
		
		for(i=0;i<length-1;i++) {
			if((denklem[i]==arti.charAt(0)) || (denklem[i]==eksi.charAt(0))|| (denklem[i]==carpi.charAt(0)) || (denklem[i]==bolu.charAt(0))) {
				if((denklem[i+1]==arti.charAt(0)) || (denklem[i+1]==eksi.charAt(0))|| (denklem[i+1]==carpi.charAt(0)) || (denklem[i+1]==bolu.charAt(0))) {
					tmp=1;
				}
			}
		}
		assertTrue("Bu denklemde birden fazla e�ittir vard�r.",tmp == 0);
		
	}

}
