import java.awt.*;
import java.awt.Font;

public class Drawing {
    //sets up the canvas, draws the empty grids - DONE
    public static void setUp () {
        StdDraw.clear();
        StdDraw.setXscale(0, 22);
        StdDraw.setYscale(-11, 11);
        StdDraw.picture(11,0,"ocean.jpg");
        StdDraw.setPenColor(Color.white);
        StdDraw.filledRectangle(5.5,5.5,5,5);
        StdDraw.filledRectangle(5.5,-5.5,5,5);
        StdDraw.setPenColor(Color.black);
        for (double i = 0.5; i <= 10.5; i++) {
            StdDraw.line(i,0.5,i,10.5);
            StdDraw.line(i,-0.5,i,-10.5);
        }
        for (double j = -10.5; j <= 10.5; j++) {
            StdDraw.line(0.5, j, 10.5,j);
        }
    }
    //draws a pair of boards - DONE
    public static void Boards(MyGrid personal, MyGrid attack, int number) {
        Font font = new Font("Arial", Font.BOLD, 15);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(16, 5, "Player " + number + ": Personal Board");
        StdDraw.text(16, -5, "Player " + number + ": Attack Board");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //draws the status of the cell for the personal board
                if (personal.getGrid()[i][j] == 1) {
                    ship(j + 1, i + 1);
                } else if (personal.getGrid()[i][j] == 3) {
                    drowned(j + 1, i + 1);
                } else if (personal.getGrid()[i][j] == 4) {
                    blank(j + 1, i + 1);
                }
                //draws the status of the cell for the attack board
                if (attack.getGrid()[i][j] == 2) {
                    hit(j + 1, i - 10);
                } else if (attack.getGrid()[i][j] == 4) {
                    blank(j + 1, i - 10);
                }
            }
        }
    }

    //draws the blue ship symbol - DONE
    public static void ship(double x, double y) {
        StdDraw.setPenColor(Color.blue);
        StdDraw.filledEllipse(Math.round(x), Math.round(y), 0.35, 0.2);
        StdDraw.filledSquare(Math.round(x)-0.2, Math.round(y), 0.2);
        StdDraw.filledEllipse(Math.round(x), Math.round(y) + 0.2, 0.2,0.1);
        StdDraw.setPenColor(Color.white);
        StdDraw.filledCircle(Math.round(x) + 0.1,Math.round(y), 0.05);
        StdDraw.filledCircle(Math.round(x) - 0.1,Math.round(y), 0.05);
        StdDraw.filledCircle(Math.round(x) - 0.3,Math.round(y), 0.05);
    }

    //draws the hit symbol - green circle - DONE
    public static void hit(double x, double y) {
        StdDraw.setPenColor(Color.green);
        StdDraw.filledCircle(x,y,0.4);
    }

    //draws the hit symbol - red cross - DONE
    public static void drowned(double x, double y) {
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.red);
        StdDraw.line(x-0.5, y + 0.5, x + 0.5, y - 0.5);
        StdDraw.line(x-0.5, y-0.5, x + 0.5, y + 0.5);
        StdDraw.setPenRadius();
    }

    //draws the miss symbol - black square - DONE
    public static void blank(double x, double y) {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(Color.black);
        StdDraw.filledSquare(x,y,0.5);
    }

    //attack animation - DONE
    public static void boom(double x, double y) {
        StdDraw.enableDoubleBuffering();
        for (int i = 1; i < 20; i++) {
            StdDraw.picture(x,y,"boom.png", 0.1 * i,0.1 * i);
            StdDraw.show();
            StdDraw.pause(20);
        }
        StdDraw.disableDoubleBuffering();
    }

    //tells the players to switch and makes it harder for them to see each other's personal board to cheat - DONE
    public static void switchScreen() {
        StdDraw.clear();
        StdDraw.setXscale(0, 22);
        StdDraw.setYscale(-11, 11);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.picture(11,0,"ocean.jpg");
        Font font = new Font("Arial", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.text(11,0,"Switch Players");
        StdDraw.pause(2000);
    }
}
