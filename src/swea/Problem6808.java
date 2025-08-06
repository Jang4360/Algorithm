package swea;
import java.io.*;
import java.util.*;
public class Problem6808 {
	static List<Integer> myCards;
	static List<Integer> otherCards;
	static boolean[] visited;
	static int win;
	static int lose;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <=T; t++) {
			win = 0;
			lose = 0;
			myCards = new ArrayList<>();
			otherCards = new ArrayList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i<9; i++) {
				myCards.add(Integer.parseInt(st.nextToken()));
			}
			
			for (int i = 1; i<=18; i++) {
				if (myCards.contains(i)) continue;
				otherCards.add(i);
			}
			
			visited = new boolean[9];
			dfs(new ArrayList<>());
			System.out.println("#"+t+ " "+ win + " " + lose);
		}
		
	}
	
	static void dfs(List<Integer> list) {
		if (list.size() ==  9) {
			playGame(list);
			return;
		}
		
		for (int i = 0; i<9; i++) {
			if (!visited[i]) {
				list.add(otherCards.get(i));
				visited[i] = true;
				dfs(list);
				list.remove(list.size()-1);
				visited[i] = false;
			}
		}
		
	}
	
	static void playGame(List<Integer> others) {
		int myTotal = 0;
		int otherTotal = 0;
		for (int i = 0; i<9; i++) {
			int other = others.get(i);
			int my = myCards.get(i);
			if (my > other) {
				myTotal += my+other;
			} else {
				otherTotal += my+other;
			}
		}
		if (myTotal>otherTotal) win++;
		else if (myTotal<otherTotal) lose++;
	}
}
