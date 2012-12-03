/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import java.io.*;

class BuildDomino {
    public static int rankBound = 3;
    public static String inputLine = "> ";
    public static String secondaryInputLine = ">> ";
    public static String notFound = "command not found";
    public static String permLeft = "\\[";
    public static String permRight = "\\]";
    public static String exprLeft = "\\(";
    public static String exprRight = "\\)";
    
    public static void main(String[] args) {
        Element w = null;
        intro();
        while(true) {
            w = prompt(w);
        }
    }

    public static int[] getArray(String left, String right) throws NumberFormatException {
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

        String[] items = userInput.replaceAll(left, "").replaceAll(right, "").split(",");
        
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
        AccessFile message = new AccessFile("help.txt");
        System.out.println();
        message.show();
        System.out.println();
    }

    public static void intro() {
        System.out.println("");
        System.out.println("BuildDomino version 0.2.");
        System.out.println("Enter help if you need assistance.");
        System.out.println("");
    }

    public static void badInput(String choice) {
        System.out.println(choice + " : " + notFound);
    }

    public static void nullElement() {
        System.out.println("Please enter an element first.");
    }

    public static Element prompt(Element w) {
        Tableau tR = null;
        Element wInv = null;
        Element x = null;
        Tableau tL = null;
        Heap wHeap = null;
        String choice, form;
        
        if (w != null) {
            tR = new Tableau(w);
            wHeap = new Heap(w);
            wInv = w.findInverse();
            tL = new Tableau(wInv);
        }

        choice = getString(inputLine);
        switch (choice) {
        case "expression":
            w = fromRE();
            break;
        case "permutation":
            w = fromPerm();
            break;
        case "print":
            if (w == null) nullElement();
            else System.out.println(w);
            break;
        case "rightmultiply":
            if (w == null) {
                nullElement();
                break;
            }
            System.out.println("permutation or expression?");
            form = getString(secondaryInputLine);
            switch (form) {
            case "permutation":
                x = fromPerm();
                System.out.println(w.rightMultiply(x));
                break;
            case "expression":
                x = fromRE(w.getRank());
                System.out.println(w.findRE().rightMultiply(x.findRE()));
                break;
            default:
                badInput(form);
                break;
            }
            break;
        case "leftmultiply":
            if (w == null) {
                nullElement();
                break;
            }
            System.out.println("Permutation or expression?");
            form = getString(secondaryInputLine);
            switch (form) {
            case "permutation":
                x = fromPerm();
                System.out.println(w.leftMultiply(x));
                break;
            case "expression":
                x = fromRE(w.getRank());
                System.out.println(w.findRE().leftMultiply(x.findRE()));
                break;
            default:
                badInput(form);
                break;
            }
            break;
        case "reduced":
            if (w == null) nullElement();
            else System.out.println(w.findRE());
            break;
        case "length":
            if (w == null) nullElement();
            else System.out.println(w.length());
            break;
        case "descent":
            if (w == null) nullElement();
            else {
                System.out.println("Right: " + w.rightDescent());
                System.out.println("Left: " + w.leftDescent());
            }
            break;
        case "inverse":
            if (wInv == null) nullElement();
            else System.out.println(wInv);
            break;
        case "righttikz":
            if (tR == null) nullElement();
            else tR.tikzDraw();
            break;
        case "lefttikz":
            if (tL == null) nullElement();
            else tL.tikzDraw();
            break;
        case "heaptikz":
            if (wHeap == null) nullElement();
            else wHeap.tikzDraw();
            break;
        case "rightdraw":
            if (tR == null) nullElement();
            else tR.screenDraw();
            break;
        case "leftdraw":
            if (tL == null) nullElement();
            else tL.screenDraw();
            break;
        case "heapdraw":
            if (wHeap == null) nullElement();
            else wHeap.screenDraw();
            break;
        case "bad":
            if (tL == null) nullElement();
            else System.out.println(w.isBad());
            break;
        case "help":
            printHelp();
            break;
        case "quit": case "q": case "exit":
            System.exit(0);
            break;
        default:
            badInput(choice);
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
                    intArray = getArray(exprLeft, exprRight);
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
    public static Element fromRE(int rank) {
        int[] intArray = null;
        int highGenerator = 0;
        CoxeterElement wCox = null;

        while (wCox == null) {
            while (intArray == null) {
                System.out.print("Enter an element of rank " + rank + " in terms of generators: \n" + secondaryInputLine);
                try {
                    intArray = getArray(exprLeft, exprRight);
                }
                catch (NumberFormatException nfe) {
                    System.out.println("Please input a list of positive integers separated by commas.");
                }
            }
            
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
                intArray = getArray(permLeft, permRight);
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