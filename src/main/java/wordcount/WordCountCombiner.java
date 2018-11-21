/**
 * 
 */
package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月7日下午4:41:26
 * @Description Combiner用于每一个maptask节点 结束后调用 汇总，减少网络传输
 */
public class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable>{
	protected void reduce(Text k, Iterable<IntWritable> vlaues,
			Context context) throws IOException, InterruptedException {
		int sum=0;
		//累加求和
		for (IntWritable value : vlaues) {
			sum+=value.get();
		}
		IntWritable v = new IntWritable(sum);
		//写出
		context.write(k, v);
	}
}
