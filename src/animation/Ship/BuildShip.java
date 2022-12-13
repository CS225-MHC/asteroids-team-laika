package animation.Ship;  
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Random;
import animation.AbstractAnimation;
import animation.AnimatedObject;


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
        x = 300;  // adjusts to the screen 
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
        //up
        if ( rotateAmt == 0 ){
            y = y-speed;
        }
        //down
        else if (rotateAmt == -Math.PI || rotateAmt == Math.PI){
            y = y - (speed*(-Math.abs(rotateAmt)/Math.PI));
        }
        //right
        else if ( rotateAmt == Math.PI/2  || rotateAmt == -(3*Math.PI)/2){
            x = x + speed; 
        }
        //left
        else if ( rotateAmt == (3*Math.PI)/2 || rotateAmt == -Math.PI/2){
            x = x - speed; 
        }
        //right-up
        else if (rotateAmt == Math.PI/4 || rotateAmt == (-7*Math.PI)/4){
            x = x +speed;
            y = y-speed;
        }
        //right-down
        else if(rotateAmt == (-5*Math.PI)/4 || rotateAmt == (3*Math.PI)/4){
            x = x +speed;
            y=y+speed;
        }
        //left-up
        else if (rotateAmt == -Math.PI/4 || rotateAmt == (7*Math.PI)/4){
            x=x-speed;
            y=y-speed;
        }
        //left-down
        else if (rotateAmt == (5*Math.PI)/4 || rotateAmt == (-3*Math.PI)/4){
            x=x-speed;
            y=y+speed;
        }

        System.out.println ("Up");
    }

    /**
     * action to take when the user clicks the right arrow button.
     * This rotates the ship 45degree to the right
     */
    public void turnRight() {
        rotateAmt = rotateAmt + Math.PI/4;
        System.out.println ("right");

    }

    /**
     * action to take when the user clicks the left arrow button.
     * This rotates the ship 45degree to the left
     */
    public void turnLeft() {
        rotateAmt = rotateAmt + -Math.PI/4;
        System.out.println ("left");

    }


    /**
     * action to take when the user clicks the shift button.
     * This launches the ship in a random coordinate in the window 
     */
    public void hyperspace() {
        x = moveAmt.nextInt(upperBound); 
        y = moveAmt.nextInt(upperBound);
        System.out.println ("Nyoom");
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

    public double getX(){
        return x; 
    }

    public double getY(){
        return y ; 
    }

    public double getRotation (){
        return rotateAmt;
    }





}