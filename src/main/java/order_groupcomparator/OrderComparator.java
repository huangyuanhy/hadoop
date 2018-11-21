/**
 * 
 */
package order_groupcomparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author huangyuan
 * @date 2018年11月7日下午7:31:28
 * @Description
 */
public class OrderComparator extends WritableComparator{
	public OrderComparator() {
		super(Orderbean.class,true);//很重要 看源码就知道，不设置为true 会报空指针异常
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compare(WritableComparable a, WritableComparable b) {

		
		//只要id相同，就认为是相同的key
		Orderbean abean=(Orderbean)a;
		Orderbean bbean=(Orderbean)b;

		int res;
		if (abean.getId()>bbean.getId()) {
			res=1;
		}else if (abean.getId()<bbean.getId()) {
			res=-1;
		}else {
			res=0;//两个id是同一个key
		}
		return res;


	}

}
