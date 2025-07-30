package backjoon;
import java.io.*;
public class Problem2477 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());

		int[] dir = new int[6];
		int[] len = new int[6];

		for (int i = 0; i < 6; i++) {
			String[] input = br.readLine().split(" ");
			dir[i] = Integer.parseInt(input[0]);
			len[i] = Integer.parseInt(input[1]);
		}

		int maxWidth = 0, maxHeight = 0;
		int maxWidthIdx = 0, maxHeightIdx = 0;
		for (int i = 0; i<6; i++) {
			if (dir[i] == 1 || dir[i] == 2) {
				if (len[i]>maxWidth) {
					maxWidth = len[i];
					maxWidthIdx = i;
				}
			}
 		}
		
		for (int j = 0; j<6; j++) {
			if (dir[j] == 3 || dir[j] == 4) {
				if (len[j]>maxHeight) {
					maxHeight = len[j];
					maxHeightIdx = j;
				}
			}
		}
		
		int smallW = Math.abs(len[(maxWidthIdx+5)%6] - len[(maxWidthIdx+1)%6]);
		int smallH = Math.abs(len[(maxHeightIdx+5)%6] - len[(maxHeightIdx+1)%6]);
		int total = (maxWidth*maxHeight - (smallW*smallH))*K;
		System.out.println(total);
	}
}
