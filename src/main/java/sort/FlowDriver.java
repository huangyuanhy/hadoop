/**
 * 
 */
package sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author huangyuan
 * @date 2018年11月7日下午2:52:12
 * @Description
 *  12128799077	1023	2426	3449
 */
public class FlowDriver {
	public static void main(String[] args) throws Exception{
		args=new String[] {"E:\\part-r-00000",
		"E:\\out"};

		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);

		job.setJarByClass(FlowDriver.class);

		job.setMapperClass(FlowMapper.class);
		job.setReducerClass(FlowReducer.class);

		job.setMapOutputKeyClass(FlowBean.class);
		job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);
		//关联自定义的分区类
		 job.setPartitionerClass(FlowPartionDefiniton.class);
		 
		 job.setNumReduceTasks(3);

		FileInputFormat.setInputPaths(job, new  Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
	}
}
