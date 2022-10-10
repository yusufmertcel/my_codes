//Sayýsal Türev 
#include<stdio.h>
#include<math.h>
#define MAX 100
float turev(float x,float h,float kat[],int mode,int mode2,int mertebe);
float equation(float t,float kat[],int derece);
int main(){
	float kat[MAX]={0},value,h,deriv;
	int mode,mertebe,i,mode2;
	do{
		printf("Turev hesaplama metodunu seciniz:\n(1)Ileri fark\n(2)Geri fark\n(3)Merkezi fark\nMode:");
		scanf("%d",&mode);
	}while(mode<1 || mode>3);
	printf("Deger ve hassasiyet miktarini giriniz:");
	scanf("%f%f",&value,&h);
	deriv=turev(value,h,kat,mode,mode2,mertebe);
	printf("TUREV:%0.4f",deriv);
}

float turev(float x,float h,float kat[],int mode,int mode2,int mertebe){
	float f1,fxi,fx0,fx1,fx2,fx3;
	int derece,i;
	printf("Derece:");
	scanf("%d",&derece);
	for(i=derece;i>=0;i--){
		printf("x^%d. terimin katsayisini giriniz:",i);
		scanf("%f",&kat[i]);
	}
	switch(mode){
		case 1://ileri fark
			fxi=equation(x+h,kat,derece);
			fx0=equation(x,kat,derece);
			f1=(fxi-fx0)/h*1.0;
			break;	
		case 2:// geri fark
			fxi=equation(x-h,kat,derece);
			fx0=equation(x,kat,derece);
			f1=(fx0-fxi)/h*1.0;
			break;
		case 3:// merkezi fark
			fxi=equation(x+h,kat,derece);
			fx0=equation(x-h,kat,derece);
			f1=(fxi-fx0)/(h*2.0);
			break;
		default:
			printf("Mode is not correct.");
	}
	return f1;
}

float equation(float t,float kat[],int derece){
	int i;
	float denk=0;
	for(i=derece;i>=0;i--){
		denk+=(pow((double)t,(double)i)*kat[i]);
	}
	return denk;
}
