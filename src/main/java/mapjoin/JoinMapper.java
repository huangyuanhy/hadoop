/**
 * 
 */
package mapjoin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;


import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author huangyuan
 * @date 2018年11月12日上午9:57:21
 * @Description
 * 为避免数据倾斜，减少ruduce阶段的工作量，提高效率，通常可在map阶段进行合并
 * 适用于一张小表+大表,小表在setup阶段读入封装到集合中，map阶段再读取大表进行合并
 * 然后直接写出，不再用reduce
 */
public class JoinMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
	HashMap<String, String> map=new HashMap<>();
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		//加载小表
		URI[] cache = context.getCacheFiles();
		String path = cache [0].getPath().toString();
		BufferedReader bf=new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		String line;
		while (StringUtils.isEmpty(line=bf.readLine())) {
			String[] f = line.split("\t");
			//  01	  小米
			map.put(f[0],f[1]);
		}
		IOUtils.closeStream(bf);
	}
	Text k = new Text();
	
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		//1001 01 1
		
		String[] f = value.toString().split("\t");
		String pid=f[1];
		String pname=map.get(pid);
		//拼接

		String line=value.toString()+"\t"+pname;
		k.set(line);
		
		context.write(k, NullWritable.get());

	}
}
