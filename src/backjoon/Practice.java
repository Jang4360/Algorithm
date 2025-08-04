package backjoon;
import java.io.*;
import java.util.*;

public class Practice {
    static int N;
    static int[][] S;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        visited = new boolean[N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0, 0);
        System.out.println(min);
    }
    
    static void dfs(int idx, int count) {
        // N/2명을 선택했으면 능력치 차이 계산
        if (count == N / 2) {
            diff();
            return;
        }
        
        for (int i = idx; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }
    
    static void diff() {
        int team_start = 0;
        int team_link = 0;
        
        // 스타트 팀 능력치 계산
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // 둘 다 스타트 팀
                if (visited[i] && visited[j]) {
                    team_start += S[i][j];
                    team_start += S[j][i];
                }
                // 둘 다 링크 팀
                else if (!visited[i] && !visited[j]) {
                    team_link += S[i][j];
                    team_link += S[j][i];
                }
            }
        }
        
        int val = Math.abs(team_start - team_link);
        min = Math.min(val, min);
    }
}