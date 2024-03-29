package animation.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * This class demonstrates how shapes can be rotated and translated.
 *
 */
public class Asteroids implements AnimatedObject {

    // The shape that is drawn
    private Polygon p;
    
    // The left edge of the shape
    public double x;
    
    // The top edge of the shape
    public double y;

    // The number of pixels to move on each frame of the animation.
    private int moveAmount = 2;

    //makes the asteroid visible in the frame 
    public boolean astVisible = true;

    // The animation that this object is part of.
    private AbstractAnimation animation;

    private double rotation;

    public boolean isMoving = true;

    
    /**
     * Constructs a triangle
     * 
     * Creates the animated object
     * @param animation the animation this object is part of
     */
    public Asteroids (AbstractAnimation animation, double astx, double asty, double rotate, boolean isMoving) {

        this.animation = animation;
        this.rotation = rotate;
        this.isMoving = isMoving;

        this.x = astx;
        this.y = asty;

        p = new Polygon();

        //Create the Asteroid of 40x60 pixels
        p.addPoint(-10, 30);
        p.addPoint(10,30);
        p.addPoint(20,0);
        p.addPoint(10,-30);
        p.addPoint(-10, -30);
        p.addPoint(-20,0);
        p.addPoint(-20,20);

    
    }


    /**
     * Draws the asteroid
     * @param g the graphics context to draw on
     */
    public void paint(Graphics2D g) {
        if(astVisible){
            g.setColor(Color.BLACK);
            g.draw(getShape());
        }
        

    }

    /**
     * Returns the shape after applying the current translation
     * and rotation
     * @return the shape located as we want it to appear
     */
   
    public Shape getShape() {
        // AffineTransform captures the movement and rotation we
        // want the shape to have
        AffineTransform at1 = new AffineTransform();
        
        // x, y are where the origin of the shape will be.
        at1.translate(x, y);

        // Rotate the shape according to random angle
        at1.rotate(rotation);
        AffineTransform at = at1;
        
        // Create a asteroid
        return at.createTransformedShape(p);
    }

    @Override
    public void nextFrame() {

       if(isMoving){
        // Update the x value to move in the current direction
            x = calculateX(x, rotation);
            y = calculateY(y, rotation);

        // wrap around 
        if (x - 40> animation.getWidth()) {
            x = 0;
        }

        if(y-60> animation.getHeight()){
            y = 0;
        }

        else if (x +40 <= 0) {
            x = animation.getWidth()-40;
            
        }
        else if (y + 60<= 0){
            y = animation.getHeight()-60;
            
        }
    }
        
        
    }

    /**
     * @param x
     * @param rotation
     * @return horizontal movement depending on asteroid's rotation
     */
    public double calculateX(double x, double rotation) {
        double xOffset = moveAmount * Math.sin(rotation);
 
        x = x + xOffset;
        return x;
    }

    /**
     * @param y
     * @param rotation
     * @return vertical movement depending on asteroid's rotation
     */
    public double calculateY( double y, double rotation){
        double yOffset = -moveAmount * Math.cos(rotation);

        y = y + yOffset;
        return y;

    }

    // used for testing
    public double getAstX(){
        return x;
    }

    public double getAstY(){
        return y;
    }

    public double setAstX(double num){
        x = num;
        return x;
    }

    public double setAstY(double num){
        y = num;
        return y;
    }


}
