#include<stdio.h>
#include<stdlib.h>

typedef struct Adjnode{
	int dest;
	struct Adjnode *next;
}NODE;

typedef struct Graph{
	int V;
	struct Adjlist *array;
}GRAPH;

typedef struct Adjlist{
	struct Adjnode *head;
}LIST;

typedef struct queue{
	int front,rear,size;
	int *queue;	
}Q;

GRAPH* creategraph(int V);
NODE* createnode(int dest);
void addEdge(GRAPH *iter,int src,int dest);
void printAdjList(GRAPH *graph);
void enqueue(Q *q,int value);
int dequeue(Q *q);
void initialise(Q* q);
int isFull(Q *q);
int isEmpty(Q *q);
void printqueue(Q *q);
void BFS(GRAPH *graph,Q *q,int src);
void DFS(int v,GRAPH *graph,int*);
int main(){
	int V;
	int *visited;
	printf("Number of vertices:");
	scanf("%d",&V);
	Q *q = (Q*)malloc(sizeof(Q));
	initialise(q);
	visited = (int*)calloc(V,sizeof(int));
	GRAPH *graph = creategraph(V);
	addEdge(graph,0,1);
	addEdge(graph,0,2);
	addEdge(graph,0,3);
	addEdge(graph,2,4);
	printAdjList(graph);
	BFS(graph,q,0);
	/*enqueue(q,2);
	enqueue(q,3);
	enqueue(q,1);
	enqueue(q,5);
	enqueue(q,51);
	printqueue(q);
	printf(" %d\n",dequeue(q));
	enqueue(q,12);
	printqueue(q);
	printf(" %d\n",dequeue(q));
	printf(" %d\n",dequeue(q));
	printqueue(q);
	printf(" %d\n",dequeue(q));
	printf(" %d\n",dequeue(q));
	enqueue(q,100);
	enqueue(q,200);
	printf(" %d\n",dequeue(q));*/
	printf("\n");
	DFS(0,graph,visited);
	free(q);
	free(graph);
	
	//printAdjList(graph);
	return 0;
}

void DFS(int v,GRAPH *graph,int *visited){
	NODE *iter;
	printf("%d\t",v);
	visited[v] =  1;
	iter = graph->array[v].head;
	while(iter!=NULL){
		v = iter->dest;
		if(visited[v] == 0){
			DFS(iter->dest,graph,visited);
		}
		iter = iter->next;
	}
}

void BFS(GRAPH *graph,Q *q,int src){
	int *visited,v,i;
	visited = (int*)calloc(graph->V,sizeof(int));
	NODE *iter;
	enqueue(q,src);
	visited[src] = 1;
	printf("\nBFS of graph:\n");
	while(!isEmpty(q)){
		//printf("Girdi");
		v = dequeue(q);
		printf("%d\t",v);
		iter = graph->array[v].head;
		while(iter!=NULL){
			if(visited[iter->dest] == 0){
				enqueue(q,iter->dest);
				visited[iter->dest] = 1;
			}
			//printf("ITER%d",iter->dest);
			iter = iter->next;
		}
	}
	printf("\n");
	for(i=0;i<graph->V;i++){
		printf("%d",i);
	}
	printf("\n");
	for(i=0;i<graph->V;i++){
		printf("%d",visited[i]);
	}
}

void printqueue(Q *q){
	int i = q->front;
	do{
		printf("%d\t",q->queue[i]);
		i = (i + 1)%q->size;
	}while(i != q->rear);
	printf("\n");
}

void initialise(Q* q){
	int i;
	q->front = q->rear = 0;
	printf("Size:");
	scanf("%d",&q->size);
	q->queue = (int*)malloc(sizeof(int)*q->size);
	for(i=0;i<q->size;i++){
		q->queue[i] = 0;
	}
}

int isFull(Q *q){
	if(q->rear >= q->front){
		return (q->rear - q->front + 1) <= q->size ? 0 : 1;
	}
	else{
		return (q->front - q->rear - 1) < 0 ? 1 : 0;
	}
}

int isEmpty(Q *q){
	if(q->rear == q->front){
		if(q->queue[q->rear] == 0){
			return 1;
		}
		else{
			return 0;
		}
	}
	else{
		return !(q->rear - q->front);
	}
}

void enqueue(Q *q,int value){
	if(!isFull(q)){
		q->queue[q->rear] = value;
		//printf("\nRear:%d\n",q->rear);
		q->rear = (q->rear + 1) % q->size;
	}
	else{
		printf("\nQueue is full\n");
	}
}

int dequeue(Q *q){
	int x;
	if(isEmpty(q)){
		printf("\nQueue is empty");
		return -1;
	}
	else{
		x = q->queue[q->front];
		//printf("FRONT:%d\n",q->front);
		q->front = (q->front + 1) % q->size;
		return x;
	}
}

NODE* createnode(int dest){
	NODE *newnode = (NODE*)malloc(sizeof(NODE));
	newnode->dest = dest;
	newnode->next = NULL;
	return newnode;
}

GRAPH* creategraph(int V){
	int i;
	GRAPH *newgraph = (GRAPH*)malloc(sizeof(GRAPH));
	newgraph->V = V;
	newgraph->array =(LIST*)malloc(sizeof(LIST)*V);
	
	for(i=0;i<V;i++){
		newgraph->array[i].head = NULL;
	}
	return newgraph;
}

void addEdge(GRAPH *graph,int src,int dest){
	NODE *newnode = createnode(dest);
	NODE *check = NULL;
	NODE *temp;
	
	if(graph->array[src].head == NULL){
		graph->array[src].head = newnode;
	}
	else{
		check = graph->array[src].head;
		while(check->next!=NULL && check->next->dest < dest){
			//printf("CHECK:%d",check->dest);
			check = check->next;
		}
		//printf("Girdi2 %d",check->dest);
		temp = check->next;
		check->next = newnode;
		newnode->next = temp;
	}
	newnode = createnode(src);
	if(graph->array[dest].head == NULL){
		graph->array[dest].head = newnode;
	}
	else{
		check = graph->array[dest].head;
		while(check->next!=NULL && check->next->dest < src){
			check = check->next;
		}
		//printf("Girdi2 %d",check->dest);
		temp = check->next;
		check->next = newnode;
		newnode->next = temp;
	}
	
}

void printAdjList(GRAPH *graph){
	NODE *iter;
	int i;
	for(i=0;i<graph->V;i++){
		iter =graph->array[i].head;
		printf("\nNeighbours of %d. vertice:",i);
		while(iter != NULL){
			printf("--%d--",iter->dest);
			iter = iter->next;
		}
	}
}


