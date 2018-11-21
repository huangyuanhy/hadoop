/**
 * 
 */
package mapjoin;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import reducejoin.JoinMapper;

/**
 * @author huangyuan
 * @date 2018年11月12日上午10:03:06
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
	//没有设置mapper输出，mapper的输出即是最后输出格式
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(NullWritable.class);
	 
	 
	FileInputFormat.setInputPaths(job, new Path(args[0])); 
	 
	FileOutputFormat.setOutputPath(job, new Path(args[1]));
	//加载缓存
	job.addCacheFile(new URI("file:///e:pd.text"));
	//不需要reduce
	job.setNumReduceTasks(0);
	boolean result = job.waitForCompletion(true);
	System.exit(result?0:1);
}
}

