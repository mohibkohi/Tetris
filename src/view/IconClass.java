/*
 * TCSS 305 - IconClass
 * Class icon
 */

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * Class icon keeps track of the current color.
 *  
 * @author mohibkohi
 * @version 1.0.
 */
public class IconClass implements Icon {

    /**
     * Width of colored icon.
     */
    private static final int WIDTH = 15;

    /**
     * Width of colored icon.
     */
    private static final int HEIGHT = 15;

    /**
     * Color of the icon.
     */
    private final Color myColor;

    /**
     * Constructor of the icon.
     * 
     * @param theColor of the icon.
     */
    public IconClass(final Color theColor) {
        this.myColor = theColor;
    }

    @Override
    public void paintIcon(final Component theComp, final Graphics theGraphic, final int theX,
                          final int theY) {

        final Color oldColor = theGraphic.getColor();
        theGraphic.setColor(myColor);
        theGraphic.fill3DRect(theX, theY, getIconWidth(), getIconHeight(), true);
        theGraphic.setColor(oldColor);

    }

    /**
     * Width of the icon.
     * 
     * @return the width.
     */
    public int getIconWidth() {
        return WIDTH;
    }

    /**
     * Height of the icon.
     * 
     * @return the height.
     */
    public int getIconHeight() {
        return HEIGHT;
    }

}
