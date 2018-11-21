/**
 * 
 */
package practiceIndex;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author huangyuan
 * @date 2018年11月19日下午12:51:40
 * @Description
 */
public class PracticeIndex2Mapper extends Mapper<LongWritable, Text, Text, Text>{
	Text k = new Text();
	Text v= new Text();
@Override
protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
		throws IOException, InterruptedException {
//	atguigu--a.txt	3
//	atguigu--b.txt	3
//	atguigu--c.txt	2

	String[] split = value.toString().split("--");
	k.set(split[0]);
	v.set(split[1]);
	context.write(k, v);
}
}
