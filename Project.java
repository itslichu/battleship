import java.awt.*;
import java.awt.Font;

public class Project {
    public static void main(String[] args) {
        StdAudio.playInBackground("waves.wav");
        //start page
        StdDraw.clear();
        StdDraw.setXscale(0, 22);
        StdDraw.setYscale(-11, 11);
        StdDraw.picture(11,0,"ocean.jpg");
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("Arial", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.text(11,5, "Welcome to the Game of Battleship");
        StdDraw.filledRectangle(11, 0, 6, 2);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(11,0,"START");
        while (true) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                if (x > 5 && x < 17 && y > -2 && y < 2) {
                    StdAudio.stopInBackground();
                    break;
                }
            }
        }
        StdDraw.pause(300);
        //initialize the boards of the first player
        MyGrid Personal01 = new MyGrid();
        MyGrid Attack01 = new MyGrid();
        //initialize the boards of the second player
        MyGrid Personal02 = new MyGrid();
        MyGrid Attack02 = new MyGrid();
        //fill the personal board of the first player with ships
        System.out.println("Player 01: Please place your ships");
        Drawing.setUp();
        Drawing.Boards(Personal01,Attack01,1);
        Personal01.initialize();
        Drawing.switchScreen();
        //fill the personal board of the second player with ships
        System.out.println("Player 02: Please place your ships");
        Drawing.setUp();
        Drawing.Boards(Personal02, Attack02, 2);
        Personal02.initialize();
        //start the game - runs until all ships are hit --> 17 cells on either of the attack boards are equal to 2
        System.out.println("LET'S BEGIN THE GAME");
        while(Attack01.count(2) != 17 && Attack02.count(2) != 17) {
            //first player's turn
            Drawing.switchScreen();
            Drawing.setUp();
            Drawing.Boards(Personal01, Attack01, 1);
            while (true) {
                if (StdDraw.isMousePressed()) {
                    double x = StdDraw.mouseX();
                    double y = StdDraw.mouseY();
                    while (StdDraw.isMousePressed()) {

                    }
                    if (x < 0.5 || x > 10.5 || y < -10.5 || y > -0.5) {
                        StdAudio.playInBackground("wrong.wav");
                        System.out.println("The chosen cell is outside of the attack board. Please choose again.");
                    } else {
                        int i = 10 + (int) Math.round(y);
                        int j = (int) Math.round(x) - 1;
                        if (Personal02.getGrid()[i][j] > 1) {
                            StdAudio.playInBackground("wrong.wav");
                            System.out.println("The cell was attacked previously. Please choose another.");
                        } else {
                            StdAudio.playInBackground("explosion.wav");
                            Drawing.boom(x,y);
                            Personal02.analyze(Attack01, i, j);
                            Drawing.setUp();
                            Drawing.Boards(Personal01, Attack01, 1);
                            break;
                        }
                    }
                }
            }
            StdDraw.pause(1500);
            //break out of the loop if all ships are hit
            if (Attack01.count(2) == 17) {
                break;
            } else {
                //second player's turn
                Drawing.switchScreen();
                Drawing.setUp();
                Drawing.Boards(Personal02, Attack02, 2);
                while (true) {
                    if (StdDraw.isMousePressed()) {
                        double x = StdDraw.mouseX();
                        double y = StdDraw.mouseY();
                        while (StdDraw.isMousePressed()) {

                        }
                        if (x < 0.5 || x > 10.5 || y < -10.5 || y > -0.5) {
                            StdAudio.playInBackground("wrong.wav");
                            System.out.println("The chosen cell is outside of the attack board. Please choose again.");
                        } else {
                            int i = 10 + (int) Math.round(y);
                            int j = (int) Math.round(x) - 1;
                            if (Personal01.getGrid()[i][j] > 1) {
                                StdAudio.playInBackground("wrong.wav");
                                System.out.println("The cell was attacked previously. Please choose another.");
                            } else {
                                StdAudio.playInBackground("explosion.wav");
                                Drawing.boom(x,y);
                                Personal01.analyze(Attack02, i, j);
                                Drawing.setUp();
                                Drawing.Boards(Personal02, Attack02, 2);
                                break;
                            }
                        }
                    }
                }
            }
            StdDraw.pause(1500);
        }
        StdDraw.clear();
        StdDraw.setXscale(0, 22);
        StdDraw.setYscale(-11, 11);
        StdDraw.picture(11,0,"ocean.jpg");
        StdDraw.setPenColor(StdDraw.BLACK);
        StdAudio.playInBackground("applause.wav");
        StdDraw.setFont(font);
        if (Attack01.count(2) == 17) {
            StdDraw.text(11,0, "The Winner Is Player 1");
        }
        else {
            StdDraw.text(11,0, "The Winner Is Player 2");
        }
    }
}
