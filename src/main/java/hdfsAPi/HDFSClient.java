/**
 * 
 */
package hdfsAPi;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Test;

/**
 * @author huangyuan
 * @date 2018年11月1日下午1:30:36
 * @Description
 */
public class HDFSClient {

	/**
	 * @param args
	 * @Description TODO
	 */
	@Test
	//文件详情
	public void detailsOfFiles() throws Exception{
		//配置文件
				Configuration configuration=new Configuration();
				//获取hdfs客户端对象
				FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.211:9000"), configuration, "huangyuan");
				//获取文件
				RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/"), true);
				while (listFiles.hasNext()) {
					LocatedFileStatus next = listFiles.next();
					next.getBlockSize();
					next.getLen();
					
					
				}
				//关闭资源
				fileSystem.close();
	}
	@Test
	//文件上传
	public void delete() throws Exception{
		//配置文件
				Configuration configuration=new Configuration();
				//获取hdfs客户端对象
				FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.211:9000"), configuration, "huangyuan");
				//删除
				fileSystem.delete(new Path("/huangyuan/test.txt"), true);
				
				//关闭资源
				fileSystem.close();
	}
	@Test
	//文件上传
	public void copyfromlocal() throws Exception{
		//配置文件
				Configuration configuration=new Configuration();
				//获取hdfs客户端对象
				FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.211:9000"), configuration, "huangyuan");
				//在hdfs上创建路径
				fileSystem.copyFromLocalFile(new Path("E:\\test.txt"),new Path("/huangyuan/test.txt"));
				//文件下载
				//fileSystem.copyToLocalFile(new Path("/huangyuan/test.txt"),new Path("E:\\test.txt"));
				//关闭资源
				fileSystem.close();
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//method1();
		method2();
	}

	/**
	 * @throws IOException
	 * @Description TODO
	 */
	private static void method1() throws IOException {
		//配置文件
		Configuration configuration=new Configuration();
		configuration.set("fs.defaultFS", "hdfs://192.168.1.211:9000");
		//获取hdfs客户端对象
		FileSystem fileSystem = FileSystem.get(configuration);
		//在hdfs上创建路径
		fileSystem.mkdirs(new Path("/huangyuan/test"));
		//关闭资源
		fileSystem.close();
	}
	private static void method2() throws Exception {
		//配置文件
		Configuration configuration=new Configuration();
		//获取hdfs客户端对象
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.211:9000"), configuration, "huangyuan");
		//在hdfs上创建路径
		fileSystem.mkdirs(new Path("/huangyuan/test2"));
		//关闭资源
		fileSystem.close();
	}

}
