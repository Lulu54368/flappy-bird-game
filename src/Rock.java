import bagel.util.Rectangle;
import bagel.util.Point;
import bagel.Image;
public class Rock implements Overlap {
    private PlasticPipe[] pipes;
    private Bird bird;
    public double height;

    private int start;
    private int num;
    private Rectangle upbox;
    private Rectangle downbox;
    private double x = 1024;
    private int timer;
    protected Image img;
    private Rectangle box;

    private int shootStart;
    protected int timeLimit = 25;

    /**
     * This constructor is to build an instance for Rock
     * @param pipes PlasticPipe[] an array of pipes
     * @param bird Bird an instance of Bird
     * @param start int start time
     * @param num int the valid number of pipes
     */
    public Rock(PlasticPipe[] pipes, Bird bird, int start, int num) {
        this.pipes = pipes;
        this.bird = bird;
        this.start = start;
        this.num = num;
        this.shootStart = 0;
        img = new Image("res/level-1/rock.png");
        height = Math.random() * 400 + 100;


    }

    /**
     * This method is to get the timer
     * @param count int the timer of the game
     */
    public void setTimer(int count){
        timer = count;
    }

    /**
     * This method is to draw the weapon
     * @param timeScale int the timeScale applied
     */
    public void render(int timeScale){
        img.draw(x, height);
        x -= 3* Math.pow(1.5, timeScale-1);
    }


    /**
     * This method is to check whether the weapon is overlapped with
     * the pipes return the index of the pipe if overlapped
     * @return int -1 if not overlapped, index for the
     * overlapped pipe
     *
     */
    public int  valid(){
        box = new Rectangle(x - img.getWidth()/2, height + img.getHeight()/2, img.getWidth(), img.getHeight());
        for(int i = 0; i < num; i++){
            if(pipes[i] == null){
                continue;
            }
            upbox =  pipes[i].getboxup();
            downbox = pipes[i].getBoxdown();

            if(box.intersects(upbox) || box.intersects(downbox)){

                return i;
            }

        }
        return -1;
    }

    /**
     * THis method is to check whether the weapon is
     * carried by bird
     * @return boolean whether the bird carry the weapon
     */
    public boolean carryWeapon(){
        box = new Rectangle(x - img.getWidth()/2, height + img.getHeight()/2, img.getWidth(), img.getHeight());
        if (bird.getBox().intersects(box)){

           return true;

        }
        else{

            return false;
        }

    }

    /**
     * This method is to set the position of the weapon
     * @param height double the height of the weapon
     * @param x the position of the weapon
     */
    public void setPos(double height, double x){
        this.height = height;
        this.x = x;
    }

    /**
     * This method is to render the logic for shooting
     * @param timeScale int The timeScale applied
     * @return int 0 if out of range, -1 if any pipe
     * is shot, 1 for other cases
     */
    public int shoot(int timeScale) {

        if (timer - shootStart < timeLimit) {
            this.x = 5* Math.pow(1.5, timeScale-1) + this.x;
            render(timeScale);
            int ind = valid();
            if (ind == -1) {
                return 0;
            }
            else {
                if (hitPipe(pipes[ind])) {
                    pipes[ind] = null;
                    return -1;
                }
                return -2;

            }
        }
        else {
            return 1;
        }
    }


    /**
     * This method is to set start time of shooting
     */
    public void setShootStart(){
        this.shootStart = timer;
    }

    /**
     * This method is to judge whether the pipe is hit
     * @param pipe PlasticPipe the involved pipe
     * @return Boolean true if it's hit false otherwise
     */
    public boolean hitPipe(PlasticPipe pipe){
        if (pipe.plastic()) {

            return true;
        }

        return false;
    }
}
