package grammer;

public class CompleteBinaryTreeTest {
	public static void main(String[] args) {
		String names[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
		int size = names.length;
		CompleteBinaryTree<String> tree = new CompleteBinaryTree<>(size);
		for (String name : names) {
			tree.add(name);
		}
		
		tree.dfs();
//		tree.dfsByPreOrder(1);
	}
}
