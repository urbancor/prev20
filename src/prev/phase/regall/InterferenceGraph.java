package prev.phase.regall;

import java.util.*;

import prev.data.mem.*;
import prev.data.asm.*;
import prev.phase.*;
import prev.phase.asmgen.*;
import prev.phase.regall.*;

public class InterferenceGraph {
    private HashMap<MemTemp, Node> nodes;

    public InterferenceGraph() {
        this.nodes =  new HashMap<MemTemp,Node>();
    }

    public void addNode(MemTemp temp, Node node) {
        if(nodes.get(temp) == null) {
            this.nodes.put(temp, node);
        }
    }
    /**
     * Returns null if there is no node in the hashmap otherwise returns the node
     */
    public Node getNode(MemTemp temp) {
        return this.nodes.get(temp);
    }
    @Override
    public String toString() {
        for(Node i : nodes.values()) {
            System.out.print("NODE "+i.getTemp().toString()+"("+i.getColor()+") "+", ");
            //i.printNeighbours();
            //System.out.println();
        }
        System.out.println();
        return null;
    }

    public void remove(MemTemp n) {
        nodes.remove(n);
    }

    public HashMap<MemTemp,Node> nodes() {
        return this.nodes;
    }

    
    public int size() {
        return this.nodes.size();
    }
}