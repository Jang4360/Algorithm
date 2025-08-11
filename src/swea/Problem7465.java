package swea;
import java.io.*;
import java.util.*;
public class Problem7465 {
	static int N,M;
	static boolean[] visited;
	static List<List<Integer>> store;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			store = new ArrayList<>();
			for (int i = 0; i<=N; i++) {
				store.add(new ArrayList<>());
			}
			
			for (int i = 0; i<M; i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st1.nextToken());
				int b = Integer.parseInt(st1.nextToken());
				store.get(a).add(b);
				store.get(b).add(a);
				
			}
			
			visited = new boolean[N+1];
			int answer = 0;
			for (int i = 1; i<=N; i++) {
				if (!visited[i]) {
					bfs(i);
					answer++;
				}
			}
			System.out.println("#"+t+" "+ answer);
		}
		
	}
	
	static void bfs(int node) {
		Queue<Integer> q = new ArrayDeque<>();
		visited[node] = true;
		q.offer(node);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			List<Integer> list = store.get(cur);
			for (int next : list) {
				if (!visited[next]) {
					q.offer(next);
					visited[next] = true;
				}
			}
		}
	}
}
