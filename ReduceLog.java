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
	
public class ReduceLog extends Reducer<Text, IntWritable, Text, IntWritable> {
	int maxCount = Integer.MIN_VALUE;
	Text maxUser = new Text();
	public void reduce(Text key, Iterable<IntWritable> values, Context context) 
		      throws IOException, InterruptedException {
		        
		        int sum = 0;
		        
		 	for (IntWritable val : values) {
		 	    sum += val.get();
		 	
		 	}
		 	 if (sum > maxCount) {
		            maxCount = sum;
		            maxUser.set(key); // Set the key (IP address) with the maximum count
		        }}
		 	@Override
		    protected void cleanup(Context context) throws IOException, InterruptedException {
		        // Output the IP address with the maximum count after all reductions are done
		        context.write(maxUser, new IntWritable(maxCount));
		    }


}