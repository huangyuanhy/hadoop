/**
 * 
 */
package commomfriends;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月19日下午4:11:40
 * @Description
 */
public class FriendsReducer extends Reducer<Text, Text, Text, Text>{
/* (non-Javadoc)
 * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
 */
@Override
protected void reduce(Text k, Iterable<Text> vs, Reducer<Text, Text, Text, Text>.Context context)
		throws IOException, InterruptedException {
	StringBuilder stringBuilder=new StringBuilder();
	Text v = new Text();
	//C-->A  C-->B
	for (Text text : vs) {
		stringBuilder.append(text.toString()+" ");
	}
	v.set(stringBuilder.toString());
	context.write(k, v);
}
}
