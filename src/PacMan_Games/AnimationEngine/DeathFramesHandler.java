package PacMan_Games.AnimationEngine;
import PacMan_Games.ImagesLoader.ImagesLoader;
import java.awt.*;
public class DeathFramesHandler {

    private static Image[] dyingFramesForYellow;
    private static Image[] dyingFramesForRed;
    private static final int TOTALDEATHFRAMES = 10;

    private static void initRedDyingFrames(){

        dyingFramesForRed = ImagesLoader.loadRedDyingFrames(TOTALDEATHFRAMES);

    }

    private static void initYellowDyingFrames(){

        dyingFramesForYellow = ImagesLoader.loadYellowDyingFrames(TOTALDEATHFRAMES);

    }

    public static Image[] getDyingFrameForYellow(){
        initYellowDyingFrames();
        return dyingFramesForYellow;
    }

    public static Image[] getDyingFrameForRed(){
        initRedDyingFrames();
        return dyingFramesForRed;
    }

    public static int getTotalFramesCount(){
        return TOTALDEATHFRAMES;
    }
}
