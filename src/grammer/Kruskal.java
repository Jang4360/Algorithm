package grammer;

import java.io.*;
import java.util.*;

public class Kruskal {
	static int V,E;
	static List<long[]> edgeList;
	static int[] parents, rank;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			V = Integer.parseInt(input[0]);
			E = Integer.parseInt(input[1]);
			edgeList = new ArrayList<>();
			parents = new int[V+1];
			rank = new int[V+1];
			
			for (int i = 0; i<E; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edgeList.add(new long[] {a,b,w});
			}
			
			edgeList.sort(Comparator.comparingLong(a -> a[2]));
			make();
			int picked = 0;
			long result = 0;
			for (long[] edge : edgeList) {
				int a = (int) edge[0], b = (int) edge[1];
				long w = edge[2];
				if (!union(a,b)) continue;
				result += w;
				picked++;
				if (picked == V-1) break;
			}
			
			System.out.println("#"+ t + " " +result);
		}
	}
	
	static void make() {
		for (int i = 1; i<=V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int node) {
		if (parents[node] == node) return node;
		return parents[node] = find(parents[node]);
	}
	
	static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if (ra == rb) return false;
		
		if (rank[ra] > rank[rb]) parents[rb] = ra;
		else if (rank[ra] < rank[rb]) parents[ra] = rb;
		else {parents[rb] = ra; rank[ra]++;}
		
		return true;
	}
}
