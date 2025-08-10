package grammer;
import java.util.*;
public class CompleteBinaryTree<T> {
	private Object[] nodes;
	private int lastIndex;
	private final int SIZE;
	
	public CompleteBinaryTree(int size) {
		super();
		SIZE = size;
		nodes = new Object[size+1];
	}
	
	public boolean isEmpty() {
		return lastIndex == 0;
	}
	
	public boolean isFull() {
		return lastIndex == SIZE;
	}
	
	public void add(T e) {
		if (isFull()) throw new RuntimeException("완전 이진트리가 풀입니다.");
		nodes[++lastIndex] = e; 
	}
	
	public void bfs() {
		Queue<Integer> stack = new ArrayDeque<>();
		stack.offer(1);
		while (!stack.isEmpty()) {
			
			int cur = stack.poll();
			System.out.println(nodes[cur]);
			if (2*cur<=lastIndex) stack.add(2*cur);
			if (2*cur+1<=lastIndex)stack.add(2*cur+1);
		}
	}
	
	public void dfs() {
		Stack<Integer> q = new Stack<>();
		q.push(1);
		while (!q.isEmpty()) {
			
			int cur = q.pop();
			System.out.print(nodes[cur] + " ");
			if (2*cur<=lastIndex) q.add(2*cur);
			if (2*cur+1<=lastIndex)q.add(2*cur+1);
		}
	}
	
	public void dfsByPreOrder(int cur) {
		System.out.print(nodes[cur]+" ");
		if (cur*2<=lastIndex) dfsByPreOrder(2*cur);
		if (cur*2+1<=lastIndex) dfsByPreOrder(2*cur+1);
	}
}
