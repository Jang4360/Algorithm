package grammer;
import java.io.*;
import java.util.*;
public class TwoSum {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		int n = Integer.parseInt(input[0]);
		int x = Integer.parseInt(input[1]);
		String[] numStr = br.readLine().split(" ");
		int[]nums = new int[numStr.length];
		for (int i = 0; i<numStr.length; i++) {
			nums[i] = Integer.parseInt(numStr[i]);
		}
		Arrays.sort(nums);
		sum(n,x,nums);
		System.out.println(sum(n,x,nums));
	}
	
	static int sum(int n, int target, int[] nums) {
		int left = 0;
		int right = nums.length-1;
		int cnt = 0;
		
		while (left<right) {
			int val = nums[left] + nums[right];
			if (val == target) {cnt++; left++; right--;}
			else if (val<target) left++;
			else right--;
		}
		return cnt;
	}

}
