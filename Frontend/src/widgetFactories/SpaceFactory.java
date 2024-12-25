package widgetFactories;

import javax.swing.*;

public class SpaceFactory {

    public static void addVerticleSpaceForPanel(JPanel formPanel, int height){
        formPanel.add(Box.createVerticalStrut(height));
    }

}
