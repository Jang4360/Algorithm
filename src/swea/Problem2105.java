package swea;
import java.util.*;
import java.io.*;
public class Problem2105 {
	static int[][] board;
	static int N, maxCnt;
	static int[] dirx = {1,1,-1,-1};
	static int[] diry = {1,-1,-1,1};
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
			System.out.println("#" + t + " " +solution());
//			search(3,5,2,6,N-1);
		}
	}
	
	static int solution() {
		maxCnt = Integer.MIN_VALUE;
		for (int r = 0; r<N-2; r++) {
			for (int c = 1; c<N-1; c++) {
				for (int e = N-1; e>=2; e--) {
					for (int n = 0; n<N/2;n++) {
						int Sx1 = 0;
						int Ex1 = N-1-n;
						maxCnt = Math.max(maxCnt,search(r,c,Sx1,Ex1,e));
						int Sx2 = n;
						int Ex2 = N-1;
						maxCnt = Math.max(maxCnt,search(r,c,Sx2,Ex2,e));
						int Sx3 = n;
						int Ex3 = N-1-n;
						maxCnt = Math.max(maxCnt,search(r,c,Sx3,Ex3,e));	
						
					}
				}
			}
		}
		
		return maxCnt;
	}
	
	static int search(int sr, int sc, int Sx, int Ex , int Ey) {
		int r = sr, c = sc;
//		System.out.println("r,c: " + r +" "+ c + " / Sx,Ex: " + Sx + " " + Ex);
		List<Integer> store = new ArrayList<>();
		// 4방향 탐색하면서 맞은편 변의 길이와 동일하게 탐색 진행하는지 확인
		int[] symmetric = new int[4];
		for (int d = 0; d<4; d++) {
			r += dirx[d];
			c += diry[d];
//			System.out.println("r,c: " + r + " " + c);
			while (0<=r && r<=Ey && Sx<=c && c<=Ex) {
				if (store.contains(board[r][c])) return -1;

				store.add(board[r][c]);
				symmetric[d]++;
				if (r == sr  && c == sc) break;
				if (0>r+dirx[d] || r+dirx[d]>Ey || Sx>c+diry[d] || c+diry[d]>Ex) break;
				r += dirx[d];
				c += diry[d];
				
//				System.out.println("r,c: " + r + " " + c);
			}
		}
//		System.out.println("r,c: " + r +" "+ c + " / Sx,Ex: " + Sx + " " + Ex + store);
//		for (int s : symmetric) {
//			System.out.println(s);
//		}
		if (symmetric[1] == symmetric[3] && symmetric[0] == symmetric[2] && store.size()>0 && r == sr  && c == sc) {
//			System.out.println(sr + " " + sc + " " + Sx + " " + Ex + " " +store);
			return store.size();	
		}
		return -1;
	}
}
