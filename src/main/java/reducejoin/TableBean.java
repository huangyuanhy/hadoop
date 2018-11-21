/**
 * 
 */
package reducejoin;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

/**
 * @author huangyuan
 * @date 2018年11月8日下午6:53:25
 * @Description
 */
public class TableBean implements Writable{
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeInt(amount);
		out.writeUTF(flag);
		out.writeUTF(id);
		out.writeUTF(pid);
		out.writeUTF(pname);
		
		
	}
	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		amount=in.readInt();
		flag=in.readUTF();
		id=in.readUTF();
		pid=in.readUTF();
		pname=in.readUTF();
	}
	private String id;//id号
	private String pid;//产品号
	private int amount;//数量
	private String pname;//产品名字
	private String flag;//标记
	@Override
	public String toString() {
		return  id + "\t"+amount+"\t"+pname;
	}
	/**
	 * 
	 */
	public TableBean() {
		// TODO Auto-generated constructor stub
	}
	public TableBean(int amount, String flag, String id, String pid, String pname) {
		super();
		this.amount = amount;
		this.flag = flag;
		this.id = id;
		this.pid = pid;
		this.pname = pname;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}

}
