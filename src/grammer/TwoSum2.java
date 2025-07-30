package grammer;
import java.io.*;
import java.util.*;
public class TwoSum2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int x = Integer.parseInt(input[1]);
		
		String[] numStr = br.readLine().split(" ");
		
		int[] nums = Arrays.stream(numStr).mapToInt(Integer::parseInt).toArray();
		
		int left = 0, right = 0;
		int sum = 0, count = 0;
		
		while (true) {
			if (sum>x) {
				sum -= nums[left++];
			} else if (right == n) {
				break;
			} else {
				sum += nums[right++];
			}
			if (sum == x) {
				count++;
			}
		}
		
//		while (true) {
//			if (sum>=x) {
//				sum -= nums[left++];
//			} else if (right == n) {
//				break;
//			} else {
//				sum += nums[right++];
//			}
//			if (sum == x) {
//				count++;
//			}
//		}
		
		System.out.println(count);


		
	}

}
