package backjoon;
import java.io.*;
import java.util.*;
public class Problem14889 {
	static int N;
	static int[] team;
	static int answer = Integer.MAX_VALUE;
	static int[][] board;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[]visited = new boolean[N];
		team = new int[N];
		for (int i = 0; i<N; i++) team[i] = i;
		List<Integer> current = new ArrayList<>();
 		int r = N/2;
 		combine(team, 0, r, new ArrayList<>(), visited);
		System.out.println(answer);
	}
	
	
	static void combine(int[] team, int start, int r, List<Integer> current, boolean[] visited) {
		if (current.size() == r) {
			solution(current);
			return;
		}
		
		for (int i = start; i<team.length; i++) {
			current.add(team[i]);
			combine(team, i+1, r, current, visited);
			current.remove(current.size()-1);
		}
	}
	
	static void backtrack(int[] team, List<Integer> current, boolean[] visited, List<List<Integer>> result) {
		if (current.size() == 2) {
			result.add(new ArrayList<>(current));
			return;
		}
		
		for (int i = 0; i<team.length; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			current.add(team[i]);
			backtrack(team, current, visited, result);
			visited[i] = false;
			current.remove(current.size()-1);
		}
	}
	
	
	static void solution(List<Integer> startTeam) {
		List<Integer> linkTeam = new ArrayList<>();
		for (int i = 0; i<N; i++) {
			if (!startTeam.contains(team[i])) {
				linkTeam.add(team[i]);
			}
		}
		int[] lTeam = linkTeam.stream().mapToInt(i->i).toArray();
		int[] sTeam = startTeam.stream().mapToInt(i->i).toArray();
 		
 		// 각 팀별 N/2 C 2 한 경우를 구해서 값을 더한다.
		List<List<Integer>> result = new ArrayList<>();
		boolean[] visited = new boolean[lTeam.length];
		backtrack(lTeam, new ArrayList<>(), visited, result);
		
		int lTotal = 0;
		for (List<Integer> comb: result) {
			int a = comb.get(0);
			int b = comb.get(1);
			
			lTotal += board[a][b];
			lTotal += board[b][a];
		}
		
		result = new ArrayList<>();
		visited = new boolean[lTeam.length];
		backtrack(sTeam, new ArrayList<>(), visited, result);

		int sTotal = 0;
		for (List<Integer> comb: result) {
			int a = comb.get(0);
			int b = comb.get(1);
			
			sTotal += board[a][b];
			sTotal += board[b][a];
		}
		answer = Math.min(answer, Math.abs(sTotal-lTotal));
	}
}
