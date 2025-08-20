package swea;

import java.io.*;
import java.util.*;

public class Problem1767 {
	static int N, bestConnected, bestLen, length;
	static int[][] board;
	static boolean[][] visited;
	static List<int[]> store;
	static int[] dirx = {1,-1,0,0}; // 하 상 우 
	static int[] diry = {0,0,1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			for (int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			store = new ArrayList<>();
			for (int i = 0; i<N; i++) {
				for (int j = 0; j<N; j++) {
					if (i != 0 && i != N-1 && j != 0 && j != N-1 && board[i][j] == 1) {
						store.add(new int[] {i,j});						
					}
				}
			}
			
			length = store.size();
			if (length == 0) {
				sb.append("#").append(t).append(" ").append(0).append("\n");
				continue;
			}
			
			visited = new boolean[N][N];
			bestLen = Integer.MAX_VALUE; bestConnected = -1;
			dfs(0, 0, 0);
			sb.append("#").append(t).append(" ").append(bestLen).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	
	
	static void dfs(int idx, int connected, int len) {
		if (connected+(length-idx) < bestConnected) return;
		if (connected+(length-idx) == bestConnected && len>=bestLen) return;
		
		if (idx == length) {
			if (connected > bestConnected || (connected == bestConnected && len < bestLen)) {
				bestLen = len;
				bestConnected = connected;
			}
			return;
		}
		
		int x = store.get(idx)[0], y = store.get(idx)[1];
		for (int d = 0; d<4; d++) {
			int wireLen = setWire(x,y,d);
			if (wireLen>=0) {
				dfs(idx + 1, connected +1, len + wireLen);
				rollBack(x,y,d);
			}
		}
		// 연결 포기 
		dfs(idx + 1, connected, len);
	}
	
	static int setWire(int x, int y, int d) {
		int nx = x + dirx[d], ny = y + diry[d];
		List<int[]> path = new ArrayList<>();
		while (0<=nx && nx<N && 0<=ny && ny<N) {
			if (board[nx][ny] == 1 || visited[nx][ny]) return -1;
			path.add(new int[] {nx,ny});
			nx += dirx[d];
			ny += diry[d];
		}
		for (int[] p : path) {
			visited[p[0]][p[1]] = true;
		}
		return path.size();
	}
	
	static void rollBack(int x, int y, int d) {
		int nx = x + dirx[d], ny = y + diry[d];
		while (0<=nx && nx<N && 0<=ny && ny<N) {
			visited[nx][ny] = false;
			nx += dirx[d];
			ny += diry[d];
		}
	}
	
}

