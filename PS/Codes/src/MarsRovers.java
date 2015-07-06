import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by niranjan.agrawal on 06/07/15.
 */

public class MarsRovers {

    static int coordinates[][],roverCount=0,x,y;
    static Rover rover[];

    /*
    This method is used to take the input from file and creates as many Rovers required with the necessary attributes.
    Test Input:
    5 5
    1 2 N
    LMLMLMLMM
    3 3 E
    MMRMMRMRRM
     */

    static void getInput(){

        ArrayList<String> inputElements = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line;
            while((line=br.readLine())!=null){
                inputElements.add(line);
            }

            String pos = inputElements.get(0);
            int count = (inputElements.size()-1)/2;
            rover = new Rover[count];
            x = Integer.parseInt(pos.split(" ")[0]);
            y = Integer.parseInt(pos.split(" ")[1]);
            coordinates = new int[x][y];
            for(int i=1;i<inputElements.size();i+=2){
                String roverInfo = inputElements.get(i);
                String roverInfoArr[] = roverInfo.split(" ");
                rover[roverCount++] = new Rover(Integer.parseInt(roverInfoArr[0]),Integer.parseInt(roverInfoArr[1]),
                        roverInfoArr[2].charAt(0),inputElements.get(i+1));
            }
            startRover();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        This takes care of starting the rovers one by one, and move it as per the sequence.
     */

    static void startRover(){
        for(int i=0;i<roverCount;i++){
            char lastOrient = rover[i].getStartOrient();
            char newOrient = lastOrient;
            String sequence = rover[i].getSequence();
            int endX = rover[i].getStartX();
            int endY = rover[i].getStartY();
            for(int j=0;j<sequence.length();j++){
                switch(sequence.charAt(j)){
                    case 'L':
                        newOrient = getNewOrient('L',newOrient);
                        break;
                    case 'M':
                        if(newOrient=='N'){
                            if(endY+1>y)
                                endY=y;
                            else
                                endY=endY+1;
                        }
                        else if(newOrient=='W'){
                            if(endX-1<0)
                                endX=0;
                            else
                                endX=endX-1;
                        }
                        else if(newOrient=='S'){
                            if(endY-1<0)
                                endY=0;
                            else
                                endY=endY-1;
                        }
                        else {
                            if (endX + 1 > x)
                                endX = x;
                            else
                                endX = endX + 1;
                        }
                        break;
                    case 'R':
                        newOrient = getNewOrient('R',newOrient);
                        break;
                }
            }
            rover[i].setEndOrient(newOrient);
            rover[i].setEndX(endX);
            rover[i].setEndY(endY);
        }
        roverDestination();
    }

    /*
        This method prints the rover's final point.
     */

    static void roverDestination(){
        for(int i=0;i<roverCount;i++){
            System.out.println(rover[i].getEndX()+" "+rover[i].getEndY()+" "+rover[i].getEndOrient());
        }
    }

    /*
        Helper method to return the new orientation of the rover after receiving the signal
     */

    static char getNewOrient(char ch,char last){
        switch(last){
            case 'N':
                if(ch=='L')
                    return 'W';
                else
                    return 'E';
            case 'W':
                if(ch=='L')
                    return 'S';
                else
                    return 'N';
            case 'S':
                if(ch=='L')
                    return 'E';
                else
                    return 'W';
            case 'E':
                if(ch=='L')
                    return 'N';
                else
                    return 'S';
        }
        return last;
    }

    public static void main(String args[]){
        getInput();
    }
}