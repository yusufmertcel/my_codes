//Simpson Method
#include<stdio.h>
#include<math.h>
#define MAX 100
float integral(float kat[],float xn,float x0,int n,int ,int);
float equation(float t,float kat[],int derece,int );
int main(){
	float kat[MAX],a,b;
	int N,derece,i;
	printf("Limit degerlerini kucukten buyuge giriniz:");
	scanf("%f%f",&a,&b);
	printf("N:");
	scanf("%d",&N);
	printf("Derece:");
	scanf("%d",&derece);
	for(i=0;i<=derece;i++){
		printf("x^%d. terimin katsayisini giriniz:",i);
		scanf("%f",&kat[i]);
	}
	printf("Integral|%0.f to %0.f = %0.2f\n",a,b,integral(kat,b,a,N,1,derece));
	printf("Area|%0.f to %0.f = %0.2f",a,b,integral(kat,b,a,N,-1,derece));
}

float integral(float kat[],float xn,float x0,int n,int sign,int derece){
	float h,S=0;
	int i;
	h=fabs(1.0*(xn-x0)/n);
	S=equation(x0,kat,derece,sign)+equation(xn,kat,derece,sign);
	for(i=1;i<=n-1;i+=2){
		//printf("%f\n",S);
		S=S+4*equation(x0+i*h,kat,derece,sign);
	}
	for(i=2;i<=n-2;i+=2){
		//printf("%f\n",S);
		S=S+2*equation(x0+i*h,kat,derece,sign);
	}
	S=(h/3)*S;
	return S;
}

float equation(float t,float kat[],int derece,int sign){
	int i;
	float denk=0;
	for(i=0;i<=derece;i++){
		denk+=(pow((double)t,(double)i)*kat[i]);
	}
	if(denk<0){
		denk=denk*sign;
	}
	return denk;
}
