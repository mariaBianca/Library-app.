package library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import com.mysql.jdbc.Connection;

/**
 * WelcomeScreen class responsible for the welcome ("home") screen interface,
 * where the program starts.
 * 
 * @author Maria-Bianca Cindroi
 * 
 */

@SuppressWarnings("serial")
public class WelcomeScreen extends JPanel {
	//JPopUpMenu holding...
	JPopupMenu popupMenu;
	DBConnection db;
	Connection c ;
	JButton lblClickHereTo;
	BookList list = null;


	/**
	 * Create the frame. The frame holds other elements of the interface.
	 */
	public WelcomeScreen(JFrame frame, DBConnection db, Connection c) {
		this.db = db;
		this.c = c;
		this.list = new BookList("", db, c);

		//Add ImagePanel panel holding image "Welcome.jpg" to frame
		String path = "/images/Welcome.jpg";
		ImagePanel panel = new ImagePanel(path);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);


		//Add JPanel panel_2 to ImagePanel panel
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 17;
		gbc_panel_2.insets = new Insets(0, 0, 130, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		//A Top3 list to see the best 3 books rated on Amazon
		Top3 top = new Top3(db, c);
		top.setTop3();
		int[] top3 = top.getTop();

		//JButton btnnumber1 for the top3 list
		JButton btnnumber1 = new JButton("1." + list.title(top3[0]));
		//Add ActionListener to get book information from database
		try {
			btnnumber1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					list.viewBook(top3[0]);
					frame.getContentPane().remove(panel);
					new ViewOfTop3(frame, db, c, list.getbookid(), list.getTitle(), list.getAuthor(),
							list.getISBN(), list.getGenre(),list.getPublishDate(),list.getReviewDate(),
							list.getPrice(),list.getReviewOnAmazon(),list.getPages(),top3[0],
							list.getcoverImage(),list.getDescription(), "top3");
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
				}
			});
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showConfirmDialog(null, "Something went wrong!");
		}
		//End of ActionListener

		//Add btnnumber1 to panel_2
		btnnumber1.setForeground(new Color(255, 255, 255));
		btnnumber1.setBackground(Color.DARK_GRAY);
		btnnumber1.setMaximumSize(new Dimension(60, 30));
		btnnumber1.setMinimumSize(new Dimension(60, 25));
		btnnumber1.setOpaque(false);
		btnnumber1.setBorder(null);
		btnnumber1.setPreferredSize(new Dimension(60, 25));
		btnnumber1.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_btnnumber1 = new GridBagConstraints();
		gbc_btnnumber1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnnumber1.insets = new Insets(0, 0, 5, 0);
		gbc_btnnumber1.gridx = 0;
		gbc_btnnumber1.gridy = 1;
		panel_2.add(btnnumber1, gbc_btnnumber1);

		//JButton btnnumber2 for the top3 list
		JButton btnnumber2 = new JButton("2."+list.title(top3[1]));
		//Add ActionListener to get book information from database
		try {
			btnnumber2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					list.viewBook(top3[1]);
					frame.getContentPane().remove(panel);
					new ViewOfTop3(frame, db, c, list.getbookid(), list.getTitle(), list.getAuthor(),
							list.getISBN(), list.getGenre(),list.getPublishDate(),list.getReviewDate(),
							list.getPrice(),list.getReviewOnAmazon(),list.getPages(),top3[0],
							list.getcoverImage(),list.getDescription(), "top3");
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
				}
			});
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showConfirmDialog(null, "Something went wrong!");
		}
		//End of ActionListener

		//Add btnnumber2 to panel_2
		btnnumber2.setForeground(new Color(255, 255, 255));
		btnnumber2.setBackground(Color.DARK_GRAY);
		btnnumber2.setOpaque(false);
		btnnumber2.setBorder(null);
		btnnumber2.setMinimumSize(new Dimension(60, 30));
		btnnumber2.setMaximumSize(new Dimension(60, 25));
		btnnumber2.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_btnnumber2 = new GridBagConstraints();
		gbc_btnnumber2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnnumber2.insets = new Insets(0, 0, 5, 0);
		gbc_btnnumber2.gridx = 0;
		gbc_btnnumber2.gridy = 3;
		panel_2.add(btnnumber2, gbc_btnnumber2);

		//JButton btnnumber3 for the top3 list
		JButton btnnumber3 = new JButton("3." + list.title(top3[2]));
		//Add ActionListener to get book information from database
		try {
			btnnumber3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					list.viewBook(top3[2]);
					frame.getContentPane().remove(panel);
					new ViewOfTop3(frame, db, c, list.getbookid(), list.getTitle(), list.getAuthor(),
							list.getISBN(), list.getGenre(),list.getPublishDate(),list.getReviewDate(),
							list.getPrice(),list.getReviewOnAmazon(),list.getPages(),top3[0],
							list.getcoverImage(),list.getDescription(), "top3");
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
				}
			});
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showConfirmDialog(null, "Something went wrong!");
		}
		//End of ActionListener

		//Add btnnumber3 to panel_2
		btnnumber3.setForeground(new Color(255, 255, 255));
		btnnumber3.setBackground(Color.DARK_GRAY);
		btnnumber3.setOpaque(false);
		btnnumber3.setBorder(null);
		btnnumber3.setMinimumSize(new Dimension(60, 25));
		btnnumber3.setMaximumSize(new Dimension(60, 25));
		btnnumber3.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_btnnumber3 = new GridBagConstraints();
		gbc_btnnumber3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnnumber3.insets = new Insets(0, 0, 5, 0);
		gbc_btnnumber3.gridx = 0;
		gbc_btnnumber3.gridy = 5;
		panel_2.add(btnnumber3, gbc_btnnumber3);


		//JButton btnTakeMeTo that directs the user to Amazon.com/Books
		JButton btnTakeMeTo = new JButton("Want to see more books online?");
		btnTakeMeTo.setContentAreaFilled(false);
		//Add ActionListener
		btnTakeMeTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String url = "http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dstripbooks&field-keywords=";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		//End of ActionListener

		//Add JButton btnTakeMeTo to ImagePanel panel
		btnTakeMeTo.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 30));
		GridBagConstraints gbc_btnTakeMeTo = new GridBagConstraints();
		btnTakeMeTo.setBorder(null);
		btnTakeMeTo.setToolTipText("Press here to go to Amazon.com");
		gbc_btnTakeMeTo.gridwidth = 16;
		gbc_btnTakeMeTo.anchor = GridBagConstraints.NORTH;
		gbc_btnTakeMeTo.insets = new Insets(0, 0, 5, 600);
		gbc_btnTakeMeTo.gridx = 19;
		gbc_btnTakeMeTo.gridy = 0;
		panel.add(btnTakeMeTo, gbc_btnTakeMeTo);

		GridBagConstraints gbc_btnNewButton1 = new GridBagConstraints();
		gbc_btnNewButton1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton1.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton1.weighty = 1.0;
		gbc_btnNewButton1.weightx = 1.0;
		gbc_btnNewButton1.gridx = 43;
		gbc_btnNewButton1.gridy = 0;
		//Add JButton btnLogin to ImagePanel panel
		gbc_btnNewButton1.weighty = 1.0;
		gbc_btnNewButton1.weightx = 1.0;

		//Add JLabel lblTop containing title for 
		//top 5 list to panel_2
		JLabel lblTop = new JLabel("Top 3 most Rated books on Amazon!");
		lblTop.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 25));
		GridBagConstraints gbc_lblTop = new GridBagConstraints();
		gbc_lblTop.insets = new Insets(0, 0, 5, 0);
		gbc_lblTop.gridx = 0;
		gbc_lblTop.gridy = 0;
		panel_2.add(lblTop, gbc_lblTop);

		//Add JPanel panel_1 to ImagePanel panel
		JPanel panel_1 = new JPanel();
		//Add MouseMotionListener

		panel_1.setOpaque(false);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_panel_1.gridwidth = 26;
		gbc_panel_1.insets = new Insets(0, 5, 200, 400);
		gbc_panel_1.gridx = 17;
		gbc_panel_1.gridy = 4;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);


		//Button to access the filtering system of the program
		//Launches a new GUI on panel_1
		JButton btnFilters = new JButton("Filters");
		//Add ActionListener
		btnFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(panel);
				new FilterGui(frame, db, c);
			}
		});
		//End of ActionListener

		btnFilters.setForeground(Color.DARK_GRAY);
		btnFilters.setContentAreaFilled(false);
		btnFilters.setMaximumSize(new Dimension(80, 25));
		btnFilters.setFont(new Font("Algerian", Font.BOLD, 43));
		GridBagConstraints gbc_btnFilters = new GridBagConstraints();
		gbc_btnFilters.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFilters.insets = new Insets(100, 0, 300, 200);
		gbc_btnFilters.gridx = 0;
		gbc_btnFilters.gridy = 2;
		gbc_btnnumber1.weighty = 1.0;
		gbc_btnnumber1.weightx = 1.0;
		panel_1.add(btnFilters, gbc_btnFilters);

		//Add JButton btnBookList holding BookList" to panel_1
		JButton btnBookList = new JButton("Book List");
		btnBookList.setForeground(Color.DARK_GRAY);
		btnBookList.setContentAreaFilled(false);
		//Add ActionListener
		btnBookList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panel_1, "So sorry. For the moment, the \"Book List\" is not available"
						+ ", but you can try out the \"Filters\" section. It's fun! ");
				//uncomment this once, you done with the new Book List
				//frame.getContentPane().remove(panel);

				//Launch new GUI
				//Add the "Book List" GUI here!

				//uncomment these after u added a new GUI
				//frame.getContentPane().revalidate();
				//frame.getContentPane().repaint();
			}});
		//End of ActionListener

		btnBookList.setFont(new Font("Algerian", Font.BOLD, 43));
		GridBagConstraints gbc_btnBookList = new GridBagConstraints();
		gbc_btnBookList.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBookList.insets = new Insets(100, 5, 300, 300);
		gbc_btnnumber1.weighty = 1.0;
		gbc_btnnumber1.weightx = 1.0;
		gbc_btnBookList.gridx = 1;
		gbc_btnBookList.gridy = 2;
		panel_1.add(btnBookList, gbc_btnBookList);


		//JLabel iconImage
		JLabel iconDown = new JLabel("");
		//Set animated gif
		iconDown.setIcon(new ImageIcon(getClass().getResource("/images/Icon.gif")));
		GridBagConstraints gbc_iconDown = new GridBagConstraints();
		gbc_iconDown.gridwidth = 19;
		gbc_iconDown.insets = new Insets(0, 0, 5, 700);
		gbc_iconDown.gridx = 13;
		gbc_iconDown.gridy = 9;
		panel.add(iconDown, gbc_iconDown);

		frame.revalidate();
		frame.repaint();

	}
	public  void CloseConnection(){
		try {
			//Close connection
			c.close();
			System.err.println("Connection closed successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
//End of WelcomeScreen class
