package grammer;
import java.util.*;
public class MyChewTest {

	public static void main(String[] args) {
		int N = 20;
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int person = 1;
		q.offer(new int[] {person,1});
		while (N>0) {
			int[] cur = q.poll();
			int left = (N>cur[1] ? cur[1] : N);
			N -= left;
			
			if (N>0) {
				cur[1]++;
				q.offer(cur);
				q.offer(new int[] {++person,1});
			}
		}
		
//		ArrayDeque<int[]> q = new ArrayDeque<>();
//		int person = 1;
//		q.offer(new int[]{person,1});
//		
//		while (N>0) {
//			int[] cur = q.poll();
//			int leftMyChew = (N>cur[1]) ? cur[1] : N;
//			N -= leftMyChew;
//			
//			System.out.println(cur[0]+"번 사람이 마이쭈를" + leftMyChew+ "개 먹었습니다.");
//			if (N>0) {
//				cur[1]++;
//				q.offer(cur);
//				q.offer(new int[] {++person,1});
//			}
//		}

	}

}
