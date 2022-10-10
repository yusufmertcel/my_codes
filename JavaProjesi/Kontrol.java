package Proje;

public class Kontrol {
	
	private String ifade;
	private String tahmin;
	private String sonuc="";
	
	private int[] color;
	
	private char[] ifade2;
	private char[] tahmin2;
	
	private int length;
	private int length_tahmin;
	private int flag;
	private int tmp=0;
	
	private String esittir="=";
	private String arti="+";
	private String eksi="-";
	private String carpi="*";
	private String bolu="/";
	private String sifir="0";
	private String a="a";
	private String b="b";
	
	private String s1="";
	private String s2="";
	private String s3="";
	private String op1="";
	private String op2="";
	private int sonuc2;
	
	public int i=0;

	public Kontrol(String ifade, String tahmin, int length) {
		this.ifade = ifade;
		this.tahmin = tahmin;
		this.length = length;
		this.length_tahmin = tahmin.length();
		color= new int[length];
		ifade2=new char [length];
		tahmin2=new char [tahmin.length()];
	}
	

	//Denklemde s�ra ile uygunlu�u bozacak durumlar�n var olup olmad��� saptanmaya �al���l�r. Herhangi bi sorun olmamas� durumunda tahmin olarak yaz�lan de�erlerin bir denklem olu�turup olu�turmad��� kontrol edilir.
	//Tahmin edilen denklemin bir denklem olu�turup olu�turmad��� senaryosu 2 veya 3 operat�r olma durumuna g�re ayr� ayr� de�erlendirilm�tir.
	// 2 operat�r olma durumunda say� 1 say�2 sonuc ve operat�r ayr� ayr� tutulup iki say�n�n operat�re g�re i�lem sonucu 'sonuc'a e�it olup olmad��� kontrol edilir.4
	// 3 operat�r olma durumunda say� 1 say�2 sayi3 sonuc ve operat�r1 operat�r ayr� ayr� tutulup, operat�r �nceli�ine g�re say�lar�n operat�rlere g�re i�lem sonucu 'sonuc'a e�it olup olmad��� kontrol edilir.
	public int  kontrol_et() {
		for(i=0;i<length ; i++) {
			ifade2[i]=ifade.charAt(i);
			color[i]=0;
			
		}
		for(i=0;i<length_tahmin ; i++) {
			tahmin2[i]=tahmin.charAt(i);
		}
		
		if(length != length_tahmin) {
			flag=0;
			System.out.println("Denklemin uzunlu�u ile tahmin edilen denklemin uzunluklar� e�it de�il. Yeniden kontrol ediniz :");
			return flag;
		}
		
		else if(esittir_Arama(tahmin2) != 1) {
			flag=0;
			System.out.println("E�ittir say�s� 1 olmal�d�r. Yeniden kontrol ediniz :");
			return flag;
		}
		
		else if((op_Arama(tahmin2) == 0 ) || (op_Arama(tahmin2) > 2 ) ) {
			System.out.println("Operat�r say�s� en fazla 2 olabilir.Yeniden kontrol ediniz :" );
			flag=0;
			return flag;
		}
		else if(op_Kontrol(tahmin2) != 0) {
			System.out.println("�lk/son karakter bir operat�r olamaz. Yeniden kontrol ediniz:");
			flag=0;
			return flag;
		}
		else if(op_Pespese(tahmin2) != 0) {
			System.out.println("Pe�pe�e operat�r koyamay�n�z. Yeniden kontrol ediniz :");
			flag=0;
			return flag;
		}
		else if(tahmin2[0]==sifir.charAt(0)) {
			System.out.println(" �lk karakter '0' olamaz ");
			flag=0;
			return flag;
		}
		else {
			tmp=0;
			i=0;
			while(tmp==0) {
				if((tahmin2[i]!=arti.charAt(0)) && (tahmin2[i]!= eksi.charAt(0)) && (tahmin2[i] != carpi.charAt(0)) && (tahmin2[i]!=bolu.charAt(0))) {
					s1=s1+tahmin2[i];
					i++;
				}else {
					if(tahmin2[i]==arti.charAt(0)){
						op1=op1+arti;
					}
					else if(tahmin2[i]==eksi.charAt(0)) {
						op1=op1+eksi;
					}
					else if(tahmin2[i]==carpi.charAt(0)) {
						op1=op1+carpi;
					}
					else {
						op1=op1+bolu;
					}
					tmp=1;
					i++;
				}
			}
			tmp=0;
			while(tmp==0) {
				if((tahmin2[i]!=arti.charAt(0)) && (tahmin2[i]!= eksi.charAt(0)) && (tahmin2[i] != carpi.charAt(0)) && (tahmin2[i]!=bolu.charAt(0)) && (tahmin2[i]==esittir.charAt(0))==false  ) {
					s2=s2+tahmin2[i];
					i++;
				}else {
					if(tahmin2[i]==arti.charAt(0)){
						op2=op2+arti;
					}
					else if(tahmin2[i]==eksi.charAt(0)) {
						op2=op2+eksi;
					}
					else if(tahmin2[i]==carpi.charAt(0)) {
						op2=op2+carpi;
					}
					else if(tahmin2[i]==bolu.charAt(0)) {
						op2=op2+bolu;
					}
					tmp=1;
				}
				
			}
			if(tahmin2[i]==esittir.charAt(0)) {
				i++;
				while(i < length) {
					sonuc=sonuc+tahmin2[i];
					i++;
				}
				i--;
				if(op1.charAt(0)==arti.charAt(0)){
					sonuc2=Integer.parseInt(s1)+Integer.parseInt(s2);
				}
				else if(op1.charAt(0)==eksi.charAt(0)) {
					sonuc2=Integer.parseInt(s1)-Integer.parseInt(s2);
				}
				else if(op1.charAt(0)==carpi.charAt(0)) {
					sonuc2=Integer.parseInt(s1)*Integer.parseInt(s2);
				}
				else if(op1.charAt(0)==bolu.charAt(0)) {
					sonuc2=Integer.parseInt(s1)/Integer.parseInt(s2);
				}
				
				if(Integer.parseInt(sonuc)==sonuc2) {
					flag=1;
					return flag;
				}
				else {
					return flag;
				}
			}else {
				i++;
				while(tahmin2[i]!= esittir.charAt(0)) {
					s3=s3+tahmin2[i];
					i++;
				}
				i++;
				while(i < length) {
					sonuc=sonuc+tahmin2[i];
					i++;
				}
				if(op2.equals(carpi)) {
					tmp=Integer.parseInt(s2)*Integer.parseInt(s3);
					if(op1.equals(arti)){
						sonuc2=Integer.parseInt(s1)+tmp;
					}
					else if(op1.equals(eksi)) {
						sonuc2=Integer.parseInt(s1)-tmp;
					}
					else if(op1.equals(carpi)) {
						sonuc2=Integer.parseInt(s1)*tmp;
					}
					else if(op1.equals(bolu)) {
						sonuc2=Integer.parseInt(s1)/tmp;
					}
				}
				if(op2.equals(bolu)) {
					tmp=Integer.parseInt(s2)/Integer.parseInt(s3);
					if(op1.equals(arti)){
						sonuc2=Integer.parseInt(s1)+tmp;
					}
					else if(op1.equals(eksi)) {
						sonuc2=Integer.parseInt(s1)-tmp;
					}
					else if(op1.equals(carpi)) {
						sonuc2=Integer.parseInt(s1)*tmp;
					}
					else if(op1.equals(bolu)) {
						sonuc2=Integer.parseInt(s1)/tmp;
					}
				}
				if(op2.equals(arti)) {
					
					if(op1.equals(arti)){
						tmp=Integer.parseInt(s2)+Integer.parseInt(s3);
						sonuc2=Integer.parseInt(s1)+tmp;
					}
					else if(op1.equals(eksi)) {
						tmp=Integer.parseInt(s2)+Integer.parseInt(s3);
						sonuc2=Integer.parseInt(s1)-tmp;
					}
					else if(op1.equals(carpi)) {
						tmp=Integer.parseInt(s1)*Integer.parseInt(s2);
						sonuc2=Integer.parseInt(s3)+tmp;
					}
					else if(op1.equals(bolu)) {
						tmp=Integer.parseInt(s1)/Integer.parseInt(s2);
						sonuc2=Integer.parseInt(s3)+tmp;
					}
				}
				if(op2.equals(eksi)) {
					if(op1.equals(arti)){
						tmp=Integer.parseInt(s1)+Integer.parseInt(s2);
						sonuc2=tmp-Integer.parseInt(s3);
					}
					else if(op1.equals(eksi)) {
						tmp=Integer.parseInt(s1)-Integer.parseInt(s2);
						sonuc2=tmp-Integer.parseInt(s3);
					}
					else if(op1.equals(carpi)) {
						tmp=Integer.parseInt(s1)*Integer.parseInt(s2);
						sonuc2=tmp-Integer.parseInt(s3);
					}
					else if(op1.equals(bolu)) {
						tmp=Integer.parseInt(s1)/Integer.parseInt(s2);
						sonuc2=tmp-Integer.parseInt(s3);
					}
				}
				if(Integer.parseInt(sonuc)==sonuc2) {
					flag=1;
					return flag;
				}
				else {
					flag=0;
					return flag;
				}
				
			}
		}
		}
	
	//�retilen denklem ile tahmin edilen denklem kar��la�t�r�lmas� yap�l�r;
	//Konum ve de�er olarak ayn� olan indisler i�in color dizisine 1 say�s� atan�r.
	//De�er olarak denklemde mevcutt fakat konumu farkl� olan de�erler i�in color dizisine 2 de�eri atan�r.
	//Deklemde hi� yer almayan de�erler color dizisinde 0 olarak kal�r
	//Color dizisine 1 veya 2 diye atama oldu�u durumda denklemin o indisdeki de�eri 'a' ile tahminin de�eri 'b' ile de�i�tirilerek sonraki d�ng�lerde i�leme tekrar al�nmas� engellenir.
	public void renklendirme() {
		for(i=0;i<length;i++) {
			if(tahmin2[i]==ifade2[i]) {
				color[i]=1;
				ifade2[i]=a.charAt(0);
				tahmin2[i]=b.charAt(0);
			}
		}
		for(i=0;i<length;i++) {
			for(int j=0; j<length;j++) {
				if(color[i]==0 &&tahmin2[i]==ifade2[j]) {
					color[i]=2;
					ifade2[j]=a.charAt(0);
					tahmin2[i]=b.charAt(0);
				}
			}
		}
	}
	//Tahmin edilen denklemde ka� adet e�ittir oldu�u aran�r.
	public int esittir_Arama(char[] tahmin2) {
		tmp=0;
		for(int i=0; i<length_tahmin ; i++) {
			if(tahmin2[i]==esittir.charAt(0)) {
				tmp++;
			}

		}
		return tmp;
	}	
	// Tahmin edilen denklemde ka� adet operat�r oldu�u aran�r (+ - * /)
	public int op_Arama(char[] tahmin2) {
		tmp=0;
		for(int i=0; i<length_tahmin ; i++) {
			if((tahmin2[i]==arti.charAt(0)) || (tahmin2[i]==eksi.charAt(0))|| (tahmin2[i]==carpi.charAt(0)) || (tahmin2[i]==bolu.charAt(0))) {
				tmp++;
			}
		}
		return tmp;
	}
	// Tahmin edilen denklemde ilk veya son indisde operat�r olup olmad��� kontrol edilir
	public int op_Kontrol(char[] tahmin2) {
		tmp=0;
		if((tahmin2[0]==arti.charAt(0)) || (tahmin2[0]==eksi.charAt(0))|| (tahmin2[0]==carpi.charAt(0)) || (tahmin2[0]==bolu.charAt(0))) {
			tmp++;
		}
		if((tahmin2[length_tahmin -1]==arti.charAt(0)) || (tahmin2[length_tahmin -1]==eksi.charAt(0))|| (tahmin2[length_tahmin -1]==carpi.charAt(0)) || (tahmin2[length_tahmin -1]==bolu.charAt(0))) {
			tmp++;
		}
		
		return tmp;
	}
	//Tahmin edilen denklemde pespe�e operat�r gelip gelmedi�i kontrol edilir.
	public int op_Pespese(char[] tahmin2) {
		tmp=0;
		int i;
		for(i=0;i<length_tahmin-1;i++) {
			if((tahmin2[i]==arti.charAt(0)) || (tahmin2[i]==eksi.charAt(0))|| (tahmin2[i]==carpi.charAt(0)) || (tahmin2[i]==bolu.charAt(0))) {
				if((tahmin2[i+1]==arti.charAt(0)) || (tahmin2[i+1]==eksi.charAt(0))|| (tahmin2[i+1]==carpi.charAt(0)) || (tahmin2[i+1]==bolu.charAt(0))) {
					tmp=1;
				}
			}
		}
		
		return tmp;
	}

	public int[] getArr() {
		return color;
	}	
}
