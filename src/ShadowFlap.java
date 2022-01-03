import bagel.*;
import java.lang.*;
/**
 * Skeleton Code for SWEN20003 Project 2, Semester 2, 2021
 *
 * Please filling your name below
 * @author: LuYuntao
 */
public class ShadowFlap extends AbstractGame {


    private int status = 0;
    private int count = 0;

    private Level0 level0;
    private Level1 level1;

    private int timeScale = 1;
    private Status currstatus;
    int stayTime = 0;
    /**
     *
     */
    public ShadowFlap() {
        super(1024, 768);
        currstatus = new Status();
        level0 = new Level0(count, currstatus);



    }

    /**
     * This method is to render the logic for update
     * @param input Input
     */
    @Override
    protected void update(Input input) {
        currstatus.renderStart();
        if (input.isDown(Keys.SPACE) == true && status == 0 ){
            status = 1;
        }
        if(status == 1){

            count +=1;
            level0.updateCount(count);
            level0.renderLevel0();
            level0.renderBird();
            if(currstatus.score == 10 && stayTime <20){
                currstatus.renderLevelUp();
                stayTime += 1;

            }
            if(stayTime == 20){
                 currstatus = new UpdateStatus();
                 level1 = new Level1(count,currstatus, false);
                 currstatus.renderStart();
                if (input.isDown(Keys.SPACE) == true && status == 1){
                    status = 2;
                }
                 return;
            }
            if(input.isDown(Keys.SPACE)){
                level0.flying();
            }
            if(input.wasPressed(Keys.L) && timeScale >= 1 && timeScale < 5){

                timeScale += 1;
                level0.changeTimeScale(timeScale);

            }
            if(input.wasPressed(Keys.K) && timeScale > 1 && timeScale <= 5){
                timeScale -= 1;
                level0.changeTimeScale(timeScale);

            }


        }
        else if (status == 2){
            count +=1;
            level1.updateCount(count);
            level1.renderLevel1();

            level1.renderBird();

            if(input.isDown(Keys.SPACE)){
                level1.flying();
            }
            if(input.isDown(Keys.S)){
                level1.isPressS();

            }
            if(input.wasPressed(Keys.L) && timeScale >= 1 && timeScale < 5){
                timeScale += 1;
                level1.changeTimeScale(timeScale);

            }
            if(input.wasPressed(Keys.K) && timeScale > 1 && timeScale <= 5){
                timeScale -= 1;
                level1.changeTimeScale(timeScale);

            }
            if(currstatus.score == 30){
                status = 3;
            }

        }
        else if (status == 3){
           currstatus.renderWin();
        }



    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowFlap game = new ShadowFlap();
        game.run();
    }

    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed.
     */


}
