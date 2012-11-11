/**
* This class stores information about individual dominoes and gives
* operations to preform elementary calculus on dominoes.
* @author Tyson Gern
* @version 0.1
*/
class Domino {
    private int label; // Positive label of domino
    private Coordinate firstBlock; // Location of top or left block
    private Coordinate secondBlock; // Location of bottom or right block
    private boolean isVertical; // true if vertical

    /**
     * This constructs a domino with a specific orientation and in a
     * specific position.
     * @param label The label of the domino
     * @param xVal The x-coordinate of the first block
     * @param yVal The y-coordinate of the first block
     * @param isVertical The orientation of the domino
     */
    public Domino(int label, int xVal, int yVal, boolean isVertical) {
	this.label = label;
	firstBlock = new Coordinate(xVal, yVal);
	this.isVertical = isVertical;
	this.placeSecondBlock();
    }

    /**
     * This constructs a domino from a signed integer.  According to
     * convention, the domino is vertical if the integer is negative,
     * and is horizontal if the integer is positive.  The domino will
     * temporarily be placed at (0,0), but this is a placeholder
     * coordinate.
     * @param signedLabel The label of the domino
     */
    public Domino(int signedLabel) {
	label = Math.abs(signedLabel);
	if (signedLabel > 0) {
	    isVertical = false;
	}
	else {
	    isVertical = true;
	}
	firstBlock  = new Coordinate(0, 0);
	this.placeSecondBlock();
    }

    /**
     * This method places the second block of a domino according to
     * the orientation of the domino and the placement of the first
     * block.
     * @return Nothing
     */
    private void placeSecondBlock() {
	int secondX;
	int secondY;
	if (isVertical) {
	    secondX = firstBlock.getXVal();
	    secondY = firstBlock.getYVal() + 1;
	}
	else {
	    secondX = firstBlock.getXVal() + 1;
	    secondY = firstBlock.getYVal() ;
	}
	secondBlock = new Coordinate(secondX, secondY);
    }

    /**
     * This method changes the orientation of the domino and moves the
     * second block accordingly.
     * @return Nothing
     */
    public void flipDomino() {
	this.isVertical = !(this.isVertical);
	this.placeSecondBlock();
    }

    /**
     * This method moves the domino's first block to (xVal, yVal) and
     * moves the second block accordingly.
     * @param xVal The x-coordinate of the first block
     * @param yVal The y-coordinate of the first block
     * @return Nothing
     */
    public void moveDomino(int xVal, int yVal) {
	firstBlock.setXVal(xVal);
	firstBlock.setYVal(yVal);
	placeSecondBlock();
    }

    /**
     * This method gets the coordinates of the first block
     * @return The coordinates pair of the first block
     */
    public Coordinate getFirstBlock() {
	return firstBlock;
    }

    /**
     * This method gets the coordinates of the second block
     * @return The coordinates pair of the second block
     */
    public Coordinate getSecondBlock() {
	return secondBlock;
    }

    /**
     * This method gets the label of the domino
     * @return The label of the domino
     */
    public int getLabel() {
	return label;
    }

    /**
     * This method gets the orientation of the domino
     * @return true if the domino if vertical, otherwise false
     */
    public boolean getIsVertical() {
	return isVertical;
    }

    /**
     * This method counts the number of blocks that the domino has in
     * common with another domino
     * @param other The other domino
     * @return The number of blocks the dominoes have in common
     */
    public int overlap(Domino other) {
	int count = 0;
	if (this.firstBlock.equals(other.firstBlock))
	    count++;
	if (this.firstBlock.equals(other.secondBlock))
	    count++;
	if (this.secondBlock.equals(other.firstBlock))
	    count++;
	if (this.secondBlock.equals(other.secondBlock))
	    count++;
	return count;
    }
    
    /**
     * This method determines which of two dominoes has a larger
     * label.
     * @param other The other domino
     * @return true if the label of the current domino is larger than
     * the label of other.
     */
    public boolean biggerLabel(Domino other) {
	if (this.label > other.label) {
	    return true;
	}
	return false;
    }
    
    /**
     * This method prints the label, coordinates, and orientation of
     * the given domino to the terminal.
     * @return Nothing
     */
    public void printInfo() {
	System.out.println("Label: " + label);
	System.out.println("First block at (" + firstBlock.getXVal() + "," + firstBlock.getYVal() + ")");	
	System.out.println("Second block at (" + secondBlock.getXVal() + "," + secondBlock.getYVal() + ")");
	if (isVertical) {
	    System.out.println("Vertical");
	}
	else {
	    System.out.println("Horizontal");
	}
    }

}