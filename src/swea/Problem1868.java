package swea;
import java.io.*;
import java.util.*;
public class Problem1868 {
	static int N;
	static String[][] board;
	static int[] dirx = {1,0,0,-1,1,1,-1,-1};
	static int[] diry = {0,1,-1,0,1,-1,1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new String[N][N];
			for (int i = 0; i<N; i++) {
				String[] row = br.readLine().split("");
				for (int j = 0; j<N; j++) {
					board[i][j] = row[j];
				}
			}
			int minCnt = 0;
		
			
			for (int i = 0; i<N; i++) {
				for (int j = 0; j<N; j++) {
					if (board[i][j].equals(".")) {
						if (count(i,j) == 0) {
							spread(i,j);
							minCnt++;
						}
					}
				}
			}
			
			for (int i = 0; i<N; i++) {
				for (int j = 0; j<N; j++) {
					if (board[i][j].equals(".")) minCnt++;
				}
			}
			sb.append("#").append(t).append(" ").append(minCnt).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int count(int x, int y) {
		int cnt = 0;
		for (int d = 0; d < 8; d++) {
			int nx = x + dirx[d];
			int ny = y + diry[d];
			if (0<=nx && nx<N && 0<=ny && ny<N) {
				if (board[nx][ny].equals("*")) cnt++;
			}
		}
		return cnt;
	}
	
	// 폭탄 확산
	static void spread(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][]visited = new boolean[N][N];
		visited[x][y] = true;
		q.offer(new int[] {x,y});
		board[x][y] = String.valueOf(0);
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0], cy = cur[1];
			for (int d = 0; d<8; d++) {
				int nx = cx + dirx[d], ny = cy + diry[d];
				if (0<=nx && nx<N && 0<=ny && ny<N) {
					if (visited[nx][ny]) continue;
					int cnt = count(nx,ny);
					if (cnt == 0) q.add(new int[] {nx,ny});	

					visited[nx][ny] = true;
					board[nx][ny] = String.valueOf(cnt);
				}
			}
		}
	}
}
