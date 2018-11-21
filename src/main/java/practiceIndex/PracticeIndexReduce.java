/**
 * 
 */
package practiceIndex;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月19日上午10:59:19
 * @Description
 */
public class PracticeIndexReduce extends Reducer <Text, IntWritable, Text , IntWritable>{
IntWritable v=new IntWritable();
@Override
protected void reduce(Text key, Iterable<IntWritable> vlaues,
		Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
	//累加求和
	int sum=0;
	for (IntWritable v : vlaues) {
		sum+=v.get();
	}
	v.set(sum);
	context.write(key, v);
}
}
