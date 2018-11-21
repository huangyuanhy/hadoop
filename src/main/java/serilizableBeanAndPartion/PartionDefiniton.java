
package serilizableBeanAndPartion;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author huangyuan
 * @date 2018年11月6日下午3:33:23
 * @Description
 * 根据手机号的前3位进行分区，放在不同文件夹中 
 */
public class PartionDefiniton extends Partitioner<Text, BeanSeriliazble>{

	@Override
	public int getPartition(Text key, BeanSeriliazble value, int numPartitions) {
		// TODO Auto-generated method stub
		int partion=3;
		String phone = key.toString().substring(0, 3);
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
