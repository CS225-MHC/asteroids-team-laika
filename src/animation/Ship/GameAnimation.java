package animation.Ship;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import animation.AbstractAnimation;
import animation.Bullet;
<<<<<<< Updated upstream:src/animation/Ship/GameAnimation.java
import animation.Asteroids;



public class GameAnimation extends AbstractAnimation implements KeyListener{
=======
import animation.Scoreborad;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.WindowAdapter;


public class ShipAnimation extends AbstractAnimation implements KeyListener, WindowListener  {
>>>>>>> Stashed changes:src/animation/Ship/ShipAnimation.java
    // The width of the window, in pixels.
    private static final int WINDOW_WIDTH = 600;
    
    // The height of the window, in pixels.
    private static final int WINDOW_HEIGHT = 600;
<<<<<<< Updated upstream:src/animation/Ship/GameAnimation.java

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
=======
    
    //builidng the ship 
    private BuildShip ship = new BuildShip(this);
    
    //Create the scoreborad 
    public static Scoreborad scoreB  = new Scoreborad(); 

    //building the bullet     private Bullet bullet ;
    private Bullet bullet ;  

    private int j = 0;


    private boolean moving = false; //for ship 
    private boolean bulletMoving = false; //bullte
  
>>>>>>> Stashed changes:src/animation/Ship/ShipAnimation.java
    
    /**
     * Constructs an animation and initializes it to be able to accept
     * key input.
     */
    public GameAnimation () {
        // Allow the game to receive key input
        setFocusable(true);
<<<<<<< Updated upstream:src/animation/Ship/GameAnimation.java
        addKeyListener (this);
        //need to change name of the constructor
        setAsteroids();
=======
        addKeyListener ((KeyListener) this);

>>>>>>> Stashed changes:src/animation/Ship/ShipAnimation.java
    }


    /**
     * Paint the animation by painting the objects in the animation.
     * @param g the graphic context to draw on
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
<<<<<<< Updated upstream:src/animation/Ship/GameAnimation.java
        if(shipVisible){
            ship.paint((Graphics2D) g);
            if ( bulletMoving){
                bullet.paint((Graphics2D) g);
           }
=======
        ship.paint((Graphics2D) g);
        if ( bulletMoving){
            bullet.paint((Graphics2D) g);

>>>>>>> Stashed changes:src/animation/Ship/ShipAnimation.java
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
            //scoreB.increaseScore();//call score increase in case of collision 
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
<<<<<<< Updated upstream:src/animation/Ship/GameAnimation.java
            
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
=======

        }
        if ( bulletMoving){
            bullet.nextFrame();

>>>>>>> Stashed changes:src/animation/Ship/ShipAnimation.java
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

<<<<<<< Updated upstream:src/animation/Ship/GameAnimation.java
        return shape2.getShape().intersects(shape1.getShape().getBounds2D());
=======
    /**
     * call this method in case of ship-asteroid collison 
     * @return component over which displays game over on the screen 
     */
    public static Component gameOver(){
        JLabel over = new JLabel(); 
        over.setText("Game Over");
        over.setHorizontalAlignment(SwingConstants.CENTER);
        System.exit(2);
        return over;
>>>>>>> Stashed changes:src/animation/Ship/ShipAnimation.java
    }

    public static void main(String[] args) {
        //Create the window,
        // set the window's title and its size.
        JFrame f = new JFrame();

        f.setTitle("Ship, Asteriods & Bullet");
        f.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        // Create the animation.
<<<<<<< Updated upstream:src/animation/Ship/GameAnimation.java
        GameAnimation game = new GameAnimation();
=======
        ShipAnimation game = new ShipAnimation();
        
>>>>>>> Stashed changes:src/animation/Ship/ShipAnimation.java

        // // Add the animation to the window
        Container contentPane = f.getContentPane();
        contentPane.add(game, BorderLayout.CENTER);
        contentPane.add(scoreB,  BorderLayout.BEFORE_FIRST_LINE); 
        
        // Display the window.
        f.setVisible(true); 
        
        // Start the animation
        game.start();

        // if user closes the window, the entire program should exit.
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //before the window closes, take the score, compare it with the file's score and 
        //write it in HighScore file if currScore> file's score  
        f.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent ev) {
                // int confirmed = JOptionPane.showConfirmDialog(null, 
                // "Are you sure you want to exit the program?", "Exit Program Message Box",
                // JOptionPane.YES_NO_OPTION);
        
                // if (confirmed == JOptionPane.YES_OPTION) {
                //     System.exit(0);
                // }    
                 
                File myFile = new File( "src/animation/HighScore.txt");
                Scanner reader;
                try {
                    reader = new Scanner(myFile);
                    if (reader.hasNext()){  // file is not empty
                        String line = "";  
                        while( reader.hasNextLine()){                              
                            line = reader.nextLine();
                        }    

                        String lastScore = line.split(": ")[line.split(": ").length -1]; 
                        if ( scoreB.getScore() > Integer.parseInt(lastScore)){
                            try {
                                FileWriter writer = new FileWriter(myFile, true);
                                writer.append('\n' + "New High Score: " + scoreB.getScore() ); 
                                writer.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } 
                        }
                                                
                    }else { //if file is empty, just write
                            
                        try {
                            FileWriter writer = new FileWriter(myFile,true);
                            writer.append("New High Score: " + scoreB.getScore()); 
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } 

                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                };

            }
          });
        
    

    }


    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }






}
