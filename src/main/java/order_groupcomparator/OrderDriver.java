/**
 * 
 */
package order_groupcomparator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * @author huangyuan
 * @date 2018年11月7日下午5:18:58
 * @Description
 * 在reduce阶段根据某一个或者几个字段进行分组
 */
public class OrderDriver {
public static void main(String[] args) throws Exception{
	args=new String[] {"E:\\order_test.txt",
	"E:\\out1"};

	Configuration configuration = new Configuration();
	//获取Job对象
	Job job = Job.getInstance(configuration);
	//设置jar位置
	job.setJarByClass(OrderDriver.class);
	//关联map和reduce类
	job.setMapperClass(OrderMapper.class);
	job.setReducerClass(OrderReducer.class); 
	//设置mapper阶段输出阶段的key value 类型
	job.setMapOutputKeyClass(Orderbean.class);
	job.setMapOutputValueClass(NullWritable.class);
	
	//设置最终数据输出的 key value 类型
	job.setOutputKeyClass(Orderbean.class);
	job.setOutputValueClass(NullWritable.class);
	
	//设置分组排序
	job.setGroupingComparatorClass(OrderComparator.class);
	 
	//设置输入路径和输出路径
	FileInputFormat.setInputPaths(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));
	//提交job
	boolean result = job.waitForCompletion(true);
	System.exit(result?0:1);
}
}
