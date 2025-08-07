package backjoon;
import java.io.*;
import java.util.*;
public class Problem11600 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		StringBuilder sb = new StringBuilder();
		int[][] numbers = new int[N][N];
		
		for (int i = 0; i< N; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int j = 0; j<N; j++) {
				numbers[i][j] = Integer.parseInt(st1.nextToken());	
			}
		}
		int[][] sumNumbers = new int[N+1][N+1];
		
		for (int i = 1; i<=N; i++) {
			for (int j = 1; j<=N; j++) {
				sumNumbers[i][j] = sumNumbers[i-1][j] + sumNumbers[i][j-1] + numbers[i-1][j-1]-sumNumbers[i-1][j-1];
			}
		}
		
		for (int i = 0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int total = sumNumbers[x2][y2] - (sumNumbers[x2][y1-1] + sumNumbers[x1-1][y2] - sumNumbers[x1-1][y1-1]);
		
			
			sb.append(total + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
}
