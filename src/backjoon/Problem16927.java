package backjoon;
import java.util.*;
import java.io.*;
public class Problem16927 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]), R = Integer.parseInt(input[2]);
		int[][] arr = new int[N][M];
		for (int i = 0; i<N; i++) {
			String[] row = br.readLine().split(" ");
			for (int j = 0; j<M; j++) {
				arr[i][j] = Integer.parseInt(row[j]);
			}
		}
		  int layers = Math.min(N, M) / 2;
	        for (int l = 0; l < layers; l++) {
	            List<Integer> layer = new ArrayList<>();
	            // top
	            for (int j = l; j < M - l - 1; j++) layer.add(arr[l][j]);
	            // right
	            for (int i = l; i < N - l - 1; i++) layer.add(arr[i][M - l - 1]);
	            // bottom
	            for (int j = M - l - 1; j > l; j--) layer.add(arr[N - l - 1][j]);
	            // left
	            for (int i = N - l - 1; i > l; i--) layer.add(arr[i][l]);

	            int size = layer.size();
	            int rot = R % size;

	            // 회전한 결과
	            List<Integer> rotated = new ArrayList<>();
	            for (int i = 0; i < size; i++) {
	                rotated.add(layer.get((i + rot) % size));
	            }

	            int idx = 0;
	            // top
	            for (int j = l; j < M - l - 1; j++) arr[l][j] = rotated.get(idx++);
	            // right
	            for (int i = l; i < N - l - 1; i++) arr[i][M - l - 1] = rotated.get(idx++);
	            // bottom
	            for (int j = M - l - 1; j > l; j--) arr[N - l - 1][j] = rotated.get(idx++);
	            // left
	            for (int i = N - l - 1; i > l; i--) arr[i][l] = rotated.get(idx++);
	            
	        }

	        // 출력
	        StringBuilder sb = new StringBuilder();
	        for (int[] row : arr) {
	            for (int i = 0; i < row.length; i++) {
	                sb.append(row[i]);
	                if (i != row.length - 1) sb.append(" ");
	            }
	            sb.append("\n");
	        }
	        System.out.print(sb);
	    }
		
}
