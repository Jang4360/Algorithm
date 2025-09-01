package backjoon;
import java.io.*;
import java.util.*;

public class Practice {
	static int N, minGap;
	static int[] people;
	static boolean[] visited;
	static List<List<Integer>> map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i<=N; i++) people[i] = Integer.parseInt(st.nextToken());
		
		map = new ArrayList<>();
		for (int m = 0; m<=N; m++) map.add(new ArrayList<>());
		int zeroCnt = 0;
		for (int i = 1; i<=N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st2.nextToken());
			if (n == 0) zeroCnt++;
			for (int j = 0; j<n; j++) {
				map.get(i).add(Integer.parseInt(st2.nextToken()));
			}
		}
		if (N==2) {
			System.out.println(Math.abs(people[1]-people[2]));
		} else if (zeroCnt >= 2) {
			System.out.println(-1);
		} else {
			minGap = Integer.MAX_VALUE;
			for (int i = N/2; i>=1; i--) {
				visited = new boolean[N+1];
				comb(i, 1, new ArrayList<>());
			}
			System.out.println((minGap == Integer.MAX_VALUE) ? -1 : minGap);
		}
	}
	
	static void comb(int len, int start, List<Integer> list) {
		if (len == list.size()) {
			List<Integer> opposite = new ArrayList<>();
			for (int i = 1; i<=N; i++) if (!visited[i]) opposite.add(i);
			int op = isConnect(opposite);
			int li = isConnect(list);
			if (op == -1 || li == -1) return;
			minGap = Math.min(Math.abs(op-li), minGap);
			return;
		}
		
		for (int i = start; i<=N; i++) {
			list.add(i);
			visited[i] = true;
			comb(len, i+1, list);
			list.remove(list.size()-1);
			visited[i] = false;
		}
	}
	
	static int isConnect(List<Integer> list) {
		int length = list.size();
		int cnt = 1;
		int total = 0;
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visitNode = new boolean[N+1];
		q.offer(list.get(0));
		visitNode[list.get(0)] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			total += people[cur];
			for (int next : map.get(cur)) {
				if (visitNode[next]) continue;
				if (list.contains(next)) {
					q.offer(next);
					visitNode[next] = true;
					cnt++;
				}
			}
		}
		if (cnt != length) return -1;
		return total;
	}
}