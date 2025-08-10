package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem3234 {
	static int N;
	static int answer;
	static int[] array, numbers, left, right;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] weight = new int[N];
			for (int i = 0; i<N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			// 오른쪽이 왼쪽보다 작거나 같아야 한다
			numbers = new int[2*N];
			for (int i = 0; i<2*N; i++) {
				if (i>=N) numbers[i] = 0;
				else numbers[i] = weight[i];
			}
			
			array = new int[2*N];
			visited = new boolean[2*N+1];
			answer = 0;
			permute(0);
			System.out.println(answer);
		}
	}
	
	static void permute(int cnt) {
		if(cnt == 2*N) {
			makeLeftRight();
			if (isValidSum()) {
				answer++;
			}
			return;
		}
		
		for (int i = 0; i<2*N; i++) {
			if (i>0 && numbers[i] == numbers[i-1] && !visited[i-1]) continue;
			if (!visited[i]) {
				visited[i] = true;
				array[cnt] = numbers[i];
				permute(cnt+1);
				visited[i] = false;
			}
		}
	}
	
	static void makeLeftRight() {
		left = new int [N];
		right = new int[N];
		for (int i = 0; i<2*N; i++) {
			if (i<N) left[i] = array[i];
			else right[i-N] = array[i];
		}
	}
	
	static boolean isValidSum() {
		int rightSum=0, leftSum=0;
		for (int i = 0; i<N; i++) {
			if ((right[i] == 0 && left[i] == 0) || (right[i]!=0 && left[i]!=0)) return false;
			rightSum += right[i];
			leftSum += left[i];
			if (rightSum>leftSum) return false;
		}
		if (leftSum>=rightSum) return true;
		else return false;
	}
	
}
