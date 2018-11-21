/**
 * 
 */
package commomfriends2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author huangyuan
 * @date 2018年11月19日下午8:32:32
 * @Description 在进入reducer 之前分组排序
 * 因为 A-k 或者K-A 为一组数据
 */
public class GroupSort extends WritableComparator{

	public GroupSort() {
		super(Text.class, true);
		 
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		Text aa=(Text)a;
		Text bb=(Text)b;
		String[] asplit = a.toString().split(" ");// I K
		String[] bsplit = b.toString().split(" ");//K I
		if (asplit[0].equals(bsplit[1])&&asplit[1].equals(bsplit[0])
				||asplit[0].equals(bsplit[0])&&asplit[1].equals(bsplit[1])) {
			return 0;
		}else if (asplit[0].compareTo(bsplit[0])==-1) {
			return -1;
		}else {
			return 1;
		}
		
	}

}
