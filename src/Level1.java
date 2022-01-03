import bagel.Keys;

public class Level1 extends Level0 {
    private Rock rock;
    private static int last = 0;
    private static boolean carry = false;
    private static boolean shoot = false;
    private boolean pressS;


    /**
     * This constructor creates an instance of class Level1
     * @param count The timer of the game
     * @param pressS Whether the player presses S
     */
    public Level1(int count, Status status, boolean pressS) {
        super(count, status);
        this.pressS = pressS;
        this.lifeBarLv = new LifeBarLv1();
        this.timeScale = 1;
        this.status = status;

    }

    /**
     * This method is to render the logic to define the PlasticPipe
     */
    @Override
    protected void definePlasticPipe() {
        double which = Math.random();
        int time = (int) (100 / Math.pow(1.5, timeScale -1));
        if (count % time == 0 && count > 0) {
            if (which < 0.5) {
                this.plasticPipes[curr % 10] = new PlasticPipe(count * 3);

            } else {
                plasticPipes[curr % 10] = new SteelPipe(count * 3);
            }
            plasticPipes[curr % 10].start = count;
            curr += 1;
        }
    }

    /**
     * This method is to render the logic for level1
     * @return boolean whether the game fails
     */

    public boolean renderLevel1(){
        status.updatePipes(plasticPipes, Math.min(10, curr));
        definePlasticPipe();
        changePos();
        bird = new BirdLevel1();
        ((BirdLevel1)bird).resetParameter();
        collision = new Collision(bird, plasticPipes, Math.min(10, curr));
        cross();
        ((UpdateStatus)status).renderGame(count);

        renderWeapon();

        if(!renderLifeBar()){
            status.renderLose();

            return false;
        }
        return true;
    }

    /**
     * This method is to render the logic for weapon
     */
    public void renderWeapon() {
        int ind;
        if ((count - last) % (int)(1024/ Math.pow(1.5, timeScale-1))  == 0 && !carry) {

            createRock();

        }
        if (rock != null && plasticPipes != null && count >= last) {
            //check whether the bird carry weapon
            if (rock.carryWeapon() == true) {
                carry = true;
            }
            rock.setTimer(count);
            if (carry && !shoot) {
                // render the logic when the weapon is carried by bird
                rock.setPos(bird.getBox().centre().y, bird.getBox().right());
                rock.render(timeScale);
                if (pressS) {
                    // render the logic when the weapon begins to shoot
                    shoot = true;
                    rock.setShootStart();

                    carry = false;
                }

            }
            else if (shoot) {
                ind = rock.shoot(timeScale);
                if (ind == 1){
                    // The weapon goes out of range
                    shoot = false;
                    createRock();
                    last = count  ;
                    isPressSReset();
                }
                else if(ind < 0){
                    // the weapon hit the pipe
                    createRock();
                    last = count;
                    if(ind == -1){
                        status.changeScore();
                    }
                    else{

                    }
                }


            }

            else{
                if(rock.valid() != -1){
                    // The rock is overlap with pipe without shooting
                   createRock();
                   last = count - 1024;
                }
                else{
                    rock.render(timeScale);
                }

            }



        }

    }
    public void createRock(){
        // choose whether it's rock or bomb randomly
        carry = false;
        shoot = false;
        pressS = false;
        double random = Math.random();
        if(random > 0.5){
            rock = new Rock(plasticPipes, bird, count, Math.min(10, curr));
        }
        else{
            rock = new Bomb(plasticPipes, bird, count, Math.min(10, curr));
        }

    }
    public void isPressS(){
        this.pressS = true;
    }
    public void isPressSReset(){
        this.pressS = false;
    }

}