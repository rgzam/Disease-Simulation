
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class configPropertyValues {

    protected void ingestConfigFile(String args){
        //Variable for holding a line
        String line = "";
        try(
                BufferedReader in = new BufferedReader(new FileReader(args));
        ){
            while((line = in.readLine()) != null){
                String[] lineSplit = line.split(" ");
                if(lineSplit[0].equals("dimensions")){
                    if(lineSplit.length >= 2){
                        if(isInteger(lineSplit[1])){
                            Disease.width = Integer.parseInt(lineSplit[1]);
                        }
                    }
                    if(lineSplit.length >= 3){
                        if(isInteger(lineSplit[2])){
                            Disease.height = Integer.parseInt(lineSplit[2]);
                        }
                    }
                }
                else if(lineSplit[0].equals("exposuredistance") && lineSplit.length >= 2){
                    if(isInteger(lineSplit[1])){
                        Disease.exposureDistance = Integer.parseInt(lineSplit[1]);
                    }
                }
                else if(lineSplit[0].equals("incubation") && lineSplit.length >= 2){
                    if(isInteger(lineSplit[1])){
                        Disease.incubation = Integer.parseInt(lineSplit[1]);
                    }
                }
                else if(lineSplit[0].equals("sickness") && lineSplit.length >= 2){
                    if(isInteger(lineSplit[1])){
                        Disease.sickness = Integer.parseInt(lineSplit[1]);
                    }
                }
                else if(lineSplit[0].equals("recover") && lineSplit.length >= 2){
                    if(isFloat(lineSplit[1])){
                        Disease.recover = ((int) (100 * Float.parseFloat(lineSplit[1])));
                    }
                }
                else if(lineSplit[0].equals("initialsick") && lineSplit.length >= 2){
                    if(isInteger(lineSplit[1])){
                        Disease.initialSick = Integer.parseInt(lineSplit[1]);
                    }
                }
                else if(lineSplit[0].equals("initialimmune") && lineSplit.length >= 2){
                    if(isInteger(lineSplit[1])){
                        Disease.initialImmune = Integer.parseInt(lineSplit[1]);
                    }
                }

                else if(lineSplit[0].equals("grid")){
                    Disease.grid = true;
                    Disease.random = false;
                    Disease.randomGrid = false;
                    if(lineSplit.length >= 2){
                        if(isInteger(lineSplit[1])){
                            Disease.row = Integer.parseInt(lineSplit[1]);
                        }
                    }
                    if(lineSplit.length >= 3){
                        if(isInteger(lineSplit[2])){
                            Disease.col = Integer.parseInt(lineSplit[2]);
                        }
                    }
                    Disease.numAgents = Disease.row * Disease.col;
                }

            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean isInteger(String input){
        int inputValue;
        try{
            inputValue = Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e){}
        return false;
    }

    private boolean isFloat(String input){
        float inputValue;
        try{
            inputValue = Float.parseFloat(input);
            return true;
        }
        catch (NumberFormatException e){}
        return false;
    }

    protected static void initializeSickAgents(){
        int numSickAgents = Disease.initialSick;
        int numAgents = Disease.numAgents;
        Random rand = new Random();
        int randAgent;
        if(numSickAgents >= numAgents){
            for(int i = 0; i < numAgents; i++){
                Disease.startingSickAgents.add(i);
            }
        }
        else{
            while(numSickAgents != 0){
                randAgent = rand.nextInt(numAgents);
                if(!Disease.startingSickAgents.contains(randAgent) && !Disease.startingImmuneAgents.contains(randAgent)){
                    Disease.startingSickAgents.add(randAgent);
                    numSickAgents--;
                }
            }
        }
    }

    protected static void initializeImmuneAgents(){
        int numImmuneAgents = Disease.initialImmune;
        int numAgents = Disease.numAgents;
        Random rand = new Random();
        int randAgent;
        if(numImmuneAgents >= numAgents){
            for(int i = 0; i < numAgents; i++){
                if(!Disease.startingSickAgents.contains(i)){
                    Disease.startingImmuneAgents.add(i);
                }
            }
        }
        else{
            while(numImmuneAgents != 0){
                randAgent = rand.nextInt(numAgents);
                if(!Disease.startingSickAgents.contains(randAgent) && !Disease.startingImmuneAgents.contains(randAgent)){
                    Disease.startingImmuneAgents.add(randAgent);
                    numImmuneAgents--;
                }
            }
        }
    }
}