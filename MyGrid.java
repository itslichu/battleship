public class MyGrid {
    private int[][] grid;

    //creates a 10x10 array and fills it with 0s - DONE
    public MyGrid(){
        grid = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = 0;
            }
        }
    }
    //returns the array - DONE
    public int[][] getGrid() {
        return grid;
    }
    //counts the number of array elements that have a specific value (type) - DONE
    public int count(int type) {
        int k = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == type)
                    k++;
            }
        }
        return k;
    }

    //returns the string version of the array for better tracing - DONE
    public String toString (){
        String s = "";
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                s += grid[i][j];
            }
            s += '\n';
        }
        return s;
    }

    //draws and initializes an n-cell ship - DONE
    public void oneShip(int n) {
        System.out.println("Please click on the first and last cells of your " + n + "-cell ship");
        double x1,  y1;
        double x2, y2;
        long difX, difY;
        int k;
        while (true) {
            if (StdDraw.isMousePressed()) {
                x1 = StdDraw.mouseX();
                y1 = StdDraw.mouseY();
                while (StdDraw.isMousePressed()) {

                }
                if (x1 < 0.5 || x1 > 10.5 || y1 < 0.5 || y1 > 10.5) {
                    StdAudio.playInBackground("wrong.wav");
                    System.out.println("The chosen cell is outside of the personal board. Please choose again.");
                }
                else {
                    grid[(int) Math.round(y1) - 1][(int) Math.round(x1) - 1] = 1;
                    Drawing.ship(x1, y1);
                    break;
                }
            }
        }
        StdDraw.pause(500);
        while (true) {
            if (StdDraw.isMousePressed()) {
                x2 = StdDraw.mouseX();
                y2 = StdDraw.mouseY();
                difY = Math.abs(Math.round(y2) - Math.round(y1));
                difX = Math.abs(Math.round(x2) - Math.round(x1));
                while (StdDraw.isMousePressed()) {

                }
                if (x2 < 0.5 || x2 > 10.5 || y2 < 0.5 || y2 > 10.5) {
                    StdAudio.playInBackground("wrong.wav");
                    System.out.println("The chosen cell is outside of the personal board. Please choose again.");
                }
                else if (!(difY == n - 1 && difX == 0) && !(difY == 0 && difX == n - 1)) {
                    StdAudio.playInBackground("wrong.wav");
                    System.out.println("The ship should consist of " + n + " cells, which should be placed either horizontally or vertically");
                }
                else {
                    grid[(int) Math.round(y2) - 1][(int) Math.round(x2) - 1] = 1;
                    Drawing.ship(x2,y2);
                    break;
                }
            }
        }
        if (difY == n-1) {
            if (y2 > y1)
                k = 1;
            else
                k = -1;
            int i = (int) Math.round(y1) - 1;
            while (i != (int) Math.round(y2) - 1) {
                grid[i][(int) Math.round(x1) - 1] = 1;
                Drawing.ship(x1, i + 1);
                i += k;
            }
        }
        else if (difX == n-1) {
            if (x2 > x1)
                k = 1;
            else
                k = -1;
            int i = (int) Math.round(x1) - 1;
            while (i != (int) Math.round(x2) - 1) {
                grid[(int) Math.round(y1) - 1][i] = 1;
                Drawing.ship(i + 1, y1);
                i += k;
            }
        }
        StdDraw.pause(500);
    }
    //the placement of ships is recorded in the array as 1s
    //each player has 5 ships: 1x 5 cells, 1x 4 cells, 2x 3 cells, 1x 2 cells --> 17 cells filled in total
    public void initialize() {
        oneShip(5);
        oneShip(4);
        oneShip(3);
        oneShip(3);
        oneShip(2);
        StdDraw.pause(500);
    }

    //changes the values of the elements of the array according to the hit/miss status for easier symbol drawing
    // 1 - there is a ship (ship symbol)
    // 2 - the ship was hit (green circle - attack board)
    // 3 - the ship was hit (red cross - personal board)
    // 4 - no ship was hit (black square)
    public void analyze(MyGrid other, int i, int j) {
        if (grid[i][j] == 1) {
            other.getGrid()[i][j] = 2;
            grid[i][j] = 3;
        }
        else {
            other.getGrid()[i][j] = 4;
            grid[i][j] = 4;
        }
    }
}
