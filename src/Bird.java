import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Bird {

    private Point initial_pos = new Point(200, 350);
    private Point pos;
    protected static double h = 300;
    protected static double v0 = 0;
    protected static double vt;
    private boolean down;
    protected Image imgUp;
    protected Image imgDown;
    private Rectangle birdBox;


    public static final double GRAVITY = 0.4;
    private double fallVelocity = 0;

    /**
     * This construct is to accept the current level and
     * construct an instance of class Bird

     */
    public Bird (){
        imgUp  = new Image ("./res/level-0/birdWingUp.png");
        imgDown  = new Image ("./res/level-0/birdWingDown.png");


    }

    /**
     * This method is to draw the image when the bird flies
     */
    public void renderUp(){

        down = false;
        count_location();
        imgUp.draw(initial_pos.x, h);
    }

    /**
     * This method is to draw the image when the bird falls down
     */
    public void renderDown(){

        down = true;
        count_location();
        imgDown.draw(200, h);
    }

    /**
     * This method is to calculate the velocity the bird falls
     * @return double This returns the velocity the bird falls
     */
    private double fallVelocity(){
        v0 = vt;
        if (v0 + GRAVITY <= 10){
            vt = v0 + GRAVITY;
            fallVelocity = vt;
            return (vt);
        }
        else{
            vt = 10;
            fallVelocity = 10;
            return 10.0;
        }
    }

    /**
     * This method is to calculate the velocity when the bird flies
     * @return double This return the velocity the bird flies
     */
    private double riseVelocity(){
        v0 = vt;
        vt = fallVelocity - 6 + GRAVITY ;
        fallVelocity += GRAVITY;
        return (vt);

    }

    /**
     * This method is to count the height of the bird
     * @return double This returns the height of the bird
     */
    public double count_location(){
        if(down){
            fallVelocity();
        }
        else{
            riseVelocity();
        }
        h += (v0 + vt)/2;

        return h;
    }

    /**
     * This method is to get the current position of the bird
     * @return Point This returns the position of the bird
     */
    public Point getPos(){
        pos = new Point(initial_pos.x, h);
        return pos;

    }

    /**
     * This method is to get the box of the bird image
     * @return Rectangle Return the Rectangle of the bird image
     */
    public Rectangle getBox(){

        birdBox = new Rectangle(initial_pos.x - imgUp.getWidth()/2, h- imgUp.getHeight()/2, imgUp.getWidth(), imgUp.getHeight());
        return birdBox;
    }


}
