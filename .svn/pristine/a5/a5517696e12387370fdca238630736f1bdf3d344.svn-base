/*
 * TCSS 305 - NextShapePanel.
 * 
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Board;
import model.Point;
import model.TetrisPiece;

/**
 * Next shapes panel for every next piece, level and score.
 * 
 * @author mohibkohi
 * @version 1.0.
 */
public class NextShapePanel extends JPanel implements Observer {
    
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = -5807018020543280126L;

    /**
     * Default width of a Tetris game board.
     */
    private static final int DEFAULT_WIDTH = 150;

    /**
     * Default height of a Tetris game board.
     */
    private static final int DEFAULT_HEIGHT = 100;

    /**
     * Move X direction.
     */
    private static final int BLOCK_HEIGHT = 25;

    /**
     * Move Y direction.
     */
    private static final int BLOCK_WIDTH = 25;

    /**
     * The y difference.
     */
    private static final int Y_DIFF = 105;

    /**
     * A constant number two.
     */
    private static final int NUM_TWO = 2;
    
    /**
     * Score to multiply when one row is cleared.
     */
    private static final int ONELINE_SCORE = 40;
    
    /**
     * Score to multiply when two row is cleared.
     */
    private static final int TWOLINE_SCORE = 100;
    
    /**
     * Score to multiply when three row is cleared.
     */
    private static final int THREELINE_SCORE = 300;
    
    /**
     * Score to multiply when four row is cleared.
     */
    private static final int FOURLINE_SCORE = 1200;
    
    /**
     * number four constant.
     */
    private static final int FOUR_CONSTANT = 4;
    
    /**
     * number three constant.
     */
    private static final int THREE_CONSTANT = 3;
    
    /**
     * number twenty constant.
     */
    private static final int TWENTY_CONSTANT = 20;
    
    /**
     * Board field.
     */
    //private final Board myBoard;

    /**
     * The 4 Points of the TetrisPiece.
     */
    private Point[] myPoints;

    /**
     * Block Filed.
     */
    private TetrisPiece myTetrisPiece;

    /**
     * Complete rows.
     */
    private Integer[] myCompleteRows;

    /**
     * Score.
     */
    private int myScore;
    
    /**
     * Multiplier for every level.
     */
    private int myMuiltiplier;
    
    /**
     * First piece doped.
     */
    private boolean myPieceDroped;
    /**
     * Constructor of the panel.
     * 
     * @param theBoard board.
     * @param theTetrisPanel tetris panel
     */
    public NextShapePanel(final Board theBoard, final TetrisGamePanel theTetrisPanel) {
        super();
        final Board board = theBoard;

        board.addObserver(this);
        theTetrisPanel.addPropertyChangeListener(new PropertyChangeListener()  {

            @Override
            public void propertyChange(final PropertyChangeEvent theEvent) {
                if ("Increment".equals(theEvent.getPropertyName())) {
                    
                    myMuiltiplier++;
                    //myGameOver = true;
                } else if ("On".equals(theEvent.getPropertyName())) {
                    myMuiltiplier = 0;
                    myScore = 0;
                    myPieceDroped = false;
                    myMuiltiplier++;
                }
                
            }
            
        });
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBackground(Color.darkGray);
    }

    /**
     * Paints the current path.
     * 
     * @param theGraphics The graphics context to use for painting.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setFont(new Font("TimesRoman", Font.PLAIN, TWENTY_CONSTANT));
        if (myPoints != null) {
            g2d.setColor(Color.white);
            g2d.drawString("Next", BLOCK_WIDTH, BLOCK_WIDTH + BLOCK_WIDTH);
            g2d.drawString("Level " + myMuiltiplier , BLOCK_WIDTH, 
                           NUM_TWO * NUM_TWO * BLOCK_WIDTH + Y_DIFF);
            
            g2d.drawString("Score " + myScore , BLOCK_WIDTH, 
                           NUM_TWO * NUM_TWO * BLOCK_WIDTH + Y_DIFF + TWENTY_CONSTANT);
            for (int i = 0; i < myPoints.length; i++) {
                g2d.setColor(Color.white);
                g2d.fillRect(myPoints[i].x() * BLOCK_WIDTH + BLOCK_WIDTH,
                             myPoints[i].y() * (-1) * BLOCK_HEIGHT + Y_DIFF, BLOCK_WIDTH,
                             BLOCK_HEIGHT);
                g2d.setColor(Color.black);
                g2d.drawRect(myPoints[i].x() * BLOCK_WIDTH + BLOCK_WIDTH,
                             myPoints[i].y() * (-1) * BLOCK_HEIGHT + Y_DIFF, BLOCK_WIDTH,
                             BLOCK_HEIGHT);
            }
        }

    }

    @Override
    public void update(final Observable theObservable, final Object theArg) {

        if (theArg instanceof TetrisPiece) {
            myTetrisPiece = (TetrisPiece) theArg;
            myPoints = myTetrisPiece.getPoints();
            myPieceDroped = true;
        }
        if (theArg instanceof TetrisPiece && myPieceDroped) {
            myScore += FOUR_CONSTANT;
        }
        
        if (theArg instanceof Integer[]) {
            myCompleteRows = (Integer[]) theArg;
            switch (myCompleteRows.length) {
                case 1:
                    myScore += myCompleteRows.length * (ONELINE_SCORE * myMuiltiplier);
                    break;
                case 2:
                    myScore += myCompleteRows.length *  (TWOLINE_SCORE * myMuiltiplier);
                    break;
                case THREE_CONSTANT:
                    myScore += myCompleteRows.length * (THREELINE_SCORE * myMuiltiplier);
                    break;
                case FOUR_CONSTANT:
                    myScore += myCompleteRows.length * (FOURLINE_SCORE * myMuiltiplier);
                    break;
                default:
                    break;
            }
        }
        repaint();

    }

}
