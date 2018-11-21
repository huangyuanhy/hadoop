/**
 * 
 */
package selfdefinitionOutputformat;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月8日下午3:02:36
 * @Description
 */
public class FilterReducer extends Reducer<Text, NullWritable, Text, NullWritable>{
Text key=new Text();
@Override
protected void reduce(Text k, Iterable<NullWritable> vs,
		Reducer<Text, NullWritable, Text, NullWritable>.Context context ) throws IOException, InterruptedException {
	// TODO Auto-generated method stub
	String line = k.toString();
	line=line+"\r\n";
	key.set(line);
	for (NullWritable nullWritable : vs) {
		context.write(key, nullWritable);//循环写出，防止有重复的
	}
}
}
