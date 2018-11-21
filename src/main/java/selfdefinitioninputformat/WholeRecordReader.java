/**
 * 
 */
package selfdefinitioninputformat;

import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

/**
 * @author huangyuan
 * @date 2018年11月6日上午11:08:57
 * @Description
 */
public class WholeRecordReader extends RecordReader<Text, BytesWritable>{
	FileSplit filesplit;
	Configuration conf;
	Text k=new Text();
	BytesWritable v=new BytesWritable();
	boolean flag=true;
	@Override
	public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		filesplit=(FileSplit)split;
		conf=context.getConfiguration();
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		//  核心业务逻辑处理
		
		if (flag) {
			//获取fs对象
			byte[] buf=new byte[(int)filesplit.getLength()];
			Path path = filesplit.getPath();
			FileSystem fs = path.getFileSystem(conf);
			//获取输入流
			FSDataInputStream fis = fs.open(path);
			//借用byte数组缓存读取的内容
			IOUtils.readFully(fis, buf, 0, buf.length);
			//封装k v
			v.set(buf, 0, buf.length);
			k.set(path.toString());
			
			IOUtils.closeStream(fis);
			flag=false;
			return true;
		}
		return false;
	}

	@Override
	public Text getCurrentKey() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return k;
	}

	@Override
	public BytesWritable getCurrentValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return v;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
