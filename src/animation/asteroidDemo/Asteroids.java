package animation.asteroidDemo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import animation.AbstractAnimation;
import animation.AnimatedObject;

/**
 * This class demonstrates how shapes can be rotated and translated.
 *
 */
public class Asteroids implements AnimatedObject {
    // The shape that is drawn
    private Polygon p;
    
    // The left edge of the shape
    private int x;
    
    // The top edge of the shape
    private int y;

    // The number of pixels to move on each frame of the animation.
    private int moveAmount = 2;

    // The animation that this object is part of.
    private AbstractAnimation animation;

    private int rotation;
    
    /**
     * Constructs a triangle
     * 
     * Creates the animated object
     * @param animation the animation this object is part of
     */
    public Asteroids (AbstractAnimation animation, int astx, int asty, int rotate) {

        this.animation = animation;
        this.rotation = rotate;

        this.x = astx;
        this.y = asty;

        p = new Polygon();

        //Create the Asteroid of 40x60 pixels
        p.addPoint(-20, 40);
        p.addPoint(30,30);
        p.addPoint(30,30);
        p.addPoint(30,0);
        p.addPoint(30, -20);
        p.addPoint(-10, -30);
        p.addPoint(-40,-20);
        p.addPoint(-10,10);

    
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
    public Shape getShape(int rotation) {
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
        if (x + 40 > animation.getWidth()) {
            x = 0;
        }

        if(y + 40 > animation.getHeight()){
            y = 0;
        }
  
        // Check if the left edge of the ball is beyond the left
        // edge of the window. If it is, move it to the left edge
        // and change the direction, so it will move right on its
        // next move.

        else if (x < 0) {
            x = 500;
            
        }

        else if (y <0){

            y = 500;
            
        }
        
        
    }

    /**
     * @param x
     * @param rotation
     * @return horizontal movement depending on asteroid's rotation
     */
    public int calculateX(int x, int rotation) {
        double xOffset = moveAmount * Math.sin(rotation);
 
        x = (int) (x + xOffset);
        return x;
    }

    /**
     * @param y
     * @param rotation
     * @return vertical movement depending on asteroid's rotation
     */
    public int calculateY( int y, int rotation){
        double yOffset = -moveAmount * Math.cos(rotation);

        y = (int) (y + yOffset);
        return y;

    }
}
