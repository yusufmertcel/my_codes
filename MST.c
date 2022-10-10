#include<stdio.h>
#include<stdlib.h>

typedef struct Edge
{
	int src,dest,value;
}Edge;

struct Graph{
	int V,E;
	struct Edge* edge;
};

void printMST(struct Graph*,int size);
struct Graph* createGraph(int V,int E);
void find(int parent[],int x,int y ,int size);
void Union(int parent[],int x,int y);
int isCycle(struct Graph* graph);
void newedge(struct Graph* graph,int src,int dest,int value);
void xchg(int *a,int *b);
int main(){
	int E=0,V=0,i,src,dest,value;
	char filename[50];
	int **MST;
	struct Graph* graph;
	FILE *fptr;
	printf("Filename:");
	scanf("%s",filename);
	MST = (int**)malloc(sizeof(int*)*20);
	for(i=0;i<20;i++){
		MST[i] = (int*)malloc(sizeof(int)*3);
	}
	if((fptr=fopen(filename,"r")) == NULL){
		printf("Error opening file!!");
		return 1;
	}
	i=0;
	while(!feof(fptr)){
		fscanf(fptr,"%d %d %d",&MST[i][0],&MST[i][1],&MST[i][2]);
		E++;
		i++;
	}
	E--;
	V = E + 1;
	graph = createGraph(V,E);
	printf("Edges:%d Vertices:%d\n",graph->E,graph->V);
	printf("MST:\n");
	for(i=0;i<graph->E;i++){
		graph->edge[i].value = MST[i][0];
		graph->edge[i].src = MST[i][1];
		graph->edge[i].dest = MST[i][2];
		printf("%d %d %d\n",graph->edge[i].value,graph->edge[i].src,graph->edge[i].dest);
	}
	printf("Source Dest Value\n");
	scanf("%d %d %d",&src,&dest,&value);
	newedge(graph,src,dest,value);
	printf("Updated MST:\n");
	printMST(graph,graph->E);
	fclose(fptr);
	
	for(i=0;i<20;i++){
		free(MST[i]);
	}
	free(MST);
	
	return 0;
}


struct Graph* createGraph(int V,int E){
	struct Graph* graph = (struct Graph*)malloc(sizeof(struct Graph));
	graph->V = V;
	graph->E = E;
	graph->edge = (Edge*)malloc(sizeof(Edge)*graph->E);
	
	return graph;
}

void find(int parent[],int x,int y,int size){
	int i,value=0;
	for(i=1;i<size;i++){
		if(parent[i] >= value)
			value = parent[i]+1;
	}
	if(parent[x] !=0 && parent[y] != 0){
		//parent[x] = parent[y];
		for(i=0;i<size;i++){
			if(parent[i] != 0 && parent[i] == parent[x]){
				parent[i] = parent[y];	
			}       
		}
	}
	else if(parent[x] == 0 && parent[y] == 0){
		parent[x] = parent[y] = value;
	}
	else if(parent[y] == 0){
		parent[y] = parent[x];
	}
	else{
		parent[x] = parent[y];
	}
}

int isCycle(struct Graph* graph){
	int i,x,y;
	int *parent = (int*)malloc(graph->V+1 * sizeof(int));
	for(i=1;i<=graph->V;i++){
		parent[i] = 0;
	}
	
	for(i = 0; i < graph->E; i++){
		x = graph->edge[i].src;
		y = graph->edge[i].dest;
		//printf("X0:%d %d Y0:%d %d\n" ,x,parent[x],y,parent[y]);
		if((parent[x] != 0 && parent[y] != 0) && (parent[x] == parent[y]))
			return 1;
		find(parent,x,y,graph->V);
		//printf("X:%d %d Y:%d %d\n" ,x,parent[x],y,parent[y]);
	}
	return 0;
}

void newedge(struct Graph* graph,int src,int dest,int value){
	int i,enable = 0,maxi;
	for(i=0;i<graph->E;i++){
		if((graph->edge[i].src == src || graph->edge[i].dest == src || graph->edge[i].src == dest || graph->edge[i].dest == dest)){
			if((value < graph->edge[i].value) )
			{
				enable = 1;
				maxi = i;
			}
		}
	}
	if(enable == 1){
		//graph->edge = (Edge*)realloc(graph->edge,graph->E+1*(sizeof(Edge)));
		i= maxi-1;
		//printf("MAX:%d\n",maxi);
		while(graph->edge[i].value > value){
			xchg(&graph->edge[i].src,&graph->edge[maxi].src);
			xchg(&graph->edge[i].dest ,&graph->edge[maxi].dest);
			xchg(&graph->edge[i].value,&graph->edge[maxi].value);
			maxi = i;
			i--;
		}
		graph->edge[graph->E].src = graph->edge[maxi].src;
		graph->edge[graph->E].dest = graph->edge[maxi].dest;
		graph->edge[graph->E].value = graph->edge[maxi].value;
		graph->edge[maxi].src = src;
		graph->edge[maxi].dest = dest;
		graph->edge[maxi].value = value;
		//printMST(graph,graph->E);
		//printf("Cycle:%d\n",isCycle(graph));
		if(isCycle(graph)){
			graph->edge[graph->E-1].src = graph->edge[graph->E].src;
			graph->edge[graph->E-1].dest = graph->edge[graph->E].dest;
			graph->edge[graph->E-1].value = graph->edge[graph->E].value;
		}	
	}
}

void xchg(int *a,int *b){
	int tmp = *a;
	*a = *b;
	*b = tmp;
}

void printMST(struct Graph* G,int size){
	int i;
	for(i=0;i<size;i++){
		printf("%d %d %d\n",G->edge[i].value,G->edge[i].src,G->edge[i].dest);
	}
}
