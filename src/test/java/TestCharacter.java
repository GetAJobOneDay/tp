import Models.Ball;
import Models.Character;
import controller.GameLoop;
import de.saxsys.mvvmfx.testingutils.jfxrunner.JfxRunner;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JfxRunner.class)
public class TestCharacter {
    Character character;

    @Before
    public void setup() {
        character = new Character(100, 0, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.K, "player");
    }

    @Test
    public void characterMustGoRightAfterClickRight() {
        character = new Character(100, 236, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.K, "player");
        character.moveright();
        character.XTransfer();
        assertEquals("character must stand at 105", character.getX(), 105);
    }

    @Test
    public void characterMustGoLeftAfterClickLeft() {
        character = new Character(100, 236, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.K, "player");
        character.moveLeft();
        character.XTransfer();
        assertEquals("character must stand at 95", character.getX(), 95);
    }

    @Test
    public void characterMustJumpAfterClickJump() {
        character = new Character(100, 236, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.K, "player");
        character.ifReachFloor();
        character.jump();
        character.YTransfer();
        assertEquals("character must stand at 231", character.getY(), 231);
    }

    @Test
    public void scoreMustIncreaseOneAfterDrop() {
        Ball ball = new Ball(150, 260);
        if (ball.reachGround() || ball.gg) {
            if (ball.getx() < 400) {
                int temp = character.getScore();
                character.setScore(++temp);
            }
            assertEquals("score must be 1", 1, character.getScore());
        }
    }
    @Test
    public void BallMustBounceAfterHitCharacter(){
        character = new Character(100, 236, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.K, "player");
        Ball ball = new Ball(150,236);
        ball.bounce(character);
        assertEquals("y=136",136,ball.gety());
        assertEquals("x=185",180,ball.getx());
    }
}
