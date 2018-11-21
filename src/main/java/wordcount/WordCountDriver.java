/**
 * 
 */
package wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapred.lib.CombineFileInputFormat;
import org.apache.hadoop.mapred.lib.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author huangyuan
 * @date 2018年11月2日下午8:00:55
 * @Description
 */
public class WordCountDriver {

	/**
	 * @param args
	 * @Description TODO
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		
		//开启map端的输出压缩
		configuration.setBoolean("mapreduce.map.output.compress", true);
		//设置压缩方式
		configuration.setClass("mapreduce.map.output.compress", BZip2Codec.class, CompressionCodec.class);
		
		//获取Job对象
		Job job = Job.getInstance(configuration);
		//设置jar位置
		job.setJarByClass(WordCountDriver.class);
		//关联map和reduce类
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class); 
		//设置mapper阶段输出阶段的key value 类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//设置最终数据输出的 key value 类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//如果不设置inputformat 默认的采用textinputformat
		//job.setInputFormatClass(CombineFileInputFormat.class);
		//虚拟存储切片最大值设置为4M
		//CombineTextInputFormat.setMaxInputSplitSize(job, 4*1024*1024);
		
		//job.setInputFormatClass(NLineInputFormat.class);
		//每三行为一个切片
		//NLineInputFormat.setNumLinesPerSplit(job, 3);
		
		//设置分区 即最后形成多少个文件
		// job.setNumReduceTasks(2);
		
		//关联combiner  当然也可以直接把WordCountReducer 设置为CombinerClass
		//job.setCombinerClass(WordCountCombiner.class);
		
		//开启reduce端输出压缩
		FileOutputFormat.setCompressOutput(job, true);
		//设置压缩的方式
		FileOutputFormat.setOutputCompressorClass(job, BZip2Codec.class);		
		//设置输入路径和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		//提交job
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
		 
	}

}
