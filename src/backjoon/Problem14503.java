package backjoon;
import java.util.*;
import java.io.*;
public class Problem14503 {
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int N = 0;
	static int M = 0;
	static int[][] board;
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		String[] curLoc = br.readLine().split(" ");
		int curX = Integer.parseInt(curLoc[0]);
		int curY = Integer.parseInt(curLoc[1]);
		int curDir = Integer.parseInt(curLoc[2]);
		
		board = new int[N][M];
		
		for (int i = 0; i<N; i++) {
			String[] row = br.readLine().split(" ");
			for (int j = 0; j<M; j++) {
				board[i][j] = Integer.parseInt(row[j]);
			}
		}
		solution(curX,curY,curDir);
		System.out.println(cnt);
	}
	
	static void solution(int curX, int curY, int curDir) {
		// 1. 현재 칸 청소
		
		
		while (true) {
			
			if (board[curX][curY] == 0) {
				board[curX][curY] = -1;
				cnt ++;
			}
			
			boolean isEmpty = false;
			for (int i = 0; i<4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];
				
				if (0<=nx && nx<N && 0<=ny && ny<M && board[nx][ny] == 0) {
					isEmpty = true;
					break;
				}
			}
			
			if (isEmpty) {
				curDir = curDir>0 ? curDir-1 : 3;
				int nx = curX + dx[curDir];
				int ny = curY + dy[curDir];
				if (board[nx][ny] == 0) {
					curX = nx;
					curY = ny;
					continue;
				}
			} else {
				int newDir = curDir>1 ? curDir-2 : 2+curDir;
				int nx = curX + dx[newDir];
				int ny = curY + dy[newDir];
				if (0<=nx && nx<N && 0<=ny && ny<M && board[nx][ny] != 1) {
					curX = nx;
					curY = ny;
					continue;
				} else {
					break;
				}
			}
		}
		
	}
	
}
