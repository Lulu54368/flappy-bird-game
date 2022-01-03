import bagel.Image;

public class UpdateStatus extends Status{


    /**
     * This constructor is to create an instance for UpdateStatus
     */
    public UpdateStatus() {
        super();
        background = new Image("./res/level-1/background.png");
        score = 0;
    }

    /**
     * This method is to draw the background, pipes and score for level 1
     */

    public void renderGame(int count){
        boolean isFlame;
        background.drawFromTopLeft(0,0);

        drawscore.drawString("SCORE: " + Integer.toString(score), 100 , 100);
        for (int i = 0; i < pipeNum; i++){
            if(plasticPipes[i] == null){
                continue;
            }
            plasticPipes[i].render();

            isFlame = (count % 23 == 1)||(count % 23 == 2) ||
                    (count % 23 == 3);
            if (plasticPipes[i] instanceof SteelPipe && isFlame){
                SteelPipe steelPipe = (SteelPipe) plasticPipes[i];
                steelPipe.renderFlame();


            }
        }


    }
    public void renderStart(){
        background.drawFromTopLeft(0,0);

        message.drawString(startmessage, 512 - message.getWidth(startmessage)/2, 384);
        message.drawString("PRESS 'S' TO SHOOT",
                512 - message.getWidth("PRESS 'S' TO SHOOT")/2, 384+68);

    }
}
