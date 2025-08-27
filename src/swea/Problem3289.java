package swea;
import java.io.*;
import java.util.*;
public class Problem3289 {
	static int[] parents;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			
			make();
			List<Integer> answer = new ArrayList<>();
			for (int i = 0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int k = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (k == 0) union(a,b);
				else if (k == 1) {
					if (find(a) == find(b)) sb.append(1);
					else sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void make() {
		parents = new int[N+1];
		for (int i = 1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int node) {
		if (node == parents[node]) return parents[node];
		return parents[node] = find(parents[node]);
	}
	
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) return;
		parents[rootB] = rootA;
	}
}
