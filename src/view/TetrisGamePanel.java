/*
 * TCSS 305 - TetrisGamePanel.
 * 
 */
package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Block;
import model.Board;
import sound.SoundPlayer;

/**
 * Playing game area, an observer class. 
 * 
 * @author mohibkohi
 * @version 1.0.
 *
 */
public class TetrisGamePanel extends JPanel implements Observer {

    /**
     * Ellipse instance field name.
     */
    private static final String GAME_OVER = "Game over";

    /**
     * A UWT icon from an image file.
     */
    private static final ImageIcon GAME_OVER_PIC = new ImageIcon("./images/gameOver.jpg");

    /**
     * Rows and clumsy.
     */
    private static final int ROWS_COLMS = 30;

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 6793672164028037355L;

    /**
     * The initial frames per second at which the simulation will run.
     */
    private static final int INITIAL_FRAMES_PER_SECOND = 1;

    /**
     * The numerator for delay calculations.
     */
    private static final int MY_DELAY_NUMERATOR = 1000;

    /**
     * Default width of a Tetris game board.
     */
    private static final int DEFAULT_WIDTH = 220;

    /**
     * Default height of a Tetris game board.
     */
    private static final int DEFAULT_HEIGHT = 440;

    /**
     * Difference in the y axies.
     */
    private static final int Y_DIFF = DEFAULT_HEIGHT - 22;
    
    /**
     * Constant number twenty two.
     */
    private static final int TWENTY_TWO_CONSTANT = 22;

    /**
     * Constant number thirty.
     */
    private static final int THIRTHY_CONSTATN = 30;
    
    /**
     * Constant number forty.
     */
    private static final int FORTY_CONSTATN = 40;
    
    /**
     * Constant number one fifty.
     */
    private static final int ONE_FIFTY_CONSTATN = 150;
    
    /**
     * number four constant.
     */
    private static final int FOUR_CONSTANT = 4;
    
    /**
     * Number 100 constant.
     */
    private static final int HUNDRAD_CONSTANT = 100;
    
    /**
     * Number 11 constant.
     */
    private static final int ELEVEN_CONSTANT = 11;
    
    /**
     * SoundPlsyer class field.
     */
    private final SoundPlayer mySound; 
    
    /**
     * Board field.
     */
    private Board myBoard;

    /**
     * Board field.
     */
    private List<Block[]> myBlockPieces;

    /**
     * A timer used to update the state of the simulation.
     */
    private Timer myTimer;

    /**
     * X axes of frozen piece .
     */
    private int myXPiece;

    /**
     * Current game is playing or not.
     */
    private boolean myGameOn;

    /**
     * Size of blocks.
     */
    private int mySize;

    /**
     * In the y direction.
     */
    private int myYPiece;

    /**
     * Game over.
     */
    private boolean myGameOver;

    /**
     * Game pause state.
     */
    private boolean myGamePause;

    /**
     * Counter for the lines cleared.
     */
    private int myLinesCounter;

    /**
     * Complete rows.
     */
    private Integer[] myCompleteRows;

    /**
     * My Grid Options.
     */
    private boolean myGridOption;

    /**
     * My random options.
     */
    private boolean myPieceRadomColor;
    
    /**
     * My random options.
     */
    private boolean myBackgroundRadomColor;
    
    /**
     * Line increment for x axis.
     */
    private final int myXLineIncrement = TWENTY_TWO_CONSTANT;

    /**
     * Instance myColor.
     */
    private Color myColor;
    
    /**
     * My sound can turn on or off.
     */
    private boolean mySoundOnOff;
    
    /**
     * Music control field.
     */
    private boolean myMusicOn;
    /**
     * Constructor of the panel.
     * 
     * @param theBoard board.
     */
    public TetrisGamePanel(final Board theBoard) {
        super();
        
        setLayout(new BorderLayout());
        myBoard = theBoard;
        myBoard.addObserver(this);
        setUpTimer();

        myColor = Color.darkGray;
        mySound = new SoundPlayer();
        
        myGameOver = false;
        
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBackground(Color.darkGray);
       
    }

    /**
     * Helper method to set up timer.
     */
    private void setUpTimer() {
        final int delay = MY_DELAY_NUMERATOR / INITIAL_FRAMES_PER_SECOND;
        myTimer = new Timer(delay, new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myBoard.step();
            }
        });
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
        mySize = (getHeight() + getWidth()) / ROWS_COLMS;

        if (myGridOption) {
            drawGridLines(g2d);
        }
        
        if (myBackgroundRadomColor) {
            if (myGameOn  && !myGamePause  && !myGameOver) {
                setBackground(randomColorBlocks());
            }
        } else {
            setBackground(myColor);
        }
        

        if (myBlockPieces != null) {
            drawBlocks(g2d);
        }

    }
    
    /**
     * Helper method to draw the Tetris pieces with random colors.
     * @return randomColor random.
     */
    private Color randomColorBlocks() {
        final Random rand = new Random();

        //Three Random float for a random color.
        final float r = rand.nextFloat();
        final float g = rand.nextFloat();
        final float b = rand.nextFloat();

        return new Color(r, g, b);
    }

    /**
     * Helper method to draw the Tetris pieces.
     * @param theGraphics 2d Graphic.
     */
    private void drawBlocks(final Graphics2D theGraphics) {
        Color color = Color.black;
        
        for (final Block[] blocks : myBlockPieces) {

            for (final Block block : blocks) {

                if (block != null) {
                    if (myPieceRadomColor) {
                        color = randomColorBlocks();
                        theGraphics.setColor(color);
                    } else {
                        theGraphics.setColor(Color.black);
                    }
                    theGraphics.fillRect(myXPiece * mySize + 1, Y_DIFF - myYPiece * mySize + 1,
                                         mySize - 2, mySize - 2);
                    if (myPieceRadomColor) {
                        theGraphics.setColor(color);
                    } else {
                        theGraphics.setColor(Color.white);
                    }
                    theGraphics.drawRect(myXPiece * mySize + 1, Y_DIFF - myYPiece * mySize + 1,
                                         mySize - 2, mySize - 2);

                }
                myXPiece++;
            }
            myXPiece = 0;
            myYPiece++;
        }
        myYPiece = 0;

        theGraphics.setFont(new Font("TimesRoman", Font.PLAIN, THIRTHY_CONSTATN));
        if (myGameOn) {
            theGraphics.setColor(Color.red);
            //do something.
        } else {
            theGraphics.setColor(Color.red);
            theGraphics.drawString("Game Ended", FORTY_CONSTATN, ONE_FIFTY_CONSTATN);
        }  
        if (myGamePause) {
            theGraphics.setColor(Color.red);
            theGraphics.drawString("PAUSE", FORTY_CONSTATN, ONE_FIFTY_CONSTATN);
        }
    }

    /**
     * New game calls board new game.
     */
    public void newGame() {
        if (!myTimer.isRunning()) {
            myBoard.newGame();
            myTimer.setDelay(MY_DELAY_NUMERATOR);
            setGameOn();
            
            setBackground(Color.darkGray);
            
            myGameOn = true;
            myGamePause = false;
            playSounds();
            myTimer.start();
        }
    }
    
    /**
     * Helper method to play sound.
     */
    public void playSounds() {
        if (!myMusicOn && myGameOn && !mySoundOnOff) {
            mySound.loop("sounds/Tetris.mp3"); 
            myMusicOn = true;
        }
    }
    
    /**
     * Helper method to stop sound.
     */
    public void stopSounds() {
        if (myMusicOn) {
            mySound.stopAll();
            myMusicOn = false;
        }
    }
    
    /**
     * Set music on and off.
     * 
     * @param theMusic on or off.
     */
    public void setMusicOff(final boolean theMusic) {
        mySoundOnOff = theMusic;
    }
    
    /**
     * Helper method update the speed.
     * @param theSpeed to change.
     */
    public void setSpeed(final int theSpeed) {
        final int speed = 1000 - theSpeed * 100;
        myTimer.setDelay(speed);
    }
    /**
     * Helper method to draw the grid lines.
     * 
     * @param theGraphics 2d graphic.
     */
    private void drawGridLines(final Graphics2D theGraphics) {
        int incrementLine = ELEVEN_CONSTANT;
        theGraphics.setColor(Color.red);
        for (int i = 1; i < ELEVEN_CONSTANT; i++) {

            theGraphics.drawLine(i * myXLineIncrement, 0, i * myXLineIncrement,
                                 DEFAULT_HEIGHT);
            theGraphics.drawLine(0, i * myXLineIncrement, DEFAULT_WIDTH, i * myXLineIncrement);

            theGraphics.drawLine(0, incrementLine * myXLineIncrement, DEFAULT_WIDTH,
                                 incrementLine * myXLineIncrement);
            incrementLine++;
        }
        incrementLine = 0;
    }
    

    /**
     * Increment the score.
     */
    public void incrementScore() {
        firePropertyChange("Increment", !myGameOver, myGameOver);
    }

    /**
     * Fire property change on game over.
     */
    public void setGameOff() {
        firePropertyChange("Off", !myGameOver, myGameOver);
    }

    /**
     * Fire property change on new game.
     */
    public void setGameOn() {
        firePropertyChange("On", !myGameOver, myGameOver);
    }

    

    /**
     * Set color field.
     * 
     * @param theColor of the shape.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**
     * Access method for the timer.
     * 
     * @return myTimer timer.
     */
    public boolean isTimerOn() {
        return myTimer.isRunning();
    }

    /**
     * Set pause state of the game.
     * 
     * @param theState of the game.
     */
    public void setGamePuse(final boolean theState) {
        if (theState) {
            myTimer.stop();
            myGamePause = theState;
        } else {
            myTimer.start();
            myGamePause = theState;
        }
        repaint();
    }
   
    /**
     * Set Random Colors option.
     * 
     * @param theRandomOption option to set random colors to true.
     */
    public void setBackgroundRandomColor(final boolean theRandomOption) {
        myBackgroundRadomColor = theRandomOption;
    }
    
    /**
     * Set Random Colors option.
     * 
     * @param theRandomOption option to set random colors to true.
     */
    public void setPiecesRandomColor(final boolean theRandomOption) {
        myPieceRadomColor = theRandomOption;
    }
    
    /**
     * Set Grids option.
     * 
     * @param theGridOption option to turn the grid on.
     */
    public void setGridOption(final boolean theGridOption) {
        myGridOption = theGridOption;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(final Observable theObservable, final Object theArg) {
        
        myBoard = (Board) theObservable;
        if (theArg instanceof List) {
            myBlockPieces = (List<Block[]>) theArg;
           

        } else if (theArg instanceof Boolean) {
            playSounds();
            myGameOver = (boolean) theArg;
        } else if (theArg instanceof Integer[]) {
            myCompleteRows = (Integer[]) theArg;
            myLinesCounter += myCompleteRows.length;
            final int timepInt = myTimer.getDelay();
            if (myLinesCounter > FOUR_CONSTANT) {
                myTimer.setDelay(timepInt - HUNDRAD_CONSTANT);
                incrementScore();
                myLinesCounter -= FOUR_CONSTANT;
            } else if (myLinesCounter == FOUR_CONSTANT) {
                myTimer.setDelay(timepInt - HUNDRAD_CONSTANT);
                incrementScore();
                myLinesCounter = 0;
            }
        }
        if (myGameOver) {
            myGameOn = false;
            stopSounds();
            myTimer.stop();
            JOptionPane.showMessageDialog(null, "TCSS 305 Tetris\nWinter 2016\nMohib Kohi",
                                          GAME_OVER, JOptionPane.PLAIN_MESSAGE, GAME_OVER_PIC);
            myGameOver = false;
            
            setGameOff();
        }
        repaint();
    }
    
    /**
     * End game.
     */
    public void endGame() {
        myGameOn = false;
        stopSounds();
        myGamePause = false;
        myTimer.stop();
        setGameOff();
        
        repaint();
    }
}
