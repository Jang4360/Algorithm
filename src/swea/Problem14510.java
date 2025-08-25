package swea;
import java.util.*;
import java.io.*;
public class Problem14510 {
	static int N, minDays;
	static int[] days;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			days = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i<N; i++) days[i] = Integer.parseInt(st.nextToken());
			minDays = Integer.MAX_VALUE;
			
			int answer = Math.min(solution(max(days)), solution(max(days)+1));
			System.out.println("#" + t + " " + answer);
			
		}
	}
	
	static int max(int[] days) {
		int val = 0;
		for (int day : days) {
			if (val<day) val = day;
		}
		return val;
	}
	
	static int solution(int target) {
		int a = 0, b = 0;
		for (int day : days) {
			int gap = target - day;
			a += gap & 1; // 홀수
			b += gap >> 1; // 짝수 
		}
		
		boolean isOdd = true;
		int res = 0;
		while (a>0 || b>0) {
			res++;
			if (isOdd) {
				if (a>0) {
					a--;
				} else if (b>0) {
					b--;
					a++;
				}
			} else {
				if (b>0) {
					b--;
				} else if (a>=2) {
					a-=2;
				} else {
					res++;
					a = 0;
					break;
				}
			}
			isOdd = !isOdd;
		}
		return res;
	}
	
}
