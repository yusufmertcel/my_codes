#include<stdio.h>

main(){
	char dictionary[100][100];
	int letter[30];
	char Word[50];
	int N,i,j;
	printf("Enter the number of the words:");
	scanf("%d",&N);
	for(i=0;i<N;i++){
		scanf("%s",dictionary[i]);
	}
	int k,a;
	char tmp;
	for(i=1;i<N;i++){
		a=0;
		for(j=i;j>0;j--){
			while(dictionary[j][a]==dictionary[j-1][a]){
				a++;
			}
			if(dictionary[j][a]<dictionary[j-1][a]){
				k=0;
				while(dictionary[j][k]!='\0' || dictionary[j-1][k]!='\0'){
					tmp=dictionary[j][k];
					dictionary[j][k]=dictionary[j-1][k];
					dictionary[j-1][k]=tmp;
					k++;
				}
			}
			a=0;
		}	
	}
	printf("\n");
	for(i=0;i<N;i++){
		puts(dictionary[i]);
	}
	for(i=0;i<=25;i++){
		letter[i]=i;	
	}
	int index=0;
	i=0;
	while(index<=25){
		if(dictionary[i][0]==(letter[index]+'A')){
			letter[index]=i+1;
			i++;
			while(dictionary[i][0]==dictionary[i-1][0]){
				i++;
			}
		}
		else{
			letter[index]=0;
		}
		index++;
	}
	for(i=0;i<=25;i++){
		printf("%d+",letter[i]);
	}
	printf("\nEnter the word that you search:");
	scanf("%s",Word);
	j=0;
	i=0;
	while(Word[i]!='\0' && Word[i]>dictionary[j][i]){
		j++;
	}
	int sayac;
	if(dictionary[j][i]==Word[i]){
		k=0;
		while(letter[k]<=j+1){
			k++;
		}
		a=letter[k];
		k=1;
		for(i=j;i<a-1;i++){
			sayac=1;
			while(Word[k]!='\0' && sayac==1){
				if(dictionary[i][k]==Word[k]){
					k++;
				}
				else{
					sayac=0;
				}
			}
		}	
	}
	if(sayac==1){
		printf("\nThe word was founded.");
	}	
	else{
		printf("\nThe word wasn't founded.");
	}
	
	return 0;
}
