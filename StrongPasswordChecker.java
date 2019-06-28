/*
 * Solution to https://leetcode.com/problems/strong-password-checker/
 * Video tutorial at https://youtu.be/ZW18NOSzJy0
 */
import java.util.Arrays;

public class StrongPasswordChecker {
	public static void main(String[] args) {
		new StrongPasswordChecker();
	}
	public StrongPasswordChecker() {
		String test = "aA123"; // ans = 3
		System.out.println(strongPasswordChecker(test));
	}
	
	String chars = "";
	int AlphSize = 0;
	int oo = 999999999;
	int[] input;
	int[][][][][][][] memo;
	
	public int strongPasswordChecker(String s) {
		if(s.length()==0) {
			return 6;
		}
        
		input = remap(s);
		AlphSize = input.length;
		
//		System.out.println(Arrays.toString(input));
		
		memo = new int[2][2][2][4][21][AlphSize+1][s.length()];
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++)
				for(int k=0;k<2;k++)
					for(int l=0;l<4;l++)
						for(int m=0;m<21;m++)
							for(int n=0;n<AlphSize+1;n++)
								Arrays.fill(memo[i][j][k][l][m][n], -1);
		
		
		return solve(0,0,0,0,0,AlphSize,0);
    }
	
	private int solve(int lo, int up, int dig, int count, int len, int prev, int idx) {
		
		if(len > 20) {
			return oo;
		}
		if(count == 3) {
			return oo;
		}
		if(idx == input.length) {
			if(len < 6) return oo;
			else if(lo==1 && up==1 && dig==1) return 0;
			else return oo;
		}
		
		if(memo[lo][up][dig][count][len][prev][idx] != -1) 
			return memo[lo][up][dig][count][len][prev][idx];
		
		int sol = oo;
		
		//take
		int tLo = lo | isLo(input[idx]);
		int tUp = up | isUp(input[idx]);
		int tDig = dig | isDig(input[idx]);
		sol = Math.min(sol, solve(tLo,tUp,tDig,input[idx]==prev ? count+1 : 1, len+1, input[idx],idx+1));
		
		//delete
		sol = Math.min(sol, 1+solve(lo,up,dig,count,len,prev,idx+1));
		
		//insert
		sol = Math.min(sol, 1+solve(1,up,dig,1,len+1,AlphSize,idx));
		sol = Math.min(sol, 1+solve(lo,1,dig,1,len+1,AlphSize,idx));
		sol = Math.min(sol, 1+solve(lo,up,1,1,len+1,AlphSize,idx));
		
		//modify
		
		sol = Math.min(sol, 1+solve(1,up,dig,1,len+1,AlphSize,idx+1));
		sol = Math.min(sol, 1+solve(lo,1,dig,1,len+1,AlphSize,idx+1));
		sol = Math.min(sol, 1+solve(lo,up,1,1,len+1,AlphSize,idx+1));
		
		return memo[lo][up][dig][count][len][prev][idx] = sol;
	}
	
	
	public int isLo(int a) {
		return (Character.isLowerCase(chars.charAt(a))) ? 1 : 0;
	}
	
	public int isUp(int a) {
		return (Character.isUpperCase(chars.charAt(a))) ? 1 : 0;
	}
	
	public int isDig(int a) {
		return (Character.isDigit(chars.charAt(a))) ? 1 : 0;
	}
	
	public int[] remap(String s) {
		int[] arr = new int[s.length()];
		for(int i=0;i<s.length();i++) {
			arr[i] = chars.indexOf(s.charAt(i));
			if(arr[i] == -1) {
				arr[i] = chars.length();
				chars += s.charAt(i);
			}
		}
		return arr;
	}
}
