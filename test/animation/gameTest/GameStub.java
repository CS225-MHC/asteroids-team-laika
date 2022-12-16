package animation.gameTest;

import animation.Game.GameAnimation;

public class GameStub extends GameAnimation{

    public int getWidth() {
        return 600;
    }

    //ship's location
    public int shipLocation(){
        return -20;
    }
    
}

