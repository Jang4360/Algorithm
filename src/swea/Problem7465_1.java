package swea;
import java.io.*;
import java.util.*;
public class Problem7465_1 {
	static int N,M;
	static boolean[] visited;
	static List<Integer>[] store;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			store = new ArrayList[N+1];
			for (int i = 1; i<N+1; i++) {
				store[i] = new ArrayList<>();
			}
			
			for (int i = 0; i<M; i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st1.nextToken());
				int b = Integer.parseInt(st1.nextToken());
				store[a].add(b);
				store[b].add(a);
				
			}
			
			visited = new boolean[N+1];
			int answer = 0;
			for (int i = 1; i<=N; i++) {
				if (!visited[i]) {
					dfs(i);
					answer++;
				}
			}
			System.out.println("#"+t+" "+ answer);
		}
		
	}
	
	static void dfs(int node) {
		visited[node] = true;
		for (int next : store[node]) {
			if (visited[next]) continue;
			dfs(next);
		}
	}
}
