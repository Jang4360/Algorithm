package swea;
import java.io.*;
import java.util.*;
public class Problem5650 {
	static int N;
	static int[][] board;
	static int maxScore;
	
	// 상 우 하 좌
	static int[] dirx = {-1, 0, 1, 0}; 
	static int[] diry = {0, 1, 0, -1};
	static int[][] blockDir = {
			{},
			{2, 3, 1, 0},
			{1, 3, 0, 2},
			{3, 2, 0, 1},
			{2, 0, 3, 1},
			{2, 3, 0, 1}
	};
	static Map<Integer, List<int[]>> womHole;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine());
	    
	    for (int t = 1; t <= T; t++) {
	    	maxScore = 0;
	    	N = Integer.parseInt(br.readLine());
	    	board = new int[N][N];
	    	for (int i = 0; i<N; i++) {
	    		StringTokenizer st = new StringTokenizer(br.readLine());
	    		for (int j = 0; j<N; j++) {
	    			board[i][j] = Integer.parseInt(st.nextToken());
	    		}
	    	}
	    	
	    	// 웜홀 처리 
	    	womHole = new HashMap<>();
	    	for (int w = 6; w<=10; w++) womHole.put(w, new ArrayList<>());
	    	
	    	for (int i = 0; i<N; i++) {
	    		for (int j = 0; j<N; j++) {
	    			if (6<=board[i][j]) {
	    				womHole.get(board[i][j]).add(new int[] {i,j});
	    			}
	    		}
	    	}
	    	
	    	for (int i = 0; i<N; i++) {
	    		for (int j = 0; j<N; j++) {
	    			if (board[i][j] == 0) {
	    				playGame(i,j);
	    			}
	    		}
	    	}
	    	System.out.println("#" + t + " " +maxScore);
	    }
	}
	static void playGame(int sx, int sy) {
	    for (int startDir = 0; startDir < 4; startDir++) {
	        int score = 0;
	        int r = sx, c = sy;
	        int dir = startDir;

	        while (true) {
	            // 1) 한 칸 이동
	            r += dirx[dir];
	            c += diry[dir];

	            // 2) 벽(out of bounds): 방향만 반사 + 점수, 그 턴 종료
	            if (r < 0 || r >= N || c < 0 || c >= N) {
	                dir = (dir + 2) % 4; // 정반사
	                score++;
	                continue;            // 좌표 r,c는 '바깥' 그대로, 다음 턴에 다시 안으로 들어옴
	            }

	            int cell = board[r][c];

	            // 3) 블랙홀 / 시작점 복귀 즉시 종료
	            if (cell == -1) break;
	            if (r == sx && c == sy) break;

	            // 4) 셀 타입 처리
	            if (cell == 0) {
	                // 빈칸: 아무것도 안 함(이미 r,c가 그 칸임)
	            } else if (1 <= cell && cell <= 5) {
	                // 블록: 반사 + 점수 (좌표는 현재 칸 유지)
	                dir = blockDir[cell][dir];
	                score++;
	            } else { // 6~10 웜홀
	                // 위치만 텔레포트(방향 유지)
	                List<int[]> list = womHole.get(cell);
	                int[] a = list.get(0), b = list.get(1);
	                if (r == a[0] && c == a[1]) { r = b[0]; c = b[1]; }
	                else                        { r = a[0]; c = a[1]; }
	            }
	        }
	        maxScore = Math.max(maxScore, score);
	    }
	}
}

