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

    public void printPerm() {
	System.out.print("[" + oneLine[0]);
	for (int i = 1; i < rank; i++) {
	    System.out.print(", " + oneLine[i]);
	}
	System.out.print("]\n");
    }

    public boolean isRightBad() {
	if (-1*oneLine[1] > oneLine[0] && -1*oneLine[0] > oneLine[2]) return false; // 13
	if (   oneLine[1] > oneLine[2] && -1*oneLine[2] > oneLine[0]) return false; // 31
	
	for(int j = 0; j < rank - 3; j++) {
	    if (oneLine[  j] > oneLine[j+1] && oneLine[j+1] > oneLine[j+2]) return false; //321
	    if (oneLine[  j] > oneLine[j+2] && oneLine[j+2] > oneLine[j+1]) return false; //312
	    if (oneLine[j+1] > oneLine[  j] && oneLine[  j] > oneLine[j+2]) return false; //231
	}

	return true;
    }

    public boolean isLeftBad() {
	return findInverse().isRightBad();
    }

    public boolean isBad() {
	if (commutingGenerators()) {
	    return false;
	}
	return (isRightBad() && isLeftBad());
    }

    public boolean commutingGenerators() {
	int j = 0;
	if (oneLine[0] == 1) {
	    j = 1;
	}
	else if (oneLine[0] == -1) {
	    if (oneLine[1] != -2) {
		return false;
	    }
	    j = 2;
	}
	else if (oneLine[0] == 2) {
	    if (oneLine[1] != 1) {
		return false;
	    }
	    j = 2;
	}
	else if (oneLine[0] == -2) {
	    if (oneLine[1] != -1) {
		return false;
	    }
	    j = 2;
	}
	else {
	    return false;
	}
	
	while (j < rank - 2) {
	    if (oneLine[j] > j+2) {
		return false;
	    }
	    else if (oneLine[j] == j+1) {
		j += 1;
	    }
	    else { // oneLine[j] = j+2
		if (oneLine[j+1] != j+1) return false;
		j += 2;
	    }
	}
	    
	return true;
    }
    
}
