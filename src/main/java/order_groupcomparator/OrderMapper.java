/**
 * 
 */
package order_groupcomparator;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author huangyuan
 * @date 2018年11月7日下午5:08:15
 * @Description 将id和价格都封装到orderbean中，v为空
 */
public class OrderMapper extends Mapper<LongWritable, Text,Orderbean, NullWritable>{
	Orderbean k = new Orderbean();
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Orderbean, NullWritable>.Context context)
			throws IOException, InterruptedException {
		
		//001 pd1 100
		String[] split = value.toString().split("\t");
		k.setId(Integer.parseInt(split[0]));
		k.setPrice(Double.parseDouble(split[2]));
		
		context.write(k, NullWritable.get());
	}

}
