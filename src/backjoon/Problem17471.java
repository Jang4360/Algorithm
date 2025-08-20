package backjoon;
import java.io.*;
import java.util.*;
public class Problem17471 {
	static Map<Integer, List<Integer>> map;
	static int N, minGap;
	static int[] people;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		map = new HashMap<>();
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		for (int i = 1; i<=N; i++) {
			people[i] = Integer.parseInt(st1.nextToken());
		}
		
		int cntZero = 0;
		for (int i = 1; i<=N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st2.nextToken());
			for (int j = 0; j<n; j++) {
				int node = Integer.parseInt(st2.nextToken());
				map.computeIfAbsent(i, k -> new ArrayList<>()).add(node);
			}
			if (n == 0) {
				map.put(i, new ArrayList<>());
				cntZero++;
			}
		}
		
		if (N == 2) {
			System.out.println(Math.abs(people[1]-people[2]));
		}
		else if (cntZero >= 2) {
			System.out.println(-1);
		} else {
			minGap = Integer.MAX_VALUE;
			for (int i = N/2; i<N; i++) {
				comb(i, 1, new ArrayList<>());
			}
			if (minGap == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(minGap);
		}
	}
	
	static void comb(int len, int start, List<Integer> list) {
		if (len == list.size()) {
			List<Integer> op = new ArrayList<>();
			for (int key : map.keySet()) {
				if (!list.contains(key)) op.add(key);
			}

			if (isConnected(list) && isConnected(op)) {
				calculate(list, op);
			}
		}
		
		for (int i = start; i<=N; i++) {
			list.add(i);
			comb(len, i+1, list);
			list.remove(list.size()-1);
		}
	}
	
	static boolean isConnected(List<Integer> list) {
		int cnt = 1;
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		visited[list.get(0)] = true;
		q.offer(list.get(0));
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			List<Integer> next = map.get(cur);
			for (int n : next) {
				if (!visited[n] && list.contains(n)) {
					visited[n] = true;
					q.offer(n);
					cnt++;
				}
			}
 		}
		if (cnt == list.size()) return true;
		else return false;
	}
	
	static void calculate(List<Integer> cur, List<Integer> op) {
		int aSum = 0, bSum = 0;
		for (int a : cur) aSum += people[a];
		for (int b : op) bSum += people[b];
		minGap = Math.min(minGap, Math.abs(aSum-bSum));
	}
}
