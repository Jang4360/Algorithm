package swea;
import java.io.*;
import java.util.*;

public class Problem2382 {
	 static int N,M,K,remainCnt;
	   static int[] dirx = {0,-1,1,0,0};
	   static int[] diry = {0,0,0,-1,1};
	   static List<cell> search;
	   static cell[][] board;
	   static class cell{
		   int r, c, cnt, d, total;
		   boolean isDead;
		   public cell(int r, int c, int cnt, int d) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = this.total = cnt;
			this.d = d;
		   }
		   
	   }
	   public static void main(String[] args) throws NumberFormatException, IOException {
		   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		   int T = Integer.parseInt(br.readLine());
		   for (int t = 1; t<=T; t++) {
			   String[] input = br.readLine().split(" ");
			   N = Integer.parseInt(input[0]);
			   M = Integer.parseInt(input[1]);
			   K = Integer.parseInt(input[2]);
			   board = new cell[N][N];
			   remainCnt = 0;
			   search = new ArrayList<>();
			   
			   for (int i = 0; i<K; i++) {
				   StringTokenizer st = new StringTokenizer(br.readLine());
				   int r = Integer.parseInt(st.nextToken());
				   int c = Integer.parseInt(st.nextToken());
				   int cnt = Integer.parseInt(st.nextToken());
				   int d = Integer.parseInt(st.nextToken());
				   cell one = new cell(r,c,cnt,d);
				   search.add(one);
			   }
			   
			   System.out.println(solution());
		   }
	   }
	   
	   static int solution() {
		   for (int m = 0; m<M; m++) {
			   List<int[]> store = new ArrayList<>();
			   for (cell one : search) {
				   if (one.isDead) continue;
				   int nr = one.r + dirx[one.d], nc = one.c + diry[one.d];
				   one.r = nr; one.c = nc;
				   if (nr == 0 || nr == N-1 || nc == 0 || nc == N-1) {
					   one.cnt = one.total = one.cnt/2;
					   if (one.cnt == 0) {
						   one.isDead = true;
						   continue;
					   }
					   one.d = one.d%2==0 ? one.d-1 : one.d+1;
				   }
				   
				   if (board[nr][nc] == null) {
					   board[nr][nc] = one;
				   } else {
					   if (board[nr][nc].cnt <= one.cnt) {
						   one.total += board[nr][nc].total;
						   board[nr][nc].isDead = true;
						   board[nr][nc] = one;
					   } else {
						   board[nr][nc].total += one.total;
						   one.isDead = true;
					   }
				   }
				  
			   }
			   remainCnt = reset();
		   }
		   return remainCnt;
	   }
	   
	   static int reset() {
		   int total = 0;
		   for (int i = 0; i<N; i++) {
			   for (int j = 0; j<N; j++) {
				   if (board[i][j] == null) continue;
				   board[i][j].cnt = board[i][j].total;
				   total += board[i][j].cnt;
				   board[i][j] = null;
			   }
		   }
		   return total;
	   }
}
