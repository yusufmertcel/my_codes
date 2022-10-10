#include<stdio.h>
#include<stdlib.h>
int** compress(int **matris, int M, int N, int *satir);
int** getmatrix(int M, int N);
void print_matris(int** m, int M, int N);
int main(){

	int i,j;
	int M, N;
	int satir=0;
	int **matris, **matrisC;
	printf("Enter M an N"); scanf("%d %d",&M,&N);
	matris=getmatrix(M,N);
	
	for(i=0;i<M;i++){
		for(j=0;j<N;j++){
			matris[i][j]=rand()%2;
		}
	}
	print_matris(matris,M,N);
	matrisC=compress(matris,M,N,&satir);
	printf("===========================\n");
	print_matris(matrisC,satir,3);
	
}
int** compress(int **matris, int M, int N, int *satir){
	int i,j,k=0;
	int **m;
	for(i=0;i<M;i++){
		for(j=0;j<N;j++){
			if(matris[i][j]>0){
				(*satir)++;
			}
		}
	}
	m=getmatrix(*satir,3);
	for(i=0;i<M;i++){
		for(j=0;j<N;j++){
			if(matris[i][j]>0){
				m[k][0]=i;
				m[k][1]=j;
				m[k][2]=matris[i][j];
				k++;
			}
		}
	}
return m;	

}

int** getmatrix(int M, int N){
	int i,**m;
	m = (int**) calloc(M,sizeof(int*));
	for(i=0;i<M;i++){
		m[i] = (int*) calloc(N,sizeof(int));
	}
	return m;
}

void print_matris(int** m, int M, int N){
	int i,j;
	for(i=0;i<M;i++){
		for(j=0;j<N;j++){
			printf("%3d",m[i][j]);
		}
		printf("\n");
	}
}
