/**
 * 
 */
package selfdefinitioninputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import kv_inputformat.KVDriver;
import kv_inputformat.KVMapper;
import kv_inputformat.KVReducer;

/**
 * @author huangyuan
 * @date 2018年11月6日下午1:14:11
 * @Description
 */
public class SequenceDriver {

	/**
	 * @param args
	 * @Description TODO
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		args=new String[] {"E:\\kv_test.txt",
		"E:\\out.txt"};

		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		//设置分隔符，很重要
		 
		Job job = Job.getInstance(configuration);

		job.setJarByClass(SequenceDriver.class);

		job.setMapperClass(SequenceMapper.class);
		job.setReducerClass(SequenceReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(BytesWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(BytesWritable.class);
		//设置输入的格式
		job.setInputFormatClass(SelfDefenitionInputFormat.class);
		//设置输出的格式
		job.setOutputFormatClass(SequenceFileOutputFormat.class);

		FileInputFormat.setInputPaths(job, new Path(args[0])); 
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);

	}

}
