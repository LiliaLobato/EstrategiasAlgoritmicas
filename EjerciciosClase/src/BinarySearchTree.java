import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
	private class TreeNode {
		T key;
		TreeNode left, right, parent;

		public TreeNode(T key) {
			this.key = key;
		}
	}

	private TreeNode root = null;

	private void print(TreeNode node, String spaces) {
		if (node == null) return;
    // Recorrido PRE-orden
		System.out.println(spaces + node.key);
		print(node.left, spaces + "  ");
		print(node.right, spaces + "  ");
	}

	public void print() {
		print(this.root, "");
		System.out.println();
	}
	
	private int getLevel(TreeNode node) {
		if (node == null) return 0;
		
		int leftD = getLevel(node.left);
		int rightD = getLevel(node.right);
		return (leftD > rightD)? leftD+1:rightD+1;
	}
	
	private void nodeList (TreeNode node, List<Integer> arr, int level, int currentLevel) {
		if (node == null) return;

		if(level == currentLevel) arr.add((int) node.key);
		currentLevel++;
		nodeList(node.left, arr, level, currentLevel);
		nodeList(node.right, arr, level, currentLevel);
	}

	public List<List<Integer>> nodeList (TreeNode node) {
        List<Integer> arr=new ArrayList<>();
        List<List<Integer>> Larr=new ArrayList<>();
        int maxLevel = getLevel(this.root);
		for(int i = 0; i < maxLevel; i++) {
			nodeList(node,arr,i,0);
	        List<Integer> cloned_arr = new ArrayList<Integer>(arr);
			Larr.add(cloned_arr);
			arr.clear();
		}
		return(Larr);
	}
	
	private int sumInternalNodes(TreeNode node, int sum) {
		if (node == null) return sum;
    // Recorrido PRE-orden
		if(node.left != null || node.right != null ) {
			sum = sum + (int) node.key;
		}
		sum = sumInternalNodes(node.left, sum);
		sum = sumInternalNodes(node.right, sum);

		return sum;
	}

	public int sumInternalNodes() {
		return sumInternalNodes(this.root, 0);
	}

	private int countGreaterThan(TreeNode node, int key, int cnt) {
		if (node == null) return cnt;
    // Recorrido PRE-orden

		if((int) node.key > key ) {
			cnt = cnt + 1;
			//System.out.println(node.key);
		}
		cnt = countGreaterThan(node.left, key, cnt);
		cnt = countGreaterThan(node.right, key, cnt);

		return cnt;
	}

	public int countGreaterThan(int key) {
		int cnt = 0;
		return countGreaterThan(this.root, key, cnt);
	}

	
	public void allKeys(TreeNode node, ArrayList<Integer> arr) {
		if(node == null) return;
		
		arr.add((int) node.key);				
		allKeys(node.left,arr);
		allKeys(node.right,arr);		
		return;
	}
	
	public boolean getPath(TreeNode node, ArrayList<Integer> arr ,int key) {
		if(node == null) return false;
		arr.add((int) node.key); //añadimos la llave
		
		//llegamos al nodo deseado, retorna
		if((int)node.key == key) return true;
		
		//recorremos el árbol
		if(getPath(node.left,arr,key) || getPath(node.right,arr,key)) return true;
		
		//si no estaba ni en left ni right, lo quitamos de la lista
		arr.remove(arr.size()-1);
		return false;
	}
	
	public boolean sumOddPath(TreeNode node, int key) {
		int cnt = 0;
        ArrayList<Integer> arr=new ArrayList<>();
        ArrayList<Integer> arrKeys=new ArrayList<>();
        
        //obtenemos todas las claves de los nodos
        allKeys(node,arrKeys);
        
        //por cada una de las claves, obtenemos su path
        for (int i=0; i<arrKeys.size(); i++) {          
	        if(getPath(node,arr,arrKeys.get(i))) {
	        	//recorremos el path y contamos los impares
	            for (int j=0; j<arr.size(); j++) {  
	                if(arr.get(j) % 2 != 0) cnt++;
	            }
	            //si encontramos un path con x num impares 
	            // y no estamos en root, el path si exsite
	        	if(cnt == key && cnt != 1) return true;
	        }
	        arr.clear();
	        cnt=0;
        }
		return false;
	}
	
	
	private boolean existsNodeSmallerThan(TreeNode node, boolean exist, int key) {
		if (node == null || exist == true) return exist;
    // Recorrido PRE-orden
		if((int) node.key < key ) exist = true;
		exist = existsNodeSmallerThan(node.left,exist, key);
		exist = existsNodeSmallerThan(node.right, exist,key);
		return exist;
	}

	public boolean existsNodeSmallerThan(int key) {
		return existsNodeSmallerThan(this.root, false ,key);
	}

	private boolean isFull(TreeNode node, boolean full) {
		if (node == null || full == false) return full;
    // Revisamos que el nodo sea interno
    boolean hasOnlyLeft  = (node.left != null && node.right == null);
    boolean hasOnlyRight = (node.left == null && node.right != null);
    if(hasOnlyLeft || hasOnlyRight) {
			full = false;
		}
		full = isFull(node.left,full);
		full = isFull(node.right,full);
		return full;
	}

	public boolean isFull(TreeNode node) {
		return isFull(node, true);
	}
	
	public boolean isSmallestOdd(TreeNode node) {
  	if(node == null) return false;
    if((int) node.key % 2 == 1){
			return true;
    } else {
			return isSmallestOdd(node.left);
    }
  }
	
	public boolean add(T key) {
    // El ABB está vacío: la clave se inserta como raíz
    if(this.root == null) {
      this.root = new TreeNode(key);
      return true;
    }
    // El ABB no está vacío, lo recorremos de arriba-abajo comenzando en la raíz
    boolean added = false, found = false;
    TreeNode node = this.root;
    while(!found && !added) {
      // key < node.key, key > node.key, key == node.key
      int res = key.compareTo(node.key);
      if (res == 0) {       // key == node.key
        found = true;
      } else if(res < 0) {  // key < node.key
        if(node.left == null){
          node.left = new TreeNode(key);
          node.left.parent = node;
          added = true;
        }else{
          node = node.left;
        } 
      } else {              // key > node.key
        if (node.right == null) {
          node.right = new TreeNode(key);
          node.right.parent = node;
          added = true;
        } else {
          node = node.right;
        }
      }
    }
		return added;
	}
  
	private TreeNode search(T key, TreeNode current) {
    if (current == null) return null;
    int compareVal = key.compareTo(current.key);
    if (compareVal == 0) return current;
    if (compareVal  < 0) return search(key, current.left);
    return search(key, current.right);
	}

	public boolean contains(T key) {
    if(this.root == null) return false;
    TreeNode node = search(key, this.root);
    return node != null;
	}

	private TreeNode minimum(TreeNode current) {
    if(current == null) return null;
    while(current.left != null){
      current = current.left;
    }
		return current;
	}

	public T minimum() {
    if(this.root == null) return null;
    TreeNode minimumNode = minimum(this.root);
    return minimumNode.key;
	}

	private TreeNode maximum(TreeNode current) {
    if (current == null) return null;
    while (current.right != null) {
      current = current.right;
   }
		return current;
	} 

	public T maximum() {
    if (this.root == null) return null;
    TreeNode maximumNode = maximum(this.root);
		return maximumNode.key;
	}

	private TreeNode predecessor(TreeNode current) {
    // Tiene hijo izquierdo, el predecesor es el máximo en el subárbol izquierdo
    if (current.left != null) {
      return maximum(current.left);
    } else {
			// no tiene hijo izquierdo, el predecesor es el primer padre tal que
			// el nodo actual sea hijo derecho :c
			TreeNode parent = current.parent;
			while (parent != null && parent.right != current) {
				current = parent;
				parent = parent.parent;
			}
			return parent;
    }
	}

	public T predecessor(T key) {
    if(this.root == null) return null;
    TreeNode currNode = search(key, this.root);
    if(currNode == null) return null;
    TreeNode preNode  = predecessor(currNode);
    // preNode es null para el nodo con clave menor
		return preNode != null? preNode.key : null;
	}

  private TreeNode sucessor(TreeNode current) {
    if (current.right != null) {
      return minimum  (current.right);
    } else {
			// no tiene hijo derecho, el sucesor es el primer padre tal que
			// el nodo actual sea hijo izquierdo :c
			TreeNode parent = current.parent;
			while (parent != null && parent.left != current) {
				current = parent;
				parent = parent.parent;
			}
			return parent;
    }
  }

  public T sucessor(T key) {
    if(this.root == null) return null;
    TreeNode currNode = search(key, this.root);
    if(currNode == null) return null;
    TreeNode sucNode  = sucessor(currNode);
    // sucNode es null para el nodo con clave mayor
		return sucNode != null? sucNode.key : null;
  }


	private void delete(TreeNode node) {
    // Caso 1: node es una hoja
    if(node.right == null && node.left == null) {
      if (root == node) {
        root = null;
      } if(node.parent.right == node){
        node.parent.right = null;
      } else {
        node.parent.left = null;
      }
    }
    // Caso 3: node tiene dos hijos
    else if(node.left != null && node.right != null) {
      TreeNode pred = predecessor(node);
      T predKey = pred.key;
      node.key = predKey;
      delete(pred);
    }       
    // Caso 2: node tiene un hijo
    else {
      // node tiene sólo hijo derecho
      if(node.right != null) {
        if(root == node) {
          root = node.right;
        } else {
          // node es hijo izquierdo de su padre
          if(node.parent.left == node) {
            node.parent.left = node.right;
          } else {
          // node es hijo derecho de su padre
            node.parent.right = node.right;
          }
        } 
      } else {
        //node tiene sólo hijo izquierdo
        if(root == node) {
          root = node.left;
        } else {
          // node es hijo izquierdo de su padre
          if (node.parent.left == node) {
            node.parent.left = node.left;
          }
          // node es hijo derecho de su padre
          else{
            node.parent.right = node.left;
          }
        } 
      }
    } 
	}

	public boolean delete(T key) {
    if(this.root == null) return false;
    TreeNode currNode = search(key, this.root);
    if(currNode == null) return false;
		delete(currNode);
    return true;
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> intBST = new BinarySearchTree<>();
		intBST.add(5);  intBST.add(3);  intBST.add(7);
		intBST.add(2); intBST.add(4);   intBST.add(6);
		intBST.add(9);  
		intBST.print();

        
	
		System.out.println(intBST.sumOddPath(intBST.root, 1));
		System.out.println(intBST.sumOddPath(intBST.root, 2));
		System.out.println(intBST.sumOddPath(intBST.root, 3));
		System.out.println(intBST.sumOddPath(intBST.root, 4));
		
		BinarySearchTree<Integer> intBST2 = new BinarySearchTree<>();
		intBST2.add(5); intBST2.add(3);  intBST2.add(9);
		intBST2.add(4); intBST2.add(8);   intBST2.add(2);
		intBST2.add(6); intBST2.add(1);
		intBST2.print();
		
		List<List<Integer>> nodeList = intBST2.nodeList(intBST2.root);
		System.out.println(nodeList);
		
    //           4
    //         /   \
    //       2       6
    //      / \     / \
    //     1   3   5   7


	}
}
