package backjoon;
import java.io.*;
import java.util.*;
public class Problem14888 {
	static int[] arr;
	static int N;
	static int MaxVal = Integer.MIN_VALUE;
	static int MinVal = Integer.MAX_VALUE;
	static String[] operators;
	static boolean[] visited;
	static String[] path;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] operator = new int[4];
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i<4; i++) {
			operator[i] = Integer.parseInt(st2.nextToken());
		}
		
		List<String> list = new ArrayList<>();

        // 1. 연산자 배열 생성
        for (int i = 0; i < operator[0]; i++) list.add("+");
        for (int i = 0; i < operator[1]; i++) list.add("-");
        for (int i = 0; i < operator[2]; i++) list.add("*");
        for (int i = 0; i < operator[3]; i++) list.add("/");

        operators = list.toArray(new String[0]);
        visited = new boolean[operators.length];
        path = new String[operators.length];

        // 2. 순열 생성
        backtrack(0);
        System.out.println(MaxVal);
        System.out.println(MinVal);
	}
	
	public static void backtrack(int depth) {
        if (depth == operators.length) {
            calculate(path.clone());
            return;
        }

        Set<String> used = new HashSet<>(); // 같은 depth에서 중복 방지

        for (int i = 0; i < operators.length; i++) {
            if (visited[i] || used.contains(operators[i])) continue;

            visited[i] = true;
            path[depth] = operators[i];
            used.add(operators[i]);

            backtrack(depth + 1);
            visited[i] = false;
        }
    }
	
	public static void calculate(String[] path) {
		Stack<Integer> numStack = new Stack<>();
		for (int i = N-1; i>=0; i--) {
			numStack.push(arr[i]);
		}
		
		Stack<String> opStack = new Stack<>();
		for (int i = 0; i<path.length; i++) {
			opStack.push(path[i]);
		}
		
		while (!opStack.isEmpty()) {
			String op = opStack.pop();
			int curA = numStack.pop();
			int curB = numStack.pop();
			int result = 0;
			if (op == "+") {result = curA+curB;}
			else if (op == "-") {result = curA-curB;}
			else if (op == "*") {result = curA*curB;}
			else {
				if (curA<0) {
					curA *= -1;
					result = -1*(curA / curB);
				} else {
					result = curA / curB;
				}
				
			}
			numStack.push(result);
		}
		int answer = numStack.peek();
		MaxVal = Math.max(MaxVal, answer);
		MinVal = Math.min(MinVal, answer);
	}
}
