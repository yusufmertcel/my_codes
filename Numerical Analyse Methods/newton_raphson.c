//Newton-Raphson
#include<stdio.h>
#include<math.h>
float newton_raphson(float a,float k);
float derivative(float x,int kat[],int derece);
float equat(float x,int kat[],int derece);
int main(){
	float x1,x2,kok,initiate,mistake;
	char join;
	printf("Enter the x1 that is value of y1:");
	scanf("%f",&x1);
	printf("Enter the x2 that is value of y2:");
	scanf("%f",&x2);
	printf("Do you want to assign an initial value:(Y/N)");
	scanf(" %c",&join);
	
	
	printf("Enter the mistake:");
	scanf("%f",&mistake);
	if(join=='Y' || join=='y'){
		printf("Initial Value:");
		scanf("%f",&initiate);
		kok=newton_raphson(initiate,mistake);
	}
	else if(x1<x2)
		kok=newton_raphson(x1,mistake);
	else 
		kok=newton_raphson(x2,mistake);
	printf("X~~%f",kok);
	return 0;
}

float newton_raphson(float a,float k){
	float Hata=k+1,fa,ga,c;
	int derece,kat[100],i;
	printf("Denklemin derecesini giriniz:");
	scanf("%d",&derece);
	for(i=derece;i>=0;i--){
		printf("Enter x^%d. parameter of term:",i);
		scanf("%d",&kat[i]);
	}
	fa=equat(a,kat,derece);
	ga=derivative(a,kat,derece);
	if(ga==0){
		a+=k;
		ga=derivative(a,kat,derece);
	}
	printf("denklem=%f --- turev=%f\n",fa,ga);
	while(Hata>k){
		c=a-(fa/ga);
		printf("C:%f\n",c);
		Hata=fabs(c-a);//x1-x0
		printf("Mistake:%f\n",Hata);
		a=c; //a=x1
		fa=equat(a,kat,derece);
		ga=derivative(a,kat,derece);
		if(ga==0){
			a+=k;
			ga=derivative(a,kat,derece);
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

float derivative(float x,int kat[],int derece){
	float deriv=0;
	int i;
	for(i=derece;i>0;i--){
		deriv=deriv+(kat[i]*i*pow(x,i-1));
	}
	return deriv;
}
