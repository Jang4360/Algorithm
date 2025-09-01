package backjoon;
import java.io.*;
import java.util.*;
public class Problem17472 {
	static int N,M,idx;
	static int[][] board;
	static List<int[]> islandLoc;
	static Map<List<Integer>, Integer> map;
	static int[] parents, rank;
	static boolean[][] visited;
	static int[] dirx = {1,0,0,-1};
	static int[] diry = {0,1,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		board = new int[N][M];
		islandLoc = new ArrayList<>();
		for (int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) islandLoc.add(new int[] {i,j});
			}
		}
		
		idx = 0;
		visited = new boolean[N][M];
		// 섬 만들기
		for (int[] loc : islandLoc) {
			int i = loc[0], j = loc[1];
			if (board[i][j] == 1 && !visited[i][j]) {
				makeIsland(i,j,++idx);
			}
		}
	
		map = new HashMap<>();
		// 간선 구하기
		for (int[] loc : islandLoc) {
			makeConnect(loc[0], loc[1]);
		}
		
		// mst 구하기
		List<int[]> edges = new ArrayList<>();
		for (List<Integer> key : map.keySet()) {
			int a = key.get(0), b = key.get(1);
			int w = map.get(key);
			edges.add(new int[] {a,b,w});
		}
		
		Collections.sort(edges, Comparator.comparingInt(a -> a[2]));
		parents = new int[idx+1];
		rank = new int[idx+1];
		make();
		
		int result = 0;
		int picked = 0;
		for (int[] edge : edges) {
			if (union(edge[0], edge[1])) {
				picked++;
				result += edge[2];
			}
			if (picked == idx-1) break;
		}
		
		int answer = (picked == idx-1) ? result : -1;
		System.out.println(answer);
	}
	
	// 섬 만들기
	static void makeIsland(int x, int y, int num) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x,y});
		visited[x][y] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			board[cur[0]][cur[1]] = num;
			for (int d = 0; d<4; d++) {
				int nx = cur[0] + dirx[d];
				int ny = cur[1] + diry[d];
				if (0>nx || nx>=N || 0>ny || ny>=M || visited[nx][ny] || board[nx][ny] == 0) continue;
				visited[nx][ny] = true;
				q.offer(new int[] {nx,ny});
			}
		}
	}
	
	// 간선 구하기
	static void makeConnect(int x, int y) {
		int start = board[x][y];
		for (int d = 0; d<4; d++) {
			int cnt = 0;
			int nx = x + dirx[d], ny = y + diry[d];
			
			while (0<=nx && nx<N && 0<=ny && ny<M && board[nx][ny] == 0) {
				nx += dirx[d];
				ny += diry[d];
				cnt++;
			}
			if (0>nx || nx>=N || 0>ny || ny>=M || cnt<2 || board[nx][ny] == start) continue;
			int end = board[nx][ny];
			
			// map 키 생성 
			List<Integer> key = new ArrayList<>();
			if (start>end) {
				key.add(end); key.add(start);
			} else {
				key.add(start); key.add(end);
			}
			
			if (map.containsKey(key)) {
				if (map.get(key) > cnt) map.put(key, cnt);
			} else {
				map.put(key, cnt);
			}
		}
	}
	
	// mst
	static void make() {
		for (int i = 0; i<=idx; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int node) {
		if (node == parents[node]) return node;
		return parents[node] = find(parents[node]);
	}
	
	static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if (ra == rb) return false;
		
		if (rank[ra] > rank[rb]) parents[rb] = ra;
		else if (rank[ra] < rank[rb]) parents[ra] = rb;
		else {parents[rb] = ra; rank[ra]++;}
		return true;
	}
}
