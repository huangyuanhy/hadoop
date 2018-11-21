/**
 * 
 */
package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author huangyuan
 * @date 2018年11月2日下午7:23:00
 * @Description
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapreduce.Mapper.Context)
	 */
	Text k=new Text();

	IntWritable v = new IntWritable(1);
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] words = line.trim().split(" ");
		for(String w:words) {
			k.set(w);		
			context.write(k, v);
		}
	}
}












