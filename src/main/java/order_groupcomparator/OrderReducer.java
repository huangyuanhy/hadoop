/**
 * 
 */
package order_groupcomparator;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月7日下午5:17:33
 * @Description 直接写出
 */
public class OrderReducer extends Reducer<Orderbean, NullWritable, Orderbean, NullWritable>{
/* (non-Javadoc)
 * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
 */
@Override
protected void reduce(Orderbean k, Iterable<NullWritable> vs,
		Reducer<Orderbean, NullWritable, Orderbean, NullWritable>.Context context )
		throws IOException, InterruptedException {
	//要想输出前几位，就让context循环输出几次就行，
	//例如 只输出第一个，则直接写出就行，因为每一个Key都会调用一次reduceTask,而又是按序输出，
	Iterator<NullWritable> iterator = vs.iterator();
	int num=2;//输出前2位
		for (int i = 0; i < num; i++) {			 
			 NullWritable v = iterator.next();
			 context.write(k, v);//
		}
			
	
	
}
}
