package backjoon;
import java.io.*;
import java.util.*;
public class Problem20361 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int X = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		
		
		for (int i = 0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == X) X = b;
			else if (b == X) X = a;
		}
		
		System.out.println(X);
	}
}
