/**
 * 
 */
package practiceIndex;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月19日下午12:57:56
 * @Description
 */
public class PracticeIndex2Reducer extends Reducer<Text, Text, Text, Text>{
	Text v = new Text();
	
@Override
protected void reduce(Text k, Iterable<Text> vs, Reducer<Text, Text, Text, Text>.Context contex)
		throws IOException, InterruptedException {
//	atguigu--a.txt	3
//	         b.txt	3
//	         c.txt	2
//  atguigu c.text-->2	b.text-->3	c.text-->3
	StringBuilder s=new StringBuilder();
	for (Text text : vs) {
		s.append(text.toString().replace("\t", "-->")+"\t");
	}
	v.set(s.toString());
	contex.write(k, v);
	
}
}
