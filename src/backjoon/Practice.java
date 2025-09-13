package backjoon;
import java.io.*;
import java.util.*;

public class Practice {
	static int N,M,X;
	static List<List<int[]>> map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		X = Integer.parseInt(input[2]);
		map = new ArrayList<>();
		for (int n = 0; n<=N; n++) map.add(new ArrayList<>());
				
		for (int i = 0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map.get(a).add(new int[] {b,d});
		}
		
		// x에서 마을로 가는 길
		int[] distances = dijkstra(X);
//		System.out.println(Arrays.toString(distances));
		int maxTime = Integer.MIN_VALUE;
		// 마을에서 x로 가는 길
		for (int n = 1; n<=N; n++) {
			if (n == X) continue;
			int to = dijkstraTo(n);
			int from = distances[n];
			if (to+from > maxTime) maxTime = to+from;
		}
		System.out.println(maxTime);
	}
	
	static int[] dijkstra(int node) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.offer(new int[] {node, 0});
		int[] distance = new int[N+1];                                          
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[node] = 0;
		boolean[] visited = new boolean[N+1];
		                 
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int from = cur[0], dis = cur[1];
			if (dis > distance[from]) continue;
			visited[from] = true;
			for (int[] next : map.get(from)) {
				int to = next[0];
				if (visited[to]) continue;
				
				int newDis = dis + next[1];
				if (newDis < distance[to]) {
//					System.out.println(Arrays.toString(new int[] {to,newDis}));
					pq.offer(new int[] {to, newDis});
					distance[to] = newDis;
				}
			}
		}
		return distance;
	}
	
	static int dijkstraTo(int s){
		  int[] dist = new int[N+1];
		  Arrays.fill(dist, Integer.MAX_VALUE);
		  boolean[] vis = new boolean[N+1];
		  PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
		  dist[s]=0; pq.offer(new int[]{s,0});

		  while(!pq.isEmpty()){
		    int[] cur = pq.poll();
		    int u=cur[0], d=cur[1];
		    if (vis[u]) continue;
		    vis[u]=true;
		    if (u == X) return d; // 여기서 종료 가능: 최단 확정 순간

		    for (int[] e : map.get(u)){
		      int v=e[0], w=e[1];
		      if (dist[v] > d + w){
		        dist[v] = d + w;
		        pq.offer(new int[]{v, dist[v]});
		      }
		    }
		  }
		  return Integer.MAX_VALUE; // 도달 불가
	}

}
