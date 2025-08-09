package grammer;
import java.util.*;
public class Permutation {
	static int N,R,totalCnt;
	static int[] numbers;
	static boolean[] visited;
	public static void main(String[] args) {
		N = 6;
		R = 2;
		totalCnt = 0;
		numbers = new int[R];
		visited = new boolean[N+1];
		permutation(0);
		System.out.println();
		duplicatePermutation(0);
		
	}
	
	// 순열
	static void permutation(int cnt) {
		if (cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for (int i = 1; i<=N; i++) {
			if (visited[i]) continue;
			numbers[cnt] = i;
			visited[i] = true;
			permutation(cnt+1);
			visited[i] = false;
		}
	}
	
	// 중복 순열
	static void duplicatePermutation(int cnt) {
		if (cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for (int i = 1; i<=N; i++) {
			numbers[cnt] = i;
			duplicatePermutation(cnt+1);
		}
	}
}
