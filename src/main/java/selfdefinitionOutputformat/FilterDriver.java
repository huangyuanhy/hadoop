/**
 * 
 */
package selfdefinitionOutputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * @author huangyuan
 * @date 2018年11月8日下午3:58:58
 * @Description
 */
public class FilterDriver {
public static void main(String[] args) throws Exception{
	args=new String[] {"E:\\log.log",
	"E:\\out"};

	// TODO Auto-generated method stub
	Configuration configuration=new Configuration();

	Job job = Job.getInstance(configuration);

	job.setJarByClass(FilterDriver.class);

	job.setMapperClass(FilterMapper.class);
	job.setReducerClass(FilterReducer.class);

	job.setMapOutputKeyClass(Text.class);
	job.setMapOutputValueClass(NullWritable.class);

	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(NullWritable.class);
	 
	//设置输出的格式
	job.setOutputFormatClass(SelfDefinitonOuputformat.class);

	FileInputFormat.setInputPaths(job, new Path(args[0])); 
	
	//虽然自定义了outputformat 但是因为其继承于fileoutputformat,
	//而fileoutputformat需要输出一个_SUCCESS文件，所以还是得指定
	FileOutputFormat.setOutputPath(job, new Path(args[1]));

	boolean result = job.waitForCompletion(true);
	System.exit(result?0:1);
}
}
