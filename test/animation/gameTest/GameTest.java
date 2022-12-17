package animation.gameTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import animation.Game.Asteroids;
import animation.Game.BuildShip;
import animation.Game.Bullet;
import animation.Game.GameAnimation;
import animation.Game.Scoreboard;

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

        //the ship is amoving past lower boundry.
        shipTest.setY(640);
        shipTest.nextFrame();
        assertEquals(0, shipTest.getY());

        //the ship is moving past upper boundry
        shipTest.setY((-40));
        shipTest.setX(300);
        shipTest.nextFrame();
        assertEquals(560, shipTest.getY());

        // //WRAP AROUND TEST FOR ASTEROID
        Asteroids asteroid1 = new Asteroids(gameObj, 0, 0, 0, true); 
        asteroid1.setAstX(-40);
        asteroid1.nextFrame();
        assertEquals(0, asteroidTest.getAstX());
        
        asteroid1.setAstX(641);
        asteroid1.nextFrame();
        assertEquals(0, asteroid1.getAstX());

        asteroid1.setAstY(-60); 
        asteroid1.nextFrame();
        assertEquals(540, asteroid1.getAstY());
        
        Asteroids asteroid2 = new Asteroids(gameObj, 0, 0, Math.PI/2, true); 
        asteroid2.setAstY(661); 
        asteroid2.nextFrame();
        assertEquals(0, asteroid2.getAstY());


    }

    @Test
    void testShipAsteroidCollision(){

        shipTest.setX(200); 
        shipTest.setY(200); 
    
        asteroidTest.setAstX(200); 
        asteroidTest.setAstY(200);         

        assertEquals(true, gameObj.checkShipCollision( asteroidTest, shipTest)); 

        shipTest.setX(100); 
        shipTest.setY(200); 

        asteroidTest.setAstX(200); 
        asteroidTest.setAstY(200);  

        assertEquals(false, gameObj.checkShipCollision( asteroidTest, shipTest)); 

    }

    @Test 
    void testBulletAsteroidCollision(){
        Bullet bullet1 = new Bullet(gameObj, 500, 200, 0);
        asteroidTest.setAstX(500); 
        asteroidTest.setAstY(200);  
        
        assertEquals(true, gameObj.checkCollision(bullet1, asteroidTest));

        Bullet bullet2 = new Bullet(gameObj, 400, 200, 0);
        asteroidTest.setAstX(500); 
        asteroidTest.setAstY(200);

        assertEquals(false, gameObj.checkCollision(bullet2, asteroidTest));
        
    }

    @Test
    void testIncreaseScore(){
        Scoreboard scoreTest = new Scoreboard(); 
        Bullet bullet1 = new Bullet(gameObj, 500, 200, 0);

        asteroidTest.setAstX(500); 
        asteroidTest.setAstY(200); 

        int score = 20; 
        if( gameObj.checkCollision(bullet1, asteroidTest)){
            scoreTest.increaseScore();
        }
                  
        assertEquals(score, scoreTest.getScore() );
    }




}
