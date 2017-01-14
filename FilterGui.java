package library;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import com.mysql.jdbc.Connection;


/**
 * FilterGUI class where the user will be able to make some filtering for the books
 * existent in the database
 * @author Maria-Bianca Cindroi
 * 
 */
public class FilterGui {
	
	// Main frame for the program
	private JFrame frame;
	// Object to load the drivers for the database
	DBConnection db;
	// Object to establish connection with the database;
	Connection c;
	// integer to hold the quantity of books
	private int qty;
	// ComboBox for the book's genre
	JComboBox<String> comboBoxGenre;
	// ComboBox for the book's genre
	JComboBox<String> comboBoxAuthor;
	// ComboBox for the book's country
	JComboBox<String> comboBoxLanguage;
	// ComboBox for the book's price
	JComboBox<String> comboBoxPrice;
	// JList will hold a list with the result from the filters
	private JList<String> list;
	// Model to be populated with the book names
	DefaultListModel<String> model = new DefaultListModel<String>();
	// book's title
	String title = "";
	// book's genre
	String genre = "";
	// book's pages
	int pages = 0;
	// book's author
	String author = "";
	// book's language
	String language = "";
	// book's ISBN
	String ISBN = "";
	// book's price
	double price = 0;
	// book's reviewOnAmazon
	double reviewOnAmazon = 0;
	// book's description
	String description = "";
	// book's bookid
	int bookid = 0;
	// book's coverImage
	byte[] coverImage = null;
	// book's publishing date
	int publishDate = 0;
	//book's review date
	int reviewDate = 0;
	// Array with names of book
	String[] bookTitle = null;
	// Array with the books IDS
	int[] bookIDs = null;
	CardLayout cardLayout;
	// Panel to get a view from the book with all its fields
	BookView view;
	// Object with all the logic to retrieve the needed fields for a specific book
	//@see bookList_Log
	BookList book;

	/**
	 *  Create the application.
	 * @param frame
	 * @param db
	 * @param c
	 */
	public FilterGui(JFrame frame, DBConnection db, Connection c) {
		this.db = db;
		this.c = c;
		this.frame = frame;
		initialize();
	}

	/**
	 * Initialise the contents.
	 */
	private void initialize() {

		// Main panel holding the filters GUI and the book view GUI
		JPanel mainPanel = new JPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 0;
		frame.getContentPane().add(mainPanel, gbc_mainPanel);
		mainPanel.setLayout(new CardLayout(0, 0));
		// Cardlayout to make the change of panels
		cardLayout = (CardLayout) (mainPanel.getLayout());
		
		// Imagepanel holding the filters GUI.
		ImagePanel filters = new ImagePanel("/images/Welcome.jpg");
		filters.setSize(1200, 800);
		filters.setLayout(new BoxLayout(filters, BoxLayout.X_AXIS));

		// Panel holding the home button, the filters comboBox and the button to
		// filter the database
		JPanel panelButtons = new JPanel();
		panelButtons.setForeground(new Color(0, 0, 0));
		panelButtons.setOpaque(false);
		filters.add(panelButtons);
		panelButtons.setPreferredSize(new Dimension(500, 32767));
		GridBagLayout gbl_panelButtons = new GridBagLayout();
		gbl_panelButtons.rowHeights = new int[] { 95, 24, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelButtons.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_panelButtons.columnWeights = new double[] { 1.0, 1.0 };
		panelButtons.setLayout(gbl_panelButtons);
		// Home button
		JButton btnHome = new JButton("Home");
		btnHome.setToolTipText("Click here to go back to the home screen.");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// the frame will remove the mainpanel for this section
				frame.getContentPane().remove(mainPanel);
				// starting the welcome screen
				// @see WelcomeScreen
				new WelcomeScreen(frame, db, c);
				// setting the welcome screen boolean as the same as this
				// section
				// frame will update to show the welcome screen panel in the
				// same frame
				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
			}
		});
		btnHome.setIcon(
				new ImageIcon(FilterGui.class.getResource("/javax/swing/plaf/metal/icons/ocean/homeFolder.gif")));
		btnHome.setToolTipText("Click here to go back to the home screen.");
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(SystemColor.controlDkShadow);
		btnHome.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		btnHome.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, new Color(64, 64, 64), null));

		GridBagConstraints gbc_btnHome = new GridBagConstraints();
		gbc_btnHome.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnHome.insets = new Insets(0, 0, 5, 5);
		gbc_btnHome.gridx = 0;
		gbc_btnHome.gridy = 0;
		panelButtons.add(btnHome, gbc_btnHome);
		
		// Label for the comboBox author
		JLabel lblAuthor = new JLabel("AUTHOR:");
		lblAuthor.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 33));
		lblAuthor.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.anchor = GridBagConstraints.WEST;
		gbc_lblAuthor.insets = new Insets(0, 100, 5, 0);
		gbc_lblAuthor.gridx = 1;
		gbc_lblAuthor.gridy = 1;
		panelButtons.add(lblAuthor, gbc_lblAuthor);

		comboBoxAuthor = new JComboBox<String>();
		comboBoxAuthor.setMaximumRowCount(100);
		comboBoxAuthor.setUI(new BasicComboBoxUI());
		comboBoxAuthor.setBackground(new Color(230, 230, 250));
		comboBoxAuthor.setOpaque(false);
		comboBoxAuthor.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 30));
		GridBagConstraints gbc_comboBoxAuthor = new GridBagConstraints();
		// Array populated with all the authors of a book from the database
		// @see FiltersComboBox
		FiltersComboBox array2 = new FiltersComboBox("author", db, c);
		// Setting the array as a model for the comboBox
		comboBoxAuthor.setModel(new DefaultComboBoxModel<String>(array2.get_data()));
		gbc_comboBoxAuthor.insets = new Insets(0, 115, 35, 0);
		gbc_comboBoxAuthor.fill = GridBagConstraints.BOTH;
		gbc_comboBoxAuthor.gridx = 1;
		gbc_comboBoxAuthor.gridy = 2;
		panelButtons.add(comboBoxAuthor, gbc_comboBoxAuthor);
		comboBoxAuthor.setSelectedIndex(0);
		
		// ComboBox holding all possible GENRE for a book
		// Label for the comboBox 'GENRE'
		JLabel lblGenre = new JLabel("GENRE:");
		lblGenre.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 33));
		lblGenre.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblGenre = new GridBagConstraints();
		gbc_lblGenre.anchor = GridBagConstraints.WEST;
		gbc_lblGenre.insets = new Insets(0, 100, 5, 0);
		gbc_lblGenre.gridx = 1;
		gbc_lblGenre.gridy = 3;
		panelButtons.add(lblGenre, gbc_lblGenre);
		
		//ComboBox holding all possible genres in the database
		comboBoxGenre = new JComboBox<String>();
		comboBoxGenre.setMaximumRowCount(100);
		comboBoxGenre.setUI(new BasicComboBoxUI());
		comboBoxGenre.setBackground(new Color(230, 230, 250));
		comboBoxGenre.setOpaque(false);
		comboBoxGenre.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 30));
		// Array populated with all the genres from the database
		// @see FiltersComboBox
		FiltersComboBox array3 = new FiltersComboBox("genre", db, c);
		// Setting the array as a model for the comboBox
		comboBoxGenre.setModel(new DefaultComboBoxModel<String>(array3.get_data()));
		GridBagConstraints gbc_comboBoxGenre = new GridBagConstraints();
		gbc_comboBoxGenre.insets = new Insets(0, 115, 35, 0);
		gbc_comboBoxGenre.fill = GridBagConstraints.BOTH;
		gbc_comboBoxGenre.gridx = 1;
		gbc_comboBoxGenre.gridy = 4;
		panelButtons.add(comboBoxGenre, gbc_comboBoxGenre);
		comboBoxGenre.setSelectedIndex(0);
	
		// Label for the comboBox language
		JLabel lblCountry = new JLabel("LANGUAGE:");
		lblCountry.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 33));
		lblCountry.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.WEST;
		gbc_lblCountry.insets = new Insets(0, 100, 5, 0);
		gbc_lblCountry.gridx = 1;
		gbc_lblCountry.gridy = 5;
		panelButtons.add(lblCountry, gbc_lblCountry);

		//ComboBox holding all possible languages in the database
		comboBoxLanguage = new JComboBox<String>();
		comboBoxLanguage.setMaximumRowCount(100);
		comboBoxLanguage.setUI(new BasicComboBoxUI());
		comboBoxLanguage.setBackground(new Color(230, 230, 250));
		comboBoxLanguage.setOpaque(false);
		comboBoxLanguage.setFont(new Font("Footlight MT Light", Font.ITALIC, 30));
		comboBoxLanguage.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		// Array populated with all the languages from the database
		// @see FiltersComboBox
		FiltersComboBox array1 = new FiltersComboBox("language", db, c);
		// Setting the array as a model for the comboBox
		comboBoxLanguage.setModel(new DefaultComboBoxModel<String>(array1.get_data()));
		GridBagConstraints gbc_comboBoxLanguage = new GridBagConstraints();
		gbc_comboBoxLanguage.insets = new Insets(0, 115, 35, 0);
		gbc_comboBoxLanguage.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxLanguage.gridx = 1;
		gbc_comboBoxLanguage.gridy = 6;
		panelButtons.add(comboBoxLanguage, gbc_comboBoxLanguage);
		comboBoxLanguage.setSelectedIndex(0);
		
		// Label for the comboBox price
		JLabel lblPrice = new JLabel("PRICE:");
		lblPrice.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 33));
		lblPrice.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 100, 5, 0);
		gbc_lblPrice.gridx = 1;
		gbc_lblPrice.gridy = 7;
		panelButtons.add(lblPrice, gbc_lblPrice);

		//ComboBox holding a range of prices 
		comboBoxPrice = new JComboBox<String>();
		comboBoxPrice.setMaximumRowCount(100);
		comboBoxPrice.setUI(new BasicComboBoxUI());
		comboBoxPrice.setBackground(new Color(230, 230, 250));
		comboBoxPrice.setOpaque(false);
		comboBoxPrice.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 30));
		comboBoxPrice.addItem("");
		comboBoxPrice.addItem("0-5 $");
		comboBoxPrice.addItem("5-10 $");
		comboBoxPrice.addItem("10-15 $");
		comboBoxPrice.addItem("15+ $");
		GridBagConstraints gbc_comboBoxPrice = new GridBagConstraints();
		gbc_comboBoxPrice.insets = new Insets(0, 115, 35, 0);
		gbc_comboBoxPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPrice.gridx = 1;
		gbc_comboBoxPrice.gridy = 8;
		panelButtons.add(comboBoxPrice, gbc_comboBoxPrice);
		comboBoxPrice.setSelectedIndex(0);

		//Button to filter your selection on the database
		JButton btnFilterNow = new JButton("   Filter Now   ");
		btnFilterNow.setBackground(Color.DARK_GRAY);
		btnFilterNow.setForeground(Color.WHITE);
		btnFilterNow.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnFilterNow.setFont(new Font("Kristen ITC", Font.BOLD, 25));
		GridBagConstraints gbc_btnFilterNow = new GridBagConstraints();
		gbc_btnFilterNow.insets = new Insets(0, 115, 10, 0);
		gbc_btnFilterNow.gridx = 1;
		gbc_btnFilterNow.gridy = 10;
		panelButtons.add(btnFilterNow, gbc_btnFilterNow);

		btnFilterNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clearing the model with the books first
				model.clear();
				//int to define the min price
				double min = 0;
				//int to define the max price
				double max = 0;
				//checking the selected range of price to then define the min and max price
				if (comboBoxPrice.getSelectedItem().toString().equals("0-5 $")) {
					min = -1;
					max = 5;
				} else if (comboBoxPrice.getSelectedItem().toString().equals("5-10 $")) {
					min = 5;
					max = 10;
				} else if (comboBoxPrice.getSelectedItem().toString().equals("10-15 $")) {
					min = 10;
					max = 15;
				} else if (comboBoxPrice.getSelectedItem().toString().equals("15+ $")) {
					min = 15;
					max = 1000000;
				} else {
					min = 0;
					max = 0;
				}
				
				//Defining the object used to build the query
				//@see Filterlog
				FiltersSql filter = new FiltersSql(comboBoxGenre.getSelectedItem().toString(),
						comboBoxAuthor.getSelectedItem().toString(),
						comboBoxLanguage.getSelectedItem().toString(), min,
						max);
				//empty both arrays
				bookTitle = null;
				bookIDs = null;
				
				//setting quantity to 0
				qty = 0;
				try {
					Statement stmt = null;
					c.setAutoCommit(false);
					stmt = c.createStatement();
					//using the method to get the needed query
					//@see Filterlog
					ResultSet rs = stmt.executeQuery(filter.getBooks());

					while (rs.next()) {
						//qty equal to the amount of books in the database for the selected filters
						qty = ((Number) rs.getObject(1)).intValue();
					}
					// close the connection
					rs.close();
					stmt.close();
					c.commit();
				} catch (Exception e1) {
					System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					//in case of connection lost the program will establish connection again
					c = (Connection) db.getConn();

				}
				
				//setting the size of both arrays 
				bookTitle = new String[qty];
				bookIDs = new int[qty];
				try {
					Statement stmt = null;
					c.setAutoCommit(false);
					stmt = c.createStatement();
					//using the method to get the needed query
					//@see Filterlog
					ResultSet rs = stmt.executeQuery(filter.getBookSql());

					int i = 0;
					while (rs.next()) {
						//setting the title of each book into the model
						model.addElement(rs.getString("title"));
						//saving the bookid of each book in the array
						bookIDs[i] = rs.getInt("bookid");
						//saving the title of each book in the array
						bookTitle[i] = rs.getString("title");
						i++;
					}
					if (!model.isEmpty())
						//once here it will set the model to the list
						list.setModel(model);
					else {
						//if nothing results ut of the query
						model.addElement("Sorry, there is no book maching your selection!");
						list.setModel(model);
					}
					//updating the list
					list.revalidate();
					list.repaint();
					// close the connection
					rs.close();
					stmt.close();
					c.commit();
				} catch (SQLException ae) {
					System.out.println(ae);
					//in case of connection lost the program will establish connection again
					c = (Connection) db.getConn();
				}
			}
		});
		
		//Panel holding the ScrollPane
		JPanel panelTable = new JPanel();
		panelTable.setBorder(null);
		panelTable.setOpaque(false);
		filters.add(panelTable);
		GridBagLayout gbl_panelTable = new GridBagLayout();
		gbl_panelTable.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelTable.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelTable.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelTable.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		panelTable.setLayout(gbl_panelTable);
		
		//ScrollPane holding the list where the items will be presented to the user
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setOpaque(false);
		scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.setBackground(new Color(176,196,222));
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setPreferredSize(new Dimension(1000, 600));
		scrollPane.setMinimumSize(new Dimension(600, 400));
		scrollPane.getViewport().setOpaque(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(210, 40, 55, 190);
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 3;
		panelTable.add(scrollPane, gbc_scrollPane);
		
		//list where the items will be presented to the user
		list = new JList<String>();
		list.setSelectionForeground(SystemColor.textHighlightText);
		list.setSelectionBackground(SystemColor.activeCaptionBorder);
		list.setForeground(new Color(0, 0, 0));
		list.setOpaque(false);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		list.setBackground(new Color(230, 230, 250));
		list.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 30));
		list.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(list);
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//if the mouse is clicked once
				if (e.getClickCount() == 1) {
					//getting the book index in the list 
					int bookNo = list.getSelectedIndex();
					//getting the bookid for the selected book
					bookid = bookIDs[bookNo];
					//@see BookList
					book = new BookList(title, db, c);
					try {
						Statement stmt = null;
						c.setAutoCommit(false);
						stmt = c.createStatement();
						//retrieving all the attributes for a specific book
						ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE bookid = " + bookid);
						while (rs.next()) {
							title = rs.getString("title");
							genre = rs.getString("genre");
							pages = rs.getInt("pages");
							author = rs.getString("author");
							language = rs.getString("language");
							ISBN = rs.getString("ISBN");
							price = rs.getDouble("price");
							reviewOnAmazon = rs.getDouble("reviewOnAmazon");
							description = rs.getString("description");
							bookid = rs.getInt("bookid");
							coverImage = rs.getBytes("coverImage");
						}
						
						//close connection
						rs.close();
						stmt.close();
						c.commit();
					} catch (SQLException e2) {
						e2.printStackTrace();
						//in case of connection lost it will reconnect with the database
						c = (Connection) db.getConn();
					}
					//setting each specific field in the view with its related attribute
					if (title.length()>39){
						
					view.settextArea_Title(title.substring(0,40)+"\n"+title.substring(39)+
							"\n by" + author);
					}
					else view.settextArea_Title(title+"\n by"+" "+author);
					view.settextField_Genre(genre);
					view.settextField_ISBN(ISBN);
					if (reviewDate != 0){
					view.settextField_PublishReviewDate(Integer.toString(publishDate) +"/"+
					Integer.toString(reviewDate));
					}
					else {
						view.settextField_PublishReviewDate(Integer.toString(publishDate));
					}
					view.settextField_Pages(Integer.toString(pages));
					view.setTextField_Price(Double.toString(price));
					view.settextField_ReviewOnAmazon(Double.toString(reviewOnAmazon) + "/5");
					int length = description.length()/5;
					String string0 = description.substring(0,length);
					description = description.substring(length);
					String string = description.substring(0, length);
					description = description.substring(length);
					String string1 = description.substring(0, length);
					description = description.substring(length);
					String string2 = description.substring(0, length);
					description = description.substring(length);
					String string3 = description.substring(0, length);
					description.substring(0, length);
					view.textArea_Description.setText(string0 + "\n"+
					string + "\n" + string1 + "\n" + string2 +"\n"+ string3+ "\n" +description);
					
					view.setlblcoverImage(coverImage);
					//showing the view panel
					cardLayout.show(mainPanel, "2");
				}
			}
		};
		list.addMouseListener(mouseListener);
		list.setCellRenderer(new TransparentListCellRenderer());
		JLabel lblClickInOne = new JLabel("Choose a book for more details!");
		lblClickInOne.setForeground(Color.DARK_GRAY);
		lblClickInOne.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		scrollPane.setColumnHeaderView(lblClickInOne);
		scrollPane.getColumnHeader().setOpaque(false);
		mainPanel.add(filters, "1");
		
		//starting the panel with the book view
		view = new BookView();
		//giving it the book's title
		BookView.title = title;
		//giving it the drivers for the database
		BookView.db = db;
		//connecting it with the database
		BookView.c = (com.mysql.jdbc.Connection) c;
		
		//giving it the book's bookid
		view.bookid = bookid;
		BookView.btBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//changing to the filters panel
				cardLayout.show(mainPanel, "1");
				//setting the boolean as the same
			}
		});
		mainPanel.add(view, "2");
	}
	/**
	 * Class to set transparency to each cell in the list
	 * @author Team2
	 *
	 */
	@SuppressWarnings("serial")
	public class TransparentListCellRenderer extends DefaultListCellRenderer {
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			setOpaque(isSelected);
			return this;
		}
	}
}
