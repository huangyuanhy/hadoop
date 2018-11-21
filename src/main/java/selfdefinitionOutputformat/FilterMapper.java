/**
 * 
 */
package selfdefinitionOutputformat;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author huangyuan
 * @date 2018年11月8日下午2:59:20
 * @Description
 */
public class FilterMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
/* (non-Javadoc)
 * @see org.apache.hadoop.mapreduce.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapreduce.Mapper.Context)
 */
@Override
protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
		throws IOException, InterruptedException {
	// www.baidu.com
	//直接将text 作为value 写出
	context.write(value, NullWritable.get());
}
}
