/**
 * 
 */
package selfdefinitioninputformat;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

/**
 * @author huangyuan
 * @date 2018年11月6日上午11:05:31
 * @Description 实现一次读取一个文件，而不是一行，key 为路径+文件名 ;value 为文件内容
 */
public class SelfDefenitionInputFormat extends InputFormat<Text, BytesWritable>{

	@Override
	public List<InputSplit> getSplits(JobContext context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecordReader<Text, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WholeRecordReader recorder=new WholeRecordReader();
		recorder.initialize(split, context);
		return recorder;
	}

}
