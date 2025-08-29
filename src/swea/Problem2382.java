package swea;
import java.io.*;
import java.util.*;
public class Problem2382 {
	static int N,M,K;
	static int[][] board;
	static int dirx[] = {0,-1,1,0,0};
	static int diry[] = {0,0,0,-1,1};
	static Map<List<Integer>, int[]> locMap;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			K = Integer.parseInt(input[2]);
			
			locMap = new HashMap<>();
			for (int i = 0; i<K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				List<Integer> key = new ArrayList<>();
				key.add(x); key.add(y);
				locMap.put(key, new int[] {cnt, d});
			}
			
			solution();
			int answer = 0;
			for (int[] val : locMap.values()) {
				answer += val[0];
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void solution() {
		for (int i = 0; i<M; i++) {
			Map<List<Integer>, List<int[]>> store = new HashMap<>();
			for (List<Integer> loc : locMap.keySet()) {
				int x = loc.get(0);
				int y = loc.get(1);
				int cnt = locMap.get(loc)[0];
				int d = locMap.get(loc)[1];
				int nx = x + dirx[d], ny = y + diry[d];
				
				if (nx == 0 || ny == 0 || nx == N-1 || ny == N-1) {
					d = changeDir(d);
					cnt /= 2;
				}
				List<Integer> key = new ArrayList<>();
				key.add(nx); key.add(ny);
				store.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[] {cnt, d});
			}
			
			locMap = new HashMap<>();
			for (List<Integer> loc : store.keySet()) {
				if (store.get(loc).size()>1) {
					List<int[]> values = store.get(loc);
					int maxCnt = 0;
					int maxD = 0;
					int total = 0;
					for (int[] value : values) {
						if (value[0]>maxCnt) {
							maxCnt = value[0];
							maxD = value[1];
						}
						total += value[0];
					}
					locMap.put(loc, new int[] {total,maxD});
				} else {
					locMap.put(loc, store.get(loc).get(0));
				}
			}
		}
	}
	
	static int changeDir(int d) {
		if (d == 1) return 2;
		else if (d == 2) return 1;
		else if (d == 3) return 4;
		else return 3;
	}
}
