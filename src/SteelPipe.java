import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Rectangle;

public class SteelPipe extends PlasticPipe{
    Image flame;
    private DrawOptions flameRotate;
    private double flamePos = 0;
    private Rectangle fireboxup;
    private Rectangle fireboxdown;
    /**
     * This constructor is to build an instance of SteelPipe
     * @param v0 int the position of the steel pipe
     */
    public SteelPipe(int v0) {
        super(v0);
        img = new Image("./res/level-1/steelPipe.png");

        flame = new Image("./res/level-1/flame.png");
        height = (Math.random()* 400) + 100;

    }

    /**
     * This method is to draw flame
     */
    public void renderFlame() {
        flameRotate = new DrawOptions();
        flameRotate.setRotation(3.14);
        flame.draw(x + flame.getWidth()/2 - pos, height+10); // 768/2 - 168/2 - height / 2
        flame.draw(x + flame.getWidth()/2 - pos, height + 168-10 , flameRotate); //768/2 + 168/2
        flamePos = x + flame.getWidth() - pos;
    }

    /**
     * This method is to get the position for flame
     * @return double the position for flame
     */
    public double getFlamePos(){
        return this.flamePos;
    }

    /**
     * This method is to check whether is plastic
     * @return boolean false since it's not plastic
     */
    public boolean plastic(){
        return false;
    }

    public Rectangle getfireboxup(){
        fireboxup = new Rectangle(x - pos, height-flame.getHeight()/2+10, flame.getWidth(), flame.getHeight());
        return fireboxdown;
    }

    public Rectangle getfireboxdown(){
        fireboxdown = new Rectangle(x - pos, height+168-flame.getHeight()/2-10, flame.getWidth(), flame.getHeight());
        return fireboxdown;
    }
}
