import java.io.*;

class BuildDomino {
    public static int rankBound = 3;
    public static String inputLine = "> ";
    public static String secondaryInputLine = ">> ";
    public static String notFound = "command not found";
    
    public static void main(String[] args) {
	Element w = null;
	intro();
	while(true) {
	    w = prompt(w);
	}
    }

    public static int[] getArray() throws NumberFormatException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String userInput = null;

	while (userInput == null) {
	    try {
		userInput = br.readLine();
	    }
	    catch (IOException e) {
		System.out.println("Error, try again.");
	    }
	}

	String[] items = userInput.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
	
	int[] results = new int[items.length];
	
	for (int i = 0; i < items.length; i++) {
	    results[i] = Integer.parseInt(items[i]);
	}
	
	return results;
    }

    public static int getInt(int lowBound, String message) {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String userInput = null;
	int answer = lowBound - 1;
	
	while (answer < lowBound) {
	    System.out.print(message);
	    try {
		userInput = br.readLine();
	    }
	    catch (IOException e) {
		System.out.println("Error!");
		System.exit(1);
	    }
	    try {
		answer = Integer.parseInt(userInput);
	    }
	    catch (NumberFormatException e) {
		System.out.println("Invalid");
	    }
	}
	return answer;
    }

    public static String getString(String message) {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String userInput = null;
	
	while (userInput == null) {
	    System.out.print(message);
	    try {
		userInput = br.readLine();
	    }
	    catch (IOException e) {
		System.out.println("Error!");
		System.exit(1);
	    }
	}
	return userInput;
    }
    
    public static void printHelp() {
	System.out.println("");
	try {
	    FileInputStream fis = new FileInputStream("../resources/help.txt");
	    //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    int content;
	    while ((content = fis.read()) != -1) {
		// convert to char and display it
		System.out.print((char) content);
	    }
	}
	catch (FileNotFoundException fnfe) { 
            System.out.println(fnfe.getMessage());
        } 
	catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("");
    }

    public static void intro() {
	System.out.println("");
	System.out.println("BuildDomino version 0.1.");
	System.out.println("Enter help if you need assistance.");
	System.out.println("");
    }

    public static void nullElement() {
	System.out.println("Please enter an element first.");
    }

    public static Element prompt(Element w) {
	Tableau tR = null;
	Element wInv = null;
	Tableau tL = null;
	if (w != null) {
	    tR = new Tableau(w);
	    wInv = w.findInverse();
	    tL = new Tableau(wInv);
	}
	String choice = getString(inputLine);
	switch (choice) {
	case "generators":
	    w = fromRE();
	    break;
	case "permutation":
	    w = fromPerm();
	    break;
	case "print":
	    if (w == null) {
		nullElement();
	    }
	    else {
		w.printPerm();
	    }
	    break;
	case "reduced":
	    if (w == null) {
		nullElement();
	    }
	    else {
		w.findRE().print();
	    }
	    break;
	case "length":
	    if (w == null) {
		nullElement();
	    }
	    else {
		System.out.println(w.length());
	    }
	    break;
	case "descent":
	    if (w == null) {
		nullElement();
	    }
	    else {
		System.out.print("Right: ");
		w.rightDescent().print();
		System.out.print("Left: ");
		w.leftDescent().print();
	    }
	    break;
	case "inverse":
	    if (wInv == null) {
		nullElement();
	    }
	    else {
		wInv.printPerm();
	    }
	    break;
	case "righttikz":
	    if (tR == null) {
		nullElement();
	    }
	    else{
		tR.tikzDraw();
	    }
	    break;
	case "lefttikz":
	    if (tL == null) {
		nullElement();
	    }
	    else{
		tL.tikzDraw();
	    }
	    break;
	case "rightdraw":
	    if (tR == null) {
		nullElement();
	    }
	    else{
		tR.screenDraw();
	    }
	    break;
	case "leftdraw":
	    if (tL == null) {
		nullElement();
	    }
	    else{
		tL.screenDraw();
	    }
	    break;
	case "bad":
	    if (tL == null) {
		nullElement();
	    }
	    else{
		System.out.println(w.isBad());
	    }
	    break;
	case "help":
	    printHelp();
	    break;
	case "quit": case "q": case "exit":
	    System.exit(0);
	    break;
	default:
	    System.out.println(choice + " : " + notFound);
	    break;
	}

	return w;
    }

    public static Element fromRE() {
	int rank;
	int[] intArray = null;
	int highGenerator = 0;
	CoxeterElement wCox = null;

	while (wCox == null) {
	    while (intArray == null) {
		System.out.print("Enter an element in terms of generators: \n" + secondaryInputLine);
		try {
		    intArray = getArray();
		}
		catch (NumberFormatException nfe) {
		    System.out.println("Please input a list of positive integers separated by commas.");
		}
	    }
	    
	    rank = getInt(rankBound, "Rank: \n" + secondaryInputLine);
	    
	    try {
		wCox = new CoxeterElement(intArray, rank);
	    }
	    catch (NumberFormatException nfe) {
		System.out.println("Invalid element");
		intArray = null;
	    }
	}

	Element w = wCox.toPermutation();

	return w;
    }

    public static Element fromPerm() {
	int rank = -1;
	Element w = null;
	int[] intArray = null;

	while (w == null) {
	    System.out.print("Enter a signed permutation: \n" + secondaryInputLine);	    

	    try {
		intArray = getArray();
	    }
	    catch (NumberFormatException nfe) {
		System.out.println("Please input a list of integers separated by commas.");
	    }

	    if (intArray != null) {
		try {
		    w = new Element(intArray);
		}
		catch (IllegalArgumentException iae) {
		    System.out.println("Please use a valid signed permutation.");
		}
	    }
	}
	return w;
    }
}