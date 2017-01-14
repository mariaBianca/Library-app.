package library;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Builds a background panel with a different background image according to the frame's width 
 * and height.
 * 
 * @author Maria-Bianca Cindroi
 * License type: cc-by-sa 
 * https://creativecommons.org/licenses/by-sa/2.5/
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel{

    private Image image;
    private ImageIcon MyImage;

    /**
     * Gives the path of the desired image to be painted.
     * @param path
     */
    public ImagePanel(String path) {
       MyImage = new ImageIcon(getClass().getResource(path));
	  this.image =  MyImage.getImage();
    }

    /**
     * Paints image according to the frame's size
     */
    @Override
  //apply the image while setting the size to the panel size
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);         
    }          
}
// end of ImagePanel class
