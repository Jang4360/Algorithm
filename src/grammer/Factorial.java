package grammer;
import java.util.*;
import java.io.*;
public class Factorial {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(fact(n));
	}
	
	private static int fact(int n) {
		if (n ==1) {
			return 1;
		}
		return n*fact(n-1);
	}
	
}
