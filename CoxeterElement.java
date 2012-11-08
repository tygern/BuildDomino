import java.util.*; 

/**
* This class stores an element of a Coxeter group of type D and rank
* "rank" as a product of generators.  We use "2" as our branch node.
* @author Tyson Gern
* @version 0.1
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
    public CoxeterElement(int[] input, int rank) {
	boolean validElement = true;
	
	// Check all generators are legal
	for(int i = 0; i < input.length; i++) {
	    if ((input[i] > rank) | (input[i] < 1)) {
		validElement = false;
	    }
	}
	
	if (validElement) {
	    this.rank = rank;
	    this.length = input.length;
	    this.expression = new int[length];
	    for(int i = 0; i < input.length; i++) {
		expression[i] = input[i];
	    }
	}
	else {
	    System.out.println("Invalid element");
	}
    }

    /**
     * This method converts an element of a Coxeter group given as a
     * product of commuting generators to a signed permutation.
     * @return the corresponding signed permutation
     */
    public Element toPermutation() {
	int[] permutation = new int[rank];
	int temp;
	int generator;
	Element answer;

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
		permutation[0] = -1*permutation[1];
		permutation[1] = -1*temp;
	    }
	}
	answer = new Element(permutation);
	return answer;
    }

    public CoxeterElement reduce() {
	return toPermutation().findRE();
    }

    /**
     * This method prints the given reduced expression.
     * @return Nothing
     */
    public void print() {
	System.out.print("(");
	if (length > 0) {
	    for (int i = 0; i < length - 1; i++) {
		System.out.print(expression[i] + ", ");
	    }
	    System.out.print(expression[length - 1]);
	}
	System.out.println(")");
    }   
}
