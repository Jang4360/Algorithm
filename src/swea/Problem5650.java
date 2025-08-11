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
	    	System.out.println(maxScore);
	    }
	}
	static void playGame(int sx, int sy) {
	    for (int startDir = 0; startDir < 4; startDir++) {
	        int score = 0;
	        int cx = sx, cy = sy;
	        int dir = startDir;

	        while (true) {
	            // 한 칸 전진
	            int nx = cx + dirx[dir];
	            int ny = cy + diry[dir];

	            // 벽이면 반사 + 점수
	            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
	                dir = (dir + 2) % 4; // 벽 == block 5
	                score++;
	                cx += dirx[dir];
	                cy += diry[dir];
	                if (cx == sx && cy == sy) break;
	                continue;
	            }

	            int cell = board[nx][ny];

	            // 블랙홀
	            if (cell == -1) break;

	            // 시작점 복귀
	            if (nx == sx && ny == sy) break;

	            // 빈칸
	            if (cell == 0) {
	                cx = nx; cy = ny;
	                continue;
	            }

	            // 블록
	            if (1 <= cell && cell <= 5) {
	                dir = blockDir[cell][dir]; // 먼저 dir 인덱스 갱신
	                score++;
	                cx = nx; cy = ny;          // 블록 칸에 도달한 상태로 유지
	                continue;
	            }

	            // 웜홀(6~10)
	            if (cell >= 6) {
	                List<int[]> list = womHole.get(cell);
	                int[] a = list.get(0), b = list.get(1);
	                if (nx == a[0] && ny == a[1]) { cx = b[0]; cy = b[1]; }
	                else                           { cx = a[0]; cy = a[1]; }
	                // 방향은 유지(dir 그대로)
	                continue;
	            }
	        }
	        maxScore = Math.max(maxScore, score);
	    }
	}

	
//	static void playGame(int sx, int sy) {
//		
//		for (int d = 0; d<4; d++) {
//			int score = 0;
//			int cx = sx, cy = sy;
//			int dir = d;
//		
//			while (true) {
//				int nx = cx+dirx[dir];
//				int ny = cy+diry[dir];
//
//	    		// 벽일 경우 
//	    		if (!(0<=nx && nx<N && 0<=ny && ny<N)) {
//	    			dir = blockDir[5][dir];
//	    			score++;
//	    			cx += dirx[dir];
//	    			cy += diry[dir];
//	    			if (cx == sx && cy == sy) break;
//	    			continue;
//	    		}
//	    		
//	    		int num = board[nx][ny];
//	    		
//	    		// 블랙홀일 경우 
//	    		if (num == -1) break;
//	    		
//	    		if (nx == sx && ny == sy) break;
//	    		
//	    		// 빈칸 
//	    		if (num == 0) {
//	    			cx = nx;
//	    			cy = ny;
//	    			continue;
//	    		}
//	    		
//	    		// 블록
//	    		if (1<=num && num<=5) {
//	    			dir = blockDir[num][dir];
//	    			score++;
//	    			cx = nx; cy = ny;
//	    			continue;
//	    		}
//	    		
//	    		// 웜홀
//	    		if (num>=6) {
//	    			List<int[]> list = womHole.get(num);
//	    			int[] a = list.get(0), b = list.get(1);
//	                if (nx == a[0] && ny == a[1]) { cx = b[0]; cy = b[1]; }
//	                else { cx = a[0]; cy = a[1]; }
//	                continue;
//	    		}
//	    		
//
//	    	}
//			maxScore = Math.max(score, maxScore);
//		}
//	}
}
