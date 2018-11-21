/**
 * 
 */
package commomfriends3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * @author huangyuan
 * @date 2018年11月19日下午3:24:24
 * @Description
 */
public class FriendsMapper extends Mapper<LongWritable	, Text, FriendsBean, NullWritable>{
	TreeMap<String, String[]> map = new TreeMap<>();
	@Override
	protected void setup(Mapper<LongWritable, Text, FriendsBean, NullWritable>.Context context)
			throws IOException, InterruptedException {
		//			A:B,C,D,F,E,O
		BufferedReader bufferedReader=new BufferedReader(new FileReader(new File("e://test/list.txt")));
		String line;
		while ((line=bufferedReader.readLine())!=null&&!line.trim().equals("")) {
			String[] split = line.split(":");
			String[] val = split[1].split(",");
			map.put(split[0], val);
		}

		bufferedReader.close();
	}
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, FriendsBean, NullWritable>.Context context)
			throws IOException, InterruptedException {


		FriendsBean k = new FriendsBean();
		 
		// A:B,C,D,F,E,O
		String[] split = value.toString().split(":");

		String[] values = split[1].split(",");//B C D F E O
		map.put(split[0], null);//读取的当前这一行，在缓存中也有，为了避免重复比较，将其置为空值
		//	假设第一行现在和缓存中的第二个比较	B:A,C,E,K
		Set<Entry<String, String[]>> entrySet = map.entrySet();

		for (Entry<String, String[]> entry : entrySet) {
			String master = entry.getKey();
			String[] friends = entry.getValue();
			if (friends!=null) {//此判断为了减少重复
				String common = find(friends,values );
				if (common!=null&&!common.equals("")) {
					k.setA(split[0]);
					k.setB(master);
					k.setC(common);
					context.write(k, NullWritable.get());
				}
			}
		}


	}
	String find(String[] a,String[] b) {
		StringBuilder stringBuilder=new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				if (a[i].equals(b[j])) {
					
					stringBuilder.append(a[i]);
				}
			}
		}
		return stringBuilder.toString();
	}
}
