/**
 * 
 */
package sort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author huangyuan
 * @date 2018年11月7日下午2:31:48
 * @Description 
 * 测试用例的来源是上次beandriver 的结果  12128799077	1023	2426	3449
 * 输出的key 是流量，因为要对流量排序， value 是手机号 
 */
public class FlowMapper extends Mapper<LongWritable, Text, FlowBean,Text>{
	Text v = new Text();
	FlowBean k = new FlowBean();
/* (non-Javadoc)
 * @see org.apache.hadoop.mapreduce.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapreduce.Mapper.Context)
 */
@Override
protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, FlowBean, Text>.Context context)
		throws IOException, InterruptedException {
	String[] f = value.toString().split("\t");
	v.set(f[0]);
	
	long up=Long.parseLong(f[1]);
	long down=Long.parseLong(f[2]);
	long  sum=Long.parseLong(f[3]);
	k.setDownFlow(down); 
	k.setUpFlow(up);
	k.setSumFlow(sum);
	context.write(k, v);
}
}
