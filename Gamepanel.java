import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private int playerX = 300;
    private int playerY = 600;
    private final int playerWidth = 100;
    private final int playerHeight = 200;
    private final int roadWidth = 600;
    private final int laneWidth = 200;

    private int score = 0;
    private boolean gameOver = false;

    private Map map;
    private Timer timer;

    
    private boolean moveLeft = false;
    private boolean moveRight = false;

    public GamePanel() {
        map = new Map(roadWidth, laneWidth);
        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!gameOver) {
            // Vẽ đường
            g.setColor(Color.GRAY);
            g.fillRect(300, 0, roadWidth, getHeight());

            // Vẽ xe của người chơi
            g.setColor(Color.BLUE);
            g.fillRect(playerX, playerY, playerWidth, playerHeight);

            // Vẽ các xe cản đường
            map.drawVehicles(g);

            // Vẽ điểm số
            g.setColor(Color.BLACK);
            g.drawString("Score: " + score, 10, 20);
        } else {
            g.setColor(Color.BLACK);
            g.drawString("Game Over! Final Score: " + score, 300, 250);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // Di chuyển các xe cản đường và kiểm tra va chạm
            map.moveVehicles(playerX, playerY, playerWidth, playerHeight);

            if (moveLeft && playerX > 300) {
                playerX -= 5;
            }
            if (moveRight && playerX < 900 - playerWidth) {
                playerX += 5;
            }

            // Kiểm tra va chạm
            if (map.checkCollision(playerX, playerY, playerWidth, playerHeight)) {
                gameOver = true;
                timer.stop();
            }

            repaint();
        }
    }

    // Hàm điều khiển di chuyển của người chơi
    public void movePlayer(int direction) {
        if (direction == -1 && playerX > 300) {
            playerX -= laneWidth;
        }

        if (direction == 1 && playerX < 900 - playerWidth) {
            playerX += laneWidth;
        }
    }

    public void movePlayerLeft(boolean isMoving) {
        this.moveLeft = isMoving;
    }

    public void movePlayerRight(boolean isMoving) {
        this.moveRight = isMoving;
    }
}
