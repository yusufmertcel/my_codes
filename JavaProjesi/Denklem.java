package Proje;

import java.util.Random;

public class Denklem {
	private int n1;
	private int n2;
	private int n3;
	private int op1;
	private int op2=0;
	private int sonuc=-1;
	private int length;
	private int tmp;
	
	private String s1;
	private String s2;
	private String s3;
	private String sonuc2;
	private String ifade="";
	
	//Rastgele seçilen 2 sayý(1-99 arasý) ve 1 operatörün seçilmesi ile iþlem baþaltýlýr. 
	//Sayýlarýn operatöre göre iþleme girmesi sonucu oluþan negatif olmasý senaryosunda yeniden 2 sayý ve operatör seçme iiþlemi sonuz pozitif çýkana kadar tekrarlanýr.
	
	
	public void start() {
		Random rastgele = new Random();
		do {
			n1 = rastgele.nextInt(99)+1;
			n2 = rastgele.nextInt(9)+1;
			op1 = rastgele.nextInt(4)+1;
			switch(op1) {
	        case 1:
	            sonuc=n1+n2;
	            break;
	        case 2:
	        	sonuc=n1-n2;
	        	break;
	        case 3:
	        	sonuc=n1*n2;
	        	break;
	        case 4:
	        	if(n1 > n2) {
	        		if(n1 % n2 != 0) {
	        			n1=n1-(n1 % n2);
	        		}
	        		sonuc=n1/n2;
	        	}
	        	else {
	        		tmp=n1;
	        		n1=n2;
	        		n2=tmp;
	        		if(n1 % n2 != 0) {
	        			n1=n1-(n1 % n2);
	        		}
	        		sonuc=n1/n2;
	        	}
	        	break;
	        }
		}while(sonuc < 0);
		
		
		 s1=Integer.toString(n1);
		 s2=Integer.toString(n2);
		 sonuc2=Integer.toString(sonuc);
		length=(s1.length())+(s2.length())+(sonuc2.length());
		length=length+2;
		//2 sayýnýn sonucun operatör ve eþittirin toplam lengthinin 7den küçük veya 9dan büyük olmasýna göre 2 ayrý senaryo ele alýnmýþtýr.
		//Lengthin 7den küçük olmasý durumunda da 2 farklý senaryo ele alýnýr;
		//1-Operatör '- veya /' olmasý durumunda ilk sayý length 7den büyük olana kadar 2 ile çarýlarak büyütülür.
		//2-1-Operatör '+ veya *' olmasý durumunda 3. bir sayý ve 2. bir operatör(+ veya *) seçilerek (length uzunluðu 9dan küçük olacak þekilde 3.sayý seçimi yapýlýr)
		if(length < 7) {
			if((op1==2) || (op2==4)) {
				do {
					n1=n1*2;
					if(op1==1) {
						sonuc=n1-n2;
						 s1=Integer.toString(n1);
						 sonuc2=Integer.toString(sonuc);
						 length=(s1.length())+(s2.length())+(sonuc2.length());
						 length=length+2;
					}else {
						sonuc=n1/n2;
						 s1=Integer.toString(n1);
						 sonuc2=Integer.toString(sonuc);
						 length=(s1.length())+(s2.length())+(sonuc2.length());
						 length=length+2;
					}
					
				}while(length<7);
			}
			else {
				
				op2 = rastgele.nextInt(2)+1;
				if(op2==1) {
					n3 = rastgele.nextInt(90);
					sonuc=sonuc+n3;
				}
				else if(op2==3) {
					n3 = rastgele.nextInt(8)+1;
					if(op1==1) {
	        			tmp=n2*n3;
	        			sonuc=n1+tmp;
	        		}
	        		else if(op1==3) {
	        			sonuc=(sonuc)*n3;
	        		}
				}
				
				s3=Integer.toString(n3);
				 sonuc2=Integer.toString(sonuc);
				length=(s1.length())+(s2.length())+(s3.length())+(sonuc2.length());
				length=length+3;
			}
		}
		//Lengthin 9'dan büyük olma senaryosu yalnýzca seçilen ilk op'un + veya * olmasý durumunda gerçekleþir. Bu nedenle length 9 altýna düþene kadar ilk sayý 2ye bölünerek devam edilir.
		else if(length >9) {
			do {
				n1=n1/2;
				if(op1==1) {
					sonuc=n1+n2;
					 s1=Integer.toString(n1);
					 sonuc2=Integer.toString(sonuc);
					 length=(s1.length())+(s2.length())+(sonuc2.length());
					 length=length+2;
				}else {
					sonuc=n1*n2;
					 s1=Integer.toString(n1);
					 sonuc2=Integer.toString(sonuc);
					 length=(s1.length())+(s2.length())+(sonuc2.length());
					 length=length+2;
				}
			}while(length >9);
		}
		//op1 ve op2 durumuna göre üretilen denklem ifade edilir.
		if(op2==0) {
			switch(op1) {
        case 1:
        	ifade=s1+"+"+s2+"="+sonuc;
            break;
        case 2:
        	ifade=s1+"-"+s2+"="+sonuc;
        	break;
        case 3:
        	ifade=s1+"*"+s2+"="+sonuc;
        	break;
        case 4:
        	ifade=s1+"/"+s2+"="+sonuc;
        	break;
			}
		}else {
			switch(op1) {
	        case 1:
	        	if(op2==1) {
	        		ifade=s1+"+"+s2+"+"+s3+"="+sonuc;
	        	}
	        	else if(op2==2) {
	        		ifade=s1+"+"+s2+"-"+s3+"="+sonuc;
	        	}
	        	else if(op2==3) {
	        		ifade=s1+"+"+s2+"*-"+s3+"="+sonuc;
	        	}
	        	else if(op2==4) {
	        		ifade=s1+"+"+s2+"/"+s3+"="+sonuc;
	        	}
	            break;
	        case 2:
	        	if(op2==1) {
	        		ifade=s1+"-"+s2+"+"+s3+"="+sonuc;
	        	}
	        	else if(op2==2) {
	        		ifade=s1+"-"+s2+"-"+s3+"="+sonuc;
	        	}
	        	else if(op2==3) {
	        		ifade=s1+"-"+s2+"*-"+s3+"="+sonuc;
	        	}
	        	else if(op2==4) {
	        		ifade=s1+"-"+s2+"/"+s3+"="+sonuc;
	        	}
	        	break;
	        case 3:
	        	if(op2==1) {
	        		ifade=s1+"*"+s2+"+"+s3+"="+sonuc;
	        	}
	        	else if(op2==2) {
	        		ifade=s1+"*"+s2+"-"+s3+"="+sonuc;
	        	}
	        	else if(op2==3) {
	        		ifade=s1+"*"+s2+"*"+s3+"="+sonuc;
	        	}
	        	else if(op2==4) {
	        		ifade=s1+"*"+s2+"/"+s3+"="+sonuc;
	        	}
	        	break;
	        case 4:
	        	if(op2==1) {
	        		ifade=s1+"/"+s2+"+"+s3+"="+sonuc;
	        	}
	        	else if(op2==2) {
	        		ifade=s1+"/"+s2+"-"+s3+"="+sonuc;
	        	}
	        	else if(op2==3) {
	        		ifade=s1+"/"+s2+"*-"+s3+"="+sonuc;
	        	}
	        	else if(op2==4) {
	        		ifade=s1+"/"+s2+"/"+s3+"="+sonuc;
	        	}
	        	break;
				}
		}
			
			System.out.println(ifade);
		
	}
	
	public String getIfade() {
		return ifade;
	}

	public int getLength() {
		return length;
	}
}
