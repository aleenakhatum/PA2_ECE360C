/*
 * Name: Jon Snow
 * EID: GoT0001
 */

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Program2 {

    /**
     * findMaximumLength()
     * @param problem  - contains the regions of the graph.
     * 
     * @return The sum of all of the edges of the MST.
     * 
     * @function Should track the edges in the MST using region.mst_neighbors and region.mst_weights
     *  This function will not modify the mst_lists when run Gradescope if called in calculateDiameter()
     */
    public int findMaximumLength(Problem problem) {

        problem.getRegions().get(0).setMinDist(0);
        
        // A min heap implementation.
        Heap<Region> heap = new Heap<Region>();
        heap.buildHeap(problem.getRegions());

        // TODO: implement this function


        return -1;
    }
    
}
