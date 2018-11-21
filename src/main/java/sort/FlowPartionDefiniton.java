/**
 * 
 */
package sort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author huangyuan
 * @date 2018年11月6日下午3:33:23
 * @Description k 为流量 v手机号 是mapper的输出类型
 * 根据手机号的前3位进行分区，放在不同文件夹中 
 */
public class FlowPartionDefiniton extends Partitioner<FlowBean,Text>{
/* (non-Javadoc)
 * @see org.apache.hadoop.mapreduce.Partitioner#getPartition(java.lang.Object, java.lang.Object, int)
 */
@Override
public int getPartition(FlowBean key, Text value, int numPartitions) {
	int partion=3;
	String phone = value.toString().substring(0, 3);
	if ("155".equals(phone)) {
		partion=0;//只能够从0开始
		
	}else if ("158".equals(phone)) {
		partion=1;
	}
	else if ("137".equals(phone)) {
		partion=2;
	}
	 
	return partion;
}
}
