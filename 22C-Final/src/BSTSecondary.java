
/**
 * BSTSecondary.java
 * @author Renmei Gao
 *  
 * The second Binary Search Tree which inherited the first BST class.
 * Manipulates data include insertion, searching, deletion and sorting 
 * according method CompareSecondaryKey of the Interface Comparable 
 * which is defined by comparing prices inside the Object class Cosmetic.
 * 
 * CIS 22C Final Project
 */

import java.util.NoSuchElementException;

public class BSTSecondary<T extends Comparable<T> & Contain & FileOutPutFormat> extends BST<T> {

	/*** CONSTRUCTORS ***/

	/**
	 * Default constructor for BST2 sets root to null
	 */
	public BSTSecondary() {
		root = null;
	}

	/**
	 * Copy constructor for BSTSecondary
	 * 
	 * @param BST2 the BST2 to make a copy of
	 */
	public BSTSecondary(BSTSecondary<T> bst) {
		if (bst.root == null) {
			root = null;
		} else {
			copyHelper(bst.root);
		}
	}

	/**
	 * Helper method for copy constructor
	 * 
	 * @param node the node containing data to copy
	 */
	private void copyHelper(Node node) {
		if (node != null) {
			insert(node.data);
			copyHelper(node.left);
			copyHelper(node.right);
		}
	}

	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 * 
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when precondition is violated
	 */
	public Node getRoot() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Error for getRoot. Tree is empty.");
		}
		return root;
	}

	/**
	 * Determines whether the tree is empty
	 * 
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Returns the current size of the tree (number of nodes)
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		return getSize(root);
	}

	/**
	 * Helper method for the getSize method
	 * 
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + getSize(node.left) + getSize(node.right);
		}
	}

	/**
	 * Returns the height of tree by counting edges.
	 * 
	 * @return the height of the tree
	 */
	public int getHeight() {
		return getHeight(root);
	}

	/**
	 * Helper method for getHeight method
	 * 
	 * @param node the current node whose height to count
	 * @return the height of the tree
	 */
	private int getHeight(Node node) {
		if (node == null) {
			return -1;
		}
		return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}

	/**
	 * Returns the smallest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMin() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Error for findMin. Tree is empty.");
		}
		return findMin(root);
	}

	/**
	 * Helper method to findMin method
	 * 
	 * @param node the current node to check if it is the smallest
	 * @return the smallest value in the tree
	 */
	private T findMin(Node node) {
		if (node.left != null) {
			return findMin(node.left);
		}
		return node.data;
	}

	/**
	 * Returns the largest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMax() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Error for findMax. Tree is empty.");
		}
		return findMax(root);
	}

	/**
	 * Helper method to findMax method
	 * 
	 * @param node the current node to check if it is the largest
	 * @return the largest value in the tree
	 */
	private T findMax(Node node) {
		if (node.right != null) {
			return findMax(node.right);
		}
		return node.data;
	}

	/**
	 * Searches for a specified value in the tree
	 * 
	 * @param data the value to search for
	 * @return whether the value is stored in the tree
	 */
	@Override
	public boolean search(T data) {
		if (root == null) {
			return false;
		} else {
			return search(data, root);
		}
	}

	/**
	 * Helper method for the search method
	 * 
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored in the tree
	 */
	
	private boolean search(T data, Node node) {
		if (data.CompareSecondaryKey(node.data) == 0) {
			return true;
		} else if (data.CompareSecondaryKey(node.data) < 0) {
			if (node.left == null) {
				return false;
			}
			return search(data, node.left);
		} else {
			if (node.right == null) {
				return false;
			}
			return search(data, node.right);
		}
	}

	/**
	 * Determines whether two trees store identical data in the same structural
	 * position in the tree
	 * 
	 * @param o another Object
	 * @return whether the two trees are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BSTSecondary)) {
			return false;
		} else {
			BSTSecondary<T> b = (BSTSecondary<T>) o;
			return equals(root, b.root);
		}
	}

	/**
	 * Helper method for the equals method
	 * 
	 * @param node1 the node of the first BST2
	 * @param node2 the node of the second BST2
	 * @return whether the two trees contain identical data stored in the same
	 *         structural position inside the trees
	 */
	private boolean equals(Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			return true;
		} else if (node1 == null || node2 == null) {
			return false;
		} else {
			return node1.data.CompareSecondaryKey(node2.data) == 0 && equals(node1.left, node2.left)
					&& equals(node1.right, node2.right);
		}
	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 * 
	 * @param data the data to insert
	 */
	@Override
	public void insert(T data) {
		if (root == null) {
			root = new Node(data);
		} else {
			insert(data, root);
		}
	}

	/**
	 * Helper method to insert Inserts a new value in the tree
	 * 
	 * @param data the data to insert
	 * @param node the current node in the search for the correct location in which
	 *             to insert
	 */
	private void insert(T data, Node node) {
		if (data.CompareSecondaryKey(node.data) <= 0) {
			if (node.left == null) {
				node.left = new Node(data);
			} else {
				insert(data, node.left);
			}
		} else {
			if (node.right == null) {
				node.right = new Node(data);
			} else {
				insert(data, node.right);
			}
		}
	}

	/**
	 * Removes a value from the BST2
	 * 
	 * @param data the value to remove
	 * @precondition !isEmpty()
	 * @precondition the data is located in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	@Override
	public void remove(T data) throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Error for remove. Tree is empty.");
		} else if (!search(data, root)) {
			throw new NoSuchElementException("Error for remove. Element not found.");
		} else {
			root = remove(data, root);
		}
	}

	/**
	 * Helper method to the remove method
	 * 
	 * @param data the data to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	private Node remove(T data, Node node) {
		if (node == null) {
			return null;
		} else if (data.CompareSecondaryKey(node.data) < 0) {
			node.left = remove(data, node.left);
		} else if (data.CompareSecondaryKey(node.data) > 0) {
			node.right = remove(data, node.right);
		} else {
			if (node.left == null && node.right == null) {
				node = null;
			} else if (node.left != null && node.right == null) {
				node = node.left;
			} else if (node.left == null && node.right != null) {
				node = node.right;
			} else {
				node.data = findMin(node.right);
				node.right = remove(findMin(node.right), node.right);
			}
		}
		return node;
	}

	/*** ADDITIONAL OPERATIONS ***/

	/**
	 * Prints the data in pre order to the console
	 */
	public void preOrderPrint() {
		if (root == null) {
			return;
		} else {
			preOrderPrint(root);
		}
	}

	/**
	 * Helper method to preOrderPrint method Prints the data in pre order to the
	 * console
	 */
	private void preOrderPrint(Node node) {
		if (node != null) {
			System.out.print(node.data);
			preOrderPrint(node.left);
			preOrderPrint(node.right);
		}
	}

	/**
	 * Prints the data in sorted order to the console
	 */
	public void inOrderPrint() {
		if (root == null) {
			return;
		} else {
			inOrderPrint(root);
		}
	}

	/**
	 * Helper method to inOrderPrint method Prints the data in sorted order to the
	 * console
	 */
	private void inOrderPrint(Node node) {
		if (node != null) {
			inOrderPrint(node.left);
			System.out.print(node.data);
			inOrderPrint(node.right);
		}
	}

	/**
	 * Prints the data in post order to the console
	 */
	public void postOrderPrint() {
		if (root == null) {
			return;
		} else {
			postOrderPrint(root);
		}
	}

	/**
	 * Helper method to postOrderPrint method Prints the data in post order to the
	 * console
	 */
	private void postOrderPrint(Node node) {
		if (node != null) {
			postOrderPrint(node.left);
			postOrderPrint(node.right);
			System.out.print(node.data);
		}
	}

}