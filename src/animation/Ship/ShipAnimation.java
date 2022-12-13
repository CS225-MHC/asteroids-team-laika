package animation.Ship;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import animation.AbstractAnimation;
import animation.Bullet;
import animation.Asteroids;

public class ShipAnimation extends AbstractAnimation implements KeyListener{
    // The width of the window, in pixels.
    private static final int WINDOW_WIDTH = 600;
    
    // The height of the window, in pixels.
    private static final int WINDOW_HEIGHT = 600;

    private BuildShip ship = new BuildShip(this);

    private Bullet bullet ;
    
    private boolean moving = false; 
    private boolean bulletMoving = false; 

    //asteroid variables
    double asteroidX;
    double asteroidY; 
    double asteroidRotation;

    Asteroids[] asteroidList = new Asteroids[5];
    
    private boolean asteroidMoving = true;
    
    /**
     * Constructs an animation and initializes it to be able to accept
     * key input.
     */
    public ShipAnimation () {
        // Allow the game to receive key input
        setFocusable(true);
        addKeyListener (this);
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
        ship.paint((Graphics2D) g);
        if ( bulletMoving){
             bullet.paint((Graphics2D) g);
        }
        
    }


    
    @Override
    /**
     * This is called when the user presses a key.
     * It notifies the ship about presses of up arrow, right 
     * arrow, left arrow, and the space bar and shift. All other keys are ignored.
     * @param e information abou the key pressed
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
        case KeyEvent.VK_UP:
            ship.forward();
            break;
        case KeyEvent.VK_RIGHT:
            ship.turnRight();
            break;
        case KeyEvent.VK_LEFT:
            ship.turnLeft();
            break; 
        case KeyEvent.VK_SPACE:
            bulletMoving = true; 
            bullet = new Bullet(this, ship.getX(), ship.getY(), ship.getRotation());
            bullet.shoot();
            break;
        case KeyEvent.VK_SHIFT: 
            ship.hyperspace();
            break; 
        default:
            // Ignore all other keys
                
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void nextFrame() {
        if (moving) {
            ship.nextFrame();
            // if (checkCollision (shape, triangle)) {
            //     moving = false;
            // }
            
        }
        if ( bulletMoving){
            bullet.nextFrame();
        }
        repaint();

    }


    public static void main(String[] args) {
        //Create the window,
        // set the window's title and its size.
        JFrame f = new JFrame();
        f.setTitle("Ship");
        f.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        // if user closes the window, the entire program should exit.
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the animation.
        ShipAnimation game = new ShipAnimation();

        // // Add the animation to the window
        Container contentPane = f.getContentPane();
        contentPane.add(game, BorderLayout.CENTER);

        // Display the window.
        f.setVisible(true);
        
        // Start the animation
        game.start();
    }
}
