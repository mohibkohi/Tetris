/*
 * TCSS 305 - TetrisShapesPanel.
 * 
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * panel to add the shapes panel to.
 * @author mohibkohi
 * @version 1.0.
 */
public class TetrisShapesPanel extends JPanel {
    
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = -1035504996134211169L;
    
    /**
     * Default width of a Tetris game board.
     */
    private static final int DEFAULT_WIDTH = 250;
    
    /**
     * Default height of a Tetris game board.
     */
    private static final int DEFAULT_HEIGHT = 460;
    

    /**
     * Constructor of the shapes panel.
     * @param theTetrisGamePanel tetris panel.
     */
    public TetrisShapesPanel(final TetrisGamePanel theTetrisGamePanel) {
        super();

        final TetrisGamePanel tetrisGamePanel = theTetrisGamePanel;
        add(tetrisGamePanel);
        
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBackground(Color.gray);
    }
    
}
