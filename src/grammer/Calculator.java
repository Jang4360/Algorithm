package grammer;

import java.util.*;
import java.io.*;
public class Calculator {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Double> stack = new Stack<>();
		String[] strArr = br.readLine().split(" ");
		
		for (int i = 0; i<strArr.length; i++) {
			char c = strArr[i].charAt(0);
			if (Character.isDigit(c) || strArr[i].length()>1) {
				stack.push(Double.parseDouble(strArr[i]));
			} else {
				if (stack.size()<2) {
					System.out.println("잘못된 수식입니다.");
					return;
				}
				double b = stack.pop();
				double a = stack.pop();
				double result = 0;
				switch(c) {
					case '+': result = a+b; break;
					case '-': result = a-b; break;
					case '*': result = a*b; break;
					case '/': result = a/b; break;
				}
				stack.push(result);
			}
		}
		if (stack.size()==1) {
			System.out.println(stack.peek());
		} else {
			System.out.println("잘못된 수식입니다.");
		}
		
//		Scanner sc = new Scanner(System.in);
//		Stack<Double> stack = new Stack<>();
//		char[] chars = sc.nextLine().toCharArray();
//				
//		for (char ch : chars) {
//			if (Character.isDigit(ch)) {
//				stack.push((double)(ch-'0'));
//			} else {
//				if (stack.size()<2) {
//					System.out.println("잘못된 수식입니다");
//					return;
//				}
//				double b = stack.pop();
//				double a = stack.pop();
//				double result = 0;
//
//				switch(ch) {
//					case '+': result = a+b; break;
//					case '-': result = a-b; break;
//					case '*': result = a+b; break;
//					case '/': 
//						if (b==0) {
//							System.out.println("0으로 나눌 수 없습니다");
//							return;
//						}
//						result = a/b; break;
//					default:
//						System.out.println("잘못된 수식입니다:" + ch);
//						return;
//				}
//				stack.push(result);
//			} 
//		}
//		if (stack.size() == 1) {
//			System.out.println(stack.peek());
//		} else {
//			System.out.println("잘못된 수식입니다");
//		}
//		
		
	}
}
