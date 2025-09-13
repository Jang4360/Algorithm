package grammer;
import java.io.*;
import java.util.*;
public class dp_02 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] cn = new int[N+1];
		cn[0] = 0;
		cn[1] = 1;
		for (int n = 2; n<=N; n++) {
			int INF = Integer.MAX_VALUE;
			int a = INF, b = INF, c = INF;
			if (n>=1) a = cn[n-1]+1;
			if (n>=4) b = cn[n-4]+1;
			if (n>=6) c = cn[n-6]+1;
			int minVal = Math.min(a, b);
			cn[n] = Math.min(minVal,c);
		}
		System.out.println(cn[8]);
	}

}
