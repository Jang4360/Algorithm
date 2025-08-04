package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem14500 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int[][] board = new int[N][M];
		
		for (int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int maxVal = Integer.MIN_VALUE;
		
		// 정사각형
		for (int i = 0; i<N-1; i++) {
			for (int j = 0; j<M-1; j++) {
				int total = board[i][j] + board[i+1][j+1] + board[i][j+1] + board[i+1][j];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 일자 - 세로
		for (int i = 0; i<N-3; i++) {
			for (int j = 0; j<M; j++) {
				int total = board[i][j] + board[i+1][j] + board[i+2][j] + board[i+3][j];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 일자 - 가로
		for (int i = 0; i<N; i++) {
			for (int j = 0; j<M-3; j++) {
				int total = board[i][j] + board[i][j+1] + board[i][j+2] + board[i][j+3];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// ㅜ
		for (int i = 0; i<N-1; i++) {
			for (int j = 0; j<M-2; j++) {
				int total = board[i][j] + board[i][j+1] + board[i][j+2] + board[i+1][j+1];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// ㅗ
		for (int i = 1; i<N; i++) {
			for (int j = 0; j<M-2; j++) {
				int total = board[i][j] + board[i][j+1] + board[i][j+2] + board[i-1][j+1];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// ㅏ
		for (int i = 0; i<N-2; i++) {
			for (int j = 0; j<M-1; j++) {
				int total = board[i][j] + board[i+1][j] + board[i+1][j+1] + board[i+2][j];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// ㅓ
		for (int i = 1; i<N-1; i++) {
			for (int j = 0; j<M-1; j++) {
				int total = board[i][j] + board[i][j+1] + board[i-1][j+1] + board[i+1][j+1];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 번개 - 세로 왼
		for (int i = 0; i<N-2; i++) {
			for (int j = 0; j<M-1; j++) {
				int total = board[i][j] + board[i+1][j] + board[i+1][j+1] + board[i+2][j+1];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 번개 - 세로 오른
		for (int i = 1; i<N-1; i++) {
			for (int j = 0; j<M-1; j++) {
				int total = board[i][j] + board[i+1][j] + board[i][j+1] + board[i-1][j+1];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 번개 - 가로 왼
		for (int i = 0; i<N-1; i++) {
			for (int j = 0; j<M-2; j++) {
				int total = board[i][j] + board[i][j+1] + board[i+1][j+1] + board[i+1][j+2];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 번개 - 가로 오른
		for (int i = 1; i<N; i++) {
			for (int j = 0; j<M-2; j++) {
				int total = board[i][j] + board[i][j+1] + board[i-1][j+1] + board[i-1][j+2];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 니은 - 세로 아래 왼
		for (int i = 2; i<N; i++) {
			for (int j = 0; j<M-1; j++) {
				int total = board[i][j] + board[i][j+1] + board[i-1][j+1] + board[i-2][j+1];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 니은 - 세로 아래 오른
		for (int i = 0; i<N-2; i++) {
			for (int j = 0; j<M-1; j++) {
				int total = board[i][j] + board[i+1][j] + board[i+2][j] + board[i+2][j+1];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 니은 - 세로 위 왼
		for (int i = 0; i<N-2; i++) {
			for (int j = 0; j<M-1; j++) {
				int total = board[i][j] + board[i][j+1] + board[i+1][j+1] + board[i+2][j+1];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 니은 - 세로 위 오른
		for (int i = 0; i<N-2; i++) {
			for (int j = 0; j<M-1; j++) {
				int total = board[i][j] + board[i+1][j] + board[i+2][j] + board[i][j+1];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 니은 - 가로 위 왼
		for (int i = 0; i<N-1; i++) {
			for (int j = 0; j<M-2; j++) {
				int total = board[i][j] + board[i+1][j] + board[i+1][j+1] + board[i+1][j+2];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 니은 - 가로 위 오른
		for (int i = 1; i<N; i++) {
			for (int j = 0; j<M-2; j++) {
				int total = board[i][j] + board[i][j+1] + board[i][j+2] + board[i-1][j+2];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 니은 - 가로 아래 왼
		for (int i = 0; i<N-1; i++) {
			for (int j = 0; j<M-2; j++) {
				int total = board[i][j] + board[i+1][j] + board[i][j+1] + board[i][j+2];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		// 니은 - 가로 아래 오른
		for (int i = 0; i<N-1; i++) {
			for (int j = 0; j<M-2; j++) {
				int total = board[i][j] + board[i][j+1] + board[i][j+2] + board[i+1][j+2];
				maxVal = Math.max(maxVal, total);
			}
		}
		
		System.out.println(maxVal);
	}
}
