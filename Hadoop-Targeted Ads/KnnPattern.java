package Hadoop.MapReduce;
import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

//import Hadoop.MapReduce.WordCount.IntSumReducer;
//import Hadoop.MapReduce.WordCount.TokenizerMapper;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

//import org.joda.time.DateTime;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.io.LongWritable;


public class KnnPattern
{
	//int K=5;
	static long tStart ;
	static DoubleDouble [][]arr1 = new DoubleDouble[4898][5];
	static File file = new File("gs:////knnhadoop/su.txt"); //KEY IS DIR ex."./local-storage/" and fileName='comp.html'
	static FileWriter fw ;
   static BufferedWriter bw;
	//static BufferedWriter bw;
	static PrintWriter pw; 
	
	public static void setArray(DoubleDouble [][]a)
	{
		 
		 for(int i=0; i <4898; i++)
		 {
			 for(int j=0; j< 5; j++)
			 {
				 arr1[i][j] = a[i][j];
			 }
		 }
	}
	public static DoubleDouble[][] getArray()
	{
		return arr1;
	}
	
	//static int K=5;
	
	// WritableComparable class for a paired Double and String (distance and model)
	// This is a custom class for MapReduce to pass a double and a String through context
	// as one serializable object.
	// This example only implements the minimum required methods to make this job run. To be
	// deployed robustly is should include ToString(), hashCode(), WritableComparable interface
	// if this object was intended to be used as a key etc.
		public static class DoubleDouble implements WritableComparable<DoubleDouble>
		{
			private Double distance = 0.0;
			private Double model = 0.0;

			public void set(Double lhs, Double knnModel)
			{
				distance = lhs;
				model = knnModel;
			}
			
			public Double getDistance()
			{
				return distance;
			}
			
			public double getModel()
			{
				return model;
			}
			
			//@Override
			public void readFields(DataInput in) throws IOException
			{
				distance = in.readDouble();
				model = in.readDouble();
			}
			
			//@Override
			public void write(DataOutput out) throws IOException
			{
				out.writeDouble(distance);
				out.writeDouble(model);
			}
			
			//@Override
			public int compareTo(DoubleDouble o)
			{
				return (this.model).compareTo(o.model);
			}
		}
	
	// The mapper class accepts an object and text (row identifier and row contents) and outputs
	// two MapReduce Writable classes, NullWritable and DoubleString (defined earlier)
	public static class KnnMapper extends Mapper<Object, Text, DoubleWritable, DoubleDouble>
	{
		
		DoubleDouble distanceAndModel = new DoubleDouble();
		TreeMap<Double, Double> KnnMap = new TreeMap<Double, Double>();
		private final static DoubleWritable one = new DoubleWritable(1.0);
		private Text word = new Text();
		// Declaring some variables which will be used throughout the mapper
		static int K=5;
	    int x;
		double normalisedSA;
		double normalisedSB;
		double sS;
		double sG;
		double normalisedSC;
		double uS, vS, x1,x2,x3,x4;
		// The known ranges of the dataset, which can be hardcoded in for the purposes of this example

		double minA1 = 3.8;
		double maxA1 = 14.2;
		
		double minB2 = 0.08;
		double maxB2 = 1.1;
		
		double minC3 = 0;
		double maxC3 = 1.66;
		
		double minA4 = 0.6;
		double maxA4 = 65.8;
		
		double minA5 = 0.009;
		double maxA5 = 0.346;
		
		double minA6 = 2;
		double maxA6 = 289;
		
		double minA7 = 9;
		double maxA7 = 440;
		

double minA8 = 0.98711;
		double maxA8 = 1.03898;
		

		double minA9 = 2.72;
		double maxA9 = 3.82;
		

		double minA10 = 0.22;
		double maxA10 = 1.08;
		

		double minA11 = 8;
		double maxA11 = 14.2;
		

		int count=0;
		int i=0;
		
		// Takes a string and two double values. Converts string to a double and normalises it to
		// a value in the range supplied to reurn a double between 0.0 and 1.0 
		private double normalisedDouble(String n1, double minValue, double maxValue)
		{
			return (Double.parseDouble(n1) - minValue) / (maxValue - minValue);
		}
		
		// Takes two strings and simply compares then to return a double of 0.0 (non-identical) or 1.0 (identical).
		// This provides a way of evaluating a numerical distance between two nominal values.
		private double nominalDistance(double s3, double r3)
		{
			if (s3==r3)
			{
				return 0;
			}
			else
			{
				return 1;
			}
		}
		
		// Takes a double and returns its squared value.
		private double squaredDistance(double n1)
		{
			return Math.pow(n1,2);
		}
		

		// Takes ten pairs of values (three pairs of doubles and two of strings), finds the difference between the members
		// of each pair (using nominalDistance() for strings) and returns the sum of the squared differences as a double.
		private double totalSquaredDistance(double R1, double R2, double R3, double R4, double R5, double R6, double R7, double R8, double R9, double R10, double R11, double S1,
				double S2, double S3, double S4, double S5, double S6, double S7, double S8, double S9, double S10, double S11)
		{	
			double aDifference = S1 - R1;
			double bDifference = S2 - R2;
			double sDifference = S3- R3;
			double gDifference = S4- R4;
			double cDifference = S5 - R5;
			double cDifference2 = S6 - R6;
			double cDifference3 = S7 - R7;
			double cDifference4 = S8 - R8;
			double cDifference5 = S9 - R9;
			double cDifference6 = S10 - R10;
			double cDifference7 = S11 - R11;
	return squaredDistance(aDifference) + squaredDistance(bDifference) + squaredDistance(sDifference) + squaredDistance(gDifference) + squaredDistance(cDifference) + 
					squaredDistance(cDifference2) + squaredDistance(cDifference3) + squaredDistance(cDifference4) + squaredDistance(cDifference5 + squaredDistance(cDifference6) + squaredDistance(cDifference7));

			// The sum of squared distances is used rather than the euclidean distance
			// because taking the square root would not change the order.
			// Status and gender are not squared because they are always 0 or 1.
		//	return squaredDistance(aDifference) + squaredDistance(bDifference) + squaredDistance(sDifference) + squaredDistance(gDifference) + squaredDistance(cDifference) + 
				//	squaredDistance(cDifference2) + squaredDistance(cDifference3);
		}

		// The @Override annotation causes the compiler to check if a method is actually being overridden
		// (a warning would be produced in case of a typo or incorrectly matched parameters)
		//@Override
		// The setup() method is run once at the start of the mapper and is supplied with MapReduce's
		// context object
		protected void setup(Context context) throws IOException, InterruptedException
		{ 
			if (( DistributedCache.getCacheFiles(context.getConfiguration()) != null && DistributedCache.getCacheFiles(context.getConfiguration()).length > 0))
			{
				// Read parameter file using alias established in main()
				String knnParams = FileUtils.readFileToString(new File("./knnParamFile"));
				tStart = System.currentTimeMillis();
				StringTokenizer st = new StringTokenizer(knnParams, ",");
		    	
		    	// Using the variables declared earlier, values are assigned to K and to the test dataset, S.
		    	// These values will remain unchanged throughout the mapper
				
				normalisedSA = normalisedDouble(st.nextToken(), minA1, maxA1); //Double.parseDouble(st.nextToken());//, minAge, maxAge);
				normalisedSB = normalisedDouble(st.nextToken(), minB2, maxB2);//Double.parseDouble(st.nextToken());//, minIncome, maxIncome);
				normalisedSC = normalisedDouble(st.nextToken(), minC3, maxC3); //Double.parseDouble(st.nextToken());
				sS = normalisedDouble(st.nextToken(), minA4, maxA4); //sG = Double.parseDouble(st.nextToken());
				sG = normalisedDouble(st.nextToken(), minA5, maxA5);
				//normalisedSC = Double.parseDouble(st.nextToken());//, minChildren, maxChildren);
				uS = normalisedDouble(st.nextToken(), minA6, maxA6);
				vS = normalisedDouble(st.nextToken(), minA7, maxA7);
				x1 = normalisedDouble(st.nextToken(), minA8, maxA8);
				x2 = normalisedDouble(st.nextToken(), minA9, maxA9);
				x3 = normalisedDouble(st.nextToken(), minA10, maxA10);
				x4 = normalisedDouble(st.nextToken(), minA11, maxA11);

				x = 5;
				System.out.println("SETUP COmpleted!!");				
			}
		}
				
		
		// The map() method is run by MapReduce once for each row supplied as the input data
		 public void map(Object key, Text value, Context context) throws IOException, InterruptedException
		{
			
			// Tokenize the input line (presented as 'value' by MapReduce) from the csv file
			// This is the training dataset, R
			//List<TreeMap> lst = new ArrayList<TreeMap>();
			//ArrayList<DoubleDouble> tree = new ArrayList<DoubleDouble>();
			//ArrayList<TreeMap<Integer>> lst = new ArrayList<TreeMap<Integer>>();
			//TreeMap<Double, Double> arr=new TreeMap<Double, Double>[366][K]();
			//TreeMap<Double, Double> array[4898] = nrw 
			//TreeMap<Double,Double>[][] map = new TreeMap<Double,Double>()[366][K];
			//List<DoubleDouble> lst = new ArrayList<DoubleDouble>();
			DoubleDouble [][]arr = new DoubleDouble[4898][K];
			//TreeMap<Double, Double> [] map = new TreeMap<Double, Double>()[20000];
			String rLine = value.toString();
			StringTokenizer st = new StringTokenizer(rLine, ",");
			Text cl = new Text();
			double normalisedRAge = normalisedDouble(st.nextToken(), minA1, maxA1);
			double normalisedRIncome = normalisedDouble(st.nextToken(), minB2, maxB2);
			double normalisedRChildren = normalisedDouble(st.nextToken(), minC3, maxC3);
			double rStatus = normalisedDouble(st.nextToken(), minA4, maxA4);
			double rGender = normalisedDouble(st.nextToken(), minA5, maxA5);
			double uR = normalisedDouble(st.nextToken(), minA6, maxA6);
			double vR = normalisedDouble(st.nextToken(), minA7, maxA7);
			double x1R = normalisedDouble(st.nextToken(), minA8, maxA8);
			double x2R = normalisedDouble(st.nextToken(), minA9, maxA9);
			double x3R = normalisedDouble(st.nextToken(), minA10, maxA10);
			double x4R = normalisedDouble(st.nextToken(), minA11, maxA11);


			double rModel = Double.parseDouble(st.nextToken());
			double tDist = totalSquaredDistance(normalisedRAge, normalisedRIncome, rStatus, rGender,
					normalisedRChildren, uR, vR, x1R, x2R, x3R, x4R, normalisedSA, normalisedSB, sS, sG, normalisedSC, uS, vS, x1, x2,x3,x4);		
			
			KnnMap.put(tDist,rModel);
			for (int j=0; j<4898; j++)
			{
				String rLine1 = value.toString();
				StringTokenizer st1 = new StringTokenizer(rLine1, ",");
				Text cl1 = new Text();
				double normalisedRAge1 = normalisedDouble(st1.nextToken(), minA1, maxA1);
				double normalisedRIncome1 = normalisedDouble(st1.nextToken(), minB2, maxB2);
				double normalisedRChildren1 = normalisedDouble(st1.nextToken(), minC3, maxC3);
				double rStatus1 = normalisedDouble(st1.nextToken(), minA4, maxA4);
				double rGender1 = normalisedDouble(st1.nextToken(), minA5, maxA5);
				double uR1 = normalisedDouble(st1.nextToken(), minA6, maxA6);
				double vR1 = normalisedDouble(st1.nextToken(), minA7, maxA7);
				double x1R1 = normalisedDouble(st1.nextToken(), minA8, maxA8);
				double x2R1 = normalisedDouble(st1.nextToken(), minA9, maxA9);
				double x3R1 = normalisedDouble(st1.nextToken(), minA10, maxA10);
				double x4R1 = normalisedDouble(st1.nextToken(), minA11, maxA11);
				double rModel1 = Double.parseDouble(st1.nextToken());
			DoubleDouble e = new DoubleDouble();
			int x = 5;
			// Using these row specific values and the unchanging S dataset values, calculate a total squared
			// distance between each pair of corresponding values.
			double tDist1 = totalSquaredDistance(normalisedRAge1, normalisedRIncome1, rStatus1, rGender1,
					normalisedRChildren1, uR1, vR1, x1R1, x2R1, x3R1, x4R1, normalisedSA, normalisedSB, sS, sG, normalisedSC, uS, vS,x1,x2,x3,x4);		
			
			// Add the total distance and corresponding car model for this row into the TreeMap with distance
			// as key and model as value.
			//for(int i=0; i<366; i++)
			//{ 
			KnnMap.put(tDist1,rModel1);
			if (KnnMap.size() > K)
			{
				KnnMap.remove(KnnMap.lastKey());
			}
			
			}
			if(KnnMap.size()>=K){
			for(int j=0; j<K; j++)
			//tree.add(e);
			//if(KnnMap.size()==K)
			{
					arr[i][j]= new DoubleDouble();
					
					arr[i][j].distance=KnnMap.firstKey(); 
					 
					arr[i][j].model=KnnMap.get(KnnMap.firstKey()); 
					
					KnnMap.remove(KnnMap.firstKey());
					System.out.println("entry added "+arr[i][j].distance + ", "+arr[i][j].model);
			count++;
			// Only K distances are required, so if the TreeMap contains over K entries, remove the last one
			// which will be the highest distance number.
			 context.write(one, arr[i][j]);
			}
			}
			System.out.println("KnnMap Size "+KnnMap.size()); 
			System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKK "+K+" , "+normalisedRAge+" , "+normalisedRIncome+"  , "+rStatus+" , "+rModel+" , "+uR+ " , "+vR+ " tDist "+tDist+" rModel "+rModel);
			//System.out.println("entry added "+tDist+ ", "+rModel);
			
			System.out.println("map counter "+count+" i "+i);
			i++;
			//KnnPattern.setArray(arr);
			
		}

		//@Override
		// The cleanup() method is run once after map() has run for every row
		protected void cleanup(Context context, Iterable<DoubleWritable> values) throws IOException, InterruptedException
		{
			int count=0;
			// Loop through the K key:values in the TreeMap
			//for(Map.Entry<Double, Double> entry : KnnMap.entrySet())
			//{\
		
			DoubleDouble ar[][] = KnnPattern.getArray();
			distanceAndModel = new DoubleDouble();
			//context.
			//for(int l=0; l< 4898; l++)
			//{
				//for(int m=0; m< K; m++)
			for(DoubleWritable v:values)
				{
				  //Double knnDist = entry.getKey();
				  //Double knnModel = entry.getValue();
				  // distanceAndModel is the instance of DoubleString declared earlier
					//System.out.println("distanceAndModel "+distanceAndModel.distance);
					System.out.println("arr "+v.get());
					//System.out.println("arr "+ar[0][0].getModel()); //.distance+ " "+ arr[2][2].model);
					//System.out.println("arr "+ar[0][0].distance+ " "+ ar[1][1].model);
				distanceAndModel.set(v.get(), v.get());//, ar[l][m].model);
				 
				  // Write to context a NullWritable as key and distanceAndModel as value
				  context.write(one, distanceAndModel);
		
				  System.out.println("Double writable "+one+"/////////////////////"+distanceAndModel);
				  System.out.println("MAP ");
				  System.out.print("knnDist "+ distanceAndModel.distance + " knnModel "+distanceAndModel.model);
				  //bw.write("knnDist "+ distanceAndModel.distance + " knnModel "+distanceAndModel.model);
				  //bw.write("Double writable "+one+"/////////////////////"+distanceAndModel);
				pw.write("knnDist "+ distanceAndModel.distance + " knnModel "+distanceAndModel.model);
				 pw.write("Double writable "+one+"/////////////////////"+distanceAndModel);
				count++;	 
					
				}	
				  //context.write(word, one);
			//}
			System.out.println("clean up in mapper CCCCOOONTTTEER " +count+"\n");
		}
	}

	// The reducer class accepts the NullWritable and DoubleString objects just supplied to context and
	// outputs a NullWritable and a Text object for the final classification.
	public static class KnnReducer extends Reducer<DoubleWritable, DoubleDouble, DoubleWritable, Text>
	{
		TreeMap<Double, Double> KnnMap = new TreeMap<Double, Double>();
		int K=5;
		
		@Override
		// setup() again is run before the main reduce() method
		protected void setup(Context context) throws IOException, InterruptedException
		{
			if (DistributedCache.getCacheFiles(context.getConfiguration()) != null && DistributedCache.getCacheFiles(context.getConfiguration()).length > 0)
			{
				// Read parameter file using alias established in main()
				String knnParams = FileUtils.readFileToString(new File("small.arff"));
				StringTokenizer st = new StringTokenizer(knnParams, ",");
				// Only K is needed from the parameter file by the reducer
				//K = Integer.parseInt(st.nextToken());
			}
		}
		
		//@Override
		// The reduce() method accepts the objects the mapper wrote to context: a NullWritable and a DoubleString
		public void reduce(DoubleWritable key, Iterable<DoubleDouble> values, Context context) throws IOException, InterruptedException
		{
			// values are the K DoubleString objects which the mapper wrote to context
			// Loop through these
			int count=0;
			for (DoubleDouble val : values)
			{
				double rModel = val.getModel();
				double tDist = val.getDistance();
				
				// Populate another TreeMap with the distance and model information extracted from the
				// DoubleString objects and trim it to size K as before.
				KnnMap.put(tDist, rModel);
				//if (KnnMap.size() > K)
				//{
				//	KnnMap.remove(KnnMap.lastKey());
				//}
				count++;
				System.out.println("ENTRY ADDED "+tDist+"   "+rModel+" count "+count+ " context "+context.getCurrentKey().get()+ " "+context.getCurrentValue().distance+" model "+context.getCurrentValue().model);
				
				
			}	

				// This section determines which of the K values (models) in the TreeMap occurs most frequently
				// by means of constructing an intermediate ArrayList and HashMap.

				// A List of all the values in the TreeMap.
			System.out.println("size of knnmap  "+KnnMap.size());
				List<Double> knnList = new ArrayList<Double>(KnnMap.values());

				Map<Double, Integer> freqMap = new HashMap<Double, Integer>();
			    
			    // Add the members of the list to the HashMap as keys and the number of times each occurs
			    // (frequency) as values
			    for(int i=0; i< knnList.size(); i++)
			    {  
			        Integer frequency = freqMap.get(knnList.get(i));
			        if(frequency == null)
			        {
			            freqMap.put(knnList.get(i), 1);
			        } else
			        {
			            freqMap.put(knnList.get(i), frequency+1);
			        }
			    }
			    
			    // Examine the HashMap to determine which key (model) has the highest value (frequency)
			    double mostCommonModel = 0.0;
			    int maxFrequency = -1;
			    for(Map.Entry<Double, Integer> entry: freqMap.entrySet())
			    {
			        if(entry.getValue() > maxFrequency)
			        {
			            mostCommonModel = entry.getKey();
			            maxFrequency = entry.getValue();
			        }
			    }
			    
			// Finally write to context another NullWritable as key and the most common model just counted as value.
			System.out.println("most common "+Double.toString(mostCommonModel)+"   "+mostCommonModel+"  max "+maxFrequency+"  "+Integer.toString(maxFrequency));
			System.out.println("most common "+Double.toString(mostCommonModel)+"   "+mostCommonModel+"  max "+maxFrequency);
			 
			System.out.println("most common "+Double.toString(mostCommonModel)+"   "+mostCommonModel+"  max "+maxFrequency);
			 
			System.out.println("most common "+Double.toString(mostCommonModel)+"   "+mostCommonModel+"  max "+maxFrequency);
			 
			System.out.println("most common "+Double.toString(mostCommonModel)+"   "+mostCommonModel+"  max "+maxFrequency);
			//bw.write("most common "+Double.toString(mostCommonModel)+"   "+mostCommonModel+"  max "+maxFrequency);
			//pw.write("most common "+Double.toString(mostCommonModel)+"   "+mostCommonModel+"  max "+maxFrequency);
			key.set(mostCommonModel);
			context.write(key, new Text(Integer.toString(maxFrequency))); // Use this line to produce a single classification
//			context.write(NullWritable.get(), new Text(KnnMap.toString()));	// Use this line to see all K nearest neighbours and distances
			context.write(key, new Text(KnnMap.toString()));
			context.write(key, new Text("VALUES////////\n"+KnnMap.values().toString()));
			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			System.out.println("Elapsed time in secs "+ elapsedSeconds);
			//bw.write("Elapsed time in secs "+ elapsedSeconds);
			//pw.write("Elapsed time in secs "+ elapsedSeconds);
			context.write(key, new Text("//////////\nElapsed time in secs "+ elapsedSeconds));
			
		}
	}

	// Main program to run: By calling MapReduce's 'job' API it configures and submits the MapReduce job.
	public static void main(String[] args) throws Exception
	{
		// Create configuration
		fw = new FileWriter( file.getName());
	      // bw = new BufferedWriter( fw );
		 pw = new PrintWriter(fw);	
	         bw = new BufferedWriter( fw );
		Configuration conf = new Configuration();
		if (args.length == 0)
		{
			System.out.println("Nada");
			//System.exit(2);
		}
		if (args.length == 1)
		{
			System.out.println("Usage: 1, "+args[0].toString());
			//System.exit(2);
		}
		if (args.length == 2)
		{
			System.out.println("Usage: 2, "+args[1].toString());
			//System.exit(2);
		}
		if (args.length == 3)
		{
			System.out.println("Usage: 3, "+args[2].toString());
			//System.exit(2);
		}
		
		// writer = new PrintWriter(args[2]+"stat.txt", "UTF-8");
		//File file = new File("gs://knnhadoop/st.txt"); //KEY IS DIR ex."./local-storage/" and fileName='comp.html'

       // bw.write( text );	    
         String n1="gs:/"; String n2="/knnhadoop/s.txt";
	//	if (args.length != 3)
		//{
			//System.err.println("Usage: KnnPattern <in> <out> <parameter file>");
			//System.exit(2);
		//}
		//System.setProperty("hadoop.home.dir", "C:/hadoop-3.0.0-alpha1");
		// hadoopConfig.set("fs.hdfs.impl", 
		//	        org.apache.hadoop.hdfs.DistributedFileSystem.class.getName()
			//    );
			//    hadoopConfig.set("fs.file.impl",
			//        org.apache.hadoop.fs.LocalFileSystem.class.getName()
			//    );
    	
    		
		// Create job
		Job job = Job.getInstance(conf, "Find K-Nearest Neighbour");
		job.setJarByClass(KnnPattern.class);
		// Set the third parameter when running the job to be the parameter file and give it an alias
		// Setup MapReduce job
		job.setMapperClass(KnnMapper.class);
		job.setReducerClass(KnnReducer.class);
		job.setNumReduceTasks(1); // Only one reducer in this design
	//	job.addCacheFile(new URI(args[2] + "#knnParamFile")); // Parameter file containing test data
		
		DistributedCache.addCacheFile(new URI(args[0] + "#knnParamFile"), conf);
		// Specify key / value
		job.setMapOutputKeyClass(DoubleWritable.class);
		job.setMapOutputValueClass(DoubleDouble.class);
		job.setOutputKeyClass(DoubleWritable.class);
		job.setOutputValueClass(Text.class);
				
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
	
		// Input (the data file) and Output (the resulting classification)
		// Execute job and return status
	    //bw.close();
			
		pw.close();
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	
		
	}
}
