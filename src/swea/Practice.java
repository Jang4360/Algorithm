package swea;
import java.io.*;
import java.util.*;

public class Practice {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] heights = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }
            
            int result = solve(heights);
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
    
    static int solve(int[] heights) {
        int oneDay = 0;    // 1만큼 자라야 하는 총 횟수
        int twoDay = 0;    // 2만큼 자라야 하는 총 횟수
        
        // 각 나무별로 필요한 성장량 계산
        for (int height : heights) {
            twoDay += height / 2;      // 2씩 자라는 날 (몫)
            oneDay += height % 2;      // 1씩 자라는 날 (나머지)
        }
        
        // 2일 성장을 1일 성장으로 변환하여 균형 맞추기
        // 2일 성장 1회 = 1일 성장 2회로 변환 가능 (2 -> 1+1)
        while (twoDay > oneDay) {
            twoDay--;
            oneDay += 2;
        }
        
        // 최종 답 계산
        if (oneDay == twoDay) {
            return oneDay + twoDay;  // 완벽한 균형: 모든 날을 번갈아 사용
        } else {
            // oneDay > twoDay인 경우
            int extra = oneDay - twoDay;
            return twoDay * 2 + extra;  // 짝수 날은 교대로, 나머지는 연속으로
        }
    }
}