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
class TypeDExpression extends Expression{
    /**
     * This constructs an element of a Coxeter group of rank "rank"
     * given a list of generators.
     * @param input The element of the Coxeter group as a product of
     * generators
     * @param rank The rank of the Coxeter group
     */
    public TypeDExpression(int[] input, int rank) throws NumberFormatException{

        // Check all generators are legal
        for(int i = 0; i < input.length; i++) {
            if ((input[i] > rank) | (input[i] < 1)) {
                throw new NumberFormatException();
            }
        }
        
        this.rank = rank;
        this.length = input.length;
        this.generators = new int[length];
        for(int i = 0; i < input.length; i++) {
            generators[i] = input[i];
        }
    }

    /**
     * This method converts an element of a Coxeter group given as a
     * product of commuting generators to a signed permutation.
     * @return the corresponding signed permutation
     */
    public TypeD toPermutation() {
        TypeD answer;
        int generator;

        answer = new TypeD(rank);

        for (int i = 0; i < length; i++) {
            generator = generators[i];
            if (generator > 1) answer.switchPlaces(generator - 1, generator - 2);
            else answer.switchPlaces(0, 1, -1);
        }

        return answer;
    }

    /**
     * This method prints the a reduced expression for the given
     * element.
     * @return a reduced expression
     */
    public TypeDExpression reduce() {
        return toPermutation().findRE();
    }

    /**
     * This method tells if an expression is reduced
     * element.
     * @return true if the expression is reduced.
     */
    public boolean isReduced() {
        return (length == reduce().length);
    }

    /**
     * This method multiplies a element given in terms of generators
     * on the right by another signed permutation and reduces the
     * product.
     * @param the other experssion
     * @return a reduced expression for the product of this and other
     */
    public TypeDExpression rightMultiply(TypeDExpression other) {
        if (rank != other.rank) {
            throw new IllegalArgumentException("Invalid rank");
        }
        TypeDExpression result;
        int[] newExpression = new int[length + other.length];
        int i = 0;
        while (i < length) {
            newExpression[i] = generators[i];
            i++;
        }
        while (i < length + other.length) {
            newExpression[i] = other.generators[i - length];
            i++;
        }
        result = new TypeDExpression(newExpression, rank);
        return result.reduce();
    }

    /**
     * This method multiplies a element given in terms of generators
     * on the right by another signed permutation and reduces the
     * product.
     * @param the other experssion
     * @return a reduced expression for the product of this and other
     */
    public TypeDExpression leftMultiply(TypeDExpression other) {
        return other.rightMultiply(this);
    }
}
