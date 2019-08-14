function [AA] = wavelet(A,n)
%-------------------------------------------------------------------------
% Purpose:
% To decompose an image into an approximation and 3 detail components in an
% arrangement
% LL HL
% LH HH
% where L=low, H=high
%
% Inputs:
% A - matrix form of an image
% n - level of decomposition
%
% Output:
% AA - matrix form of new image
%-------------------------------------------------------------------------
nbcol = size(colormap(gray),1);
cA = A;
for i=1:n
[cA,cH,cV,cD] = dwt2(cA,'haar');
% rescale to appropriate pseudocolor scaling
cod_cH = wcodemat(cH,nbcol);
cod_cV = wcodemat(cV,nbcol);
end
AA = cod_cH+cod_cV;

end