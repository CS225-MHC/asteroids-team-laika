package animation.asteroidDemo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import animation.AbstractAnimation;

/**
 * This class provides a simple demonstration of how you would implement an 
 * animation (or game!) that contains multiple animated objects.
 *
 */
public class printAsteroid extends AbstractAnimation implements KeyListener {
    // The width of the window, in pixels.
    private static final int WINDOW_WIDTH = 600;
    
    // The height of the window, in pixels.
    private static final int WINDOW_HEIGHT = 600;
    
    // The object that moves during the animation.  You might have
    // many objects!

    int asteroidX ;
    int asteroidY ;
    int asteroidRotation;
    // Asteroids[] asteroidsList;
    // private Asteroids asteroidShape = new Asteroids(this, asteroidX, asteroidY, asteroidRotation);
    Asteroids[] asteroidList = new Asteroids[5];

    // Asteroids asteroid1 = new Asteroids(this, 40, 40, (int)Math.PI/4);
    // Asteroids asteroid2 = new Asteroids(this, 70, 70, (int)Math.PI/2);

    //private ArrayList<Asteroids> asteroidList = new ArrayList<Asteroids>();
    
    private boolean moving = true;

    public Asteroids[] AsteroidsMethod(){
        
        asteroidX = new Random().nextInt(600);
        asteroidY = new Random().nextInt(600);
        double randNumber = new Random().nextDouble(Math.PI);
        int rotation = (int) (Math.PI/randNumber);



        for(int i=0; i<asteroidList.length;i++){
           
            asteroidList[i] = new Asteroids(this, asteroidX, asteroidY, rotation);
        }
        return asteroidList;

    }


    /**
     * Constructs an animation and initializes it to be able to accept
     * key input.
     */
    public printAsteroid () {
        // Allow the game to receive key input
        setFocusable(true);
        addKeyListener (this);
    }

    @Override
    /**
     * Updates the animated object for the next frame of the animation
     * and repaints the window.
     */
    protected void nextFrame() {
        if (moving) {
            for(int i = 0; i <asteroidList.length ; i++){

                AsteroidsMethod()[i].nextFrame();
                //asteroidList[i].nextFrame();
            }
            // asteroid1.nextFrame();
            // asteroid2.nextFrame();

            repaint();

        }
    }

    /**
     * Paint the animation by painting the objects in the animation.
     * @param g the graphic context to draw on
     */
    public void paintComponent(Graphics g) {
        // Note that your code should not call paintComponent directly.
        // Instead your code calls repaint (as shown in the nextFrame
        // method above, and repaint will call paintComponent.
        
        super.paintComponent(g);
        // asteroid1.paint((Graphics2D) g);
        // asteroid2.paint((Graphics2D) g);
        for(int i = 0; i <asteroidList.length ; i++){

            AsteroidsMethod()[i].paint((Graphics2D) g);
            //asteroidList[i].paint((Graphics2D) g);
        }
        
    }

    
    /**
     * This is called when the user releases the key after pressing it.
     * It does nothing.
     * @param e information about the key released
     */
    public void keyReleased(KeyEvent e) {
        // Nothing to do
    }

    @Override
    /**
     * This is called when the user presses and releases a key without
     * moving the mouse in between.  Does nothing.
     * @param e information about the key typed.
     */
    public void keyTyped(KeyEvent e) {
        // Nothing to do
    }

    /**
     * The main method creates a window for the animation to run in,
     * initializes the animation and starts it running.
     * @param args none
     */
    public static void main(String[] args) {
        // JFrame is the class for a window.  Create the window,
        // set the window's title and its size.
        JFrame f = new JFrame();
        f.setTitle("Animation Demo");
        f.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        // This says that when the user closes the window, the
        // entire program should exit.
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the animation.
        printAsteroid demo = new printAsteroid();

        // Add the animation to the window
        Container contentPane = f.getContentPane();
        contentPane.add(demo, BorderLayout.CENTER);

        // Display the window.
        f.setVisible(true);
        
        // Start the animation
        demo.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

}
