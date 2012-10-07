class Element {
    private int[] oneLine;
    private int rank;

    public Element(int[] input) {
	boolean validElement = true;
	for(int i = 0; i < input.length; i++) {
	    if (Math.abs(i) > input.length) {
		validElement = false;
	    }
	    for(int j = i + 1; j < input.length; j++) {
		if (Math.abs(input[j]) == Math.abs(input[i])) {
		    validElement = false;
		}
	    }
	}
	if (validElement) {
	    this.oneLine = new int[input.length];
	    this.rank = input.length;
	    for(int i = 0; i < input.length; i++) {
		oneLine[i] = input[i];
	    }
	}
	else {
	    System.out.println("Invalid element");
	}
    }

    private Element(int rank) {
	this.rank = rank;
	oneLine = new int[rank];
    }

    public int getRank() {
	return rank;
    }

    public int getSign(int origin) {
	return Math.abs(oneLine[origin - 1])/oneLine[origin -1];
    }

    public int mapsTo(int origin) {
	if ((origin >= 1) & (origin <= rank))
	    return oneLine[origin - 1];
	return 0;
    }

    public int mapsFrom(int target) {
	for(int i = 0; i < rank; i++) {
	    if(oneLine[i] == target)
		return i + 1;
	}
	return 0;
    }

    public Element findInverse() {
	Element inverse = new Element(this.rank);
	for(int i = 0; i < rank; i++) {
	    inverse.oneLine[Math.abs(this.oneLine[i])-1] = this.getSign(i+1)*(i+1);
    	}
	return inverse;
    }
}
