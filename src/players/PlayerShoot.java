package players;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import inputs.InputManager;
import utils.Audio;

public class PlayerShoot {
    private boolean shootLock;
    FrameCounter frameCounter = new FrameCounter(50);

    void run(Player player) {
        if (InputManager.instance.xPressed && !shootLock) {
            Audio.loadSound("images/audio/knife-throw-sound-effect-051395302_prev.m4a");
            Audio.playMedia("images/audio/knife-throw-sound-effect-051395302_prev.m4a");
            Audio.initialize();
            Vector2D bulletPosition = player.position.subtract(-40, -10);

            PlayerBullet newBullet = GameObject.recycle((int)bulletPosition.x, (int)bulletPosition.y, PlayerBullet.class);
            newBullet.isFaceLeft = player.isFaceLeft;

            shootLock = true;
        }

        if (shootLock) {
            frameCounter.run();
            if (frameCounter.expired) {
                frameCounter.reset();
                shootLock = false;
            }
        }
    }
}