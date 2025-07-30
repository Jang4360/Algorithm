package grammer;

public class HanoiTower {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 3;
		hanoi(n, 'A', 'C', 'B');
	}
	
	static void hanoi(int n, char from, char to, char aux) {
		if (n == 1) {
			System.out.println("원판1을 "+from+"에서" + to+ "로 이동");
			return;
		}
		hanoi(n-1,from,aux,to);
		System.out.println("원판"+n+"을 "+from+"에서" + to + "로 이동");
		hanoi(n-1,aux,to,from);
	}
	
//	static void hanoi(int n, char from, char to, char aux) {
//		if (n)
//		
//		
//		if (n == 1) {
//			System.out.println("원판 1을 " + from +  "에서 " + to +"로 이동");
//			return;
//		}
//		
//		hanoi(n-1,from, aux, to);
//		System.out.println("원판" + n + "을 " + from +  "에서 " + to +"로 이동");
//		
//		hanoi(n-1, aux, to, from);
//		
//	}
}

