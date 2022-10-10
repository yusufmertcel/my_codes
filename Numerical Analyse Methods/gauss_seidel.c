//Gauss Seidal
#include<stdio.h>
#include<math.h>
#define MAX 100
int mistake(float hata[],float E,int n);
void max_diagonal(float a[][MAX],int m,int n,float c[]);
void find_root(float hata[],float a[][MAX],float x[],float c[],int m,int n,float E);
int main(){
	float variables[MAX],katsayi[MAX][MAX],result[MAX],delta[MAX];
	float hata;
	int row,column,i,j;
	printf("Degisken sayisini giriniz:");
	scanf("%d",&column);
	printf("Denklem sayisini giriniz:");
	scanf("%d",&row);
	for(i=0;i<row;i++){
		printf("%d.denklemin katsayilarini giriniz:",i+1);
		for(j=0;j<column;j++){
			scanf("%f",&katsayi[i][j]);
		}
		printf("%d.denklemin sabit terimini giriniz:",i+1);
		scanf("%f",&result[i]);
	}
	printf("Degiskenlerin ilk degerlerini giriniz:");
	for(i=0;i<column;i++){
		scanf("%f",&variables[i]);
	}
	printf("Hatayi giriniz:");
	scanf("%f",&hata);
	for(i=0;i<column;i++){
		delta[i]=hata+1000;
	}
	max_diagonal(katsayi,row,column,result);
	for(i=0;i<column;i++){
		printf("    x%d\t\t",i+1);
	}
	printf("\n");
	do{
		find_root(delta,katsayi,variables,result,row,column,hata);
	}while(mistake(delta,hata,column));
	for(i=0;i<column;i++){
		printf("x%d = %0.2f\t",i+1,variables[i]);
	}
}

int mistake(float hata[],float E,int n){
	int i=0;
	while(hata[i]<=E && i<n){
		i++;
	}
	if(i==n){
		return 0;
	}
	return 1;
}

void find_root(float hata[],float a[][MAX],float x[],float c[],int m,int n,float E){
	int i,j;
	float sum,hold;
	for(i=0;i<m;i++){
		sum=0;
		if(hata[i]>E){
		for(j=0;j<n;j++){
			if(i!=j){
				sum+=(a[i][j]*x[j]);
			}
		}
		hold=x[i];
		x[i]=1.0*(c[i]-sum)/a[i][i];
		hata[i]=fabs(x[i]-hold);
		printf("%0.3f ",x[i]);
		printf("%0.3f\t",hata[i]);
		}
	}
	printf("\n");
}

void max_diagonal(float a[][MAX],int m,int n,float c[]){
	float prod=1,tmp;
	int max,i,j,k;
	for(i=0;i<n;i++){
		max=0;
		for(j=1;j<m;j++){
			if(fabs(a[j][i])>fabs(a[max][i])){
				max=j;
			}
		}
		tmp=c[i];
		c[i]=c[max];
		c[max]=tmp;
		for(k=0;k<n;k++){
			tmp=a[i][k];
			a[i][k]=a[max][k];
			a[max][k]=tmp;
			
		}
	}
}
