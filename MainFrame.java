package library;

import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Dimension;

/**
 * Class containing the main frame for the application
 * @author Maria-Bianca Cindroi
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public static JFrame frame;
	WelcomeScreen welcome;

	// connect to db
	DBConnection db;
	Connection c;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public MainFrame() {
		setMinimumSize(new Dimension(1800, 900));
		this.frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1126, 699);
		setTitle("El Olam Book Store");

		// setting the program's logo 
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/Icon.png"));
		frame.setIconImage(icon.getImage());
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		// staring the panel with the loading image
		// @see Open
		new OpeningFrame(frame);

		// Setting the frame big
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		// loading the db drivers
		db = new DBConnection();

		// connecting to the db
		c = db.getConn();
		// removing the panel open
		frame.remove(OpeningFrame.panel);

		// adding the welcome screen
		// @see WelcomeScreen
		this.welcome = new WelcomeScreen(frame, db, (com.mysql.jdbc.Connection) c);

		// Confirmation dialog for exit
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				confirmAndExit();
			}

			/**
			 * Method to show a dialog box asking if the user wants to close the
			 * application
			 */
			private void confirmAndExit() {
				if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?", "Please confirm",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						welcome.CloseConnection();
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.exit(0);
				}
			}
		});
	}

}
