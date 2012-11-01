import java.io.*;

class BuildDomino {
    public static int rankBound = 3;
    public static String inputLine = "> ";
    public static String notFound = "command not found";
    
    public static void main(String[] args) {
	//	mainMenu();
	Element w = null;
	intro();
	while(true) {
	    w = prompt(w);
	}
    }

    public static int[] getArray() {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String userInput = null;
	try {
	    userInput = br.readLine();
	} catch (IOException e) {
	    System.out.println("Error!");
	    System.exit(1);
	}

	String[] items = userInput.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
	
	int[] results = new int[items.length];
	
	for (int i = 0; i < items.length; i++) {
	    try {
		results[i] = Integer.parseInt(items[i]);
	    } catch (NumberFormatException nfe) {
		System.out.println("Error.  Please input a list of integers separated by commas");
		System.exit(1);
	    };
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
	    FileInputStream fis = new FileInputStream("help.txt");
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
	
	System.out.print("Enter an element in terms of generators: \n" + inputLine);
	int[] intArray = getArray();

	rank = getInt(rankBound, "Rank: \n" + inputLine);

	CoxeterElement wCox = new CoxeterElement(intArray, rank);
	Element w = wCox.toPermutation();

	return w;
    }

    public static Element fromPerm() {
	int rank = -1;
	
	System.out.print("Enter a signed permutation: \n" + inputLine);
	int[] intArray = getArray();
	Element w = new Element(intArray);
	
	return w;
    }
}