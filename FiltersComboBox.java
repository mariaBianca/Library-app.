package library;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
/**
 * FiltersComboBox class responsible for fetching from the DataBase
 * The required information to dynamically fill in the JComboBox in the FilterGUI class
 * With information
 * 
 * @author Maria-Bianca Cindroi
 * refactored from Team 2: Project Programming, Wine Advisor
 * 
 */

public class FiltersComboBox {
	// Connection to the Database
	DBConnection db;
	Connection c;
	// Array of Strings that will hold the information
	private String [] array = null;
	// int that will hold the number of entries
	// Required to build the array
	private int qty = 0;
	// String holding the attribute/ column information in the DataBase
	private String attribute;
	
	// FiltersComboBox constructor with parameters String, DBConnection, Connection
	public FiltersComboBox(String attribute, DBConnection db, Connection c){
		this.db = db;
		this.c = c;
		this.attribute = attribute;
	}
	// end of constructor
	
	// Method to get the information from the Database
	// in the form of an array of Strings
	public String[] get_data(){
		
	// Try/ catch	
	try{
		// Execute Query
	    Statement stmt = null;
	    c.setAutoCommit(false);
	    stmt  = c.createStatement();
	    ResultSet rs = null;	  
	    rs = stmt.executeQuery( "SELECT COUNT(DISTINCT "+ this.attribute+") FROM books");
	    
	    // while loop to retrieve the number of entries
	      while ( rs.next() ) {
	         qty = ((Number) rs.getObject(1)).intValue();	      
	      }
	      //close the connection
	      rs.close();
	      stmt.close();
	      c.commit();
	    } catch ( SQLException e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      //in case of connection lost it will reconnect with the database
	      this.c = (Connection) db.getConn();
	    }
	// Set the length of the array String[]
	array = new String[qty+1];
	// initialize the array[0] as an empty string
	// which will show the first line in the JComboBox as empty
	array[0] = "";
	
	// try/ catch
	try{		
		// Execute Query
		Statement stmt = null;
		c.setAutoCommit(false);
		stmt  = c.createStatement();
		ResultSet rs = null;   
		rs = stmt.executeQuery("SELECT DISTINCT "+ this.attribute+" FROM books ORDER BY "+ this.attribute +" ASC");   
		// String[] already has the index at 0 filled
		// So we start to fill the rest of the indexes starting at 1
		int i = 1;    
		while ( rs.next() ) { 	  	
    	  	array[i] = rs.getString(this.attribute);   	  	
    	  	i++;    	    	  	
		}
      //close the connection
      rs.close();
      stmt.close();
      c.commit();    
    } catch ( SQLException ae ) {
      System.err.println( ae );
      //in case of connection lost it will reconnect with the database
      this.c = (Connection) db.getConn();
    }
	// Return the String[] containing the indicated attribute in the String attribute ordered in ascending order
	return array;	
	}
	
	// Method to get the String[] created in this class
	public String[] getArray (){
		return this.array;
	}	
}
// End of FiltersComboBox class
