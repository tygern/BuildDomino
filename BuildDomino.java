import javax.swing.JFrame;
import java.io.*;

class BuildDomino {
    public static void main(String[] args) {
	System.out.print("Enter an element of a Coxeter group: ");
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
	
	Element w = new Element(results);
	Tableau t = new Tableau(w);

	t.tikzDraw();

    }
}