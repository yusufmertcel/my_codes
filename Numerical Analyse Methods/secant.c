//Secant
#include<stdio.h>
#include<math.h>
float newton_raphson(float a,float k,float b);
float derivative(float a,float b,int kat[],int derece);
float equat(float x,int kat[],int derece);
int main(){
	float x0,x1,x2,kok,initiate,mistake;
	char join;
	//x2-4x+3
	printf("Enter the x0 that is index of y0:");
	scanf("%f",&x0);
	printf("Please,assign an initial value:");
	scanf("%f",&x1);
	/*printf("Do you want to assign an initial value:(Y/N)");
	scanf(" %c",&join);*/
	
	printf("Enter the mistake:");
	scanf("%f",&mistake);
	
	kok=(x1,mistake,x0);
	/*if(join=='Y' || join=='y'){
		printf("Initial Value:");
		scanf("%f",&initiate);
		kok=newton_raphson(initiate,mistake,x0);
	}
	else if(x1<x2)
		kok=newton_raphson(x1,mistake,x0);
	else 
		kok=newton_raphson(x2,mistake,x0);*/
	printf("X~~%f",kok);
	return 0;
}

float secant(float a,float k,float b){
	float Hata=k+1,fa,ga,c;
	int derece,kat[100],i;
	printf("Denklemin derecesini giriniz:");
	scanf("%d",&derece);
	for(i=derece;i>=0;i--){
		printf("Enter x^%d. parameter of term:",i);
		scanf("%d",&kat[i]);
	}
	fa=equat(a,kat,derece);
	ga=derivative(a,b,kat,derece);
	if(ga==0){
		a+=k;
		ga=derivative(a,b,kat,derece);
	}
	printf("denklem=%f --- secant=%f\n",fa,ga);
	while(Hata>k){
		c=a-(fa/ga);
		printf("C:%f\n",c);
		Hata=fabs(c-a);//x2-x1
		printf("Mistake:%f\n",Hata);
		b=a;//b=x1
		a=c; //a=x2
		fa=equat(a,kat,derece);
		ga=derivative(a,b,kat,derece);
		if(ga==0){
			a+=k;
			ga=derivative(a,b,kat,derece);
		}
	}
	//printf("Iteration:%d\n",iteration);
	if(Hata<=k){
		return c;
	}
}

float equat(float x,int kat[],int derece){
	float denklem=0,deriv=0;
	int i;
	for(i=derece;i>=0;i--){
		denklem=denklem+kat[i]*pow(x,i);
	}
	return denklem;
}

float derivative(float x1,float x0,int kat[],int derece){
	//x3+x2+1
	float deriv,f0,f1;
	int i;
	f0=equat(x0,kat,derece);
	f1=equat(x1,kat,derece);
	deriv=(f0-f1)/(x0-x1);
	return deriv;
}
