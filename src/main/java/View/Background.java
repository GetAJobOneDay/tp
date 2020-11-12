package View;

import Models.Ball;
import Models.Character;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Background extends Pane {
    HashMap<KeyCode,Boolean> setOfAction;
    private Image background;
    private ArrayList<Character> arrChar;
    private ArrayList<Score> score;
    private Ball ball;
    public Background(){
        ball = new Ball(150,-100);
        score = new ArrayList<>();
        setOfAction = new HashMap<>();
        arrChar = new ArrayList<>();
        background=new Image(getClass().getClassLoader().getResource("assets/Background.png").toString());
        ImageView iv = new ImageView(background);
        this.arrChar.add(new Character(100,0, KeyCode.A,KeyCode.D,KeyCode.W,KeyCode.K,"player"));
        this.arrChar.add(new Character(650,0, KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP,KeyCode.L,"computer"));
        score.add(new Score(30,330));
        score.add(new Score(740,330));
        this.getChildren().add(iv);
        this.getChildren().add(ball);
        this.getChildren().addAll(score);
        for(Character c:arrChar){
            this.getChildren().add(c);
        }
    }
    public ArrayList<Character> getArrChar(){
        return arrChar;
    }

    public void setAction(KeyCode code) {
        setOfAction.put(code,true);
    }

    public void unset(KeyCode code) {
        setOfAction.put(code,false);
    }
    public boolean isPressed(KeyCode key){
        if(setOfAction.containsKey(KeyCode.O)){
            for(Character c:arrChar){
                c.fallingDown();
                ball.fallDown();
            }
        }
        return setOfAction.getOrDefault(key,false);
    }

    public ArrayList<Score> getSetScore() {
        return score;
    }
    public Ball getBall(){
        return ball;
    }
}
