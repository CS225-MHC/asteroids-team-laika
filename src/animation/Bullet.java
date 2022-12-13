package animation;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;




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
     * Moves the ball moveAmt times. if it goes above the screen, it doesn't wrap around 
     */
    public void nextFrame() {
        if ( moving){
            // Update the x value to move in the current direction
            //x = x  +  moveAmt*Math.sin(rotateAmt);
            //y = -y + moveAmt*Math.cos(rotateAmt);
                    //up
            if ( rotateAmt == 0 ){
                y = y-moveAmt;
            }
            //down
            else if (rotateAmt == -Math.PI || rotateAmt == Math.PI){
                y = y - (moveAmt*(-Math.abs(rotateAmt)/Math.PI));
            }
            //right
            else if ( rotateAmt == Math.PI/2  || rotateAmt == -(3*Math.PI)/2){
                x = x + moveAmt; 
            }
            //left
            else if ( rotateAmt == (3*Math.PI)/2 || rotateAmt == -Math.PI/2){
                x = x - moveAmt; 
            }
            //right-up
            else if (rotateAmt == Math.PI/4 || rotateAmt == (-7*Math.PI)/4){
                x = x +moveAmt;
                y = y-moveAmt;
            }
            //right-down
            else if(rotateAmt == (-5*Math.PI)/4 || rotateAmt == (3*Math.PI)/4){
                x = x +moveAmt;
                y=y+moveAmt;
            }
            //left-up
            else if (rotateAmt == -Math.PI/4 || rotateAmt == (7*Math.PI)/4){
                x=x-moveAmt;
                y=y-moveAmt;
            }
            //left-down
            else if (rotateAmt == (5*Math.PI)/4 || rotateAmt == (-3*Math.PI)/4){
                x=x-moveAmt;
                y=y+moveAmt;
            }

        }
            // Check if the right edge of the ball is beyond the right
            // edge of the window. If it is, move it to the right edge
            // and change the direction, so it will move left on its
            // next move.
            // if (x + BULLET_SIZE > animation.getWidth()) {
            //     x = animation.getWidth() - BULLET_SIZE;
            //     moveAmt = moveAmt * -1;
            // }

            // Check if the left edge of the ball is beyond the left
            // edge of the window. If it is, move it to the left edge
            // and chante the direction, so it will move right on its
            // next move.
            // else if (x < 0) {
            //     x = 0;
            //     moveAmt = moveAmt * -1;
            // }
            
        bullet.setFrame(x, y, BULLET_SIZE, BULLET_SIZE);
        
    }

    /**
     * this method is executed when the ship tries to shoot bullet
     */
    public boolean shoot() {
        nextFrame();
        return moving; 
    }

}
