package grammer;
import java.io.*;
import java.util.*;
public class Prim {
	static int V,E;
	static List<List<long[]>> adjList;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			V = Integer.parseInt(input[0]);
			E = Integer.parseInt(input[1]);
			adjList = new ArrayList<>();
			for (int i = 0; i<=V; i++) adjList.add(new ArrayList<>());

			for (int i = 0; i<E; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				adjList.get(a).add(new long[] {b,w});
				adjList.get(b).add(new long[] {a,w});
			}
			
			PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
			visited = new boolean[V+1];
			pq.offer(new long[] {1,0});
			int picked = 0;
			long result = 0L;
			
			while (!pq.isEmpty() && picked < V) {
				long[] cur = pq.poll();
				int to = (int) cur[0]; long w = cur[1];
				if (visited[to]) continue;
				visited[to] = true;
				result += w;
				picked++;
				for (long[] next : adjList.get(to)) {
					pq.offer(next);
				}
			}
			System.out.println("#"+ t + " " +result);
		}
	}
}
