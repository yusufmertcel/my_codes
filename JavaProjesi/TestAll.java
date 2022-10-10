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
	
	
	//Üretilen denklemde eþittir(=) sayýsýnýn 1 olup olmadýðý kontrol edilir
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
		assertTrue("Bu denklemde birden fazla eþittir vardýr.",tmp == 1);
		
	}
	@Test
	//Üretilen denklemin uzunluðunun 7 ile 9 arasýnda olup olmadýðý kontrol edilir.
	public void test_length() {
		int length;
		Denklem b=new Denklem();
		b.start();
		length=b.getLength();
		int tmp=0;
		if(length <7 || length > 9) {
			tmp=1;
		}
		assertTrue("Bu denklemde birden fazla eþittir vardýr.",tmp == 0);
		
	}
	////Üretilen denklemde operatörlerin ilk veya son indisde olup olmadýðý kontrol edilir.
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
		assertTrue("Bu denklemde birden fazla eþittir vardýr.",tmp == 0);
		
	}
	//Üretilen denklemde operatör sayýsýnýn 2 veya 3den farklý olup olmadýðý kontrol edilir.
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
		assertTrue("Bu denklemde birden fazla eþittir vardýr.",tmp <= 2);
		
	}
	//Üretilen denklemde peþ peþe operatör olup olmadýðý kontrol edilir.
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
		assertTrue("Bu denklemde birden fazla eþittir vardýr.",tmp == 0);
		
	}

}
