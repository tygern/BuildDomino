/**
* This class stores a tableau of dominoes, and allows a user to add a
* domino to the tableau using a technique called "shuffling" developed
* by Devra Garfinkle.  Please see README.md for more information about
* Garfinkle's techniques.
* @author Tyson Gern
* @version 0.1
*/
class Tableau {
    private int rank; // The rank (max size) of a tableau
    private Domino[] dominoes; // array of dominoes in a tableau
    private int size = 0; // number of dominoes in a tableau
    private int maxLabel = 0; // largest label in the tableau

    /**
     * This constructs an empty tableau of a certain rank.
     * @param rank The rank of the tableau
     */
    public Tableau (int rank) {
	// Create an empty Domino array and set the rank.
	dominoes = new Domino[rank];
	this.rank = rank;
    }

    /**
     * This constructs a tableau from an element w of a Coxeter group.
     * @param w The element of the Coxeter group
     */
    public Tableau (Element w) {
	rank = w.getRank();
	dominoes = new Domino[rank];
	Domino temp;

	// Add a domino for each entry in w.
	for (int i = 0; i < rank; i++) {
	    temp = new Domino(w.mapsTo(i + 1));
	    addDomino(temp);
	}
    }
    
    /**
     * This method gets a domino in a certain position in dominoes.
     * @param position The index in dominoes
     * @return The domino at position
     */
    public Domino getDomino(int position) {
	assert(position < size);
	return dominoes[position];
    }

    /**
     * This method returns the current size of the tableau.
     * @return The size of the tableau
     */
    public int getSize() {
	return size;
    }

    /**
     * This method returns the rank, or maximum size, of the tableau.
     * @return The rank of the tableau
     */
    public int getRank() {
	return rank;
    }

    /**
     * This method returns the width of the tableau.
     * @return The width of the tableau
     */
    public int maxWidth() {
	return largestInRow(1);
    }

    /**
     * This method returns the height of the tableau.
     * @return The height of the tableau
     */
    public int maxHeight() {
	return largestInCol(1);
    }

    /**
     * This method adds a domino to a tableau using Garfinkle's
     * algorithms.  Please see README.md for more information about
     * Garfinkle's algorithm.
     * @param current The domino to add
     * @return Nothing
     */
    public void addDomino(Domino current) {
	int domLabel = current.getLabel();

	// If biggest, then add on to end of...
	if (domLabel > maxLabel) {
	    // first column if vertical.
	    if (current.getIsVertical()) {
		current.moveDomino(1, largestInCol(1) + 1);
	    }
	    // first row if horizontal.
	    else {
		current.moveDomino(largestInRow(1) + 1, 1);
	    }

	    // Update tableau information
	    dominoes[size] = current;
	    size++;
	    maxLabel = domLabel;
	}
	// If not biggest, use \alpha map.
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

    /**
     * This method gets the largest x-coordinate of a domino in a
     * given row
     * @param row The row
     * @return The largest x-coordinate in row
     */
    public int largestInRow(int row) {
	return largestInRow(row, rank + 1);
    }

    /**
     * This method gets the largest x-coordinate of a domino with
     * label less than bound in a given row
     * @param row The row
     * @param bound The bound
     * @return The largest x-coordinate in row, given the bound
     */
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
 
    /**
     * This method gets the largest y-coordinate of a domino in a
     * given column
     * @param column The column
     * @return The largest y-coordinate in column
     */
    public int largestInCol(int column) {
	return largestInCol(column, rank + 1);
    }

    /**
     * This method gets the largest y-coordinate of a domino with
     * label less than bound in a given column
     * @param col The column
     * @param bound The bound
     * @return The largest y-coordinate in column, given the bound
     */
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
 
    /**
     * This method prints the coordinates and orientation of
     * the domino with a given label in the tableau
     * @param label The label
     * @return Nothing
     */
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

    /**
     * This method prints the labels, coordinates, and orientation of
     * the dominos in the tableau
     * @return Nothing
     */
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

    /**
     * This method finds the location of a domino with a certain label
     * in the tableau.
     * @param label The label
     * @return The index of the domino in the array dominoes
     */
    public int findLabel(int label) {
	for (int i = 0; i < size; i++) {
	    if (dominoes[i].getLabel() == label) {
		return i;
	    }
	}
	return -1;
    }

    /**
     * This method shuffles a domino with a given label into the
     * tableau according to Garfinkle's algorithm.  Please see
     * README.md for more information about Garfinkles algorithms.
     * @param label The label
     * @return Nothing
     */
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

    /**
     * This method calculates how much a given domino overlapps the
     * tableau.
     * @param position The index of the domino in the array dominoes
     * @return The number of blocks that the domino overlapps the
     * tableau
     */
    public int overlap(int position) {
	int count = 0;
	for (int i = 0; i < size; i++) {
	    if (i != position) {
		count += dominoes[i].overlap(dominoes[position]);
	    }
	}
	return count;
    }

    /**
     * This method outputs tikz commands to draw the tableau.  See
     * README.md for more information about tikz.
     * @return Nothing
     */
    public void tikzDraw() {
	System.out.println("\\begin{tikzpicture}[node distance=0 cm,outer sep = 0pt]");
	System.out.println("\\tikzstyle{ver}=[rectangle, draw, thick, minimum width=1cm, minimum height=2cm]");        
	System.out.println("\\tikzstyle{hor}=[rectangle, draw, thick, minimum width=2cm, minimum height=1cm]");
	
	for (int i = 0; i < size; i++) {
	    Domino current = dominoes[i];
	    int xDom = current.getFirstBlock().getXVal();
	    int yDom = current.getFirstBlock().getYVal();
	    boolean vert = current.getIsVertical();
	    int label = current.getLabel();
	    if (vert) {
		System.out.println("\\node[ver] at (0 + " + xDom + ", 4 - " + yDom + ") {" + label + "};");	
	    }
	    else {
		System.out.println("\\node[hor] at (.5 + " + xDom + ", 4.5 - " + yDom + ") {" + label + "};");	
	    }
	}
	System.out.println("\\end{tikzpicture}");
    }
}