/**
 * 
 */
package serilizableBeanAndPartion;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月4日下午9:58:54
 * @Description
 */
public class BeanSerilizableReducer extends Reducer<Text, BeanSeriliazble, Text, BeanSeriliazble>{
	BeanSeriliazble v = new BeanSeriliazble();
	long downFlow=0;
	long upFlow =0;
	@Override
	protected void reduce(Text k, Iterable<BeanSeriliazble> vs,
			Reducer<Text, BeanSeriliazble, Text, BeanSeriliazble>.Context context)
			throws IOException, InterruptedException {
		for (BeanSeriliazble tmp : vs) {
			downFlow+= tmp.getDownFlow();
			upFlow+= tmp.getUpFlow();
			
		}
		v.setDownFlow(downFlow);
		v.setUpFlow(upFlow);
		v.setSumFlow(upFlow+downFlow);
		context.write(k, v);
	}

}
