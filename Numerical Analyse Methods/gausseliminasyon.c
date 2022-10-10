//Gauss Eleminasyon
#include<stdio.h>
#define MAX 100
int main(){
	float extcoefmatrix[MAX][MAX],variables[MAX]={0};
	int row,column,i;
	printf("Denklem sayisini giriniz:");
	scanf("%d",&row);
	printf("Degisken sayisini giriniz:");
	scanf("%d",&column);
	column++;
	scan(extcoefmatrix,row,column);
	print(extcoefmatrix,row,column);
	eliminasyon(extcoefmatrix,row,column);
	print(extcoefmatrix,row,column);
	variable(extcoefmatrix,variables,row,column);
	for(i=0;i<column-1;i++){
		printf("x%d= %0.3f\t",i+1,variables[i]);
	}
}

void eliminasyon(float a[][MAX],int r,int c){
	int i,j,p,k;
	float hold,tmp;
	i=0;
	while(i<r){//1.satýr
		p=i+1;
		while(a[i][i]==0 && p<r){
			for(k=0;k<c;k++){
				tmp=a[p][k];
				a[p][k]=a[i][k];
				a[i][k]=tmp;
			}
			p++;
		}
		hold=a[i][i];
		for(k=i;k<c;k++){
			a[i][k]/=hold;
		}
		for(j=i+1;j<r;j++){
			hold=a[j][i];
			for(p=i;p<c;p++){
				a[j][p]=a[j][p]-(hold*a[i][p]);
			}
		}
		printf("\n\n\n");
		print(a,r,c);
		i++;
	}
}

void variable(float a[][MAX],float arr[],int r,int c){
	int i,j;
	arr[c-2]=a[r-1][c-1];
	for(i=r-2;i>=0;i--){
		for(j=c-2;j>i;j--){
			arr[i]=arr[i]+(arr[j]*a[i][j]);
		}
		arr[i]=a[i][c-1]-arr[i];
	}
}

void print(float a[][MAX],int r,int c){
	int i,j;
	for(i=0;i<r;i++){
		for(j=0;j<c;j++){
			printf("%0.2f ",a[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}

void scan(float a[][MAX],int r,int c){
	int i,j;
	for(i=0;i<r;i++){
		printf("%d. denklemi giriniz:\n",(i+1));
		for(j=0;j<c;j++){
			if(j==c-1){
				printf("C%d'i giriniz:",i+1);
			}
			else
				printf("x%d:",j+1);
			scanf("%f",&a[i][j]);
		}
	}
}
