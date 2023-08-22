package prev.phase.regall;

import java.util.*;

import prev.data.mem.*;
import prev.data.asm.*;
import prev.phase.*;
import prev.phase.asmgen.*;
import prev.phase.regall.*;

public class Node {
    private MemTemp temp;
    private HashSet<Node> neighbours;
    private int color;
    private boolean potentialSpill;
    private boolean actualSpill;
    private long offset;
    private boolean offsetWasSet;

    public Node(MemTemp temp) {
        this.temp = temp;
        this.color = -1;
        neighbours = new HashSet<>();
        potentialSpill = false;
        actualSpill = false;
        offsetWasSet = false;
        offset = 0;
    }

    public long getOffset() {
        return this.offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
        this.offsetWasSet = true;
    }

    public boolean offsetWasSet() {
        return this.offsetWasSet;
    }

    public void setPotentialSpill() {
        this.potentialSpill = true;
    }

    public boolean isPotentialSpill() {
        return this.potentialSpill;
    }

    public void setActualSpill() {
        this.actualSpill = true;
    }

    public boolean isActualSpill() {
        return this.actualSpill;
    }

    public void addNeighbours(HashSet<Node> nghbrs) {
        neighbours.addAll(nghbrs);
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public HashSet<Node> neighbours() {
        return this.neighbours;
    }

    public int noOfNeighbours() {
        return this.neighbours.size();
    }

    public MemTemp getTemp() {
        return this.temp;
    }
    public void printNeighbours() {
        for(Node i : neighbours) {
            System.out.print(i.getTemp().toString()+", ");
        }
    }
}