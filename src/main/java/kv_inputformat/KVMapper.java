/**
 * 
 */
package kv_inputformat;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author huangyuan
 * @date 2018年11月5日下午4:17:09
 * @Description 处理key value 形式
 * banzhang ni hao 
 * bangzhang zui shuai
 * banhua xi huan ni 
 *  处理结果： bangzhang 2 banhua 1
 */
public class KVMapper extends Mapper<Text, Text, Text, IntWritable>{
	IntWritable v=new IntWritable(1);
	@Override
	protected void map(Text key, Text value, Mapper<Text, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//封装
		 
		//写出 banzhang 1 
		context.write(key, v);
	}

}
