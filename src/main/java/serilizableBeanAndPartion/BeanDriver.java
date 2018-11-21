/**
 * 
 */
package serilizableBeanAndPartion;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * @author huangyuan
 * @date 2018年11月5日上 午9:49:53
 * @Description 
 * 1.实现了自定义的序列化机制，key 为手机号， value 为 上行流量和下行流量
 * 2.手机号为155 137 158 开头的放到一个独立的文件中，其他的开头的放在
 * 另外一个文件中 
 */
public class BeanDriver {

	/**
	 * @param args
	 * @Description TODO
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		args=new String[] {"E:\\phone_test.txt",
		"E:\\out"};
		
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		
		job.setJarByClass(BeanDriver.class);
		
		job.setMapperClass(BeanSeriliazbleMapper.class);
		job.setReducerClass(BeanSerilizableReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(BeanSeriliazble.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(BeanSeriliazble.class);
		
		//关联自定义的分区类
		//job.setPartitionerClass(PartionDefiniton.class);
		//设置分区 如果为1 正常 如果为 2 3 则io异常，如果大于4 ，比如5 结果会多出一个空文件
		//job.setNumReduceTasks(4);
		
		FileInputFormat.setInputPaths(job, new  Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
				
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
	}

}
