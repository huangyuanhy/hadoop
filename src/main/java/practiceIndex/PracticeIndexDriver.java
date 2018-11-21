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
 * @date 2018年11月19日上午11:17:53
 * @Description
 * 第一次输出的结果为：
 * atguigu--a.txt	3
atguigu--b.txt	3
atguigu--c.txt	2
pingping--a.txt	1
pingping--b.txt	2
pingping--c.txt	1
ss--a.txt	2
ss--b.txt	1
ss--c.txt	1
 */
public class PracticeIndexDriver {

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
		job.setMapperClass(PracticeIndexMapper.class);
		job.setReducerClass(PracticeIndexReduce.class);
		
		job.setJarByClass(PracticeIndexDriver.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		//提交job
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
	}

}
