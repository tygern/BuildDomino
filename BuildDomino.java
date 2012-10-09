import java.io.*;

class BuildDomino {
    public static int rankBound = 3;
    
    public static void main(String[] args) {
	mainMenu();
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

    public static void mainMenu() {
	while (true) {
	    System.out.println("");
	    System.out.println("---------------------------------------------");
	    System.out.println("----------------  MAIN MENU  ----------------");
	    System.out.println("---------------------------------------------");
	    System.out.println("1: Enter an element in terms of generators");
	    System.out.println("2: Enter a signed permutation");
	    System.out.println("0: Quit");
	    System.out.println("---------------------------------------------");
	    System.out.println("");

	    int choice = getInt(0, "Make a choice: ");
	    switch (choice) {
	    case 0: System.exit(0);
            case 1: fromRE();
		break;
            case 2: fromPerm();
		break;
            default: System.out.println("Wrong choice");
	    }
	}
    }

    public static void secondMenu(Tableau t, Element w) {
	while (true) {
	    System.out.println("");
	    System.out.println("---------------------------------------------");
	    System.out.println("--------------  TABLEAU MENU  ---------------");
	    System.out.println("---------------------------------------------");
	    System.out.println("1: Print the signed permutation");
	    System.out.println("2: Print tableau in TikZ code");
	    System.out.println("3: Print tableau on screen");
	    System.out.println("4: Return to Main Menu");
	    System.out.println("0: Quit");
	    System.out.println("---------------------------------------------");
	    System.out.println("");

	    int choice = getInt(0, "Make a choice: ");
	    switch (choice) {
	    case 0: System.exit(0);
            case 1: w.printPerm();
		break;
            case 2: t.tikzDraw();
		break;
            case 3: t.screenDraw();
		break;
            case 4: mainMenu();
		break;
            default: System.out.println("Wrong choice");
	    }
	    secondMenu(t,w);
	}
    }

    public static void fromRE() {
	int rank;
	
	System.out.print("Enter an element in terms of generators: ");
	int[] intArray = getArray();

	rank = getInt(rankBound, "Rank: ");

	CoxeterElement wCox = new CoxeterElement(intArray, rank);
	Element w = wCox.toPermutation();
	Tableau t = new Tableau(w);

	secondMenu(t,w);
    }

    public static void fromPerm() {
	int rank = -1;
	
	System.out.print("Enter a signed permutation: ");
	int[] intArray = getArray();
	Element w = new Element(intArray);

	Tableau t = new Tableau(w);

	secondMenu(t,w);
    }
}