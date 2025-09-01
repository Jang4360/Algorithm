package swea;
import java.util.*;
import java.io.*;
public class Problem3124 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			int V = Integer.parseInt(input[0]);
			int E = Integer.parseInt(input[1]);
			
//			List<List<long[]>> adjList = new ArrayList<>();
			Map<Integer, List<long[]>> mapList = new HashMap<>();
//			for (int i = 0; i<=V; i++) adjList.add(new ArrayList<>());
			
			for (int i = 0; i<E; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long w = Long.parseLong(st.nextToken());
				mapList.computeIfAbsent(a, k -> new ArrayList<>()).add(new long[] {w,b});
				mapList.computeIfAbsent(b, k -> new ArrayList<>()).add(new long[] {w,a});
//				adjList.get(a).add(new long[] {w,b});
//				adjList.get(b).add(new long[] {w,a});
			}
			
			PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a->a[0]));
			pq.offer(new long[] {0L,1});
			boolean[] visited = new boolean[V+1];
			int picked = 0;
			long result = 0;
			
			while (!pq.isEmpty() && picked < V) {
				long[] cur = pq.poll();
				int to = (int) cur[1];
				if (visited[to]) continue;
				visited[to] = true;
				picked++;
				result += cur[0];
				for (long[] next : mapList.get(to)) {
					if (!visited[(int) next[1]]) pq.offer(next);
				}
			}
			System.out.println("#"+ t + " " +result);
		}
	}
}
