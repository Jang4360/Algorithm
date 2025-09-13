package backjoon;

import java.io.*;
import java.util.*;
public class Problem1238 {
	static int N,M,X;
	static List<List<int[]>> fromTo;
	static List<List<int[]>> toFrom;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		X = Integer.parseInt(input[2]);
		
		fromTo = new ArrayList<>();
		toFrom = new ArrayList<>();
		
		for (int n = 0; n<=N; n++) fromTo.add(new ArrayList<>());
		for (int n = 0; n<=N; n++) toFrom.add(new ArrayList<>());
				
		for (int i = 0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			fromTo.get(a).add(new int[] {b,t});
			toFrom.get(b).add(new int[] {a,t});
		}
		
		int[] timeForward = dijkstra(fromTo);
		int[] timeBackward = dijkstra(toFrom);
		
		int maxTime = Integer.MIN_VALUE;

		for (int n = 1; n<=N; n++) {
			if (n == X) continue;
			int time = timeForward[n] + timeBackward[n];
			maxTime = Math.max(maxTime, time);
		}
		
		System.out.println(maxTime);
	}
	
	static int[] dijkstra(List<List<int[]>> list) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.offer(new int[] {X, 0});
		
		int[] timeArr = new int[N+1];                                          
		Arrays.fill(timeArr, Integer.MAX_VALUE);
		timeArr[X] = 0;
		
		boolean[] visited = new boolean[N+1];
		                 
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int from = cur[0], time = cur[1];
			if (time > timeArr[from]) continue;
			visited[from] = true;
			
			for (int[] next : list.get(from)) {
				int to = next[0];
				if (visited[to]) continue;
				
				int newTime = time + next[1];
				if (newTime < timeArr[to]) {
					pq.offer(new int[] {to, newTime});
					timeArr[to] = newTime;
				}
			}
		}
		return timeArr;
	}
}
