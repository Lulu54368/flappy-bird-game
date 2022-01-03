
import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Rectangle;

import java.awt.*;

public class PlasticPipe {
    protected Image img;
    protected int start = 0;
    protected int x = 1024;
    protected int y = 768;
    private DrawOptions rotate;
    protected int pos;
    private int t = 0;
    private Rectangle boxup;
    private Rectangle boxdown;
    protected double height;

    /**
     * This constructor is to build an instance for PlasticPipe
     * @param pos int the position of PlasticPipe
     */
    public PlasticPipe(int pos){
        this.pos = pos;
        img = new Image("./res/level/plasticPipe.png");
        height = ((int)(Math.random()*3)) *200+ 100;
    }

    /**
     * This method is to change the position of Plastic pipe
     * @param pos int the new position
     */
    public void changePos(int pos){
        this.pos = pos;
    }

    /**
     * This method is to draw the plastic pipe
     */
    public void render() {
        rotate = new DrawOptions();
        rotate.setRotation(3.14);
        img.draw(x + img.getWidth()/2 - pos, height - img.getHeight() / 2); // 768/2 - 168/2 - height / 2
        img.drawFromTopLeft(x - pos, height + 168, rotate); //768/2 + 168/2
    }

    /**
     * This method is to get the Rectangle corresponding
     * to the upper pipe
     * @return Rectangle The rectangle corresponding to upper
     * pipe
     */
    public Rectangle getboxup(){
        boxup = new Rectangle(x  - pos, 0, img.getWidth(), height);
        return boxup;
    }

    /**
     * This method is to get the rectangle corresponding to
     * lower pipe
     * @return Rectangle the rectangle corresponding to
     * lower pipe
     */
    public Rectangle getBoxdown(){
        boxdown = new Rectangle(x - pos, height+168, img.getWidth(), img.getHeight());
        return boxdown;
    }

    /**
     * This method is to get the absolute position of the pipe
     * @return int the position of the pipe
     */
    public double getPos(){
        return (x - pos);
    }
    public boolean plastic(){
        return true;
    }

}