/**
 * 
 */
package order_groupcomparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

/**
 * @author huangyuan
 * @date 2018年11月7日下午4:57:57
 * @Description
 */
public class Orderbean implements WritableComparable<Orderbean>{
	private int id;
	private double price;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Orderbean(int id, double price) {
		super();
		this.id = id;
		this.price = price;
	}

	public Orderbean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(id);
		out.writeDouble(price);
		
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		id=in.readInt();
		price=in.readDouble();
	}

	@Override
	public int compareTo(Orderbean o) {
		//先按照id升序排，如果id相同，则按照价格降序排列
		int res=0;
		if (id>o.getId()) {
			res=1;
		}else if (id<o.getId()) {
			res=-1;
		}else {
			if (price>o.getPrice()) {
				res=-1;
			}else if (price<o.getPrice()) {
				res=1;
			}else {
				res=0;
			}
		}
		return res;
	}

	@Override
	public String toString() {
		return id + "\t" + price ;
	}

}
