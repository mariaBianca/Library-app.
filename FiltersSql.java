package library;

/**
 * Class that builds the filters as an input and then builds the query upon these filters
 * @author Maria-Bianca Cindroi
 *
 */
public class FiltersSql {
	String[] title;
	String genre;
	String language;
	String author;
	double priceMin;
	double priceMax;
	int bookid;
	/**
	 * Constructor    
	 * @param genre
	 * @param language
	 * @param author
	 * @param priceMin
	 * @param priceMax
	 */
	public FiltersSql(String genre, String author, String language, double priceMin, double priceMax){
		this.genre = genre;
		this.language = language;
		this.author = author;
		this.priceMin = priceMin;
		this.priceMax = priceMax;

	}
	/**
	 * Builds the query from the selected filters
	 * @return
	 */
	public String getBookSql(){
		String sql = "";


		if(this.priceMin == 0 && this.priceMax == 0 && this.genre.equals("") && 
				this.author.equals("")){
			sql = "SELECT * FROM books Where language='"+this.language+"';";
		}
		else if(this.priceMin == 0 && this.priceMax == 0 && this.language.equals("") && this.author.equals("")){
			sql = "SELECT * FROM books Where genre='"+this.genre+"';";
		}

		else if(this.priceMin == 0 && this.priceMax == 0 && this.genre.equals("") && 
				this.language.equals("")){
			sql = "SELECT * FROM books Where author='"+this.author+"';";
		}
		else if(this.author.equals("") && this.genre.equals("") && this.language.equals("")){
			sql = "SELECT * FROM books Where price > " + this.priceMin + " AND price < " +
					this.priceMax + ";";
		}
		else if(this.genre.equals("") && this.language.equals("")){
			sql = "SELECT * FROM books Where price > " + this.priceMin + " AND price < " 
					+ this.priceMax + " AND author = '" + this.author + "';";
		}
		else if(this.author.equals("") && this.language.equals("")){
			sql = "SELECT * FROM books Where price > " + this.priceMin + " AND price < " 
					+ this.priceMax + " AND genre = '" + this.genre + "';";
		}
		else if(this.author.equals("") && this.genre.equals("")){
			sql = "SELECT * FROM books Where price > " + this.priceMin + " AND price < " 
					+ this.priceMax + " AND language = '" + this.language + "';";
		}
		else if(this.priceMin == 0 && this.priceMax == 0 && this.language.equals("")){
			sql = "SELECT * FROM books Where author='" 
					+ this.author + "' AND genre = '" + this.genre + "';";
		}
		else if(this.priceMin == 0 && this.priceMax == 0 && this.genre.equals("")){
			sql = "SELECT * FROM books Where author='" 
					+ this.author + "' AND language = '" + this.language + "';";
		}
		else if(this.priceMin == 0 && this.priceMax == 0 && this.author.equals("")){
			sql = "SELECT * FROM books Where genre='" 
					+ this.genre + "' AND language = '" + this.language + "';";
		}
		else if (this.priceMin == 0 && this.priceMax == 0){
			sql = "SELECT * FROM books WHERE genre = '"+this.genre+"' AND language ='"+
					this.language+"' AND author = '"+this.author+"';";
		}
		else if (author.equals("")){
			sql = "SELECT * FROM books WHERE genre = '"+this.genre+"' AND language ='"+
					this.language+"' AND price >"+this.priceMin + "AND price <"+this.priceMax+";";
		}
		else if (language.equals("")){
			sql = "SELECT * FROM books WHERE genre = '"+this.genre+"' AND author ='"+
					this.author+"' AND price >"+this.priceMin + "AND price <"+this.priceMax+";";

		}
		else if (this.author.equals("")){
			sql = "SELECT * FROM books WHERE genre='"+this.genre+"' AND language ='"+
					this.language+"'+ price <"+ this.priceMax + "price > "+this.priceMin+";";
		}
		else if (this.language.equals("")){
			sql = "SELECT * FROM books WHERE genre = '"+this.genre+"' AND author='"+
					this.author+"' AND  price < "+ this.priceMax + " AND price > "+this.priceMin+";";

		}
		else if (this.priceMax == 0 && this.priceMin == 0){
			sql = "SELECT * FROM books WHERE genre ='"+this.genre+"'AND author = '"+
					this.author+"' AND language = '"+this.language+"';";
		}
		else if (this.genre.equals("")){
			sql = "SELECT * FROM books WHERE author = '"+this.author+"'AND language ='"+
					this.language+"' AND price <"+this.priceMax + "AND price > " + this.priceMin+";";
		}
		else if (!this.author.equals("") && !this.language.equals("") && !this.genre.equals("")
				&& this.priceMax != 0 && this.priceMin != 0)
		{
			sql = "SELECT * FROM books Where genre='" 
					+ this.genre + "' AND language ='" + this.language + "' AND author='" 
					+ this.author + "' AND price > " + this.priceMin + " AND price < " 
					+ this.priceMax + ";";
		}

		return sql;
	}
	/**
	 * Get the query to find the amount of books for the selected filters
	 * @return
	 */
	public String getBooks(){
		String sql = "";

		if(this.priceMin == 0 && this.priceMax == 0 && this.language.equals("") && this.author.equals("")){
			sql = "SELECT COUNT(title) FROM books Where genre='"+this.genre+"' ;";
		}
		else if(this.priceMin == 0 && this.priceMax == 0 && this.genre.equals("") && this.author.equals("")){
			sql = "SELECT COUNT(title) FROM books Where language='"+this.language+"';";
		}
		else if(this.priceMin == 0 && this.priceMax == 0 && this.genre.equals("") && this.language.equals("")){
			sql = "SELECT COUNT(title) FROM books Where author='"+this.author+"';";
		}
		else if(this.author.equals("") && this.genre.equals("") && this.language.equals("")){
			sql = "SELECT COUNT(title) FROM books Where price > " + this.priceMin + " AND price < " + this.priceMax + ";";
		}
		else if(this.genre.equals("") && this.language.equals("")){
			sql = "SELECT COUNT(title) FROM books Where price > " + this.priceMin + " AND price < " 
					+ this.priceMax + " AND author = '" + this.author + "';";
		}
		else if(this.author.equals("") && this.language.equals("")){
			sql = "SELECT COUNT(title) FROM books Where price > " + this.priceMin + " AND price < " 
					+ this.priceMax + " AND genre = '" + this.genre + "';";
		}
		else if(this.author.equals("") && this.genre.equals("")){
			sql = "SELECT COUNT(title) FROM books Where price > " + this.priceMin + " AND price < " 
					+ this.priceMax + " AND language = '" + this.language + "';";
		}
		else if(this.priceMin == 0 && this.priceMax == 0 && this.language.equals("")){
			sql = "SELECT COUNT(title) FROM books Where author='" 
					+ this.author + "' AND genre = '" + this.genre + "';";
		}
		else if(this.priceMin == 0 && this.priceMax == 0 && this.genre.equals("")){
			sql = "SELECT COUNT(title) FROM books Where author='" 
					+ this.author + "' AND language = '" + this.language + "' ORDER BY title ASC;";
		}
		else if(this.priceMin == 0 && this.priceMax == 0 && this.author.equals("")){
			sql = "SELECT COUNT(title) FROM books Where genre='" 
					+ this.genre + "' AND language = '" + this.language + "';";
		}	
		else if (this.author.equals("")){
			sql = "SELECT COUNT(title) FROM books WHERE genre='"+this.genre+"' AND language ='"+
					this.language+"'+ price <"+ this.priceMax + "price > "+this.priceMin+";";
		}
		else if (this.language.equals("")){
			sql = "SELECT COUNT(title) FROM books WHERE genre = '"+this.genre+"' AND author='"+
					this.author+"' AND  price < "+ this.priceMax + " AND price > "+this.priceMin+";";

		}
		else if (this.priceMax == 0 && this.priceMin == 0){
			sql = "SELECT COUNT(title) FROM books WHERE genre ='"+this.genre+"'AND author = '"+
					this.author+"' AND language = '"+this.language+"';";
		}
		else if (this.genre.equals("")){
			sql = "SELECT COUNT(title) FROM books WHERE author = '"+this.author+"'AND language ='"+
					this.language+"' AND price <"+this.priceMax + "AND price > " + this.priceMin+";";
		}
		else if (!this.author.equals("") && !this.language.equals("") && !this.genre.equals("")
				&& this.priceMax != 0 && this.priceMin != 0)
		{
			sql = "SELECT COUNT(title) FROM books Where genre='" 
					+ this.genre + "' AND language ='" + this.language + "' AND author='" 
					+ this.author + "' AND price > " + this.priceMin + " AND price < " 
					+ this.priceMax + ";";
		}

		return sql;
	}

	static public String sqlTotal(){
		String sql ="SELECT * FROM books";
		return sql;
	}

}//end of class FiltersSql
