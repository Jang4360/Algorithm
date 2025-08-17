package grammer;
import java.util.*;

public class MakeSpaceTest {
	static int[][] spaces;
	static int green, white;
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int N = sc.nextInt();
		spaces = new int[N][N];
		
		for (int i = 0; i<N; i++) {
			for (int j = 0; j<N; j++) {
				spaces[i][j] = sc.nextInt();
			}
		}
		green = white = 0;
		makeSpace(0,0,N);
		System.out.println(white);
		System.out.println(green);
	}
	
	static void makeSpace(int r, int c, int size) {
		// 1. 주어진 영역이 같은 색으로 이루어져 있는지 체크
		int sum = 0;
		for (int i = r, rEnd = r+size; i<rEnd; i++) {
			for (int j = c, cEnd = c+size; j<cEnd; j++) {
				sum += spaces[i][j];
			}
		}
		
		if (sum == size*size) { // 모두 초록색 
			++green;
		} else if (sum == 0) { // 모두 하얀색 
			++white;
		} else { // 색이 섞여있으면 
			int newSize = size/2;
			makeSpace(r,c,newSize);
			makeSpace(r,c+newSize,newSize);
			makeSpace(r+newSize,c,newSize);
			makeSpace(r+newSize,c+newSize,newSize);
		}
		// 2. 같은 색으로 이루어져 있다면 해당 색의 카운트 증가후 리턴
		
	}
	
}
