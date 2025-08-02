package backjoon;
import java.io.*;
import java.util.*;
public class Problem22866 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        int[] heights = new int[n + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] count = new int[n + 1];  // 각 건물에서 볼 수 있는 건물 개수
        int[] nearest = new int[n + 1];  // 가장 가까운 건물 번호
        
        // 왼쪽에서 볼 수 있는 건물들 찾기
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            // 현재 건물보다 낮거나 같은 건물들은 스택에서 제거
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            
            // 스택에 남은 건물들은 현재 건물에서 볼 수 있는 건물들
            count[i] += stack.size();
            
            // 가장 가까운 건물 (스택의 맨 위)
            if (!stack.isEmpty()) {
                nearest[i] = stack.peek();
            }
            
            stack.push(i);
        }
        
        // 오른쪽에서 볼 수 있는 건물들 찾기
        stack.clear();
        for (int i = n; i >= 1; i--) {
            // 현재 건물보다 낮거나 같은 건물들은 스택에서 제거
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            
            // 스택에 남은 건물들은 현재 건물에서 볼 수 있는 건물들
            count[i] += stack.size();
            
            // 가장 가까운 건물 업데이트
            if (!stack.isEmpty()) {
                int rightNearest = stack.peek();
                if (nearest[i] == 0 || Math.abs(i - rightNearest) < Math.abs(i - nearest[i]) ||
                    (Math.abs(i - rightNearest) == Math.abs(i - nearest[i]) && rightNearest < nearest[i])) {
                    nearest[i] = rightNearest;
                }
            }
            
            stack.push(i);
        }
        
        // 결과 출력
        for (int i = 1; i <= n; i++) {
            if (count[i] == 0) {
                bw.write("0\n");
            } else {
                bw.write(count[i] + " " + nearest[i] + "\n");
            }
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
}
