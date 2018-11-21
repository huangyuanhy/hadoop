/**
 * 
 */
package selfdefinitionOutputformat;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

/**
 * @author huangyuan
 * @date 2018年11月8日下午3:36:14
 * @Description
 */
public class FilterRecordWriter extends RecordWriter<Text, NullWritable>{
	FSDataOutputStream fosother;
	FSDataOutputStream fosbaidu;
	public FilterRecordWriter(TaskAttemptContext context) {
		try {
			FileSystem fs = FileSystem.get(context.getConfiguration());
			//创建两个输出流
			fosbaidu = fs.create(new Path("e:/baidu.log"));
			fosother = fs.create(new Path("e:/others.log"));
			
			
		} catch (Exception e) {
			// TODO: handle exce
			e.printStackTrace();
		}
		
	}

	@Override
	public void write(Text key, NullWritable value) throws IOException, InterruptedException {
		// 真正处理业务逻辑的地方
		if (key.toString().contains("baidu")) {
			fosbaidu.write(key.toString().getBytes());
		}else {
			fosother.write(key.toString().getBytes());
		}
		
	}

	@Override
	public void close(TaskAttemptContext context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		IOUtils.closeStream(fosbaidu);
		IOUtils.closeStream(fosother);
	}

}
