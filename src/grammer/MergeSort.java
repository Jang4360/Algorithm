package grammer;
import java.io.*;
import java.util.*;
public class MergeSort {
	static int N;
	static int[] A, tmp;
	static long count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t<=T; t++) {
        	N = Integer.parseInt(br.readLine());
        	A = new int[N];
        	tmp = new int[N];
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for (int i = 0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());
        	count = 0;
        	mergeSort(0,N-1);
        	System.out.println(A[A.length/2] + " " + count);
        }
	}
	
	static void mergeSort(int l, int r) {
		if (l>=r) return;
		int n = r - l + 1;                // 현재 구간 길이
        int m = l + (n / 2) - 1;
        
		mergeSort(l,m);
		mergeSort(m+1,r);
		
		if (A[m] > A[r]) count++;
		
		int i = l, j = m+1, k = l;
		while (i<=m && j<=r) {
			if (A[i]<=A[j]) tmp[k++] = A[i++];
			else tmp[k++] = A[j++];
		}
		while (i<=m) tmp[k++] = A[i++];
		while (j<=r) tmp[k++] = A[j++];
		for(int x = l; x<=r; x++) A[x] = tmp[x];
	}
}
