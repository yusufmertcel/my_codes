#include<stdio.h>
#include<stdlib.h>
#include<time.h>
typedef struct node{
	int value;
	struct node *next[2];
}NODE;

NODE* create_node(int data);
void insert(NODE **head_Ref,int number);
NODE* search(NODE *iter,int x);
NODE* insert_shortcut(NODE *curr_Node,NODE *head,int number);
int random(int arr[],int *size);
void short_link(NODE **head,int n,int *arr);
void bubblesort(int *arr,int size);
void swap(int *xp,int *yp);
void arr_copy(int *arr,NODE *iter);
void print_list2(NODE *head_Ref,int arr[],int );
void levels(NODE **head,int n,int MAX_LEVEL,int *arr);
void search_shortcut(NODE *iter,int search_value,int level);
int add_element(NODE **head_Ref,int add_value,int *MAX_LEVEL,int *arr,int n);
int delete_element(NODE **head_Ref,int delete_value,int *MAX_LEVEL,int n);
int main(){
	NODE *head=NULL;
	int arr[20];
	int value,linked_list_size=0;
	int MAX_LEVEL,end=1,i;
	head=(NODE*)malloc(sizeof(NODE)*1);
	if(head==NULL){
		printf("Error!");
		exit(0);
	}
	srand(time(NULL));
	head->next[0]=NULL;
	while(end){
		printf("Eklemek istediginiz degeri giriniz:");
		scanf("%d",&value);
		insert(&head,value);
		linked_list_size++;
		printf("Eklemeyi sonlandirmak istiyorsaniz 0'a basiniz!");
		scanf("%d",&end);
	}
	printf("Kac seviye olusturmak istiyorsunuz?");
	scanf("%d",&MAX_LEVEL);
	levels(&head,linked_list_size,MAX_LEVEL,arr);
	printf("Olusturulan seviyeler:\n");
	print_list2(head,arr,MAX_LEVEL);
	
	for(i=0;i<4;i++){
	/*	printf("Eklemek istediginiz degeri giriniz:");
		scanf("%d",&value);
		linked_list_size=add_element(&head,value,&MAX_LEVEL,arr,linked_list_size);
		print_list2(head,arr,MAX_LEVEL);
		printf("Aramak istediginiz degeri giriniz:");
		scanf("%d",&value);
		search_shortcut(head,value,MAX_LEVEL);*/
		
		printf("Silmek istediginiz degeri giriniz:");
		scanf("%d",&value);
		linked_list_size=delete_element(&head,value,&MAX_LEVEL,linked_list_size);
		print_list2(head,arr,MAX_LEVEL);
	}

	
	
/*	*/
	
	
	return 0;
}

NODE* create_node(int data){
	NODE *newNode=(NODE*)malloc(sizeof(NODE)*1);
	if(newNode==NULL){
		printf("Error!");
		exit(0);
	}
	newNode->value=data;
	newNode->next[0]=NULL;
	return newNode;
}

void search_shortcut(NODE *iter,int search_value,int level){
	int i=1;
	while(i<=level && search_value!=iter->value){
		while(iter->next[0]!=NULL && search_value >= iter->next[0]->value){
			iter=iter->next[0];
			//printf("%d\n",iter->value);
		}
		if(i!=level)
			iter=iter->next[1];
		i++;
	}
	if(search_value==iter->value){
		printf("%d degeri %d seviyesinde bulunmustur\n",iter->value,level-i+2);
	}
	else{
		printf("Eleman bulunamadi\n");
	}
}

int delete_element(NODE **head_Ref,int delete_value,int *MAX_LEVEL,int n){
	NODE *iter=*head_Ref;
	NODE *tmp;
	int i=1,found=0;
	while(i<=*MAX_LEVEL){
		while(iter->next[0]!=NULL && iter->next[0]->value<delete_value){
			iter=iter->next[0];
		}
		if(iter->next[0]!=NULL && iter->next[0]->value==delete_value){
			tmp=iter->next[0];
			found=1;
			iter->next[0]=iter->next[0]->next[0];
			free(tmp);	
		}
		iter=iter->next[1];
		//printf("\n%d\n",iter->value);
		i++;
	}
	if(!found){
		printf("Eleman bulunamadi.\n");
		return n;
	}
	else
		return --n;
}

int add_element(NODE **head_Ref,int add_value,int *MAX_LEVEL,int *arr,int n){
	NODE *newNode;
	NODE *prev=NULL;
	NODE *iter=*head_Ref;
	int i=1,size,seviye;
	while(i<=*MAX_LEVEL){
		newNode=create_node(add_value);
		while(iter->next[0]!=NULL && iter->next[0]->value<add_value){
			iter=iter->next[0];
		}
		newNode->next[0]=iter->next[0];
		iter->next[0]=newNode;
		if(prev!=NULL){
			prev->next[1]=newNode;
		}
		prev=newNode;
		iter=iter->next[1];
		i++;
	}
	size=++n;
	if(prev->next[0]==NULL){
		seviye=pow(2,(*MAX_LEVEL)-2);
		//(n+(2^(maxlevel-1)-1))/2^(maxlevel-1)
		n=(n+seviye-1)/seviye;
		//printf("N:%d",n);
		//printf("IFCONTROL\n");
		iter=*head_Ref;
		arr_copy(arr,iter->next[0]);
		short_link(head_Ref,n,arr);
		(*MAX_LEVEL)++;
	}
	return size;
}


void levels(NODE **head,int n,int MAX_LEVEL,int *arr){
	int i=1;
	NODE *iter= (*head)->next[0];
	while(i<MAX_LEVEL){
		iter= (*head)->next[0];
		//printf("HEAD:%d",iter->value);
		arr_copy(arr,iter);
		short_link(head,n,arr);
		n=(n+1)/2;
		i++;
	}
}


int random(int arr[],int *size){
	int index=rand()%(*size);
	int i;
	int deger=arr[index];
	for(i=index;i<(*size);i++){
		arr[i]=arr[i+1];
	}
	(*size)--;
	//printf("\n%d\n",deger);
	return deger;
}

void short_link(NODE **head,int n,int *arr){
	NODE *first_node=NULL;
	NODE *tmp=*head;
	NODE *curr_node;
	int *rand,i=0;
	int size=(n+1)/2;
	rand=(int*)malloc(sizeof(int)*size);
	first_node=(NODE*)malloc(sizeof(NODE)*1);
	if(first_node==NULL){
		printf("Error!");
		exit(0);
	}
	first_node->next[0]=NULL;
	first_node->next[1]=*head;
	curr_node=first_node;
	//(n+1)/2
	//bazen randomu yanlýþ buluyo
	while(i<size){
		rand[i]=random(arr,&n);
		i++;
	}
	bubblesort(rand,size);
	i=0;
	while(i<size){
		curr_node=insert_shortcut(curr_node,tmp->next[0],rand[i]);
		i++;
	}
	//printf("Girdi");
	*head=first_node;
}

void bubblesort(int *arr,int size){
	int i,j;
	for(i=0;i<size-1;i++){
		for(j=0;j<size-i-1;j++){
			if(arr[j]>arr[j+1]){
				swap(&arr[j],&arr[j+1]);
			}
		}
	}
}


void swap(int *xp,int *yp){
	int tmp = *xp;
	*xp=*yp;
	*yp=tmp;
}

NODE* insert_shortcut(NODE *curr_Node,NODE *head,int number){
	NODE *newNode=create_node(number);
	if(head->value==number){
		curr_Node->next[0]=newNode;
		newNode->next[1]=head;
		curr_Node=newNode;
		return curr_Node;
	}
	else{
		//printf("Value:%d ",head->value);
		return insert_shortcut(curr_Node,head->next[0],number);
		//printf("Girdi");
	}
}

void arr_copy(int *arr,NODE *iter){
	int i=0;
	while(iter!=NULL){
		arr[i]=iter->value;
		//printf("ARR:%d",arr[i]);
		iter=iter->next[0];
		i++;
	}
}

void print_list(NODE *head_Ref,int arr[]){
	NODE *tmp=head_Ref->next[0];
	int i=0,j=0;
		while(tmp!=NULL){
			printf("%2d",tmp->value);
			tmp=tmp->next[0];
		}
}

void print_list2(NODE *head_Ref,int arr[],int MAX_LEVEL){
	NODE *tmp=head_Ref;
	int i=0;
	while(i<MAX_LEVEL){
		tmp=tmp->next[0];
		while(tmp!=NULL){
			printf("%3d",tmp->value);
			tmp=tmp->next[0];
		}
		head_Ref=head_Ref->next[1];
		tmp=head_Ref;
		printf("\n");
		i++;
	}
	
}

void insert(NODE **head_Ref,int number){
	NODE *tmp=*head_Ref;
	NODE *newNode=create_node(number);
	if(tmp->next[0]==NULL){
		tmp->next[0]=newNode;
	}
	else{
		if(number<tmp->next[0]->value){
			newNode->next[0]=tmp->next[0];
			tmp->next[0]=newNode;
		}
		else{
			tmp=tmp->next[0];
			tmp=search(tmp,number);
			newNode->next[0]=tmp->next[0];
			tmp->next[0]=newNode;
		}
	}
}

NODE* search(NODE *iter,int x){
	NODE *prev=iter;
	while(iter!=NULL && iter->value<x){
		prev=iter;
		iter=iter->next[0];
	}
	return prev;
}
