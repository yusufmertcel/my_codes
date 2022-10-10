clear;clc;

n=input('Please enter number of elements of first signal:');
%%First signal read
 indexx=zeros(1,n);
 x=zeros(1,n);
for i=1:n
 fprintf('First Signal Index %d:',i);
 indexx(i)=input('');
 fprintf('First Signal %d. Value ',i);
 x(i)=input('');
end

m=input('Please enter number of elements of second signal:');
indexy=zeros(1,m);
y=zeros(1,m);
%%Second signal read
for i=1:m
 fprintf('Second Signal Index %d:',i);
 indexy(i)=input('');
 fprintf('Second Signal %d. Value ',i);
 y(i)=input('');
end
y1n=conv(x,y);
y2n=myconv(x,n,y,m);

%%2.soru
%%Grafiksel
figure();
hold on
subplot(1,2,1);
stem(indexx,x,"filled","LineStyleMode","auto");
title('First Signal');
xlabel('n');
ylabel('Amplitude');
subplot(1,2,2);
stem(indexy,y,"filled","LineStyleMode","auto");
title('Second Signal');
xlabel('n');
ylabel('Amplitude');
hold off
min = indexx(1)+indexy(1);
max = indexx(length(indexx))+indexy(length(indexy)); 
figure();
stem(min:1:max,y2n,"LineWidth",2,"Color",'r','Marker','*');
title('My Convolution');
xlabel('N Values');
ylabel('Amplitude Values');

%%Hazır conv fucntion
figure();
stem(min:max,y1n,"LineWidth",2,"Color",'b','Marker','o');
title('Matlab Convolution');
xlabel('N Values');
ylabel('Amplitude Values');

%%Vektörel
fprintf("My Convolution:\n");
disp(y2n);
disp(min:max);
fprintf('Matlab Convolution:\n')
disp(y1n);
disp(min:max);


%% 5 saniye ses kaydetme
recObj = audiorecorder;
disp('Start speaking.');
recordblocking(recObj,5);
disp('End of recording');
X1 = getaudiodata(recObj);
pause(10);
%%10 saniye ses kaydetme
recObj1 = audiorecorder;
disp('Start speaking.');
recordblocking(recObj1,10);
disp('End of recording');
X2 = getaudiodata(recObj1);
H = zeros(1,length(X1));
H(1) = 1;
H(401) = 0.4*1;
H(801) = 0.4*1;
V=X1.';
My_Y1 = myconv(V,length(V),H,length(H));
My_Y1=My_Y1(:);

Y1=conv(V,H);

V=X2.';
My_Y2 = myconv(V,length(V),H,length(H));
My_Y2=My_Y2(:);

Y2=conv(V,H);

%%soru 5
%%5 seconds recording
disp('My 5 seconds Recording X1:');
sound(X1)
pause(10);
disp('5 seconds Sound Matlab Convolution Y1:');
sound(Y1)
pause(10);
disp('5 Seconds Sound My Convolution My_Y1:');
sound(My_Y1)
pause(10);
%%10 seconds recording
disp('My 10 seconds Recording X2:');
sound(X2)
pause(10);
disp('10 seconds Sound Matlab Convolution Y2:');
sound(Y2)
pause(10);
disp('10 Seconds Sound My Convolution My_Y2:');
sound(My_Y2)
pause(10);

%%my convolution function
function W = myconv(X,n,Y,m)
W= zeros(1,n+m-1);
for i=1:n+m-1
    for j=1:i
        if(i-j+1<=n && j<=m)
            W(i)=W(i)+X(i-j+1)*Y(j);
        else
        end
    end
end

end
