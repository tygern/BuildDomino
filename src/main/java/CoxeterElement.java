/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import java.util.*; 

/**
* This class stores an element of a Coxeter group of type D and rank
* "rank" as a product of generators.  We use "2" as our branch node.
* @author Tyson Gern (tygern@gmail.com)
*/
class CoxeterElement {
    private int[] expression;
    private int rank = 0;
    private int length;

    /**
     * This constructs an element of a Coxeter group of rank "rank"
     * given a list of generators.
     * @param input The element of the Coxeter group as a product of
     * generators
     * @param rank The rank of the Coxeter group
     */
    public CoxeterElement(int[] input, int rank) throws NumberFormatException{

        // Check all generators are legal
        for(int i = 0; i < input.length; i++) {
            if ((input[i] > rank) | (input[i] < 1)) {
                throw new NumberFormatException();
            }
        }
        
        this.rank = rank;
        this.length = input.length;
        this.expression = new int[length];
        for(int i = 0; i < input.length; i++) {
            expression[i] = input[i];
        }
    }

    /**
     * This method gets the rank of the CoxeterElement
     * @return The rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * This method gets the length of the CoxeterElement
     * @return The length
     */
    public int getLength() {
        return length;
    }

    /**
     * This method decides if two elements of a Coxeter group are
     * equal.
     * @param other The other element
     * @return true if the two elements are equal
     */
    public boolean equals(CoxeterElement other) {
        if (rank != other.rank) return false;
        return (toPermutation().equals(other.toPermutation()));
   }

    /**
     * This method returns the nth generator in the given expression
     * for the CoxeterElement.
     * @param n The place of the desired generator
     * @return The label of the generator
     */
    public int nthGenerator(int n) {
        if ((n >= 0) && (n < length)) {
            return expression[n];
        }
        return 0;
    }

    /**
     * This method converts an element of a Coxeter group given as a
     * product of commuting generators to a signed permutation.
     * @return the corresponding signed permutation
     */
    public TypeD toPermutation() {
        int[] permutation = new int[rank];
        int temp;
        int generator;
        TypeD answer;

        for (int i = 0; i < rank; i++) {
            permutation[i] = i + 1;
        }
        for (int i = 0; i < length; i++) {
            generator = expression[i];
            if (generator > 1) {
                temp = permutation[generator - 2];
                permutation[generator - 2] = permutation[generator - 1];
                permutation[generator - 1] = temp;
            }
            else {
                temp = permutation[0];
                permutation[0] = -1 * permutation[1];
                permutation[1] = -1 * temp;
            }
        }
        answer = new TypeD(permutation);
        return answer;
    }

    /**
     * This method prints the a reduced expression for the given
     * element.
     * @return a reduced expression
     */
    public CoxeterElement reduce() {
        return toPermutation().findRE();
    }

    /**
     * This method tells if an expression is reduced
     * element.
     * @return true if the expression is reduced.
     */
    public boolean isReduced() {
        return (length == toPermutation().findRE().length);
    }

    /**
     * This method multiplies a element given in terms of generators
     * on the right by another signed permutation and reduces the
     * product.
     * @param the other experssion
     * @return a reduced expression for the product of this and other
     */
    public CoxeterElement rightMultiply(CoxeterElement other) {
        if (rank != other.rank) {
            throw new IllegalArgumentException("Invalid rank");
        }
        CoxeterElement result;
        int[] newExpression = new int[length + other.length];
        int i = 0;
        while (i < length) {
            newExpression[i] = expression[i];
            i++;
        }
        while (i < length + other.length) {
            newExpression[i] = other.expression[i - length];
            i++;
        }
        result = new CoxeterElement(newExpression, rank);
        return result.reduce();
    }

    /**
     * This method multiplies a element given in terms of generators
     * on the right by another signed permutation and reduces the
     * product.
     * @param the other experssion
     * @return a reduced expression for the product of this and other
     */
    public CoxeterElement leftMultiply(CoxeterElement other) {
        return other.rightMultiply(this);
    }

    /**
     * This method returns a string representing the given expression.
     * @return A string of the expression
     */
    public String toString() {
        String output = "(";
        if (length > 0) {
            for (int i = 0; i < length - 1; i++) {
                output += (expression[i] + ", ");
            }
            output += expression[length - 1];
        }
        
        return output += ")";
    }   
}
