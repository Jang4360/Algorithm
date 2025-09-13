package swea;
import java.io.*;
import java.util.*;
public class Problem7793 {
	static int N,M,sx,sy,ex,ey;
	static Character[][] board;
	static int[] dirx = {1,0,0,-1};
	static int[] diry = {0,1,-1,0};
	static List<int[]> devils;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			board = new Character[N][M];
			devils = new ArrayList<>();
			for (int i = 0; i<N; i++) {
				String row = br.readLine();
				for (int j = 0; j<M; j++) {
					board[i][j] = row.charAt(j);
					if (board[i][j] == 'S') {
						sx = i; sy = j;
					} else if (board[i][j] == 'D') {
						ex = i; ey = j;
					} else if (board[i][j] == '*') {
						devils.add(new int[] {i,j});
					}
				}
			}
			
			int result = dijkstra();
			if (result != Integer.MAX_VALUE) {
				System.out.println("#" + t + " " + result);
			} else {
				System.out.println("#" + t + " GAME OVER");
			}
		}
	}

	static int dijkstra() {
		int[][] distance = new int[N][M];
		for (int i = 0; i<N; i++) Arrays.fill(distance[i], Integer.MAX_VALUE);
		distance[sx][sy] = 1;
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
		pq.offer(new int[] {sx, sy, 0});
		
		int time = 0;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int x = cur[0], y = cur[1], dis = cur[2];
			if (dis > distance[x][y]) continue;
			
			for (int d = 0; d < 4; d++ ) {
				int nx = x + dirx[d], ny = y + diry[d];
				if (0>nx || nx>=N || 0>ny || ny>=M || board[nx][ny] == 'X' || board[nx][ny] == '*') continue;
				int newDis = dis + 1;
				if (newDis < distance[nx][ny]) {
					distance[nx][ny] = newDis;
					pq.offer(new int[] {nx, ny, newDis});
					if (time != newDis) {
						spread();
						time = newDis;
					}
				}
			}
		}
		return distance[ex][ey];
	}
	
	// 악마 확장
	static void spread() { 
		List<int[]> addList = new ArrayList<>();
		for (int[] loc : devils) {
			int x = loc[0], y = loc[1];
			for (int d = 0; d<4; d++) {
				int nx = x + dirx[d], ny = y + diry[d];
				if (0>nx || nx>=N || 0>ny || ny>=M) continue;
				if (board[nx][ny] == '.') {
					board[nx][ny] = '*';
					addList.add(new int[] {nx,ny});
				}
			}
		}
		for (int[] add : addList) {
			devils.add(new int[] {add[0],add[1]});
		}
	}
	
}
