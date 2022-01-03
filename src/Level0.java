import bagel.Keys;

/**
 * This class is to render all the logic in Level0
 */
public class Level0 {
    protected int count;
    protected int curr = 0;
    protected PlasticPipe[] plasticPipes;
    protected Status status;
    protected Bird bird;
    protected Collision collision;

    protected LifeBarLv0 lifeBarLv;
    private boolean falling = true;
    protected int timeScale = 1;

    /**
     * This constructor is to construct an instance of level0
     * @param count The timer
     */
    public Level0(int count, Status status){
        this.count = count;
        plasticPipes = new PlasticPipe[10];
        this.status = status;
        status.updatePipes(plasticPipes, Math.min(10, curr));
        status.renderStart();
        lifeBarLv = new LifeBarLv0();

    }

    /**
     * This method is to check whether it's right time to define a PlasticPipe
     * create a new PlasticPipe if it's right time
     */
    protected void definePlasticPipe(){
        int time = (int) (100 / Math.pow(1.5, timeScale-1));
        if (count % time == 0 && count > 0) {
            plasticPipes[curr % 10] =  new PlasticPipe(count * 3);
            plasticPipes[curr % 10].start = count;
            curr += 1;
        }


    }

    /**
     * This method is to change the position of the plastic pipe
     */
    protected void changePos(){

        for (int i = 0; i < Math.min(10,curr); i++){
            if(plasticPipes[i] == null){
                continue;
            }
            int start = plasticPipes[i].start;
            plasticPipes[i].changePos((int) ((count - start) *  3 * Math.pow(1.5, timeScale-1)));


        }
    }

    /**
     * This method is to render the logic for level1
     * @return int whether the game fails or not
     */
    public boolean renderLevel0(){

        status.updatePipes(plasticPipes, Math.min(10, curr));
        definePlasticPipe();
        changePos();
        bird = new Bird();
        collision = new Collision(bird, plasticPipes, Math.min(10, curr));
        cross();
        status.renderGame();

        if(!renderLifeBar() ){
            status.renderLose();

            return false;
        }
        return true;
    }

    /**
     * This method is to update timer
     * @param count int the current timer
     */
    public void updateCount(int count){

        this.count = count;
    }


    /**
     * This method is to render logic when the bird cross the pipe
     */
    protected void cross(){

        int ind = collision.valid();
        if(ind != -1){
            plasticPipes[ind] = null;
            lifeBarLv.loseLife();
        }
        else{
            if(collision.cross(timeScale)){

                status.changeScore();
            }
        }
    }
    public void changeTimeScale(int timeScale){
        this.timeScale = timeScale;
    }

    protected boolean renderLifeBar() {
        return lifeBarLv.render();
    }



    /**
     * This method is to render the logic for bird
     * @return boolean whether the game fails because bird fly out of bound
     */
    public boolean renderBird(){

        if(bird.getPos().y < 0 ||bird.getPos().y > 768){
            status.renderLose();
            return false;
        }
        if (falling == false){
            bird.renderUp();
            this.falling = true;
        }
        else {
            bird.renderDown();
        }
        return true;
    }

    /**
     * This method is to render the logic when the bird flies
     */
    public void flying(){
        this.falling = false;
    }


}
