/*
 * Solution to https://leetcode.com/problems/max-points-on-a-line/
 * Video tutorial https://youtu.be/3nXAk21kF-8
 */
public class MaxPointsOnLine {
	public static void main(String[] args) {
		new MaxPointsOnLine();
	}
	public MaxPointsOnLine() {
		int[][] list = {{1,1},{2,2},{3,3}};
		System.out.println(maxPoints(list));	
	}
	
	public int maxPoints(int[][] points) {
        
		int global = 0;
		
		boolean same = true;
		for(int i=0;i<points.length;i++) {
			if(points[i][0] != points[0][0] || points[i][1] != points[0][1]) { 
				same = false;
			}
		}
		
		if(same) {
			global = points.length;
		}
		
		for (int i = 0 ; i < points.length; i++) {
			for (int j = i+1; j< points.length; j++) {
				if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
					continue;
				}
				
				int count = 2;
				for(int k = 0; k < points.length; k++) {
					if( i==k || j == k)continue;
					
					
					long ijx = points[j][0] - points[i][0], 
						ijy = points[j][1] - points[i][1];
					long ikx = points[k][0] - points[i][0],
						iky = points[k][1] - points[i][1];
					
					long crossproduct = ijx*iky - ikx*ijy;
					if(crossproduct == 0) {
						count++;
					}
				}
				
				global = Math.max(global, count);
			}
		}
		
		return global;
    }
}
