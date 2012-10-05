class Coordinate {
    private int xVal;
    private int yVal;

    public Coordinate(int xVal, int yVal) {
	this.xVal = xVal;
	this.yVal = yVal;
    }

    public boolean equals(Coordinate other) {
	if ((this.xVal == other.xVal) & (this.yVal == other.yVal)) {
	    return true;
	}
	return false;
    }

    public void setXVal(int xVal) {
	this.xVal = xVal;
    }

    public void setYVal(int yVal) {
	this.yVal = yVal;
    }

    public int getXVal() {
	return this.xVal;
    }

    public int getYVal() {
	return this.yVal;
    }

    public void print() {
	System.out.println("(" + xVal + "," + yVal + ")");
    }

}