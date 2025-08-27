package swea;
import java.io.*;
import java.util.*;
public class Problem17779 {
	static int N, sx, sy;
	static int[][] area;
	static int[] dirx = {1,1,-1,-1};
	static int[] diry = {1,-1,-1,1};
	static List<List<int[]>> store;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		area = new int[N][N];
		for (int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j<N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		store = new ArrayList<>();
		for (int i = 0; i<N-2; i++) {
			for (int j = 1; j<N-1; j++) {
				sx = i; sy = j;
				dfs(i, j, 0, new ArrayList<>());
			}
		}
	}
	
	static void dfs(int x, int y, int dir, List<int[]> loc) {
		for (int d = dir; d<4; d++) {
			int nx = x + dirx[d], ny = y + diry[d];
			if (nx<0 || nx>=N || ny<0 || ny>=N) continue;
			loc.add(new int[] {nx,ny});
			dfs(x, y, dir+1, loc);
			dfs(x, y, dir, loc);
			if (x == sx && y == sy) {
//				if (cnt>=3 && d == 3) {
//					store.add
				}
			}
		}
	}
}
