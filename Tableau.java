class Tableau {
    private int rank;
    private Domino[] dominoes;
    private int size = 0;
    private int maxLabel = 0;

    public Tableau (int rank) {
	dominoes = new Domino[rank];
	this.rank = rank;
    }

    public Tableau (Element w) {
	rank = w.getRank();
	dominoes = new Domino[rank];
	Domino temp;
	for (int i = 0; i < rank; i++) {
	    temp = new Domino(w.mapsTo(i + 1));
	    addDomino(temp);
	}
    }
    
    public void addDomino(Domino current) {
	int domLabel = current.getLabel();
	if (domLabel > maxLabel) {
	    if (current.getIsVertical()) {
		current.moveDomino(1, largestInCol(1) + 1);
	    }
	    else {
		current.moveDomino(largestInRow(1) + 1, 1);
	    }
	    dominoes[size] = current;
	    size++;
	    maxLabel = domLabel;
	}
	else {
	    if (current.getIsVertical()) {
		current.moveDomino(1, largestInCol(1, domLabel) + 1);
	    }
	    else {
		current.moveDomino(largestInRow(1, domLabel) + 1, 1);
	    }
	    dominoes[size] = current;
	    size++;
	    for (int j = domLabel + 1; j <= maxLabel; j++) {
		if (findLabel(j) != -1) {
		    shuffle(j);
		}
	    }
	}
    }

    public int largestInRow(int row) {
	return largestInRow(row, rank + 1);
    }

    public int largestInRow(int row, int bound) {
	int column = 0;
	for(int i = 0; i < size; i++) {
	    if (dominoes[i].getLabel() < bound) {
		if (dominoes[i].getFirstBlock().getYVal() == row) {
		    column = Math.max(dominoes[i].getFirstBlock().getXVal(), column);
		}
		if (dominoes[i].getSecondBlock().getYVal() == row) {
		    column = Math.max(dominoes[i].getSecondBlock().getXVal(), column);
		}
	    }
	}
	return column;
    }
 
    public int largestInCol(int column) {
	return largestInCol(column, rank + 1);
    }

    public int largestInCol(int column, int bound) {
	int row = 0;
	for(int i = 0; i < size; i++) {
	    if (dominoes[i].getLabel() < bound) {
		if (dominoes[i].getFirstBlock().getXVal() == column) {
		    row = Math.max(dominoes[i].getFirstBlock().getYVal(), row);
		}
		if (dominoes[i].getSecondBlock().getXVal() == column) {
		    row = Math.max(dominoes[i].getSecondBlock().getYVal(), row);
		}
	    }
	}
	return row;
    }
 
    public void printDomino(int label) {
	if (size == 0) {
	    System.out.println("The tableau is empty.");
	    return;
	}
	for(int i = 0; i < size; i++) {
	    if (dominoes[i].getLabel() == label) {
		dominoes[i].printInfo();
		return;
	    }
	}
	System.out.println("There is no domino with label " + label + " in the tableau.");
    }

    public void printAll() {
	if (size == 0) {
	    System.out.println("The tableau is empty.");
	    return;
	}
	System.out.println("----------------------");
	for(int i = 0; i < size; i++) {
	    dominoes[i].printInfo();
	    System.out.println("----------------------");
	}
    }

    public int findLabel(int label) {
	for (int i = 0; i < size; i++) {
	    if (dominoes[i].getLabel() == label) {
		return i;
	    }
	}
	return -1;
    }

    public void shuffle(int label) {
	int position = findLabel(label);
	Domino current = dominoes[position];
	int row = current.getFirstBlock().getYVal();
	int col = current.getFirstBlock().getXVal();
	if (position != -1) {
	    if (overlap(position) == 0) {
		// do nothing
	    }
	    else if (overlap(position) == 1) {
		// twist
		if (current.getIsVertical()) {
		    current.flipDomino();
		    current.moveDomino(largestInRow(row + 1, label) + 1, row + 1);
		}
		else {
		    current.flipDomino();
		    current.moveDomino(col + 1, largestInCol(col + 1, label) + 1);
		}
	    }
	    else {
		//slide
		if (current.getIsVertical()) {
		    current.moveDomino(col + 1, largestInCol(col + 1, label) + 1);
		}
		else {
		    current.moveDomino(largestInRow(row + 1, label) + 1, row + 1);
		}
	    }
	}
    }

    public int overlap(int position) {
	int count = 0;
	for (int i = 0; i < size; i++) {
	    if (i != position) {
		count += dominoes[i].overlap(dominoes[position]);
	    }
	}
	return count;
    }

}