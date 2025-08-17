package swea;

import java.io.*;
import java.util.*;

public class Problem1249 {
	static int N;
	static int[][] board;
	static int minPath;
	static int[] dirx = {1,0,-1,0};
	static int[] diry = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			
			for (int i = 0; i<N; i++) {
				String row = br.readLine();
				for (int j = 0; j<N; j++) {
					board[i][j] = row.charAt(j)-'0';	
				}
			}
			minPath = Integer.MAX_VALUE;
//			dfs(0,0,0);  
			sb.append("#").append(t).append(" ").append(dijkstra()).append("\n");
		}
		System.out.println(sb.toString());
	}
	
//	static void dfs(int x, int y, int path) {
//		if (path>=minPath) return;
//		
//		if (x == N-1 && y == N-1) {
//			minPath = Math.min(minPath, path);
//			return;
//		}
//		
//		for (int d = 0; d<2; d++) {
//			int nx = x + dirx[d];
//			int ny = y + diry[d];
//			if (0<= nx && nx<N && 0<=ny && ny<N) {
//				dfs(nx, ny, path + board[nx][ny]);
//			}
//		}
//	}
	static int dijkstra() {
		int[][] distance = new int[N][N];
		for (int i = 0; i<N; i++) Arrays.fill(distance[i], Integer.MAX_VALUE);
		distance[0][0] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
		pq.offer(new int[] {0,0,0});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cx = cur[0], cy = cur[1], dist = cur[2];
			if (dist>distance[cx][cy]) continue;
			
			for (int d = 0; d<4; d++) {
				int nx = cx + dirx[d];
				int ny = cy + diry[d];
				if (0<=nx && nx<N && 0<=ny && ny<N) {
					int nextDist = board[nx][ny] + dist;
					
					if (distance[nx][ny] > nextDist) {
						distance[nx][ny] = nextDist;
						pq.offer(new int[] {nx, ny, nextDist});
					}
				}
			}
		}
		return distance[N-1][N-1];
	}
}
