package swea;
import java.io.*;
import java.util.*;

public class Practice {
    static int D, W, K, minVal;
    static int[][] layers;
    static List<Integer> defaultList;
    static boolean found;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
        	String[] input = br.readLine().split(" ");
        	D = Integer.parseInt(input[0]);
        	W = Integer.parseInt(input[1]);
        	K = Integer.parseInt(input[2]);
        	
        	layers = new int[D][W];
        	for (int i = 0; i<D; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		for (int j = 0; j<W; j++) {
        			layers[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	if (K <= 1 || checkAllCol(layers)) {
        		sb.append("#").append(t).append(" ").append(0).append("\n");		
        		continue;
        	}
        	
        	defaultList = new ArrayList<>();
        	for (int i = 0; i<D; i++) {
        		defaultList.add(i+1);
        		defaultList.add(-(i+1));
        	}
        	
        	minVal = Integer.MAX_VALUE;
        	found = false; 
        	for (int idx = 1; idx<=D && !found; idx ++) {
        		comb(idx, 0, new ArrayList<>(), new boolean[D]);
        	}
        	sb.append("#").append(t).append(" ").append(minVal).append("\n");		
        }   
        System.out.println(sb.toString());
    }
    
    static void comb(int len, int start, List<Integer> idxList, boolean[] usedRow) {
    	if (found) return;
    	if (idxList.size() == len) {
    		if (len >= minVal) return; 
    		if (passPossible(idxList)) {
    			minVal = len;
    			found = true;
    		}
    		return;
    	}
    	if (len >= minVal) return;
    	
    	for (int i = start; i<defaultList.size() && !found; i++) {
    		int code = defaultList.get(i);
    		int row = Math.abs(code)-1;
    		if (usedRow[row]) continue;
    		
    		usedRow[row] = true;
    		idxList.add(code);
    		comb(len, i+1, idxList, usedRow);
    		idxList.remove(idxList.size()-1);
    		usedRow[row] = false;
    	}
    	
    }
    
    static boolean checkCol(int col, int[][] arr) {
    	int cnt = 1;
		for (int i = 1; i<D; i++) {
			if (arr[i][col] == arr[i-1][col]) {
				cnt++;
				if (cnt >= K) return true;
			} else {
				cnt = 1;
			}
		}
		return false;
    }
    
    // 전체 col 확인
    static boolean checkAllCol(int[][] arr) {
    	for (int j = 0; j<W; j++) {
    		if(!checkCol(j,arr)) return false;
    	} 
    	return true;
    }
    
    static boolean passPossible(List<Integer> list) {
    	int[][] newLayers = new int[D][];
    	for (int i = 0; i<D; i++) {
    		newLayers[i] = Arrays.copyOf(layers[i], W);
    	}
    	
    	for (int code : list) {
    		int row = Math.abs(code) -1;
    		int fill = (code>0) ? 1 : 0;
    		Arrays.fill(newLayers[row], fill);
    		
    	}
    	return checkAllCol(newLayers);
    }
    
}
