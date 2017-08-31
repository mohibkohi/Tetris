/*
 * TCSS 305 - TetrisMain.
 * 
 */
package view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * Main class runs main method.
 * @author mohibkohi
 * @version 1.0.
 */
public final class TetrisMain {
    
    /**
     * Overloaded constructor.
     */
    private TetrisMain() {
        //do nothing.
    }
    
    /**
     * Tetris main method.
     * @param theArgs string.
     */
    public static void main(final String[] theArgs) {
        try {
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TetrisGUI();
            }
        });
    }

}
