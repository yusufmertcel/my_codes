#include<stdio.h>

int BinarysearchR(int arr[],int bas,int son,int x){
	if(bas<=son){
		int m=bas+(son-bas)/2;
		if(arr[m]==x)
			return m;
		if(arr[m]<x)
			return BinarysearchR(arr,m+1,son,x);
		else
			return BinarysearchR(arr,m-1,son,x);
	}
	else{
		return -1;
	}
}

int Binarysearch(int arr[],int bas,int son,int x){
	while(bas<=son){
		int m=bas+(son-bas)/2;
		if(arr[m]==x)
			return m;
		if(arr[m]<x)
			bas=m+1;
		else
			son=m-1;
	}
	return -1;
}

int main(){
	int dizi[100],N,i,index,X;
	printf("N değeri giriniz");
	scanf("%d",&N);
	for(i=0;i<N;i++){
		scanf("%d",&dizi[i]);
	}
	printf("Bulmak istediğinin değeri giriniz:");
	scanf("%d",&X);
	index=BinarysearchR(dizi,0,N-1,X);
	printf("The number %d that you search is at index %d.",dizi[index],index);
}
