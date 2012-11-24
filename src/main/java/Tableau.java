/**
 * Copyright (c) 2011 by Tyson Gern
 * Licensed under the MIT License 
 */

import javax.swing.JFrame;

/**
* This class stores a tableau of dominoes, and allows a user to add a
* domino to the tableau using a technique called "shuffling" developed
* by Devra Garfinkle.  Please see README.md for more information about
* Garfinkle's techniques.
* @author Tyson Gern (tygern@gmail.com)
*/
class Tableau {
    private int rank; // The rank (max size) of a tableau
    private Domino[] dominoes; // array of dominoes in a tableau
    private int maxLabel = 0; // largest label in the tableau

    /**
     * This constructs an empty tableau of a certain rank.
     * @param rank The rank of the tableau
     */
    public Tableau (int rank) {
        // Create an empty Domino array and set the rank.
        dominoes = new Domino[rank];
        this.rank = rank;
    }

    /**
     * This constructs the right tableau from an element w of a
     * Coxeter group.
     * @param w The element of the Coxeter group
     */
    public Tableau (Element w) {
        rank = w.getRank();
        dominoes = new Domino[rank];
        Domino temp;

        // Add a domino for each entry in w.
        for (int i = 0; i < rank; i++) {
            temp = new Domino(w.mapsTo(i + 1));
            addDomino(temp);
        }
    }
    
    /**
     * This method gets a domino with a certain label.
     * @param label The label of the domino
     * @return The domino with the label
     */
    public Domino getDomino(int label) {
        return dominoes[label - 1];
    }

    /**
     * This method determines whether the tableau has a domino with a
     * particular label/
     * @param label The label in question
     * @return true if there is a domino with the given label
     */
    public boolean inTableau(int label) {
        return dominoes[label - 1] != null;
    }

    /**
     * This method returns the current size of the tableau.
     * @return The size of the tableau
     */
    public int getSize() {
        int count = 0;
        for (int i = 1; i <= rank; i++) {
            if (inTableau(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * This method returns the rank, or maximum size, of the tableau.
     * @return The rank of the tableau
     */
    public int getRank() {
        return rank;
    }

    /**
     * This method returns the width of the tableau.
     * @return The width of the tableau
     */
    public int maxWidth() {
        return largestInRow(1);
    }

    /**
     * This method returns the height of the tableau.
     * @return The height of the tableau
     */
    public int maxHeight() {
        return largestInCol(1);
    }

    /**
     * This method adds a domino to a tableau using Garfinkle's
     * algorithms.  Please see README.md for more information about
     * Garfinkle's algorithm.
     * @param current The domino to add
     * @return Nothing
     */
    private void addDomino(Domino current) {
        int domLabel = current.getLabel();

        // If biggest, then add on to end of...
        if (domLabel > maxLabel) {
            // first column if vertical.
            if (current.getIsVertical()) {
                current.moveDomino(1, largestInCol(1) + 1);
            }
            // first row if horizontal.
            else {
                current.moveDomino(largestInRow(1) + 1, 1);
            }

            // Update tableau information
            dominoes[domLabel - 1] = current;
            maxLabel = domLabel;
        }
        // If not biggest, use \alpha map.
        else {
            // Take away dominoes with larger labels and repeat
            // process above.
            if (current.getIsVertical()) {
                current.moveDomino(1, largestInCol(1, domLabel) + 1);
            }
            else {
                current.moveDomino(largestInRow(1, domLabel) + 1, 1);
            }
            dominoes[domLabel - 1] = current;

            // Shuffle in remaining dominoes.
            for (int j = domLabel + 1; j <= rank; j++) {
                if (dominoes[j - 1] != null) {
                    shuffle(j);
                }
            }
        }
    }

    /**
     * This method gets the largest x-coordinate of a domino in a
     * given row
     * @param row The row
     * @return The largest x-coordinate in row
     */
    private int largestInRow(int row) {
        return largestInRow(row, rank + 1);
    }

    /**
     * This method gets the largest x-coordinate of a domino with
     * label less than bound in a given row
     * @param row The row
     * @param bound The bound
     * @return The largest x-coordinate in row, given the bound
     */
    private int largestInRow(int row, int bound) {
        int column = 0;
        for(int i = 1; i < bound; i++) {
            if (inTableau(i)) {
                if (dominoes[i - 1].getFirstBlock().getYVal() == row) {
                    column = Math.max(dominoes[i - 1].getFirstBlock().getXVal(), column);
                }
                if (dominoes[i - 1].getSecondBlock().getYVal() == row) {
                    column = Math.max(dominoes[i - 1].getSecondBlock().getXVal(), column);
                }
            }
        }
        return column;
    }
 
    /**
     * This method gets the largest y-coordinate of a domino in a
     * given column
     * @param column The column
     * @return The largest y-coordinate in column
     */
    private int largestInCol(int column) {
        return largestInCol(column, rank + 1);
    }

    /**
     * This method gets the largest y-coordinate of a domino with
     * label less than bound in a given column
     * @param col The column
     * @param bound The bound
     * @return The largest y-coordinate in column, given the bound
     */
    private int largestInCol(int column, int bound) {
        int row = 0;
        for(int i = 1; i < bound; i++) {
            if (inTableau(i)) {
                if (dominoes[i - 1].getFirstBlock().getXVal() == column) {
                    row = Math.max(dominoes[i - 1].getFirstBlock().getYVal(), row);
                }
                if (dominoes[i - 1].getSecondBlock().getXVal() == column) {
                    row = Math.max(dominoes[i - 1].getSecondBlock().getYVal(), row);
                }
            }
        }
        return row;
    }
 
    /**
     * This method prints the coordinates and orientation of
     * the domino with a given label in the tableau
     * @param label The label
     * @return Nothing
     */
    public void printDomino(int label) {
        if (inTableau(label)) {
            dominoes[label - 1].printInfo();
        }
        else {
            System.out.println("There is no domino with label " + label + " in the tableau.");
        }
    }

    /**
     * This method prints the labels, coordinates, and orientation of
     * the dominos in the tableau
     * @return Nothing
     */
    public void printAll() {
        System.out.println("----------------------");
        for(int i = 1; i <= rank; i++) {
            if (inTableau(i)) {
                dominoes[i - 1].printInfo();
                System.out.println("----------------------");
            }
        }
    }
    
    /**
     * This method finds the location of a domino with a certain label
     * in the tableau.
     * @param label The label
     * @return The index of the domino in the array dominoes
     */
    public int findLabel(int label) {
        if (inTableau(label)) {
            return label - 1;
        }
        return -1;
    }

    /**
     * This method shuffles a domino with a given label into the
     * tableau according to Garfinkle's algorithm.  Please see
     * README.md for more information about Garfinkles algorithms.
     * @param label The label
     * @return Nothing
     */
    private void shuffle(int label) {
        Domino current = dominoes[label - 1];
        int row = current.getFirstBlock().getYVal();
        int col = current.getFirstBlock().getXVal();
        if (inTableau(label)) {
            if (overlap(label) == 0) {
                // do nothing
            }
            else if (overlap(label) == 1) {
                // twist
                if (current.getIsVertical()) {
                    current.flipDomino();
                    current.moveDomino(largestInRow(row + 1, label) + 1, row + 1);
                }
                else {
                    current.flipDomino();
                    current.moveDomino(col + 1, largestInCol(col + 1, label) + 1);
                }
            }
            else {
                //slide
                if (current.getIsVertical()) {
                    current.moveDomino(col + 1, largestInCol(col + 1, label) + 1);
                }
                else {
                    current.moveDomino(largestInRow(row + 1, label) + 1, row + 1);
                }
            }
        }
    }

    /**
     * This method calculates how much a given domino overlapps the
     * tableau.
     * @param label The label of the domino
     * @return The number of blocks that the domino overlapps the
     * tableau
     */
    private int overlap(int label) {
        int count = 0;
        for (int i = 1; i <= rank; i++) {
            if (inTableau(i) && i != label) {
                count += dominoes[i - 1].overlap(dominoes[label - 1]);
            }
        }
        return count;
    }

    /**
     * This method outputs tikz commands to draw the tableau.  See
     * README.md for more information about tikz.
     * @return Nothing
     */
    public void tikzDraw() {
        System.out.println("");       

        // Beginning TikZ code
        System.out.println("\\begin{tikzpicture}[node distance=0 cm,outer sep = 0pt]");
        System.out.println("\\tikzstyle{ver}=[rectangle, draw, thick, minimum width=1cm, minimum height=2cm]");        
        System.out.println("\\tikzstyle{hor}=[rectangle, draw, thick, minimum width=2cm, minimum height=1cm]");
        
        for (int i = 1; i <= rank; i++) {
            if (inTableau(i)) {
                Domino current = dominoes[i-1];
                int xDom = current.getFirstBlock().getXVal();
                int yDom = current.getFirstBlock().getYVal();
                boolean vert = current.getIsVertical();

                if (vert) {
                    // Start at (0, 4)
                    System.out.println("\\node[ver] at (0 + " + xDom + ", 4 - " + yDom + ") {" + i + "};");     
                }
                else {
                    // Start at (.5, 4.5)
                    System.out.println("\\node[hor] at (.5 + " + xDom + ", 4.5 - " + yDom + ") {" + i + "};");  
                }
            }
        }

        // Ending TikZ code
        System.out.println("\\end{tikzpicture}");
        System.out.println("");       
    }

    /**
     * This method draws the tableau in a graphical window.
     * @return Nothing
     */
    public void screenDraw() {
        DrawDomino canvas = new DrawDomino(this);
        JFrame frame = new JFrame();

        frame.setSize(canvas.width, canvas.height);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
    }

    /**
     * This method determines whether two tableaux are equal.
     * @param other The other tableau
     * @return true if the two tableaux are equal
     */
    public boolean equals(Tableau other) {
        if (this.rank != other.rank) return false;
        for (int i = 1; i <= rank; i++) {
            if (inTableau(i) && !other.inTableau(i)) return false;
            if (!inTableau(i) && other.inTableau(i)) return false;
            if (inTableau(i) && other.inTableau(i)) {
                if (!getDomino(i).equals(other.getDomino(i))) return false;
            }
        }
        return true;
    }

}