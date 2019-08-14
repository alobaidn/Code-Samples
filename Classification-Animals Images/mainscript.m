% Final Project Main Script
% by John Hammer, Tuyen Ly
% Math 521, Spring 2012
clear all;
%--- Get Raw Training Data and Raw Testing Data ---%
M = 70;
N = 48;
Train_Raw = mkData('train4', M);
cd('C:\Users\user\Documents\Courses\CS688\Project\');
Test_Raw = mkData('test3', N);
%True = [ones(27,1); zeros(10,1)]; % Correct Classification Sequence %1's horses
True =[ones(27,1); zeros(10,1) ; ones(11,1)+1;] %1 horse, 2 flamingos, 3 cats
%--- Run Filter Mask on Training and Test Data ---%
Train_Bin = binaryData(Train_Raw, M);
Test_Bin = binaryData(Test_Raw, N);
%--- Run Classification Algorithms on Filtered Data ---%
[Result, P]  = LDA(Train_Bin, Test_Bin, M, N);
mkResults(Test_Bin, True, Result, N);
Result = Hammer_Ly_Final_PCA(Train_Bin, Test_Bin, M, N);
mkResults(Test_Bin, True, Result, N);