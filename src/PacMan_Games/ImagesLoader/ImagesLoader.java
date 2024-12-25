package PacMan_Games.ImagesLoader;

import java.awt.*;
import java.io.File;
import java.util.logging.*;
import javax.swing.ImageIcon;
import Utils.LoggerUtility;

public class ImagesLoader {

    private static final Logger logger = LoggerUtility.getLogger("ImagesLoader", "ImagesLoader.log");

    private static Image loadImage(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new IllegalArgumentException("Image file not found: " + filePath);
            }
            return new ImageIcon(filePath).getImage();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to load image: " + filePath, e);
            return null;
        }
    }

    // Wall Image
    public static Image loadWallImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/wall/wall.png";
        return loadImage(filePath);
    }

    // Ghost Images
    public static Image loadBlueGhostImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/ghosts/normal ghosts/blueGhost.png";
        return loadImage(filePath);
    }

    public static Image loadOrangeGhostImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/ghosts/normal ghosts/orangeGhost.png";
        return loadImage(filePath);
    }

    public static Image loadRedGhostImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/ghosts/normal ghosts/redGhost.png";
        return loadImage(filePath);
    }

    public static Image loadPinkGhostImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/ghosts/normal ghosts/pinkGhost.png";
        return loadImage(filePath);
    }

    // Pacman 1 Images
    public static Image loadPacMan1UpImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/pacmans/yellow pacman/pacmanUp.png";
        return loadImage(filePath);
    }

    public static Image loadPacMan1DownImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/pacmans/yellow pacman/pacmanDown.png";
        return loadImage(filePath);
    }

    public static Image loadPacMan1LeftImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/pacmans/yellow pacman/pacmanLeft.png";
        return loadImage(filePath);
    }

    public static Image loadPacMan1RightImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/pacmans/yellow pacman/pacmanRight.png";
        return loadImage(filePath);
    }

    // Pacman 2 Images
    public static Image loadPacMan2UpImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/pacmans/red pacman/pacmanUp.png";
        return loadImage(filePath);
    }

    public static Image loadPacMan2DownImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/pacmans/red pacman/pacmanDown.png";
        return loadImage(filePath);
    }

    public static Image loadPacMan2LeftImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/pacmans/red pacman/pacmanLeft.png";
        return loadImage(filePath);
    }

    public static Image loadPacMan2RightImage() {
        String filePath = System.getProperty("user.dir") + "/sprites/pacmans/red pacman/pacmanRight.png";
        return loadImage(filePath);
    }

     // logo Image
     public static Image loadLogo() {
        String filePath = System.getProperty("user.dir") + "/sprites/logo/pacman.png";
        return loadImage(filePath);
    }

    public static Image[] loadRedDyingFrames(int totalFrames) {
        String baseDir = System.getProperty("user.dir") + "/sprites/";
        return loadDyingFrames(baseDir + "pacmans/red pacman/dying_frame_", totalFrames);
    }

    public static Image[] loadYellowDyingFrames(int totalFrames) {
        String baseDir = System.getProperty("user.dir") + "/sprites/";
        return loadDyingFrames(baseDir + "pacmans/yellow pacman/dying_frame_", totalFrames);
    }

    private static Image[] loadDyingFrames(String basePath, int frameCount) {
        Image[] frames = new Image[frameCount];
        for (int i = 0; i < frameCount; i++) {
            try {
                if (i < 9) {
                    frames[i] = loadImage(basePath + i + ".png");
                    if (frames[i] == null) {
                        throw new IllegalArgumentException("Missing frame: " + basePath + i + ".png");
                    }
                } else {
                    frames[i] = null;
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error loading dying frame: " + basePath + i + ".png", e);
                frames[i] = null;
            }
        }
        return frames;
    }
}
