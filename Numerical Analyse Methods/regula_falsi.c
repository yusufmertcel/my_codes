//Regula-Falsi
#include<stdio.h>
#include<math.h>

float regula_falsi(float a,float b,float k);
float equat(float ,int [],int );
int main(){
	float x1,x2,kok,mistake;
	printf("Enter the x1 that is value of y1:");
	scanf("%f",&x1);
	printf("Enter the x2 that is value of y2:");
	scanf("%f",&x2);
	printf("Enter the mistake:");
	scanf("%f",&mistake);
	kok=regula_falsi(x1,x2,mistake);
	printf("X~~%f",kok);
	
	
	return 0;
}

float regula_falsi(float a,float b,float k){
	float Hata=k+1,fa,fb,fc,c;
	int derece,kat[100],i,iteration=0;
	printf("Denklemin derecesini giriniz:");
	scanf("%d",&derece);
	for(i=derece;i>=0;i--){
		printf("Enter x^%d. parameter of term:",i);
		scanf("%d",&kat[i]);
	}
	fa=equat(a,kat,derece);
	fb=equat(b,kat,derece);
	if((fa*fb)>=0){
		printf("You entered wrong index!---");
		return -1;
	}
	while((fa*fb)<0 && Hata>k){
		iteration++;
		Hata=fabs(b-a)/pow(2,iteration);
		printf("Mistake:%f\n",Hata);
		c=(b*fa-a*fb)/(fa-fb);
		fc=equat(c,kat,derece);
		if(fa*fc<0){
			b=c;
			fb=fc;
		}
		else if(fa*fc>0){
			a=c;
			fa=fc;
		}
		else{
			printf("Iteration:%d\n",iteration);
			return c;
		}
	}
	if(Hata<=k){
		return c;
	}
}

float equat(float x,int kat[],int derece){
	float denklem=0;
	int i;
	for(i=derece;i>=0;i--){
		denklem=denklem+kat[i]*pow(x,i);
	}
	return denklem;
}
