package swea;

import java.io.*;
import java.util.*;

public class Practice {
    static int N;
    static int[] w;              // 추 무게
    static boolean[] used;       // 사용 여부
    static long answer;
    static long[] fact;          // 팩토리얼
    static long[] pow2;          // 2의 거듭제곱
    static int totalSum;         // 전체 무게 합

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        // 팩토리얼, 2^k 미리
        fact = new long[11];
        pow2 = new long[11];
        fact[0] = 1; pow2[0] = 1;
        for (int i = 1; i <= 10; i++) {
            fact[i] = fact[i - 1] * i;
            pow2[i] = pow2[i - 1] << 1;
        }

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            w = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            totalSum = 0;
            for (int i = 0; i < N; i++) {
                w[i] = Integer.parseInt(st.nextToken());
                totalSum += w[i];
            }

            // 무거운 것부터 시도하면 컷이 더 잘 난다
            Arrays.sort(w);
            for (int i = 0; i < N / 2; i++) {
                int tmp = w[i]; w[i] = w[N - 1 - i]; w[N - 1 - i] = tmp;
            }

            used = new boolean[N];
            answer = 0;
            dfs(0, 0, 0, 0); // idx, left, right, usedSum

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }

    // idx: 놓은 추 개수, left/right: 현재까지 양쪽 합, usedSum: 사용한 추 무게 합
    static void dfs(int idx, int left, int right, int usedSum) {
        // 규칙 위반 즉시 컷
        if (right > left) return;

        if (idx == N) { // 전부 놓음
            answer++;
            return;
        }

        // 남은 무게 합
        int remain = totalSum - usedSum;

        // 남은 모든 추를 전부 오른쪽에 얹어도 left >= right 유지되면
        // 이후는 어떤 순서/배치도 전부 유효 → 일괄 더하기
        if (left >= right + (remain - 0)) {
            int rest = N - idx;
            answer += fact[rest] * pow2[rest];
            return;
        }

        // 아직 남은 추들 중 하나를 선택(순열)
        for (int i = 0; i < N; i++) if (!used[i]) {
            used[i] = true;

            // 1) 왼쪽에 놓기 (항상 가능)
            dfs(idx + 1, left + w[i], right, usedSum + w[i]);

            // 2) 오른쪽에 놓기 (조건: 놓은 직후에도 right <= left)
            if (right + w[i] <= left) {
                dfs(idx + 1, left, right + w[i], usedSum + w[i]);
            }

            used[i] = false;
        }
    }
}
