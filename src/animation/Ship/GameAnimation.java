package animation.Ship;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import animation.AbstractAnimation;
import animation.Bullet;
import animation.Asteroids;
import animation.Scoreborad;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.WindowAdapter;


public class GameAnimation extends AbstractAnimation implements KeyListener, WindowListener{

    // The width of the window, in pixels.
    private static final int WINDOW_WIDTH = 600;
    
    // The height of the window, in pixels.
    private static final int WINDOW_HEIGHT = 600;

    // Create new ship
    private BuildShip ship = new BuildShip(this);
    private static boolean shipVisible = true;
    private boolean moving = false;

    // Create a list of bullets
    private Bullet[] bullets = new Bullet[100];
    int b =0;
    private boolean bulletMoving = false; 

    //asteroid variables
    double asteroidX;
    double asteroidY; 
    double asteroidRotation;
    Asteroids[] asteroidList = new Asteroids[5];
    private boolean asteroidMoving = true;

    //Create the scoreboard 
    static Scoreborad scoreB = new Scoreborad(); 

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
            asteroidY = new Random().nextInt(380, 600); //makes the atreoids pop only in the corners 
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
                int activeBullets=0;
                for(int i=0; i<bullets.length;i++){
                    if(bullets[i]!= null){
                        activeBullets = activeBullets +1;
                    }
                }
                for(int j =0; j < activeBullets;j++){
                    bullets[j].paint((Graphics2D) g);
                }    
           }

           int counter = 0 ; 
            for ( int i = 0 ; i< asteroidList.length; i++){
                if ( getAsteroids()[i].astVisible == false){
                    counter++;
                } 
                if( counter == asteroidList.length){
                    if ( scoreB.getScore() > scoreB.getHighScore() ){
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("arcade", Font.BOLD, 40));
                        g.drawString("New High Score!!", 200, 200);
                    }
                    else{
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("arcade", Font.BOLD, 40));
                        g.drawString("You Win!!", 200, 200);
                    }

                }    
                
            }
        }else{
            g.setColor(Color.BLACK);
            g.setFont(new Font("arcade", Font.BOLD, 40));
            g.drawString("Game Over", 200, 200);
        }

        for(int i = 0; i <asteroidList.length ; i++){
            if(asteroidList[i].astVisible){
                getAsteroids()[i].paint((Graphics2D) g);
            }        
        }

        
    }

    public void buildBullet(){
        int activeBullets=0;
        for(int i=0; i<bullets.length;i++){
            if(bullets[i]!= null){
                activeBullets = activeBullets +1;
            }
        }
        if ( activeBullets> b){
            b++;
        }
        if(bullets[b] ==null){
            bullets[b] = new Bullet(this, ship.getX(), ship.getY(), ship.getRotation());
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
            buildBullet();
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
            // check if bullet hits an asteroid
            int activeBullets=0;
            for(int i=0; i<bullets.length;i++){
                if(bullets[i]!= null){
                    activeBullets = activeBullets +1;
                }
            }
            for(int j =0; j < activeBullets;j++){
                bullets[j].nextFrame();;
            }
            for(int j = 0; j< activeBullets; j++){ 
                for (int i = 0; i <asteroidList.length ; i++){
                    if (checkCollision (bullets[j], getAsteroids()[i])) { 
                        getAsteroids()[i].isMoving = false; //asteroid stops moving
                        getAsteroids()[i].astVisible = false;
                        getAsteroids()[i].x = 2000000;
                        getAsteroids()[i].y = 2000000; //puts the asteroid out of the frame  
                        scoreB.increaseScore(); 
                        break; 
                    }
                }

            }
        }

        for(int i = 0; i <asteroidList.length ; i++){
            getAsteroids()[i].nextFrame();
            // check if asteroid hits the ship
            if (checkShipCollision(getAsteroids()[i], ship)) {
                shipVisible = false;  // make ship disappear
                break; 
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
        
        if (shape2.getShape().intersects(shape1.getShape().getBounds2D())){
            return true; 
        }else{
            return false; 
        }
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

        f.setTitle("Ship, Asteriods & Bullet");
        f.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        // Create the animation.
        GameAnimation game = new GameAnimation();
        

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
