package Models;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Character extends Pane {
    Logger logger = LoggerFactory.getLogger(Character.class);
    private boolean ml,mr;
    private KeyCode left,right,up,spx;
    private int x;
    private int y;
    private int xValo,yValo;
    private boolean isFalling;
    private Sprite imgv;
    private String player;
    private KeyCode movement;
    private boolean canJump,isJump;
    private int score=0;

    public Character(int x,int y,KeyCode left,KeyCode right,KeyCode up,KeyCode spx,String player){
        this.mr = false;
        this.ml = false;
        this.player=player;
        this.x=x;
        this.y=y;
        this.isJump=false;
        this.spx=spx;
        this.left=left;
        this.isFalling=true;
        this.right=right;
        this.up = up;
        this.canJump = false;
        this.setTranslateX(x);
        this.setTranslateY(y);
        Image img = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/ss.png")).toString());
        this.imgv = new Sprite(img,6,6,1, 0, 270, 66, 64);
        this.getChildren().add(imgv);
    }
    public void YTransfer(){
        yValo = 5;
        if(isFalling){
            y=y+yValo;
        }else if(isJump){
            y=y-yValo;
        }
        setTranslateY(y);
    }
    public void trace() {
        String action="";
        if(isJump){
            action="jump";
        }else if(mr){
            action="moveRight";
        }else if(ml){
            action="moveLeft";
        }
        logger.info("x:{} y:{} vx:{} vy:{} score:{} action:{}", x, y, xValo, yValo,score,action);
    }
    public void ifReachFloor(){
        if(isFalling && y>=300-64){
            this.isFalling = false;
            this.yValo=0;
            this.canJump = true;
        }
    }
    public void checkHighest(){
        if(y<=195){
            canJump=false;
            isFalling=true;
            isJump=false;
        }
    }
    public void updatePosition(){
        XTransfer();
        YTransfer();
        ifReachFloor();
    }
    public String getPlayer(){
        return this.player;
    }
    public Sprite getImageView(){
        return this.imgv;
    }
    public KeyCode getLeft(){
        return this.left;
    }
    public KeyCode getRight(){
        return this.right;
    }
    public KeyCode getUp(){
        return this.up;
    }

    public void XTransfer() {
        setTranslateX(x);
        xValo = 5;
        if(mr){
            x=x+xValo;
        }else if(ml){
            x=x-xValo;
        }

    }

    public void moveLeft() {
        this.mr = false;
        this.ml = true;
    }

    public void moveright() {
        this.mr = true;
        this.ml=  false;
    }

    public void stop() {
        this.mr = false;
        this.ml = false;
    }

    public void jump() {
        canJump = true;
        isJump=true;
    }
    public void dive(){
        jump();
        YTransfer();
        moveright();
        XTransfer();
        //halfY();
    }
    private void halfY(){
        if(y<=220){
            canJump=false;
            isFalling=true;
            isJump=false;
        }
    }

    public KeyCode getSpx() {
        return spx;
    }
    public void setScore(int x){
        this.score=x;
    }
    public int getScore() {
        return this.score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXvalo() {
        return xValo;
    }
    public boolean isFalling(){
        return isFalling;
    }
}
