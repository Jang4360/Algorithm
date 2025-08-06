package backjoon;
import java.io.*;
import java.util.*;

public class Practice {
	public static void main(String[] args) {
		
		int[] a = new int[]{1,2,3,4,5};
		int[] b = new int[]{1,2,2,3,3};
		boolean[] visited = new boolean[a.length];
 		permute(a,visited,new ArrayList<>());
 		combine(a, 0, new ArrayList<>());
 		duplicatePermute(b, b.length, new ArrayList<>());
 		visited = new boolean[b.length];
 		uniquePermute(b, visited, new ArrayList<>());
 		uniqueCombine(b, 0, new ArrayList<>());
	}
	
	// 순열
	static void permute(int[] a, boolean[] visited, List<Integer> list) {
		if (list.size() == a.length) {
//			System.out.println(list);
			return;
		}
		
		for (int i = 0; i<a.length; i++) {
			if (!visited[i]) {
				list.add(a[i]);
				visited[i] = true;
				permute(a, visited, list);
				list.remove(list.size()-1);
				visited[i] = false;
			}
			
			
		}
	}
	
	// 조합
	static void combine(int[] a, int start, List<Integer> list) {
		if (list.size() == 3) {
//			System.out.println(list);
			return;
		}
		
		for (int i = start; i<a.length; i++) {
			list.add(a[i]);
			combine(a, i+1, list);
			list.remove(list.size()-1);
		}
	}
	
	// 중복 순열
	static void duplicatePermute(int[] b, int r, List<Integer> list) {
		if (list.size() == r) {
//			System.out.println(list);
			return;
		}
		for (int i = 0; i<r; i++) {
			list.add(b[i]);
			duplicatePermute(b, r, list);
			list.remove(list.size()-1);
			
		}
	}
	
	// 중복 제거 순열
	static void uniquePermute(int[] b, boolean[] visited, List<Integer> list) {
		if (list.size() == b.length) {
//			System.out.println(list);
			return;
		}
		
		for (int i = 0; i<b.length; i++) {
			if (visited[i]) continue;
			if (i>0 && b[i] == b[i-1] && !visited[i-1]) continue;
			
			visited[i] = true;
			list.add(b[i]);
			uniquePermute(b, visited, list);
			visited[i] = false;
			list.remove(list.size()-1);
			
		}
	}
	
	// 중복 제거 조합
	static void uniqueCombine(int[]b, int start, List<Integer> list) {
		if (list.size() == 3) {
			System.out.println(list);
			return;
		}
		
		for (int i = start; i<b.length; i++) {
			if (i>start && b[i] == b[i-1]) continue;
			list.add(b[i]);
			uniqueCombine(b, start, list);
			list.remove(list.size()-1);
		}
	}
}