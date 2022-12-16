package animation.gameTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import animation.Game.AbstractAnimation;
import animation.Game.Asteroids;
import animation.Game.BuildShip;
import animation.Game.GameAnimation;

class GameTest {




    private GameAnimation gameObj = new GameStub();
    private BuildShip shipTest = new BuildShip(gameObj);
    private Asteroids asteroidTest = new Asteroids(gameObj, 0, 0, Math.PI, false);

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testNextFrame() {
        //WRAP AROUND TEST FOR SHIP
        // Test what happens when the ship is at the left edge and moving right.
        shipTest.setX(-40);
        shipTest.nextFrame();
        assertEquals(560,shipTest.getX());
        //Test what happens when the ship is at the right edge and moving left.
        shipTest.setX(600);
        shipTest.nextFrame();
        assertEquals(0, shipTest.getX());

        //WRAP AROUND TEST FOR ASTEROID
        // asteroidTest.setAstX(-40);
        // asteroidTest.nextFrame();
        // assertEquals(560, asteroidTest.getAstX());

        asteroidTest.setAstX(641);
        asteroidTest.nextFrame();
        assertEquals(0, asteroidTest.getAstX());

    

    }

    @Test
    void testCollision(){


    }

    @Test
    void testIncreaseScore(){

    }

    @Test
    void testBounceLeft() {
        // Test what happens when the ball is past the right edge
        
        // Initialize the object to be beyond the right edge
        double move = shipTest.getSpeed();
        shipTest.setX(demo.getWidth()-1);
        
        // Move the ball
        shipTest.nextFrame();
        
        // Check that the move amount value has been negated
        assertEquals(-1 * move, shipTest.getSpeed());
        
        // Check that the ball is at the right edge of the window
        assertEquals(demo.getWidth()-shipTest.getShipSize(), shipTest.getX());
        
        // Move the ball again
        shipTest.nextFrame();
        
        // Check that it is further to the left.
        assertEquals(demo.getWidth()-shipTest.getShipSize()-move, shipTest.getX());
    }

    @Test
    void testBounceRight() {
        // Test what happens when the ball reaches the left edge
        
        // Initialize the ball to be left of the left edge and the move
        // amount to be negative, so the ball is moving left
        double amount = -1 * shipTest.getSpeed();
        shipTest.setMoveAmount(amount);
        shipTest.setX(-1);
        
        // Move the ball
        shipTest.nextFrame();
        
        // Check that the ball is at the left edge
        assertEquals (0, shipTest.getX());
        
        // Check that the move amount has been negated
        assertEquals(-1 * amount, shipTest.getSpeed());
        
        // Move the ball again
        shipTest.nextFrame();
        
        // Check that it is further to the right.
        assertEquals(Math.abs(amount), shipTest.getX());

    }

}
