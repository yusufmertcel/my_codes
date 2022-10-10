#include<stdio.h>
#define MAX 100
void gregory_newton(float a[][2],int size,float x,int);
float pow(float a,int b);
int fakt(int f);
int ileri_fark(float a[][2],int size);
int esitmi(float dizi[],int a,int n);
float kok(float x,int kere,float a[][2]);
int main(){
	float deger[MAX][2];
	int i,j,cozum,exit;
	float value;
	printf("Gireceginiz ornek sayisini yaziniz:");
	scanf("%d",&cozum);
	for(i=0;i<cozum;i++){
			printf("%d. index degerini giriniz:",i+1);
			scanf("%f",&deger[i][0]);
			printf("%d. fonksiyon degerini giriniz:",i+1);
			scanf("%f",&deger[i][1]);	
	}
	i=0;
	do{
		printf("f(x) fonksiyonu icin x degerini giriniz:");
		scanf("%f",&value);
		gregory_newton(deger,cozum,value,i);
		printf("(1)Tekrar deger girmek icin 1'e basiniz\n(2)Cikmak icin 0'a basiniz\nEnter:");
		scanf("%d",&exit);
		i++;	
	}while(exit);
	

	
	return 0;
}

void gregory_newton(float a[][2],int size,float x,int sayi){
	float h=a[1][0]-a[0][0];
	static int sinir;
	int i;
	float denk=a[0][1];
	if(sayi==0){
		sinir=ileri_fark(a,size);
	}
	for(i=1;i<=sinir;i++){
		denk+=((a[i][1]*kok(x,i,a))/(pow(h,i)*1.0*fakt(i)));
	}
	printf("f(%.2f) = %f\n",x,denk);
}

float kok(float x,int kere,float a[][2]){
	int i;
	float kuad=1;
	for(i=0;i<kere;i++){
		kuad*=(x-a[i][0]);
	}
	return kuad;
}

float pow(float a,int b){
	int k;
	if(b==0){
		return 1;
	}
	else{
		k=pow(a,b/2);
		if(b%2==1)
			return a*k*k;
		else
			return k*k;
	}
}

int fakt(int f){
	if(f==1 || f==0)
		return 1;
	else
		return f*fakt(f-1);
}

int ileri_fark(float a[][2],int size){
	int i,j=0;
	float gecici[size];
	do{
		for(i=j;i<size;i++){
			gecici[i+1]=a[i+1][1]-a[i][1];
		}
		printf("%d. ileri fark:\n",j+1);
		for(i=j+1;i<size;i++){
			a[i][1]=gecici[i];
			printf("%.f\n",a[i][1]);
		}
		j++;
	}while(esitmi(gecici,j,size));
	return j;
} 

int esitmi(float dizi[],int a,int n){
	int i=a;
	while(i<n-1 && dizi[i]==dizi[i+1]){
		i++;
	}
	if(i==(n-1))
		return 0;
	else
		return 1;
}
