package swea;
import java.util.*;
public class Problem5650 {
	static int N;
    static int[][] board;
    static int[] blockCount;
    static int maxScore;
    
    // 5가지 블록 패턴 정의 (상대 좌표)
    static int[][][] blockShapes = {
        {{0, 0}}, // 블록 1: 1x1
        {{0, 0}, {0, 1}}, // 블록 2: 1x2 가로
        {{0, 0}, {1, 0}}, // 블록 3: 2x1 세로
        {{0, 0}, {0, 1}, {1, 0}}, // 블록 4: L자 모양
        {{0, 0}} // 블록 5: 1x1
    };
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            
            // 패딩을 포함한 보드 생성 (주변을 5로 채움)
            board = new int[N + 4][N + 4];
            
            // 패딩 초기화 (5로 설정)
            for (int i = 0; i < N + 4; i++) {
                for (int j = 0; j < N + 4; j++) {
                    board[i][j] = 5;
                }
            }
            
            // 실제 게임판 입력 (2,2부터 시작)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i + 2][j + 2] = sc.nextInt();
                }
            }
            
            // 블록 개수 입력
            blockCount = new int[5];
            int totalBlocks = 0;
            for (int i = 0; i < 5; i++) {
                blockCount[i] = sc.nextInt();
                totalBlocks += blockCount[i];
            }
            
            maxScore = 0;
            boolean[][] used = new boolean[N + 4][N + 4];
            
            dfs(0, 0, used, Math.min(5, totalBlocks));
            
            System.out.println("#" + tc + " " + maxScore);
        }
        sc.close();
    }
    
    // DFS로 블록 배치 (최대 개수 제한)
    static void dfs(int placedBlocks, int currentScore, boolean[][] used, int maxBlocks) {
        maxScore = Math.max(maxScore, currentScore);
        
        // 최대 블록 수에 도달했으면 종료
        if (placedBlocks >= maxBlocks) {
            return;
        }
        
        // 조기 종료 조건: 남은 블록으로 얻을 수 있는 최대 점수가 현재 최대보다 작으면 중단
        if (currentScore + getMaxPossibleScore(placedBlocks, maxBlocks, used) <= maxScore) {
            return;
        }
        
        // 각 블록 타입 시도
        for (int blockType = 0; blockType < 5; blockType++) {
            if (blockCount[blockType] > 0) {
                // 안전한 범위에서 블록 배치 시도 (패딩 고려)
                for (int r = 0; r < N + 2; r++) {
                    for (int c = 0; c < N + 2; c++) {
                        if (canPlaceBlock(blockType, r, c, used)) {
                            int score = placeBlock(blockType, r, c, used, true);
                            blockCount[blockType]--;
                            
                            dfs(placedBlocks + 1, currentScore + score, used, maxBlocks);
                            
                            // 백트래킹
                            placeBlock(blockType, r, c, used, false);
                            blockCount[blockType]++;
                        }
                    }
                }
            }
        }
    }
    
    // 남은 블록으로 얻을 수 있는 대략적인 최대 점수 추정
    static int getMaxPossibleScore(int placedBlocks, int maxBlocks, boolean[][] used) {
        int remainingBlocks = maxBlocks - placedBlocks;
        if (remainingBlocks <= 0) return 0;
        
        // 사용 가능한 셀 중 가장 높은 값들을 찾아서 추정
        List<Integer> availableScores = new ArrayList<>();
        for (int r = 2; r < N + 2; r++) {
            for (int c = 2; c < N + 2; c++) {
                if (!used[r][c] && board[r][c] != -1) {
                    availableScores.add(Math.max(0, board[r][c]));
                }
            }
        }
        
        Collections.sort(availableScores, Collections.reverseOrder());
        
        int maxPossible = 0;
        for (int i = 0; i < Math.min(remainingBlocks * 3, availableScores.size()); i++) {
            maxPossible += availableScores.get(i);
        }
        
        return maxPossible;
    }
    
    // 특정 위치에 블록을 놓을 수 있는지 확인
    static boolean canPlaceBlock(int blockType, int startR, int startC, boolean[][] used) {
        int[][] shape = blockShapes[blockType];
        
        for (int[] cell : shape) {
            int r = startR + cell[0];
            int c = startC + cell[1];
            
            // 배열 범위 체크
            if (r < 0 || r >= N + 4 || c < 0 || c >= N + 4) {
                return false;
            }
            
            // 이미 사용된 셀이거나 블랙홀인 경우
            if (used[r][c] || board[r][c] == -1) {
                return false;
            }
        }
        return true;
    }
    
    // 블록을 놓거나 제거하고 점수 반환
    static int placeBlock(int blockType, int startR, int startC, boolean[][] used, boolean place) {
        int[][] shape = blockShapes[blockType];
        int score = 0;
        
        for (int[] cell : shape) {
            int r = startR + cell[0];
            int c = startC + cell[1];
            
            // 배열 범위 체크 (안전장치)
            if (r >= 0 && r < N + 4 && c >= 0 && c < N + 4) {
                if (place) {
                    used[r][c] = true;
                    score += board[r][c];
                } else {
                    used[r][c] = false;
                }
            }
        }
        return score;
    }
}
