#include<stdio.h>
#include<time.h>
#include<stdlib.h>
#undef NULL
#define NULL '\0'


int dicefunc(int k){
	int i,a;
	srand(time(NULL));
	for(i=0;i<k;i++){
		a=rand() % 6 +1;
	}
	return a;
}

int movement(int *p,int z){
	printf("Move your piece forward %d squares\n",z);
	int sum=0;
	*p=0;
	*(p+1)=0;
	int x,y;
	while(sum<z){
		printf("Row:");
		scanf("%d",&x);
		*p=*p+x;
		printf("Column:");
		scanf("%d",&y);
		*(p+1)=*(p+1)+y;
		sum+=abs(x)+abs(y);
		//printf("\nSUM:%d",sum);
	}
	return sum;
}

main(){
	
	char board[10][10] = {
							{NULL,'1','2','3','4','5','6','7','8','9'},
							{'1','Y','Y',NULL,'P','P','P',NULL,'R','R'},
							{'2','Y','Y',NULL,'P','0','P',NULL,'R','R'},
							{'3',NULL,NULL,NULL,'P','0','P',NULL,NULL,NULL},
							{'4','P','P','P','P','0','P','P','P','P'},
							{'5','P','0','0','0',NULL,'0','0','0','P'},
							{'6','P','P','P','P','0','P','P','P','P'},
							{'7',NULL,NULL,NULL,'P','0','P',NULL,NULL,NULL},
							{'8','G','G',NULL,'P','0','P',NULL,'B','B'},
							{'9','G','G',NULL,'P','P','P',NULL,'B','B'},
							};
	char dizi[33],press;
	dizi[32]=NULL;
	int i,j,u,user,count=0;
	int move[2]; //Saða,sola ve yukarý aþaðý kaç kare hareket edileceðini tutan dizi
	int winner,tour=1,player;
	int a,b;//nest deðiþkenleri
	int Y[4]={0,0,0,0},R[4]={8,8,8,8},B[4]={16,16,16,16},G[4]={24,24,24,24};//dizideki taþlarýn pozisyonlarý
	for(i=0;i<32;i++){
		dizi[i]='P';
	}
	int numberofplayers,dice;
	//MENU
	printf("<RULES>\n");
	printf("1.Each Player has 4 pieces.\n");
	printf("2. There might be 2 , 3, or 4 players (Red (R) ,Yellow (Y), Green (G) and Blue (B))\n");
	printf("3. At each round the respective player rolls the single dice (1 6).\n");
	printf("4. The player whose all 4 pieces reach at final destination becomes the WINNER.\n");
	printf("---------------------------------------------------------------------------\n");
	printf("<How to Play?>\n");
	printf("#When the player dice 6 you should chose row and column number to select a piece, such as (row)5   (column)2\n");
	printf("#If you want to move along row,you should enter positive number to move left or negative number to move right.");
	printf("OR\n If you want to move along column you should enter positive number to move down and negative number to move up.\n");
	for(i=0;i<10;i++){
		for(j=0;j<10;j++){
			printf("%2c",board[i][j]);
		}
		printf("\n");
	}
	printf("PRESS ANY KEY");
	getch();
	printf("\n\nPlease,enter the number of players:");
	scanf("%d",&numberofplayers);
	if(numberofplayers>4 || numberofplayers<2){
		printf("\nYou have entered an invalid value.\n\n");
		return main();
	}
	while(winner!=1){
		printf("Tour %d\n",tour);
		for(player=1;player<=numberofplayers;player++){
			if(player==1){
				int sayac=0;
				i=0;
				while(dizi[i]!='\0'){
					if(dizi[i]=='Y'){
					sayac=1;
					}
				i++;
				}
				printf("(Yellow)P%d's turn.\n",player);
				srand(time(NULL));
				//scanf("%d",&dice);
				dice=dicefunc(player);
				printf("Dice:%d\n",dice);
				if(dice==6){
					if(sayac==0){
						printf("You must place your piece into the raceway.\n");
						scanf("%d%d",&a,&b);
						if(board[a][b]!='Y'){
							printf("You've tried to place empty nest.TRY AGAIN!!");
							scanf("%d%d",&a,&b);
						}
						if(board[4][1]=='Y'){
							printf("Since this place isn't empty,you cannot play.\n");
						}
						else{
							if(board[4][1]=='R'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='R';	
								}
							if(board[4][1]=='B'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='B';	
								}
							if(board[4][1]=='G'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='G';	
								}
							board[a][b]='0';
							board[4][1]='Y';
							dizi[0]='Y';
						}
					}
					else{
						printf("Either you can place your piece(for press space), or you can play your piece which is on the raceway.\n");
						press=getch();
						if(press==32){
							printf("You must place your piece into the raceway.\n");
							scanf("%d%d",&a,&b);
							if(board[a][b]!='Y'){
							printf("You've tried to place empty nest.TRY AGAIN!!");
							scanf("%d%d",&a,&b);
							}
							if(board[4][1]=='Y'){
								printf("Since this place isn't empty,you cannot play.\n");
							}
							else{
								if(board[4][1]=='R'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}	
										j++;
									}
									board[i-1][j-1]='R';	
								}
								if(board[4][1]=='B'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='B';	
								}
								if(board[4][1]=='G'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count=0 && j<3){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='G';	
								}
							board[a][b]='0';
							board[4][1]='Y';
							dizi[0]='Y';
							}
						}
						else{
							printf("Enter the coordinates and choose the first,second,third or forth piece.\n");
							printf("Horizontal axis:");
							scanf("%d",&a);
							printf("Vertical axis:");
							scanf("%d",&b);
							printf("Which piece?");
							scanf("%d",&u);
							user=movement(move,dice);
							dizi[Y[u-1]]='P';
							Y[u-1]=(Y[u-1]+dice)%32;
							if(dizi[Y[u-1]]=='Y'){
								printf("Since this place isn't empty,you cannot play.\n");
							}	
							else{
								if(board[a][b]!='Y' || user!=dice){
									printf("You've chosen a wrong piece or you placed your piece in a wrong square.TRY AGAIN!!!");
									printf("Horizontal axis:");
									scanf("%d",&a);
									printf("Vertical axis:");
									scanf("%d",&b);
									movement(move,dice);
								}
								if(dizi[Y[u-1]]=='R'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}	
										j++;
									}
									board[i-1][j-1]='R';	
								}
								if(dizi[Y[u-1]]=='B'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}	
										j++;
									}
									board[i-1][j-1]='B';	
								}
								if(dizi[Y[u-1]]=='G'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='G';	
								}
							if(board[a+move[1]][b+move[0]]=='Y' || board[a+move[1]][b+move[0]]==NULL){
								printf("You have to wait until next tour.\n");
							}
							else if((a+move[1])==5 && board[a+move[1]][b+move[0]]=='0'){
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='Y';
							}
							else{
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='Y';
								dizi[Y[u-1]]='Y';	
							}
							}
							printf("Y%d=%d,%d\n",u,a,b);
							}
						}
					}
				else if(sayac==1){
					printf("Enter the coordinates and choose the first,second,third or forth piece.\n");
					printf("Horizontal axis:");
					scanf("%d",&a);
					printf("Vertical axis:");
					scanf("%d",&b);
					printf("Which piece?");
					scanf("%d",&u);
					user=movement(move,dice);
					dizi[Y[u-1]]='P';
					Y[u-1]=(Y[u-1]+dice)%32;
					if(dizi[Y[u-1]]=='Y'){
								printf("Since this place isn't empty,you cannot play.");
					}
					else{
						if(board[a][b]!='Y' || user!=dice){
							printf("You've chosen a wrong piece or you placed your piece in a wrong square.TRY AGAIN!!!");
							printf("Horizontal axis:");
							scanf("%d",&a);
							printf("Vertical axis:");
							scanf("%d",&b);
							movement(move,dice);
							}
							if(dizi[Y[u-1]]=='R'){
								printf("Other player must return to his/her piece's nest.\n");
								j=8;
								count=0;
								while(count==0 && j<10){
									i=1;
									while(count==0 && i<3){
										if(board[i][j]=='0'){
											count=1;
										}
										i++;
									}
									j++;	
								}
								board[i-1][j-1]='R';	
							}
							if(dizi[Y[u-1]]=='B'){
								printf("Other player must return to his/her piece's nest.\n");
								j=8;
								count=0;
								while(count==0 && j<10){
									i=8;
									while(count==0 && i<10){
										if(board[i][j]=='0'){
												count=1;
											}
										i++;
									}
									j++;	
								}
								board[i-1][j-1]='B';
							}
							if(dizi[Y[u-1]]=='G'){
								printf("Other player must return to his/her piece's nest.\n");
								j=1;
								count=0;
								while(count==0 && j<3){
									i=8;
									while(count==0 && i<10){
										if(board[i][j]=='0'){
											count=1;
										}
										i++;
									}
									j++;	
								}
								board[i-1][j-1]='G';	
							}
							if(board[a+move[1]][b+move[0]]=='Y' || board[a+move[1]][b+move[0]]==NULL){
								printf("You have to wait until next tour.\n");
							}
							else if((a+move[1])==5 && board[a+move[1]][b+move[0]]=='0'){
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='Y';
							}
							else{
								dizi[Y[u-1]]='Y';
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='Y';
							}
							printf("Y%d=%d,%d\n",u,a,b);
						}	
					}
				else{
					printf("You have to wait until next tour.\n");
				}
			if(board[5][1]=='Y' && board[5][2]=='Y' && board[5][3]=='Y' && board[5][4]=='Y'){
				winner=1;
				printf("Yellow won.");
			}
			for(i=0;i<10;i++){
				for(j=0;j<10;j++){
					printf("%2c",board[i][j]);
				}
			printf("\n");
				}	
			}
			else if(player==2){
				int sayac=0;
				i=0;
				while(dizi[i]!='\0'){
					if(dizi[i]=='R'){
					sayac=1;
					}
				i++;
				}
				printf("(Red)P%d's turn.\n",player);
				srand(time(NULL));
				//scanf("%d",&dice);
				dice=dicefunc(player);
				printf("Dice:%d\n",dice);
				if(dice==6){
					if(sayac==0){
						printf("You must place your piece into the raceway.\n");
						scanf("%d%d",&a,&b);
						if(board[a][b]!='R'){
							printf("You've tried to place empty nest.TRY AGAIN!!");
							scanf("%d%d",&a,&b);
						}
						if(board[1][6]=='R'){
							printf("Since this place isn't empty, you cannot play");
						}
						else{
							if(board[1][6]=='Y'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
								board[i-1][j-1]='Y';	
								}
							if(board[1][6]=='B'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='B';	
								}
							if(board[1][6]=='G'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='G';
								}
							board[a][b]='0';
							board[1][6]='R';
							dizi[8]='R';
						}
					}
					else{
						printf("Either you can place your piece(for press space), or you can play your piece which is on the raceway.\n");
						press=getch();
						if(press==32){
							printf("You must place your piece into the raceway.\n");
							scanf("%d%d",&a,&b);
						if(board[a][b]!='R'){
							printf("You've tried to place empty nest.TRY AGAIN!!");
							scanf("%d%d",&a,&b);
						}
						if(board[1][6]=='R'){
							printf("Since this place isn't empty, you cannot play");
						}
						else{
							if(board[1][6]=='Y'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}	
										j++;
									}
								board[i-1][j-1]='Y';	
								}
							if(board[1][6]=='B'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='B';	
								}
							if(board[1][6]=='G'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='G';
								}
							board[a][b]='0';
							board[1][6]='R';
							dizi[8]='R';
						}
					}
						else{
							printf("Enter the coordinates and choose the first,second,third or forth piece.\n");
							printf("Horizontal axis:");
							scanf("%d",&a);
							printf("Vertical axis:");
							scanf("%d",&b);
							printf("Which piece?");
							scanf("%d",&u);
							user=movement(move,dice);
							dizi[R[u-1]]='P';
							R[u-1]=(R[u-1]+dice)%32;
							if(dizi[R[u-1]]=='R'){
								printf("\nSince this place isn't empty,you cannot play.");
							}
							else{
								if(board[a][b]!='R' || user!=dice){
									printf("You've chosen a wrong piece or you placed your piece in a wrong square.TRY AGAIN!!!");
									printf("Horizontal axis:");
									scanf("%d",&a);
									printf("Vertical axis:");
									scanf("%d",&b);
									movement(move,dice);
								}
								
								if(dizi[R[u-1]]=='Y'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
								board[i-1][j-1]='Y';	
								}
								if(dizi[R[u-1]]=='B'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='B';	
								}
								if(dizi[R[u-1]]=='G'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='G';
								}
							if(board[a+move[1]][b+move[0]]=='Y' || board[a+move[1]][b+move[0]]==NULL){
								printf("You have to wait until next tour.\n");
							}
							else if((b+move[0]==5) && board[a+move[1]][b+move[0]]=='0'){
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='R';
							}
							else{
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='R';
								dizi[R[u-1]]='R';
							}	
							}
						}
					}
				}
				else if(sayac==1){
					printf("Enter the coordinates and choose the first,second,third or forth piece.\n");
					printf("Horizontal axis:");
					scanf("%d",&a);
					printf("Vertical axis:");
					scanf("%d",&b);
					printf("Which piece?");
					scanf("%d",&u);
					user=movement(move,dice);
					dizi[R[u-1]]='P';
					R[u-1]=(R[u-1]+dice)%32;
					if(dizi[R[u-1]]=='R'){
						printf("Since this place isn't empty,you cannot play.\n");
					}
					else{
						if(board[a][b]!='R' || user!=dice){
							printf("You've chosen a wrong piece or you placed your piece in a wrong square.TRY AGAIN!!!");
							printf("Horizontal axis:");
							scanf("%d",&a);
							printf("Vertical axis:");
							scanf("%d",&b);
							user=movement(move,dice);
						}
							if(dizi[R[u-1]]=='Y'){
								printf("Other player must return to his/her piece's nest.\n");
								j=1;
								count=0;
								while(count==0 && j<3){
									i=1;
									while(count==0 && i<3){
										if(board[i][j]=='0'){
											count=1;
										}
										i++;
									}
									j++;	
								}
							board[i-1][j-1]='Y';	
							}
							if(dizi[R[u-1]]=='B'){
								printf("Other player must return to his/her piece's nest.\n");
								j=8;
								count=0;
								while(count==0 && j<10){
									i=8;
									while(count==0 && i<10){
										if(board[i][j]=='0'){
												count=1;
										}
										i++;
									}
									j++;	
								}
								board[i-1][j-1]='B';	
								}
							if(dizi[R[u-1]]=='G'){
								printf("Other player must return to his/her piece's nest.\n");
								j=1;
								count=0;
								while(count==0 && j<3){
									i=8;
									while(count==0 && i<10){
										if(board[i][j]=='0'){
											count=1;
										}
										i++;
									}
									j++;	
									}
								board[i-1][j-1]='G';	
								}
						if(board[a+move[1]][b+move[0]]=='Y' || board[a+move[1]][b+move[0]]==NULL){
								printf("You have to wait until next tour.\n");
							}
						else if((b+move[0]==5) && board[a+move[1]][b+move[0]]=='0'){
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='R';
							}
						else{
							board[a][b]='P';
							board[a+move[1]][b+move[0]]='R';
							dizi[R[u-1]]='R';
						}		
				}
				printf("R%d=%d,%d\n",u,a,b);
				}
				else{
					printf("You have to wait until next tour.\n");
				}
			if(board[1][2]=='R' && board[2][5]=='R' && board[3][5]=='R' && board[4][5]=='R'){
				winner=1;
				printf("Red won.");
			}
			for(i=0;i<10;i++){
				for(j=0;j<10;j++){
					printf("%2c",board[i][j]);
				}
			printf("\n");
				}	
			}	
			else if(player==3){
				int sayac=0;
				i=0;
				while(dizi[i]!='\0'){
					if(dizi[i]=='B'){
					sayac=1;
					}
				i++;
				}
				printf("(Blue)P%d's turn\n",player);
				//scanf("%d",&dice);
				dice=dicefunc(player);
				printf("Dice:%d\n",dice);
				if(dice==6){
					if(sayac==0){
						printf("You must place your piece into the raceway.\n");
						scanf("%d%d",&a,&b);
						if(board[a][b]!='B'){
							printf("You've tried to place empty nest.TRY AGAIN!!");
							scanf("%d%d",&a,&b);
						}
						if(board[6][9]=='B'){
							printf("Since this place isn't empty,you cannot play.\n");
						}
						else{
							if(board[6][9]=='R'){
									printf("Other player must return to his/her piece's nest.\n");
									i=1;
									count=0;
									while(count==0 && i<3){
										j=8;
										while(count==0 && j<10){
											if(board[i][j]=='0'){
												count=1;
											}
											j++;
										}
										i++;
									}	
									board[i-1][j-1]='R';	
								}
							if(board[6][9]=='Y'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='Y';	
								}
							if(board[6][9]=='G'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='G';	
								}
							board[a][b]='0';
							board[6][9]='B';
							dizi[16]='B';
						}
					}
					else{
						printf("Either you can place your piece(for press space), or you can play your piece which is on the raceway.\n");
						press=getch();
						if(press==32){
							printf("You must place your piece into the raceway.\n");
							scanf("%d%d",&a,&b);
							if(board[a][b]!='B'){
								printf("You must place your piece into the raceway.\n");
								printf("You've tried to place empty nest.TRY AGAIN!!");
								scanf("%d%d",&a,&b);
							}
							if(board[6][9]=='B'){
							printf("Since this place isn't empty,you cannot play.\n");
							}
							else{
							if(board[6][9]=='R'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='R';	
								}
							if(board[6][9]=='Y'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}	
									}
									board[i-1][j-1]='Y';	
								}
							if(board[6][9]=='G'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='G';	
								}
							board[a][b]='0';
							board[6][9]='B';
							dizi[16]='B';
						}
						}
						else{
							printf("Enter the coordinates and choose the first,second,third or forth piece.\n");
							printf("Horizontal axis:");
							scanf("%d",&a);
							printf("Vertical axis:");
							scanf("%d",&b);
							printf("Which piece?");
							scanf("%d",&u);
							user=movement(move,dice);
							dizi[B[u-1]]='P';
							B[u-1]=(B[u-1]+dice)%32;
							if(dizi[B[u-1]]=='B'){
								printf("Since this place isn't empty,you cannot play.\n");
							}
							else{
								if(board[a][b]!='B' || user!=dice){
									printf("You've chosen a wrong piece or you placed your piece in a wrong square.TRY AGAIN!!!");
									printf("Horizontal axis:");
									scanf("%d",&a);
									printf("Vertical axis:");
									scanf("%d",&b);
									movement(move,dice);
								}
								if(dizi[B[u-1]]=='Y'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}	
										j++;
									}
									board[i-1][j-1]='Y';	
								}
								if(dizi[B[u-1]]=='R'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='R';	
								}
								if(dizi[B[u-1]]=='G'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='G';	
								}
							if(board[a+move[1]][b+move[0]]=='B' || board[a+move[1]][b+move[0]]==NULL){
								printf("You have to wait until next tour.\n");
							}
							else if((a+move[1]==5) && board[a+move[1]][b+move[0]]=='0'){
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='B';
							}
							else{
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='B';
								dizi[B[u-1]]='B';
							}
							printf("B%d= %d,%d\n",u,a,b);		
							}
						}
					}
				}
				else if(sayac==1){
					printf("Enter the coordinates and choose the first,second,third or forth piece.\n");
					printf("Horizontal axis:");
					scanf("%d",&a);
					printf("Vertical axis:");
					scanf("%d",&b);
					printf("Which piece?");
					scanf("%d",&u);
					user=movement(move,dice);
					dizi[B[u-1]]='P';
					B[u-1]=(B[u-1]+dice)%32;
					if(dizi[B[u-1]]=='B'){
						printf("Since this place isn't empty,you cannot play.The piece should return to its nest.");
					}
					else{
						if(board[a][b]!='B' || user!=dice){
							printf("You've chosen a wrong piece or you placed your piece in a wrong square.TRY AGAIN!!!");
							printf("Horizontal axis:");
							scanf("%d",&a);
							printf("Vertical axis:");
							scanf("%d",&b);
							user=movement(move,dice);
						}
						if(dizi[B[u-1]]=='Y'){
							printf("Other player must return to his/her piece's nest.\n");
							j=1;
							count=0;
							while(count==0 && j<3){
								i=1;
								while(count==0 && i<3){
									if(board[i][j]=='0'){
										count=1;
									}
									i++;
								}
								j++;	
							}
							board[i-1][j-1]='Y';	
						}
						if(dizi[B[u-1]]=='R'){
							printf("Other player must return to his/her piece's nest.\n");
							j=8;
							count=0;
							while(count==0 && j<10){
								i=1;
								while(count==0 && i<3){
									if(board[i][j]=='0'){
												count=1;
											}
									i++;
								}
								j++;	
							}
							board[i-1][j-1]='R';
						}
						if(dizi[B[u-1]]=='G'){
							printf("Other player must return to his/her piece's nest.\n");
							j=1;
							count=0;
							while(count==0 && j<3){
								i=8;
								while(count==0 && i<10){
									if(board[i][j]=='0'){
										count=1;
									}
									i++;
								}
								j++;	
							}
							board[i-1][j-1]='G';	
						}
						if(board[a+move[1]][b+move[0]]=='B' || board[a+move[1]][b+move[0]]==NULL){
								printf("You have to wait until next tour.\n");
							}
						else if((a+move[1]==5) && board[a+move[1]][b+move[0]]=='0'){
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='B';
							}
						else{
							board[a][b]='P';
							board[a+move[1]][b+move[0]]='B';
							dizi[B[u-1]]='B';
						}	
						printf("B%d= %d,%d\n",u,a,b);	
					}
				}
				else{
					printf("You have to wait until next tour.\n");
				}
			if(board[5][6]=='B' && board[5][7]=='B' && board[5][8]=='B' && board[5][9]=='B'){
				winner=1;
				printf("Blue won."); 
			}
			for(i=0;i<10;i++){
				for(j=0;j<10;j++){
					printf("%2c",board[i][j]);
				}
			printf("\n");
				}	
			}	
			else{
				int sayac=0;
				i=0;
				while(dizi[i]!='\0'){
					if(dizi[i]=='G'){
					sayac=1;
					}
				i++;
				}
				dice=dicefunc(player);
				printf("(Green)P%d's turn\n",player);
				scanf("%d",&dice);
				printf("Dice:%d\n",dice);
				if(dice==6){
					if(sayac==0){
						printf("You must place your piece into the raceway.\n");
						scanf("%d%d",&a,&b);
						if(board[a][b]!='G'){
							printf("You've tried to place empty nest.TRY AGAIN!!");
							scanf("%d%d",&a,&b);
						}
						if(board[9][4]=='G'){
							printf("Since this place isn't empty,you cannot play.\n");
						}
						else{
							if(board[9][4]=='R'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='R';	
								}
							if(board[9][4]=='Y'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='Y';	
								}
							if(board[9][4]=='B'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='B';	
								}
							board[a][b]='0';
							board[9][4]='G';
							dizi[24]='G';
						}
					}
					else{
						printf("Either you can place your piece(for press space), or you can play your piece which is on the raceway.\n");
						press=getch();
						if(press==32){
							printf("You must place your piece into the raceway.\n");
							scanf("%d%d",&a,&b);
							if(board[a][b]!='G'){
								printf("You've tried to place empty nest.TRY AGAIN!!");
								scanf("%d%d",&a,&b);
							}
							if(board[9][4]=='G'){
							printf("Since this place isn't empty,you cannot play.\n");
						}
						else{
							if(board[9][4]=='R'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='R';	
								}
							if(board[9][4]=='Y'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='Y';	
								}
							if(board[9][4]=='B'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='B';	
								}
							board[a][b]='0';
							board[9][4]='G';
							dizi[24]='G';
						}
						}
						else{
							printf("Enter the coordinates and choose the first,second,third or forth piece.\n");
							printf("Horizontal axis:");
							scanf("%d",&a);
							printf("Vertical axis:");
							scanf("%d",&b);
							printf("Which piece?");
							scanf("%d",&u);
							user=movement(move,dice);
							dizi[G[u-1]]='P';
							G[u-1]=(G[u-1]+dice)%32;
							if(dizi[G[u-1]]=='G'){
								printf("Since this place isn't empty,you cannot play.");
							}
							else{
								if(board[a][b]!='G' || user!=dice){
									printf("You've chosen a wrong piece or you placed your piece in a wrong square.TRY AGAIN!!!");
									printf("Horizontal axis:");
									scanf("%d",&a);
									printf("Vertical axis:");
									scanf("%d",&b);
									movement(move,dice);
								}
								if(dizi[G[u-1]]=='Y'){
									printf("Other player must return to his/her piece's nest.\n");
									j=1;
									count=0;
									while(count==0 && j<3){
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}	
										j++;
									}
									board[i-1][j-1]='Y';	
								}
								if(dizi[G[u-1]]=='R'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=1;
										while(count==0 && i<3){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}
										j++;	
									}
									board[i-1][j-1]='R';
								}
								if(dizi[G[u-1]]=='B'){
									printf("Other player must return to his/her piece's nest.\n");
									j=8;
									count=0;
									while(count==0 && j<10){
										i=8;
										while(count==0 && i<10){
											if(board[i][j]=='0'){
												count=1;
											}
											i++;
										}	
										j++;
									}
									board[i-1][j-1]='B';	
								}
							if(board[a+move[1]][b+move[0]]=='G' || board[a+move[1]][b+move[0]]==NULL){
								printf("You have to wait until next tour.\n");
							}
							else if((b+move[0]==5) && board[a+move[1]][b+move[0]]=='0'){
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='G';
							}
							else{
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='G';
								dizi[G[u-1]]='G';
							}
							printf("G%d=%d,%d\n",u,a,b);			
							}
						}
					}
				}
				else if(sayac==1){
					printf("Enter the coordinates and choose the first,second,third or forth piece.\n");
					printf("Horizontal axis:");
					scanf("%d",&a);
					printf("Vertical axis:");
					scanf("%d",&b);
					printf("Which piece?");
					scanf("%d",&u);
					user=movement(move,dice);
					dizi[G[u-1]]='P';
					G[u-1]=(G[u-1]+dice)%32;
					if(dizi[G[u-1]]=='G'){
								printf("Since this place isn't empty,you cannot play.The piece should return to its nest.");
					}
					else{
						if(board[a][b]!='G' || user!=dice){
							printf("You've chosen a wrong piece or you placed your piece in a wrong square.TRY AGAIN!!!");
							printf("Horizontal axis:");
							scanf("%d",&a);
							printf("Vertical axis:");
							scanf("%d",&b);
							movement(move,dice);
						}
						if(dizi[G[u-1]]=='Y'){
							printf("Other player must return to his/her piece's nest.\n");
							j=1;
							count=0;
							while(count==0 && j<3){
								i=1;
								while(count==0 && i<3){
									if(board[i][j]=='0'){
											count=1;
										}
									i++;
								}
								j++;	
							}
							board[i-1][j-1]='Y';	
							}
						if(dizi[G[u-1]]=='R'){
							printf("Other player must return to his/her piece's nest.\n");
							j=8;
							count=0;
							while(count==0 && j<10){
								i=1;
								while(count==0 && i<3){
									if(board[i][j]=='0'){
										count=1;
									}
									i++;
								}	
								j++;
							}
							board[i-1][j-1]='R';	
							}
						if(dizi[G[u-1]]=='B'){
							printf("Other player must return to his/her piece's nest.\n");
							j=8;
							count=0;
							while(count==0 && j<10){
								i=8;
								while(count==0 && i<10){
									if(board[i][j]=='0'){
										count=1;
									}
									i++;
								}	
								j++;
							}
							board[i-1][j-1]='B';	
							}
						if(board[a+move[1]][b+move[0]]=='G' || board[a+move[1]][b+move[0]]==NULL){
								printf("You have to wait until next tour.\n");
							}
						else if((b+move[0]==5) && board[a+move[1]][b+move[0]]=='0'){
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='G';
							}
						else{
								board[a][b]='P';
								board[a+move[1]][b+move[0]]='G';
								dizi[G[u-1]]='G';
							}
							printf("G%d=%d,%d\n",u,a,b);			
					}
				}
				else{
					printf("You have to wait until next tour.\n");
				}
			if(board[6][5]=='G' && board[7][5]=='G' && board[8][5]=='G' && board[9][5]=='G'){
				winner=1;
				printf("Green won.");
			}
			for(i=0;i<10;i++){
				for(j=0;j<10;j++){
					printf("%2c",board[i][j]);
				}
			printf("\n");
				}	
			}		
			}
		tour++;	
	}
	
	return 0;
}
