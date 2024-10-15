import java.awt.*;

public class Vehicle {
    public int x, y, width, height, xVelocity, yVelocity;
    public Color color;

    public Vehicle(int x, int y, int width, int height, int xVelocity, int yVelocity, Color color) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
        this.xVelocity = xVelocity; this.yVelocity = yVelocity;
        this.color = color;
    }

    // Hàm di chuyển xe
    public void move() {
        this.y += yVelocity;
        if(x >= 300 && x <= 900){
            this.x += xVelocity;
        }
    }

    // Hàm vẽ xe lên màn hình
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
