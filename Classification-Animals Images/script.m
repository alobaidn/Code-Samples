clear
% Read in data
%cd('/dogs')
fname = ls;
N = length(fname);
for i=3:N
Ddata = imread(fname(i,:));
Ddata = double(Ddata);
Ddata = Ddata(:,:,1);
Dmat(:,i-2) = reshape(Ddata,size(Ddata,1)*size(Ddata,2),1);
end
cd ..
cd('/elephant')
fname = ls;
N = length(fname);
for i=1:N
Edata = imread(fname(i,:));
Edata = double(Edata);
Edata = Edata(:,:,1);
Emat(:,i-2) = reshape(Edata,size(Edata,1)*size(Edata,2),1);
end
cd ..
% Training Set
elephants = Emat(:,1:80);
dogs = Dmat(:,1:80);
% Testing Set
load PatternRecAns
probes = TestSet;
% Number of features
feature = 20;
% Classify the probes as cats or dogs
clsfy = waveFDA(elephants,dogs,probes,feature);
counter = abs(clsfy - hiddenlabels);
