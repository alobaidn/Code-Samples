% mkResults.m
% by John Hammer, Tuyen Ly
% Math 521, Spring 2012
function X= mkResults(Test, True, Result, N)
% Test is the test image Set
% True holds the CORRECT classifications of test images
% Result is the METHOD classifications of test images
% Dog is zero, Cat is one
% N is the number of Test images
%
% Conf is a 2x2 confusion matrix given by
% [ Dogs as Dogs , Dogs as Cats ]
% [ Cats as Cats , Cats as Dogs ]
%--- Construct 2x2 Confusion Matrix ---%
Conf = zeros(3);
X = True - Result;
for k = 1:N
if ((X(k) == -1) && (True(k)==0))
Conf(1,2) = Conf(1,2) + 1; % h as f
elseif ((X(k) == 1) && (True(k)==1))
Conf(2,2) = Conf(2,2) + 1; % f as h
elseif ((X(k) == 2) && (True(k)==2))
Conf(3,1) = Conf(3,1) + 1; % Cat as h
elseif ((X(k) == -2) && (True(k)==0))
Conf(1,3) = Conf(1,3) + 1; % h as C
elseif ((X(k) == -1) && (True(k)==1))
Conf(2,3) = Conf(2,3) + 1; % f as c
elseif ((X(k) == 1) && (True(k)==2))
Conf(3,3) = Conf(3,3) + 1; % Cat as f
else
if True(k) == 0
Conf(1,1) = Conf(1,1) + 1; % Dog as Dog
else
    if True(k) == 1 
Conf(2,1) = Conf(2,1) + 1; % Cat as Cat
    else
        if X(k) == 2 
Conf(3,1) = Conf(3,1) + 1; % Cat as Cat
display(Conf(3,1));
    end
end
end
end
disp('Results are');
disp(Conf);

%--- Display incorrectly classified images ---%
%i = 0;
%figure()
%nWrong = sum(Conf(:,2));
%for k = 1:N
%if X(k) ~= 0
%i = i+1;
%subplot(ceil(sqrt(nWrong)),ceil(sqrt(nWrong)),i);
%imagesc(reshape(Test(:,k),64,64)); colormap(gray); axis off;

%str = sprintf('Image %d',k); title(str);
%end
%end
end % end function