class Domino {
    private int label; // Positive label of domino
    private Coordinate firstBlock;
    private Coordinate secondBlock;
    private boolean isVertical;

    public Domino(int label, int xVal, int yVal, boolean isVertical) {
	this.label = label;
	firstBlock  = new Coordinate(xVal, yVal);
	this.isVertical = isVertical;
	this.placeSecondBlock();
    }

    public Domino(int signedLabel) {
	label = Math.abs(signedLabel);
	if (signedLabel > 0) {
	    isVertical = false;
	}
	else {
	    isVertical = true;
	}
    }

    // Should be private?
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

    public void flipDomino() {
	this.isVertical = !(this.isVertical);
	this.placeSecondBlock();
    }

    public void moveDomino(int xVal, int yVal) {
	firstBlock.setXVal(xVal);
	firstBlock.setYVal(yVal);
	placeSecondBlock();
    }

    public Coordinate getFirstBlock() {
	return firstBlock;
    }

    public Coordinate getSecondBlock() {
	return secondBlock;
    }

    public int getLabel() {
	return label;
    }

    public boolean getIsVertical() {
	return isVertical;
    }

    public boolean overlap(Domino other) {
	if (this.firstBlock.equals(other.firstBlock))
	    return true;
	if (this.firstBlock.equals(other.secondBlock))
	    return true;
	if (this.secondBlock.equals(other.firstBlock))
	    return true;
	if (this.secondBlock.equals(other.secondBlock))
	    return true;
	return false;
    }
    
    public boolean biggerLabel(Domino other) {
	if (this.label > other.label) {
	    return true;
	}
	return false;
    }
    
    public void printInfo() {
	System.out.println("Label: " + label);
	System.out.println("First block at (" + firstBlock.getXVal() + "," + firstBlock.getYVal() + ")");	
	System.out.println("Second block at (" + secondBlock.getXVal() + "," + secondBlock.getYVal() + ")");
	System.out.println("Vertical? " + isVertical);
    }

}