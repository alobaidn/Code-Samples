%--- Classify and Plot Test Animals ---%
function [Result, P] = LDA(Train,Test,M,N)
% LINEAR DISCRIMINANT ANALYSIS
% Train is the set of Training data
% Test is the set of Testing data
% M is the number of images in Train
% N is the number of images in Test
% Result holds the classifications of test data
% dog is zero, cat is one
Result = zeros(N,1);
%--- Reduce Dimension of Training Data using PCA ---%
[d,KL,A] = PCA(Train, 0.97);
Cats = A(:,1:36); %horses
Dogs = A(:,37:60); % flaminogs
cat = A(:,61:70); %cats
%--- Reduce Dimension of Test Data ---%
Test = KL'*Test;
%--- Perform LDA and Project Data onto Real Line ---%
[W] = LDA1(Cats,Dogs, cat, d);
C = W'*Cats;
D = W'*Dogs;
CC = W'*cat;
%--- Find threshold value alpha ---%
Cmin = min(C);
Cmax = max(C);
Dmin = min(D);
Dmax = max(D);
CCmin = min(CC);
CCmax = max(CC);
Cpos = 0;
if Cmax < Dmax% Cats are left of alpha
alpha = (Cmax + Dmin)/2;
Cpos = 1;
span = [0 30 -0.5 0.5];
else
alpha = (Cmin + Dmax)/2;
span = [0 30 -0.5 0.5];
end
display(size(C));
display(size(D));
%display(size(CC));
%--- Plot Projected Data ---%
figure();
plot(C,zeros(36,1),'bo'); hold on; % Plot Cat Data Set in Blue horse
plot(D,zeros(24,1),'ro'); hold on;% Plot Dog Data Set in Red flamingo
plot(CC,zeros(10,1),'go'); hold on;
%plot(CC,zeros(10,1),'g*'); % cats
plot(5.3,0,'ms'); % Plot threshold value
plot(8,0,'ms');
%plot(2*alpha/3,0,'gs');
for k = 1:N
P = W'*Test(:,k);
display(P);
if Cpos
if P < 5.3
Result(k) = 0;
else
if (P > 5.3) & (P < 8)
Result(k) = 1;
else
if P >  8
Result(k) = 2;
end
end
end
end
%if k
if P < 5.3
plot(P,0.25,'b*');
%end
else
if (P > 5.3) & (P < 8 )  %(k > 27) & (k < 38 )
plot(P,0.25,'g*');
%end
else
if P > 8
plot(P,0.25,'r*');  
end
%end
end
end
end
hold off; %axis(span);
end % end function
%if k <= N/2
%plot(P,0.25,'bo');
%else
%plot(P,0.25,'ro');
%end