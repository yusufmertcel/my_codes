#include<stdio.h>
#include<stdlib.h>
#include<dos.h>
#include<time.h>


void mazeRead(FILE *fptr,int **,char**,int*,int*,int*,int*);
void printMatris(int **matrisptr,int row,int col);
void printBoard(char **board,int row,int col);
int pathFind(int **matris,char **board,int **pathMatris,int startX,int startY,int endX,int endY,int row,int col,int*);
int** matrisAllocate(int row,int col);
char** boardAllocate(int row,int col);

int main(){
	int **maze,**pathMatris;
	char **board;
	char read;
	int puan = 0;
	int startX,startY,finishX,finishY,row=0,col=0,i;
	FILE *fptr;
	if((fptr = fopen("largemaze.txt","r")) == NULL){
		printf("File cannot opened!!!");
		exit(0);
	}
	srand(time(NULL));
	while((read = fgetc(fptr)) != EOF && read != '\n'){
		if(read == '+' || read == '-'){
			col++;
		}	
	}
	
	while((read = fgetc(fptr)) != EOF){
		if(read == '\n'){
			row++;
		}
	}
	
	printf("Row = %d Col = %d\n",row,col);
	maze = matrisAllocate(row+1,col+1);
	pathMatris = matrisAllocate(row+1,col+1);
	board = boardAllocate(row+1,col+1);
	//printMatris(pathMatris,row,col);
	//printMatris(maze,row,col);
	
	
	mazeRead(fptr,maze,board,&startX,&startY,&finishX,&finishY);
	fclose(fptr);
	//printf("\n%d-%d -- %d-%d\n",startX,startY,finishX,finishY);
	//printf("%d---%d\n",row,col);
	printMatris(maze,row,col);
	
	if(pathFind(maze,board,pathMatris,startX,startY,finishX,finishY,row,col,&puan))
		printf("Path Found (+_+)\n");
	else
		printf("Path not Found (-_-)\n");
	
	printf("All possible roads:\n");
	printMatris(maze,row,col);
	printf("Board Path: Score:%d\n",puan);
	printBoard(board,row+1,col);
	return 0;
}

void printMatris(int **matrisptr,int row,int col){
	int i,j;
	for(i=0;i<row;i++){
		for(j=0;j<col;j++){
			printf("%d",matrisptr[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}

void printBoard(char **board,int row,int col){
	int i,j;
	for(i=0;i<row;i++){
		for(j=0;j<col;j++){
			printf("%c",board[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}

int pathFind(int **matris,char **board,int **pathMatris,int startX,int startY,int endX,int endY,int row,int col,int *score){
	if(startX == endX && startY == endY && matris[startX][startY] == 1){
		pathMatris[startX][startY] = 1;
		return 1;
	}
	
	if(startX > 0 && startY > 0 && startX<row && startY<col && matris[startX][startY] == 1){	

		if(pathMatris[startX][startY] == 1){
			return 0;
		}
		
		pathMatris[startX][startY] = 1;
		if(board[startX][startY] == 'O')
			(*score)+=10;
		if(board[startX][startY] != 'b')
			board[startX][startY] = '*';
		printf("Path:\n");
		printMatris(pathMatris,row,col);
		printf("Board: Score=%d\n",*score);
		printBoard(board,row+1,col);
		sleep(1);
		
		if(pathFind(matris,board,pathMatris,startX-1,startY,endX,endY,row,col,score)){
			return 1;
		}
		
		if(pathFind(matris,board,pathMatris,startX,startY-1,endX,endY,row,col,score)){
			return 1;
		}
		
		if(pathFind(matris,board,pathMatris,startX+1,startY,endX,endY,row,col,score)){
			return 1;
		}
		
		if(pathFind(matris,board,pathMatris,startX,startY+1,endX,endY,row,col,score)){
			return 1;
		}
		
	
		
		printf("Path:\n");
		printMatris(pathMatris,row,col);
		printf("Board: Score=%d\n",*score);
		printBoard(board,row+1,col);
		sleep(1);
		
		if(pathMatris[startX+1][startY] == matris[startX+1][startY] && 	
		pathMatris[startX][startY+1] == matris[startX][startY+1] && 
		pathMatris[startX-1][startY] == matris[startX-1][startY] && 
		pathMatris[startX][startY-1] == matris[startX][startY-1]){
			(*score)-=5;
		}
		
		pathMatris[startX][startY] = 0;
		board[startX][startY] = ' ';
		return 0;
	}
	return 0;	
}

int** matrisAllocate(int row,int col){
	int i;
	int **m;
	m = (int**)malloc(sizeof(int*)*row);
	for(i=0;i<row;i++){
		m[i] = (int*)calloc(sizeof(int),col);
	}
	return m;
}

char** boardAllocate(int row,int col){
	int i;
	char **m;
	m = (char**)malloc(sizeof(char*)*row);
	for(i=0;i<row;i++){
		m[i] = (char*)malloc(sizeof(char)*col);
	}
	return m;
}

void mazeRead(FILE *fptr,int **matris,char **board,int *startingIndexX,int *startingIndexY,int *endingIndexX,int* endingIndexY){
	int i,j,random,format;
	char read;
	i=j=0;
	fseek(fptr,0,SEEK_SET);
	do{
	  printf("In order to determine rate of apples enter a number between (1-5): ");
	  scanf("%d",&format);	
	}while(format < 1 || format > 5);
	format = (6-format)*10;
	while((read = fgetc(fptr)) != EOF){
		board[i][j] = read;
		if (read == ' ' || read == 'b' || read == 'c'){
			matris[i][j] = 1;
			if(read == ' '){
				random = rand() % format;
				if(random == 4){
					board[i][j] = 'O';
				}
			}
			if(read == 'b'){
				*startingIndexX = i;
				*startingIndexY = j;
			}
			if(read == 'c'){
				*endingIndexX = i;
				*endingIndexY = j;
			}
		}
		else if(read == '\n'){
			i++;
			j=-1;
		}
		j++;
	}
}


