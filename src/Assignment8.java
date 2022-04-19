import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;


public class Assignment8 extends Canvas {

    static int screenSize=1000;
    static int cellSize=10;
    static int arraySize=screenSize/cellSize;


    final int ALIVE=1;
    final int Healthy = 0;
    final int DEAD=4;
    final int INFECTED = 2;
    final int RECOVERED = 3;

    final Color ALIVE_COLOR = Color.GREEN;
    final Color Healthy_Color = Color.WHITE;
    final Color INFECTED_COLOR = Color.RED;
    final Color RECOVERED_COLOR = Color.BLUE;
    final Color DEAD_COLOR = Color.BLACK;
    final Color GRID_COLOR = new Color(50, 50, 50);



    int[][] currentStates=new int[arraySize][arraySize];
    int[][] nextStates=new int[arraySize][arraySize];
    int[][] lastStates=new int[arraySize][arraySize];

    public static void main (String[] args) {
        JFrame frame = new JFrame("Cellular Automata"); //give screen a name
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Assignment8 canvas = new Assignment8();
        // Sets the size of the screen
        // See https://docs.oracle.com/javase/9/docs/api/javafx/scene/canvas/Canvas.html
        canvas.setSize(screenSize, screenSize);
        // Sets the background color
        // See https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html
        canvas.setBackground(Color.white);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        canvas.myMethod();  //This calls the method myMethod
    }

    /**
     * This method draws things on the screen.
     * This is where you will write
     * code that diplays your CA. You do not
     * call this method. It is called automatically.
     * A few sample drawing features are demonstrated below.
     */
    public void paint(Graphics g) {
        for(int i=0; i<arraySize;i++){
            for(int j=0; j<arraySize;j++){
                if (currentStates[i][j] == ALIVE) {
                    g.setColor(ALIVE_COLOR);
                }
                if (currentStates[i][j] == DEAD) {
                    g.setColor(DEAD_COLOR);
                }
                if (currentStates[i][j] == INFECTED) {
                    g.setColor(INFECTED_COLOR);
                }
                if (currentStates[i][j] == RECOVERED) {
                    g.setColor(RECOVERED_COLOR);
                }
                if (currentStates[i][j] == Healthy) {
                    g.setColor(Healthy_Color);
                }
                g.fillRect(cellSize*j,cellSize*i,cellSize,cellSize);
                g.setColor(GRID_COLOR);
                g.drawRect(cellSize*j,cellSize*i,cellSize,cellSize);
            }
        }

    }

    /**
     * This method includes some `-+
     * functionality that you will want
     * to include in your code. Feel free
     * to rename or delete this method
     */
    public void myMethod () {
        randomInitialization();
        int count=0;


        while (count<=100000) {


            for (int i = 1; i < arraySize - 1; i++) {
                for (int j = 1; j < arraySize - 1; j++) {
                    nextStates[i][j] = gameOfLifeRule(i, j);

                }
            }
            for (int i = 0; i < arraySize; i++) {
                for (int j = 0; j < arraySize; j++) {

                    currentStates[i][j] = nextStates[i][j];

                }
            }

            while(count==10000){
                for (int i = 0; i < arraySize; i++) {
                    for (int j = 0; j < arraySize; j++) {

                        currentStates[i][j] =DEAD;

                    }
                }
                myMethod();


            }

            count++;






            // This block of code pauses the
            // program for 500ms (1/2 of a second)
            // It will be useful for animating your CA
            try {
                Thread.sleep(100);
            } catch (Exception exc) {
            }

            // The repaint() method redraws your screen.
            // You can use it to refresh your screen after
            // you've updated your CA to its next state
            repaint();



        }
    }


    void randomInitialization() {


//        for (int i = 0; i < arraySize; i++) {
//            for (int j = 0; j < arraySize; j++) {
//                currentStates[i][j] = (int) (Math.random() * 2);
//            }
//        }

        for (int i = 45; i < 50; i++) {
            for (int j = 45; j < 50; j++) {
                currentStates[i][j] = INFECTED;
            }
        }


//        currentStates[50][50] = INFECTED;



    }

    void blinker(){
        for(int i=0; i<arraySize;i++){
            for(int j=0; j<arraySize;j++){
                currentStates[i][j]=DEAD;

            }
        }
        currentStates[arraySize/2 - 1][arraySize/2]=ALIVE;
        currentStates[arraySize/2][arraySize/2]=ALIVE;
        currentStates[arraySize/2+1][arraySize/2]=ALIVE;
    }
//    int gameOfLifeRule(int row, int column){
////        int neighborSum=currentStates[row-1][column]+currentStates[row-1][column+1]+
////                currentStates[row][column-1]+currentStates[row-1][column+1]+currentStates[row+1][column-1]+
////                currentStates[row+1][column]+currentStates[row+1][column+1];
//        int neighborSum=currentStates[row-1][column-1]+currentStates[row-1][column]
//                +currentStates[row-1][column+1]+currentStates[row][column-1]
//                +currentStates[row][column+1]+currentStates[row+1][column-1]
//                +currentStates[row+1][column]+currentStates[row+1][column+1];
//        if(currentStates[row][column]==ALIVE && neighborSum==1 || neighborSum==2 || neighborSum==4 )
//            return ALIVE;
//        if(currentStates[row][column]==DEAD && neighborSum==1 || neighborSum==2 || neighborSum==4 )
//            return DEAD;
//
//        else if(currentStates[row][column]==ALIVE && neighborSum==5 ||neighborSum==6||neighborSum==7||neighborSum==8)
//            return DEAD;
//
//        else if(currentStates[row][column]==ALIVE && neighborSum==3)
//            return ALIVE;
//        else if(currentStates[row][column]==DEAD && neighborSum==3)
//            return ALIVE;
//        else
//            return DEAD;
//    }

    int gameOfLifeRule(int row, int column) {



        int neighborSum=currentStates[row-1][column-1]+currentStates[row-1][column]
                +currentStates[row-1][column+1]+currentStates[row][column-1]
                +currentStates[row][column+1]+currentStates[row+1][column-1]
                +currentStates[row+1][column]+currentStates[row+1][column+1];
        if(currentStates[row][column]==INFECTED && neighborSum==1 || neighborSum==2 || neighborSum==4 )
            return INFECTED;
        if(currentStates[row][column]==DEAD && neighborSum==1 || neighborSum==2 || neighborSum==4 )
            return DEAD;

        else if(currentStates[row][column]==ALIVE && neighborSum==5 ||neighborSum==6||neighborSum==7||neighborSum==8)
            return DEAD;

        else if(currentStates[row][column]==ALIVE && neighborSum==3)
            return ALIVE;
        else if(currentStates[row][column]==DEAD && neighborSum==3)
            return ALIVE;
        else if (currentStates[row][column] == ALIVE){
            //count number of INFECTED neighbors
            int infectedNeighbors=0;
            for (int i=row-1; i<=row+1;i++){
                for (int j=column-1;j<=column+1;j++){
                    if (!(i==row && j==column)) {
                        if (currentStates[i][j] == INFECTED) {
                            infectedNeighbors++;
                        }
                    }
                }
            }
            //compute rule and return next state for cell
            //healthy cell has probability of becoming infected equal to 15% times it's number of infected neighbors, or
            //a 100% chance with 7 or more infected neighbors
            double infectedRisk = (Math.random()*100);
            if (infectedRisk <= 15*infectedNeighbors){
                return INFECTED;
            }
            else
                return ALIVE;
        }



        if (currentStates[row][column] == INFECTED){//infected cell has 70% chance of staying infected, 15% chance of dying,
            //and 15% chance of recovering
            double diceRoll = (Math.random()*100);
            if (diceRoll < 70)
                return INFECTED;
            if (diceRoll > 70 && diceRoll < 85)
                return DEAD;
            if (diceRoll > 85)
                return RECOVERED;
        }
        if (currentStates[row][column] == DEAD)
            return DEAD;
        if (currentStates[row][column] == RECOVERED)
            return RECOVERED;

        return Healthy;

    }


    /**
     * This method reduces flickering of the display
     * Don't change it.
     */
    public void update(Graphics g) {
        paint(g);
    }
}
