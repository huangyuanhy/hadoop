/**
 * 
 */
package commomfriends;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月19日下午8:30:52
 * @Description
 */
public class FriendsReducer1 extends Reducer<Text, Text, Text, Text>{
/* (non-Javadoc)
 * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
 */
@Override
protected void reduce(Text key, Iterable<Text> vs, Reducer<Text, Text, Text, Text>.Context context)
		throws IOException, InterruptedException {
	Text val = new Text();
	StringBuilder stringBuilder=new StringBuilder();
	//  A-B E
	//  B-A D 
	for (Text text : vs) {
		stringBuilder.append(text.toString()+" ");
	}
	val.set(stringBuilder.toString());
	context.write(key, val);
}
}
