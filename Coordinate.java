/**
* This class keeps track of coordinates in the Cartesian plane.
* @author Tyson Gern
* @version 0.1
*/
class Coordinate {
    private int xVal;
    private int yVal;

    /**
     * This constructs an coordinate pair
     * @param xVal The x-coordinate
     * @param yVal The y-coordinate
     */
    public Coordinate(int xVal, int yVal) {
	this.xVal = xVal;
	this.yVal = yVal;
    }

    /**
     * This method decides if two coordinates pairs are equal.
     * @param other The other coordinate pair
     * @return true if they are equal, otherwise false.
     */
    public boolean equals(Coordinate other) {
	if ((this.xVal == other.xVal) & (this.yVal == other.yVal)) {
	    return true;
	}
	return false;
    }

    /**
     * This method sets x-coordinate for a coordinate pair.
     * @param xVal The new x-coordinate
     * @return Nothing.
     */
    public void setXVal(int xVal) {
	this.xVal = xVal;
    }

    /**
     * This method sets y-coordinate for a coordinate pair.
     * @param yVal The new y-coordinate
     * @return Nothing.
     */
    public void setYVal(int yVal) {
	this.yVal = yVal;
    }

    /**
     * This method returns the x-coordinate for a coordinate pair.
     * @return The x-coordinate for a coordinate pair.
     */
    public int getXVal() {
	return this.xVal;
    }

    /**
     * This method returns the y-coordinate for a coordinate pair.
     * @return The y-coordinate for a coordinate pair.
     */
    public int getYVal() {
	return this.yVal;
    }

    /**
     * This method prints the (x,y)-pair for the set of coordinates.
     * @return Nothing.
     */
    public void print() {
	System.out.println("(" + xVal + "," + yVal + ")");
    }

}