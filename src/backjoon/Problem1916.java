package backjoon;
import java.io.*;
import java.util.*;
public class Problem1916 {
	static int N, M;
	static List<List<int[]>> map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new ArrayList<>();
		for (int i = 0; i<=N; i++) map.add(new ArrayList<>());
		
		for (int i = 0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			map.get(a).add(new int[] {b,w});
//			map.get(b).add(new int[] {a,w});
		}
		String[] loc = br.readLine().split(" ");
		int start = Integer.parseInt(loc[0]);
		int end = Integer.parseInt(loc[1]);
		int answer = dijkstra(start,end);
		System.out.println(answer);
	}
	
	static int dijkstra(int start, int end) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
		pq.offer(new int[] {start,0});
		
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cNode = cur[0], dis = cur[1];
			
			if (dis != distance[cNode]) continue;
			
			for (int[] next : map.get(cNode)) {
				int nNode = next[0], nDis = next[1];
				int newDis = nDis + dis;
				
				if (newDis < distance[nNode]) {
					distance[nNode] = newDis;
					pq.offer(new int[] {nNode, newDis});
				}
			}
		}
		return distance[end];		
	}
}
