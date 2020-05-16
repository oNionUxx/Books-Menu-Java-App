package allFiles;

import java.util.Stack;

public class BinarySearchTree<T> {

	// Instance Variables

	private Node<Book> root;

	// Constructor

	public BinarySearchTree() {
		this.root = null ;
	}

	// Return the root of the tree

	public Node<Book> getRoot() {
		return root;
	}

			/****************************************************************/
			/*							add methods							*/ 
			/*																*/
			/****************************************************************/

	public void add(Node<Book> b) {
		if (root == null) // tree is empty
			this.root = b; // set as root

		else 
			addIterative(b); 
	}

	// Add new book to the tree in a Iterative manner
	// The method using comparable for comparison

	private void addIterative(Node<Book> b) {

		Node<Book> current = this.root;

		while(b.compare(current) < 0 && current.hasLeft()
				|| (b.compare(current)) > 0 && current.hasRight()) {

			if (b.compare(current) < 0)
				current = current.getLeft(); // go left
			else
				current = current.getRight(); // go right

		} // End of while

		Node<Book> node = b;

		if (b.compare(current) < 0) 
			current.setLeft(node);

		else
			current.setRight(node);
	} // End of method

			/****************************************************************/
			/*					   Exists&&Search Methods			        */ 
			/*																*/
			/****************************************************************/

	// Find s in the tree

	public boolean exists(String s) {
		return existsRecursive(root, s);
	}

	// Find s in the tree in recursive manner

	private boolean existsRecursive(Node<Book> current, String s) {
		if (current == null) 
			return false;

		if(current.getValue().equals(s)) // return true if the value was found	
			return true;

		boolean res1 = existsRecursive(current.getLeft(),s); // search left side

		boolean res2 = existsRecursive(current.getRight(),s); // search right side

		return res1 || res2;
	}	

	// Find s in the tree

	public Node<Book> searchElement(Node<Book> current, String s) {
		if(current != null) 
			if(current.getValue().equals(s)) { // return the object if the value was found	 
				return current;
			}
			else {
				Node<Book> foundNode = searchElement(current.getLeft(), s); // search left side

				if(foundNode == null) 
					foundNode = searchElement(current.getRight(), s); // search right side

				return foundNode;
			}
		else 
			return null;
	}
	
			/****************************************************************/
			/*					Find max, Minimum Successor					*/
			/*  					&& Predecessor Nodes  					*/ 
			/****************************************************************/

	// Find node with maximum value

	public Node<Book> biggest() throws Exception{
		if (root == null)
			throw new Exception("Tree is Empty");
		return biggest(this.root);
	}

	private Node<Book> biggest (Node<Book> current){
		if (!current.hasRight())
			return current;
		return biggest(current.getRight());
	}

	// Find node with minimum value

	public Node<Book> smallest() throws Exception{
		if (root == null) 
			throw new Exception("Tree is Empty");
		return smallest(this.root);
	}

	private Node<Book> smallest(Node<Book> current){
		while(current.hasLeft()) {
			current = current.getLeft();
		}
		return current;
	}

	private Node<Book> getPredecessor(Node<Book> node, Stack<Node<Book>> ancestors){
		if (node == smallest(root)) {
			return null;
		}
		else if (node.hasLeft()) { // item has left subtree
			return biggest(node.getLeft());
		}
		else {
			Node<Book> parent = ancestors.pop();
			while(parent.getLeft() == node && !ancestors.isEmpty()) {
				node = parent;
				parent = ancestors.pop(); // previous ancestor
			}
			return parent;
		}
	}

			/****************************************************************/
			/*					    Delete Methods						  	*/ 
			/*   															*/
			/****************************************************************/

	public boolean delete(Node<Book> b) {		

		if (root == null)
			return false;

		Stack<Node<Book>> ancestors = new Stack<Node<Book>>(); 
		Node<Book> current = root;

		while(current != b && b != null) { //search for the node with the value of key
			ancestors.push(current);

			if(b.compare(current) < 0)   // go left
				current = current.getLeft();

			else  //go right
				current = current.getRight();

			if(current == null)  // didn't find it
				return false;	  // return false 

		}

		// We get to this section if the node to be deleted exists 
		// Current holds the node to be deleted
		// Ancestors holds all ancestors along the path from root to current

		// Case One: THE NODE TO BE DELETED HAS NO CHILDREN		
		// If no children, simply delete it

		if(current.hasLeft() == false && current.hasRight() == false) 
			deleteLeaf(current, ancestors);

		// Case Two: THE NODE TO BE DELETED HAS TWO CHILDREN	

		else if(current.hasLeft() && current.hasRight()) 
			deleteNodeWithTwoSubtrees(current, ancestors);

		// Case Three: THE NODE TO BE DELETED HAS ONE CHILD	
		// If only one child, replace with subtree

		else 
			deleteNodeWithOneSubtree(current, ancestors);

		return true;
	}

	private void deleteNodeWithOneSubtree(Node<Book> itemToDelete, Stack<Node<Book>> ancestors) {
		if(itemToDelete == root) {

			if (itemToDelete.hasLeft()) 
				root = itemToDelete.getLeft();

			else 
				root = itemToDelete.getRight();
		}
		else {
			Node<Book> parent = ancestors.pop(); // last ancestors from stack

			if (itemToDelete == parent.getLeft())   // itemToDelete is a left child
				if (itemToDelete.hasLeft())  // itemToDelete has a left child 
					parent.setLeft(itemToDelete.getLeft());

				else  // itemToDelete has a right child 
					parent.setLeft(itemToDelete.getRight());

			else  // itemToDelete is a right child
				if (itemToDelete.hasLeft())  // itemToDelete has a left child 
					parent.setRight(itemToDelete.getLeft());

				else // itemToDelete has a right child 
					parent.setRight(itemToDelete.getRight());
		}

		if(itemToDelete.hasLeft()) 
			itemToDelete.setLeft(null);

		else 
			itemToDelete.setRight(null);
	}

	private void deleteLeaf(Node<Book> itemToDelete, Stack<Node<Book>> ancestors) {

		if(itemToDelete == root) { // we have a tree with just a root 
			root = null;  // tree is empty
		}
		else {
			Node<Book> parent = ancestors.pop(); // last ancestors popped from stack

			if (itemToDelete == parent.getLeft())  // itemToDelete is a left child
				parent.setLeft(null);
			
			else  // deletedItem is a right child
				parent.setRight(null);
		}
	}

	public void deleteNodeWithTwoSubtrees(Node<Book> itemToDelete, Stack<Node<Book>>ancestors) {

		Node<Book> predecessor = getPredecessor(itemToDelete, ancestors);
		delete(predecessor); // delete the successor from the tree

		if(itemToDelete == root) {
			root = predecessor;
		}
		else { // connect parent of deletedItem to successor
			Node<Book> parent = ancestors.pop();  // last ancestors of stack

			if (itemToDelete == parent.getLeft())  // itemToDelete is a left child
				parent.setLeft(predecessor);
			
			else  // deletedItem is a right child
				parent.setRight(predecessor);
		}	
		
		// Connect successor to itemToDelete's left child
		
		predecessor.setLeft(itemToDelete.getLeft());

		// Connect successor to itemToDelete's right child
		
		predecessor.setRight(itemToDelete.getRight());
	}

			/****************************************************************/
			/*					   Traversal Methods					  	*/ 
			/*   															*/
			/****************************************************************/

	// INORDER traversal

	public void inOrder() {
		callInOrder(this.root);
	}

	private void callInOrder(Node<Book> current) {
		if (current == null) 
			return;
		
		callInOrder(current.getLeft());

		System.out.println(" " + current);

		callInOrder(current.getRight());
	}

	// PREORDER traversal

	public void preOrder() {
		callPreOrder(this.root);
	}

	private void callPreOrder(Node<Book> current) {
		if (current == null) 
			return;
		
		System.out.println(" " + current);

		callInOrder(current.getLeft());

		callInOrder(current.getRight());
	}

	// POSTORDER traversal

	public void postOrder() {
		callPostOrder(this.root);
	}

	private void callPostOrder(Node<Book> current) {
		if (current == null) 
			return;
		
		callInOrder(current.getLeft());

		callInOrder(current.getRight());

		System.out.println(" " + current);
	}
}