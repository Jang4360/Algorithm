package grammer;

public class StackLinkedList {
	static class Node {
		Object data;
		Node link;
		public Node(Object data, Node link) {
			super();
			this.data = data;
			this.link = link;
		}
		
		public Node(Object data) {
			this(data,null);
		}
		
	}
	
	private Node top;
	public void push(Object data) {
		top = new Node(data, top);
		
	}
	
	public Object pop() {
		if (isEmpty()) return null;
		Node popNode = top;
		top = popNode.link;
		popNode.link = null;
		return popNode.data;
	}
	
	public Object peek() {
		if (isEmpty()) return null;
		return top.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public int size() {
		int cnt = 0;
		for (Node temp = top; temp!=null; temp = temp.link) {
			cnt += 1;
		}
		return cnt;
	}
}
