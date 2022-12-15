package animation.Ship;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import animation.AbstractAnimation;
import animation.Bullet;
import animation.Asteroids;



public class GameAnimation extends AbstractAnimation implements KeyListener{
    // The width of the window, in pixels.
    private static final int WINDOW_WIDTH = 600;
    
    // The height of the window, in pixels.
    private static final int WINDOW_HEIGHT = 600;

    // Create new ship
    private BuildShip ship = new BuildShip(this);
    private boolean shipVisible = true;
    private boolean moving = false;

    // Create a bullet
    private Bullet bullet ;
    private boolean bulletMoving = false; 

    //asteroid variables
    double asteroidX;
    double asteroidY; 
    double asteroidRotation;
    Asteroids[] asteroidList = new Asteroids[5];
    private boolean asteroidMoving = true;

    /**
     * Create a method to set an asteroids list of multiple asteroids
     * moving at different points
     */
    public void setAsteroids(){
        
        for(int i=0; i<asteroidList.length;i++){
            //create a random from 0 to pi
            double randNumber = new Random().nextDouble(2*Math.PI);
            //set the rotation to a random value
            asteroidRotation = randNumber;
            //create random x and y points where each asteroid appears
            asteroidX = new Random().nextInt(400);
            asteroidY = new Random().nextInt(400);
            //update the asteroidList
            asteroidList[i] = new Asteroids(this, asteroidX, asteroidY, asteroidRotation, asteroidMoving);

         }
    }
    /**
     * @return the list of multiple asteroids
     */
    public Asteroids[] getAsteroids(){
        return asteroidList;
    }
    
    /**
     * Constructs an animation and initializes it to be able to accept
     * key input.
     */
    public GameAnimation () {
        // Allow the game to receive key input
        setFocusable(true);
        addKeyListener (this);
        //need to change name of the constructor
        setAsteroids();
    }

    /**
     * Paint the animation by painting the objects in the animation.
     * @param g the graphic context to draw on
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        if(shipVisible){
            ship.paint((Graphics2D) g);
            if ( bulletMoving){
                bullet.paint((Graphics2D) g);
           }
        }

        for(int i = 0; i <asteroidList.length ; i++){
            if(asteroidList[i].astVisible){
                getAsteroids()[i].paint((Graphics2D) g);
            }        
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
            
        }
        if ( bulletMoving){
            bullet.nextFrame();
            // check if bullet hits an asteroid
            for(int i = 0; i <asteroidList.length ; i++){ 
                if (checkCollision (bullet, getAsteroids()[i])) {
                    getAsteroids()[i].isMoving = false; //asteroid stops moving
                    getAsteroids()[i].astVisible = false; //asteroid dissappears if bullets hits it
                    System.out.println("HIT");
                }
            }
        }

        for(int i = 0; i <asteroidList.length ; i++){
            getAsteroids()[i].nextFrame();
            // check if asteroid hits the ship
            if (checkShipCollision(getAsteroids()[i], ship)) {
                shipVisible = false; // make ship disappear
                System.out.println("HIT");
            }
        }
        repaint();
    }

    /**
     * Check whether the bullet and asteroid collides
     * @param shape1 the bullet
     * @param shape2 the asteroid
     * @return true if the shapes intersect
     */
    private boolean checkCollision(Bullet shape1, Asteroids shape2) {

        return shape2.getShape().intersects(shape1.getShape().getBounds2D());

    }
    /**
     * Check whether asteroid and ship collides.
     * @param shape1 the asteroid
     * @param shape2 the ship
     * @return
     */
    private boolean checkShipCollision(Asteroids shape1, BuildShip shape2){

        return shape2.getShape().intersects(shape1.getShape().getBounds2D());
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
        GameAnimation game = new GameAnimation();

        // // Add the animation to the window
        Container contentPane = f.getContentPane();
        contentPane.add(game, BorderLayout.CENTER);

        // Display the window.
        f.setVisible(true);
        
        // Start the animation
        game.start();
    }
}
