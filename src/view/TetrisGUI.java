/*
 * TCSS 305 - TetrisGUI.
 * 
 */

package view;

import control.KeyboardControl;

import java.awt.BorderLayout;
import javax.swing.JFrame;


import model.Board;

/**
 * Tetris GUI class for every tetris piece. 
 * @author mohibkohi.
 * @version 1.0
 */
public class TetrisGUI extends JFrame {
    
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 2167516530264875860L;
    
    /**
     * Constructor to set the frame.
     */
    public TetrisGUI() {
        super("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        inti();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Initialize the GUIs.
     */
    private void inti() {
        
        final Board board = new Board();
        
        final TetrisGamePanel gameArea = new TetrisGamePanel(board);
        final TetrisShapesPanel shapesArea = new TetrisShapesPanel(gameArea);
        
        final NextShapePanel nextShapeArea = new NextShapePanel(board, gameArea);
        add(nextShapeArea, BorderLayout.EAST);
        
        final KeyboardControl keyControl = new KeyboardControl(board, gameArea);
        addKeyListener(keyControl);
        
        add(shapesArea, BorderLayout.CENTER);
        final MenuBar menu = new MenuBar(gameArea);
        
        setJMenuBar(menu);
        
       
      
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
}
