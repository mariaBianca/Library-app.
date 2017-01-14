package library;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import com.mysql.jdbc.Connection;

/**
 * Book class is responsible for setting up book list GUI and presenting 
 * the books information and attributes. 
 * 
 * @author Maria-Bianca Cindroi
 * 
 */
@SuppressWarnings("serial")
public class BookView extends ImagePanel {
	//JTextFields and Areas for books' attributes
	JTextArea textArea_Title;
	JTextField textField_Genre;
	JTextField textField_ISBN;
	JTextField textField_PublishReviewDate;
	JTextField textField_Pages;
	JTextField textField_Price;
	JTextField textField_ReviewOnAmazon;
	JTextArea textArea_Description;
	JTextArea textArea_PrReview;
	//JLabel holding the Amazon star image
	JLabel label_ReviewPic;
	BookList book = new BookList(title, db, c);
	//book's bookid
	int bookid;
	//loading database drivers
	static DBConnection db;
	//connecting with the database
	static Connection c;
	//button to go back to the last GUI
	static JButton btBack;
	//book's photo
	JLabel lblcoverImage;
	static String title;


	/**
	 * Create the panel.
	 */
	public BookView() {
		//Set background image to "Welcome.jpg"
		super("/images/Welcome.jpg");
		//Add MouseMotionListener that sets the book rating 

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_panel);

		//Create JPanel panel_Fields 
		JPanel panel_Fields = new JPanel();
		panel_Fields.setBorder(null);
		panel_Fields.setOpaque(false);
		GridBagConstraints gbc_panel_Fields = new GridBagConstraints();
		gbc_panel_Fields.gridheight = 8;
		gbc_panel_Fields.insets = new Insets(0, 0, 0, 5);
		gbc_panel_Fields.fill = GridBagConstraints.BOTH;
		gbc_panel_Fields.gridx = 0;
		gbc_panel_Fields.gridy = 0;
		add(panel_Fields, gbc_panel_Fields);
		GridBagLayout gbl_panel_Fields = new GridBagLayout();
		gbl_panel_Fields.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_Fields.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_Fields.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Fields.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_Fields.setLayout(gbl_panel_Fields);

		//Add JButton btBack holding "Back" to panel_Fields
		btBack = new JButton("Back   ");
		btBack.setForeground(SystemColor.textHighlightText);
		btBack.setBackground(SystemColor.controlDkShadow);
		btBack.setIcon(new ImageIcon(BookView.class.getResource("/com/sun/javafx/scene/control/skin/caspian/fxvk-backspace-button.png")));
		btBack.setBorder(new BevelBorder(BevelBorder.RAISED, UIManager.getColor("Button.shadow"), UIManager.getColor("Button.shadow"), new Color(64, 64, 64), new Color(64, 64, 64)));
		btBack.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		GridBagConstraints gbc_btBack = new GridBagConstraints();
		gbc_btBack.anchor = GridBagConstraints.NORTHWEST;
		gbc_btBack.gridwidth = 2;
		gbc_btBack.insets = new Insets(0, 0, 5, 5);
		gbc_btBack.gridx = 0;
		gbc_btBack.gridy = 0;
		panel_Fields.add(btBack, gbc_btBack);

		//Add JLabel label holding "title:" to panel_Fields
		JLabel labelTitle = new JLabel("TITLE:");
		labelTitle.setForeground(new Color(102, 0, 0));
		labelTitle.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_labelTitle = new GridBagConstraints();
		gbc_labelTitle.anchor = GridBagConstraints.WEST;
		gbc_labelTitle.insets = new Insets(0, 200, 5, 5);
		gbc_labelTitle.gridx = 1;
		gbc_labelTitle.gridy = 0;
		panel_Fields.add(labelTitle, gbc_labelTitle);

		//Add textArea_Title to panel_Fields
		textArea_Title = new JTextArea();
		textArea_Title.setForeground(Color.DARK_GRAY);
		textArea_Title.setBorder(null);
		textArea_Title.setEditable(false);
		textArea_Title.setOpaque(false);
		textArea_Title.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textArea_Title.setColumns(10);
		GridBagConstraints gbc_textArea_Title = new GridBagConstraints();
		gbc_textArea_Title.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea_Title.anchor = GridBagConstraints.NORTH;
		gbc_textArea_Title.gridwidth = 13;
		gbc_textArea_Title.insets = new Insets(0, 200, 5, 0);
		gbc_textArea_Title.gridx = 1;
		gbc_textArea_Title.gridy = 1;
		panel_Fields.add(textArea_Title, gbc_textArea_Title);

		//Add JLabel genre holding "GENRE:" panel_Fields
		JLabel genre = new JLabel("GENRE:");
		genre.setForeground(new Color(102, 0, 0));
		genre.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_genre = new GridBagConstraints();
		gbc_genre.anchor = GridBagConstraints.WEST;
		gbc_genre.insets = new Insets(0, 200, 5, 5);
		gbc_genre.gridx = 1;
		gbc_genre.gridy = 2;
		panel_Fields.add(genre, gbc_genre);

		//Add JTextField textField_Genre  to panel_Fields
		textField_Genre = new JTextField();
		textField_Genre.setForeground(Color.DARK_GRAY);
		textField_Genre.setBorder(null);
		textField_Genre.setEditable(false);
		textField_Genre.setOpaque(false);
		textField_Genre.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_Genre.setColumns(10);
		GridBagConstraints gbc_textField_Genre = new GridBagConstraints();
		gbc_textField_Genre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Genre.anchor = GridBagConstraints.NORTH;
		gbc_textField_Genre.gridwidth = 6;
		gbc_textField_Genre.insets = new Insets(0, 200, 5, 5);
		gbc_textField_Genre.gridx = 1;
		gbc_textField_Genre.gridy = 3;
		panel_Fields.add(textField_Genre, gbc_textField_Genre);

		//Add JLabel isbn holding "ISBN:" to panel_Fields
		JLabel isbn = new JLabel("ISBN:");
		isbn.setForeground(new Color(102, 0, 0));
		isbn.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_isbn = new GridBagConstraints();
		gbc_isbn.anchor = GridBagConstraints.WEST;
		gbc_isbn.gridwidth = 3;
		gbc_isbn.insets = new Insets(0, 200, 5, 5);
		gbc_isbn.gridx = 1;
		gbc_isbn.gridy = 4;
		panel_Fields.add(isbn, gbc_isbn);

		//Add JTextField textField_ISBN to panel_Fields
		textField_ISBN = new JTextField();
		textField_ISBN.setForeground(Color.DARK_GRAY);
		textField_ISBN.setBorder(null);
		textField_ISBN.setEditable(false);
		textField_ISBN.setOpaque(false);
		textField_ISBN.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_ISBN.setColumns(10);
		GridBagConstraints gbc_textField_ISBN = new GridBagConstraints();
		gbc_textField_ISBN.anchor = GridBagConstraints.NORTH;
		gbc_textField_ISBN.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_ISBN.gridwidth = 6;
		gbc_textField_ISBN.insets = new Insets(0, 200, 5, 5);
		gbc_textField_ISBN.gridx = 1;
		gbc_textField_ISBN.gridy = 5;
		panel_Fields.add(textField_ISBN, gbc_textField_ISBN);

		//Add JLabel publishReviewLbl holding "PUBLISH/REVIEW DATE:" to panel_Fields
		JLabel publishReviewLbl = new JLabel("PUBLISH/REVIEW DATE:");
		publishReviewLbl.setForeground(new Color(102, 0, 0));
		publishReviewLbl.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_publishReviewLbl = new GridBagConstraints();
		gbc_publishReviewLbl.anchor = GridBagConstraints.WEST;
		gbc_publishReviewLbl.gridwidth = 2;
		gbc_publishReviewLbl.insets = new Insets(0, 200, 5, 5);
		gbc_publishReviewLbl.gridx = 1;
		gbc_publishReviewLbl.gridy = 6;
		panel_Fields.add(publishReviewLbl, gbc_publishReviewLbl);

		//Add JTextField textField_PublishReviewDate to panel_Fields
		textField_PublishReviewDate = new JTextField();
		textField_PublishReviewDate.setForeground(Color.DARK_GRAY);
		textField_PublishReviewDate.setBorder(null);
		textField_PublishReviewDate.setEditable(false);
		textField_PublishReviewDate.setOpaque(false);
		textField_PublishReviewDate.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_PublishReviewDate.setColumns(10);
		GridBagConstraints gbc_textField_PublishReviewDate = new GridBagConstraints();
		gbc_textField_PublishReviewDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_PublishReviewDate.anchor = GridBagConstraints.NORTH;
		gbc_textField_PublishReviewDate.gridwidth = 6;
		gbc_textField_PublishReviewDate.insets = new Insets(0, 200, 5, 5);
		gbc_textField_PublishReviewDate.gridx = 1;
		gbc_textField_PublishReviewDate.gridy = 7;
		panel_Fields.add(textField_PublishReviewDate, gbc_textField_PublishReviewDate);

		//Add JLabel pagesLbl holding "PAGES:" to panel_Fields
		JLabel pagesLbl = new JLabel("PAGES:");
		pagesLbl.setForeground(new Color(102, 0, 0));
		pagesLbl.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_pagesLbl = new GridBagConstraints();
		gbc_pagesLbl.anchor = GridBagConstraints.WEST;
		gbc_pagesLbl.gridwidth = 3;
		gbc_pagesLbl.insets = new Insets(0, 200, 5, 5);
		gbc_pagesLbl.gridx = 1;
		gbc_pagesLbl.gridy = 8;
		panel_Fields.add(pagesLbl, gbc_pagesLbl);

		//Add JTextField textField_Pages to panel_Fields
		textField_Pages = new JTextField();
		textField_Pages.setForeground(Color.DARK_GRAY);
		textField_Pages.setBorder(null);
		textField_Pages.setEditable(false);
		textField_Pages.setOpaque(false);
		textField_Pages.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_Pages.setColumns(10);
		GridBagConstraints gbc_textField_Pages = new GridBagConstraints();
		gbc_textField_Pages.anchor = GridBagConstraints.NORTH;
		gbc_textField_Pages.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Pages.gridwidth = 6;
		gbc_textField_Pages.insets = new Insets(0, 200, 5, 5);
		gbc_textField_Pages.gridx = 1;
		gbc_textField_Pages.gridy = 9;
		panel_Fields.add(textField_Pages, gbc_textField_Pages);

		//Add JLabel lblLabel_5 holding "PRICE:" to panel_Fields
		JLabel lblLabel_5 = new JLabel("PRICE:");
		lblLabel_5.setForeground(new Color(102, 0, 0));
		lblLabel_5.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_lblLabel_5 = new GridBagConstraints();
		gbc_lblLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblLabel_5.insets = new Insets(0, 200, 5, 5);
		gbc_lblLabel_5.gridx = 1;
		gbc_lblLabel_5.gridy = 10;
		panel_Fields.add(lblLabel_5, gbc_lblLabel_5);

		//Add JTextField textField_Price to panel_Fields
		textField_Price = new JTextField();
		textField_Price.setForeground(Color.DARK_GRAY);
		textField_Price.setBorder(null);
		textField_Price.setEditable(false);
		textField_Price.setOpaque(false);
		textField_Price.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_Price.setColumns(10);
		GridBagConstraints gbc_textField_Price = new GridBagConstraints();
		gbc_textField_Price.anchor = GridBagConstraints.NORTH;
		gbc_textField_Price.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Price.gridwidth = 6;
		gbc_textField_Price.insets = new Insets(0, 200, 5, 5);
		gbc_textField_Price.gridx = 1;
		gbc_textField_Price.gridy = 11;
		panel_Fields.add(textField_Price, gbc_textField_Price);

		//Add JLabel lblPricesek holding "REVIEW ON AMAZON:" to panel_Fields
		JLabel lblReviewOnAmazon = new JLabel("REVIEW ON AMAZON:");
		lblReviewOnAmazon.setForeground(new Color(102, 0, 0));
		lblReviewOnAmazon.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_lblReviewOnAmazon = new GridBagConstraints();
		gbc_lblReviewOnAmazon.anchor = GridBagConstraints.WEST;
		gbc_lblReviewOnAmazon.insets = new Insets(0, 200, 5, 5);
		gbc_lblReviewOnAmazon.gridx = 1;
		gbc_lblReviewOnAmazon.gridy = 12;
		panel_Fields.add(lblReviewOnAmazon, gbc_lblReviewOnAmazon);

		//Add JTextField textField_ReviewOnAmazon to panel_Fields
		textField_ReviewOnAmazon = new JTextField();
		textField_ReviewOnAmazon.setForeground(Color.DARK_GRAY);
		textField_ReviewOnAmazon.setBorder(null);
		textField_ReviewOnAmazon.setEditable(false);
		textField_ReviewOnAmazon.setOpaque(false);
		textField_ReviewOnAmazon.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_ReviewOnAmazon.setColumns(10);
		GridBagConstraints gbc_textField_ReviewOnAmazon = new GridBagConstraints();
		gbc_textField_ReviewOnAmazon.anchor = GridBagConstraints.NORTH;
		gbc_textField_ReviewOnAmazon.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_ReviewOnAmazon.gridwidth = 6;
		gbc_textField_ReviewOnAmazon.insets = new Insets(0, 200, 0, 5);
		gbc_textField_ReviewOnAmazon.gridx = 1;
		gbc_textField_ReviewOnAmazon.gridy = 13;
		panel_Fields.add(textField_ReviewOnAmazon, gbc_textField_ReviewOnAmazon);

		//Add JLabel label_ReviewPic holding the logo of the Book store
		label_ReviewPic = new JLabel("");
		label_ReviewPic.setIcon(new ImageIcon(getClass().getResource("/images/star.png")));
		label_ReviewPic.setBorder(null);
		GridBagConstraints gbc_label_ReviewPic = new GridBagConstraints();
		gbc_label_ReviewPic.anchor = GridBagConstraints.WEST;
		gbc_label_ReviewPic.fill = GridBagConstraints.VERTICAL;
		gbc_label_ReviewPic.insets = new Insets(0, 275, 0, 0);
		gbc_label_ReviewPic.gridwidth = 6;
		gbc_label_ReviewPic.gridx = 1;
		gbc_label_ReviewPic.gridy = 13;
		panel_Fields.add(label_ReviewPic, gbc_label_ReviewPic);

		
		//Add JLabel lblDescription holding "BOOK DESCRIPTION" to panel_Fields
		JLabel lblDescription = new JLabel("BOOK DESCRIPTION");
		lblDescription.setForeground(new Color(102, 0, 0));
		lblDescription.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.WEST;
		gbc_lblDescription.insets = new Insets(0, 200, 0, 0);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 15;
		panel_Fields.add(lblDescription, gbc_lblDescription);
		
		//Add textArea containing the description of the book
		textArea_Description = new JTextArea();
		textArea_Description.setForeground(Color.BLACK);
		textArea_Description.setBorder(null);
		textArea_Description.setEditable(false);
		textArea_Description.setOpaque(false);
		textArea_Description.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		textArea_Description.setColumns(10);
		GridBagConstraints gbc_textField_Description = new GridBagConstraints();
		gbc_textField_Description.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Description.anchor = GridBagConstraints.NORTH;
		gbc_textField_Description.insets = new Insets(0, 200, 0, 0);
		gbc_textField_Description.gridx = 1;
		gbc_textField_Description.gridy = 16;
		panel_Fields.add(textArea_Description, gbc_textField_Description);

		//Add JPanel panel_Pic
		JPanel panel_Pic = new JPanel();
		panel_Pic.setOpaque(false);
		GridBagConstraints gbc_panel_Pic = new GridBagConstraints();
		gbc_panel_Pic.insets = new Insets(0, 800, 5, 0);
		gbc_panel_Pic.fill = GridBagConstraints.BOTH;
		gbc_panel_Pic.gridx = 0;
		gbc_panel_Pic.gridy = 3;
		add(panel_Pic, gbc_panel_Pic);
		GridBagLayout gbl_panel_Pic = new GridBagLayout();
		gbl_panel_Pic.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_Pic.rowHeights = new int[]{0, 0, 0};
		gbl_panel_Pic.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Pic.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_Pic.setLayout(gbl_panel_Pic);

		//Add JPanel lblcoverImage to panel_Pic
		lblcoverImage = new JLabel("");
		lblcoverImage.setBorder(null);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_Pic.add(lblcoverImage, gbc_lblNewLabel);
	}



	/**
	 * @param 
	 */
	//Setter method that sets the book's attribute 'title' to correct
	//one for the book selected
	public void settextArea_Title(String title) {
		this.textArea_Title.setText(title);;
	}

	/**
	 * @param 
	 */
	//Setter method that sets the book's attribute 'genre' to correct
	//one for the book selected
	public void settextField_Genre(String genre) {
		this.textField_Genre.setText(genre);
	}

	/**
	 * @param 
	 */
	//Setter method that sets the book's attribute 'ISBN' to correct
	//one for the book selected
	public void settextField_ISBN(String ISBN) {
		this.textField_ISBN.setText(ISBN);;
	}

	/**
	 * @param
	 */
	//Setter method that sets the book's attribute 'review/publishDate' to correct
	//one for the book selected
	public void settextField_PublishReviewDate(String dates) {
		this.textField_PublishReviewDate.setText(dates);
	}

	/**
	 * @param 
	 */
	//Setter method that sets the book's attribute 'pages' to correct
	//one for the book selected
	public void settextField_Pages(String continent) {
		this.textField_Pages.setText(continent);
	}

	/**
	 * @param 
	 */
	//Setter method that sets the book's attribute 'revieOnAmazon' to correct
	//one for the book selected
	public void settextField_ReviewOnAmazon(String reviewOnAmazon) {
		this.textField_ReviewOnAmazon.setText(reviewOnAmazon);
	}

	/**
	 * @param
	 */
	//Setter method that sets the book's attribute 'price' to correct
	//one for the book selected
	public void setTextField_Price(String price) {
		this.textField_Price.setText(price);
	}

	/**
	 * @param
	 */
	//Setter method that sets the book's attribute 'description' to correct
	//one for the book selected
	public void setTextArea_Description(String description) {
		this.textArea_Description.setText(description);
	}
	/**
	 * @param lblcoverImage the lblcoverImage to set
	 */
	//Setter method that sets the book's image to correct
	//one for the book selected
	public void setlblcoverImage(byte[] photo) {
		this.lblcoverImage.setIcon(new ImageIcon(photo));
	}

}
