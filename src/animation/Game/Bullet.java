package animation.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;


public class Bullet implements AnimatedObject{

    // The diameter of the ball, in pixels
    private static final double BULLET_SIZE = 5;
 
    //starting point of the bullet 
    private double x ;
    private double y ; 

    //initializing the direction of the bullet
    private double rotateAmt; 

    //initial speed of the bullet
    private int moveAmt  = 5; 

    public boolean moving = true; 

    // The animation that this object is part of.
    private AbstractAnimation animation;
    
    // The bullet shape
     private Ellipse2D bullet;


    /**
     * Creates the animated object
     * 
     * @param animation the animation this object is part of
     */
    public Bullet(AbstractAnimation animation, double origin_x, double origin_y, double rotateAmt ) {
        this.animation = animation;
        this.x = origin_x;
        this.y = origin_y;  
        this.rotateAmt = rotateAmt; 
        bullet = new Ellipse2D.Double(x, y, BULLET_SIZE, BULLET_SIZE);
    }
    

    /**
     * Draws a black circle at its current location.
     * 
     * @param g the graphics context to draw on.
     */
    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fill(bullet);
    
    }

    /**
     * Returns the ball that is the graphics shape 
     * @return the ball that is the graphics shape being drawn
     */
    public Shape getShape() {
        return bullet;
    }


    /**
     * Moves the ball moveAmt times. if it goes above the screen, it doesn't wrap around 
     */
    public void nextFrame() {
        if ( moving){

            //straight 
            double xOffset =  moveAmt*Math.sin(rotateAmt); 
            double yOffset = -moveAmt*Math.cos(rotateAmt); 

            y = y+ yOffset; 
            x = x+ xOffset; 

        }
            
        bullet.setFrame(x, y, BULLET_SIZE, BULLET_SIZE);
        
    }

}
