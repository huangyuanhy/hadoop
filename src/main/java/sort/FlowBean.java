/**
 * 
 */
package sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author huangyuan
 * @date 2018年11月6日下午4:47:08
 * @Description
 * 对key进行自定义的倒序排序
 */
public class FlowBean implements WritableComparable<FlowBean>{
	@Override
	public int compareTo(FlowBean o) {
		if (this.getSumFlow()>o.getSumFlow()) {
			return -1;
		}	
		if (this.getSumFlow()==o.getSumFlow()) {
			return 0;
		}else {
			return 1;
		}

	}
	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		upFlow=in.readLong();
		downFlow=in.readLong();
		sumFlow=in.readLong();
	}
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeLong(upFlow);
		out.writeLong(downFlow);
		out.writeLong(sumFlow);
	}
	private long upFlow;
	private long downFlow;
	private long sumFlow;
	/**
	 * 用于反序列化时候的反射
	 */
	public FlowBean() {
		// TODO Auto-generated constructor stub
	}
	public long getUpFlow() {
		return upFlow;
	}

	public void setUpFlow(long upFlow) {
		this.upFlow = upFlow;
	}

	public long getDownFlow() {
		return downFlow;
	}

	public void setDownFlow(long downFlow) {
		this.downFlow = downFlow;
	}

	public long getSumFlow() {
		return sumFlow;
	}

	public void setSumFlow(long sumFlow) {
		this.sumFlow = sumFlow;
	}

	public FlowBean(long upFlow, long downFlow) {
		super();
		this.upFlow = upFlow;
		this.downFlow = downFlow;
		this.sumFlow = upFlow+downFlow;
	}

	public String toString() {
		return upFlow + "\t"+ downFlow + "\t"+sumFlow ;
	}


}
