package library;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import com.mysql.jdbc.Connection;

/**
 *ViewOfTop3 is a class that puts together the design for the books that have the 
 *top 3 rating from on Amazon.com
 * 
 * @author Maria-Bianca Cindroi
 * 
 */
@SuppressWarnings("serial")
public class ViewOfTop3 extends JPanel {
	//Connection with the database
	DBConnection db;
	Connection c;
	//JTextFields and Areas for Books' attributes
	private JTextArea textArea_Title;
	private JTextField textField_Genre;
	private JTextField textField_ISBN;
	private JTextField textField_PublishDate;
	private JTextField textField_Pages;
	private JTextField textField_Price;
	private JTextField textField_ReviewOnAmazon;
	private JTextArea textArea_Description;
	//JLabel holding image
	JLabel label_LogoPic;
	JLabel label_ReviewPic;
	//the list of books
	BookList book;
	String author;
	String title;
	String genre;
	String ISBN;
	int publishDate;
	int reviewDate;
	double price;
	double reviewOnAmazon;
	int bookid;
	int pages;
	String description;
	byte[] coverImage;
	String lastGUI;

	/**
	 * Create the panel.
	 */
	public ViewOfTop3(JFrame frame, DBConnection db, Connection c, int bookid, 
			String title, String author, String ISBN, String genre, int publishDate, 
			int reviewDate, double price, double reviewOnAmazon, int pages, int top3,
			byte[] coverImage,String description, String lastGUI) {

		this.db = db;
		this.c = c;
		this.title = title;
		this.ISBN = ISBN;
		this.genre = genre;
		this.publishDate = publishDate;
		this.reviewDate = reviewDate;
		this.price = price;
		this.reviewOnAmazon = reviewOnAmazon;
		this.pages = pages;
		this.bookid = bookid;
		this.coverImage = coverImage;
		this.lastGUI = lastGUI;
		this.author = author;
		this.description = description;
		book = new BookList(title, db, c);

		//ImagePanel panel holding the image "Welcome.jpg"
		ImagePanel panel = new ImagePanel("/images/Welcome.jpg");

		//Add ImagePanel panel to frame
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		//Add JPanel panel_Fields to panel
		JPanel panel_Fields = new JPanel();
		panel_Fields.setBorder(null);
		panel_Fields.setOpaque(false);
		GridBagConstraints gbc_panel_Fields = new GridBagConstraints();
		gbc_panel_Fields.gridheight = 8;
		gbc_panel_Fields.insets = new Insets(0, 0, 0, 5);
		gbc_panel_Fields.fill = GridBagConstraints.BOTH;
		gbc_panel_Fields.gridx = 0;
		gbc_panel_Fields.gridy = 0;
		panel.add(panel_Fields, gbc_panel_Fields);
		GridBagLayout gbl_panel_Fields = new GridBagLayout();
		gbl_panel_Fields.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_Fields.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_Fields.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Fields.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_Fields.setLayout(gbl_panel_Fields);

		//Add JButton btBack, that takes the user back to Filters or 
		//Book List page
		JButton btBack = new JButton("Back   ");
		btBack.setForeground(SystemColor.textHighlightText);
		btBack.setBackground(SystemColor.controlDkShadow);
		btBack.setIcon(new ImageIcon(ViewOfTop3.class.getResource("/com/sun/javafx/scene/control/skin/caspian/fxvk-backspace-button.png")));
		btBack.setBorder(new BevelBorder(BevelBorder.RAISED, UIManager.getColor("Button.shadow"), UIManager.getColor("Button.shadow"), new Color(64, 64, 64), new Color(64, 64, 64)));
		//Add ActionListener that takes user back to the part
		//of GUI from where the user launched the book list
		btBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					frame.getContentPane().remove(panel);
					new WelcomeScreen(frame, db, c);
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
			}
		});
		//Ends of ActionListener

		btBack.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		GridBagConstraints gbc_btBack = new GridBagConstraints();
		gbc_btBack.anchor = GridBagConstraints.NORTHWEST;
		gbc_btBack.gridwidth = 2;
		gbc_btBack.insets = new Insets(0, 0, 5, 5);
		gbc_btBack.gridx = 0;
		gbc_btBack.gridy = 0;
		panel_Fields.add(btBack, gbc_btBack);


		//Add JLabel label holding "TITLE:" to panel_Fields
		JLabel label = new JLabel("TITLE:");
		label.setForeground(new Color(102, 0, 0));
		label.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 200, 5, 0);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		panel_Fields.add(label, gbc_label);

		textArea_Title = new JTextArea();
		textArea_Title.setForeground(Color.BLACK);
		textArea_Title.setBorder(null);
		textArea_Title.setEditable(false);
		textArea_Title.setOpaque(false);
		textArea_Title.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textArea_Title.setColumns(10);
		GridBagConstraints gbc_textField_title = new GridBagConstraints();
		gbc_textField_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_title.anchor = GridBagConstraints.NORTH;
		gbc_textField_title.gridwidth = 13;
		gbc_textField_title.insets = new Insets(0, 200, 0, 0);
		gbc_textField_title.gridx = 1;
		gbc_textField_title.gridy = 1;
		panel_Fields.add(textArea_Title, gbc_textField_title);
		if (title.length()>39){
			textArea_Title.setText(title.substring(0,39) + "\n" + title.substring(39)+
					"\n by " + author);
		}
		else{
			textArea_Title.setText(title + "\n by " + author);
		}

		//Add JLabel label_1 holding "GENRE:" to panel_Fields
		JLabel label_1 = new JLabel("GENRE:");
		label_1.setForeground(new Color(102, 0, 0));
		label_1.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 200, 0, 0);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 2;
		panel_Fields.add(label_1, gbc_label_1);

		textField_Genre = new JTextField();
		textField_Genre.setForeground(Color.BLACK);
		textField_Genre.setBorder(null);
		textField_Genre.setEditable(false);
		textField_Genre.setOpaque(false);
		textField_Genre.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_Genre.setColumns(10);
		GridBagConstraints gbc_textField_Genre = new GridBagConstraints();
		gbc_textField_Genre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Genre.anchor = GridBagConstraints.NORTH;
		gbc_textField_Genre.gridwidth = 6;
		gbc_textField_Genre.insets = new Insets(0, 200, 0, 0);
		gbc_textField_Genre.gridx = 1;
		gbc_textField_Genre.gridy = 3;
		panel_Fields.add(textField_Genre, gbc_textField_Genre);
		textField_Genre.setText(genre);

		//Add JLabel label_2 holding "ISBN:" to panel_Fields
		JLabel label_2 = new JLabel("ISBN:");
		label_2.setForeground(new Color(102, 0, 0));
		label_2.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.gridwidth = 3;
		gbc_label_2.insets = new Insets(0, 200, 0, 0);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 4;
		panel_Fields.add(label_2, gbc_label_2);

		textField_ISBN = new JTextField();
		textField_ISBN.setForeground(Color.BLACK);
		textField_ISBN.setBorder(null);
		textField_ISBN.setEditable(false);
		textField_ISBN.setOpaque(false);
		textField_ISBN.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_ISBN.setColumns(10);
		GridBagConstraints gbc_textField_ISBN = new GridBagConstraints();
		gbc_textField_ISBN.anchor = GridBagConstraints.NORTH;
		gbc_textField_ISBN.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_ISBN.gridwidth = 6;
		gbc_textField_ISBN.insets = new Insets(0, 200, 0, 0);
		gbc_textField_ISBN.gridx = 1;
		gbc_textField_ISBN.gridy = 5;
		panel_Fields.add(textField_ISBN, gbc_textField_ISBN);
		textField_ISBN.setText(ISBN);

		//Add JLabel label_3 holding "PUBLISH/REVIEW:" to panel_Fields
		JLabel publishReviewDate = new JLabel("PUBLISH/REVIEW DATES:");
		publishReviewDate.setForeground(new Color(102, 0, 0));
		publishReviewDate.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_publishReviewDate = new GridBagConstraints();
		gbc_publishReviewDate.anchor = GridBagConstraints.WEST;
		gbc_publishReviewDate.gridwidth = 2;
		gbc_publishReviewDate.insets = new Insets(0, 200, 0, 0);
		gbc_publishReviewDate.gridx = 1;
		gbc_publishReviewDate.gridy = 6;
		panel_Fields.add(publishReviewDate, gbc_publishReviewDate);

		textField_PublishDate = new JTextField();
		textField_PublishDate.setForeground(Color.BLACK);
		textField_PublishDate.setBorder(null);
		textField_PublishDate.setEditable(false);
		textField_PublishDate.setOpaque(false);
		textField_PublishDate.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_PublishDate.setColumns(10);
		GridBagConstraints gbc_textField_PublishDate = new GridBagConstraints();
		gbc_textField_PublishDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_PublishDate.anchor = GridBagConstraints.NORTH;
		gbc_textField_PublishDate.gridwidth = 6;
		gbc_textField_PublishDate.insets = new Insets(0, 200, 0, 0);
		gbc_textField_PublishDate.gridx = 1;
		gbc_textField_PublishDate.gridy = 7;
		panel_Fields.add(textField_PublishDate, gbc_textField_PublishDate);
		if (reviewDate != 0){
			textField_PublishDate.setText(Integer.toString(publishDate) + "/"+ Integer.toString(reviewDate));
		}
		else
			if (reviewDate == 0){
				textField_PublishDate.setText(Integer.toString(publishDate));
			}

		//Add JLabel label_4 holding "PAGES:" to panel_Fields
		JLabel lblPages = new JLabel();
		lblPages.setBackground(new Color(102, 0, 0));
		lblPages.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_lblPages = new GridBagConstraints();
		gbc_lblPages.anchor = GridBagConstraints.WEST;
		gbc_lblPages.gridwidth = 3;
		gbc_lblPages.insets = new Insets(0, 200, 0, 0);
		gbc_lblPages.gridx = 1;
		gbc_lblPages.gridy = 8;
		panel_Fields.add(lblPages, gbc_lblPages);

		textField_Pages = new JTextField();
		textField_Pages.setForeground(Color.BLACK);
		textField_Pages.setBorder(null);
		textField_Pages.setEditable(false);
		textField_Pages.setOpaque(false);
		textField_Pages.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_Pages.setColumns(10);
		GridBagConstraints gbc_ = new GridBagConstraints();
		gbc_.anchor = GridBagConstraints.NORTH;
		gbc_.fill = GridBagConstraints.HORIZONTAL;
		gbc_.gridwidth = 6;
		gbc_.insets = new Insets(0, 200, 0, 0);
		gbc_.gridx = 1;
		gbc_.gridy = 9;
		panel_Fields.add(textField_Pages, gbc_);
		textField_Pages.setText(Integer.toString(pages));

		//Add JLabel label_5 holding "PRICE:" to panel_Fields
		JLabel label_5 = new JLabel("PRICE:");
		label_5.setForeground(new Color(102, 0, 0));
		label_5.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.WEST;
		gbc_label_5.insets = new Insets(0, 200, 0, 0);
		gbc_label_5.gridx = 1;
		gbc_label_5.gridy = 10;
		panel_Fields.add(label_5, gbc_label_5);

		textField_Price = new JTextField();
		textField_Price.setForeground(Color.BLACK);
		textField_Price.setBorder(null);
		textField_Price.setEditable(false);
		textField_Price.setOpaque(false);
		textField_Price.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_Price.setColumns(10);
		GridBagConstraints gbc_textField_Price = new GridBagConstraints();
		gbc_textField_Price.anchor = GridBagConstraints.NORTH;
		gbc_textField_Price.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Price.gridwidth = 6;
		gbc_textField_Price.insets = new Insets(0, 200, 0,0);
		gbc_textField_Price.gridx = 1;
		gbc_textField_Price.gridy = 11;
		panel_Fields.add(textField_Price, gbc_textField_Price);
		textField_Price.setText(Double.toString(price)+ ":-");

		//Add JLabel lblReviewAmazon holding "REVIEW ON AMAZON" to panel_Fields
		JLabel lblReviewAmazon = new JLabel("REVIEW ON AMAZON");
		lblReviewAmazon.setForeground(new Color(102, 0, 0));
		lblReviewAmazon.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		GridBagConstraints gbc_lblReviewAmazon = new GridBagConstraints();
		gbc_lblReviewAmazon.anchor = GridBagConstraints.WEST;
		gbc_lblReviewAmazon.insets = new Insets(0, 200, 0, 0);
		gbc_lblReviewAmazon.gridx = 1;
		gbc_lblReviewAmazon.gridy = 12;
		panel_Fields.add(lblReviewAmazon, gbc_lblReviewAmazon);

		textField_ReviewOnAmazon = new JTextField();
		textField_ReviewOnAmazon.setForeground(Color.BLACK);
		textField_ReviewOnAmazon.setBorder(null);
		textField_ReviewOnAmazon.setEditable(false);
		textField_ReviewOnAmazon.setOpaque(false);
		textField_ReviewOnAmazon.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
		textField_ReviewOnAmazon.setColumns(10);
		GridBagConstraints gbc_textField_Review = new GridBagConstraints();
		gbc_textField_Review.anchor = GridBagConstraints.NORTH;
		gbc_textField_Review.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Review.gridwidth = 6;
		gbc_textField_Review.insets = new Insets(0, 200, 0, 0);
		gbc_textField_Review.gridx = 1;
		gbc_textField_Review.gridy = 13;
		panel_Fields.add(textField_ReviewOnAmazon, gbc_textField_Review);
		textField_ReviewOnAmazon.setText(Double.toString(reviewOnAmazon) + " / 5");


		//Add JLabel label_ReviewPic holding the review star from Amazon.com
		label_ReviewPic = new JLabel("");
		label_ReviewPic.setIcon(new ImageIcon(getClass().getResource("/images/star.png")));
		label_ReviewPic.setBorder(null);
		GridBagConstraints gbc_label_ReviewPic = new GridBagConstraints();
		gbc_label_ReviewPic.anchor = GridBagConstraints.WEST;
		gbc_label_ReviewPic.fill = GridBagConstraints.VERTICAL;
		gbc_label_ReviewPic.insets = new Insets(0, 295, 0, 0);
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
		panel_Fields.add(textArea_Description, gbc_textField_Description);
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
		textArea_Description.setText(string0 + "\n"+string + "\n" + string1 + "\n" + string2 +"\n"+ string3+ "\n" +description);

		//Add JPanel panel_Pic to panel
		JPanel panel_Pic = new JPanel();
		panel_Pic.setOpaque(false);
		GridBagConstraints gbc_panel_Pic = new GridBagConstraints();
		gbc_panel_Pic.insets = new Insets(0, 0, 5, 200);
		gbc_panel_Pic.fill = GridBagConstraints.CENTER;
		gbc_panel_Pic.gridx = 0;
		gbc_panel_Pic.gridy = 3;
		panel.add(panel_Pic, gbc_panel_Pic);
		GridBagLayout gbl_panel_Pic = new GridBagLayout();
		gbl_panel_Pic.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_Pic.rowHeights = new int[]{0, 0, 0};
		gbl_panel_Pic.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Pic.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_Pic.setToolTipText(description);
		panel_Pic.setLayout(gbl_panel_Pic);

		//Add JLabel lblCoverImage to panel_Pic holding 
		//image of selected book
		JLabel lblCoverImage = new JLabel("");
		lblCoverImage.setToolTipText(description);
		lblCoverImage.setBorder(null);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_Pic.add(lblCoverImage, gbc_lblNewLabel);
		lblCoverImage.setIcon(new ImageIcon(coverImage));

	}

}//end of BookList


