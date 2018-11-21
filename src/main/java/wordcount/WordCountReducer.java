/**
 * 
 */
package wordcount;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月2日下午7:44:02
 * @Description
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
/* (non-Javadoc)
 * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
 */
@Override
protected void reduce(Text k, Iterable<IntWritable> vlaues,
		Context context) throws IOException, InterruptedException {
	int sum=0;
	for (IntWritable value : vlaues) {
		sum+=value.get();
	}
	IntWritable v = new IntWritable(sum);
	context.write(k, v);
}
}
