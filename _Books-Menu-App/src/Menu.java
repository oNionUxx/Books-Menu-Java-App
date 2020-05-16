package allFiles;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Menu {

	/* Variables Declarations - do not modified */

	public static boolean exit, startAgain;
	public static String answer, toBeSearched;
	public static Scanner input = new Scanner(System.in);
	public static BinarySearchTree tree = new BinarySearchTree();
	public static ArrayList<Node<Book>> books;

	public static <T> void main(String[] args) {
		
		Menu menu = new Menu();
		menu.runMenu();
	}
	
	private void initiazlize() {
		
		books = new ArrayList<Node<Book>>();

		// List of books

		books.add(new Node<Book>(new Book("AAA","1","z",66,100)));
		books.add(new Node<Book>(new Book("BBB","2","y",44,200)));
		books.add(new Node<Book>(new Book("CCC","3","x",33,300)));
		books.add(new Node<Book>(new Book("DDD","4","w",22,400)));
		books.add(new Node<Book>(new Book("EEE","5","v",55,500)));
		books.add(new Node<Book>(new Book("FFF","6","u",11,600)));
		books.add(new Node<Book>(new Book("GGG","7","t",77,700)));
		books.add(new Node<Book>(new Book("HHH","8","s",300,800)));
		books.add(new Node<Book>(new Book("III","9","r",99,900)));
		books.add(new Node<Book>(new Book("JJJ","10","q",100,1000)));
		books.add(new Node<Book>(new Book("KKK","11","p",200,2000)));
		books.add(new Node<Book>(new Book("LLL","12","o",88,3000)));
	}
	
	public void runMenu() {
		performAction();
	}

	private void printHeader() {
		System.out.println("			+---------------------------------------------------+");
		System.out.println("			|                  Welcome to our 		    |        ");
		System.out.println("			|                 Menu Applications                 |");
		System.out.println("			|               ---------------------               |");
		System.out.println("			|						    |"                        );
	}

	private void printMenu() {

		System.out.println("			| (1) Add Book                                      |");
		System.out.println("			| (2) Remove Book                                   |");
		System.out.println("			| (3) Search For A Book                             |");
		System.out.println("			| (4) Display Book                                  |");
		System.out.println("			| (5) Exit                                          |");
		System.out.println("			+---------------------------------------------------+");

	}

	private int getInput(int choise) {

		while(choise < 0 || choise > 5) {
			System.out.print("\n Please make a selection: ");
			try {
				if(input.hasNextInt()) {
					choise = input.nextInt();
				}
				else {
					choise = Integer.parseInt(input.nextLine());
					break;
				}

				if(choise < 0 || choise > 5) {
					System.out.print("\n Enter a number within the required range");
				}
			} catch (NumberFormatException e) {
				System.out.println("\n Invalid Selection. Please try again");
			}
		}
		return choise;
	}

	private void performAction() {

		int choise;

		while(!exit) {

			choise = -1;
			answer = "";
			printHeader();
			printMenu();
			choise = getInput(choise);

			switch(choise) {

			case 1: 
				add();  /* add book */
				break;

			case 2: 
				removed(); /* remove book */
				break;

			case 3: 
				SearchBook(); /* search for a book */
				break;	

			case 4: 
				traversTheTree(); /* display traversals different type of ways */
				break;

			case 5: 
				exitOption(); 	// exit 
			}

		}
	}

	public void add() {
		
		initiazlize();
		boolean startAgain = true;

		for(ListIterator<Node<Book>> b = books.listIterator(); b.hasNext();) {
			if(startAgain)  // iterate the book list
				while(b.hasNext()) { // book list is not empty
					Node<Book> book = b.next(); 

					tree.add(book); // add book to the tree

					b.remove(); // remove current book from the list

					System.out.println("\n " + book.getValue().getBook_name() +
							" has been Succsessfully added to your tree!"); 

					System.out.print("\n Would you like to add another book? (Y/N) ");
					utilityExitOption(); //user's input

					if(answer.equalsIgnoreCase("Y")) 
						break; // breaks the loop, repeat process

					else  // raise flag, breaks current loop
						startAgain = false; 
					break;
				}

			else  // user do not want to add another book
				break;
		}

		if(books.isEmpty() && startAgain)  // book list is empty
			System.out.println("\n Sorry no books available atm");

		return; // return to menu
	}

	public void removed() {

		Node<Book> toBeDeleted;
		String nameToBeDeleted;
		startAgain = true;	

		while(startAgain) { 

			System.out.print("\n Enter a book name partial or full name: ");
			nameToBeDeleted = input.next(); // user book name's input

			if(tree.exists(nameToBeDeleted)) { // if book was found 

				while(tree.getRoot() != null) { // Start from tree's root and checks if the tree isn't empty

					toBeDeleted = tree.searchElement(tree.getRoot(), nameToBeDeleted); // search for the requested name 																	    // assigning toBeDeleted --> name  

					tree.delete(toBeDeleted); // requested book was removed from the tree

					System.out.println("\n " + toBeDeleted.getValue().getBook_name()
							+ " has been Succsessfully removed from your tree!");
					break;
				}

				System.out.print("\n would you like to stay? ");
				utilityExitOption();

				if(!answer.equalsIgnoreCase("Y")) 
					startAgain = false; // start whole process again
			}

			else {
				System.out.println("\n The book you are looking for was not found");
				System.out.print("\n You might have enterd a wrong book name"
						+ " or you've alreay removed this book");

				System.out.print("\n \n Try again? ");
				utilityExitOption();

				if(answer.equalsIgnoreCase("Y")) 
					startAgain = false; // start whole process again

				else 
					return; // back to menu
			}
		}
	}

	public void SearchBook() {

		startAgain = true;
		System.out.print("\n Enter a book name partial or full name: ");
		String toBeSearched = input.next();


		// Search the tree according to the book's name
		while(startAgain) {

			if(tree.exists(toBeSearched))  // return true if the book exists
				System.out.println("\n Book has been found!");

			else  
				System.out.println("\n Sorry this book is unavailable atm or"
						+ " you might have entered the book name wrong");

			System.out.print(" Would you like to search again? (Y/N) ");

			utilityExitOption();

			if(!answer.equalsIgnoreCase("Y")) 
				startAgain = false;
		}
		return;
	}

	public void traversTheTree() {

		String toBeDisplayed;
		startAgain = true;

		while(startAgain) {

			if(tree.getRoot() != null)  /* tree is not empty */ {

				System.out.println("\n Please select the way you'd like to traversed the tree: ");
				System.out.print("\n PreOrder type --> 'PreO', InOrder  type 'InO', PostOrder type --> 'PostO' ");

				toBeDisplayed = input.next(); /* user's input */

				if(toBeDisplayed.equalsIgnoreCase("PreO") || toBeDisplayed.equalsIgnoreCase("InO") 
						|| toBeDisplayed.equalsIgnoreCase("PostO")) {

					traversTheTreeUtilityFunction(toBeDisplayed);
					System.out.print("\n Return to menu? or keep traversing? (Y/N) ");

					utilityExitOption(); /* the user may want to try again */

					if(!answer.equalsIgnoreCase("Y")) 
						startAgain = false; /* breaks loop */
				}
				else { /* if we reached here, user's input might be wrong */
					System.out.println("\n Your selection must be written excatly as mentioned above!");
				}
			}
			else {
				System.out.println("\n Please add books before you trying to display them.");
				break;
			}
		}
		return;
	}

	private void traversTheTreeUtilityFunction(String traversal) {

		if(traversal.equalsIgnoreCase("preO")) /* display preOrder traversal */
			tree.preOrder();

		else if(traversal.equalsIgnoreCase("inO")) /*display inOrder order traversal*/
			tree.inOrder();

		else 
			tree.postOrder(); /* display postOrder order traversal */
	}

	private String utilityExitOption() {

		answer = input.next();

		while(!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
			System.out.print("\n Something went wrong, be sure to type only (Y/N)");
			answer = input.next();
		}	
		return answer;
	}

	public void exitOption() {

		System.out.print("\n Are u sure you want to exit? (Y/N) ");
		answer = input.next(); /* user's answer */

		while(!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N") ) {
			System.out.print("\n Invalid choise. Please type only Y/N ");
			answer = input.next();
		}

		if(answer.equalsIgnoreCase("Y")) {
			System.out.println("\n Bye Bye!");
			exit = true;
		}
		else  {
			System.out.print("\n Thank you for choosing to stay! \n");
			return;
		}
	}
}