import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class LogCount {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
Configuration conf = new Configuration();
		
		Job job = new Job(conf,"logfile");
	    
	   
		job.setJarByClass(LogCount.class);
	    job.setMapperClass(MapLog.class);
	    job.setReducerClass(ReduceLog.class);
	 	
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	 	
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	 	
	    System.exit(job.waitForCompletion(true)?0:1);


	}

}