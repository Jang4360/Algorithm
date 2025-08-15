package swea;

import java.io.*;
import java.util.*;

public class Problem5644 {
	static int[] dirx = {0,0,1,0,-1};
	static int[] diry = {0,-1,0,1,0};
	static int M, A;
	static int[] aArr, bArr, Ax, Ay, C, P;
	static int ax, ay, bx, by; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			M = Integer.parseInt(input[0]);
			A = Integer.parseInt(input[1]);
			
			aArr = new int[M];
			bArr = new int[M];
			
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int i = 0; i<M; i++) aArr[i] = Integer.parseInt(st1.nextToken());

			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int i = 0; i<M; i++) bArr[i] = Integer.parseInt(st2.nextToken());

			Ax = new int[A];
			Ay = new int[A];
			C = new int[A];
			P = new int[A];
			for (int i = 0; i<A; i++) {
				StringTokenizer st3 = new StringTokenizer(br.readLine());
				Ax[i] = Integer.parseInt(st3.nextToken());
				Ay[i] = Integer.parseInt(st3.nextToken());
				C[i] = Integer.parseInt(st3.nextToken());
				P[i] = Integer.parseInt(st3.nextToken());
			}
			
			ax = 1; ay = 1; bx = 10; by = 10;
			int answer = 0;
			for (int i = 0; i<=M; i++) {
				answer += move();
				if (i == M) break;
				ax += dirx[aArr[i]];
				ay += diry[aArr[i]];
				bx += dirx[bArr[i]];
				by += diry[bArr[i]];
			}
			System.out.println("#" + t + " " + answer);
		}
	}
	
	static int move() {
		boolean[] aPossible = new boolean[A];
		boolean[] bPossible = new boolean[A];
		for (int i = 0; i<A; i++) {
			aPossible[i] = manhatten(Ax[i], Ay[i], ax, ay, C[i]);
			bPossible[i] = manhatten(Ax[i], Ay[i], bx, by, C[i]);
		}
		
		int best = 0;
		for (int i = -1; i<A; i++) {
			if (i!=-1 && !aPossible[i]) continue;
			int aVal = (i == -1) ? 0 : P[i];
			for (int j = -1; j<A; j++) {
				if (j!=-1 && !bPossible[j]) continue;
				int bVal = (j == -1) ? 0 : P[j];
				
				int sum = 0;
				if (i!=-1 && i==j) sum = P[i];
				else sum = aVal + bVal;
				
				if (sum>best) best = sum;
			}
		}
		return best;
	}
	
	static boolean manhatten(int dx, int dy, int x, int y, int c) {
		if (Math.abs(x-dx) + Math.abs(y-dy) <= c) return true;
		else return false;
	}
}
