package examples2;

/**
 * Created by herpderp on 27/05/2015.
 */

/**
 * The game manager is an important part of most games. A game manager
 * is a class that should contain data relating to gameplay; including,
 * but not limited to keeping track of score, credits/currency, player health,
 * and other general gameplay information. In this topic, we're going to take
 * a look at a game manager class to gain an understanding of how they work into our game structure.
 */
public class GameManagerExample {
    /** The GameManager, will be a singleton implementation.
     *
     * Obviously depending on the game, the GameManager will have different tasks.
     *  This implementation is rather simple, but as the game becomes more complex, this call
     *  will become rather large.
     * */
    private static GameManagerExample instance;
    private int currentScore = 0;
    private int birdCount = 0;
    private int enemyCount = 0;

    public static final int INITIAL_BIRDCOUNT = 0;
    public static final int INITIAL_CURRENTSCORE = 0;
    public static final int INITIAL_ENEMYCOUNT = 0;

    public int getEnemyCount(){
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount){
        this.enemyCount = enemyCount;
    }

    public int getBirdCount(){
        return birdCount;
    }

    public void setBirdCount(int birdCount){
        this.birdCount = birdCount;
    }

    /**
     * We need the resetGame() method because a singleton implementations state,
     * is  being persisted through the switching of the scenes and all of that stuff. .
     */
    public void resetGame(){
        currentScore = INITIAL_BIRDCOUNT;
        birdCount = INITIAL_CURRENTSCORE;
        enemyCount= INITIAL_ENEMYCOUNT;
    }

    public int getCurrentScore(){
        return currentScore;
    }

    public void setCurrentScore(int currentScore){
        this.currentScore = currentScore;
    }

    public void incrementScore(int incrementBy){
        currentScore += incrementBy;
    }

    public void decrementScore(int decrementBy){
        currentScore -= decrementBy;
    }

    private GameManagerExample() throws IllegalAccessException{
        //defend vs reflection.
        if(instance !=null) {
            throw new IllegalAccessException();
        }
    }

    public static GameManagerExample getInstance(){
        if(instance == null){
            try {
                instance = new GameManagerExample();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }



}
