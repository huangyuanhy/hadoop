/**
 * 
 */
package kv_inputformat;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月5日下午4:22:07
 * @Description
 */
public class KVReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	IntWritable v=new IntWritable();
	@Override
	protected void reduce(Text k, Iterable<IntWritable> vs,
			Reducer<Text, IntWritable, Text, IntWritable>.Context c) throws IOException, InterruptedException {
		int sum=0;
		for (IntWritable tmp : vs) {
			sum+=tmp.get();

		}
		v.set(sum);
		c.write(k, v);
	}

}
