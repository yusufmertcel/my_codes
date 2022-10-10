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
	
	//Rastgele se�ilen 2 say�(1-99 aras�) ve 1 operat�r�n se�ilmesi ile i�lem ba�alt�l�r. 
	//Say�lar�n operat�re g�re i�leme girmesi sonucu olu�an negatif olmas� senaryosunda yeniden 2 say� ve operat�r se�me ii�lemi sonuz pozitif ��kana kadar tekrarlan�r.
	
	
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
		//2 say�n�n sonucun operat�r ve e�ittirin toplam lengthinin 7den k���k veya 9dan b�y�k olmas�na g�re 2 ayr� senaryo ele al�nm��t�r.
		//Lengthin 7den k���k olmas� durumunda da 2 farkl� senaryo ele al�n�r;
		//1-Operat�r '- veya /' olmas� durumunda ilk say� length 7den b�y�k olana kadar 2 ile �ar�larak b�y�t�l�r.
		//2-1-Operat�r '+ veya *' olmas� durumunda 3. bir say� ve 2. bir operat�r(+ veya *) se�ilerek (length uzunlu�u 9dan k���k olacak �ekilde 3.say� se�imi yap�l�r)
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
		//Lengthin 9'dan b�y�k olma senaryosu yaln�zca se�ilen ilk op'un + veya * olmas� durumunda ger�ekle�ir. Bu nedenle length 9 alt�na d��ene kadar ilk say� 2ye b�l�nerek devam edilir.
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
		//op1 ve op2 durumuna g�re �retilen denklem ifade edilir.
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
