package swea;
import java.io.*;
import java.util.*;
public class Problem2112 {
	static int D, W, K, minVal;
	static int[][] layer;
	static List<Integer> store;
	static List<Integer> defaultList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			D = Integer.parseInt(input[0]);
			W = Integer.parseInt(input[1]);
			K = Integer.parseInt(input[2]);
			

			layer = new int[D][W];
			for (int i = 0; i<D; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j<W; j++) {
					layer[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			store = new ArrayList<>();
			minVal = Integer.MAX_VALUE;
			
			// 성능 테스트 확인 
			for (int j = 0; j<W; j++) {
				if (!checkTest(j, layer)) store.add(j);
			}
			
			if (store.size() == 0) minVal = 0; // 
			else {
				defaultList = new ArrayList<>();
				for (int i = 0; i<D;i++) {
					defaultList.add(i+1);
					defaultList.add(-(i+1));
				}
			
				for (int i = 1; i<=D; i++) {
					List<Integer> list = new ArrayList<>();
					comb(list, 0, i);
					if (minVal != Integer.MAX_VALUE) break;
				}
			}
			System.out.println(minVal);
		}
	}
	
	// Col 성능 테스트 
	static boolean checkTest(int j, int[][] layerArr) {
		int run = 0;

		for (int i = 1; i<D; i++) {
			if (layerArr[i][j] == layerArr[i-1][j]) {
				run++;
				if (run>=K) return true;
			} else run = 1;
		}
		return false;
	}
	
	// 배치할 조합 
	static void comb(List<Integer> cur, int start, int len) {
		if (len == cur.size()) {
			makeNewLayer(new ArrayList<>(cur), len);
			return;
		}
		
		for (int i = start; i<defaultList.size(); i++) {
			
			cur.add(defaultList.get(i));
			comb(cur, i+1, len);
			cur.remove(cur.size()-1);
		}
	}
	
	// 새로운 층 생성 
	static void makeNewLayer(List<Integer> list, int val) {
		
		int[][] newLayer = new int[D][];
		for (int i = 0; i<D; i++) {
			newLayer[i] = Arrays.copyOf(layer[i], layer[i].length);
		}
		
		for (int n : list) {
			int row = Math.abs(n)-1;		
			int fill = (n>0) ? 1 : 0;
			Arrays.fill(newLayer[row], fill);
		}
		
		for (int col : store) {
			if (!checkTest(col, newLayer)) return;
		}
		
		minVal = Math.min(minVal, val);
		
	}
}
