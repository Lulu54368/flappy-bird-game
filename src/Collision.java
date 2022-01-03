import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * This class is to render all the logic about Collision
 */
public class Collision implements Overlap{
    private Bird bird;
    private PlasticPipe[] plasticPipes;
    private Point birdPos;
    private Rectangle upbox;
    private Rectangle downbox;
    private int num;

    /**
     * This constructor is to build an instance of Collision class
     * @param bird Bird bird instance involved in this collision
     * @param plasticPipes PlasticPipe[] an array of current plastic pipes
     * @param num int the number of pipes built
     */
    public Collision(Bird bird, PlasticPipe[] plasticPipes, int num){
        this.bird = bird;
        this.plasticPipes = plasticPipes;
        birdPos = bird.getPos();
        this.num = num;

    }


    /**
     * This method is to find which pipe is hit by the bird
     * @return int the index of the pipe, -1 if no pipe is hit
     */
    public int  valid(){
        for(int i = 0; i < num; i++){
            if(plasticPipes[i] == null){
                continue;
            }
            boolean whetherFlame = false;
            upbox =  plasticPipes[i].getboxup();
            downbox = plasticPipes[i].getBoxdown();
            if (plasticPipes[i] instanceof SteelPipe){
                SteelPipe steelPipe = (SteelPipe) plasticPipes[i];
                if(steelPipe.getFlamePos() == steelPipe.getPos() && steelPipe.getfireboxup() != null){
                    if(steelPipe.getfireboxup().intersects(bird.getBox()) ||
                            (steelPipe.getfireboxdown().intersects(bird.getBox()))){
                        return i;
                    }
                }

            }

            if (bird.getBox().intersects(upbox) || bird.getBox().intersects(downbox)){
                return i;
            }


        }


        return -1;
    }

    /**
     * This method is to check whether the bird crosses the pipe,
     * and make the pipe null type for whatever the bird crosses
     * @return boolean Whether the bird crosses the pipe or not
     */
    public boolean cross(int timeScale) {

        for(int i = 0; i < num; i++){
            if(plasticPipes[i] == null){
                continue;
            }
            upbox =  plasticPipes[i].getboxup();
            downbox = plasticPipes[i].getBoxdown();
            if (bird.getBox().left() >upbox.right() && bird.getBox().left() < upbox.right() +
                    3.1*Math.pow(1.5, timeScale -1)){

                return true;
            }
        }
        return false;
    }

}
