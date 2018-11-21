/**
 * 
 */
package reducejoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
 

/**
 * @author huangyuan
 * @date 2018年11月8日下午8:16:52
 * @Description
 */
public class JoinDriver {
public static void main(String[] args) throws Exception{
	args=new String[] {"E:\\input",
	"E:\\out"};

	// TODO Auto-generated method stub
	Configuration configuration=new Configuration();

	Job job = Job.getInstance(configuration);

	job.setJarByClass(JoinDriver.class);

	job.setMapperClass(JoinMapper.class);
	job.setReducerClass(JoinReducer.class);

	job.setMapOutputKeyClass(Text.class);
	job.setMapOutputValueClass(TableBean.class);

	job.setOutputKeyClass(TableBean.class);
	job.setOutputValueClass(NullWritable.class);
	 
	 
	FileInputFormat.setInputPaths(job, new Path(args[0])); 
	 
	FileOutputFormat.setOutputPath(job, new Path(args[1]));

	boolean result = job.waitForCompletion(true);
	System.exit(result?0:1);
}
}
