package backjoon;
import java.util.*;
import java.io.*;

public class Problem14502 {
	static int[][] board;
	static int n,m;
	static int maxCnt = 0;
	static int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		board = new int[n][m];
		
		for (int i = 0; i<n; i++) {
			String[] row = br.readLine().split(" ");
			for (int j = 0; j<m; j++) {
				board[i][j] = Integer.parseInt(row[j]);
			}
		}
		
		dfs(0);
		System.out.println(maxCnt);
	}
	
	
	// 벽 3개 세우기
	static void dfs(int cnt) {
		
		if (cnt == 3) {
			spread();
			return;
		}
		
		for (int i = 0; i<n; i++) {
			for (int j = 0; j<m; j++) {
				if (board[i][j] == 0) {
					board[i][j] = 1;
					dfs(cnt+1);
					board[i][j] = 0;
				}
			}
		}
	}
	
	// 바이러스 퍼지기
	static void spread() {
		int[][] newBoard = new int[n][];
		for (int k = 0; k<n; k++) {
			newBoard[k] = Arrays.copyOf(board[k], m);
		}
		boolean[][] visited = new boolean[n][m];
		for (int i = 0; i<n; i++) {
			for (int j = 0; j<m; j++) {
				if (newBoard[i][j] == 2 && !visited[i][j]) {
					bfs(visited, newBoard,i,j);
				}
			}
		}
		calculateSavePlace(newBoard);
	}
	
	static void bfs(boolean[][] visited, int[][] newBoard, int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{x,y});
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0], curY = cur[1];
			
			for (int[] dir: dirs) {
				int nx = dir[0] + curX;
				int ny = dir[1] + curY;
				if (0<=nx && nx<n && 0<=ny && ny<m && newBoard[nx][ny] == 0 && !visited[nx][ny]) {
					newBoard[nx][ny] = 2;
					visited[nx][ny] = true;
					q.offer(new int[] {nx,ny});
				}
			}
		}
	}
	
	// 안전 영역 구하기
	static void calculateSavePlace(int[][] newBoard) {
		int cnt = 0;
		for (int i = 0; i<n; i++) {
			for (int j = 0; j<m; j++) {
				if (newBoard[i][j] == 0) cnt++;
			}
		}
		maxCnt = Math.max(cnt,maxCnt);
	}
	
}
