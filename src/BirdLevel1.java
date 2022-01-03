import bagel.Image;

public class BirdLevel1 extends Bird{
    /**
     * This construct is to accept the current level and
     * construct an instance of class Bird
     *

     */
    private static boolean changed = false;
    public BirdLevel1() {
        super();
        imgUp = new Image("res/level-1/birdWingUp.png");
        imgDown = new Image("res/level-1/birdWingDown.png");

    }

    /**
     *This method is to reset Parameter
     */

    public void resetParameter(){
        if(!changed){
            h = 300;
            v0 = 0;
            changed = true;
        }

    }
}
