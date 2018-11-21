/**
 * 
 */
package serilizableBeanAndPartion;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author huangyuan
 * @date 2018年11月4日下午9:42:46
 * @Description
 */
public class BeanSeriliazbleMapper extends Mapper<LongWritable, Text, Text, BeanSeriliazble>{
	Text k = new Text();
	BeanSeriliazble v = new BeanSeriliazble();
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// 1 12432434 168.192.1.11 100 200 300
		String line = value.toString();
		String[] f = line.split("\t");
		k.set(f[1]);
		long up=Long.parseLong(f[f.length-3]);
		long down=Long.parseLong(f[f.length-2]);
		v.setDownFlow(down);
		v.setUpFlow(up);
		
		context.write(k, v);
	}

}
