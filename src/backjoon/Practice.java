package backjoon;
import java.io.*;
import java.util.*;

public class Practice {
	static int minDist, N, M;
	static int[][] road;
	static List<int[]> chicken, home; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		road = new int[N][N];
		for (int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j<N; j++) {
				road[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		minDist = Integer.MAX_VALUE;
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		for (int i = 0; i<N; i++) {
			for (int j = 0; j<N; j++) {
				if (road[i][j] == 2) chicken.add(new int[] {i,j});
				else if (road[i][j] == 1) home.add(new int[] {i,j});
			}
		}
		comb(0, new ArrayList<>());
		System.out.println(minDist);
	}
	
	static void comb(int start, List<int[]> list) {
		if (list.size() == M) {
			calculate(list);
			return;
		}
		
		for (int i = start; i<chicken.size(); i++) {
			list.add(chicken.get(i));
			comb(i+1, list);
			list.remove(list.size()-1);
		}
	}
	
	static void calculate(List<int[]> ch) {
		int dist = 0;
		for (int[] h : home) {
			int minD = Integer.MAX_VALUE;
			for (int[] loc : ch) {
				minD = Math.min(Math.abs(loc[0]-h[0]) + Math.abs(loc[1]-h[1]),minD);
			}
			dist += minD;
		}
		
		minDist = Math.min(dist, minDist);
	}
}