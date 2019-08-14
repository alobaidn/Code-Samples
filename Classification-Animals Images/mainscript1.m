% Final Project Main Script
% by John Hammer, Tuyen Ly
% Math 521, Spring 2012
clear all;
%--- Get Raw Training Data and Raw Testing Data ---%
M = 38;
N = 20;
Train_Raw = mkData('train1', M);
cd('C:\Users\user\Documents\Courses\CS688\Project\test1');
Test_Raw = mkData('test1', N);
True = [ones(10,1); zeros(N-10,1)]; % Correct Classification Sequence %1's cats
%--- Run Filter Mask on Training and Test Data ---%
Train_Bin = binaryData(Train_Raw, M);
Test_Bin = binaryData(Test_Raw, N);
%--- Run Classification Algorithms on Filtered Data ---%
Result = LDA(Train_Bin, Test_Bin, M, N);
mkResults(Test_Bin, True, Result, N);
Result = Hammer_Ly_Final_PCA(Train_Bin, Test_Bin, M, N);
mkResults(Test_Bin, True, Result, N);