import java.util.ArrayList;

import java.util.List;

public class BinarySearchTree {

	public static class TreeNode {

		int key;
		TreeNode left, right, parent;

		public TreeNode(int key) {
			this.key = key;
		}

		public String toString() {
			return String.format("%d", key);

		}
	}

	private TreeNode root = null;
	
	public void root(TreeNode root) {
		this.root = root;
	}

	private static int getLevel(TreeNode node) {

		if (node == null)
			return 0;

		int leftD = getLevel(node.left);

		int rightD = getLevel(node.right);

		return (leftD > rightD) ? leftD + 1 : rightD + 1;

	}

	private static void nodeList(TreeNode node, List<TreeNode> arr, int level, int currentLevel) {

		if (node == null)
			return;

		if (level == currentLevel)
			arr.add(node);

		currentLevel++;

		nodeList(node.left, arr, level, currentLevel);

		nodeList(node.right, arr, level, currentLevel);

	}

	public static List<List<TreeNode>> nodeList(TreeNode node) {

		List<TreeNode> arr = new ArrayList<>();

		List<List<TreeNode>> Larr = new ArrayList<>();

		int maxLevel = getLevel(node);

		for (int i = 0; i < maxLevel; i++) {

			nodeList(node, arr, i, 0);

			List<TreeNode> cloned_arr = new ArrayList<TreeNode>(arr);

			Larr.add(cloned_arr);

			arr.clear();

		}

		return (Larr);

	}

	public static void main(String[] args) {
		
		BinarySearchTree isBTS1 = new BinarySearchTree(); 

		TreeNode root = new TreeNode(5);
		TreeNode node1 = new TreeNode(3);
		TreeNode node2 = new TreeNode(9);
		TreeNode node3 = new TreeNode(4);
		TreeNode node4 = new TreeNode(8);
		TreeNode node5 = new TreeNode(2);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(1);  
		
		isBTS1.root(root); 
		root.left = node1; 
		root.right = node2; 

		node1.right = node3; 
		
		node2.left = node4;
		node2.right = node5;  

		node4.left = node6;
		node4.right = node7; 

		System.out.println(nodeList(root));
	}

}