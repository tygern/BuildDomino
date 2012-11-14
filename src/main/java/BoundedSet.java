/**
 * This class stores a bounded set of integers.  The methods contained
 * in this class can preform elementary operations on sets.
 * @author Tyson Gern
 * @version 0.1
 */
class BoundedSet {
    private boolean[] elements;
    private int size;
    private int min;
    private int max;
    
    public BoundedSet(int min, int max) {
        this.max = max;
        this.min = min;
        elements = new boolean[max - min + 1];
        
        for (int i = 0; i < max - min + 1; i++) {
            elements[i] = false;
        }
    }

    /**
     * This method determines if an element is in the set.
     * @param other The element in question
     * @return true if the set contains other
     */
    public boolean contains(int other) {
        if (other > max || other < min) {
            return false;
        }
        return elements[other - min];
    }

    /**
     * This method adds an element to a set if it is not already
     * contained in the set.
     * @param other The new element
     * @return Nothing
     */
    public void add(int other) {
        if (!elements[other - min]) {
            size++;
            elements[other - min] = true;
        }
    }

    /**
     * This method removes an element from a set if it is contained in
     * the set.
     * @param other The element to be removed
     * @return Nothing
     */
    public void remove(int other) {
        if (elements[other - min]) {
            size--;
            elements[other - min] = false;
        }
    }

    /**
     * This method gets the number of elements in the set.
     * @return The size of the set
     */
    public int getSize() {
        return size;
    }

    /**
     * This method determines whether the set is empty
     * @return true if the set is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * This prints the elements of the current set in {braces}.
     * @return Nothing
     */
    public void print() {
        System.out.print("{");
        for (int i = 0; i < max - min + 1; i++) {
            if (elements[i]) {
                System.out.print((min + i) + ", ");
            }
        }

        if (!isEmpty()) System.out.print("\b\b");
        System.out.println("}");
    }

}