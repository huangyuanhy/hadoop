/**
 * 
 */
package practiceIndex;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

/**
 * @author huangyuan
 * @date 2018年11月19日上午10:38:46
 * @Description
 * 倒排索引案例
 * 最后输出结果 ：atguigu--a.txt 3 b.text 2 c.text 1
 */
public class PracticeIndexMapper extends Mapper<LongWritable, Text, Text , IntWritable>{
	String name ;
@Override
protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
		throws IOException, InterruptedException {
	FileSplit fs =(FileSplit) context.getInputSplit();
	name= fs.getPath().getName();
}
Text k = new Text();
IntWritable v = new IntWritable(1);
@Override
protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
		throws IOException, InterruptedException {
	//atguigu pingping
	String[] split = value.toString().split(" ");
	for (String string : split) {
		//以单词和文件名称作为key
		k.set(string+"--"+name);//atguigu--a.text pingping--a.text
		context.write(k, v);//写出
	}
}
}
