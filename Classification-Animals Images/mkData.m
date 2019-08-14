% mkData.m
% by John Hammer, Tuyen Ly
% Math 521, Spring 2012
function Data = mkData(directory,N)
% directory is the path where the images are stored
% N is the number of images to convert to column vectors
% Data is the data matrix containing the column vectors
Data = zeros(64*64,N,1);
cd(directory);
Names = dir('*.jpg');
for k = 1:N
  %  display(k);
  % display(Names(k).name);
         I = imread(Names(k).name);
     A = rgb2gray(I);
Img = double(A);
Img1 = imresize(Img, [64 64]);
vec = Img1(:);
Data(:,k) = vec;
%Data(:,k) = reshape(Img,64*64,1);
end
cd('..');
end