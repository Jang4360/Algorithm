package backjoon;
import java.util.*;
import java.io.*;
public class Problem13458 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] aArr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i<N; i++) {
			aArr[i] = Integer.parseInt(st.nextToken());
		}
		
		String[] directors = br.readLine().split(" ");
		int B = Integer.parseInt(directors[0]);
		int C = Integer.parseInt(directors[1]);
		
		int totalCnt = N;
		
		for (int i = 0; i<N; i++) {
			aArr[i] = aArr[i]>B ? aArr[i]-B : 0;
			
			if (aArr[i] == 0) continue;
			int cnt = aArr[i] / C;
			totalCnt += cnt;
			if (aArr[i]%C != 0) totalCnt++;
		}
		
		System.out.println(totalCnt);
	}
}
