package animation;

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
    private double x;
    
    // The top edge of the shape
    private double y;

    // The number of pixels to move on each frame of the animation.
    private int moveAmount = 2;

    // The animation that this object is part of.
    private AbstractAnimation animation;

    private double rotation;

    
    /**
     * Constructs a triangle
     * 
     * Creates the animated object
     * @param animation the animation this object is part of
     */
    public Asteroids (AbstractAnimation animation, double astx, double asty, double rotate) {

        this.animation = animation;
        this.rotation = rotate;

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

        g.setColor(Color.BLACK);
        g.draw(getShape(rotation));

    }

    /**
     * Returns the shape after applying the current translation
     * and rotation
     * @return the shape located as we want it to appear
     */
    public Shape getShape(double rotation) {
        // AffineTransform captures the movement and rotation we
        // want the shape to have
        AffineTransform at1 = new AffineTransform();
        
        // x, y are where the origin of the shape will be.  In this
        // case, this is the center of the triangle.  See the constructor
        // to see where the points are.
        at1.translate(x, y);

        
        // Rotate the shape 45 degrees to the left
        at1.rotate(rotation);
        AffineTransform at = at1;
        
        // Create a shape that looks like our triangle, but centered
        // and rotated as specified by the AffineTransform object.
       

        return at.createTransformedShape(p);
    }

    @Override
    public void nextFrame() {
        // Update the x value to move in the current direction
      
        x = calculateX(x, rotation);
        y = calculateY(y, rotation);


        // Check if the right edge of the ball is beyond the right
        // edge of the window. If it is, move it to the right edge
        // and change the direction, so it will move left on its
        // next move.
        if (x - 20 > animation.getWidth()) {
            x = 0;
        }

        if(y -30 > animation.getHeight()){
            y = 0;
        }
  
        // Check if the left edge of the ball is beyond the left
        // edge of the window. If it is, move it to the left edge
        // and change the direction, so it will move right on its
        // next move.

        else if (x + 20< 0) {
            x = 620;
            
        }

        else if (y +30 <0){

            y = 630;
            
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
}
