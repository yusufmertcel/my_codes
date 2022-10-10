#include<stdio.h>
#include<stdlib.h>


void printAdj(int **matrix,int vertices);
void addedge(int **adj,int u,int v);
void DFS(int V,int v,int **adj,int *visited);

int main(){
	int **adj,*visited;
	int numberofvertices,numberofedges,v1,v2,i;
	printf("Enter the number of vertices:");
	scanf("%d",&numberofvertices);
	printf("Enter the number of edges:");
	scanf("%d",&numberofedges);
	adj = (int**)calloc(numberofvertices,sizeof(int*));
	for(i=0;i<numberofvertices;i++){
		adj[i] = (int*)calloc(numberofvertices,sizeof(int));
	}
	visited = (int*)calloc(numberofvertices,sizeof(int));
	for(i=1;i<=numberofedges;i++){
		printf("Enter the two vertices of edges:\n");
		scanf("%d %d",&v1,&v2);
		addedge(adj,v1,v2);
	}
	
	printAdj(adj,numberofvertices);
	DFS(numberofvertices,0,adj,visited);	
	for(i=0;i<numberofvertices;i++){
		free(adj[i]);
	}
	free(adj);
	
	
	
	return 0;
}

void addedge(int **adj,int u,int v){
	adj[u][v] = 1;
	adj[v][u] = 1;
}

int mothervertex(int **adj,int V){
	
}

void DFS(int V,int v,int **adj,int *visited){
	int i = 0;
	printf("%d\t",v);
	visited[v] =  1;
	while(i < V){
		if(adj[row][i]){
			printf("----%d----",v);
			if(visited[v] == 0){
				DFS(V,v,adj,visited);
			}	
		}
		i++;
	}
}

void printAdj(int **matrix,int vertices){
	int i,j;
	for(i=0;i<vertices;i++){
		for(j=0;j<vertices;j++){
			printf("%2d",matrix[i][j]);
		}
		printf("\n");
	}
}
