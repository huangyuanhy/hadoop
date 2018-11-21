/**
 * 
 */
package selfdefinitioninputformat;

import java.io.IOException;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author huangyuan
 * @date 2018年11月6日下午12:40:44
 * @Description
 */
public class SequenceMapper extends Mapper<Text, BytesWritable, Text, BytesWritable>{
/* (non-Javadoc)
 * @see org.apache.hadoop.mapreduce.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapreduce.Mapper.Context)
 */
@Override
protected void map(Text key, BytesWritable value, Mapper<Text, BytesWritable, Text, BytesWritable>.Context context)
		throws IOException, InterruptedException {
	context.write(key, value);
}
}
