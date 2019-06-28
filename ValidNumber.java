/* 
 * Solution to https://leetcode.com/problems/valid-number/
 */

import java.math.BigDecimal;
import java.math.BigInteger;

public class ValidNumber {
	public static void main(String[] args) {
		new ValidNumber();
	}
	
	public ValidNumber() {
		String test = "44e016912630333";
		
		System.out.println(isNumber(test));
	}
	
	public boolean isNumber(String s) {
		
		boolean flag = true;
		
		String num = s.trim();
		
		int count = 0, index = -1;
		for(int i=0;i<num.length();i++) {
			if(num.charAt(i) == 'e' || num.charAt(i)=='E') {
				count++;
				index = i;
			}
		}
		
		if(count > 1) {
			return false;
		} else if (count == 1) {
			String A = num.substring(0, index);
			String B = num.substring(index+1);
			try {
				BigDecimal a = new BigDecimal(A);
				BigInteger b = new BigInteger(B);
			} catch(Exception e) {
				flag = false;
			}
		} else {
			try {
				BigDecimal b = new BigDecimal(num);
			} catch(Exception e) {
				flag = false;
				e.printStackTrace();
			}
		}
		
		return flag;
		
    }
}
