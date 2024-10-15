import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Map {
    private ArrayList<Vehicle> vehicles; // Danh sách các xe cản đường
    private Random random;
    private int roadWidth;
    private int laneWidth;

    public Map(int roadWidth, int laneWidth) {
        this.vehicles = new ArrayList<>();
        this.random = new Random();
        this.roadWidth = roadWidth;
        this.laneWidth = laneWidth;
        spawnVehicle();
    }

    // Tạo xe ngẫu nhiên
    public void spawnVehicle() {
        int laneX = 300 + random.nextInt(roadWidth / laneWidth) * laneWidth;
        int vehicleY = -100;

        // Loại xe
        int type = random.nextInt(3); // 3 loại xe khác nhau
        int width, height, xVelocity, yVelocity;
        Color color; // Thay bằng ảnh sau

        switch (type) {
            case 0: // Xe đi ngang
            laneX = 300;
                width = 160;
                height = 80;
                xVelocity = 1;
                yVelocity = 2;
                color = Color.RED;
                break;
            case 1:
                width = 80;
                height = 160;
                xVelocity = 0;
                yVelocity = 5;
                color = Color.GREEN;
                break;
            case 2:
                width = 120;
                height = 240;
                xVelocity = 0;
                yVelocity = 4;
                color = Color.ORANGE;
                break;
            default:
                width = 80;
                height = 160;
                xVelocity = 0;
                yVelocity = 4;
                color = Color.RED;
        }

        vehicles.add(new Vehicle(laneX, vehicleY, width, height, xVelocity, yVelocity, color));
    }

    // Di chuyển
    public void moveVehicles(int playerX, int playerY, int playerWidth, int playerHeight) {
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            vehicle.move();

            // Xóa xe đã ra khỏi màn hình
            if (vehicle.y > 900) {
                vehicles.remove(i);
                spawnVehicle();
            }
        }
    }

    // Kiểm tra va chạm giữa xe cản đường và người chơi
    public boolean checkCollision(int playerX, int playerY, int playerWidth, int playerHeight) {
        for (Vehicle vehicle : vehicles) {
            Rectangle playerRect = new Rectangle(playerX, playerY, playerWidth, playerHeight);
            Rectangle vehicleRect = new Rectangle(vehicle.x, vehicle.y, vehicle.width, vehicle.height);

            if (playerRect.intersects(vehicleRect)) {
                return true;
            }
        }
        return false;
    }

    // Vẽ tất cả các xe cản đường
    public void drawVehicles(Graphics g) {
        for (Vehicle vehicle : vehicles) {
            vehicle.draw(g);
        }
    }
}
