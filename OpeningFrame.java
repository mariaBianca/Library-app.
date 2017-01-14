package library;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class to show a loading screen to the user until connection with the database is established 
 * Maria-Bianca Cindroi
 * refactored from Team2: Project Programming, Wine Advisor
 *
 */
public class OpeningFrame {

	private JFrame frame;
	static JPanel panel;

	/**
	 * Create the application.
	 */
	public OpeningFrame(JFrame frame) {
		this.frame = frame;
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		String path = "/images/loading.gif";
		panel = new ImagePanel(path);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		panel.setLayout(null);		
	}
}
