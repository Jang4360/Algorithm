package swea;
import java.util.*;
import java.io.*;
public class Problem4012 {
	static int N, minGap;
	static int[][] board;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			for (int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			minGap = Integer.MAX_VALUE;
			backtrack(0, N/2, new ArrayList<>());
			System.out.println("#" + t + " " + minGap);
		}
	}
	
	static void backtrack(int start, int len, List<Integer> list) {
		if (len == list.size()) {
			calculate(list);
			return;
		}
		
		for (int i = start; i<N; i++) {
			list.add(i);
			backtrack(i+1, len, list);
			list.remove(list.size()-1);
		}
	}
	
	static void calculate(List<Integer> list1) {
		List<Integer> list2 = new ArrayList<>();
		for (int n = 0; n<N; n++) {
			if (!list1.contains(n)) list2.add(n);
		}
		
		int sum1 = 0, sum2 = 0;
		for (int i = 0; i<list1.size()-1; i++) {
			int x = list1.get(i);
			for (int j = i+1; j<list1.size();j++) {
				int y = list1.get(j);
				sum1 += board[x][y];
				sum1 += board[y][x];
			}
		}
		
		for (int i = 0; i<list2.size()-1; i++) {
			int x = list2.get(i);
			for (int j = i+1; j<list2.size();j++) {
				int y = list2.get(j);
				sum2 += board[x][y];
				sum2 += board[y][x];
			}
		}
		minGap = Math.min(Math.abs(sum1-sum2), minGap);
	}
}
