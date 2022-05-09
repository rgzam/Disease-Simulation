import java.awt.*;
import java.io.File;
import javax.swing.*;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.layout.HBox;


public class DiseasesSimulation extends Canvas {
    int hBoxLabelSpacing = 165;
    static int screenSize=1000;
    static int cellSize=10;
    static int arraySize=screenSize/cellSize;
    int fontSize = 25;

    final int ALIVE=1;
    final int Empty = 0;
    final int DEAD=4;
    final int INFECTED = 2;
    final int RECOVERED = 3;
    final Color ALIVE_COLOR = Color.BLUE;
    final Color Empty_Color = Color.WHITE;
    final Color INFECTED_COLOR = Color.RED;
    final Color RECOVERED_COLOR = Color.GREEN;
    final Color DEAD_COLOR = Color.BLACK;
    final Color GRID_COLOR = new Color(50, 50, 50);



    int[][] currentStates=new int[arraySize][arraySize];
    int[][] nextStates=new int[arraySize][arraySize];
    int[][] lastStates=new int[arraySize][arraySize];

    public static void main (String[] args) {
        JFrame frame = new JFrame("Disease Simulator"); //give screen a name
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DiseasesSimulation canvas = new DiseasesSimulation();
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
    void randomInitialization() {

        //set every cell to be alive
        for(int i=0; i<arraySize; i++){
            for(int j=0; j<arraySize; j++){
                currentStates[i][j]=ALIVE;
            }
        }
        //staring one infect cells in the center
        currentStates[50][50] = INFECTED;



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

    /**
     *set the initlization and the current state and next state
     */
    public void myMethod () {
        randomInitialization();
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



    void blinker(){
        for(int i=0; i<arraySize;i++){
            for(int j=0; j<arraySize;j++){
                currentStates[i][j]=DEAD;

            }
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
                            System.out.println("number of infected: "+infectedNeighbors);

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
            if (diceRoll > 85)
                return RECOVERED;
        }
        if (currentStates[row][column] == DEAD)
            return DEAD;
        if (currentStates[row][column] == RECOVERED)
            return RECOVERED;

        return ALIVE;

    }
//    public Font setFontt(){
//        return  Font.font("Sans", FontWeight.MEDIUM, fontSize = 25);
//    }
//    public int getDEAD(){
//        return DEAD;
//    }
//    public HBox label() {
//        Label agentX = new Label("Human Died" + getDEAD());
//        agentX.getContentDisplay();
//        System.out.println(agentX);
//
//        Label agentY = new Label("time: ");
//
//        HBox hBox = new HBox(hBoxLabelSpacing,agentX,agentY);
//        agentY.setAlignment(Pos.BASELINE_CENTER);
//        System.out.println(agentY);
//       // agentY.setFont(new gameOfLifeRule.Font);
//        agentY.setPadding(new Insets(0,0,15,0));
//        return  (hBox);
//
//    }

    /**
     * This method reduces flickering of the display
     * Don't change it.
     */
    public void update(Graphics g) {
        paint(g);
    }
    public void restartApplication()
    {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
       // final File currentJar = new File(.class.getProtectionDomain().getCodeSource().getLocation().toURI());

        /* is it a jar file? */
       // if(!currentJar.getName().endsWith(".jar"))
            return;

        /* Build command: java -jar application.jar */
      //  final ArrayList<String> command = new ArrayList<String>();
     //   command.add(javaBin);
     //  command.add("-jar");
     //   command.add(currentJar.getPath());

     //   final ProcessBuilder builder = new ProcessBuilder(command);
     //   builder.start();
    //    System.exit(0);
    }

    private class gameOfLifeRule {
    }
}
