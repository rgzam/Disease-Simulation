import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class configPropertyValues {

    public configPropertyValues() {
        dimWidth = 200;
        dimHeight = 200;
        exposureDistance = 20;
        incubation = 5;
        sickness = 10;
        recover = 0.95;
        initSick = 1;
        initImmune = 0;
        unitTime = 150;
    }

    public int dimWidth, dimHeight; //Dimensions of board
    public int exposureDistance;
    public int incubation; //Time it takes until sick
    public int sickness; //Time the agent is sick
    public double recover; //Chance for agent to recover :: (1-recover) = chance to die
    public int initSick; //How many agents are sick from the start?
    public int initImmune; //How many agents are immune from the start?

    public int unitTime;
}