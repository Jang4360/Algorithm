package swea;
import java.io.*;
import java.util.*;
public class Problem7465_1 {
	static int N,M;
	static boolean[] visited;
	static Map<Integer,List<Integer>> map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N+1];
			map = new HashMap<>();
			for (int i = 0; i<M; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st2.nextToken());
				int b = Integer.parseInt(st2.nextToken());
				map.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
				map.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
			}
			int answer = 0;
			for (int n = 1; n<=N; n++) {
				if (visited[n]) continue;
				dfs(n);
				answer++;
			}
			System.out.println("#" + t + " " + answer);
		}
		
	}
	
	static void dfs(int node) {
		visited[node] = true;
		if (map.containsKey(node)) {
			for (int next : map.get(node)) {
				if (visited[next]) continue;
				dfs(next);
			}
		}
	}
}
