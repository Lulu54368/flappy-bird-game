import bagel.Font;
import bagel.Image;
import bagel.Input;
import bagel.Keys;

public class Status {
    protected String startmessage = "PRESS SPACE TO START";
    protected Font message;
    protected int score = 0;
    protected Font drawscore;
    protected int pipeNum;
    protected Image background;

    protected PlasticPipe[] plasticPipes;

    /**
     * This constructor is to build an instance for class Status
     */
    public Status(){

        background = new Image("./res/level-0/background.png");


        drawscore = new Font("./res/font/slkscr.ttf", 48);
        message = new Font("./res/font/slkscr.ttf", 48);

    }

    /**
     * This method is to update score
     */
    public void changeScore(){
        this.score += 1;
    }

    /**
     * This method is to draw start
     */
    public void renderStart(){
        background.drawFromTopLeft(0,0);

        message.drawString(startmessage, 512 - message.getWidth(startmessage)/2, 384);

    }

    /**
     * This method is to update the Pipes for pipes array
     * @param pipes PlasticPipe[] an array of pipes
     * @param num int the valid number for pipes array
     */
    public void updatePipes(PlasticPipe[] pipes, int num){
        this.pipeNum = num;
        this.plasticPipes = pipes;

    }

    /**
     * This method is to draw the background pipes and score for the game
     * (level 0)
     */
    public void renderGame(){
        background.drawFromTopLeft(0,0);

        drawscore.drawString("SCORE: " + Integer.toString(score), 100 , 100);
        for (int i = 0; i < pipeNum; i++){
            if(plasticPipes[i] != null){
                plasticPipes[i].render();
            }

        }


    }


    /**
     * This method is to draw the pictures when the game wins
     */
    public void renderWin(){
        background.drawFromTopLeft(0,0);

        message.drawString("CONGRATULATIONS!", 512 - message.getWidth("CONGRATULATIONS!")/2, 384);
        drawscore.drawString("FINAL SCORE: " + Integer.toString(score),
                512 - message.getWidth("FINAL SCORE: 1")/2, 384+75);

    }

    /**
     * This method is to draw the scenario when the game lose
     */
    public void renderLose(){

        background.drawFromTopLeft(0,0);

        message.drawString("GAME OVER", 512 - message.getWidth("GAME OVER")/2, 384);
        drawscore.drawString("FINAL SCORE: " + Integer.toString(score),
                512 - message.getWidth("FINAL SCORE: 1")/2, 384+75);

    }
    public void renderLevelUp(){
        background.drawFromTopLeft(0,0);

        message.drawString("LEVEL-UP", 512 - message.getWidth("LEVEL-UP")/2, 384);


    }
}


