package grammer;
import java.util.*;
import java.util.Queue;
public class PracticeQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(1);
		q.offer(4);
		q.addFirst(0);
		q.addLast(5);
		while (!q.isEmpty()) {
			int n = q.poll();
			System.out.println(n);
		}
	}

}
