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
	

	//Denklemde sýra ile uygunluðu bozacak durumlarýn var olup olmadýðý saptanmaya çalýþýlýr. Herhangi bi sorun olmamasý durumunda tahmin olarak yazýlan deðerlerin bir denklem oluþturup oluþturmadýðý kontrol edilir.
	//Tahmin edilen denklemin bir denklem oluþturup oluþturmadýðý senaryosu 2 veya 3 operatör olma durumuna göre ayrý ayrý deðerlendirilmþtir.
	// 2 operatör olma durumunda sayý 1 sayý2 sonuc ve operatör ayrý ayrý tutulup iki sayýnýn operatöre göre iþlem sonucu 'sonuc'a eþit olup olmadýðý kontrol edilir.4
	// 3 operatör olma durumunda sayý 1 sayý2 sayi3 sonuc ve operatör1 operatör ayrý ayrý tutulup, operatör önceliðine göre sayýlarýn operatörlere göre iþlem sonucu 'sonuc'a eþit olup olmadýðý kontrol edilir.
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
			System.out.println("Denklemin uzunluðu ile tahmin edilen denklemin uzunluklarý eþit deðil. Yeniden kontrol ediniz :");
			return flag;
		}
		
		else if(esittir_Arama(tahmin2) != 1) {
			flag=0;
			System.out.println("Eþittir sayýsý 1 olmalýdýr. Yeniden kontrol ediniz :");
			return flag;
		}
		
		else if((op_Arama(tahmin2) == 0 ) || (op_Arama(tahmin2) > 2 ) ) {
			System.out.println("Operatör sayýsý en fazla 2 olabilir.Yeniden kontrol ediniz :" );
			flag=0;
			return flag;
		}
		else if(op_Kontrol(tahmin2) != 0) {
			System.out.println("Ýlk/son karakter bir operatör olamaz. Yeniden kontrol ediniz:");
			flag=0;
			return flag;
		}
		else if(op_Pespese(tahmin2) != 0) {
			System.out.println("Peþpeþe operatör koyamayýnýz. Yeniden kontrol ediniz :");
			flag=0;
			return flag;
		}
		else if(tahmin2[0]==sifir.charAt(0)) {
			System.out.println(" Ýlk karakter '0' olamaz ");
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
	
	//Üretilen denklem ile tahmin edilen denklem karþýlaþtýrýlmasý yapýlýr;
	//Konum ve deðer olarak ayný olan indisler için color dizisine 1 sayýsý atanýr.
	//Deðer olarak denklemde mevcutt fakat konumu farklý olan deðerler için color dizisine 2 deðeri atanýr.
	//Deklemde hiç yer almayan deðerler color dizisinde 0 olarak kalýr
	//Color dizisine 1 veya 2 diye atama olduðu durumda denklemin o indisdeki deðeri 'a' ile tahminin deðeri 'b' ile deðiþtirilerek sonraki döngülerde iþleme tekrar alýnmasý engellenir.
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
	//Tahmin edilen denklemde kaç adet eþittir olduðu aranýr.
	public int esittir_Arama(char[] tahmin2) {
		tmp=0;
		for(int i=0; i<length_tahmin ; i++) {
			if(tahmin2[i]==esittir.charAt(0)) {
				tmp++;
			}

		}
		return tmp;
	}	
	// Tahmin edilen denklemde kaç adet operatör olduðu aranýr (+ - * /)
	public int op_Arama(char[] tahmin2) {
		tmp=0;
		for(int i=0; i<length_tahmin ; i++) {
			if((tahmin2[i]==arti.charAt(0)) || (tahmin2[i]==eksi.charAt(0))|| (tahmin2[i]==carpi.charAt(0)) || (tahmin2[i]==bolu.charAt(0))) {
				tmp++;
			}
		}
		return tmp;
	}
	// Tahmin edilen denklemde ilk veya son indisde operatör olup olmadýðý kontrol edilir
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
	//Tahmin edilen denklemde pespeþe operatör gelip gelmediði kontrol edilir.
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
