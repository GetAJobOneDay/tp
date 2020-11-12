package Models;

import View.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

public class Ball extends Pane {
    public boolean gg;
    private int yValo;
    private boolean isFalling,gotHit;
    private int x,y;
    private Sprite sprite;
    public Ball(int x,int y){
        this.x=x;
        this.y =y;
        isFalling=true;
        gotHit=false;
        gg=false;
        this.setTranslateX(x);
        this.setTranslateY(y);
        Image image= new Image(getClass().getClassLoader().getResource("assets/ss.png").toString());
        sprite = new Sprite(image,5,5,1,85,155,43,44);
        this.getChildren().add(sprite);
    }
    public Sprite getSprite(){
        return this.sprite;
    }
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    public void YTransfer(){
        setTranslateY(y);
        yValo = 5;
        if(isFalling){
            y=y+yValo;
        }else if(gotHit){
            y=y-yValo;
        }
    }
    public boolean reachGround(){
        if(isFalling && y>=300-44){
            this.isFalling = false;
            this.yValo=0;
            System.out.println(x+" "+y);
            this.gg=true;
            return true;
        }
        return false;
    }
    public void bounce(Character cc){
        y=y-100;
        setTranslateY(y);
            if(cc.getPlayer().equals("player")){
                if(cc.action=="jump"){
                    System.out.println("here");
                    x=x+60;
                }else{
                    x=x+30;
                }

            }else{
                if(cc.getXvalo()!=0 && cc.isFalling()){
                    x=x-60;
                }else{
                    x=x-30;
                }
            }

        setTranslateX(x);

    }

    public void response() {
        Random random = new Random();
        this.x=random.nextInt(750);
        this.y =-100;
        isFalling=true;
        gg= false;
        gotHit=false;
        this.setTranslateX(x);
        this.setTranslateY(y);
    }
    private void reachSky(){
        if(isFalling && y<=350){
            isFalling=false;
        }
    }

    public void fallDown() {
            isFalling=true;
    }
}
