package swea;
import java.io.*;
import java.util.*;

public class Practice {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] tree = new int[N];
			for (int i = 0; i<N; i++) tree[i] = Integer.parseInt(st.nextToken());
			
			int maxTree = 0;
			for (int i = 0; i<N; i++) {
				if (maxTree<tree[i]) maxTree = tree[i];
			}
			int odd = 0;
			int even = 0;
			for (int i = 0; i<N; i++) {
				int gap = maxTree-tree[i];
				even += gap/2;
				odd += gap%2;
			}
			
			if (even>odd) {
				while (Math.abs(even-odd)>1) {
					even--;
					odd+=2;
				}
			}
			
			int answer = 0;
			if (odd>even) {
				answer += 2*odd-1;
			} else if (odd<even) {
				answer += 2*even;
			} else {
				answer += even+odd;
			}
			
			System.out.println(answer);
		}
	}
}
