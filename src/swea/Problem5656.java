package swea;

import java.io.*;
import java.util.*;

public class Problem5656 {
	static int N, W, H, minBlock;
	static int[][] board;
	static int[] permuteNums;
	static int[] dirx = {-1,0,1,0};
	static int[] diry = {0,1,0,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]); W = Integer.parseInt(input[1]); H = Integer.parseInt(input[2]);
			board = new int[H][W];
			for (int i = 0; i<H; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j<W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			minBlock = Integer.MAX_VALUE;
			permuteNums = new int[W];
			permute(new ArrayList<>());
			System.out.println("#"+t+" " + minBlock);
		}
	}
	
	// 중복 순열 생성
	static void permute(List<Integer> list) {
		if (list.size() == N) {
			delete(list);
			return;
		}
		
		for (int i = 0; i<W; i++) {
			list.add(i);
			permute(list);
			list.remove(list.size()-1);
		}
	}
	
	// 벽돌 제거
	static void delete(List<Integer> idxList) {
		// 딥 카피
		int[][] newBoard = new int[H][];
		for (int i = 0; i<H; i++) {
			newBoard[i] = Arrays.copyOf(board[i], W);
		}
		
		
		// 발사
		for (int idx : idxList) {
			int cx = 0, cy = idx; 
			
			while (true) {
				if (cx>=H-1 || newBoard[cx][cy] != 0) break;
				cx += dirx[2];
			}
			
			Deque<int[]> q = new ArrayDeque<>();
			
			q.offer(new int[] {cx,cy,newBoard[cx][cy]-1});
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				newBoard[cur[0]][cur[1]] = 0;
				
				for (int d = 0; d<4; d++) {
					int nx = cur[0] + dirx[d];
					int ny = cur[1] + diry[d];
					int cnt = cur[2];
					while (cnt>0) {
						if (0>nx || nx>=H || 0>ny || ny>=W || cnt==0) break; 
						if (newBoard[nx][ny] > 1) q.offer(new int[] {nx,ny,newBoard[nx][ny]-1});
						newBoard[nx][ny] = 0;
						nx += dirx[d];
						ny += diry[d];
						cnt--;
					}
				}
			}
			replace(newBoard);
		}
	}
	
	static void replace(int[][] newBoard) {
		for (int j = 0; j<W; j++) {
			List<Integer> col = new ArrayList<>(); 
			for (int i = H-1; i>=0; i--) {
				if (newBoard[i][j] > 0) col.add(newBoard[i][j]);
			}
			for (int r = H-1; r>=0; r--) {
				if (col.size()==0) newBoard[r][j] = 0;
				else newBoard[r][j] = col.remove(0);
			}
		}
		
		int total = 0;
		for (int i = 0; i<H; i++) {
			for (int j = 0; j<W; j++) {
				if (newBoard[i][j]>0) total++;
			}
		}
		minBlock = Math.min(total, minBlock);
	}
}
