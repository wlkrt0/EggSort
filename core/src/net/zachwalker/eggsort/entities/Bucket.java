package net.zachwalker.eggsort.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import net.zachwalker.eggsort.util.Assets;
import net.zachwalker.eggsort.util.DrawingHelpers;
import net.zachwalker.eggsort.util.Enums;

public class Bucket {

    private Vector2 position;
    private int caughtEggs;
    private int caughtEggsGoal;
    private Enums.EggType eggType;

    public Bucket(Vector2 position, Enums.EggType eggType, int caughtEggsGoal) {
        this.position = new Vector2(position);
        this.eggType = eggType;
        this.caughtEggsGoal = caughtEggsGoal;
    }

    //no update method on the Buckets, since they don't move and are updated by Eggs

    public void render(SpriteBatch batch) {
        switch(eggType) {
            case WHITE:
                DrawingHelpers.drawSprite(batch, Assets.singleton.images.bucketLeft.get(caughtEggs), position);
                break;
            case BROWN:
                DrawingHelpers.drawSprite(batch, Assets.singleton.images.bucketMiddle.get(caughtEggs), position);
                break;
            case CHICK:
                DrawingHelpers.drawSprite(batch, Assets.singleton.images.bucketRight.get(caughtEggs), position);
                break;
        }
    }

    /** Increments this bucket's eggs caught counter. Returns true if it's now full. */
    public boolean caughtEgg() {
        if (caughtEggs >= caughtEggsGoal - 1) {
            caughtEggs = caughtEggsGoal;
            return true;
        } else {
            caughtEggs += 1;
            return false;
        }
    }

    /** Decrements this bucket's eggs caught. Returns true if it's empty (game over). */
    public boolean missedBall() {
        if (caughtEggs == 0) {
            return true;
        } else {
            caughtEggs -= 1;
            return false;
        }
    }

    public boolean isFull() {
        return (caughtEggs >= caughtEggsGoal);
    }

    public void reset(int caughtEggsGoal) {
        caughtEggs = 0;
        this.caughtEggsGoal = caughtEggsGoal;
    }
}
