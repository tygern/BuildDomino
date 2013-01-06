/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import java.util.*; 

/**
* This class stores an element of a Coxeter group of rank "rank" as a
* product of generators.
* @author Tyson Gern (tygern@gmail.com)
*/
abstract class Expression {
    protected int[] generators;
    protected int rank = 0;
    protected int length;

    abstract Element toPermutation();

    abstract Expression reduce();

    /**
     * This method gets the rank of the TypeDExpression
     * @return The rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * This method gets the length of the TypeDExpression
     * @return The length
     */
    public int getLength() {
        return length;
    }

    /**
     * This method returns the nth generator in the given expression
     * for the TypeDExpression.
     * @param n The place of the desired generator
     * @return The label of the generator
     */
    public int nthGenerator(int n) {
        if ((n >= 0) && (n < length)) {
            return generators[n];
        }
        return 0;
    }

    /**
     * This method returns a string representing the given expression.
     * @return A string of the expression
     */
    public String toString() {
        String output = "(";
        if (length > 0) {
            for (int i = 0; i < length - 1; i++) {
                output += (generators[i] + ", ");
            }
            output += generators[length - 1];
        }
        
        return output += ")";
    }   

    /**
     * This method decides if two elements of a Coxeter group are
     * equal.
     * @param other The other element
     * @return true if the two elements are equal
     */
    public boolean equals(Expression other) {
        if (getClass() != other.getClass()) return false;
        if (rank != other.rank) return false;
        return (toPermutation().equals(other.toPermutation()));
   }

    /**
     * This method tells if an expression is reduced
     * element.
     * @return true if the expression is reduced.
     */
    public boolean isReduced() {
        return (length == reduce().length);
    }

}
