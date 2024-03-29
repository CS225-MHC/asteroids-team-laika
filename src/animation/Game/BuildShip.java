package animation.Game;  
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Random;


//build the ship 
public class BuildShip implements AnimatedObject {

    //shape of the ship 
    private Polygon s; 

    //left edge of ship 
    private double x ; 

    //right edge of ship
    private double y; 

    private static final int SHIP_SIZE = 40 ; 

    // The animation that this object is part of.
    private AbstractAnimation animation;
    
    //variable to decide on the rotation amount 
    private double rotateAmt ; 
    
    //generate random number to use hyperspace 
    Random moveAmt = new Random(); 
    private int upperBound = 600; 

    //points to build the ship 
    int[] xPoints = {-10,0,10} ; 
    int[] yPoints = {20, -20,20};

    //speed 
    private int speed = 10; 

    /**
     * Creates the animated object
     * 
     * @param animation the animation this object is part of
     */
    public BuildShip(AbstractAnimation animation) {
        this.animation = animation;
        s = new Polygon(xPoints, yPoints, 3 );
        // adjusts to the screen and puts the ship in thi middle of the frame 
        x = 300; 
        y = 300; 

    }

    
    /**
     * Draws the triangle
     * @param g the graphics context to draw on
     */
    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.draw(getShape());
    }


    /**
     * Returns the shape after applying the current translation
     * and rotation
     * @return the shape located as we want it to appear
     */
    public Shape getShape() {

        AffineTransform at1 = new AffineTransform();
        
        // x, y are where the origin of the shape will be: center of the window 
        at1.translate(x, y);
        
        // Rotate the shape rotateAmt degrees to the direction of key-pressed
        at1.rotate(rotateAmt);
        
        if(Math.abs(rotateAmt) == Math.PI*2 ){
            rotateAmt = 0;
        }
    
        nextFrame();

        // Creates a ship
        return at1.createTransformedShape(s);
    }

    /**
     * action to take when the user clicks the up arrow button.
     * This moves the ship straight to the direction the ship is facing
     */
    public void forward() {

        double xOffset =  speed*Math.sin(rotateAmt); 
        double yOffset = -speed*Math.cos(rotateAmt); 

        y = y+ yOffset; 
        x = x+ xOffset; 

    }

    /**
     * action to take when the user clicks the right arrow button.
     * This rotates the ship 45degree to the right
     */
    public void turnRight() {
        rotateAmt = rotateAmt + Math.PI/4;
    }

    /**
     * action to take when the user clicks the left arrow button.
     * This rotates the ship 45degree to the left
     */
    public void turnLeft() {
        rotateAmt = rotateAmt + -Math.PI/4;

    }


    /**
     * action to take when the user clicks the shift button.
     * This launches the ship in a random coordinate in the window 
     */
    public void hyperspace() {
        x = moveAmt.nextInt(upperBound); 
        y = moveAmt.nextInt(upperBound);
        
    }

    /**
     * wraps around the screen so that if a ship is on the edge,
     * it will appear from the other end of the screen
     */
    @Override
    public void nextFrame() {
        if (x+SHIP_SIZE>animation.getWidth()){
            x = 0;
        }
        else if (x<0){
            x = animation.getWidth()-SHIP_SIZE;
        }

        if(y+SHIP_SIZE>animation.getHeight()){
            y=0;
        }
        else if(y<0){
            y = animation.getHeight()-SHIP_SIZE;
        }
    
    }

    //used for testing 
    public double getX(){
        return x; 
    }

    public double getY(){
        return y ; 
    }

    public double getRotation (){
        return rotateAmt;
    }

    public double getSpeed(){
        return speed;
    }

    public double getShipSize(){
        return SHIP_SIZE;
    }

    public double setX(double num){
        x = num;
        return x;
    }
    public double setY(double num){
        y = num;
        return y;
    }




}