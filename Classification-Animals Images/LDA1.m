%LDA
function [W] = LDA1(D1,D2,D3,M)
% D1 is the first Data Set
% D2 is the second Data Set
% M is the row dimension for both classes
% W is the Optimal Projection Vector
%--- Find number of data points in each set ---%
n1 = size(D1,2);
n2 = size(D2,2);
n3 = size(D3,2);
%--- Define class-wise means ---%
m1 = sum(D1,2)/n1;
m2 = sum(D2,2)/n2;
m3 = sum(D3,2)/n3;
%--- Define between-class scatter matrix ---%
v1 = m2-m1;
v2 = m3-m1;
Sb1 = v1*v1';
Sb2 = v2*v2';
%--- Define within-class scatter matrix ---%
Sw = zeros(M,M);
for i = 1:n1
v1 = D1(:,i) - m1;
Sw = Sw + v1*v1';
end
for i = 1:n2
v1 = D2(:,i) - m2;
Sw = Sw + v1*v1';
end
for i = 1:n3
v2 = D3(:,i) - m3;
Sw = Sw + v2*v2';
end
%--- Solve generalized eigenvalue problem Sb*W=L*Sw*W ---%
[V,D] = eig(Sb1,Sw);
[V1,D1] = eig(Sb2,Sw);
%--- Find Eigenvector with Largest Eigenvalue ---%
evals = diag(D);
evals1 = diag(D1);
evmax = evals(1);
evmax1 = evals1(1);
ind = 1; % Index of largest EV
for i=2:length(evals)
if evmax < evals(i)
evmax = evals(i);
ind = i;
end
end
W = V(:,ind)/norm(V(:,ind),2); % Optimal Projection vector
end