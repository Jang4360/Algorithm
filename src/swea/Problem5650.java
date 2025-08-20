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
			{2, 3, 1, 0},   // 1번 블록
			{1, 3, 0, 2},   // 2번 블록
			{3, 2, 0, 1},   // 3번 블록
			{2, 0, 3, 1},   // 4번 블록
			{2, 3, 0, 1}    // 5번 블록
	};
	static Map<Integer, List<int[]>> womHole;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine().trim());
	    
	    for (int t = 1; t <= T; t++) {
	    	maxScore = 0;
	    	N = Integer.parseInt(br.readLine().trim());
	    	board = new int[N][N];
	    	for (int i = 0; i<N; i++) {
	    		StringTokenizer st = new StringTokenizer(br.readLine().trim());
	    		for (int j = 0; j<N; j++) {
	    			board[i][j] = Integer.parseInt(st.nextToken());
	    		}
	    	}
	    	
	    	// 웜홀 처리 
	    	womHole = new HashMap<>();
	    	for (int w = 6; w<=10; w++) womHole.put(w, new ArrayList<>());
	    	
	    	for (int i = 0; i<N; i++) {
	    		for (int j = 0; j<N; j++) {
	    			if (6<=board[i][j] && board[i][j]<=10) {
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
	static void playGame(int startx, int starty) {
	    for (int startDir = 0; startDir < 4; startDir++) {
	        int score = 0;
	        int row = startx, col = starty;
	        int dir = startDir;

	        while (true) {
	            // 1) 한 칸 이동
	            row += dirx[dir];
	            col += diry[dir];

	            // 2) 벽에 부딪혔을 경우: 방향만 반사, 점수 +1, 그 턴 종료
	            if (row < 0 || row >= N || col < 0 || col >= N) {
	                dir = (dir + 2) % 4; // 반사
	                score++;
	                continue;         
	            }
	            
	            // 현재 위치의 값
	            int current = board[row][col];

	            // 3) 종료 조건: 블랙홀 / 시작점 복귀 즉시 종료
	            if (current == -1 || row == startx && col == starty) break;
	   
	            // 4) 현재 위치 처리
	            // 블록에 부딪혔을 경우 : 방향 반사, 점수 +1, 현재 위치 유지  
	            if (1 <= current && current <= 5) {
	                dir = blockDir[current][dir];
	                score++;
	            } 
	            // 웜홀일 경우: 위치만 이동, 방향 유지
	            else if (6 <= current && current <= 10){ 
	                List<int[]> holeList = womHole.get(current);
	                int[] a = holeList.get(0), b = holeList.get(1);
	                
	                if (row == a[0] && col == a[1]) { row = b[0]; col = b[1]; }
	                else { row = a[0]; col = a[1]; }
	            }
	        }
	        // 최대 점수 갱신
	        maxScore = Math.max(maxScore, score);
	    }
	}
}

