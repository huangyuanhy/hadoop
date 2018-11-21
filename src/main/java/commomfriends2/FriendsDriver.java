/**
 * 
 */
package commomfriends2;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * @author huangyuan
 * @date 2018年11月19日下午4:16:32
 * @Description 寻找共同好友，第一次结果为XX被哪些人关注 了
 * 输出结果如下
 * A	I k C B G F H O D 
	B	A F J E 

 */
public class FriendsDriver {

	/**
	 * @param args
	 * @Description TODO
	 */
	public static void main(String[] args) throws Exception{
		args=new String[] {"E:\\test",
		"E:\\out"};
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();

		Job job = Job.getInstance(configuration);

		job.setJarByClass(FriendsDriver.class);

		job.setMapperClass(FriendsMapper.class);
		job.setReducerClass(FriendsReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(FriendsBean.class);
		job.setOutputValueClass(Text.class);
		 
		job.setGroupingComparatorClass(GroupSort.class);
		job.setCombinerClass(FriendsCombiner.class);
		 
		FileInputFormat.setInputPaths(job, new Path(args[0])); 
		 
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		 
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
	}

}
