package controller;

import Models.Ball;
import Models.Character;
import View.Background;
import View.Score;
import javafx.application.Platform;

import java.util.ArrayList;

public class GameLoop implements Runnable{
    private Ball ball;
    private float interval=1000.0f/24;
    private Background background;
    private boolean running;
    public GameLoop(Background background){
        this.background = background;
        this.running = true;
        this.ball =background.getBall();
    }
    public GameLoop(Ball ball){this.ball=ball;}
    public void update(){
        ArrayList<Character> c = background.getArrChar();
        for(Character ch:c){
            if(background.isPressed(ch.getLeft())){
                ch.moveLeft();
                ch.XTransfer();
                ch.getImageView().tick();
            }
            if( background.isPressed(ch.getRight())){
                ch.moveright();
                ch.XTransfer();
                ch.getImageView().tick();
            }
            if(background.isPressed(ch.getUp())){
                ch.jump();
                ch.YTransfer();
                ch.getImageView().tock();
            }
            if(!background.isPressed(ch.getLeft()) && !background.isPressed(ch.getRight())){
                ch.stop();
            }
            if(background.isPressed(ch.getSpx())){
                ch.dive();
                ch.getImageView().ticjtock();
            }
            //ch.trace();
        }
    }
    public void gmaeplay(){
        ArrayList<Character> c = background.getArrChar();
        ball.getSprite().tick();
        if(ball.reachGround() || ball.gg){
            System.out.println("ssss");
            if(ball.getx()<400){
                int temp =c.get(1).getScore();
                c.get(1).setScore(++temp);
            }else{
                int temp =c.get(0).getScore();
                c.get(0).setScore(++temp);
            }
            ball.response();
        }
        for(Character cc:c){
            if(cc.getBoundsInParent().intersects(ball.getBoundsInParent())){
                ball.bounce(cc);
            }
        }
    }
    @Override
    public void run() {
        ArrayList<Character> arr = background.getArrChar();
        while (running){
            gmaeplay();
            ball.YTransfer();
            ball.reachGround();
            float time = System.currentTimeMillis();
            for(Character c:arr){
                c.updatePosition();
                c.checkHighest();
                update();
            }
            updateScore(background.getSetScore(),background.getArrChar());
            time = System.currentTimeMillis()-time;
            try{
                Thread.sleep((int)(interval-time));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void updateScore(ArrayList<Score> score,ArrayList<Character> c){
        Platform.runLater(()->{
            for(int i=0;i<score.size();i++){
                score.get(i).setPoint(c.get(i).getScore());
            }
        });
    }
}
