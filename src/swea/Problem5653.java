package swea;

import java.io.*;
import java.util.*;

public class Problem5653 {
	static int N, M, K, maxKey;
	static int[][] board;
	static int[] dirx = {1,-1,0,0};
	static int[] diry = {0,0,1,-1};
	static Map<Integer, List<List<Integer>>> map;
	static Map<List<Integer>, Integer> spread;
	static Set<List<Integer>> store;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]); M = Integer.parseInt(input[1]); K = Integer.parseInt(input[2]);
			
			board = new int[N][M];
			map = new HashMap<>();
			spread = new HashMap<>();
			store = new HashSet<>();
			maxKey = Integer.MIN_VALUE;
			for (int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j<M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					
					if (board[i][j]>=1) {
						
						List<List<Integer>> list = new ArrayList<>();
						List<Integer> loc = new ArrayList<>();
						List<Integer> locStore = new ArrayList<>(); 
						loc.add(i); loc.add(j); loc.add(board[i][j]);
						locStore.add(i); locStore.add(j);
						maxKey = Math.max(maxKey, board[i][j]);
						if (map.containsKey(board[i][j])) {
							map.get(board[i][j]).add(loc);
						} else {
							list.add(loc);
							map.put(board[i][j], list);
						}
						store.add(locStore);
						
					}
				}
			}
			System.out.println("map "+map);
			System.out.println("store "+store);
			// 키 
			
			for (int k = 1; k<K; k++) {
				// 번식 저장 
				List<List<Integer>> locations  = map.get(1); 
				for (List<Integer> loc : locations) {
					int cx = loc.get(0), cy = loc.get(1), life = loc.get(2);
					maxKey = Math.max(life, maxKey);
					for (int d = 0; d<4; d++) {
						int nx = cx + dirx[d];
						int ny = cy + diry[d];
						
						List<Integer> key = new ArrayList<>();
						key.add(nx); key.add(ny);
						
						// 이미 생명력이 존재하는 좌표는 패스 
						if (store.contains(key)) continue;
						
						// 좌표가 번식에 존재 하면서 기존 생명보다 클 때 
						if (spread.containsKey(key) && spread.get(key) < life) {
							spread.put(key, life);

						} else if (!spread.containsKey(key)) {
							spread.put(key, life);
						}
					}
				}
				System.out.println("spread "+ spread);
				
				// map -1 하기
				for (int key : map.keySet()) {
					if (key == 1) continue;
					map.put(key-1, map.get(key));
				}
				map.remove(maxKey);
				
				System.out.println("맵 갱신 " +map);
				
				// 번식 값 store, map에 갱신
				for (List<Integer> loc : spread.keySet()) {
					store.add(loc);
					int life = spread.get(loc);
					loc.add(life);
					map.get(life).add(loc);
				}
				
				System.out.println("맵 재저장 " +map);
			}
			
			System.out.println(store.size());
		}
	}
}
