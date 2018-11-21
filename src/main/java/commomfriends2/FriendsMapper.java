/**
 * 
 */
package commomfriends2;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 * @author huangyuan
 * @date 2018年11月19日下午3:24:24
 * @Description
 */
public class FriendsMapper extends Mapper<LongWritable	, Text, Text, Text>{
	
@Override
protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
		throws IOException, InterruptedException {
	// A:B,C,D,F,E,O
//	B:A,C,E,K
	Text K = new Text();
	Text V=new Text();
	String[] split = value.toString().split(":");
	 
	String[] s2 = split[1].split(",");//B C D F E O
	 
	for (String string : s2) {
		//B-->A C-->A
		 K.set(string);
		 V.set(split[0]);
		 context.write(K, V);
	}
	
}
}
