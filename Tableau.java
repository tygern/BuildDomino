class Tableau {
    private int rank;
    private Domino[] dominoes;
    private int size = 0;
    private int maxLabel;

    public Tableau (int rank) {
	dominoes = new Domino[rank];
    }

    public Tableau (Element w) {
	// rank = w.getRank();
	// dominoes = new Domino[rank];
	// for (int i = 1; i <= rank; i++) {
	//    
	// }
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
	    maxLabel = domLabel;
	    size++;
	}
	else {
	    // Garfinkle's \alpha-function
	}
    }

    public int largestInRow(int row) {
	int column = 0;
	for(int i = 0; i < size; i++) {
	    if (dominoes[i].getFirstBlock().getYVal() == row) {
		column = Math.max(dominoes[i].getFirstBlock().getXVal(), column);
	    }
	}
	return column;
    }

    public int largestInRow(int row, int bound) {
	int column = 0;
	for(int i = 0; i < size; i++) {
	    if (dominoes[i].getLabel() < bound) {
		if (dominoes[i].getFirstBlock().getYVal() == row) {
		    column = Math.max(dominoes[i].getFirstBlock().getXVal(), column);
		}
	    }
	}
	return column;
    }
 
    public int largestInCol(int column) {
	int row = 0;
	for(int i = 0; i < size; i++) {
	    if (dominoes[i].getFirstBlock().getXVal() == column) {
		row = Math.max(dominoes[i].getFirstBlock().getYVal(), row);
	    }
	}
	return row;
    }

    public int largestInCol(int column, int bound) {
	int row = 0;
	for(int i = 0; i < size; i++) {
	    if (dominoes[i].getLabel() < bound) {
		if (dominoes[i].getFirstBlock().getXVal() == column) {
		    row = Math.max(dominoes[i].getFirstBlock().getYVal(), row);
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

}