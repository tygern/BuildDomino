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
class Element {
    protected int[] oneLine; // The signed permutation of the element
    protected int rank; // The rank of the Coxeter group containing
                        // the element
}