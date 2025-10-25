/*
 * Name: Jon Snow
 * EID: got001
 */

// DO NOT MODIFY THIS FILE IN ANY WAY !!! 😂
// DO NOT INCLUDE THIS FILE IN YOUR SUBMISSION

import java.util.*;

public class Region extends HeapMember {

    private ArrayList<Region> neighbors;
    private ArrayList<Integer> weights;

    private ArrayList<Region> mst_neighbors;
    private ArrayList<Integer> mst_weights;



    public Region(int x) {
        super(x);
        
        neighbors = new ArrayList<Region>();
        weights = new ArrayList<Integer>();
        
        mst_neighbors = new ArrayList<Region>();
        mst_weights = new ArrayList<Integer>();
    }


    public void setNeighborAndWeight(Region n, Integer w) {
        neighbors.add(n);
        weights.add(w);
    }

    public void setMST_NeighborAndWeight(Region n, Integer w) {
        if(Problem.MST_MODIFY){
            mst_neighbors.add(n);
            mst_weights.add(w);
        }
    }

    public ArrayList<Region> getNeighbors() {
        return neighbors;
    }

    public ArrayList<Integer> getWeights() {
        return weights;
    }

    public void updateWeight(Integer index, Integer new_weight){
        weights.set(index, new_weight);
    }

    public ArrayList<Region> getMST_Neighbors() {
        return mst_neighbors;
    }

    public ArrayList<Integer> getMST_Weights() {
        return mst_weights;
    }

    public int getMinDist() { return this.getValue(); }


    // Does not call heapify
    public void setMinDist(int x) {
        this.setValue(x);
    }

    public void resetMinDist() {
        this.setValue(Integer.MAX_VALUE);
    }

    public int get_Id() {
        return this.getId();
    }
    
    // clear neighbors array
    public void clearNeighbors(){
        neighbors = new ArrayList<Region>();
        weights = new ArrayList<Integer>();
    }

    public void clearMSTNeighbors(){
        mst_neighbors = new ArrayList<Region>();
        mst_weights = new ArrayList<Integer>();
    }

}
