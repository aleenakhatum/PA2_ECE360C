/*
 * Name: Jon Snow
 * EID: GoT0001
 */

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;

public class Heap<HeapMemberGeneric extends HeapMember> {
    private ArrayList<HeapMemberGeneric> minHeap;

    public Heap() {
        minHeap = new ArrayList<HeapMemberGeneric>();
    }

    private void heapifyUp(int i)
    {
        // Print heap state after swap
//        System.out.print("  Current heap: ");
//        for (int k = 0; k < minHeap.size(); k++) {
//            System.out.print(minHeap.get(k).getValue() + " ");
//        }
//        System.out.println();

        int parent;
        if (i == 0) {
            parent = 0;
        }else parent = (i-1)/2;

        if (minHeap.get(parent).getValue() > minHeap.get(i).getValue() && i > 0)
        {
            HeapMemberGeneric swapedHeapMember = minHeap.get(i);
            swapedHeapMember.setIndex(parent);
            minHeap.get(parent).setIndex(i);
            minHeap.set(i,minHeap.get(parent));
            minHeap.set(parent,swapedHeapMember);

            // Print heap state after swap
            System.out.print("  Current heap: ");
            for (int k = 0; k < minHeap.size(); k++) {
                System.out.print(minHeap.get(k).getValue() + " ");
            }
            System.out.println();

            heapifyUp(parent);
        }
    }

    private void heapifyDown(int i)
    {
      // TODO
      /**
       *    Let n= length(H)
            If 2i>n then
            Terminate with H unchanged
            Else if 2i<n then
            Let left=2i, and right=2i+1
            Let j be the index that minimizes key[H[left]] and key[H[right]]
            Else if 2i=n then
            Let j =2i
            Endif
            If key[H[j]] < key[H[i]] then
            swap the array entries H[i] and H[j]
            Heapify-down(H, j)
            Endi
       */

        int n = minHeap.size();
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smaller = i; // will store the index of the smaller child

        //Find smallest child
        if ((left < n) && (minHeap.get(left).getValue() < minHeap.get(smaller).getValue())) { //left child is in heap and is smaller
            smaller = left;
        }
        if ((right < n) && (minHeap.get(right).getValue() < minHeap.get(smaller).getValue())) { //right child is in heap and is smaller
            smaller = right;
        }
        if (smaller == i) { //parent is smallest
            return; //do nothing (no swaps necessary)
        }

        //Swap parent with smaller child
        HeapMemberGeneric currentNode = minHeap.get(i); //return currentNode object
        HeapMemberGeneric smallerNode = minHeap.get(smaller); //return smallerNode object
        minHeap.set(i, smallerNode); //set old index with smaller node
        minHeap.set(smaller,currentNode); //move old parent down (swap)
        smallerNode.setIndex(i); //set smaller node to new larger index (old index)
        currentNode.setIndex(smaller); //set old parent node to larger index (smaller is the index of a node lower in tree, so 'smaller' value > 'i'

        // recurse down
        heapifyDown(smaller); //start at node that we just moved down to see if we need to maintain structure till bottom of heap
    }

    //added for debug
    public int size() {
        return minHeap.size();
    }

    public HeapMemberGeneric get(int index) {
        return minHeap.get(index);
    }


    /**
     * buildHeap(ArrayList<HeapMember> heap_members)
     * Given an ArrayList of Regions, build a min-heap keyed on each HeapMember's minDist
     * Time Complexity - O(nlog(n)) or O(n)
     *
     * @param heap_members
     */
    public void buildHeap(ArrayList<HeapMemberGeneric> heap_members) {

        for(int i = 0; i < heap_members.size(); i++){
            heap_members.get(i).setIndex(i);
            minHeap.add(heap_members.get(i));
        }
        int firstIndex = (heap_members.size()/2)-1;

        for(int k = firstIndex; k > -1; k--){
            heapifyDown(k);
        }
    }

    /**
     * insertNode(HeapMemberGeneric in)
     * Insert a HeapMemberGeneric into the heap.
     * Time Complexity - O(log(n))
     *
     * @param in - the HeapMemberGeneric to insert.
     */
    public void insertNode(HeapMemberGeneric in) {
        minHeap.add(in); //add to end
        int index = getSize() - 1;
        in.setIndex(index); //set index to last index
        heapifyUp(index); //reorganize heap
    }

    /**
     * findMin()
     * Time Complexity - O(1)
     *
     * @return the minimum element of the heap.
     */
    public HeapMemberGeneric findMin() {
        if (minHeap.isEmpty()) {
            return null;
        }
        return minHeap.get(0);
    }

    /**
     * extractMin()
     * Time Complexity - O(log(n))
     *
     * @return the minimum element of the heap, AND removes the element from said heap.
     */
    public HeapMemberGeneric extractMin() {
        if (minHeap.isEmpty()) {
            return null;
        }

        HeapMemberGeneric min = minHeap.get(0); //save min element

        HeapMemberGeneric last = minHeap.remove(minHeap.size()-1); //save smallest element (size - 1 gives index of last element)

        //Check to see if heap is empty after removing an element
        if (!minHeap.isEmpty()) { //as long as not empty, fix heap
            minHeap.set(0, last); //set root to smallest element
            minHeap.get(0).setIndex(0); //change index of last element to root
            heapifyDown(0); //move last element back to last element
        }
        return min;
    }

    /**
     * delete(HeapMemberGeneric gen)
     * Deletes an element in the min-heap given a member to delete.
     * Sets index to -1 to indicate that the HeapMemberGeneric is no longer in the heap.
     * Time Complexity - O(log(n))
     *
     * @param gen - the member to be deleted in the min-heap.
     */
    public void delete(HeapMemberGeneric gen) {
        if (gen == null || minHeap.isEmpty()) {
            return;
        }

        int index = gen.getIndex();
        if (index == -1) { //check if gen is already removed from heap
            return;
        }

        HeapMemberGeneric last = minHeap.remove(minHeap.size()-1); //remove last element

        //If element being deleted is the last element
        if (minHeap.size() == gen.getIndex()) {
            gen.setIndex(-1);
            return;
        }

        //If element being deleted is not the last element
        minHeap.set(index, last); //replace node to delete as last node
        minHeap.get(index).setIndex(index); //update index of last node (moved to old node)
        if (last.getValue() < gen.getValue()) {
            heapifyUp(index);
        }
        else if (last.getValue() > gen.getValue()) {
            heapifyDown(index);
        }
        gen.setIndex(-1); //set index to -1 to indicate that HeapMemberGeneric is no longer in the heap
        return;
    }

    /**
     * changeKey(HeapMemberGeneric r, int newKey)
     * Changes the attribute value of HeapMemberGeneric s to newKey and updates the heap.
     * Time Complexity - O(log(n))
     *
     * @param r       - the HeapMemberGeneric in the heap that needs to be updated.
     * @param newKey - the new value of HeapMemberGeneric r in the heap
     */
    public void changeKey(HeapMemberGeneric r, int newKey) {
        if (r == null) {
            return;
        }

        int oldkey = r.getValue();
        r.setValue(newKey);

        if (oldkey > newKey) {
            heapifyUp(r.getIndex());
        }
        else if (oldkey < newKey) {
            heapifyDown(r.getIndex());
        }
        //else keys are equal and don't need to update heap
    }

    /*
     *  contains(HeapMemberGeneric m)
     *  Checks if the HeapMemberGeneric m is in the heap.
     *  HeapMemberGeneric m, must have been added to the heap previously.
     *  Time Complexity - O(1)
     *
     *  @param m - the HeapMemberGeneric to check if it is in the heap.
     *
     */

    public boolean contains(HeapMemberGeneric m) {
        if(m.getIndex() == -1) return false;
        return true;
    }

    public int getSize() {
        return minHeap.size();
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < minHeap.size(); i++) {
            output += "<index: " + minHeap.get(i).getIndex() + ", id: " + minHeap.get(i).getId() + ", value: " + minHeap.get(i).getValue() + ">\n";
        }
        return output;
    }


    /* Time Complexity - O(1)
     *
     * @return the minimum element of the heap.
     */

    public ArrayList<HeapMemberGeneric> toArrayList() {
        return minHeap;
    }

}
