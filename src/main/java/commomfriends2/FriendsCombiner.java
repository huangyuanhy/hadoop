/**
 * 
 */
package commomfriends2;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月19日下午7:57:59
 * @Description
 * 去除没有重复好友的输出，去重用户组合拼接
 */
public class FriendsCombiner extends Reducer<Text, Text, Text, Text>{
/* (non-Javadoc)
 * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
 */
@Override
protected void reduce(Text key, Iterable<Text> vs, Reducer<Text, Text, Text, Text>.Context context)
		throws IOException, InterruptedException {
	LinkedList<String> list = new LinkedList<>();
	
	Text k = new Text();
	Text val = new Text();
	StringBuilder stringBuilder=new StringBuilder();
	//C A	C B 
	for (Text text : vs) {
		list.add(text.toString());
	}
	Collections.sort(list);
	if (list.size()>=2) {
		val.set(key);//C
		for (int i = 0; i < list.size()-1; i++) {
			for (int j =i+1 ; j < list.size(); j++) {
				stringBuilder.append(list.get(i)+" "+list.get(j));
				k.set(stringBuilder.toString());
				context.write(k, val);
			}
		}
		
	}

}
}
