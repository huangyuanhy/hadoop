/**
 * 
 */
package selfdefinitioninputformat;

import java.io.IOException;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月6日下午12:42:19
 * @Description
 */
public class SequenceReducer extends Reducer<Text, BytesWritable, Text, BytesWritable>{
/* (non-Javadoc)
 * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
 */
@Override
protected void reduce(Text key, Iterable<BytesWritable> values,
		Reducer<Text, BytesWritable, Text, BytesWritable>.Context context) throws IOException, InterruptedException {
	for (BytesWritable v : values) {
		context.write(key, v);
	}
}
}
