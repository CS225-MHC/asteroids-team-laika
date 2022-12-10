package animation.asteroidDemo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
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

    //private int x1;

    // The number of pixels to move on each frame of the animation.
    private int moveAmount = 2;

    // The animation that this object is part of.
    private AbstractAnimation animation;

    int x1 = new Random().nextInt(500);
    int y1 = new Random().nextInt(400);

    //List<Polygon> asteroidsList = new ArrayList<>();

    int randNum =  new Random().nextInt(8);
    
    
    /**
     * Constructs a triangle
     * 
     * Creates the animated object
     * @param animation the animation this object is part of
     */
    public Asteroids (AbstractAnimation animation) {

        this.animation = animation;

        // for(int i = 0; i< asteroidsList.size();i++){
        //     asteroidsList. = new Polygon();
        // }
        p = new Polygon();

        //Create the Asteroid
        p.addPoint(-20, 40);
        p.addPoint(30, 40);
        p.addPoint(50, 10);
        p.addPoint(30,0);
        p.addPoint(30, -20);
        p.addPoint(-10, -40);
        p.addPoint(-40,-20);
        p.addPoint(-40,10);
        // for(int i = 0; i< 2;i++){
        //     asteroidsList.add(p);
        // }
        ArrayList<Polygon> asteroidList = new ArrayList<Polygon>();
        for(int i = 0; i<= 2;i++){
            asteroidList.add(p);

        }
        


        //x = 0;
        //y = y1;
    }


    /**
     * Draws the asteroid
     * @param g the graphics context to draw on
     */
    public void paint(Graphics2D g) {
        // for(int i =0; i<10; i++){
        //     ast.add(g.draw(getShape()));
        // }
        g.setColor(Color.BLACK);
        g.draw(getShape());
        //need to udate list
        // for(Polygon p : asteroidsList){
        //     g.draw(getShape());
        // }
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
        //AffineTransform at2 = new AffineTransform();
        
        // x, y are where the origin of the shape will be.  In this
        // case, this is the center of the triangle.  See the constructor
        // to see where the points are.
        at1.translate(x, y);

        
        // Rotate the shape 45 degrees to the left
        at1.rotate(Math.PI/2);
        AffineTransform at = at1;
        
        // Create a shape that looks like our triangle, but centered
        // and rotated as specified by the AffineTransform object.
        // for(Polygon p1 : asteroidsList){
        //     at.createTransformedShape(p1);
        // }

        return at.createTransformedShape(p);
    }

    @Override
    public void nextFrame() {
        // TODO Auto-generated method stub
        // Update the x value to move in the current direction
      

        if(randNum == 0){
            x = x + moveAmount;

        }
        else if(randNum ==1){
            y = y + moveAmount;

        }
        else if(randNum ==2){
            x = x + moveAmount;
            y = y + moveAmount;
        }
        else if(randNum ==3){
            x = x - moveAmount;
        }
        else if(randNum ==4){
            y = y - moveAmount;

        }
        else if(randNum ==5){
            x = x - moveAmount;
            y = y + moveAmount;
        }
        else if(randNum ==6){
            x = x + moveAmount;
            y = y - moveAmount;
        }
        else if (randNum ==7){
            x = x - moveAmount;
            y = y - moveAmount;
        }

        // Check if the right edge of the ball is beyond the right
        // edge of the window. If it is, move it to the right edge
        // and change the direction, so it will move left on its
        // next move.
        if (x + 40 > animation.getWidth()) {
            x = 0;
            y = new Random().nextInt(400);
            randNum =  new Random().nextInt(8);


        }

        if(y + 40 > animation.getHeight()){
            x = new Random().nextInt(500);
            y = 0;
            randNum =  new Random().nextInt(8);
            
        }
  

        // Check if the left edge of the ball is beyond the left
        // edge of the window. If it is, move it to the left edge
        // and change the direction, so it will move right on its
        // next move.

        else if (x < 0) {
            x = 500;
            y = new Random().nextInt(400);
            randNum = new Random().nextInt(8);
        }

        else if (y <0){
            x = new Random().nextInt(500);
            y = 500;
            randNum = new Random().nextInt(8);
        }
        
        //p.setFrame(x, y, Polygon_height, polygon_width);
        
    }
}
