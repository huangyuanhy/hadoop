/**
 * 
 */
package practiceIndex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author huangyuan
 * @date 2018年11月19日下午1:10:22
 * @Description
 */
public class PracticeIndex2Driver {

	/**
	 * @param args
	 * @Description TODO
	 */
	public static void main(String[] args) throws Exception{
		args=new String[] {"E:\\test",
		"E:\\out"};
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setMapperClass(PracticeIndex2Mapper.class);
		job.setReducerClass(PracticeIndex2Reducer.class);
		
		job.setJarByClass(PracticeIndex2Driver.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		//提交job
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);

	}

}
