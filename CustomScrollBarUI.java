package library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * Class to modify the aspect of a scrollBar
 * @author Maria-Bianca Cindroi
 * Refactored from Team2: Project Programming, Wine Advisor
 *
 */
public class CustomScrollBarUI extends BasicScrollBarUI
{
	/**
	 * method to override the super method and set the thumb in a different aspect
	 */
	@Override
	protected void paintThumb( Graphics g, JComponent c, Rectangle thumbBounds )
	{
		if( thumbBounds.isEmpty() || !scrollbar.isEnabled() )
		{
			return;
		}
		
		if( scrollbar.getOrientation() == JScrollBar.VERTICAL )
		{
			g.setColor(Color.DARK_GRAY);
			g.fillRect( thumbBounds.x+thumbBounds.width/4, thumbBounds.y, thumbBounds.width/2, thumbBounds.height );
		}
		else if( scrollbar.getOrientation() == JScrollBar.HORIZONTAL )
		{
			g.setColor(Color.DARK_GRAY);
			g.fillRect( thumbBounds.x, thumbBounds.y+thumbBounds.height/4, thumbBounds.width, thumbBounds.height/2 );
		}
	}
	/**
	 * method to override the super method and set the track in a different aspect
	 */
	@Override
	protected void paintTrack( Graphics g, JComponent c, Rectangle trackBounds )
	{
		if( scrollbar.getOrientation() == JScrollBar.VERTICAL )
		{
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect( trackBounds.x+trackBounds.width/4, trackBounds.y, trackBounds.width/2, trackBounds.height );
		}
		else if( scrollbar.getOrientation() == JScrollBar.HORIZONTAL )
		{
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect( trackBounds.x, trackBounds.y+trackBounds.height/4, trackBounds.width, trackBounds.height/2 );
		}
		
		if( this.trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT )
		{
			paintDecreaseHighlight( g );
		}
		else if( this.trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT )
		{
			paintIncreaseHighlight( g );
		}
	}
	
	/**
	 * method to override the super method and take off the decrease button
	 */
	@Override
	protected JButton createDecreaseButton( int orientation )
	{
		return createEmptyButton();
	}
	
	/**
	 * method to override the super method and take off the increase button
	 */
	@Override
	protected JButton createIncreaseButton( int orientation )
	{
		return createEmptyButton();
	}
	/**
	 * method to create an empty Jbutton 
	 * @return JButton
	 */
	private JButton createEmptyButton()
	{
		JButton jbutton = new JButton();
		jbutton.setPreferredSize( new Dimension( 0, 0 ) );
		jbutton.setMinimumSize( new Dimension( 0, 0 ) );
		jbutton.setMaximumSize( new Dimension( 0, 0 ) );
		return jbutton;
	}
}
//end of CustomScrollBarUI