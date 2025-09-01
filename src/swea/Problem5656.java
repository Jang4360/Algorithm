package swea;

import java.io.*;
import java.util.*;

public class Problem5656 {
	static int N, W, H, minBlock;
	static int[][] board;
	static int[] depth;
	static int[] dirx = {1,0,0,-1};
	static int[] diry = {0,1,-1,0};
	
	static List<List<Integer>> works;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			W = Integer.parseInt(input[1]);
			H = Integer.parseInt(input[2]);
			
			board = new int[H][W];
			for (int i = 0; i<H; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j<W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			minBlock = Integer.MAX_VALUE;
			comb(new ArrayList<>());
			System.out.println("#" + t + " " + minBlock);
		}
	}
	
	static void comb(List<Integer> list) {
		if (list.size() == N) {
			drop(list);
			return;
		}
		
		for (int i = 0; i<W; i++) {
			list.add(i);
			comb(list);
			list.remove(list.size()-1);
		}
	}
	
	// 떨어뜨리기
	static void drop(List<Integer> colList) {
		int[][] newBoard = new int[H][];
		for (int i = 0; i<H; i++) {
			newBoard[i] = Arrays.copyOf(board[i], W);
		}
		
		int total = 0;
		for (int c : colList) {
			Deque<int[]> q = new ArrayDeque<>();
			int r = 0;
			while (r<H) {
				if (newBoard[r][c]>=1) {
					if (newBoard[r][c]>1) q.offer(new int[] {r,c,newBoard[r][c]-1});
					newBoard[r][c] = 0;
					break;
				}
				r += dirx[0];
			}
		
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0], y = cur[1], weight = cur[2];
				
				for (int d = 0; d<4; d++) {
					int nx = x + dirx[d], ny = y + diry[d];
					for (int w = 0; w<weight; w++) {
						if (0>nx || nx>=H || 0>ny || ny>=W) break;
						if (newBoard[nx][ny]>1) q.offer(new int[] {nx,ny,newBoard[nx][ny]-1});
						newBoard[nx][ny] = 0;
						nx += dirx[d];
						ny += diry[d];
					}
				}
			
			}
			
			total = reLocate(newBoard);
		}
		minBlock = Math.min(total, minBlock);
	}
	
	static int reLocate(int[][] newBoard) {
		int total = 0;
		for (int j = 0; j<W; j++) {
			Stack<Integer> stack = new Stack<>();
			for (int i = 0; i<H; i++) {
				if (newBoard[i][j]>0) {
					stack.push(newBoard[i][j]);
					total++;
				}
			}
			
			for (int i = H-1; i>=0; i--) {
				if (!stack.isEmpty()) {
					newBoard[i][j] = stack.pop();
				} else {
					newBoard[i][j] = 0;
				}
			}
		}
		return total;
	}
}


