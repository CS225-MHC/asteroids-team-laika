package animation.asteroidDemo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Random;

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

    int x1 = new Random().nextInt(500);
    int y1 = new Random().nextInt(400);

    List<Polygon> ast;
    


    
    /**
     * Constructs a triangle
     * 
     * Creates the animated object
     * @param animation the animation this object is part of
     */
    public Asteroids (AbstractAnimation animation) {
        this.animation = animation;
        p = new Polygon();
        p.addPoint(0, 30);
        p.addPoint(20, 10);
        p.addPoint(20, -10);
        p.addPoint(0, -30);
        p.addPoint(-20, -10);
        p.addPoint(-20, 10);
        
        x = 0;
        y = y1;
    }


    /**
     * Draws the triangle
     * @param g the graphics context to draw on
     */
    public void paint(Graphics2D g) {
        // for(int i =0; i<10; i++){
        //     ast.add(g.draw(getShape()));
        // }
        g.setColor(Color.BLACK);
        g.draw(getShape());
        //need to udate list
        for(Polygon p1 : ast){
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
        
        // x, y are where the origin of the shape will be.  In this
        // case, this is the center of the triangle.  See the constructor
        // to see where the points are.
        at1.translate(x, y);

        
        // Rotate the shape 45 degrees to the left
        at1.rotate(Math.PI/2);
        AffineTransform at = at1;
        
        // Create a shape that looks like our triangle, but centered
        // and rotated as specified by the AffineTransform object.
        return at.createTransformedShape(p);
    }

    @Override
    public void nextFrame() {
        // TODO Auto-generated method stub
        // Update the x value to move in the current direction
        x = x + moveAmount;

        // Check if the right edge of the ball is beyond the right
        // edge of the window. If it is, move it to the right edge
        // and change the direction, so it will move left on its
        // next move.
        // if (x + 40 > animation.getWidth()) {
        //     x = animation.getWidth() - 40;
        //     moveAmount = moveAmount * -1;
        // }

        // if(y + 40 > animation.getWidth()){
        //     y = animation.getWidth() - 40;
        //     moveAmount = moveAmount * -1;
        // }

        // Check if the left edge of the ball is beyond the left
        // edge of the window. If it is, move it to the left edge
        // and chante the direction, so it will move right on its
        // next move.
        // else if (x < 0) {
        //     x = 0;
        //     moveAmount = moveAmount * -1;
        // }

        // else if (y <0){
        //     y = 0;
        //     moveAmount = moveAmount * -1;
        // }
        //
        //p.setFrame(x, y, Polygon_height, polygon_width);
        
    }
}
