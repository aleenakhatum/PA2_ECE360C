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

        //Initialize
        boolean[] visited = new boolean[problem.getRegions().size()]; //initialize all nodes to not visited
        problem.getRegions().get(0).setMinDist(0); //starting vertex for algorithm
        for (int i = 1; i < problem.getRegions().size(); i++) {
            problem.getRegions().get(i).setMinDist(Integer.MAX_VALUE); // set each node to infiniti
        }
        
        // A min heap implementation.
        Heap<Region> heap = new Heap<Region>(); //priority queue
        heap.buildHeap(problem.getRegions()); //turns list of regions into a min-heap

        System.out.println("Initial heap state:");
        for (Region r : problem.getRegions()) {
            System.out.println("  Region " + r.getIndex() + " minDist=" + r.getMinDist());
        }

        // Print heap state after swap
        System.out.print("  Current heap: ");
        for (int k = 0; k < heap.size(); k++) {
            System.out.print(heap.get(k).getValue() + " ");
        }
        System.out.println();

        //Prim's
        int maxPath = 0;
        while (heap.getSize() > 0) { //Loops through each node (O(n))
            Region region = heap.extractMin(); //return region with smallest minDist
            System.out.println("Extracted: " + region.getId());

            //Find max path
            if (!visited[region.getId()]) {
                maxPath += region.getMinDist();
                System.out.println("new max length" + maxPath);
                visited[region.getId()] = true; //mark node as visited
            }

            //Relax neighbors of node (O(n))
            relax(region, visited, heap);

        }

        return -maxPath;
    }

    /*Relax Function
     */
    public void relax(Region region, boolean[] visited, Heap<Region> heap) {
        System.out.println("Relaxing region " + region.getIndex());
        for (int i = 0; i < region.getNeighbors().size(); i++) {
            Region neighbor = region.getNeighbors().get(i);
            int neighborMinDist = neighbor.getMinDist();
            int weight = region.getWeights().get(i);

            System.out.println("  Neighbor " + neighbor.getIndex() + " weight " + weight +
                    " visited=" + visited[neighbor.getId()] +
                    " minDist=" + neighbor.getMinDist());


            int neg_weight = -weight;

            if (visited[neighbor.getId()]) {
                continue; //skip neighbor if already visited
            }

            //Add node to MST if heavier edge found
            if (neg_weight < neighbor.getMinDist()) {
                heap.changeKey(neighbor, neg_weight); //update priority queue
                neighbor.setMinDist(neg_weight); //update all neighbor regions from infiniti to lower values for distance
                //visited[neighbor.getId()] = true;

                //track MST
                region.getMST_Neighbors().add(neighbor); //add new node to MST
                region.getMST_Weights().add(weight); //save weight of new node in MST

                //DOUBEL CHECK THIS LOGIC DEPENDS ON HOW WE TRAVERSE AT THE END I THINK
                neighbor.getMST_Neighbors().add(region); //add parent to mst (update info)
                neighbor.getMST_Weights().add(weight); //add parent weight to mst
            }

            System.out.println("after" + "  Neighbor " + neighbor.getIndex() + " weight " + weight +
                    " visited=" + visited[neighbor.getId()] +
                    " minDist=" + neighbor.getMinDist());
        }
    }
}
