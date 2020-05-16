package allFiles;

public class Node<T extends Comparable<Book>>  {

	// Instance Variables

	private Book book; 
	private Node<Book> left, right;

	// Constructor

	Node(Book b) {
		this.book = b;
		this.left = null;
		this.right = null;
	}

	// hasLeft() --> return true if the node has left child  

	public boolean hasLeft() {
		return (this.left != null );
	}

	// getLeft() --> return the left child

	public Node<Book> getLeft() {
		return left;
	}

	// setLeft() --> create a new left child

	public void setLeft(Node<Book> left) {
		this.left = left;
	}

	// hasRight() --> return true if the node has right child   

	public boolean hasRight() {
		return (this.right != null );
	}
 
	// getRight --> return the right child

	public Node<Book> getRight() {
		return right;
	}

	// setRight() --> create a new right child

	public void setRight(Node<Book> right) {
		this.right = right;
	}

	// getValue() --> return the value of the node(the key)

	public Book getValue() {
		return book;
	}

	// setValue() --> update the value of the node

	public void setValue(Book book) {
		this.book = book;
	}

	// toString

	public String toString() {

		return this.book.toString();
	}	

	// comparable

	public int compare(Node<Book> b) {
		return this.book.compareTo(b.getValue());
	}
}
