#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef char* String;

typedef struct namenode{
	String filename;
	struct namenode *next;
}FNODE;

typedef struct tnode{
	struct tnode *left;
	struct tnode *right;
	String word;
	struct namenode *head;
}TNODE;

TNODE* minvalueNode(TNODE *root);
FNODE* filename_search(FNODE *iter,String filename);
TNODE* search(TNODE *root,String item);
TNODE* split_data(TNODE *root,char buff[255],char *delim);
TNODE* insert(TNODE *root,String item,String filename);
TNODE* addFile(TNODE *root,char *buff,char *delim,char filename[20]);
TNODE *newtNode(String item,String filename);
TNODE* delete_file(TNODE* root,String filename,String buff,String delim);
TNODE* delete_node(TNODE *root,String item);
void dosya_listele(FNODE *iter);
void inOrder(TNODE *root);
void deallocate_tree(TNODE *root);
int main(){
	int i;
	TNODE* root = NULL,*tmp;
	char delim[2] = ":";
	String buff;
	String filename;
	int menu;
	char keyword[30];
	do{
		printf("0)Exit the program\n1)Add file\n2)Print all the words and their files at in order form\n3)Search word\n4)Delete File\nInput:");
		scanf("%d",&menu);
		switch(menu){
		case 0:
			printf("You exit the program.\n");
			deallocate_tree(root);
			printf("The memory which was reserved for the program was deallocated.");
			break;
		case 1:
			printf("Add File\n-----------------------\n");
			filename = calloc(50,sizeof(char));
			printf("Enter the name of the file you want to add(ex: a.txt):");
			scanf("%s",filename);
			buff = (char*)malloc(sizeof(char)*255);
			root = addFile(root,buff,delim,filename);
			break;
		case 2:
			printf("In order form of tree:\n");
			inOrder(root);
			printf("\n-------------------------------------------------------\n");
			break;
		case 3:
			printf("Search word:\n");
			printf("Enter the word that you want to search:");
			scanf("%s",keyword);
			tmp = search(root,keyword);
			if(tmp == NULL){
				printf("The word you searched was not founded.\n");
			}
			else{
				printf("The word is \"%s\" and files that contains \"%s\": ",tmp->word,tmp->word);
				dosya_listele(tmp->head);
			}
			break;
		case 4:
			printf("Delete file:\n");
			filename = calloc(50,sizeof(char));
			printf("Enter the name of the file you want to delete(ex: a.txt):");
			scanf("%s",filename);
			buff = calloc(255,sizeof(char));
			root = delete_file(root,filename,buff,delim);
			break;
		default:
			printf("You entered wrong number.\n");
			break;
		}
	}while(menu != 0);
	
	return 0;
}

void deallocate_tree(TNODE *root){
	if(root!=NULL){
		deallocate_tree(root->left);
		deallocate_tree(root->right);
		free(root);
	}
}

TNODE* delete_file(TNODE* root,String filename,String buff,String delim){
	FILE *fptr;
	if((fptr = fopen(filename,"r")) == NULL){
		printf("Error !!! Cannot open file.");
		exit(1);
	}
	char *token;
	char delim2[2] = " ";
	TNODE* iter;
	FNODE *head,*tmp;
	fgets(buff,255,fptr);
	token = strtok(buff,delim);
	//printf("%s\n",token);
	if(strcmp(token,filename) == 0){
		while(token != NULL){
			token = strtok(NULL,delim2);
			//printf("%s\n",token);
			if(token != NULL){
				iter = search(root,token);
				//printf("\n%s\n",iter->word);
				head = filename_search(iter->head,filename);
				//printf("%s\n",head->filename);
				if(strcmp(iter->head->filename,filename) == 0){
					tmp = iter->head;
					iter->head = iter->head->next;
					free(tmp);
				}
				else if(strcmp(head->next->filename,filename) == 0){
					tmp =  head->next;
					head->next = tmp->next;
					free(tmp);
				}
				if(iter->head == NULL){
					root = delete_node(root,token);
				} 
			}
		}
	}
	fclose(fptr);
	return root;
}

TNODE* delete_node(TNODE *root,String item){
	if(root == NULL)
		return root;
	if(strcmp(root->word,item) < 0){
		root->right = delete_node(root->right,item);
	}
	else if(strcmp(root->word,item) > 0){
		root->left = delete_node(root->left,item);
	}
	else{
		if(root->left == NULL){
			TNODE *temp;
			temp = root->right;
			free(root);
			return temp;
		}
		else if(root->right == NULL){
			TNODE *temp;
			temp = root->left;
			free(root);
			return temp;
		}
		else{
			TNODE *temp = minvalueNode(root->right);
			root->word = temp->word;
			root->head = temp->head;
			root->right = delete_node(root->right,temp->word);	
		}
	}
	return root;
}

TNODE* minvalueNode(TNODE *root){
	if(root->left != NULL){
		minvalueNode(root->left);
	}
	else{
		return root;
	}
}

void inOrder(TNODE *root){
	if(root!=NULL){
		inOrder(root->left);
		printf("%s :",root->word);
		dosya_listele(root->head);
		inOrder(root->right);
	}
}

void dosya_listele(FNODE *iter){
	while(iter != NULL){
			printf("\n---->%s\n",iter->filename);
			iter = iter->next;
	}	
}

TNODE* search(TNODE *root,String item){
	if(root == NULL){
		return root;
	}
	if(strcmp(root->word,item) < 0){
		root = search(root->right,item);
	}
	else if(strcmp(root->word,item) > 0){
		root = search(root->left,item);
	}
	else{
		return root;
	}
}


TNODE* split_data(TNODE *root,String buff,String delim){
	String token,tmp;
	char delim2[2] = " \n";
	TNODE* iter;
	FNODE *head;
	token = strtok(buff,delim);
	tmp = token;
	//printf("%s\n",tmp);
	while(token != NULL){
		//printf("1");
		token = strtok(NULL,delim2);
		//printf("%s",token);
		if(token!=NULL){
			iter = root;
			iter = search(root,token);
		}
		//printf("2");
		if(iter == NULL && token!=NULL){
			root = insert(root,token,tmp);
			//printf("3");
			//inOrder(root);
			//printf("\n");
		}
		else if(token!=NULL){
			head = filename_search(iter->head,tmp);
			//printf("Girdi");
			if(head->next == NULL){
				head->next = (FNODE*)malloc(sizeof(FNODE));
				head->next->filename = (char*)malloc(sizeof(char)*20);
				strcpy(head->next->filename,tmp);
				head->next->next = NULL;
			}
		}
	}
	return root;
}

TNODE* addFile(TNODE *root,String buff,String delim,String filename){
	FILE *fptr;
	if((fptr = fopen(filename,"r")) == NULL){
		printf("Error !! Opening file");
		exit(1);
	}
	while(!feof(fptr)){
		while(fgets(buff,255,fptr) != NULL){
			root = split_data(root,buff,delim);
			//printf("Girdi");
		}
	}
	printf("%s was added.\n",filename);
	fclose(fptr);
	return root;
}

FNODE* filename_search(FNODE *iter,String filename){
	while(iter->next != NULL && strcmp(iter->next->filename,filename) != 0){
		//printf("%s",iter->filename);
		iter = iter->next;
	}
	return iter;
}

TNODE* insert(TNODE *root,String item,String filename){
	if(root == NULL){
		return newtNode(item,filename);
	}
	if(strcmp(root->word,item) > 0){
		root->left = insert(root->left,item,filename);
	}
	else if(strcmp(root->word,item) < 0){
		//printf(":%s:",root->word);
		root->right = insert(root->right,item,filename);
	}
	return root;
}

TNODE *newtNode(String item,String filename){
	TNODE *newtnode = (TNODE*)malloc(sizeof(TNODE));
	newtnode->word = (char*)malloc(sizeof(char)*20);
	strcpy(newtnode->word,item);
	FNODE *newfnode =(FNODE*)malloc(sizeof(FNODE));
	newfnode->filename = (char*)malloc(sizeof(char)*20);
	strcpy(newfnode->filename,filename);
	newfnode->next = NULL;
	newtnode->head = newfnode;
	newtnode->left = newtnode->right = NULL; 
	return newtnode;
}
