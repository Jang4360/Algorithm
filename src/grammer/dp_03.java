package grammer;
import java.io.*;
import java.util.*;
public class dp_03 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int[][] maze = new int[N][M];
		
		for (int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j<M; j++) {
				maze[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N+1][M+1];
		for (int i = 0; i<N; i++) dp[i][0] = 0;
		for (int j = 0; j<M; j++) dp[0][j] = 0;
		
		for (int i = 1; i<=N; i++) {
			for (int j = 1; j<=M; j++) {
				int val = Math.max(dp[i-1][j-1], dp[i][j-1]);
				dp[i][j] = maze[i-1][j-1] + Math.max(val, dp[i-1][j]);
			}
		}
		System.out.println(dp[N][M]);
	}
}
