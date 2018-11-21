/**
 * 
 */
package selfdefinitionOutputformat;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author huangyuan
 * @date 2018年11月8日下午2:49:44
 * @Description 过滤输入的log文件，包含baidu的网站输出到e:\\baidu.log，
 * 其他的输出到e"\\other.log
 */    
public class SelfDefinitonOuputformat extends FileOutputFormat<Text,NullWritable>{

	@Override
	public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return new FilterRecordWriter(context);
	}

	
}
