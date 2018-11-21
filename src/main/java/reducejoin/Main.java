/**
 * 
 */
package reducejoin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author huangyuan
 * @date 2018年11月18日上午10:26:25
 * @Description
 */
public class Main {

	/**
	 * @param args
	 * @Description TODO
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		String[] nextLine = scanner.nextLine().split(" ");
		BigDecimal[] bigDecimal=null;
		double[] num= null;
		int k=0;
		for (int i = 0; i < nextLine.length; i++) {
			num[k++]=Double.parseDouble(nextLine[i]);
		}
		 
		
		name(num);
	}
static void name(double[] num) {
	List<Double> list=new ArrayList<>();
	double min=Integer.MAX_VALUE;
	for (int i = 0; i < num.length; i++) {
		for (int j = 1; j < num.length; j++) {
			double sum=num[i]+num[j];
			double abs = Math.abs(sum);
			list.add(abs);
		}
	}
	Collections.sort(list);
	for (int i = 0; i < num.length; i++) {
		for (int j = 1; j < num.length; j++) {
			double sum=num[i]+num[j];
			double abs = Math.abs(sum);
			if (list.get(0)==abs) {
				System.out.print(i+" "+j+" ");
				System.out.printf("%.1f",sum);
				return;
			}
		}
	}
	
}
}
