/**
 * 
 */
package commomfriends2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

/**
 * @author huangyuan
 * @date 2018年11月19日下午8:13:16
 * @Description
 */
public class FriendsBean implements WritableComparable<FriendsBean>{


	private String A;
	private String B;
	
	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public String getB() {
		return B;
	}

	public void setB(String b) {
		B = b;
	}

	public FriendsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FriendsBean(String a, String b) {
		super();
		A = a;
		B = b;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(A);
		out.writeUTF(B);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		A=in.readUTF();
		B=in.readUTF();
	}

	@Override
	public int compareTo(FriendsBean o) {
		// TODO Auto-generated method stub
		if (A.compareTo(o.getA())>0) {
			return 1;
		}else if (A.compareTo(o.getA())==0) {
			return 0;
		}
		return -1;
	}

	@Override
	public String toString() {
		return  A + "-" + B;
	}
	
}
