/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import java.util.*;

/**
 * This class stores an element of a Coxeter group of type A and rank
 * "size" as a permutation, oneLine.  The methods contained in
 * this class can preform elementary operations on the element.
 * @author Tyson Gern (tygern@gmail.com)
 */
class TypeA extends Element{

    /**
     * This constructs an element from a permutation.
     * @param input The permutation
     */
    public TypeA(int[] input) {
        size = input.length;
        rank = input.length - 1;
        oneLine = new int[size];

        boolean[] number = new boolean[size];
        for (int i = 0; i < size; i++) number[i] = false;
        
        for(int i = 0; i < size; i++) {
            if (input[i] > size || input[i] <= 0 || number[input[i] - 1]) {
                throw new IllegalArgumentException("Invalid permutation");
            }
            oneLine[i] = input[i];
            number[input[i] - 1] = true;
        }
    }
    
    /**
     * This constructs the identity element of a particular rank.
     * @param rank The rank of the element
     */
    protected TypeA(int rank) {
        this.rank = rank;
        this.size = rank + 1;
        oneLine = new int[size];
        for (int i = 0; i < size; i++) {
            oneLine[i] = i + 1;
        }
    }

    /**
     * This method gets the inverse
     * @return The inverse of element
     */
    public TypeA findInverse() {
        return new TypeA(this.invertPermutation());
    }

    /**
     * This method inverts the element
     * @return Nothing
     */
    public void invert() {
        this.oneLine = findInverse().oneLine;
    }

    /**
     * This method multiplies the element on the right by a generator,
     * s.
     * @param s The generator
     * @return Nothing
     */
    private TypeA rightMultiplyS(int s) {
        if (s <= rank && s >= 1) {
            switchPlaces(s-1, s);
        }
        return this;
    }

    /**
     * This method multiplies the element on the left by a generator,
     * s.
     * @param s The generator
     * @return Nothing
     */
    private TypeA leftMultiplyS(int s) {
        if (s <= rank && s >= 1) {
            switchValues(s,s + 1);
        }

        return this;
    }

    /**
     * This method gets the length of the element
     * @return the length of the element.
     */
    public int length() {
        return countInv();
    }

    /**
     * This method returns true if s is in the right descent set of
     * the element, false otherwise.
     * @return true is s is a descent of the element
     */
    protected boolean isRightDescent(int s) {
        if (s >= 1 && s <= rank) {
            return (oneLine[s - 1] > oneLine[s]);
        }
        return false;
    }

    /**
     * This method creates a TypeAExpression reduced expression from a
     * signed permutation.
     * @return a reduced expression
     */
    public TypeAExpression findRE() {
        ArrayList<Integer> generator = new ArrayList<Integer> ();
        TypeA permutation = new TypeA(oneLine);
        
        while (permutation.length() != 0) {
            for (int i = permutation.size; i >= 1; i--) {
                if (permutation.isRightDescent(i)) {
                    generator.add(i);
                    permutation.rightMultiplyS(i);
                }
            }
        }

        int length = generator.size();
        int[] genArray = new int[length];
        for (int i = 0; i < length; i++) {
            genArray[length - 1 - i] = generator.get(i).intValue();
        }

        TypeAExpression redExp = new TypeAExpression(genArray, permutation.rank);
        return redExp;
        
    }

    /**
     * This method multiplies a permutation on the right by another
     * permutation.
     * @param the other element
     * @return the product of this and other
     */
    public TypeA rightMultiply(TypeA other) {
        if (!compatible(other)) return null;
        return new TypeA(rightMultiplyPerm(other));
    }

    /**
     * This method multiplies a permutation on the left by another
     * permutation.
     * @param the other element
     * @return the product of this and other
     */
    public TypeA leftMultiply(TypeA other) {
        return other.rightMultiply(this);
    }
}
