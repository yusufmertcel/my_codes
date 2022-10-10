
myds SEGMENT PARA 'veri'
	CR	EQU 13
	LF  EQU 10
	links DW 200 DUP(?)
	address DW 200 DUP(?)
	dizi DW 200 DUP(?)
	min_indis DW ?
	N DW 0
	message1 DB "1-Fill an array of size of n ",0
	message2  DB "2-Print the array",0
	message3 DB "3-Adding a new element to the array",0
	message4 DB "Please enter a number:",0
	message5 DB "Elements of array:",0
	message6 DB "Please enter the size of array:",0
	message7 DB "Please enter the elements of array:",0
	message8 DB "Please add a new element to the array:",0
	exit DB "0-Exit",0
	err	DB CR, LF, 'You have entered a wrong character.Please,re-enter', 0
	err2	DB CR, LF, 'The number you entered is wrong.Please,re-enter', 0
	err3 DB CR,LF, 'Negative value or zero have been entered,Please,re-enter',0
	n_line DB 0AH,0DH,"$",0
	tab DB "|$",0
myds ENDS

myss SEGMENT PARA STACK 'yigin'
     DW 20 DUP(?)
myss ENDS

mycs SEGMENT PARA 'k'
     ASSUME CS:mycs,SS:myss,DS:myds
	SATIR_ATLA MACRO
	     MOV DL,10;satır atlamak için
	     MOV AH,2
	     INT 21H

	     MOV DL,13
	     MOV AH,2
	     INT 21H
	 ENDM
ANA  PROC FAR
    PUSH DS
    XOR AX,AX
    PUSH AX

    MOV AX,myds
    MOV DS,AX
DONGU:
	MOV AX,OFFSET exit
	CALL PUT_STR
	MOV AX,OFFSET message1
	CALL PUT_STR
	MOV AX,OFFSET message2
	CALL PUT_STR
	MOV AX,OFFSET message3
	CALL PUT_STR
	MOV AX,OFFSET message4
	CALL PUT_STR

	CALL GETN
	CMP AX,1
	JNE MENU2
		;ALT MENU 1
		;n aliyor
		MOV AX,OFFSET message6
		CALL PUT_STR
	  CALL GETN
		CMP AX,0
		JG DEVAM
		MOV AX,OFFSET err3
		CALL PUT_STR
	DEVAM:
		MOV N,AX
		MOV CX,N
		MOV AX,OFFSET message7
		CALL PUT_STR
		 ;diziye elemena aliyor
		XOR SI,SI
		L1:
		CALL GETN
		MOV dizi[SI],AX
		ADD SI,2
		LOOP L1
		CALL COPY
		CALL LINKED_LIST
		JMP DONGU
MENU2:
		CMP N,0
		JZ DONGU
		CMP AX,2
		JNE MENU3
		;ALT MENU 2
		MOV CX,N
		XOR DI,DI
		DEC CX
		XOR SI,SI
		L6:	PUSH DI
		ADD SI,2
		MOV DI,address[DI]
		MOV AX,address[SI]
		SHR AX,1
		MOV links[DI],AX
		POP DI
		ADD DI,2
		LOOP L6
		MOV BX,1
		NEG BX
		MOV DI,address[DI]
		;MOV AX,address[SI]
		MOV links[DI],BX

		MOV AX,OFFSET message5
		CALL PUT_STR
		CALL ARR_LIST
		SATIR_ATLA
		CALL INDIS_LIST
		SATIR_ATLA
		;ALT_MENU2
		JMP DONGU
  MOV AX,OFFSET err2
	CALL PUT_STR
	JMP DONGU
	;CIKIS1:JMP CIKIS
	;ALT_MENU3_1:JMP ALT_MENU3
MENU3:
	CMP AX,3
	JNE HATA
	CMP N,0
	JZ MENU2
	MOV AX,OFFSET message8
	CALL PUT_STR
	CALL INSERT_ELEMENT
	JMP DONGU
HATA:
	CMP AX,0
	JE CIKIS
	MOV AX,OFFSET err
	CALL PUT_STR
	JMP DONGU
CIKIS:

     RETF
ANA  ENDP

;Alt menu 3'teki yeni eleman ekleme yordami.
INSERT_ELEMENT PROC NEAR
		INC N
		MOV SI,N
		SHL SI,1
		SUB SI,2
		CALL GETN
		MOV dizi[SI],AX
		XOR DI,DI
DON:
		CMP DI,SI
		JAE DON_KIR
		PUSH DI
		MOV DI,address[DI]
		MOV AX,dizi[DI]
		CMP AX,dizi[SI]
		POP DI
		JGE DON_KIR
		ADD DI,2
		JMP DON
DON_KIR:
		;POP DI
		PUSH DI
		SHR DI,1
		MOV CX,N
		SUB CX,DI
		DEC CX
		CMP CX,0
		JZ SKIP
		MOV BX,SI
		PUSH SI
L5:
		SUB BX,2
		MOV AX,address[BX]
 		MOV address[SI],AX
		SUB SI,2
		LOOP L5
		POP SI
SKIP:
		POP DI
		MOV address[DI],SI
   RET
INSERT_ELEMENT ENDP

;Dizinin kendisini yazdırır.
ARR_LIST PROC NEAR
	XOR SI,SI
	MOV CX,N
	L2:
	PUSH SI
	;MOV SI,address[SI]
	MOV AX,dizi[SI]
	CALL PUTN
	MOV DX,OFFSET tab
	MOV AH,09H
	INT 21H
	POP SI
	ADD SI,2
	LOOP L2
	RET
ARR_LIST ENDP

;Dizinin sıralanmıs halindeki indis degerlerini yazdırır.
INDIS_LIST PROC NEAR
	MOV CX,N
	XOR SI,SI
	L9:
	MOV AX,links[SI]
	CALL PUTN
	MOV DX,OFFSET tab
	MOV AH,09H
	INT 21H
	ADD SI,2
	LOOP L9
	RET
INDIS_LIST ENDP

;Diziyi linked list mantıgında sıralar.
LINKED_LIST PROC NEAR
	XOR SI,SI
	ADD SI,2
	MOV CX,N
	DEC CX
 DISARDA_DON:
	MOV BX,address[SI]
	MOV DI,SI
	SUB DI,2
	IN_DON:
	CMP DI,0
	JL DONGU_KIR
	PUSH DI
	MOV DI,address[DI]
	MOV AX,dizi[DI]
	POP DI
	CMP AX,dizi[BX]
	JLE DONGU_KIR
	PUSH DI
	MOV AX,address[DI]
	ADD DI,2
	MOV address[DI],AX
	POP DI
	SUB DI,2
	JMP IN_DON
	DONGU_KIR:
	ADD DI,2
	MOV address[DI],BX
	ADD SI,2
	LOOP DISARDA_DON
	RET
LINKED_LIST ENDP

;Dizinin indis numaralarını kopyalar.
COPY PROC NEAR
	XOR SI,SI
	XOR DI,DI
	MOV CX,N
L3:
	MOV address[SI],SI
	ADD SI,2
	LOOP L3
	RET
COPY ENDP

GETC	PROC NEAR
        ;------------------------------------------------------------------------
        ; Klavyeden basılan karakteri AL yazmacına alır ve ekranda gösterir.
        ; işlem sonucunda sadece AL etkilenir.
        ;------------------------------------------------------------------------
        MOV AH, 1h
        INT 21H
        RET
GETC	ENDP

GETN 	PROC NEAR
        ;------------------------------------------------------------------------
        ; Klavyeden basılan sayiyi okur, sonucu AX yazmacı üzerinden dondurur.
        ; DX: sayının işaretli olup/olmadığını belirler. 1 (+), -1 (-) demek
        ; BL: hane bilgisini tutar
        ; CX: okunan sayının islenmesi sırasındaki ara değeri tutar.
        ; AL: klavyeden okunan karakteri tutar (ASCII)
        ; AX zaten dönüş değeri olarak değişmek durumundadır. Ancak diğer
        ; yazmaçların önceki değerleri korunmalıdır.
        ;------------------------------------------------------------------------
        PUSH BX
        PUSH CX
        PUSH DX
GETN_START:
        MOV DX, 1	                        ; sayının şimdilik + olduğunu varsayalım
        XOR BX, BX 	                        ; okuma yapmadı Hane 0 olur.
        XOR CX,CX	                        ; ara toplam değeri de 0’dır.
NEW:
        CALL GETC	                        ; klavyeden ilk değeri AL’ye oku.
        CMP AL,CR
        JE FIN_READ	                        ; Enter tuşuna basilmiş ise okuma biter
        CMP  AL, '-'	                        ; AL ,'-' mi geldi ?
        JNE  CTRL_NUM	                        ; gelen 0-9 arasında bir sayı mı?
NEGATIVE:
        MOV DX, -1	                        ; - basıldı ise sayı negatif, DX=-1 olur
        JMP NEW		                        ; yeni haneyi al
CTRL_NUM:
        CMP AL, '0'	                        ; sayının 0-9 arasında olduğunu kontrol et.
        JB error
        CMP AL, '9'
        JA error		                ; değil ise HATA mesajı verilecek
        SUB AL,'0'	                        ; rakam alındı, haneyi toplama dâhil et
        MOV BL, AL	                        ; BL’ye okunan haneyi koy
        MOV AX, 10 	                        ; Haneyi eklerken *10 yapılacak
        PUSH DX		                        ; MUL komutu DX’i bozar işaret için saklanmalı
        MUL CX		                        ; DX:AX = AX * CX
        POP DX		                        ; işareti geri al
        MOV CX, AX	                        ; CX deki ara değer *10 yapıldı
        ADD CX, BX 	                        ; okunan haneyi ara değere ekle
        JMP NEW 		                ; klavyeden yeni basılan değeri al
ERROR:
        MOV AX, OFFSET HATA
        CALL PUT_STR	                        ; HATA mesajını göster
        JMP GETN_START                          ; o ana kadar okunanları unut yeniden sayı almaya başla
FIN_READ:
        MOV AX, CX	                        ; sonuç AX üzerinden dönecek
        CMP DX, 1	                        ; İşarete göre sayıyı ayarlamak lazım
        JE FIN_GETN
        NEG AX		                        ; AX = -AX
FIN_GETN:
        POP DX
        POP CX
        POP DX
        RET
GETN 	ENDP

PUTN 	PROC NEAR
        ;------------------------------------------------------------------------
        ; AX de bulunan sayiyi onluk tabanda hane hane yazdırır.
        ; CX: haneleri 10’a bölerek bulacağız, CX=10 olacak
        ; DX: 32 bölmede işleme dâhil olacak. Soncu etkilemesin diye 0 olmalı
        ;------------------------------------------------------------------------
        PUSH CX
        PUSH DX
        XOR DX,	DX 	                        ; DX 32 bit bölmede soncu etkilemesin diye 0 olmalı
        PUSH DX		                        ; haneleri ASCII karakter olarak yığında saklayacağız.
                                                ; Kaç haneyi alacağımızı bilmediğimiz için yığına 0
                                                ; değeri koyup onu alana kadar devam edelim.
        MOV CX, 10	                        ; CX = 10
        CMP AX, 0
        JGE CALC_DIGITS
        NEG AX 		                        ; sayı negatif ise AX pozitif yapılır.
        PUSH AX		                        ; AX sakla
        MOV AL, '-'	                        ; işareti ekrana yazdır.
        CALL PUTC
        POP AX		                        ; AX’i geri al

CALC_DIGITS:
        DIV CX  		                ; DX:AX = AX/CX  AX = bölüm DX = kalan
        ADD DX, '0'	                        ; kalan değerini ASCII olarak bul
        PUSH DX		                        ; yığına sakla
        XOR DX,DX	                        ; DX = 0
        CMP AX, 0	                        ; bölen 0 kaldı ise sayının işlenmesi bitti demek
        JNE CALC_DIGITS	                        ; işlemi tekrarla

DISP_LOOP:
                                                ; yazılacak tüm haneler yığında. En anlamlı hane üstte
                                                ; en az anlamlı hane en alta ve onu altında da
                                                ; sona vardığımızı anlamak için konan 0 değeri var.
        POP AX		                        ; sırayla değerleri yığından alalım
        CMP AX, 0 	                        ; AX=0 olursa sona geldik demek
        JE END_DISP_LOOP
        CALL PUTC 	                        ; AL deki ASCII değeri yaz
        JMP DISP_LOOP                           ; işleme devam

END_DISP_LOOP:
        POP DX
        POP CX
        RET
PUTN 	ENDP

PUTC	PROC NEAR
        ;------------------------------------------------------------------------
        ; AL yazmacındaki değeri ekranda gösterir. DL ve AH değişiyor. AX ve DX
        ; yazmaçlarının değerleri korumak için PUSH/POP yapılır.
        ;------------------------------------------------------------------------
        PUSH AX
        PUSH DX
        MOV DL, AL
        MOV AH,2
        INT 21H
        POP DX
        POP AX
        RET
PUTC 	ENDP

PUT_STR	PROC NEAR
        ;------------------------------------------------------------------------
        ; AX de adresi verilen sonunda 0 olan dizgeyi karakter karakter yazdırır.
        ; BX dizgeye indis olarak kullanılır. Önceki değeri saklanmalıdır.
        ;------------------------------------------------------------------------
		PUSH BX
        MOV BX,	AX			        ; Adresi BX’e al
        MOV AL, BYTE PTR [BX]	                ; AL’de ilk karakter var
PUT_LOOP:
        CMP AL,0
        JE  PUT_FIN 			        ; 0 geldi ise dizge sona erdi demek
        CALL PUTC 			        ; AL’deki karakteri ekrana yazar
        INC BX 				        ; bir sonraki karaktere geç
        MOV AL, BYTE PTR [BX]
        JMP PUT_LOOP			        ; yazdırmaya devam
PUT_FIN:
	SATIR_ATLA
	;LEA DX,n_line
	;MOV AH,9
	;INT 21H
	POP BX
	RET
PUT_STR	ENDP

mycs ENDS
     END ANA
