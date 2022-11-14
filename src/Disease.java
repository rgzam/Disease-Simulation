import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Disease extends Canvas {
    protected static configPropertyValues config = new configPropertyValues();
    static int width =200;
    static int height = 200;
    protected static int exposureDistance = 20;
    protected static int incubation = 5;
    protected static int sickness = 0;
    protected static int recover = 95;
    protected static boolean grid = true;

    protected static boolean random = false;

    protected static boolean randomGrid = false;
    protected static int row = 10;
    protected static int col = 10;
    protected static int numAgents = row * col;
    protected static int initialSick = 5;
    protected static int initialImmune = 0;
    protected static int distance = 0;
    protected static java.util.List<Integer> startingSickAgents = new ArrayList<Integer>();

    protected static List<Integer> startingImmuneAgents = new ArrayList<Integer>();

    protected static int delay = 0;
    static int screenSize=1000;
    static int cellSize=10;
    static int arraySize=screenSize/cellSize;



    final int ALIVE=1;
    final int Empty = 0;
    final int DEAD=4;
    final int INFECTED = 2;
    final int RECOVERED = 3;
    int getRECOVERED;
    int getDEAD;
    int getAlive;

    final Color ALIVE_COLOR = Color.BLUE;
    final Color Empty_Color = Color.WHITE;
    final Color DEAD_COLOR = Color.BLACK;
    final Color INFECTED_COLOR = Color.RED;
    final Color RECOVERED_COLOR = Color.GREEN;
    final Color GRID_COLOR = new Color(50, 50, 50);



    int[][] currentStates=new int[arraySize][arraySize];
    int[][] nextStates=new int[arraySize][arraySize];
    int[][] lastStates=new int[arraySize][arraySize];

    public static void main (String[] args) {
        System.out.println("Default: ");

        configurationPrint();

        if(args.length == 1){

            config.ingestConfigFile(args[0]);

        }

        System.out.println("\nConfig file: ");

        configurationPrint();

        config.initializeSickAgents();

        System.out.println("Size sick list size: " + startingSickAgents.size());

        for(int i = 0; i < startingSickAgents.size(); i++){


            System.out.println("Sick agent: " + startingSickAgents.get(i));

        }

        config.initializeImmuneAgents();

        System.out.println("Size immune list size: " + startingImmuneAgents.size());

        for(int i = 0; i < startingImmuneAgents.size(); i++){

            System.out.println("Immune agent: " + startingImmuneAgents.get(i));

        }
        JFrame frame = new JFrame("Project 4: Disease Simulation"); //screen a name
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Disease canvas = new Disease();

        canvas.setSize(screenSize, screenSize);

        canvas.setBackground(Color.white);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        canvas.myMethod();
    }
    protected static void configurationPrint(){

        System.out.println("width: " + width);

        System.out.println("height: " + height);

        System.out.println("exposure distance: " + exposureDistance);

        System.out.println("incubation: " + incubation);

        System.out.println("sickness: " + sickness);

        System.out.println("recover: " + recover);

        System.out.println("grid? " + grid);

        System.out.println("random? " + random);

        System.out.println("random grid? " + randomGrid);

        System.out.println("row: " + row);

        System.out.println("column: " + col);

        System.out.println("number of agents: " + numAgents);

        System.out.println("initial sick: " + initialSick);

        System.out.println("initial immune: " + initialImmune);

        System.out.println("distance: " + distance);

        System.out.println("delay: " + delay);

    }
    void aliveRandomInitialization() {



        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                currentStates[i][j] = ALIVE;
            }
        }

    }
    void sicknessRandomInitialization(){
        Random randomNumber= new Random();
        int myRandomNumberx=randomNumber.nextInt(arraySize);
        int myRandomNumbery=randomNumber.nextInt(arraySize);



        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                currentStates[myRandomNumberx][myRandomNumbery] = INFECTED;
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
                if (currentStates[i][j] == INFECTED) {
                    g.setColor(INFECTED_COLOR);
                }
                if (currentStates[i][j] == Empty) {
                    g.setColor(Empty_Color);
                }
                if (currentStates[i][j] == RECOVERED) {
                    g.setColor(RECOVERED_COLOR);
                }
                if (currentStates[i][j] == DEAD) {
                    g.setColor(DEAD_COLOR);
                }
                g.fillRect(cellSize*j,cellSize*i,cellSize,cellSize);
                g.setColor(GRID_COLOR);
                g.drawRect(cellSize*j,cellSize*i,cellSize,cellSize);
            }
        }

    }
    public void counting(){
        for(int i=0; i<arraySize;i++){
            for(int j=0; j<arraySize;j++) {
                if (currentStates[i][j] == INFECTED){
                    sickness++;

                    System.out.println("sickness: "+ sickness);
                }
                if(currentStates[i][j] == RECOVERED){
                    getRECOVERED++;
                    sickness--;
                    System.out.println("Recovered: "+getRECOVERED);
                }



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

        aliveRandomInitialization();
        for(int i=0; i<initialSick; i++){
            sicknessRandomInitialization();
        }
        int count=0;


        while (count<=100000) {


            for (int i = 1; i < arraySize - 1; i++) {
                for (int j = 1; j < arraySize - 1; j++) {
                    nextStates[i][j] = simulationRules(i, j);

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





    int simulationRules(int row, int column) {



        if (currentStates[row][column] == ALIVE){
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

            // healthy cell has 15 percent chance infected by the infected neighbor.
            double infectedRisk = (Math.random()*100);
            if (infectedRisk <= 15*infectedNeighbors){
                return INFECTED;
            }
            else
                return ALIVE;
        }


        //infected cell has 15 percent chance of dying and 70 percent of chance of staying infected.
        if (currentStates[row][column] == INFECTED){
            double diceRoll = (Math.random()*100);
            if (diceRoll < 70)
                return INFECTED;
            if (diceRoll > 70 && diceRoll < 85)
                return DEAD;
            if (diceRoll < 95)
                return RECOVERED;
        }
        if (currentStates[row][column] == DEAD)
            return DEAD;
        if (currentStates[row][column] == RECOVERED)
            return RECOVERED;

        return ALIVE;

    }


    /**
     * This method reduces flickering of the display
     * Don't change it.
     */
    public void update(Graphics g) {

        paint(g);
    }
}