package library;


import java.sql.*;

/**
 * Connection to the database.
 * 
 * @author Maria-Bianca Cindroi
 */
public class DBConnection {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/elolambookstore"; 

	/**
	 * Constructor. Loads the driver to be used.
	 */
	public DBConnection() {
		try {
			System.err.println("Loading driver...");
			Class.forName("com.mysql.jdbc.Driver");
			System.err.println("Driver loaded...");
			//getConn();
		} catch (ClassNotFoundException ex) {
			System.err.println("\n" + "Could not load driver..." + "\n");
			System.err.println(ex);
		}
	}

	/**
	 * Returns a connection to the database.
	 * @return Connection
	 */
	public Connection getConn() {
		// the connection object to return
		Connection conn = null;

		try {
			//connect
			String res = "";
			conn = DriverManager.getConnection(URL, "root", "Alisoi1*");
			System.err.println("Connected!");
			//test the connection
			Statement stmt = null;
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT title FROM books " + " WHERE bookid=" + 6 + ";");
			// fetch the "title" of the book
			while (rs.next()) {
				res = rs.getString("title");
			}
			System.out.println(res);

		} catch (SQLException e) {
			// handle errors for JDBC here
			System.err.println("\n" + "Could not connect..." + "\n");
			System.err.println(e);
		}

		// return the connection
		return conn;

	} // end getConn()
}
