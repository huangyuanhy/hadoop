/**
 * 
 */
package commomfriends2;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月19日下午4:11:40
 * @Description
 */
public class FriendsReducer extends Reducer<Text, Text, FriendsBean, Text>{
/* (non-Javadoc)
 * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
 */
@Override
protected void reduce(Text key, Iterable<Text> vs, Reducer<Text, Text, FriendsBean, Text>.Context context)
		throws IOException, InterruptedException {
	StringBuilder stringBuilder=new StringBuilder();
	//A B C 	A B	E 
	String[] split = key.toString().split(" ");
	
	FriendsBean k = new FriendsBean();
	k.setA(split[0]);
	k.setB(split[1]);
	
	Text v = new Text();
	
	for (Text text : vs) {
		stringBuilder.append(text.toString()+" ");
	}
	v.set(stringBuilder.toString());
	context.write(k, v);
}
}
