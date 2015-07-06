/**
 * Created by niranjan.agrawal on 06/07/15.
 */
public class Rover {

    private int startX, startY, endX, endY;
    private char startOrient;

    public char getEndOrient() {
        return endOrient;
    }

    public void setEndOrient(char endOrient) {
        this.endOrient = endOrient;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    private char endOrient;
    private  String sequence;

    public String getSequence() {
        return sequence;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public char getStartOrient() {
        return startOrient;
    }

    public Rover(int startX, int startY, char startOrient,String sequence) {
        this.startX = startX;
        this.startY = startY;
        this.startOrient = startOrient;
        this.sequence = sequence;
    }
}
