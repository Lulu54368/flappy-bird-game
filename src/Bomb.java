import bagel.Image;

import java.awt.*;

/**
 *
 */
public class Bomb extends Rock{
    /**
     * This is a constructor creating an instance for class Bomb
     * @param pipes an array of PlasticPipe class
     * @param bird an instance of Bird class
     * @param start start time
     * @param num
     */
    public Bomb(PlasticPipe[] pipes, Bird bird, int start, int num) {
        super(pipes, bird, start, num);
        img = new Image("res/level-1/bomb.png");
        timeLimit = 50;
    }

    /**
     * This method is to judge whether it hit the pipe when bomb is overlap with pipe
     * @param pipe The PlasticPipe instance overlap with bomb
     * @return boolean return whether it hits
     */
    @Override
    public boolean hitPipe(PlasticPipe pipe){
        return true;
    }

}
