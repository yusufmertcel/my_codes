#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define SIZE 50

typedef struct dersler{//Dersler dosyasý
	char ders_kodu[SIZE];
	char ders_adi[SIZE];
	short unsigned int kapasite;
	short unsigned int ogrenci_sayisi;
	short unsigned int kredi;
	struct dersler *next;
	char **numara;
}DERS;

typedef struct ogrenci_listesi{//Öðrenciler dosyasý
	char numara[10];
	char isim[SIZE];
	char soyisim[SIZE];
	short unsigned int alinan_ders;
	short unsigned int alinan_kredi;
	int ogrenci_sayisi;
	struct ogrenci_listesi *next;
	struct ogrenci_listesi *prev;
}OGRENCI;

typedef struct ogrenci_kayit{
	int ID;
	char ders_kodu[SIZE];
	char numara[10];
	char tarih[SIZE]; 
	char durum[SIZE];
	struct ogrenci_kayit *next;
}KAYIT;


int ders_kapatma(DERS **head,KAYIT **);
int ders_acma(DERS **head);

//ogrenci ekleme ve silme
int ogrenci_ekle(OGRENCI **head,OGRENCI **);
int ogrenci_sil(OGRENCI **head,OGRENCI **tail,KAYIT **head1_ref,DERS **head2_ref);

//Linked list yazdýrma fonksiyonlarý
void list_ders(DERS **head);
void list_ogrenci(OGRENCI **head,OGRENCI **tail,OGRENCI* (*ascordesc)(OGRENCI **head,OGRENCI **tail));
OGRENCI *ascending(OGRENCI **head,OGRENCI **tail);
OGRENCI *descending(OGRENCI **head,OGRENCI **tail);
void list_kayit(KAYIT **head);

//Arama fonksiyonlarý
OGRENCI* search_ogrenci_no(OGRENCI *head,char no[]);
DERS* search_ders_kod(DERS *,char *no);
KAYIT* search_kayit_no(KAYIT *iter,char *no);
KAYIT* search_kayit_kod(KAYIT *iter,char *kod);
void durum_guncelleme(KAYIT *iter,char *no);

//ders kayit ve silme
int ders_kayit(KAYIT **head,OGRENCI **head1,OGRENCI **,DERS **head2,int* ,int ,int);
int kayit_sil(KAYIT **head_ref,DERS **head1_ref,OGRENCI **head2_ref);

//ders kaydetme dosyadan
void dosyayaKaydet(DERS **head);
void Split_Data(DERS* holder,char buff[255],char delim[2]);
void dersfile_read(DERS **head,char file_name[20],char delim[2]);

//ogrenci kaydetme dosyadan
void Split_Ogrenci(OGRENCI* holder,char buff[100],char delim[2]);
void ogrenciler_read(OGRENCI **head,OGRENCI **tail,char file_name[20],char delim[2]);
void ogrenci_Kaydet(OGRENCI **head);

//dosyadan ve dosyaya kayit kaydetme
void kayitKaydet(KAYIT **head);
void kayitfile_read(KAYIT **head,DERS **,int *,char file_name[20],char delim[2]);
void Split_Kayit(KAYIT *holder,char buff[100],char delim[2]);

//dosya olusturma
void ders_kodu(DERS **head_ref,OGRENCI **,char file_name[]);
void ders_programi(KAYIT **head_ref,DERS **head1_ref);

void free_nodes(DERS **head,OGRENCI **head1,KAYIT **head2);
int main(){
	int i=10000;
	int girdi,choice;
	short unsigned int max_kredi,max_ders;
	DERS *head=NULL;
	OGRENCI *head1=NULL;
	OGRENCI *tail1=NULL;
	KAYIT *head2=NULL;
	printf("DIKKAT EDILMESI GEREKEN KURALLAR\n");
	printf("Lutfen Ders Kodunu Buyuk Harflerle Giriniz!!!\n");
	printf("Lutfen ders kodu veya ogrenci numarasi arasinda bosluk býrakmayiniz\n");
	printf("\n---------------------------------------------\n");
	//printf("D");
	printf("Alinabilecek maksimum kredi sayisini giriniz:");
	scanf("%hu",&max_kredi);
	printf("Alinabilecek maksimum ders sayisini giriniz:");
	scanf("%hu",&max_ders);
	
	printf("Ogrenci sistemine hazir dosya kayitlarinizi aktarmak istiyorsaniz 0'a basiniz-\n-aksi halde ogrenci yonetim sistemine yonlendirileceksiniz:");
	scanf("%d",&girdi);
	if(girdi==0){
		do{
			printf("\n#Hazir ders dosyasi eklemek icin 1'e;\n");
			printf("#Hazir ogrenci dosyasi eklemek icin 2'ye;\n");
			printf("#Hazir kayit dosyasi eklemek icin 3'e;\n");
			printf("#Dosya ekleme sisteminden cikmak icin 0'a;\n");
			printf("\nbasiniz:");
			scanf("%d",&girdi);
			switch(girdi){
			case 0:
				printf("Dosya ekleme sisteminden ciktiniz.\n");
				break;
			case 1:
				dersfile_read(&head,"D:\\CE 2.SINIF\\Yapýsal Programlama\\dersler.txt",",\0");
				list_ders(&head);
				break;
			case 2:
				ogrenciler_read(&head1,&tail1,"D:\\CE 2.SINIF\\Yapýsal Programlama\\ogrenciler.txt",",\0");
				list_ogrenci(&head1,&tail1,ascending);
				break;
			case 3:
				if(head!=NULL && head1!=NULL){
					kayitfile_read(&head2,&head,&i,"D:\\CE 2.SINIF\\Yapýsal Programlama\\OgrenciDersKayit.txt",",\0");
					list_kayit(&head2);
				}
				else{
					printf("\nOgrenci veya ders listeniz bos lutfen ilk once ogrenci ve ders listelerini sisteme ekleyiniz.\n");
				}
				break;
			default:
				printf("Yanlis numara girdiniz.\n");
			 	break;
			}	
		}while(girdi!=0);
	}
	do{
		printf("\n#Ders acmak icin 1'e kapatmak icin 2'ye;\n");
		printf("#Ogrenci eklemek icin 3'e silmek icin 4'e;\n");
		printf("#Ders'e ogrenci kaydi yapmak icin 5'e kayit silmek icin 6'ya;\n");
		printf("#Dosya kayit islemleri icin 7'ye;\n");
		printf("#Cikmak icin 0'a;\n");
		printf("\nbasiniz:");
		scanf("%d",&girdi);
		switch(girdi){
			case 0:
				kayitKaydet(&head2);
				ogrenci_Kaydet(&head1);
				dosyayaKaydet(&head);
				free_nodes(&head,&head1,&head2);
				printf("Ogrenci yonetim sisteminden ciktiniz.\n");
				break;
			case 1:
				list_ders(&head);
			    ders_acma(&head);
			    printf("Acilan dersleri listelemek icin 1'e basiniz:");
			    scanf("%d",&choice);
			    if(choice==1){
			    	list_ders(&head);
				}
				break;
			case 2:
				list_ders(&head);
				ders_kapatma(&head,&head2);
				printf("Acilan dersleri listelemek icin 1'e basiniz:");
			    scanf("%d",&choice);
			    if(choice==1){
			    	list_ders(&head);
				}
				break;
			case 3:
				list_ogrenci(&head1,&tail1,ascending);
				ogrenci_ekle(&head1,&tail1);
				printf("Sistemdeki ogrencileri,\n\t*artan numara sirasinda gormek istiyorsaniz 1'e\n\t*azalan numara sirasinda gormek istiyorsaniz 2'ye basiniz:");
			    scanf("%d",&choice);
			    if(choice==1){
			    	list_ogrenci(&head1,&tail1,ascending);
				}
				else if(choice==2){
					list_ogrenci(&head1,&tail1,descending);
				}
				break;
			case 4:
				list_ogrenci(&head1,&tail1,ascending);
				ogrenci_sil(&head1,&tail1,&head2,&head);
				printf("Sistemdeki ogrencileri,\n\t*artan numara sirasinda gormek istiyorsaniz 1'e\n\t*azalan numara sirasinda gormek istiyorsaniz 2'ye basiniz:");
			    scanf("%d",&choice);
			    if(choice==1){
			    	list_ogrenci(&head1,&tail1,ascending);
				}
				else if(choice==2){
					list_ogrenci(&head1,&tail1,descending);
				}
				break;
			case 5:
				list_kayit(&head2);
				ders_kayit(&head2,&head1,&tail1,&head,&i,max_kredi,max_ders);
				printf("Kayit olma listesini gormek icin 1'e basiniz:");
				scanf("%d",&choice);
			    if(choice==1){
			    	list_kayit(&head2);
				}
				break;
			case 6:
				list_kayit(&head2);
				kayit_sil(&head2,&head,&head1);
				printf("Kayit olma listesini gormek icin 1'e basiniz:");
				scanf("%d",&choice);
			    if(choice==1){
			    	list_kayit(&head2);
				}
				break;
			case 7:
				printf("Bir ogrencinin ders programini kaydetmek icin 1'e\nBir dersin sinif listesine ulasmak icin 2'ye\basiniz:");
				scanf("%d",&choice);
				if(choice==1){
					ders_programi(&head2,&head);
				}
				else if(choice==2){
					ders_kodu(&head,&head1,"D:\\CE 2.SINIF\\Yapýsal Programlama\\DERSKODU.txt");
				}
				break;
			default:
				printf("Yanlis numara girdiniz.\n");
				break;
			}
	}while(girdi!=0);
				
	return 0;
}

void free_nodes(DERS **head,OGRENCI **head1,KAYIT **head2){
	DERS *tmp=*head;
	OGRENCI *tmp2=*head1;
	KAYIT *tmp3=*head2;
	while(*head!=NULL){
		tmp=*head;
		*head=(*head)->next;
		free(tmp);
	}
	while(*head1!=NULL){
		tmp2=*head1;
		*head1=(*head1)->next;
		free(tmp2);
	}
	while(*head2!=NULL){
		tmp3=*head2;
		*head2=(*head2)->next;
		free(tmp3);
	}
	printf("Linked listler temizlendi.\n");
}
void list_ders(DERS **head){
	DERS *iter=*head;
	printf("\nDERS KODU|\t\t DERS ADI \t\t    |DERS KREDISI|DERS KAPASITESI\n");
	while(iter!=NULL){
		printf("\n---------------------------------------------------------------------------------\n");
		printf("%s  |%40s  |\t%3hu\t |\t%-5hu\t|\n",iter->ders_kodu,iter->ders_adi,iter->kredi,iter->kapasite);
		iter=iter->next;
	}
	printf("\n");
}

void list_ogrenci(OGRENCI **head,OGRENCI **tail,OGRENCI* (*ascordesc)(OGRENCI **,OGRENCI **)){
	OGRENCI *iter=ascordesc(head,tail);
	printf("\nOGRENCI NO| OGRENCI ISMI  |OGRENCI SOYISMI| ALINAN KREDI SAYISI |ALINAN DERS SAYISI\n");
	while(iter!=NULL){
		printf("%s  |\t%-10s|%-15s|\t%5hu\t\t|%10hu\n",iter->numara,iter->isim,iter->soyisim,iter->alinan_kredi,iter->alinan_ders);
		if(ascordesc(head,tail)==(*head))
			iter=iter->next;
		else
			iter=iter->prev;
	}
	printf("\n");
}

OGRENCI* ascending(OGRENCI **head,OGRENCI **tail){
	return *head;
}

OGRENCI* descending(OGRENCI **head,OGRENCI **tail){
	return *tail;
}

void list_kayit(KAYIT **head){
	KAYIT *iter=*head;
	printf("ID   | DERS KODU | OGRENCI NUMARASI | KAYIT TARIHI | KAYIT DURUMU\n");
	while(iter!=NULL){
		printf("%d| %s   |%12s\t    |%13s |%-5s\n",iter->ID,iter->ders_kodu,iter->numara,iter->tarih,iter->durum);
		iter=iter->next;
	}
	printf("\n");
}

void ders_kodu(DERS **head_ref,OGRENCI **head2_ref,char file_name[30]){
	FILE *fptr;
	if((fptr=fopen(file_name,"w"))==NULL){
		printf("Error!! Opening file");
		exit(1);
	}
	DERS *iter=*head_ref;
	OGRENCI *tmp=*head2_ref;
	int j,i;
	char kod[SIZE];
	printf("Ogrenci listesini gormek istediginiz dersin ders kodunu giriniz:");
	scanf("%s",&kod);
	iter=search_ders_kod(iter,kod);
	printf("%s kodlu dersi alan ogrenciler:\n",kod);
	fprintf(fptr,"%s kodlu dersi alan ogrenciler:\n",kod);
	for(i=0;i<iter->ogrenci_sayisi;i++){
		printf("%s",iter->numara[i]);
		tmp=*head2_ref;
		tmp=search_ogrenci_no(tmp,iter->numara[i]);
		printf(" %s %s\n",tmp->isim,tmp->soyisim);
		fprintf(fptr,"%s,%s,%s\n",iter->numara[i],tmp->isim,tmp->soyisim);
	}
	fclose(fptr);
}

void ders_programi(KAYIT **head_ref,DERS **head1_ref){
	char no[10];
	char gecici_no[10];
	char file_name[40]="D:\\CE 2.SINIF\\Yapýsal Programlama\\";
	FILE *fptr;
	printf("Ders programini istediginiz ogrencinin numarasini giriniz:");
	scanf("%s",no);
	strcpy(gecici_no,no);
	strcat(file_name,gecici_no);
	strcat(file_name,"_DERSPROGRAMI.txt");
	//printf("%s",file_name);
	if((fptr=fopen(file_name,"w"))==NULL){
		printf("Error!! Opening file ");
		exit(1);
	}
	KAYIT *iter=*head_ref;
	DERS *tmp=*head1_ref;
	printf("%s no'lu ogrencinin ders programi:\n",no);
	fprintf(fptr,"%s no'lu ogrencinin ders programi:\n",no);
	while(iter!=NULL){
		iter=search_kayit_no(iter,no); 
		if(strcmp(iter->durum,"kayitli")==0){
			tmp=*head1_ref;
			printf("%s",iter->ders_kodu);
			tmp=search_ders_kod(tmp,iter->ders_kodu);
			printf(" %s %hu\n",tmp->ders_adi,tmp->kredi);
			fprintf(fptr,"%s,%s,%hu\n",iter->ders_kodu,tmp->ders_adi,tmp->kredi);
		}
		iter=iter->next;
	}
	printf("\nOgrenci ders programi %s adli dosyaya kaydedildi.\n",file_name);
	fclose(fptr);
}

OGRENCI* search_ogrenci_no(OGRENCI *iter,char *no){
	while(iter!=NULL && strcmp(iter->numara,no)!=0){
		iter=iter->next;
	}
	return iter;
}

DERS* search_ders_kod(DERS *iter,char *no){
	while(iter!=NULL && strcmp(iter->ders_kodu,no)!=0){
		iter=iter->next;
	}
	return iter;
}

KAYIT* search_kayit_no(KAYIT *iter,char *no){
	while(iter!=NULL && strcmp(iter->numara,no)!=0){
		iter=iter->next;
	}
	return iter;
}

KAYIT* search_kayit_kod(KAYIT *iter,char *kod){
	while(iter!=NULL && strcmp(iter->ders_kodu,kod)!=0){
		iter=iter->next;
	}
	return iter;
}

void durum_guncelleme(KAYIT *iter,char *no){
	while(iter!=NULL){
		if(strcmp(iter->ders_kodu,no)==0){
			strcpy(iter->durum,"DERS_KAPANDI");
			printf("\n%s nolu ders kapandi\n",no);
		}
		iter=iter->next;
	}
}

void kayitKaydet(KAYIT **head){
	FILE *fptr;
	KAYIT *iter=*head;
	if((fptr = fopen("D:\\CE 2.SINIF\\Yapýsal Programlama\\OgrenciDersKayit.txt","w")) == NULL){
		printf("Error !! Opening file");
		exit(1);
	}
	while(iter!=NULL){
		fprintf(fptr,"%d,%s,%s,%s,%s,\n",iter->ID,iter->ders_kodu,iter->numara,iter->tarih,iter->durum);
		iter=iter->next;
	}
	printf("Ogrenci kayitlari ogrencikayit.txt isimli dosyaya kaydedildi.\n");
	fclose(fptr);
}

void Split_Kayit(KAYIT *holder,char buff[255],char delim[2]){
	char *token;
	// strtok(); Delim parametresi ile gösterilen karakter dizisi içindeki sýnýrlayýcý karakterleri kullanarak str parametresi ile gösterilen karakter dizisini parçalara ayýrýr.
	token=strtok(buff,delim);
	// atoi(); Str parametresi ile gösterilen karakter dizisini int bir deðere çevirir.
	holder->ID=atoi(token);
	// NULL; strtok() fonksiyonuna yapýlan ilk çaðrý, parçalanacak C dizesini iletmelidir 
	// ve sonraki çaðrýlar, ilk argüman olarak NULL'u belirtmelidir; bu, fonksiyona, ilk olarak ilettiðiniz diziyi parçalamaya devam etmesini söyler.
	token=strtok(NULL,delim);
	strcpy(holder->ders_kodu,token);//
	token=strtok(NULL,delim);
	strcpy(holder->numara,token);
	token=strtok(NULL,delim);
	strcpy(holder->tarih,token);
	token=strtok(NULL,delim);
	strcpy(holder->durum,token);
}

void kayitfile_read(KAYIT **head,DERS **head_ref,int *ident,char file_name[20],char delim[2]){
	FILE *fptr;
	int n,i;
	KAYIT *reader;
	DERS *tmp=(*head_ref);
	void *pointer;
	char buf[255];
	if((fptr=fopen(file_name,"r"))==NULL){
		printf("Error !! Opening file");
		exit(1);
	}
	fseek(fptr,0,SEEK_END);
	if(ftell(fptr)==0){
		printf("\nAktarmaya calistiginiz dosya bos tekrar deneyiniz.\n");
		exit(8);
	}
	rewind(fptr);
	reader=(KAYIT*)malloc(sizeof(KAYIT));
	(*head)=reader;
	KAYIT* curr;
	KAYIT* prev;
	if(fgets(buf,255,fptr)!=NULL){
		//printf("%s",buf);
		Split_Kayit(reader,buf,delim);
		*ident=reader->ID;
		//printf("\n%s\n",reader->ders_kodu);	
		tmp=search_ders_kod(tmp,reader->ders_kodu);
		if(strcmp(reader->durum,"kayitli")==0)
		{
			printf("Girdi");
			tmp->ogrenci_sayisi=1;
			strcpy(tmp->numara[0],reader->numara);
		}
		//printf("\n%s\n",tmp->numara[0]);
		//printf("%s %hu\n",tmp->ders_kodu,tmp->ogrenci_sayisi);	
		//printf("Girdi");
	}
	prev=reader;
	while(fgets(buf,255,fptr)!=NULL){
		tmp=(*head_ref);
		//printf("%s",buf);
		curr=(KAYIT*)malloc(sizeof(KAYIT));
		prev->next=curr;
		Split_Kayit(curr,buf,delim);
		(*ident)=curr->ID;
		//printf("%s",curr->ders_kodu);
		tmp=search_ders_kod(tmp,curr->ders_kodu);
		//printf("%s",tmp->ders_kodu);
		if(strcmp(curr->durum,"kayitli")==0){
			n=++tmp->ogrenci_sayisi;
			pointer=realloc(tmp->numara,n*sizeof(char*));
			tmp->numara=pointer;
			for(i=n-1;i<n;i++){
				tmp->numara[i]=(char*)malloc(sizeof(char)*9);
			}
			strcpy(tmp->numara[n-1],curr->numara);
		}
		//printf("\n%s\n",tmp->numara[n-1]);
		//printf("%s %hu\n",tmp->ders_kodu,tmp->ogrenci_sayisi);
		// ilk eklendiðinde dersteki kayýtlý öðrenci sayisini bir arttýr	
		prev=curr;
		//tmp=tmp->next;
	}
	(*ident)++;
	prev->next=NULL;
	fclose(fptr);
}

int ders_kayit(KAYIT **head,OGRENCI **head1,OGRENCI **tail,DERS **head2,int *ident,int mkredi,int mders){
	int n,i;
	int day,month,year;
	char date[20];
	time_t now;
	void *pointer;
	char num[10],kod[SIZE];
	KAYIT *iter=*head,*tmp_iter=*head;
	OGRENCI *tmp=*head1;
	DERS *tmp2=*head2;
	printf("Ogrenci numaranizi giriniz:");
	scanf("%s",num);
	tmp=search_ogrenci_no(tmp,num);
	if(tmp==NULL){
		printf("\nBoyle bir ogrenci numarasi bulunamadi.\n");
		return 1;
	}
	else if(tmp->alinan_ders >= mders){
		printf("Alabileceginiz maksimum ders sayisina ulastiniz.\n");
		return 4;
	}
	else if(tmp->alinan_kredi >= mkredi){
		printf("Alabileceginiz maksimum kredi sayisina ulastiniz.\n");
		return 5;
	}
	//printf("\n%s",tmp->numara);
	printf("Kayit olmak istediginiz ders kodunu giriniz:");
	scanf("%s",kod);
	tmp2=search_ders_kod(tmp2,kod);
	iter=search_kayit_no(iter,num);
	tmp_iter=search_kayit_kod(tmp_iter,kod);
	if(tmp2==NULL){
		printf("Boyle bir ders kodu bulunamadi!!.\n");
		return 3;
	}
	else if(tmp2->ogrenci_sayisi == tmp2->kapasite){
		printf("\n%s kodlu dersin kapasitesi doludur.\n",kod);
		return 4;
	}
	else if(tmp_iter->ID == iter->ID){
		printf("Daha once ayni derse kayitli bir ogrenci var!!!\n");
		return 5;
	}
	iter=*head;
	printf("\n%s nolu derse kayit oldunuz.\n",tmp2->ders_kodu);
	KAYIT *newkayit=(KAYIT*)malloc(1*sizeof(KAYIT));
	newkayit->next=NULL;
	newkayit->ID=*ident;
	ident++;
	strcpy(newkayit->ders_kodu,kod);
	strcpy(newkayit->numara,num);  
	strcpy(newkayit->tarih,__DATE__);
	//printf("Girdi");
	strcpy(newkayit->durum,"kayitli");
	//printf("Girdi");
	tmp->alinan_ders++;
	//printf("Girdi");
	tmp->alinan_kredi+=tmp2->kredi;
	n=tmp2->ogrenci_sayisi;
	n=++tmp2->ogrenci_sayisi;
	//printf("%s %d",tmp2->ders_kodu,tmp2->ogrenci_sayisi);
	pointer=realloc(tmp2->numara,n*sizeof(char*));
	tmp2->numara=pointer;
	for(i=n-1;i<n;i++){
		tmp2->numara[i]=(char*)malloc(sizeof(char)*9);
	}
	strcpy(tmp2->numara[n-1],num);
	//printf("\n%s nolu ogrenci %d\n",tmp2->numara[n-1],tmp2->ogrenci_sayisi);
	//printf("Girdi");
	if((*head)==NULL){
		//printf("Girdi");
		*head=newkayit;
	}
	else if(iter->ID >= newkayit->ID){
		newkayit->next=*head;
		(*head)=newkayit;
	}
	else{
		while(iter->next!=NULL && iter->ID < newkayit->ID ){
			iter = iter->next;
		}
		newkayit->next = iter->next;
		iter->next = newkayit;
	}
	kayitKaydet(head);
	printf("\n%hu\n",tmp2->ogrenci_sayisi);
	return 0;
}


int kayit_sil(KAYIT **head_ref,DERS **head1_ref,OGRENCI **head2_ref){
	char no[10],kod[SIZE];
	KAYIT *iter=*head_ref;
	DERS *tmp=*head1_ref;
	OGRENCI *iter_ogrenci=*head2_ref;
	printf("Ogrenci numaranizi giriniz:");
	scanf("%s",no);
	printf("Kaydinizi silmek istediginiz ders kodunu giriniz:");
	scanf("%s",kod);
	while(iter!=NULL && strcmp(iter->ders_kodu,kod)!=0 && strcmp(iter->numara,no)!=0){
		iter=iter->next;
	}
	if(iter!=NULL){
		strcpy(iter->durum,"sildi");
		tmp=search_ders_kod(tmp,iter->ders_kodu);
		tmp->ogrenci_sayisi--;
		iter_ogrenci=search_ogrenci_no(iter_ogrenci,iter->numara);
		iter_ogrenci->alinan_ders--;
		iter_ogrenci->alinan_kredi-=tmp->kredi;
	}
	else{
		printf("\nSistemde boyle bir kayit bulunamadi lutfen kontrol edin.\n");
	}
	printf("%s no'lu ogrencinin kaydi %s kodlu dersten silindi.\n",iter->numara,iter->ders_kodu);
	kayitKaydet(head_ref);
	return 0;
}
int ders_kapatma(DERS **head,KAYIT **head1){
	DERS *iter,*tmp=*head;
	KAYIT *search=*head1;
	char kod[SIZE];
	printf("Silmek istediginiz dersin kodunu giriniz:");
	scanf("%s",kod);
	tmp=search_ders_kod(tmp,kod);
	if(tmp==NULL){
		printf("Hatali islem var olmayan bir ders kodu girdiniz!!!\n");
		return 1;
	}
	if(strcmp((*head)->ders_kodu,kod)==0){
		tmp=*head;
		(*head)=(*head)->next;
		durum_guncelleme(search,kod);
		free(tmp);
		printf("%s kodlu ders silindi\n",kod);
	}
	else{
		iter=*head;
		//iter=search_ders_kod(iter,kod);
		while(iter->next!=NULL && strcmp(iter->next->ders_kodu,kod)!=0){
			iter=iter->next;
		}
		if(iter->next!=NULL){
			tmp = iter->next;
			iter->next=iter->next->next;
			free(tmp);
			durum_guncelleme(search,kod);
			printf("%s kodlu ders silindi\n",kod);
		}
		else{
			printf("%s kodlu ders bulunamadi\n",kod);
			return 1;
		}
	}
	dosyayaKaydet(head);
	return 0;	
}

void Split_Ders(DERS* holder,char buff[100],char delim[2]){
	char *token;
	// strtok(); Delim parametresi ile gösterilen karakter dizisi içindeki sýnýrlayýcý karakterleri kullanarak str parametresi ile gösterilen karakter dizisini parçalara ayýrýr.
	token=strtok(buff,delim);
	// atoi(); Str parametresi ile gösterilen karakter dizisini int bir deðere çevirir.
	strcpy(holder->ders_kodu,token);
	// NULL; strtok() fonksiyonuna yapýlan ilk çaðrý, parçalanacak C dizesini iletmelidir 
	// ve sonraki çaðrýlar, ilk argüman olarak NULL'u belirtmelidir; bu, fonksiyona, ilk olarak ilettiðiniz diziyi parçalamaya devam etmesini söyler.
	token=strtok(NULL,delim);
	strcpy(holder->ders_adi,token);
	token=strtok(NULL,delim);
	holder->kredi=atoi(token);
	token=strtok(NULL,delim);
	holder->kapasite=atoi(token);
}

void dersfile_read(DERS **head,char file_name[20],char delim[2]){
	FILE *fptr,*fp;
	DERS *reader;
	int i;
	char buf[100];
	if((fptr=fopen(file_name,"r"))==NULL){
		printf("Error !! Opening file");
		exit(1);
	}
	fseek(fptr,0,SEEK_END);
	if(ftell(fptr)==0){
		printf("\nAktarmaya calistiginiz dosya bos tekrar deneyiniz.\n");
		exit(8);
	}
	rewind(fptr);
	reader=(DERS*)malloc(sizeof(DERS));
	(*head)=reader;
	DERS* curr;
	DERS* prev;
	reader->numara=(char**)malloc(1*sizeof(char*));
	for(i=0;i<2;i++){
		reader->numara[i]=(char*)malloc(9*sizeof(char));
	}
	if(fgets(buf,100,fptr)!=NULL){
		//printf("%s",buf);
		reader->ogrenci_sayisi=0;
		Split_Ders(reader,buf,delim);		
	}
	prev=reader;
	while(fgets(buf,100,fptr)!=NULL){
		//printf("%s",buf);
		curr=(DERS*)malloc(sizeof(DERS));
		curr->numara=(char**)malloc(1*sizeof(char*));
		for(i=0;i<2;i++){
			curr->numara[i]=(char*)malloc(9*sizeof(char));
		}
		curr->ogrenci_sayisi=0;
		prev->next=curr;
		Split_Ders(curr,buf,delim);
		prev=curr;
	}
	prev->next=NULL;
	fclose(fptr);
}

int ders_acma(DERS **head){
	int i;
	DERS *tmp=*head;
	DERS *newders=(DERS*)malloc(1*sizeof(DERS));
	newders->next=NULL;
	fflush(stdin);
	printf("Ders adini giriniz:");
	gets(newders->ders_adi);
	//scanf("%s",newders->ders_adi);
	printf("Ders kodunu giriniz:");
	gets(newders->ders_kodu);
	//scanf("%s",newders->ders_kodu);
	tmp=search_ders_kod(tmp,newders->ders_kodu);
	if(tmp!=NULL){
		printf("Hatali islem ders daha onceden eklenmis veya hatali ders kodu girdiniz!!!\n");
		return 1;
	}
	newders->ogrenci_sayisi=0;
	newders->numara=(char**)malloc(1*sizeof(char*));
	for(i=0;i<1;i++){
		newders->numara[i]=(char*)malloc(9*sizeof(char));
	}
	printf("Ders kapasitesini giriniz:");
	scanf("%hu",&newders->kapasite);
	printf("Ders kredisini giriniz:");
	scanf("%hu",&newders->kredi);
	tmp=*head;
	if(*head==NULL){
		*head=newders;
	}
	else if(strcmp(tmp->ders_kodu , newders->ders_kodu)>0){
		newders->next=*head;
		*head=newders;
	}
	else{
		while(tmp->next!=NULL && strcmp(tmp->next->ders_kodu,newders->ders_kodu)<0 ){
			tmp = tmp->next;
		}
		newders->next = tmp->next;
		tmp->next = newders;
	}
	dosyayaKaydet(head);
	return 0;
}

void dosyayaKaydet(DERS **head){
	FILE *fptr;
	DERS *iter=*head;
	if((fptr = fopen("D:\\CE 2.SINIF\\Yapýsal Programlama\\dersler.txt","w")) == NULL){
		printf("Error !! Opening file");
		exit(1);
	}
	while(iter!=NULL){
		fprintf(fptr,"%s,%s,%hu,%hu\n",iter->ders_kodu,iter->ders_adi,iter->kredi,iter->kapasite);
		iter=iter->next;
	}
	printf("Dersler dersler.txt isimli dosyaya kaydedildi.\n");
	fclose(fptr);
}

//OGRENCI
void ogrenciler_read(OGRENCI **head,OGRENCI **tail,char file_name[20],char delim[2]){
	FILE *fptr;
	OGRENCI *reader;
	char buf[100];
	if((fptr=fopen(file_name,"r"))==NULL){
		printf("Error !! Opening file");
		exit(1);
	}
	reader=(OGRENCI*)malloc(sizeof(OGRENCI));
	(*head)=reader;
	(*head)->prev=NULL;
	//(*tail)=reader;
	OGRENCI* curr;
	OGRENCI* prev;
	if(fgets(buf,100,fptr)!=NULL){
		//printf("%s",buf);
		Split_Ogrenci(reader,buf,delim);
		reader->ogrenci_sayisi=1;		
	}
	prev=reader;
	while(fgets(buf,100,fptr)!=NULL){
		//printf("%s",buf);
		curr=(OGRENCI*)malloc(sizeof(OGRENCI));
		curr->ogrenci_sayisi=prev->ogrenci_sayisi+1;
		//printf("%d\n",curr->ogrenci_sayisi);
		prev->next=curr;
		curr->prev=prev;
		Split_Ogrenci(curr,buf,delim);
		prev=curr;
	}
	(*tail)=prev;
	prev->next=NULL;
	fclose(fptr);
}

void Split_Ogrenci(OGRENCI* holder,char buff[100],char delim[2]){
	char *token;
	// strtok(); Delim parametresi ile gösterilen karakter dizisi içindeki sýnýrlayýcý karakterleri kullanarak str parametresi ile gösterilen karakter dizisini parçalara ayýrýr.
	token=strtok(buff,delim);
	// atoi(); Str parametresi ile gösterilen karakter dizisini int bir deðere çevirir.
	strcpy(holder->numara,token);
	// NULL; strtok() fonksiyonuna yapýlan ilk çaðrý, parçalanacak C dizesini iletmelidir 
	// ve sonraki çaðrýlar, ilk argüman olarak NULL'u belirtmelidir; bu, fonksiyona, ilk olarak ilettiðiniz diziyi parçalamaya devam etmesini söyler.
	token=strtok(NULL,delim);
	strcpy(holder->isim,token);
	token=strtok(NULL,delim);
	strcpy(holder->soyisim,token);
	token=strtok(NULL,delim);
	holder->alinan_kredi=atoi(token);
	token=strtok(NULL,delim);
	holder->alinan_ders=atoi(token);
}

void ogrenci_Kaydet(OGRENCI **head){
	FILE *fptr;
	OGRENCI *iter=*head;
	if((fptr = fopen("D:\\CE 2.SINIF\\Yapýsal Programlama\\ogrenciler.txt","w")) == NULL){
		printf("Error !! Opening file");
		exit(1);
	}
	while(iter!=NULL){
		fprintf(fptr,"%s,%s,%s,%hu,%hu\n",iter->numara,iter->isim,iter->soyisim,iter->alinan_kredi,iter->alinan_ders);
		iter=iter->next;
	}
	printf("Ogrenci bilgileri ogrenciler.txt isimli dosyaya kaydedildi.\n");
	fclose(fptr);
}

int ogrenci_ekle(OGRENCI **head,OGRENCI **tail){
	OGRENCI *tmp=*head;
	OGRENCI *newogrenci=(OGRENCI*)malloc(1*sizeof(OGRENCI));
	newogrenci->next=NULL;
	newogrenci->prev=NULL;
	printf("Ogrenci numarasi giriniz:");
	scanf("%s",newogrenci->numara);
	tmp=search_ogrenci_no(tmp,newogrenci->numara);
	if(tmp!=NULL){
		printf("\nVar olan bir ogrenciyi eklemeye calistiniz!!\n");
		return 2;
	}
	fflush(stdin);
	printf("Ogrenci ismini giriniz:");
	gets(newogrenci->isim);
	//scanf("%s",newogrenci->isim);
	printf("Ogrenci soyismini giriniz:");
	gets(newogrenci->soyisim);
	//scanf("%s",newogrenci->soyisim);
	//printf("Ogrencinin aldigi ders sayisi:");
	//scanf("%d",&newogrenci->alinan_ders);
	newogrenci->alinan_ders=0;
	//printf("Ogrencinin aldigi kredi sayisi:");
	//scanf("%d",&newogrenci->alinan_kredi);
	newogrenci->alinan_kredi=0;
	tmp=*head;
	if(*head==NULL){
		*head=newogrenci;
		(*tail)=newogrenci;
		newogrenci->ogrenci_sayisi=1;
	}
	else if(strcmp(tmp->numara , newogrenci->numara)>0){
		newogrenci->next=*head;
		tmp->prev=newogrenci;
		*head=newogrenci;
		(*tail)->ogrenci_sayisi++;
	}
	else{
		while(tmp->next!=NULL && strcmp(tmp->next->numara,newogrenci->numara)<0 ){
			tmp = tmp->next;
		}
		if(tmp->next==NULL){
			newogrenci->ogrenci_sayisi=(*tail)->ogrenci_sayisi+1;
			(*tail)=newogrenci;
		}
		else{
			tmp->next->prev=newogrenci;
			(*tail)->ogrenci_sayisi++;
		}
		newogrenci->next = tmp->next;
		tmp->next = newogrenci;
		newogrenci->prev=tmp;
	}
	ogrenci_Kaydet(head);
}

int ogrenci_sil(OGRENCI **head,OGRENCI **tail,KAYIT **head1_ref,DERS **head2_ref){
	OGRENCI *tmp=*head,*search=*head;
	KAYIT *iter=*head1_ref;
	DERS *iter_ders=*head2_ref;
	int i;
	char no[10];
	char *p;
	printf("Silmek istediginiz ogrenci numarasini giriniz:");
	scanf("%s",no);
	if(strcmp(tmp->numara,no)==0){
		tmp=*head;
		(*head)=(*head)->next;
		(*head)->prev=NULL;
		(*tail)->ogrenci_sayisi--;
		//durum_guncelleme(search,kod);
		free(tmp);
		printf("%s nolu ogrenci silindi\n",no);
	}
	else{
		tmp=*head;
		while(search->next!=NULL && strcmp(search->next->numara,no)!=0){
			search=search->next;
		}
		if(search->next!=NULL){
			tmp = search->next;
			search->next=search->next->next;
			if(tmp->next!=NULL){
				tmp->next->prev=search;
				(*tail)->ogrenci_sayisi--;
			}
			else{
				(*tail)=search;
			}
			free(tmp);
			//durum_guncelleme(search,kod);
			printf("%s nolu ogrenci silindi\n",no);
		}
		else{
			printf("%s nolu ogrenci bulunamadi\n",no);
			return 1;
		}
	}
	ogrenci_Kaydet(head);
	while(iter->next!=NULL){
		printf("\n%s\n",iter->ders_kodu);
		if(strcmp(iter->numara,no)==0){
			strcpy(iter->durum,"ogrenci_kaydi_silindi");
		}
		//printf("%s",iter->durum);
		iter=iter->next;
	}
	printf("\n%s\n",iter->ders_kodu);
	kayitKaydet(head1_ref);
	printf("Girdi");
	//Öðrencinin numarasini derslerden silme
	while(iter_ders!=NULL){
		i=0;
		//
			printf("%s %hu\n",iter_ders->ders_kodu,iter_ders->ogrenci_sayisi);
		while(i<iter_ders->ogrenci_sayisi && strcmp(iter_ders->numara[i],no)!=0){
			printf("%s\n",iter_ders->ders_kodu);
			printf("%s\n",iter_ders->numara[i]);
			i++;
		}
		//printf("%s\n",iter_ders->numara[i]);
		if(i<iter_ders->ogrenci_sayisi){
			while(i<iter_ders->ogrenci_sayisi-1){
				printf("%s %s\n",iter_ders->numara[i],iter_ders->numara[i+1]);
				strcpy(iter_ders->numara[i],iter_ders->numara[i+1]);
				printf("%s %s\n",iter_ders->numara[i],iter_ders->numara[i+1]);
				i++;
			}
			iter_ders->numara[i]="0";
			iter_ders->ogrenci_sayisi--;
		}
		iter_ders=iter_ders->next;
	}
	return 0;
}
