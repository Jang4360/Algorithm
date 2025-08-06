package backjoon;
import java.util.*;
import java.io.*;
public class Problem15649 {
	static List<List<Integer>> result;
	static int N;
	static int M;
	static int[] array;
	static boolean[] visited;
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		array = new int[N];
		for (int i = 1; i<=N; i++) {
			array[i-1] = i;
		
		}
		
		visited = new boolean[N];
		result = new ArrayList<>();
		dfs(new ArrayList<>());
		
		StringBuilder sb = new StringBuilder();
		for (List<Integer> res : result) {
			for (int i = 0; i<res.size(); i++) {
				sb.append(res.get(i));
				if (i<res.size()-1) sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void dfs(List<Integer> list) {
		if (list.size() == M) {
			result.add(new ArrayList<>(list));
			
			return;
		}
		
		for (int i = 0; i<array.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				list.add(array[i]);
				dfs(list);
				visited[i] = false;
				list.remove(list.size()-1);
			}
		}
	}
	
}
