package library;
/**
 * BookList class is responsible for retrieving and displaying the book list from the db,
 * where the program starts.
 * 
 * @author Maria-Bianca Cindroi
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookList {
	// Connection to the Database
	DBConnection db;
	Connection c;
	// Int that holds the bookid
	int bookid;
	// String that Holds the Title of the book
	String title;
	// String ISBN holds the ISBN of the specific book
	String ISBN;
	// String genre, the genre of the book is held
	String genre;
	// Int publishDate represents the date the specific book has been published
	int publishDate;
	// int reviewDate represents the date the specific book has been reviewed by the author
	int reviewDate;
	// int price holds the price of the book on amazon
	double price;
	// double reviewOnAmazon holds the review that the book has gotten on amazon so far
	double reviewOnAmazon;
	// byte[] holds an array of bytes representing the coverImage of the book
	byte[] coverImage;
	// int pages holds the pages number of the book
	int pages;
	// String author holds a specific book's author's name
	String author;
	//String description holds the book's description
	String description;
	//String language holds the languge a book is written in
	String language;
	// byte used to check if any entry is found in the genre views or not
	byte check = 0;

	/**
	 * BookList constructor
	 * 
	 * @param bookid
	 * @param db
	 * @param c
	 */
	public BookList(String title, DBConnection db, Connection c) {
		this.title = title;
		this.db = db;
		this.c = c;
	}

	/**
	 * title method fetches from the database the title of the book 
	 * according to the bookid
	 * 
	 * @param bookid
	 * @return String title holds the title of the book
	 */
	public String title(int bookid) {
		String res = "";
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT title FROM books " + " WHERE bookid=" + bookid + ";");
			// fetch the "title" of the book
			while (rs.next()) {
				res = rs.getString("title");
			}
			// close connection to db
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//reconnect to Db in case the connection was lost before the fetching
			this.c = (Connection) db.getConn();
		}
		// return String with the title of the book
		return res;
	}
	// end of title method

	/**
	 * viewcoverImage method fetches from DataBase the cover image of the specific book
	 * 
	 * @param bookid
	 * @return coverImage
	 */
	public byte[] viewcoverImage(int bookid) {
		byte[] coverImage = null;
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT coverImage FROM books " + " WHERE bookid=" + bookid + ";");
			// fetch the image
			while (rs.next()) {
				coverImage = rs.getBytes("coverImage");
			}
			// end of while loop

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
		// return Image of Book
		return coverImage;
	}
	// end of viewcoverImage method

	/**
	 * viewBook fetches from the DataBase all the values from each column that
	 * agree with the query
	 * 
	 * @param bookid
	 */
	public void viewBook(int bookid) {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE bookid=" + bookid + ";");
			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of viewBook method

	//following methods for views according to book's genre 
	/**
	 * ViewAction&Adventure method fetches books in the "Action&Adventure" category
	 */
	public void ViewActionAdventure() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Action&Adventure' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");

			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}
			// close connection
			rs.close();
			//reset check
			check = 0 ;
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewActionAdventure method

	/**
	 * ViewAnthology fetches books in the "Anthology" category
	 */
	public void ViewAnthology() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Anthology' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			// close connection
			rs.close();
			stmt.close();
			c.commit();

			//reset check
			check = 0;

		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewAnthology method

	/**
	 * ViewChildrensS fetches books in the "Children's" category
	 */
	public void ViewChildrens() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Children's' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}	

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewChildren's method

	/**
	 * ViewComics fetches books in the "Comics" category
	 */
	public void ViewComics() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Comics' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewComics method

	/**
	 * ViewDictionaries fetches books in the "Dictionaries" category
	 */
	public void ViewDictionaries() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Dictionaries' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewDictionaries method

	/**
	 * ViewDrama fetches books in the "Drama" category
	 */
	public void ViewDrama() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Drama' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewDrama method

	/**
	 * ViewEncyclopedias fetches books in the "Encyclopedias" category
	 */
	public void ViewEncyclopedias() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Encyclopedias' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewEncyclopedias method

	/**
	 * ViewGuide fetches books in the "Guide" category
	 */
	public void ViewGuide() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Guide' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewGuide method

	/**
	 * ViewHealth fetches books in the "Health" category
	 */
	public void ViewHealth() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Health' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewHealth method

	/**
	 * ViewHistory fetches books in the "History" category
	 */
	public void ViewHistory() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='History' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewHistory method

	/**
	 * ViewHorror fetches books in the "Horror" category
	 */
	public void ViewHorror() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Horror' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewHorror method

	/**
	 * ViewLiterature fetches books in the "Literature" category
	 */
	public void ViewLiterature() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Literature' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewLiterature method

	/**
	 * ViewMath fetches books in the "Math" category
	 */
	public void ViewMath() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Math' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewMath method

	/**
	 * ViewReligion fetches books in the "Religion" category
	 */
	public void ViewReligion() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Religion' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewMystery method

	/**
	 * ViewPoetry fetches books in the "Poetry" category
	 */
	public void ViewPoetry() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Poetry' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}

	/**
	 * ViewRomance fetches books in the "Romance" category
	 */
	public void ViewRomance() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Romance' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewRomance method

	/**
	 * ViewSatire fetches books in the "Satire" category
	 */
	public void ViewSatire() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Satire' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewSatire method

	/**
	 * ViewScience fetches books in the "Science" category
	 */
	public void ViewScience() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Science' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewScience method

	/**
	 * ViewSelfHelp fetches books in the "Self Help" category
	 */
	public void ViewSelfHelp() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Self Help' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewSelpHelp method

	/**
	 * ViewTravel fetches books in the "Travel" category
	 */
	public void ViewTravel() {
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books " + " WHERE genre='Travel' AND bookid='" + this.bookid + "';");

			// while loop fetches all the values that agree with the Query and
			// saves it in variables
			while (rs.next()) {
				check = 1;
				this.title = rs.getString("title");
				this.ISBN = rs.getString("ISBN");
				this.genre = rs.getString("genre");
				this.publishDate = rs.getInt("publishDate");
				this.reviewDate = rs.getInt("reviewDate");
				this.price = rs.getDouble("price");
				this.reviewOnAmazon = rs.getDouble("reviewOnAmazon");
				this.coverImage = rs.getBytes("coverImage");
				this.pages = rs.getInt("pages");
				this.author = rs.getString("author");
				this.description = rs.getString("description");
				this.language = rs.getString("language");
			}
			// end of while loop
			if (check == 0){
				this.title = "Sorry! There is no book that fits in this genre.";
			}

			//reset check
			check = 0;

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
	}
	// end of ViewTravel method


	/**
	 * Method that retrieves the author of a Book
	 */
	public String Author(int bookid) {
		// Strings to hold the information
		String res = "";
		String sql1 = "SELECT authorid FROM books where bookid =" + this.bookid;
		int num = Integer.parseInt(sql1);
		String sql = "SELECT authorFirstName, authorFamName FROM  author WHERE authorId =  '" + num + "'";
		// try/ catch
		try {
			// Execute Query
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			//hold author's info
			ResultSet rs = stmt.executeQuery(sql);
			// while loop fetches the full name of the author
			while (rs.next()) {
				res = rs.getString("Author");
			}
			//end of while loop

			// close connection
			rs.close();
			stmt.close();
			c.commit();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//in case it loses the connection it will reconnect with the database
			this.c = (Connection) db.getConn();
		}
		// return the resulting author'S info
		return res;
	}
	// end of author method

	
	/**
	 * @return the bookid
	 */
	public int getbookid() {
		return bookid;
	}

	/**
	 * @return the coverImage
	 */
	public byte[] getcoverImage() {
		return this.coverImage;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the ISBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @return the publishDate
	 */
	public int getPublishDate() {
		return publishDate;
	}

	/**
	 * @return the reviewDate
	 */
	public int getReviewDate() {
		return reviewDate;
	}
	/**

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the reviewOnAmazon
	 */
	public double getReviewOnAmazon() {
		return reviewOnAmazon;
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	public String getAuthor(){
		return author;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getLanguage(){
		return language;
	}
	/**
	 * Close the connection with the database
	 */
	public void CloseConnection() {
		try {
			c.close();
			System.out.println("Connection closed successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
// end of BookList class
