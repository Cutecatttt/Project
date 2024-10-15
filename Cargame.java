import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CarGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chạy đi chờ chi");
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Đặt Game toàn màn hình
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        if (device.isFullScreenSupported()) {
            device.setFullScreenWindow(frame); 
        } else {
            frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
            frame.setVisible(true);
        }

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    gamePanel.movePlayerLeft(true);
                } else if (key == KeyEvent.VK_RIGHT) {
                    gamePanel.movePlayerRight(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    gamePanel.movePlayerLeft(false);
                } else if (key == KeyEvent.VK_RIGHT) {
                    gamePanel.movePlayerRight(false);
                }
            }
        });
    }
}
