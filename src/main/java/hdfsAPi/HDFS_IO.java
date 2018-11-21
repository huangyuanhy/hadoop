/**
 * 
 */
package hdfsAPi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

/**
 * @author huangyuan
 * @date 2018年11月1日下午4:26:35
 * @Description
 */
public class HDFS_IO {
	@Test
	//文件下载指定大小
	public void iopintedSize() throws Exception{
		//配置文件
				Configuration configuration=new Configuration();
				//获取hdfs客户端对象
				FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.211:9000"), configuration, "huangyuan");
				//输入
				FSDataInputStream fis = fileSystem.open(new Path("/test.txt"));
				//-------------指定从10M的位置开始读取----------------------
				fis.seek(1*2014*10);
				//输出
				FileOutputStream fos = new FileOutputStream(new File("e:test.txt"));
				//流的对拷 128M
				byte[] b = new byte[1024];
				for (int i=0;i<1024*128;i++) {
					fis.read(b);
					fos.write(b);
				}
				//关闭资源
				IOUtils.closeStream(fos);
				IOUtils.closeStream(fis);
				fileSystem.close();
	}
	@Test
	//文件下载
	public void ioDownload() throws Exception{
		//配置文件
				Configuration configuration=new Configuration();
				//获取hdfs客户端对象
				FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.211:9000"), configuration, "huangyuan");
				//输入
				FSDataInputStream fis = fileSystem.open(new Path("/test.txt"));
				
				//输出
				FileOutputStream fos = new FileOutputStream(new File("e:test.txt"));
				//流的对拷
				IOUtils.copyBytes(fis, fos, configuration);
				
				//关闭资源
				IOUtils.closeStream(fos);
				IOUtils.closeStream(fis);
				fileSystem.close();
	}
	@Test
	//文件上传
	public void ioUpload() throws Exception{
		//配置文件
				Configuration configuration=new Configuration();
				//获取hdfs客户端对象
				FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.211:9000"), configuration, "huangyuan");
				//输入
				FileInputStream fis = new FileInputStream(new File("e:test.txt"));
				//输出
				FSDataOutputStream fos = fileSystem.create(new Path("/test.txt"));
				//流的对拷
				IOUtils.copyBytes(fis, fos, configuration);
				
				//关闭资源
				IOUtils.closeStream(fos);
				IOUtils.closeStream(fis);
				fileSystem.close();
	}
}
