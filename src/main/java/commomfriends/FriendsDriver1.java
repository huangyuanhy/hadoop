/**
 * 
 */
package commomfriends;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author huangyuan
 * @date 2018年11月19日下午7:24:43
 * @Description
 */
public class FriendsDriver1 {
	public static void main(String[] args) throws Exception{
		args=new String[] {"E:\\test",
		"E:\\out"};
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();

		Job job = Job.getInstance(configuration);

		job.setJarByClass(FriendsDriver1.class);

		job.setMapperClass(FriendsMapper1.class);
		job.setReducerClass(FriendsReducer1.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
	 
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		//设置分组排序
		job.setGroupingComparatorClass(GroupSort.class);
		
		FileInputFormat.setInputPaths(job, new Path(args[0])); 
		 
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		 
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
	}

}
