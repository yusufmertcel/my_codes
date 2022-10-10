//Matrix Invers
#include<stdio.h>
#define MAX 100
int main(){
	float matrix[MAX][MAX];
	int row,column,i,j;
	printf("ROW LENGTH:");
	scanf("%d",&row);
	printf("COLUMN LENGTH:");
	scanf("%d",&column);
	scan(matrix,row,column);
	for(i=0;i<row;i++){
		for(j=column;j<column*2;j++){
			if(i==(j-column)){
				matrix[i][j]=1;
			}
			else{
				matrix[i][j]=0;
			}
		}
	}
	print(matrix,row,column*2);
	eliminasyon(matrix,row,column*2);
	printf("Invers A matrix:\n");
	for(i=0;i<row;i++){
		for(j=column;j<column*2;j++){
			printf("%+-0.3f ",matrix[i][j]);
		}
		printf("\n");
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
		for(j=0;j<r;j++){
			hold=a[j][i];
			if(j!=i){
				for(p=i;p<c;p++){
					a[j][p]=a[j][p]-(hold*a[i][p]);
				}	
			}
			printf("\n");
		}
		print(a,r,c);
		i++;
	}
}

void print(float a[][MAX],int r,int c){
	int i,j;
	for(i=0;i<r;i++){
		for(j=0;j<c;j++){
			printf("%0.3f ",a[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}

void scan(float a[][MAX],int r,int c){
	int i,j;
	for(i=0;i<r;i++){
		for(j=0;j<c;j++){
			printf("[%d][%d]=",i,j);
			scanf("%f",&a[i][j]);
		}
	}
}
