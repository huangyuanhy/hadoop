/**
 * 
 */
package reducejoin;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


/**
 * @author huangyuan
 * @date 2018年11月8日下午4:41:51
 * @Description
 */
public class JoinMapper extends Mapper<LongWritable, Text, Text, TableBean>{
	String name ;
	TableBean tableBean = new TableBean();
	Text k = new Text();
@Override
protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, TableBean>.Context context)
		throws IOException, InterruptedException {

	String line = value.toString();
	if (name.startsWith("order")) {//订单表
	/*	ID  pId amount
		 1001 01   1*/
		String[] f = line.split(" ");
		tableBean.setId(f[0]);
		tableBean.setPid(f[1]);
		tableBean.setAmount(Integer.parseInt(f[2]));
		tableBean.setPname("");//必须设置，否则报错
		tableBean.setFlag("order");
		//以共同的pid作为key
		k.set(f[1]);
	}else {
		//产品表
		/*
		 pId  pname
		 01    小米*/
		String[] f = line.split(" ");
		tableBean.setId("");
		tableBean.setPid(f[0]);
		tableBean.setAmount(0);
		tableBean.setPname(f[1]);//必须设置，否则报错
		tableBean.setFlag("pd");
		
		k.set(f[0]);
	}
	context.write(k, tableBean);
}
 
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, TableBean>.Context context)
			throws IOException, InterruptedException {
		 
		FileSplit split=(FileSplit)context.getInputSplit();//强转
		 name = split.getPath().getName();
	}
}
