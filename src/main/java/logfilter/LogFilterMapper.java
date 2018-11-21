/**
 * 
 */
package logfilter;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author huangyuan
 * @date 2018年11月12日上午10:53:37
 * @Description 日志数据清洗，过滤掉每行的长度小于10的数据
 */
public class LogFilterMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapreduce.Mapper.Context)
	 */
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		
		boolean res=parseLog(line,context);
		if (!res) {
			return;
		}else {
			context.write(value, NullWritable.get());
		}
	}
	boolean parseLog(String line,Context context) {
		String[] split = line.split(" ");
		if (split.length>10) {
			//引用计数器的使用，可以在控制台打印出来
			context.getCounter("map", "true").increment(1);
			return true;
		}else {
			context.getCounter("map", "false").increment(1);

			return false;
		}
	}

}
