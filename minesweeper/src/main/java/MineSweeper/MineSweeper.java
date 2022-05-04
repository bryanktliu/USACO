package MineSweeper;

import MineSweeper.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public final class MineSweeper {

    public static void main(String[] args) {
        JFrame frame = new MainFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        frameSize.height = Math.min(frameSize.height, screenSize.height);
        frameSize.width = Math.min(frameSize.width, screenSize.width);
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
