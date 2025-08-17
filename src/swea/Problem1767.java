package swea;

import java.io.*;
import java.util.*;

public class Problem1767 {
	static int N, minVal, len;
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
			
			len = store.size();
			if (len == 0) {
				sb.append("#").append(t).append(" ").append(0).append("\n");
				continue;
			}
			
			minVal = Integer.MAX_VALUE;
			dfs(0, new ArrayList<>());
			sb.append("#").append(t).append(" ").append(minVal).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	
	
	static void dfs(int cnt, List<Integer> list) {
		if (list.size() == len) {
			visited = new boolean[N][N];
			findDistance(list);
			return;
		}
		
		
		for (int d = 0; d<4; d++) {
			if (isValid(cnt,d)) {
				list.add(d);
				dfs(cnt + 1, list);
				list.remove(list.size()-1);
			}
		}
	}
	
	static boolean isValid(int cur, int d) {
		int cx = store.get(cur)[0], cy = store.get(cur)[1];
		int nx = cx + dirx[d], ny = cy + diry[d];
		while (0<=nx && nx<N && 0<=ny && ny<N && board[nx][ny] == 0) {
			nx += dirx[d];
			ny += diry[d];
		}
		if (0>nx || nx>=N || 0>ny || ny>=N) return true;
		else return false;
	}
	
	static void findDistance(List<Integer> direction) {
		int total = 0;
		for (int i = 0; i<len; i++) {
			int cx = store.get(i)[0], cy = store.get(i)[1];
			int d = direction.get(i);
			int nx = cx + dirx[d], ny = cy + diry[d];
			int dist = 0;
			while (0<=nx && nx<N && 0<=ny && ny<N && !visited[nx][ny] && board[nx][ny] == 0) {
				nx += dirx[d];
				ny += diry[d];
				dist++;
				if (total>=minVal) return;
			}
			if (total>=minVal) return;
			
			if (0>nx || nx>=N || 0>ny || ny>=N) {
				int curx = cx + dirx[d], cury = cy + diry[d];
				while (0<=curx && curx<N && 0<=cury && cury<N) {
					visited[curx][cury] = true;
					curx += dirx[d]; cury += diry[d];
				}
				total += dist;
			} else if (visited[nx][ny] || board[nx][ny] != 0) return;
		}
//		System.out.println(total);
		minVal = Math.min(total, minVal);
	}
	
	
}

