package swea;

import java.io.*;
import java.util.*;

public class Problem6782 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 가까운 제곱근을 구하고 현재 값에서 뺀 값을 count -> 이를 반복
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			long N = Long.parseLong(br.readLine());
			if (N == 2) {
				sb.append("#").append(t).append(" ").append(0).append("\n"); 
				continue;
			}
			
			long cnt = 0L;
			
			while (N!=2L) {
				long root = (long) Math.sqrt(N);
				if (root*root == N) {
					N = root;
					cnt++;
				} else {
					long nextN = (root+1L)*(root+1L);
					cnt += nextN-N;
					N = nextN;
				}
			}
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
}
