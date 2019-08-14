% PCA.m
% by John Hammer, Tuyen Ly
% Math 521, Spring 2012
function [D,KL,A] = PCA(X, TOL)
% X is the Data Ensemble
% TOL is the tolerance
% D is the reduced dimension
% KL is the KL basis
% A contains the coordinates of each data point in X relative to KL basis
%--- Perform SVD ---%
[KL,S,V] = svd(X,'econ');
%--- Cumulative Energy Procedure ---%
ds = diag(S).^2;
dsum = sum(ds);
ksum = 0;
for k = 1:length(ds)
ksum = ksum + ds(k);
if (ksum/dsum) > TOL
D = k; % Best D Approx for KL basis
break;
end
end
KL = KL(:,1:D); % KL basis
A = S(1:D,1:D)*V(:,1:D)'; % Expansion coefs
end