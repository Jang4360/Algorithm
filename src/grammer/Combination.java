package grammer;

import java.util.Arrays;

public class Combination {
	static int N,R;
	static int[] numbers;
	
	public static void main(String[] args) {
		N = 6;
		R = 2;
		numbers = new int[R];
		comb(0,1);
		System.out.println();
		duplicatecomb(0,1);
	}
	
	// 조합
	static void comb(int cnt, int start) {
		if (cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for (int i = start; i<=N; i++) {
			numbers[cnt] = i;
			comb(cnt+1,i+1);
		}
	}
	
	// 중복 조합
	static void duplicatecomb(int cnt, int start) {
		if (cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for (int i = start; i<=N; i++) {
			numbers[cnt] = i;
			comb(cnt+1,i);
		}
	}
		
}
