package backjoon;
import java.io.*;
import java.util.*;
public class Problem15685 {
	static int[][] board;
	static int[][] dirs = {{1,0},{0,-1},{-1,0},{0,1}}; // 문제에서 말하는 x,y가 배열 기준으로 y,x로 바뀜
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		board = new int[101][101];
		
		for (int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			solution(x,y,d,g);
		}
		int total = 0;
		for (int i = 0; i<100; i++) {
			for (int j = 0; j<100; j++) {
				if ((board[i][j] == 1) && (board[i+1][j] == 1) && (board[i][j+1] == 1) && (board[i+1][j+1]==1)) {
					total++;
				}
			}
		}
		System.out.println(total);
	}
	
	static void solution(int x, int y, int d, int g) {
		List<Integer> direction = new ArrayList<>();
		direction.add(d);
		
		for (int i = 0; i<g; i++) {
			List<Integer> temp = new ArrayList<>();
			for (int j = direction.size()-1; j>=0; j--) { // 제일 마지막에 들어온 위치부터 방향 탐색 시작 
				temp.add((direction.get(j)+1)%4); // 방향 4개 탐색 
			}
			direction.addAll(temp);
		}
		
		board[y][x] = 1;
		for (int dir: direction) {
			x += dirs[dir][0];
			y += dirs[dir][1];
			if (0<=x && x<=100 && 0<=y && y<=100) {
				board[y][x] = 1;
			}
		}
	}
}
