/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import java.util.*;

/**
 * This class stores an element of a Coxeter group of rank "rank" as a
 * signed permutation, oneLine.  The methods contained in this class
 * can preform elementary operations on the element.
 * @author Tyson Gern (tygern@gmail.com)
 */
abstract class Element {
    protected int[] oneLine; // The signed permutation of the element
    protected int size; // The number of elements acted upon by the element
    protected int rank; // The rank of the Coxeter group containing
                        // the element

    abstract boolean isRightDescent(int s);

    abstract Element findInverse();

    abstract int length();

    /**
     * This method gets the size of the element
     * @return The rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * This method gets the sign of the signed of the number that
     * origin maps to.
     * @param origin The origin number
     * @return The sign of the element applied to origin
     */
    public int getSign(int origin) {
        return Math.abs(oneLine[origin - 1]) / oneLine[origin - 1];
    }

    /**
     * This method gets the number that origin maps to.
     * @param origin The origin number
     * @return The element applied to origin
     */
    public int mapsTo(int origin) {
        if ((Math.abs(origin) <= size) && (origin != 0)) {
            if (origin > 0) {
                return oneLine[origin - 1];
            }
            return -1 * oneLine[-1 * origin - 1];
        }
        return 0;
    }

    /**
     * This method gets the number that maps to target.
     * @param target The target number
     * @return The number that element maps to target
     */
    public int mapsFrom(int target) {
        for(int i = 0; i < size; i++) {
            if(oneLine[i] == target)
                return i + 1;
            if(-1 * oneLine[i] == target)
                return -1 * (i + 1);
        }
        return 0;
    }

    /**
     * This method inverts the element
     * @return Nothing
     */
    public void invert() {
        this.oneLine = findInverse().oneLine;
    }

    /**
     * This method returns a string of the one line signed permutation
     * of the element.
     * @return A string that describes the element
     */
    public String toString() {
        String output;
        
        output = "[" + oneLine[0];
        for (int i = 1; i < size; i++) {
            output += (", " + oneLine[i]);
        }
        output += "]";

        return output;
    }

    /**
     * This method counts the number of inversions in the signed
     * permutation of the element.
     * @return the number of inversions
     */
    protected int countInv() {
        return countInv(1);
    }

    /**
     * This method counts the number of inversions in the signed
     * permutation of the element, allowing for the multiplication of
     * factor (usually -1).
     * @return the number of inversions, taking the factor into
     * account.
     */
    protected int countInv(int factor) {
        return countInv(factor, 0, size);
    }

    /**
     * This is a helper method for countInv(int).  It counts the
     * number of inversions between start and end in the signed
     * permutation of the element, allowing for the multiplication of
     * factor (usually -1).
     * @return the number of inversions, taking the factor into
     * account.
     */
    private int countInv(int factor, int start, int end) {
        if (end - start <= 1) {
            return 0;
        }
        else if (end - start == 2) {
            if (factor * oneLine[start] > oneLine[end - 1]) {
                return 1;
            }
            return 0;
        }
        int middle = (start + end) / 2;
        return countInv(factor, start, middle) + countInv(factor, middle, end) + mergeInv(factor, start, end);
    }

    /**
     * This method counts the number of inversions between start and
     * end in the signed permutation of the element tha happen between
     * the first and second half of the array, allowing for the
     * multiplication of factor (usually -1).
     * @return the number of inversions, taking the factor into
     * account.
     */
    private int mergeInv(int factor, int start, int end) {
        int middle = (start + end) / 2;
        int count = 0;

        for (int i = start; i < middle; i++) {
            int value = factor*oneLine[i];
            for (int j = middle; j < end; j++) {
                if (value > oneLine[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    protected void switchPlaces(int place1, int place2, int sign) {
        int temp;
        
        temp = oneLine[place1];
        oneLine[place1] = sign * oneLine[place2];
        oneLine[place2] = sign * temp;
    }

    protected void switchPlaces(int place1, int place2) {
        switchPlaces(place1, place2, 1);
    }

    protected void switchValues(int value1, int value2, int sign) {
        int first;
        int second;
        int loc1 = 0;
        int loc2;
        
        while (Math.abs(oneLine[loc1]) != value1 && Math.abs(oneLine[loc1]) != value2) {
            loc1++;
        }
        first = oneLine[loc1];
        loc2 = loc1 + 1;
        
        while (Math.abs(oneLine[loc2]) != value1 && Math.abs(oneLine[loc2]) != value2) {
            loc2++;
        }
        second = oneLine[loc2];
        oneLine[loc1] = sign * second;
        oneLine[loc2] = sign * first;
    }

    protected void switchValues(int value1, int value2) {
        switchValues(value1, value2, 1);
    }

    /**
     * This method gets the right descent set of the element.
     * @return the right descent set.
     */
    public BoundedSet rightDescent() {
        BoundedSet right = new BoundedSet(1, rank);

        for (int i = 1; i <= rank; i++) {
            if (isRightDescent(i)) {
                right.add(i);
            }
        }

        return right;
    }

    /**
     * This method gets the left descent set of the element.
     * @return the left descent set.
     */
    public BoundedSet leftDescent() {
        return findInverse().rightDescent();
    }

}