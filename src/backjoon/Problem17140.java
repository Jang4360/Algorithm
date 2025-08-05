package backjoon;
import java.io.*;
import java.util.*;
public class Problem17140 {
	static int R,C,K;
	static List<List<Integer>> A;
	static int time=0;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st1.nextToken());
		C = Integer.parseInt(st1.nextToken());
		K = Integer.parseInt(st1.nextToken());
		A = new ArrayList<>();
		
		for (int i = 0; i<3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			List<Integer> list = new ArrayList<>();
			for (int j = 0; j<3; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}		
			A.add(list);
		}
		
		while (true) {
			
			if (time > 100) {
				time = -1;
				break;
			}
			
			if (A.size() >= R && A.size() > 0 && A.get(0).size() >= C && A.get(R-1).get(C-1) == K) {
				break;
			}
			
			time++;
			int rLen = A.size();
			int cLen = (rLen > 0) ? A.get(0).size() : 0;
			
			if (rLen>=cLen) {
				// R 연산
				List<List<Integer>> newA = new ArrayList<>();
				int maxLen = 0;
				
				for (int i = 0; i<rLen; i++) {
					Map<Integer,Integer> map = new HashMap<>();
					for (int j = 0; j<A.get(i).size(); j++) {
						int key = A.get(i).get(j);
						if (key != 0) {
							map.put(key, map.getOrDefault(key, 0)+1);
						}
					}
					
					List<int[]> list = new ArrayList<>();
					for (int key :map.keySet()) {
						int val = map.get(key);
						list.add(new int[] {key, val});
					}
					
					list.sort((a,b)->{
						if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
						else return Integer.compare(a[0], b[0]);
					});
					
					List<Integer> row = new ArrayList<>();
					for (int[] value: list) {
						if (row.size() >= 100) break;
						row.add(value[0]);
						if (row.size() >= 100) break;
						row.add(value[1]);
						
					}
 					newA.add(row);
 					maxLen = Math.max(maxLen, row.size());
				}
				

				for (List<Integer> row : newA) {
					while (row.size() < maxLen) {
						row.add(0);
					}
				}
				A = newA;
				
			} else {
				// C 연산
				List<List<Integer>> newA = new ArrayList<>();
				
				for (int j = 0; j<cLen; j++) {
					Map<Integer,Integer> map = new HashMap<>();
					for (int i = 0; i<rLen; i++) {
						int key = A.get(i).get(j);
						if (key != 0) {
							map.put(key, map.getOrDefault(key, 0)+1);
						}
					}
					
					List<int[]> list = new ArrayList<>();
					for (int key :map.keySet()) {
						int val = map.get(key);
						list.add(new int[] {key, val});
					}
					
					list.sort((a,b)->{
						if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
						else return Integer.compare(a[0], b[0]);
					});
					
					List<Integer> col = new ArrayList<>();
					for (int[] value: list) {
						if (col.size() >= 100) break;
						col.add(value[0]);
						if (col.size() >= 100) break;
						col.add(value[1]);
					}
										
					// col에 빈 리스트 추가하기 
 					while (newA.size() < col.size()) {
 						newA.add(new ArrayList<>());
 					}
 					
 					
 					// col 열에 넣기 
 					for (int r = 0; r<col.size(); r++) {
 						List<Integer> row = newA.get(r);
 						while (row.size() < j) {
 							row.add(0);
 						}
 						row.add(col.get(r));
 					}
				}
				int maxLen = 0;
				for (List<Integer> row : newA) {
				    maxLen = Math.max(maxLen, row.size());
				}
				
				for (List<Integer> row : newA) {
					while (row.size()<maxLen) {
						row.add(0);
					}
				}
				

				A = newA;
			}

			
		}
		System.out.println(time);
	}
	
}
