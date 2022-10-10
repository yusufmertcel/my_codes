#include<stdio.h>
#include "menu.h"
#include "calcul.h"

#define RESULT printf("Result: %.3f",result);
typedef enum{sum=1,mul,div,mod}ops;

int main(){
	int op;
	
	float result;
	int x,y;
	printf("Operand 1:");
	scanf("%d",&x);
	printf("Operand 2:");
	scanf("%d",&y);
	MENU;
	scanf("%d",&op);
	switch(op){
		case sum:
			result=SUM(x,y);
			RESULT;
			break;
		case mul:
			result=MUL(x,y);
			RESULT;
			break;
		case div:
			result=DIV((float)x,y);
			RESULT;
			break;
		case mod:
			result=MOD(x,y);
			RESULT;
			break;
		default:
			printf("Wrong operation have been entered!!!");
			break;
	}
	
}
