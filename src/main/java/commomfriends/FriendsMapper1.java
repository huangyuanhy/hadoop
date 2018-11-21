/**
 * 
 */
package commomfriends;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author huangyuan
 * @date 2018年11月19日下午4:27:45
 * @Description
 */
public class FriendsMapper1 extends Mapper<LongWritable, Text, Text, Text>{
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		Text k = new Text();
		Text v=new Text();
		LinkedList<String> list = new LinkedList<>();
		//				A	I k C B G F H O D 
		String[] split = value.toString().split("\t");
		String[] person = split[1].split(" ");
		for (int i = 0; i < person.length-1; i++) {
			for (int j = i+1; j < person.length; j++) {
				//I-k,I-C 等两两组合放入集合中
				list.add(person[i]+"-"+person[j]);
			}
		}
		//将集合中值循环写出 I-k	A
		for (String s : list) {
			k.set(s);
			v.set(split[0]);
			context.write(k, v);
		}
	}
}