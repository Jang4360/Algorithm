package swea;

import java.io.*;
import java.util.*;
public class Problem1267 {
	static Map<Integer,List<Integer>> topDown, downTop;
	static List<Integer> result, parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t<=10; t++) {
			String[] input = br.readLine().split(" ");
			topDown = new HashMap<>();
			downTop = new HashMap<>();
			int V = Integer.parseInt(input[0]);
			int E = Integer.parseInt(input[1]);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i<E; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				topDown.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
				downTop.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
			}
			
			// 부모키 탐색 
			parents = new ArrayList<>();
			for (int key : downTop.keySet()) {
				findParent(downTop, key);
			}
			solution();
			
			sb.append("#").append(t);
			for (int i = 0; i < result.size(); i++) {
				sb.append(" ").append(result.get(i));
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void findParent(Map<Integer,List<Integer>> map, int key) {
		
		if (!map.containsKey(key) && !parents.contains(key)) {
			parents.add(key);
			return;
		}
		
		List<Integer> nextList = map.get(key);
		for (int next: nextList) {
			if (!parents.contains(next)) {
				findParent(map, next);
			}
		}
	}
	
	static void solution() {
		// 한칸씩 탐색
		result = new ArrayList<>();
		Deque<Integer> q = new ArrayDeque<>();
		for (int parent : parents) {
			q.offer(parent);
			result.add(parent);
		}
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (topDown.containsKey(cur)) {
				List<Integer> nextList = topDown.get(cur);
				for (int next : nextList) {
					if (result.contains(next)) continue;
					if (downTop.containsKey(next) && downTop.get(next).size()>1) {
						List<Integer> beforeList = downTop.get(next);
						int cnt = 0;
						for (int before : beforeList) if (result.contains(before)) cnt++;
						if (beforeList.size() == cnt) {
							result.add(next);
							q.offer(next);
						}
					} else {
						result.add(next);
						q.offer(next);
					}
				}
			}
		}
	}
}
