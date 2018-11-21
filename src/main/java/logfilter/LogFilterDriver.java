/**
 * 
 */
package logfilter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import reducejoin.JoinDriver;
import reducejoin.JoinMapper;
import reducejoin.JoinReducer;
import reducejoin.TableBean;

/**
 * @author huangyuan
 * @date 2018年11月12日上午11:05:35
 * @Description
 */
public class LogFilterDriver {
public static void main(String[] args) throws Exception{
	args=new String[] {"E:\\input",
	"E:\\out"};

	// TODO Auto-generated method stub
	Configuration configuration=new Configuration();

	Job job = Job.getInstance(configuration);

	job.setJarByClass(LogFilterDriver.class);

	job.setMapperClass(LogFilterMapper.class);
	 
	 
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(NullWritable.class);
	
	job.setNumReduceTasks(0);//没有reduce阶段
	 
	FileInputFormat.setInputPaths(job, new Path(args[0])); 
	 
	FileOutputFormat.setOutputPath(job, new Path(args[1]));

	boolean result = job.waitForCompletion(true);
	System.exit(result?0:1);
}
}
