package backjoon;
import java.io.*;
import java.util.*;
public class Retirement {	
	static int N;
	static int[] T, P;
	static int maxProfit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int [N];
		P = new int [N];
		for (int i = 0; i<N; i++) {
			String input[] = br.readLine().split(" ");
			T[i] = Integer.parseInt(input[0]);
			P[i] = Integer.parseInt(input[1]);
		}
		
		int[] dp = new int[N+1];
        
        // 뒤에서부터 계산 (bottom-up)
        for (int i = N - 1; i >= 0; i--) {
            // i일에 상담을 하는 경우
            if (i +T[i] <= N) {
                // 상담을 하는 경우 vs 하지 않는 경우 중 최대값
                dp[i] = Math.max(P[i] + dp[i + T[i]], dp[i + 1]);
            } else {
                // 상담이 퇴사일을 넘어가는 경우, 상담을 할 수 없음
                dp[i] = dp[i + 1];
            }
        }
        
        System.out.println( dp[0]);
	}
}
//	
//	static void dfs(int day, int profit) {
//		if (day>N+1) return;
//		maxProfit = Math.max(maxProfit, profit);
//		
//		for (int i = day; i<=N; i++) {
//			if (i+T[i] <= N+1) {
//				dfs(i+T[i], profit+P[i]);
//			}
//			
//		}
//	}
//	static int N;
//    static int[] T, P;
//    static int maxProfit = 0;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//
//        T = new int[N + 1]; // Ti: 걸리는 기간
//        P = new int[N + 1]; // Pi: 이익
//
//        for (int i = 1; i <= N; i++) {
//            String[] input = br.readLine().split(" ");
//            T[i] = Integer.parseInt(input[0]);
//            P[i] = Integer.parseInt(input[1]);
//        }
//
//        dfs(1,0);
//        System.out.println(maxProfit);
//    }
//    static void dfs(int day, int profit ) {
//    	if (day>N+1) return;
//    	maxProfit = Math.max(maxProfit, profit);
//    	
//    	for (int i = day; i<=N; i++) {
//    		if (i+T[i] <= N+1) {
//    			dfs(i+T[i], profit+P[i]);
//    		}
//    	}
//    	
//    			
//    }
//    
//    static void dfs(int day, int profit) {
//        if (day > N + 1) return; // 날짜 초과 → 무효
//        maxProfit = Math.max(maxProfit, profit);
//
//        for (int i = day; i <= N; i++) {
//            if (i + T[i] <= N + 1) {
//                dfs(i + T[i], profit + P[i]);
//            }
//        }
//    }

