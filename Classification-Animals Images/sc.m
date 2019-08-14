
gr = imread('dogs\1.jpg');
B = rgb2gray(gr);
for i=2:35
    j = i;
   s1 = num2str(i); 
  s = strcat('dogs\',s1);
   A = imread(strcat(s,'.jpg'));
 gr = rgb2gray(A);
 B = cat(1, gr, B);
end