#include<stdio.h>
#include<stdlib.h>
#include<time.h>
void sieve_of_atkin(int limit,int [],int);
int main(){
	long int limit[100];
	int i,complexity[100],length,exit;
	double time_spent[100]={0};
	i=0;
	do{
		printf("Bulmak istediginiz asal sayilarin limitini giriniz:");
		scanf("%ld",&limit[i]);
		clock_t begin=clock();
		sieve_of_atkin(limit[i],complexity,i);
		clock_t end=clock();
		time_spent[i]=((double)(end-begin))/CLOCKS_PER_SEC;
		printf("\nExecution Time:%lfs\n",time_spent[i]);
		printf("Programi bitirmek icin 0'a\nDevam etmek icin 1'e basiniz\nEnter:");
		scanf("%d",&exit);
		i++;
	}while(exit!=0);
	length=i;
	printf("       Limit\t|\tComplexity\t|\tTime(s)\n");
	for(i=0;i<length;i++){
		printf("%10ld     \t%10d  \t %20.3lf\n",limit[i],complexity[i],time_spent[i]);
	}
	
}

void sieve_of_atkin(int limit,int a[],int index){
	int n,i;
	int *numbers=(int*)malloc(sizeof(int)*limit);
	int x=1,y,counter=0;
	for(i=0;i<limit;i++){
		numbers[i]=0;
	}
	for(x=1;x*x<limit;x++) {
        for(y=1;y*y<limit;y++) {
			//Sieve of atkin formüllerinin hesabý
            n = (4 * x * x) + (y * y);
            counter++;
            if(n<=limit && (n%12==1 || n%12==5))
                numbers[n]++;
                
            n = (3 * x * x) + (y * y);
            if(n<=limit && n%12==7)
                numbers[n]++;

            n = (3 * x * x) - (y * y);
            if(x>y && n<=limit && n%12==11)
                numbers[n]++;
        }
    }
	//Kare olan sayýlarýn katlarýný dizide 0 yaparak asal olmadýðýný belirtiyoruz.
	for(x=5;x*x<limit;x++){
		if(numbers[x]%2!=0){
			for(i=x*x;i<limit;i+=(x*x)){
				numbers[i]=0;
				counter++;
			}
		}
	}
	printf("ASAL SAYILAR:\n");
	if(limit>2)
		printf("\t 2\t");
	if(limit>3)
		printf(" 3\t");//2 ve 3'ü asal kabul ediyoruz diðer sayýlar asal deðil diye iþaretleniyor.
	//Diziyi kullanarak tek sayýda çözüme sahip olan indexleri yazdýrýyoruz.
	for(i=5;i<=limit;i++){
		if(numbers[i]%2!=0)
			printf(" %d\t",i);
	}
	printf("\nComplexity=%d",counter);
	a[index]=counter;
}
