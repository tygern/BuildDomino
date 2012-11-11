import java.util.*;

/**
 * This class stores a set of integers.  The methods contained in this
 * class can preform elementary operations on sets.
 * @author Tyson Gern
 * @version 0.1
 */
class Set {
    private ArrayList<Integer> elements = new ArrayList<Integer>();
    
    /**
     * This method adds an element to a set if it is not already
     * contained in the set.
     * @param other The new element
     * @return Nothing
     */
    public void add(int other) {
        if (elements.isEmpty()) {
            elements.add(other);
        }
        if (!elements.contains(other)) {
            int index = 0;
            while (index < elements.size() && elements.get(index).intValue() < other ) {
                index++;
            }
            elements.add(index, other);
        }
    }

    /**
     * This method removes an element from a set if it is contained in
     * the set.
     * @param other The element to be removed
     * @return Nothing
     */
    public void remove(int other) {
        if (elements.contains(other)) {
            elements.remove(elements.indexOf(other));
        }
    }

    /**
     * This method gets the number of elements in the set.
     * @return The size of the set
     */
    public int getSize() {
        return elements.size();
    }

    /**
     * This method determines whether the set is empty
     * @return true if the set is empty
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    /**
     * This method adds each element of another set to the current
     * set.
     * @param other The set to be added
     * @return Nothing
     */
    private void addSet(Set other) {
        for (int i = 0; i < other.elements.size(); i++) {
            add(other.elements.get(i));
        }
    }

    /**
     * This method returns the union of the current set and other.
     * @param other The set to be unioned
     * @return The union of this and other
     */
    public Set union(Set other) {
        Set result = new Set();
        result.addSet(this);
        result.addSet(other);
        return result;
    }
    
    /**
     * This method returns the intersection of the current set and
     * other.
     * @param other The set to be intersected
     * @return The intersection of this and other
     */
    public Set intersection(Set other) {
        Set result = new Set();
        for (int i = 0; i < elements.size(); i++) {
            if (other.elements.contains(elements.get(i))) {
                result.add(elements.get(i));
            }
        }
        return result;
    }
    
    /**
     * This prints the elements of the current set in {braces}.
     * @return Nothing
     */
    public void print() {
        System.out.print("{");
        if (!isEmpty()) {
            int i = 0;
            while (i < elements.size() - 1) {
                System.out.print(elements.get(i) + ", ");
                i++;
            }
            System.out.print(elements.get(i));
        }
        System.out.println("}");
    }

}