/**
 * 
 */
package kv_inputformat;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author huangyuan
 * @date 2018年11月5日下午4:31:31
 * @Description
 */
public class KVDriver {

	/**
	 * @param args
	 * @Description TODO
	 */
	public static void main(String[] args) throws Exception{
		args=new String[] {"E:\\kv_test.txt",
				"E:\\out.txt"};
		
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		//设置分隔符，很重要
		configuration.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR," ");
		Job job = Job.getInstance(configuration);
		
		job.setJarByClass(KVDriver.class);
		
		job.setMapperClass(KVMapper.class);
		job.setReducerClass(KVReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//设置输入的格式
		job.setInputFormatClass(KeyValueTextInputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path(args[0])); 
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
			
	}

}
