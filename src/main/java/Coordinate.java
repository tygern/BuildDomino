/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 *
 * This class keeps track of coordinates in the Cartesian plane.
 * @author Tyson Gern (tygern@gmail.com)
 */
class Coordinate {
    private int xVal; // x-coordinate
    private int yVal; // y-coordinate

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
        return ((this.xVal == other.xVal) && (this.yVal == other.yVal));
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
     * This method returns a string of the (x,y)-pair for the set of
     * coordinates.
     * @return a string of the coordinate pair
     */
    public String toString() {
        return ("(" + xVal + "," + yVal + ")");
    }

}