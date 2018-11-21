/**
 * 
 */
package compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Decompressor;
import org.apache.hadoop.util.ReflectionUtils;

/**
 * @author huangyuan
 * @date 2018年11月12日下午3:27:48
 * @Description
 */
public class CompressTest {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//compress("e:/log.log","org.apache.hadoop.io.compress.BZip2Codec");
		compress("e:/hello.txt","org.apache.hadoop.io.compress.GzipCodeC");
		//compress("e:/hello.txt","org.apache.hadoop.io.compress.DefaultCodeC");
		decompress("log.log.bz2");
	}
	private static void decompress(String file) throws Exception{
		//压缩方式的检查
		CompressionCodecFactory factory=new CompressionCodecFactory(new Configuration());
		CompressionCodec codec = factory.getCodec(new Path(file));
		if (codec==null) {
			System.out.println("支持的格式");
			return;
		}
		FileInputStream fis=new FileInputStream(new File(file));
		CompressionInputStream cis = codec.createInputStream(fis);
		
		FileOutputStream fos=new FileOutputStream(new File(file+".decode"));
		
		IOUtils.copyBytes(cis, fos, 0, false);
		
		IOUtils.closeStream(cis);
		IOUtils.closeStream(fos);
		IOUtils.closeStream(fis);
		
			
	}
	private static void compress(String name,String method) throws Exception{
		//获取输入流
		FileInputStream fis = new FileInputStream(new File(name));
		Class codec=Class.forName(method);
		CompressionCodec newInstance = (CompressionCodec)ReflectionUtils.newInstance(codec, new Configuration());
		
		//获取输出流   根据压缩对象可以获取其后缀名
		FileOutputStream fos=new FileOutputStream(new File(name+newInstance.getDefaultExtension()));
		CompressionOutputStream cos = newInstance.createOutputStream(fos);
		//流的对拷
		IOUtils.copyBytes(fis, cos, 1024*1024, false);
		//关闭流
		IOUtils.closeStream(cos);
		IOUtils.closeStream(fos);
		IOUtils.closeStream(fis);
		
	}

}
