import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;


public class Assignment8 extends Canvas {

    final int ALIVE=1;
    final int DEAD=0;
    static int screenSize=1000;
    static int cellSize=10;
    static int arraySize=screenSize/cellSize;

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
                Color aliveColor = new Color(68, 250, 30);
                Color deadColor = new Color(241, 236, 250);
                if(currentStates[i][j]==ALIVE)
                    g.setColor(aliveColor);
                else
                    g.setColor(deadColor);
                g.fillRect(j*cellSize,i*cellSize,cellSize-1,cellSize-1);
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


        while (count<=90) {


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

            while(count==90){
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
                Thread.sleep(50);
            } catch (Exception exc) {
            }

            // The repaint() method redraws your screen.
            // You can use it to refresh your screen after
            // you've updated your CA to its next state
            repaint();



        }
    }


    void randomInitialization() {
//        for(int i=0; i<arraySize;i++){
//            for(int j=0; j<arraySize;j++){
//                currentStates[i][j]=(int)(Math.random()*2);
//            }
//        }
//    }
        for(int i=50; i<=50;i++){//rows
            for(int j=46; j<53;j++) {//columns
                currentStates[i][j]=ALIVE;
            }
        }
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
    int gameOfLifeRule(int row, int column){
//        int neighborSum=currentStates[row-1][column]+currentStates[row-1][column+1]+
//                currentStates[row][column-1]+currentStates[row-1][column+1]+currentStates[row+1][column-1]+
//                currentStates[row+1][column]+currentStates[row+1][column+1];
        int neighborSum=currentStates[row-1][column-1]+currentStates[row-1][column]
                +currentStates[row-1][column+1]+currentStates[row][column-1]
                +currentStates[row][column+1]+currentStates[row+1][column-1]
                +currentStates[row+1][column]+currentStates[row+1][column+1];
        if(currentStates[row][column]==ALIVE && neighborSum==1 || neighborSum==2 || neighborSum==4 )
            return ALIVE;
        if(currentStates[row][column]==DEAD && neighborSum==1 || neighborSum==2 || neighborSum==4 )
            return DEAD;

        else if(currentStates[row][column]==ALIVE && neighborSum==5 ||neighborSum==6||neighborSum==7||neighborSum==8)
            return DEAD;

        else if(currentStates[row][column]==ALIVE && neighborSum==3)
            return ALIVE;
        else if(currentStates[row][column]==DEAD && neighborSum==3)
            return ALIVE;
        else
            return DEAD;
    }



    /**
     * This method reduces flickering of the display
     * Don't change it.
     */
    public void update(Graphics g) {
        paint(g);
    }
}