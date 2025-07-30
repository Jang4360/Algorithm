package grammer;
import java.util.*;
import java.util.Stack;
public class Parenthesis {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] c = sc.nextLine().toCharArray();
		Stack<Character> stack = new Stack<>();
		boolean answer = true;
		for (char ch : c) {
			if (ch == '{') {
				stack.push('}');
			} else if (ch == '[') {
				stack.push(']');
			} else if (ch == '(') {
				stack.push(')');
			} else {
				if (!stack.isEmpty() && stack.peek()==ch) {
					stack.pop();
				} else {
					answer = false;
					break;
				}
			}
		}
		if (!stack.isEmpty()) answer = false;
		
		
		
//		boolean answer = true;
//		for (char ch: c) {
//			if (ch == '{') {
//				stack.push('}');
//			} else if (ch == '[') {
//				stack.push(']');
//			} else if (ch == '(') {
//				stack.push(')');
//			} else {
//				if (stack.isEmpty() || ch != stack.peek()) {
//					answer =false; 
//					break;
//				}
//				stack.pop();
//			}
//		}
//		if (!stack.isEmpty()) {
//			answer = false;
//		}
//		
//		System.out.println(answer ? "올바릅니다":"잘못됬습니다");
	}
}
