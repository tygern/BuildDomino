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
        TypeD w = null;
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

    public static TypeD prompt(TypeD w) {
        Tableau tR = null;
        TypeD wInv = null;
        TypeD x = null;
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
        if (choice.equals("expression")) {
            w = fromRE();
            }
        else if (choice.equals("permutation")) {
            w = fromPerm();
            }
        else if (choice.equals("print")) {
            if (w == null) nullElement();
            else System.out.println(w);
            }
        else if (choice.equals("rightmultiply")) {
            if (w == null) {
                nullElement();
            }
            else {
                System.out.println("permutation or expression?");
                form = getString(secondaryInputLine);
                if (form.equals("permutation")) {
                    x = fromPerm();
                    System.out.println(w.rightMultiply(x));
                }
                else if (form.equals("expression")) {
                    x = fromRE(w.getRank());
                    System.out.println(w.findRE().rightMultiply(x.findRE()));
                }
                else {
                    badInput(form);
                }
            }
        }
        else if (choice.equals("leftmultiply")) {
            if (w == null) {
                nullElement();
            }
            else {
                System.out.println("Permutation or expression?");
                form = getString(secondaryInputLine);
                if (form.equals("permutation")) {
                    x = fromPerm();
                    System.out.println(w.leftMultiply(x));
                }
                else if (form.equals("expression")) {
                    x = fromRE(w.getRank());
                    System.out.println(w.findRE().leftMultiply(x.findRE()));
                }
                else {
                    badInput(form);
                }
            }
        }
        else if (choice.equals("reduced")) {
            if (w == null) nullElement();
            else System.out.println(w.findRE());
        }
        else if (choice.equals("length")) {
            if (w == null) nullElement();
            else System.out.println(w.length());
        }
        else if (choice.equals("descent")) {
            if (w == null) nullElement();
            else {
                System.out.println("Right: " + w.rightDescent());
                System.out.println("Left: " + w.leftDescent());
            }
        }
        else if (choice.equals("inverse")) {
            if (wInv == null) nullElement();
            else System.out.println(wInv);
        }
        else if (choice.equals("righttikz")) {
            if (tR == null) nullElement();
            else tR.tikzDraw();
        }
        else if (choice.equals("lefttikz")) {
            if (tL == null) nullElement();
            else tL.tikzDraw();
        }
        else if (choice.equals("heaptikz")) {
            if (wHeap == null) nullElement();
            else wHeap.tikzDraw();
        }
        else if (choice.equals("rightdraw")) {
            if (tR == null) nullElement();
            else tR.screenDraw();
        }
        else if (choice.equals("leftdraw")) {
            if (tL == null) nullElement();
            else tL.screenDraw();
        }
        else if (choice.equals("heapdraw")) {
            if (wHeap == null) nullElement();
            else wHeap.screenDraw();
        }
        else if (choice.equals("bad")) {
            if (tL == null) nullElement();
            else System.out.println(w.isBad());
        }
        else if (choice.equals("help")) {
            printHelp();
        }
        else if (choice.equals("quit") || choice.equals("q") || choice.equals("exit")) {
            System.exit(0);
        }
        else {
            badInput(choice);
        }

        return w;
    }

    public static TypeD fromRE() {
        int rank;
        int[] intArray = null;
        int highGenerator = 0;
        TypeDExpression wCox = null;

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
                wCox = new TypeDExpression(intArray, rank);
            }
            catch (NumberFormatException nfe) {
                System.out.println("Invalid element");
                intArray = null;
            }
        }

        TypeD w = wCox.toPermutation();

        return w;
    }
    public static TypeD fromRE(int rank) {
        int[] intArray = null;
        int highGenerator = 0;
        TypeDExpression wCox = null;

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
                wCox = new TypeDExpression(intArray, rank);
            }
            catch (NumberFormatException nfe) {
                System.out.println("Invalid element");
                intArray = null;
            }
        }

        TypeD w = wCox.toPermutation();

        return w;
    }

    public static TypeD fromPerm() {
        int rank = -1;
        TypeD w = null;
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
                    w = new TypeD(intArray);
                }
                catch (IllegalArgumentException iae) {
                    System.out.println("Please use a valid signed permutation.");
                }
            }
        }
        return w;
    }
}