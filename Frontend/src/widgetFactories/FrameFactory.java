package widgetFactories;

import javax.swing.*;
import java.awt.*;

public class FrameFactory {

    public static JFrame getSplashScreenFrame(){
        JFrame frame = getFrame("Splash Screen", 1280, 656, JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);
        return frame;
    }

    public static JFrame getColoredFrame(String title, int width, int height, Color color){

        JFrame frame = getFrame(title,width,height, JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(color);

        return frame;
    }

    public static JFrame getFrame(String title, int width, int height, int onClose){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(onClose);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        return frame;
    }
}
