import bagel.Image;
public class LifeBarLv0 {
    protected int num = 3;
    protected int life = 3;
    private Image fullLife;
    private Image noLife;
    private int xStart;
    private int y;

    /**
     * This constructor is to build an instance for LifeBarLv0
     */
    public LifeBarLv0(){
        fullLife = new Image("./res/level/fullLife.png");
        noLife = new Image("./res/level/noLife.png");
        xStart =100;
        y = 15;

    }

    /**
     * This method is to deduct the life when the bird loses life
     */
    public void loseLife(){
        this.life -= 1;
    }

    /**
     * This method is to render the logic for life bar and check
     * whether the game fails
     * @return boolean whether the game fails or not
     */
    public boolean render(){
        int i;

        if(life == 0){
            return false;
        }
        for (i = 0; i < num; i ++){

            if (i < life){
                fullLife.drawFromTopLeft(xStart + 50* i, y);
            }
            else{
                noLife.drawFromTopLeft(xStart + i * 50, y);
            }
        }
        return true;
    }



}
