package grammer;

import java.io.*;
import java.util.*;

public class NQueen {
	static int[][] board;
	static int N, totalCnt;
	static int[] dirx = {1,-1,0,0};
	static int[] diry = {0,0,1,-1};
	static int[] crossx = {1,1,-1,-1};
	static int[] crossy = {1,-1,1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for (int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i<N*N; i++) {
			dfs(i, 0, new boolean[N][N]);
		}
		
		System.out.println(totalCnt);
	}
	
	
	static void dfs(int len, int cnt, boolean[][] visited) {
		// 유망하지 않는 것을 어떻게 가지치기?
		if (cnt == len) {
			totalCnt++;
			return;
		}
		
		for (int i = 0; i<N; i++) {
			for (int j = 0; j<N; j++) {
				if (!visited[i][j] && valid(i,j)) {
					visited[i][j] = true;
					dfs(len, cnt+1, visited);
					visited[i][j] = false;
					board[i][j] = 0;
				}
			}
		}
	}
	
	static boolean valid(int x, int y) {
		board[x][y] = 1;
		// 상하좌우 
		for (int d = 0; d<4; d++) {
			int nx = x+dirx[d];
			int ny = y+diry[d];
			while (0<=nx && nx<N && 0<=ny && ny<N) {
				if (board[nx][ny] == 1) {
					board[x][y] = 0;
					return false;
				}
				nx += dirx[d];
				ny += diry[d];
			}
		}
		
		// 대각선 
		for (int d = 0; d<4; d++) {
			int nx = x+crossx[d];
			int ny = y+crossy[d];
			while (0<=nx && nx<N && 0<=ny && ny<N) {
				if (board[nx][ny] == 1) {
					board[x][y] = 0;
					return false;
				}
				nx += dirx[d];
				ny += diry[d];
			}
		}
		return true;
	}
}
