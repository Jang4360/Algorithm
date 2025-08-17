package grammer;
import java.io.*;
import java.util.*;
// N개의 수를 N 번 나열하는 경우
public class NextPermutation {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] numbers = new int[N];
		for (int i = 0; i<N; i++) numbers[i] = sc.nextInt();
		
		// 1. 전처리 - 가장 작은 순열 생성
		Arrays.sort(numbers);
		do {
			System.out.println(Arrays.toString(numbers));
		} while (np(numbers));		
	}
	
	static boolean np(int[] numbers) { // 현재 배열의 순열 상태에서 사전순 다음 순열 생성후 다음 순열이 존재하면 true, 없으면 false 
		int N = numbers.length;
		// 1. 꼭대기 찾아 바로 앞 교환 위치 찾기
		int i = N-1;
		while (i>0 && numbers[i-1]>=numbers[i]) --i;
		if (i == 0) return false; // 다음 순열이 없음
		
		// 2. 교환 위치에 값을 크게 만들 뒤쪽에서 자신보다 큰 값중 가장 작은 값 찾기
		int j = N-1;
		while (numbers[i-1] >= numbers[j]) --j;
		
		// 3. i-1위치 값과 j 위치 값 교환
		swap(numbers, i-1, j);
		
		// 4. 꼭대기 부터 뒤의 모든 순열을 오름차순으로 변경
		int k = N-1; 
		while (i<k) swap(numbers, i++, k--);
		
		return true;
		
	} 
	
	static void swap(int[] numbers, int a, int b) {
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}
}
