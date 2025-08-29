package swea;
import java.io.*;
import java.util.*;
public class Problem7699 {
	static int R, C, bestLen;
	static char[][] board;
	static int[] dirx = {1,0,0,-1};
	static int[] diry = {0,1,-1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			R = Integer.parseInt(input[0]);
			C = Integer.parseInt(input[1]);
			board = new char[R][C];
			for (int i = 0; i<R; i++) {
				String line = br.readLine().trim();
				for (int j = 0; j<C; j++) {
					board[i][j] = line.charAt(j);
				}
			}
			bestLen = 0;
			boolean[] used = new boolean[26];
			used[board[0][0]-'A'] = true;
			dfs(0,0,1,used);
			System.out.println("#"+ t + " " + bestLen);
		}
	}
	static void dfs(int x, int y, int len, boolean[] used) {
		if (len>bestLen) bestLen = len;
		if (bestLen == 26) return;
		
		for (int d = 0; d<4; d++) {
			int nx = x + dirx[d];
			int ny = y + diry[d];
			if (0>nx || nx>=R || 0>ny || ny>=C) continue;
			int idx = board[nx][ny] - 'A';
			if (used[idx]) continue;
			used[idx] = true;
			dfs(nx, ny, len+1, used);
			used[idx] = false;
		}
	}
}
