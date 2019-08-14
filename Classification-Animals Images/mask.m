function [AA] = mask(A)
%-------------------------------------------------------------------------
% Purpose:
% To extract the edges(outline) of the image
%
% Input:
% A - matrix of image
%
% Output:
% AA - matrix of image after filter and mask
%-------------------------------------------------------------------------
% The weighted average filter
filter = (1/16)*[1 2 1 ; 2 4 2 ; 1 2 1];
% The Laplacian mask
mask = [-1 -1 -1 ; -1 8 -1 ; -1 -1 -1];
Af = conv2(A,filter,'same');
Afm = conv2(Af,mask,'same');
nbcol = size(colormap(gray),1);
AA = wcodemat(Afm,nbcol);

