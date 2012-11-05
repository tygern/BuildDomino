import java.util.*;

class Set {
    private ArrayList<Integer> elements = new ArrayList<Integer>();
    
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

    public void remove(int other) {
	if (elements.contains(other)) {
	    elements.remove(elements.indexOf(other));
	}
    }

    public int getSize() {
	return elements.size();
    }

    public boolean isEmpty() {
	return elements.isEmpty();
    }

    private void addSet(Set other) {
	for (int i=0; i < other.elements.size(); i++) {
	    add(other.elements.get(i));
	}
    }

    public Set union(Set other) {
	Set result = new Set();
	result.addSet(this);
	result.addSet(other);
	return result;
    }
    
    public Set intersection(Set other) {
	Set result = new Set();
	for (int i = 0; i < elements.size(); i++) {
	    if (other.elements.contains(elements.get(i))) {
		result.add(elements.get(i));
	    }
	}
	return result;
    }
    
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