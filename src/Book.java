package allFiles;

public class Book implements Comparable<Book> {
	
	// Instance Variables
	
	private String catalog_number, book_name, author;
	private int publish_year, copy_number;

	// Constructor
    
	public Book(String book_name, String catalog_number,
			String author, int publish_year, int copy_number) {
		
		this.book_name = book_name;
		this.catalog_number =  catalog_number;
		this.author = author;
		this.publish_year = publish_year;
		this.copy_number = copy_number;
	}
	
	/**********************************************************************************************/
	/*															                      			  */ 
	/*																						      */
	/**********************************************************************************************/
		
	// Setters && Getters
	
	public String getCatalog_number() {
		return catalog_number;
	}

	public void setCatalog_number(String catalog_number) {
		this.catalog_number = catalog_number;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublish_year() {
		return publish_year;
	}

	public void setPublish_year(int publish_year) {
		this.publish_year = publish_year;
	}

	public int getCopy_number() {
		return copy_number;
	}

	public void setCopy_number(int copy_number) {
		this.copy_number = copy_number;
	}

	
	/**********************************************************************************************/
	/*															                      			  */ 
	/*																						      */
	/**********************************************************************************************/
	
	// toString
	
	@Override
	public String toString() {
		return "Book [catalog_number=" + catalog_number + ", book_name=" + book_name + ", author=" + author
				+ ", publish_year=" + publish_year + ", copy_number=" + copy_number + "]";
	}

	// equals
	
	public boolean equals(String s) {
		return this.book_name.contains(s.toUpperCase());
	}
	
	// comparable
	

	@Override
	public int compareTo(Book b) {
	
		if(this.getPublish_year() > b.getPublish_year()) 
			return 1;
		
		else if(this.getPublish_year() < b.getPublish_year()) 
			return -1;
		
		else 
			return 0;	
	}
}
