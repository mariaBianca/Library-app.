package library;

import java.sql.ResultSet;
import java.sql.Statement;
import com.mysql.jdbc.Connection;

/**
 * Class to define the top 3 books found in the db according to Amazon rating
 * 
 * @author Maria-Bianca Cindroi
 * Refactored from Team2: Project PRogramming, Wine Advisor
 *
 */
public class Top3 {
	//Load drivers
	DBConnection db;
	//Connect to the db
	Connection c;
	//Array to s
	int[] top = new int[3];

	/**
	 * Constructor
	 * @param db
	 * @param c
	 */
	public Top3(DBConnection db, Connection c) {
		this.db = db;
		this.c = c;
	}

	/**
	 * Method to set the top 3 books
	 */
	public void setTop3() {
		try {
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT bookid, title FROM books GROUP BY bookid ORDER BY reviewOnAmazon DESC LIMIT 3");
			int i = 0;
			while (rs.next()) {
				top[i] = rs.getInt("bookid");
				i++;
			}
			// close the connection
			rs.close();
			stmt.close();
			c.commit();
			
		} catch (Exception e1) {
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			//reconnect to db in case of lost connection 
			this.c = (Connection) db.getConn();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int[] getTop() {
		return top;
	}
}
