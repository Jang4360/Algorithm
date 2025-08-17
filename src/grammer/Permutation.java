package grammer;
import java.util.*;
public class Permutation {
	static int N,R,totalCnt;
	static int[] numbers, input;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();

		input = new int[N];
		numbers = new int[R];
		for (int i = 0; i<N; i++) {
			input[i] = sc.nextInt();
		}
		permutation(0,0);
		System.out.println();
//		duplicatePermutation(0);
		
	}
	
	// 순열
	static void permutation(int cnt, int flag) {
		if (cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for (int i = 0; i<N; i++) {
			if ((flag & 1<<i) != 0) continue;
			numbers[cnt] = input[i];
			permutation(cnt+1, flag | 1<<i);
			
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
