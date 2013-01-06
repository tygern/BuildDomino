/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import javax.swing.JFrame;

/**
* This class bulids a Heap from an element of a Coxeter group.  A heap
* is a useful way of visualizing an element.
* @author Tyson Gern (tygern@gmail.com)
*/
class Heap {
    private int size; // The number of blocks in the heap.
    private int rank; // The rank of the Coxeter Group.
    private int tall; // The height of the heap
    private int wide; // The width of the heap
    private int height[]; // The height of the heap at a given row.
    private Domino[] blocks; // array of dominoes representing blocks.
    private boolean[] firstBlock; // Does the first block in the row
                                  // have a 1 or 2?
    
    /**
     * This constructs a heap from a reduced expression of an element
     * w of a Coxeter group given as a signed permutation.
     * @param w The element of the Coxeter group
     */
    public Heap (TypeD w) {
        TypeDExpression wRE = w.findRE(); // Note that this will always be reduced.
        
        size = wRE.getLength();
        blocks = new Domino[size];
        rank = wRE.getRank();
        height = new int[rank];

        firstBlock = new boolean[size + 1]; 
        for (int i = 0; i < size; i++) firstBlock[i] = false;

        for (int i = 0; i < size; i++) {
            drop(wRE.nthGenerator(i), i);
        }
        if (wide == 1) wide = 2;
    }

    /**
     * This constructs a heap from a reduced expression of an element
     * w of a Coxeter group given as a (not necessarily reduced)
     * product of generators.
     * @param w The element of the Coxeter group
     */
    public Heap (TypeDExpression w) {
        this(w.toPermutation());
    }

    /**
     * This method adds a block with label "label" to a heap.
     * @param label The label of the domino to add
     * @param index The index of the domino to add
     * @return Nothing
     */
    private void drop(int label, int index) {
        int col;
        int row;

        if (label <= 2) {
            col = 0;
            if (firstBlock[height[1]]) {
                row = height[1] - 1;
            }
            else {
                row = height[1];
                height[1] += 1;
                firstBlock[height[1]] = true;
            }
        }
        else {
            col = label - 2;
            row = Math.max(height[col], height[col + 1]);
            height[col] = row + 1;
            height[col + 1] = row + 1;
        }

        if (row + 1 > tall) tall = row + 1;
        if (label > wide) wide = label;

        blocks[index] = new Domino(label, col, row, false); 
    }

    /**
     * This method returns the number of blocks in the heap.
     * @return The size of the heap
     */
    public int getSize() {
        return size;
    }

    /**
     * This method returns the rank of the Coxeter group
     * @return The rank of the Coxeter group
     */
    public int getRank() {
        return rank;
    }

    /**
     * This method returns block in a given index
     * @param index The index of the block
     * @return The size of the heap
     */
    public Domino getBlock(int index) {
        return blocks[index];
    }

    /**
     * This method returns the width of the heap.
     * @return The width of the heap
     */
    public int maxWidth() {
        return wide;
    }

    /**
     * This method returns the height of the heap.
     * @return The height of the heap
     */
    public int maxHeight() {
        return tall;
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
        System.out.println("\\tikzstyle{hor}=[rectangle, draw, thick, minimum width=2cm, minimum height=1cm]");
        
        for (int i = 0; i < size; i++) {
            Domino current = blocks[i];
            int label = current.getLabel();
            int x = current.getFirstBlock().getXVal();
            int y = current.getFirstBlock().getYVal();

            if (label == 1) {
                System.out.println("\\node[hor] at ( " + x + ", " + y + ") {1\\phantom{ 2}};");
            }
            else if (label == 2) {
                System.out.println("\\node[hor] at ( " + x + ", " + y + ") {\\phantom{1 }2};");
            }
            else {
                System.out.println("\\node[hor] at ( " + x + ", " + y + ") {" + label + "};");
            }
        }
        
        // Ending TikZ code
        System.out.println("\\end{tikzpicture}");
        System.out.println("");       
    }

    /**
     * This method draws the heap in a graphical window.
     * @return Nothing
     */
    public void screenDraw() {
        DrawHeap canvas = new DrawHeap(this);
        JFrame frame = new JFrame();

        frame.setSize(canvas.width, canvas.height);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
    }
}