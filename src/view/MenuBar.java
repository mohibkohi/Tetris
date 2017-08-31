/*
 * TCSS 305 - MenuBar
 * 
 */

package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * MenuBar class extends JMenuBar, adds a menu bar to the frame.
 * Has various methods.
 * 
 * @author mohibkohi
 * @version 1.0.
 */
public class MenuBar extends JMenuBar {

    /** A generated serialization ID. */
    private static final long serialVersionUID = -368853769852963099L;

    /**
     * The minor tick spacing for the FPS slider.
     */
    private static final int MINOR_TICK_SPACING = 1;
    
    /**
     * The major tick spacing for the FPS slider.
     */
    private static final int MAJOR_TICK_SPACING = 5;
    
    /**
     * The maximum frames at which the speed will be drawn.
     */
    private static final int MAX_FRAMES = 9;

    /**
     * The initial frames at which the speed will be drawn.
     */
    private static final int INITIAL_FRAMES = 0;
    
    /**
     * Ellipse instance field name.
     */
    private static final String ABOUT = "About...";

    /**
     * A UWT icon from an image file.
     */
    private static final ImageIcon UW = new ImageIcon("./images/uw.png");

    /**
     * Power point gui.
     */
    private final TetrisGamePanel myPlayingArea;
    
    /**
     * New game menu item.
     */
    private JMenuItem myNewGameMenuItem;
    
    /**
     * End game menu Item.
     */
    private JMenuItem myEndGameMenuItem;
    
    /**
     * Instance myColor.
     */
    private Color myColor;
    
    /**
     * Constructor initializes the drawing area and gui.
     * 
     * @param thePlayingArea to draw shape.
     */
    public MenuBar(final TetrisGamePanel thePlayingArea) {
        super();
        myPlayingArea = thePlayingArea;
        
        //Add property change to playing panel.
        myPlayingArea.addPropertyChangeListener(new PropertyChangeListener()  {

            @Override
            public void propertyChange(final PropertyChangeEvent theEvent) {
                //enable/disable new and end game menu items.
                if ("On".equals(theEvent.getPropertyName())) {
                    myNewGameMenuItem.setEnabled(false);
                    myEndGameMenuItem.setEnabled(true);
                } else if ("Off".equals(theEvent.getPropertyName())) {
                    myEndGameMenuItem.setEnabled(false);
                    myNewGameMenuItem.setEnabled(true);
                }
                
            }
            
        });
        createMenuBar();
        
    }

    /**
     * Method to create the menu bar.
     */
    private void createMenuBar() {
        createGameMenu();
        createOptionsMenu();
        createSoundMenu();
        crateHelpMenu();
        
    }

    /**
     * Create file menu.
     */
    private void createGameMenu() {
        final JMenu fileMenu = new JMenu("Game");
        fileMenu.setMnemonic(KeyEvent.VK_G);
        
        myNewGameMenuItem = new JMenuItem("New Game");
        myNewGameMenuItem.setMnemonic(KeyEvent.VK_W);
        

        myNewGameMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPlayingArea.newGame();
            }
            
        });

        myEndGameMenuItem = new JMenuItem("End Game");
        myEndGameMenuItem.setMnemonic(KeyEvent.VK_E);
        myEndGameMenuItem.setEnabled(false);
        
        myEndGameMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPlayingArea.endGame();
            }
            
        });
        
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPlayingArea.endGame();
                System.exit(0);
            }
        });

        fileMenu.add(myNewGameMenuItem);
        fileMenu.add(myEndGameMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        add(fileMenu);
    }
    

    /**
     * Create option menu.
     */
    private void createOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        
        //final ButtonGroup buttonGroup = new ButtonGroup();
        
        final JRadioButtonMenuItem gridLines = new JRadioButtonMenuItem("Show Grid");
        gridLines.setMnemonic(KeyEvent.VK_G);
        gridLines.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPlayingArea.setGridOption(gridLines.isSelected());
                myPlayingArea.repaint();
            }
            
        });
        
        final JRadioButtonMenuItem piecesRandomColors = 
                        new JRadioButtonMenuItem("Pieces Random Colors");
        piecesRandomColors.setMnemonic(KeyEvent.VK_P);
        piecesRandomColors.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPlayingArea.setPiecesRandomColor(piecesRandomColors.isSelected());
                myPlayingArea.repaint();
                
            }
            
        });
        
        final JRadioButtonMenuItem backgroundandomColors = 
                        new JRadioButtonMenuItem("Background Random Colors");
        backgroundandomColors.setMnemonic(KeyEvent.VK_B);
        
        backgroundandomColors.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPlayingArea.setBackgroundRandomColor(backgroundandomColors.isSelected());
                myPlayingArea.repaint();
                
            }
            
        });
        
        
        final JMenuItem colorMenuItem = new JMenuItem("Color...");
        colorMenuItem.setMnemonic(KeyEvent.VK_C);
        
        colorMenuItem.setIcon(new IconClass(Color.darkGray));

        colorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myColor = JColorChooser.showDialog(myPlayingArea, "Choose a color", myColor);
                if (myColor == null) {
                    myColor = Color.darkGray;
                }
                colorMenuItem.setIcon(new IconClass(myColor));
                myPlayingArea.setColor(myColor);
                myPlayingArea.repaint();
            }
        });
        
        final JMenu thicknessMenuItem = new JMenu("Speed");
        
        thicknessMenuItem.setMnemonic(KeyEvent.VK_T);

        final JSlider thicknessSlider =
                        new JSlider(SwingConstants.HORIZONTAL, 0, MAX_FRAMES, INITIAL_FRAMES);
        thicknessSlider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        thicknessSlider.setMinorTickSpacing(MINOR_TICK_SPACING);
        
        thicknessSlider.setPaintLabels(true);
        thicknessSlider.setPaintTicks(true);
        
        thicknessSlider.addChangeListener(new ChangeListener() {
            /** Called in response to slider events in this window. */
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                myPlayingArea.setSpeed(thicknessSlider.getValue());
            }
        });

        thicknessMenuItem.add(thicknessSlider);
        
        optionsMenu.add(thicknessMenuItem);
        //buttonGroup.add(gridLines);
        optionsMenu.addSeparator();
        optionsMenu.add(backgroundandomColors);
        optionsMenu.add(piecesRandomColors);
        optionsMenu.add(gridLines);
        optionsMenu.addSeparator();
        optionsMenu.add(colorMenuItem);
        add(optionsMenu);
    }

    /**
     * Helper method to add sound menu.
     */
    private void createSoundMenu() {
        final JMenu soundMenu = new JMenu("Sound");
        soundMenu.setMnemonic(KeyEvent.VK_S);
        final JRadioButtonMenuItem soundOn = 
                        new JRadioButtonMenuItem("Sound On");
        soundOn.setMnemonic(KeyEvent.VK_O);
        
        soundOn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPlayingArea.setMusicOff(false);
                myPlayingArea.playSounds();
            }
        });
        
        
        final JRadioButtonMenuItem soundOff = 
                        new JRadioButtonMenuItem("Sound Off");
        soundOff.setMnemonic(KeyEvent.VK_F);
        
        soundOff.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPlayingArea.stopSounds();
                myPlayingArea.setMusicOff(true);
            }
        });
        
        final ButtonGroup buttonGroup = new ButtonGroup();
        soundOn.setSelected(true);
        buttonGroup.add(soundOn);
        buttonGroup.add(soundOff);

        soundMenu.add(soundOn);
        soundMenu.add(soundOff);
        add(soundMenu);
        
    }
    /**
     * Create help menu.
     */
    private void crateHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        final JMenuItem aboutMenu = new JMenuItem(ABOUT);
        aboutMenu.setMnemonic(KeyEvent.VK_A);

        aboutMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null,
                                              "Tetris Movement\nRight = Right Arrow key"
                                              + "\nLeft = Left Arrow key"
                                              + "\nDown = Down Arrow key"
                                              + "\nRotate ClockWise = Up Arrow key"
                                              + "\nDrop = Space"
                                              + "\nRotate Counter Clock Wise = z"
                                              + "\nPause Game = P",
                                              ABOUT, JOptionPane.PLAIN_MESSAGE, UW);
            }
        });
        helpMenu.add(aboutMenu);
        add(helpMenu);
    }

}
