package grammer;
import java.io.*;
import java.util.*;
public class SubSet {
	static int n;
	static String[] arr;
	public static void main(String[] args) {
		arr = new String[] {"a","b","c","d"};
		n = arr.length;
//		for (int i = 0; i<(1<<n); i++) {
//			List<String> result = new ArrayList<>();
//			for (int j = 0; j<n; j++) {
//				if ((i&(1<<j)) != 0) {
//					result.add(arr[j]);
//				}
//			}
//			System.out.println(result);
//		}
		subset(0, new boolean[n]);
	}
	
	static void subset(int idx, boolean[] selected) {
		if (idx == n) {
			for (int i = 0; i<n; i++) {
				if (selected[i]) System.out.print(arr[i]);
			}
			System.out.println();
			return;
		}
		
		selected[idx] = true;
		subset(idx+1, selected);
		
		selected[idx] = false;
		subset(idx+1, selected);
	}
}
