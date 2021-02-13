package org.academiadecodigo.timemaravilha.sprite;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.timemaravilha.game.Game;
import org.academiadecodigo.timemaravilha.game.PlayerType;
import org.academiadecodigo.timemaravilha.entities.EntityType;
import org.academiadecodigo.timemaravilha.grid.position.GridPosition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The SpriteManager
 *
 * Responsible for changing the sprites in our entities.
 */
public class SpriteManager {

    /**
     * The attributes
     */
    private Picture[][] spriteArr; //Our sprites
    private GridPosition position; //The position (has the sprite)
    private boolean flipped; //If it's supposed to be flipped or not
    private int index;
    private Game.Timer timer;

    public SpriteManager(EntityType type, GridPosition position){
        init(type);
        this.position = position;
        timer = new Game.Timer(125);
    }

    public void loadNextFrame(int state){
        if(timer.timerOver()) {
            position.loadNextFrame(spriteArr[state][index]);
            index = (index + 1) % spriteArr[state].length;
            timer.reset();
        }
    }

    public boolean isFlipped(){
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        for(Picture[] picArr: spriteArr){
            for(Picture pic : picArr){
                pic.grow(-pic.getWidth(),0);
            }
        }
        this.flipped = flipped;
    }

    private void init(EntityType type){
        int i = 0;
        int j = 0;
        String[][] aux = SpriteMap.map.get(type);
        spriteArr = new Picture[aux.length][];
        for(String[] s: aux){
            spriteArr[i] = new Picture[aux[i].length];
            for(String name : s){
                if(name != null)
                    spriteArr[i][j] = new Picture(0,0,name);
                if(type == EntityType.PLAYER)
                    spriteArr[i][j].grow(10,10);
                j++;
            }
            i++;
            j = 0;
        }
    }

    public static class SpriteMap{

        private static final String COVIDINHOF = "sprites/covidinho/follow/";
        private static final String COVIDINHOP = "sprites/covidinho/patrolling/";
        private static final String COVIDINHOR = "sprites/covidinho/random/";
        private static final String ANDRE = "sprites/player/andre/";
        private static final String RENATA = "sprites/player/renata/";
        private static final String PAULO = "sprites/player/paulo/";
        private static final String MASK = "sprites/Powerups/Mask.png";
        private static final String POWERUP = "sprites/Powerups/PowerUP.png";
        private static final String VACCINE = "sprites/Powerups/Vaccine.png";

        public static final Map<EntityType, String[][]> map = new HashMap<>();
        private static SpriteMap instance;
        private boolean playerSet;

        private SpriteMap(){
            String[][] arr = new String[2][3];
            addInArr(arr,3,COVIDINHOF+"covidinhoFollow");
            Arrays.fill(arr[1], COVIDINHOF + "Mask.png");
            map.put(EntityType.COVIDINHOTARGET,arr);

            arr = new String[2][7];
            addInArr(arr,7,COVIDINHOP+"covidinhoRandom");
            Arrays.fill(arr[1], COVIDINHOP + "Mask.png");
            map.put(EntityType.COVIDINHOPATROLLING, arr);

            arr = new String[2][8];
            addInArr(arr,8,COVIDINHOR+"covidinhoStatic");
            Arrays.fill(arr[1],COVIDINHOR+ "Mask.png");
            map.put(EntityType.COVIDINHOSIMPLES,arr);

            arr = new String[1][1];
            arr[0][0] = MASK;
            map.put(EntityType.MASK,arr);

            arr = new String[1][1];
            arr[0][0] = POWERUP;
            map.put(EntityType.IMMUNITY,arr);

            arr = new String[1][1];
            arr[0][0] = VACCINE;
            map.put(EntityType.VACCINE,arr);
        }

        public static SpriteMap getInstance() {
            if(instance == null)
                instance = new SpriteMap();
            return instance;
        }

        public void setPlayer(PlayerType type){
            String[][] arr = new String[2][8];
            switch(type) {
                case ANDRE:
                    addInArrP(arr,ANDRE+"andre");
                    break;
                case RENATA:
                    addInArrP(arr,RENATA+"renata");
                    break;
                case PAULO:
                    addInArrP(arr,PAULO+"paulo");
                    break;
            }
            map.put(EntityType.PLAYER,arr);
            playerSet = true;
        }

        public boolean isPlayerSet() {
            return playerSet;
        }

        private void addInArr(String[][] arr, int max, String name){
            for(int i = 1; i <= max; i++){
                arr[0][i-1] = name+i+".png";
            }
        }

        private void addInArrP(String[][] arr, String name){
            for(int i = 0; i < arr.length; i++){
                for(int j = 1; j <= arr[i].length; j++)
                    arr[i][j-1] = i==0?name+j+".png":name+"M"+j+".png";
            }
        }

        public void reset(){
            map.remove(EntityType.PLAYER);
            playerSet = false;
        }

    }

}
