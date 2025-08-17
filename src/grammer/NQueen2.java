package grammer;

import java.io.*;
import java.util.*;

public class NQueen2 {
	static int N;
	static int[][] board;
	static boolean[] col, slash, bslash;
	static int answer;
 	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new boolean[N+1];
		slash = new boolean[2*N+1];
		bslash = new boolean[2*N];
		answer = 0;
		backTracking(0);
		System.out.println(answer);
	}
	
 	static void backTracking(int row) {
 		if (row == N) {
 			++answer;
 			return;
 		}
 		
 		for (int c = 0; c<N; c++) {
 			if (col[c] || slash[row+c] || bslash[row-c+N]) continue;
 			
 			col[c] = slash[row+c] = bslash[row-c+N] = true;
 			backTracking(row+1);
 			col[c] = slash[row+c] = bslash[row-c+N] = false;
 		}
 	}
	
}
