package grammer;

public class Recursion {
	public static void main(String[] args) {
		int N = 5;
		rec(N);
	}	
	
	
	private static void rec(int N) {
		
		
		if (N == 0) return;
		rec(N-1);
		for (int i = 0; i<N; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
	}
	
	
	
	
//	private static void rec(int N) {
//		if (N == 0) return;
//		
//		rec(N-1);
//		for (int i = 0; i<N; i++) {
//			System.out.print("*");
//		}
//		System.out.println();
//		
//	}
}
