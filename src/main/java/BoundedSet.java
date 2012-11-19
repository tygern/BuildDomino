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
    private int length;
    
    public BoundedSet(int min, int max) {
        this.max = max;
        this.min = min;
        length = max - min + 1;
        elements = new boolean[length];
        
        for (int i = 0; i < length; i++) {
            elements[i] = false;
        }
    }

    public BoundedSet(int max) {
        this.max = max;
        this.min = 1;
        length = max;
        elements = new boolean[max - min + 1];
        
        for (int i = 0; i < length; i++) {
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
     * @return The set
     */
    public BoundedSet add(int other) {
        checkRange(other);
        if (!elements[other - min]) {
            size++;
            elements[other - min] = true;
        }
        return this;
    }

    /**
     * This method removes an element from a set if it is contained in
     * the set.
     * @param other The element to be removed
     * @return The set
     */
    public BoundedSet remove(int other) {
        checkRange(other);
        if (elements[other - min]) {
            size--;
            elements[other - min] = false;
        }
        return this;
    }

    private void checkRange(int other) throws IllegalArgumentException {
        if (other < min || other > max) {
            throw new IllegalArgumentException("Out of Range");
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
     * This method decides if two bounded sets contain the same
     * elements
     * @param other The other set
     * @return true if the two sets contain the same elements
     */
    public boolean equals(BoundedSet other) {
        int difference = other.min - min;

        if (difference < 0) return other.equals(this);

        int i = 0;
        int j = 0;
        while (i < difference) {
            if (elements[i]) return false;
            i++;
        }
        while (i < length && j < other.length) {
            if (elements[i] != other.elements[j]) return false;
            i++;
            j++;
        }
        while (i < length) {
            if (elements[i]) return false;
            i++;
        }
        while (j < other.length) {
            if (other.elements[j]) return false;
            j++;
        }
        return true;
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