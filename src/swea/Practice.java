package swea;
import java.io.*;
import java.util.*;

public class Practice {
    static int N, M;
    static int[][] lab;
    static List<int[]> viruses = new ArrayList<>();
    static int emptyCnt;             
    static int best = Integer.MAX_VALUE;

    static final int[] dx = {1,0,0,-1};
    static final int[] dy = {0,1,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][N];
        emptyCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2) viruses.add(new int[]{i, j});
                else if (lab[i][j] == 0) emptyCnt++;
            }
        }

        if (emptyCnt == 0) {           // 빈 칸이 없으면 0
            System.out.println(0);
            return;
        }

        int K = viruses.size();
        int[] pick = new int[M];
        comb(0, 0, K, pick);

        System.out.println(best == Integer.MAX_VALUE ? -1 : best);
    }

    // 조합: idx부터, pcnt개 뽑기
    static void comb(int idx, int pcnt, int K, int[] pick) {
        // 가지치기: 남은 후보 수로는 M 못 채우면 컷
        if (pcnt + (K - idx) < M) return;

        if (pcnt == M) {
            bfs(pick);
            return;
        }
        if (idx == K) return;

        // 뽑는 경우
        pick[pcnt] = idx;
        comb(idx + 1, pcnt + 1, K, pick);

        // 안 뽑는 경우
        comb(idx + 1, pcnt, K, pick);
    }

    // 다중 시작점 BFS: 빈 칸이 모두 감염될 때까지의 최소 시간 반환(갱신 only)
    static void bfs(int[] pick) {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);

        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int id : pick) {
            int[] v = viruses.get(id);
            q.offer(new int[]{v[0], v[1]});
            dist[v[0]][v[1]] = 0;
        }

        int infected = 0;
        int maxTime = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            int t = dist[x][y];

            // 현재까지의 시간으로도 이미 최적해를 넘으면 더 볼 필요 없음
            if (maxTime >= best) return;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (lab[nx][ny] == 1) continue;               // 벽
                if (dist[nx][ny] != -1) continue;             // 이미 방문

                dist[nx][ny] = t + 1;
                q.offer(new int[]{nx, ny});

                if (lab[nx][ny] == 0) {                       // 빈 칸 감염
                    infected++;
                    maxTime = Math.max(maxTime, t + 1);
                    if (infected == emptyCnt) {               // 전부 감염 완료 → 조기 종료
                        best = Math.min(best, maxTime);
                        return;
                    }
                }
                // lab[nx][ny] == 2(비활성 바이러스)면 시간은 흐르지만 ‘감염 카운트’는 증가하지 않음
            }
        }
        // 여기까지 왔으면 모든 빈 칸 감염 실패 → 갱신 없음
    }
}
