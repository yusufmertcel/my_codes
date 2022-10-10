#include<stdio.h>
#include<stdlib.h>
#include<time.h>
int enbuyuk_bul(int **matrix,int size);
void liste_yazdir(int **matrix,int size);
int main(){
	int N,i,j;
	int **complex;
	printf("N:");
	scanf("%d",&N);
	srand(time(NULL));
	
	//complex matris oluþturma
	complex=(int**)calloc(N,sizeof(int*));
	if(complex==NULL){
		printf("%s:%d>> CAN NOT allocate the space!\n",__FILE__,__LINE__);
	}
	for(i=0;i<N;i++){
		complex[i]=(int*)calloc(2,sizeof(int));
		if(complex[i]==NULL){
			printf("%s:%d>> CAN NOT allocate the space!\n",__FILE__,__LINE__);
		}
	}
	
	for(i=0;i<N;i++){
		for(j=0;j<2;j++){
			complex[i][j]=rand()%10;
		}
	}
	
	for(i=0;i<N;i++){
		for(j=0;j<2;j++){
			printf("%3d",complex[i][j]);
		}
		printf("\n");
	}
	printf("%d\n",enbuyuk_bul(complex,N));
	liste_yazdir(complex,N);
	
	
	
	
	return 0;
}

int enbuyuk_bul(int **matrix,int size){
	int i,kare=0;
	for(i=0;i<size;i++){
		if(kare<(matrix[i][0]*matrix[i][0])+(matrix[i][1]*matrix[i][1]))
			kare=(matrix[i][0]*matrix[i][0])+(matrix[i][1]*matrix[i][1]);
	}
	
	return kare;
}

void liste_yazdir(int **matrix,int size){
	int i;
	for(i=0;i<size;i++){
		printf("%d+%di\n",matrix[i][0],matrix[i][1]);
	}
}
