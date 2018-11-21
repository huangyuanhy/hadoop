/**
 * 
 */
package sort;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月7日下午2:48:10
 * @Description 输出的key 是手机号， value 是流量
 */
public class FlowReducer extends Reducer<FlowBean, Text, Text, FlowBean>{
	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
	 */
	@Override
	protected void reduce(FlowBean k, Iterable<Text> vs, Reducer<FlowBean, Text, Text, FlowBean>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		for (Text text : vs) {
			context.write(text, k);
		}
	}
}
