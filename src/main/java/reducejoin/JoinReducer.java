/**
 * 
 */
package reducejoin;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author huangyuan
 * @date 2018年11月8日下午7:36:40
 * @Description
 */
public class JoinReducer extends Reducer<Text, TableBean, TableBean, NullWritable>{

	@Override
	protected void reduce(Text k, Iterable<TableBean> vs,
			Reducer<Text, TableBean, TableBean, NullWritable>.Context context ) throws IOException, InterruptedException {
		//数据： 01 1001 1 order
		//	01  小米    pd
		//存所有的订单集合
		ArrayList<TableBean> orderBean = new ArrayList<>();
		//存产品信息 只有一份记录
		TableBean pdBean=new TableBean();
		for (TableBean v : vs) {
			if ("order".equals(v.getFlag())) {
				TableBean tmp=new TableBean();
				try {//必须先拷贝再添加到集合中去 不然集合中只有只有最后一个
					BeanUtils.copyProperties(tmp, v);
					orderBean.add(tmp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				try {
					BeanUtils.copyProperties(pdBean, v);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
		for (TableBean tableBean : orderBean) {
			tableBean.setPname(pdBean.getPname());
			context.write(tableBean, NullWritable.get());
		}
	}
}
