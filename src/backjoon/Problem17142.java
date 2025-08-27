package backjoon;
import java.io.*;
import java.util.*;
public class Problem17142 {
	static int N, M, emptyCnt,best;
	static int[][] labs;
	static List<int[]> virus;
	static int[] dirx = {1,0,0,-1};
	static int[] diry = {0,1,-1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		labs = new int[N][N];
		virus = new ArrayList<>();
		for (int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j<N; j++) {
				labs[i][j] = Integer.parseInt(st.nextToken());
				if (labs[i][j] == 2) virus.add(new int[] {i,j});
				else if (labs[i][j] == 0) emptyCnt++;
			}
		}
		if (emptyCnt == 0) {
			System.out.println(0);
			return;
		}
		best = Integer.MAX_VALUE;
		comb(0, new ArrayList<>());
		
		System.out.println(best == Integer.MAX_VALUE ? -1 : best);
	}
	
	static void comb(int start, List<int[]> store) {
		if (store.size() == M) {
			spread(store);
			return;
		}
		
		for (int i = start; i<virus.size(); i++) {
			store.add(virus.get(i));
			comb(i+1, store);
			store.remove(store.size()-1);
		}
	}
	
	static void spread(List<int[]> list) {
		int[][] dist = new int[N][N];
		for (int i = 0; i<N; i++) Arrays.fill(dist[i], -1);
		
		Deque<int[]> q = new ArrayDeque<>();
		for (int[] loc: list) {
			q.offer(loc);
			dist[loc[0]][loc[1]] = 0;
		}
		
		int infected = 0;
		int maxTime = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (maxTime >= best) return;
			int t = dist[cur[0]][cur[1]];
			
			for (int d = 0; d<4; d++) {
				int nx = cur[0]+dirx[d];
				int ny = cur[1]+diry[d];
				if (0<=nx && nx<N && 0<=ny && ny<N && labs[nx][ny] != 1 && dist[nx][ny] == -1) {
					dist[nx][ny] = t+1;
					q.offer(new int[] {nx,ny});
					
					if (labs[nx][ny] == 0) {
						infected++;
						maxTime = Math.max(maxTime, t+1);
						if (infected == emptyCnt) {
							best = Math.min(best, maxTime);
							return;
						}
					}
				}
			}
		}
	}
}
